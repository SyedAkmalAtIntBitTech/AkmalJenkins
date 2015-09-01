/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.dao.ScheduleDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mohamed
 */
@WebServlet(name = "ScheduleEmailServlet", urlPatterns = {"/ScheduleEmail"})
public class ScheduleEmailServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            if ( session.getAttribute("UID") == null){
                Map<String, Object> error = new HashMap<>();
                error.put("error", "User is not logged in");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().flush();
                response.setContentType("application/json");
                return;
            }
            Integer userId = Integer.parseInt(session.getAttribute("UID").toString());
            Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            if ( requestBodyMap == null){
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Request body is missing");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                response.setContentType("application/json");
                return;
            }
            
            List<String> errorMsgs = validateRequestBody(requestBodyMap);
            if ( !errorMsgs.isEmpty()){
                Map<String, Object> error = new HashMap<>();
                error.put("error", errorMsgs);
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                response.setContentType("application/json");
                return;
            }
            Map<String, Integer> idMap = ScheduleDAO.addToScheduledEmailList(
                    userId,
                    requestBodyMap.get("email_subject").toString(),
                    requestBodyMap.get("email_body").toString(),
                    requestBodyMap.get("from_email_address").toString(),
                    requestBodyMap.get("email_list").toString(),
                    requestBodyMap.get("from_name").toString(),
                    requestBodyMap.get("to_email_addresses").toString().split(","),
                    requestBodyMap.get("schedule_title").toString(),
                    new Timestamp(Long.parseLong(requestBodyMap.get("schedule_time").toString()))
            );
            response.getWriter().write(AppConstants.GSON.toJson(idMap));
            response.getWriter().flush();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex){
            Logger.getLogger(ScheduleEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Invalid format for schedule time.");
            response.getWriter().write(AppConstants.GSON.toJson(error));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().flush();
            response.setContentType("application/json");
            return;
        }
    }
    
    private List<String> validateRequestBody(Map<String, Object> requestBodyMap){
        List<String> errorMsgs = new ArrayList<>();
        if ( !mapContainsKey(requestBodyMap, "email_subject")){
            errorMsgs.add("Email subject is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "to_email_addresses")){
            errorMsgs.add("To email address is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "email_body")){
            errorMsgs.add("Email body is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "email_list")){
            errorMsgs.add("Email List name is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "reply_to_email_address")){
            errorMsgs.add("Reply to email address is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "from_email_address")){
            errorMsgs.add("From email address is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "from_name")){
            errorMsgs.add("From name is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "schedule_title")){
            errorMsgs.add("Schedule title is missing");
        }
        if ( !mapContainsKey(requestBodyMap, "schedule_time")){
            errorMsgs.add("Schedule time is missing");
        }
        return errorMsgs;
    }
    
    private boolean mapContainsKey(Map<String, Object> requestBodyMap, String key){
        if ( !requestBodyMap.containsKey(key) || 
                requestBodyMap.get(key) == null ||
                StringUtils.isEmpty(key)){
            return false;
        }
        return true;
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
