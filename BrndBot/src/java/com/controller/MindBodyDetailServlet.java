/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbittech.utility.AppConstants;
import com.intbittech.enums.ScheduledEntityType;
import com.mindbodyonline.clients.api._0_5Class.Class;
import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Staff.Staff;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.intbittech.mindbody.MindBodyDataMapper;
import org.json.simple.JSONObject;

/**
 *
 * @author intbit
 */
public class MindBodyDetailServlet extends BrndBotBaseHttpServlet {

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
        PrintWriter out = response.getWriter();
        getSqlMethodsInstance().session = request.getSession(true);
        try {
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            Integer organization_id = 0, block_id = 0;
            String mindbody_query = null, editor_type = null;
            String category_id = (String) getSqlMethodsInstance().session.getAttribute("category_id");
            String sub_category_id = (String) getSqlMethodsInstance().session.getAttribute("sub_category_id");
            String sub_category_name = (String) getSqlMethodsInstance().session.getAttribute("sub_category_name");

            String mindbody_data_id = "";
            Integer model_mapper_id = 0;
            HashMap<String, Object> mindbody_hash_map = null;

            Enumeration params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String paramName = (String) params.nextElement();
                logger.log(Level.INFO, "Attribute Name - " + paramName + ", Value - " + request.getParameter(paramName));
            }
            organization_id = getSqlMethodsInstance().getOrganizationID(user_id);
            getSqlMethodsInstance().session = request.getSession(true);
            if (!(request.getParameter("mindbody_id").equals(""))) {
                mindbody_data_id = request.getParameter("mindbody_id");
            }
            if (request.getParameter("model_mapper_id") != null){
                model_mapper_id = Integer.parseInt(request.getParameter("model_mapper_id"));
            }
            editor_type = request.getParameter("editor_type");
            if (request.getParameter("query") != null && request.getParameter("query").equalsIgnoreCase("block")) {
                mindbody_query = request.getParameter("mindbody_query");
                mindbody_hash_map = (HashMap<String, Object>) getSqlMethodsInstance().session.getAttribute(getSqlMethodsInstance().k_mind_body + mindbody_query);
                sub_category_name = mindbody_query;//doing this since its a block and we are checking against the query to send appropriate file
                block_id = Integer.parseInt(request.getParameter("block_id"));
            } else {
                mindbody_hash_map = (HashMap<String, Object>) getSqlMethodsInstance().session.getAttribute(getSqlMethodsInstance().k_mind_body);
            }

            String mapperFileName = getSqlMethodsInstance().getMapperFile(user_id, organization_id, Integer.parseInt(category_id), Integer.parseInt(sub_category_id), model_mapper_id, block_id, editor_type);
            String editor_mapper_file_name = AppConstants.BASE_XML_UPLOAD_PATH + File.separator + mapperFileName + ".xml";

            JSONObject mapped_json_object = null;
            sub_category_name = sub_category_name.toLowerCase();
            if (sub_category_name.contains("class")) {
                Object selected_object = mindbody_hash_map.get(mindbody_data_id);
                Class mindbody_class = (Class) selected_object;
                if (editor_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
//                    mapped_json_object = MindBodyDataMapper.mapClassDataRaw(mindbody_class);
                } else {
                    mapped_json_object = MindBodyDataMapper.mapClassData(mindbody_class, editor_mapper_file_name);
                }
            } else if (sub_category_name.contains("work shop") || sub_category_name.contains("workshop")) {
                Object selected_object = mindbody_hash_map.get(mindbody_data_id);
                ClassSchedule mindbody_enrollments = (ClassSchedule) selected_object;
                if (editor_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
//                    mapped_json_object = MindBodyDataMapper.mapEnrollmentDataRaw(mindbody_enrollments);
                } else {
                mapped_json_object = MindBodyDataMapper.mapEnrollmentData(mindbody_enrollments, editor_mapper_file_name);
                }
            } else if (sub_category_name.contains("staff")) {
                Object selected_object = mindbody_hash_map.get(mindbody_data_id);
                Staff mindbody_staff = (Staff) selected_object;
                if (editor_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
//                    mapped_json_object = MindBodyDataMapper.mapStaffDataRaw(mindbody_staff);
                } else {
                mapped_json_object = MindBodyDataMapper.mapStaffData(mindbody_staff, editor_mapper_file_name);
                }
            }

            if (mapped_json_object != null) {
                response.setContentType("application/json");
                response.getWriter().write(mapped_json_object.toString());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while displaying the mindbody data:", getSqlMethodsInstance().error));
        } finally {
            out.close();
            getSqlMethodsInstance().closeConnection();
        }
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

}
