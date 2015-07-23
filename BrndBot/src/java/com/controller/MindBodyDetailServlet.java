/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import com.mindbodyonline.clients.api._0_5Class.Class;
import com.mindbodyonline.clients.api._0_5Class.ClassSchedule;
import com.mindbodyonline.clients.api._0_5Class.GetClassesResult;
import com.mindbodyonline.clients.api._0_5Class.GetEnrollmentsResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mindbody.controller.MindBodyDataMapper;
import org.json.JSONObject;

/**
 *
 * @author intbit
 */

public class MindBodyDetailServlet extends HttpServlet {
SqlMethods sql_methods = new SqlMethods();
protected String xml_file_directory = null;

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
        PrintWriter out = response.getWriter();
        String mindbody_data_id = "";
        try {
            sql_methods.session = request.getSession(true);
            if (request.getParameter("mindbody_id") != null){
                mindbody_data_id = request.getParameter("mindbody_id");
            }
//          String socialEditorLayoutFileName = request.getParameter("fileName");
            xml_file_directory = getServletContext().getRealPath("") + File.separator +"xml";
            String socialEditorLayoutFileName = xml_file_directory +File.separator + "class_model_mapper1.xml";

            String sub_category_id = (String)sql_methods.session.getAttribute("sub_category_id");
            String sub_category_name = (String)sql_methods.session.getAttribute("sub_category_name");

            HashMap<String, Object> hash_map = (HashMap<String, Object>)sql_methods.session.getAttribute(sql_methods.k_mind_body);
            JSONObject mapped_json_object = null;
            Object selected_object = hash_map.get(mindbody_data_id);
            
            if(sub_category_name.equals("promote todays class")){
                 Class mindbody_class = (Class)selected_object;
                 mapped_json_object = MindBodyDataMapper.mapTodaysClassData(mindbody_class, socialEditorLayoutFileName);
            } else if (sub_category_name.equals("promote class")){
                 Class mindbody_class = (Class)selected_object;
                 mapped_json_object = MindBodyDataMapper.mapClassData(mindbody_class, socialEditorLayoutFileName);
            }else if (sub_category_name.equals("promote work shop")){
                 ClassSchedule mindbody_enrollments = (ClassSchedule)selected_object;
                 mapped_json_object = MindBodyDataMapper.mapEnrollmentData(mindbody_enrollments, socialEditorLayoutFileName);
            }
                    
            if (mapped_json_object != null){

                response.setContentType("application/json");
                response.getWriter().write(mapped_json_object.toString());
                
            }
            
        }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
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
