/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.MindBodyConstants;
import com.google.gson.Gson;
import com.mindbodyonline.clients.api._0_5.GetActivationCodeResult;
import com.mindbodyonline.clients.api._0_5Class.Class;
import com.mindbodyonline.clients.api._0_5Class.ArrayOfClass;
import com.mindbodyonline.clients.api._0_5Class.ArrayOfClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.ClassDescription;
import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.GetClassesResult;
import com.mindbodyonline.clients.api._0_5Class.GetEnrollmentsResult;
import com.mindbodyonline.clients.api._0_5Class.Staff;
import com.mindbodyonline.clients.api._0_5Staff.ArrayOfStaff;
import com.mindbodyonline.clients.api._0_5Staff.GetStaffResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import mindbody.controller.MindBodyClass;
import mindbody.controller.MindBodyDuration;
import mindbody.controller.MindBodyProcessedData;
import org.json.JSONException;
import org.json.simple.JSONArray;

/**
 *
 * @author intbit
 */
public class MindBodyDataServlet extends BrndBotBaseHttpServlet {

    static final SimpleDateFormat newFormat = new SimpleDateFormat("MMMMM dd h:mm a");
    static final SimpleDateFormat newEnrollmentFormat = new SimpleDateFormat("MMMMM dd yyyy");
    static final SimpleDateFormat existingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        getSqlMethodsInstance().session = request.getSession();

        PrintWriter out = response.getWriter();
        MindBodyClass mind_body_class = null;
        try {

            MindBodyProcessedData mind_body_processed_data = null;
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            String query = request.getParameter("query");

            String mindbody_query = request.getParameter("mindbody_query");
            org.json.simple.JSONObject json_mindbody_activation = new org.json.simple.JSONObject();

            if (query != null) {
                Boolean isActivated = false;
                if (query.equalsIgnoreCase("isMindBodyActivated")) {
                    isActivated = checkIfActivated(user_id);
                    if (!isActivated) {
                        Integer studio_id = getSqlMethodsInstance().getStudioID(user_id);
                        int[] siteids = new int[]{studio_id};
                        mind_body_class = new MindBodyClass(siteids);

                        json_mindbody_activation.put("status", "unactivated");

                        GetActivationCodeResult result = mind_body_class.getActivationCode();
                        if (result.getStatus() == com.mindbodyonline.clients.api._0_5.StatusCode.SUCCESS) {
                            json_mindbody_activation.put("activation_link", result.getActivationLink());
                        } else {
                            json_mindbody_activation.put("activation_link", "");
                        }
                    } else {
                        json_mindbody_activation.put("status", "activated");
                    }

                } else if (query.equalsIgnoreCase("activationlink")) {
                    String studio_id = (String) request.getParameter("studioid");
                    int[] siteids = new int[]{Integer.parseInt(studio_id)};
                    mind_body_class = new MindBodyClass(siteids);

                    GetActivationCodeResult result = mind_body_class.getActivationCode();
                    if (result.getStatus() == com.mindbodyonline.clients.api._0_5.StatusCode.SUCCESS) {
                        json_mindbody_activation.put("activation_link", result.getActivationLink());
                    } else {
                        json_mindbody_activation.put("activation_link", result.getActivationLink());
                    }
                }
                String json = new Gson().toJson(json_mindbody_activation);
                response.setContentType("application/json");
                response.getWriter().write(json);

            } else if (mindbody_query != null) {
                Integer studio_id = getSqlMethodsInstance().getStudioID(user_id);
                int[] siteids = new int[]{studio_id};
                mind_body_class = new MindBodyClass(siteids);
                mind_body_processed_data = getDataForQueryOrSubCategory(mind_body_class, mindbody_query);
                getSqlMethodsInstance().session.setAttribute(getSqlMethodsInstance().k_mind_body + mindbody_query, mind_body_processed_data.getData_hash_map());
                String jsonn = new Gson().toJson(mind_body_processed_data.getJSON());
                response.setContentType("application/json");
                out.write(jsonn);

            } else {
                String sub_category_name = (String) getSqlMethodsInstance().session.getAttribute("sub_category_name");
                Integer studio_id = getSqlMethodsInstance().getStudioID(user_id);
                int[] siteids = new int[]{studio_id};
                mind_body_class = new MindBodyClass(siteids);
                sub_category_name = sub_category_name.toLowerCase();
                mind_body_processed_data = getDataForQueryOrSubCategory(mind_body_class, sub_category_name);
                getSqlMethodsInstance().session.setAttribute(getSqlMethodsInstance().k_mind_body, mind_body_processed_data.getData_hash_map());

                String jsonn = new Gson().toJson(mind_body_processed_data.getJSON());
//                logger.log(Level.INFO, jsonn);
                response.setContentType("application/json");
                out.write(jsonn);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

        } finally {
            out.close();
        }
    }

    private MindBodyProcessedData getDataForQueryOrSubCategory(MindBodyClass mind_body_class, String mindbody_query) throws JSONException, ParseException {

        //Make change in site.js, MindBodyDataServlet and MindBodyConstants
        MindBodyProcessedData mind_body_processed_data = null;
        if (mindbody_query.equalsIgnoreCase(MindBodyConstants.kPromote_new_staff_query)) {
            GetStaffResult staffResult = mind_body_class.getStaff();
            mind_body_processed_data = getMindBodyProcessedStaffData(staffResult);
        } else if (mindbody_query.equalsIgnoreCase(MindBodyConstants.kPromote_staff_spotlight_query)) {
            GetStaffResult staffResult = mind_body_class.getStaff();
            mind_body_processed_data = getMindBodyProcessedStaffData(staffResult);
        } else if (mindbody_query.equalsIgnoreCase(MindBodyConstants.kPromote_workshop_query)) {
            GetEnrollmentsResult enrollmentsResult = mind_body_class.getEnrollments(MindBodyDuration.All);
            mind_body_processed_data = getMindBodyProcessedEnrollmentData(enrollmentsResult);
        } else if (mindbody_query.equalsIgnoreCase(MindBodyConstants.kPromote_todays_workshop_query)) {
            GetEnrollmentsResult enrollmentsResult = mind_body_class.getEnrollments(MindBodyDuration.Today);
            mind_body_processed_data = getMindBodyProcessedEnrollmentData(enrollmentsResult);
        } else if (mindbody_query.equalsIgnoreCase(MindBodyConstants.kPromote_upcoming_workshop_query)) {
            GetEnrollmentsResult enrollmentsResult = mind_body_class.getEnrollments(MindBodyDuration.All);
            mind_body_processed_data = getMindBodyProcessedEnrollmentData(enrollmentsResult);
        } else if (mindbody_query.equalsIgnoreCase(MindBodyConstants.kPromote_class_query)) {
            GetClassesResult classResult = mind_body_class.getClasses(MindBodyDuration.Thirty);
            mind_body_processed_data = getMindBodyProcessedClassData(classResult);
        } else if (mindbody_query.equalsIgnoreCase(MindBodyConstants.kPromote_todays_class_query)) {
            GetClassesResult classResult = mind_body_class.getClasses(MindBodyDuration.Today);
            mind_body_processed_data = getMindBodyProcessedClassData(classResult);
        }

        return mind_body_processed_data;
    }

    private MindBodyProcessedData getMindBodyProcessedEnrollmentData(GetEnrollmentsResult result) throws JSONException, ParseException {
        HashMap<String, Object> hash_map = new HashMap<>();
        MindBodyProcessedData mind_body_process_data = new MindBodyProcessedData();
        
        JSONArray json_column_header_array = new JSONArray();
        json_column_header_array.add(0, "Workshop");
        json_column_header_array.add(1, "Teacher");
        json_column_header_array.add(2, "Date");


        ArrayOfClassSchedule array_of_enrollments = result.getEnrollments();
        JSONArray json_data_array = new JSONArray();
        if (array_of_enrollments != null && array_of_enrollments.getClassSchedule() != null) {
            List<ClassSchedule> enrollmentsList = array_of_enrollments.getClassSchedule();
            for (int i = 0; i < enrollmentsList.size(); i++) {

                ClassSchedule enrollmentInstance = enrollmentsList.get(i);
                String enrollment_id = String.valueOf(enrollmentInstance.getID().getValue());

                hash_map.put(enrollment_id, enrollmentInstance);

                JAXBElement<XMLGregorianCalendar> calendarStart = enrollmentInstance.getStartDate();
                JAXBElement<XMLGregorianCalendar> calendarEnd = enrollmentInstance.getEndDate();

                ClassDescription class_description = enrollmentInstance.getClassDescription();

                String name = class_description.getName();
                Staff staff = enrollmentInstance.getStaff();

                XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();
                XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                Date newDate = existingFormat.parse(calendarStartDateTime.toString());
                String newDateString = newEnrollmentFormat.format(newDate);

                org.json.simple.JSONObject newJSONObject = new org.json.simple.JSONObject();
                newJSONObject.put("column1", name);
                newJSONObject.put("column2", staff.getName());
                newJSONObject.put("column3", newDateString);
                newJSONObject.put("id", enrollment_id);
                json_data_array.add(newJSONObject);

            }

            String jsonString = json_data_array.toString();//new Gson().toJson(json_data_array);
            mind_body_process_data.setData_hash_map(hash_map);
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setColumnHeader(json_column_header_array);
            mind_body_process_data.setTitle("Please select a work shop to promote:");
        } else {
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setTitle("There are no workshops to promote.");
        }
        return mind_body_process_data;
    }

    private MindBodyProcessedData getMindBodyProcessedClassData(GetClassesResult result) throws JSONException, ParseException {
        HashMap<String, Object> hash_map = new HashMap<String, Object>();
        MindBodyProcessedData mind_body_process_data = new MindBodyProcessedData();
        
        JSONArray json_column_header_array = new JSONArray();
        json_column_header_array.add(0, "Class");
        json_column_header_array.add(1, "Teacher");
        json_column_header_array.add(2, "Date");
        
        ArrayOfClass array_of_classes = result.getClasses();
        JSONArray json_data_array = new JSONArray();
        if (array_of_classes != null && array_of_classes.getClazz() != null) {
            List<Class> classesList = array_of_classes.getClazz();
            for (int i = 0; i < classesList.size(); i++) {

                Class classInstance = classesList.get(i);
                String class_id = String.valueOf(classInstance.getID().getValue());

                hash_map.put(class_id, classInstance);

                JAXBElement<XMLGregorianCalendar> calendarStart = classInstance.getStartDateTime();
                JAXBElement<XMLGregorianCalendar> calendarEnd = classInstance.getEndDateTime();
                Staff staff = classInstance.getStaff();

                ClassDescription class_description = classInstance.getClassDescription();

                String name = class_description.getName();

                XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();
                XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                Date newDate = existingFormat.parse(calendarStartDateTime.toString());
                String newDateString = newFormat.format(newDate);

                org.json.simple.JSONObject newJSONObject = new org.json.simple.JSONObject();
                newJSONObject.put("column1", name);
                newJSONObject.put("column2", staff.getName());
                newJSONObject.put("column3", newDateString);
                newJSONObject.put("id", class_id);

                json_data_array.add(newJSONObject);

            }

            String jsonString = json_data_array.toString();//new Gson().toJson(json_data_array);
            mind_body_process_data.setData_hash_map(hash_map);
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setColumnHeader(json_column_header_array);
            mind_body_process_data.setTitle("Please select a class to promote:");
        } else {
            mind_body_process_data.setTitle("There are no classes to promote");
            mind_body_process_data.setJsonData(json_data_array);
        }
        return mind_body_process_data;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Boolean checkIfActivated(Integer user_id) throws SQLException {
        Integer studio_id = getSqlMethodsInstance().getStudioID(user_id);
        int[] siteids = new int[]{studio_id};
        MindBodyClass mind_body_class = new MindBodyClass(siteids);
        return mind_body_class.isActivated();
    }

    private MindBodyProcessedData getMindBodyProcessedStaffData(GetStaffResult result) throws JSONException {
        HashMap<String, Object> hash_map = new HashMap<>();
        MindBodyProcessedData mind_body_process_data = new MindBodyProcessedData();
        ArrayOfStaff array_of_staff = result.getStaffMembers();
        JSONArray json_data_array = new JSONArray();
        
        JSONArray json_column_header_array = new JSONArray();
        json_column_header_array.add(0, "Teacher Name");

        if (array_of_staff != null && array_of_staff.getStaff() != null) {
            List<com.mindbodyonline.clients.api._0_5Staff.Staff> staffList = array_of_staff.getStaff();
            for (int i = 0; i < staffList.size(); i++) {

                com.mindbodyonline.clients.api._0_5Staff.Staff staffInstance = staffList.get(i);
                String staff_id = String.valueOf(staffInstance.getID().getValue());
             
                hash_map.put(staff_id, staffInstance);

                org.json.simple.JSONObject newJSONObject = new org.json.simple.JSONObject();
                newJSONObject.put("column1", staffInstance.getName());
                newJSONObject.put("column2", " ");
                newJSONObject.put("column3", " ");
                newJSONObject.put("id", staff_id);

                json_data_array.add(newJSONObject);

            }

            String jsonString = json_data_array.toString();//new Gson().toJson(json_data_array);
            mind_body_process_data.setData_hash_map(hash_map);
            mind_body_process_data.setJsonData(json_data_array);
            mind_body_process_data.setColumnHeader(json_column_header_array);
            mind_body_process_data.setTitle("Please select a teacher to promote:");

        } else {
            mind_body_process_data.setJsonData(json_data_array);

            mind_body_process_data.setTitle("There are no teachers to promote");

        }
        return mind_body_process_data;
    }
}
