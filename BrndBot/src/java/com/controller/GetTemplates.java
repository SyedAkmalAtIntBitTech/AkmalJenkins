/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.dao.CheckTemplates;
import com.intbit.util.AuthenticationUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author development
 */
public class GetTemplates extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetTemplates.class.getName());

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
        /* TODO output your page here. You may use following sample code. */
        try {
            if (!AuthenticationUtil.isUserLoggedIn(request)){
                AuthenticationUtil.printAuthErrorToResponse(response);
                return;
            }
            Integer userId = AuthenticationUtil.getUUID(request);
            
            Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            if ( requestBodyMap == null){
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Request body is missing");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                return;
            }
            
            Double category_id = (Double)requestBodyMap.get("category_id");
            Double sub_category_id = (Double)requestBodyMap.get("sub_category_id");
            CheckTemplates chktemplates = new CheckTemplates();
            Integer email_template_availability = chktemplates.checkTemplates(category_id.intValue(),
                    sub_category_id.intValue(), "email", userId);
            Integer social_template_availability = chktemplates.checkTemplates(category_id.intValue(),
                    sub_category_id.intValue(), "social", userId);
            Integer social_template_print = chktemplates.checkTemplates(category_id.intValue(),
                    sub_category_id.intValue(), "print", userId);
            Integer social_template_download = chktemplates.checkTemplates(category_id.intValue(),
                    sub_category_id.intValue(), "download", userId);

            JSONObject responseObject = new JSONObject();
            
            responseObject.put("email_template_availability", email_template_availability);
            responseObject.put("social_template_availability", social_template_availability);
            responseObject.put("social_template_print", social_template_print);
            responseObject.put("social_template_download", social_template_download);
            String json = new Gson().toJson(responseObject);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error caused while checking the saved template", e);
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
