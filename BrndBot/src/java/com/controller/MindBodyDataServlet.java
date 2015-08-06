/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

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
import com.mindbodyonline.clients.api._0_5Class.StatusCode;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import mindbody.controller.MindBodyClass;
import mindbody.controller.MindBodyProcessedData;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

/**
 *
 * @author intbit
 */
public class MindBodyDataServlet extends HttpServlet {

    SqlMethods sql_methods = new SqlMethods();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        sql_methods.session = request.getSession(true);

        PrintWriter out = response.getWriter();
        MindBodyClass mind_body_class = null;
        try {

            MindBodyProcessedData mind_body_processed_data = null;
//            Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
            Integer user_id = 40;

            String query_mindbody = request.getParameter("query");
            org.json.simple.JSONObject json_mindbody_activation = new org.json.simple.JSONObject();

            if (query_mindbody != null ) {
                Boolean isActivated = false;
                if (query_mindbody.equalsIgnoreCase("isMindBodyActivated")) {
                    isActivated = checkIfActivated(user_id);
                    if (!isActivated) {
                        Integer studio_id = sql_methods.getStudioID(user_id);
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

                } else if (query_mindbody.equalsIgnoreCase("activationlink")) {
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

            } else {
                String sub_category_name = (String) sql_methods.session.getAttribute("sub_category_name");
                Integer studio_id = sql_methods.getStudioID(user_id);
                int[] siteids = new int[]{studio_id};
                mind_body_class = new MindBodyClass(siteids);
                if (sub_category_name.equals("promote todays class")) {
                    GetClassesResult classResult = mind_body_class.getTodaysClass();
                    mind_body_processed_data = getMindBodyProcessedClassData(classResult);
                } else if (sub_category_name.equals("promote class")) {
                    GetClassesResult classResult = mind_body_class.getClasses();
                    mind_body_processed_data = getMindBodyProcessedClassData(classResult);
                } else if (sub_category_name.equals("promote work shop")) {
                    GetEnrollmentsResult enrollmentsResult = mind_body_class.getTodaysEnrollments();
                    mind_body_processed_data = getMindBodyProcessedEnrollmentData(enrollmentsResult);
                }
                sql_methods.session.setAttribute(sql_methods.k_mind_body, mind_body_processed_data.getData_hash_map());
                response.setContentType("application/json");
                out.write(mind_body_processed_data.getJsonDisplayString());
            }
//            int i = 7335;

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private MindBodyProcessedData getMindBodyProcessedEnrollmentData(GetEnrollmentsResult result) throws JSONException {
        HashMap<String, Object> hash_map = new HashMap<String, Object>();
        MindBodyProcessedData mind_body_process_data = null;
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

                XMLGregorianCalendar calendarStartDateTime = (XMLGregorianCalendar) calendarStart.getValue();
                XMLGregorianCalendar calendarEndDateTime = (XMLGregorianCalendar) calendarEnd.getValue();

                JSONObject newJSONObject = new JSONObject();
                newJSONObject.put("column1", name);
                newJSONObject.put("column2", calendarStartDateTime.toString());
                newJSONObject.put("column3", calendarEndDateTime.toString());
                newJSONObject.put("id", enrollment_id);
                System.out.println(enrollmentInstance.getID().getValue());
                json_data_array.add(newJSONObject);

            }
            String jsonString = json_data_array.toString();//new Gson().toJson(json_data_array);
            mind_body_process_data = new MindBodyProcessedData(hash_map, jsonString);

        }
        return mind_body_process_data;
    }

    private MindBodyProcessedData getMindBodyProcessedClassData(GetClassesResult result) throws JSONException {
        HashMap<String, Object> hash_map = new HashMap<String, Object>();
        MindBodyProcessedData mind_body_process_data = null;
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

                JSONObject newJSONObject = new JSONObject();
                newJSONObject.put("column1", name);
                newJSONObject.put("column2", staff.getName());
                newJSONObject.put("column3", calendarStartDateTime.toString() + "\n" + calendarEndDateTime.toString());
                newJSONObject.put("id", class_id);

                json_data_array.add(newJSONObject);

            }
            String jsonString = json_data_array.toString();//new Gson().toJson(json_data_array);
            mind_body_process_data = new MindBodyProcessedData(hash_map, jsonString);

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
        Integer studio_id = sql_methods.getStudioID(user_id);
        int[] siteids = new int[]{studio_id};
        MindBodyClass mind_body_class = new MindBodyClass(siteids);

        GetClassesResult classResult = mind_body_class.getTodaysClass();
        return classResult.getStatus().equals(StatusCode.SUCCESS);
    }

}
