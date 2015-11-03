/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.dao.ScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mohamed
 */
@WebServlet(name = "GetScheduledEntitiesServlet", urlPatterns = {"/GetScheduledEntities"})
public class GetScheduledEntitiesServlet extends HttpServlet {
    private static final Logger logger = 
            Logger.getLogger(GetScheduledEntitiesServlet.class.getName());
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
        response.setContentType("application/json");
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("UID") == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "User is not logged in");
                response.getWriter().write(AppConstants.GSON.toJson(error));
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().flush();
                return;
            }
            Integer userId = Integer.parseInt(session.getAttribute("UID").toString());
            List<String> errorMsgs = new ArrayList<>();
            
            if ( StringUtils.isEmpty(request.getParameter("from")) ){
                errorMsgs.add("from date parameter is missing");
            }
            if ( StringUtils.isEmpty(request.getParameter("to")) ){
                errorMsgs.add("to date parameter is missing");
            }
            
            if ( !errorMsgs.isEmpty() ){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("error", errorMsgs);
                response.getWriter().write(AppConstants.GSON.toJson(responseMap));
                response.getWriter().flush();
                return;
            }
            
            LocalDate fromDate = null;
            LocalDate toDate = null;
            //Dates have to follow the format: 2011-12-03
            try{
                fromDate = LocalDate.parse(request.getParameter("from"));
            }catch(DateTimeParseException ex){
                errorMsgs.add("from parameter is not in the required yyyy-mm-dd format");
                logger.log(Level.SEVERE, "", ex);
            }
            
            try{
                toDate = LocalDate.parse(request.getParameter("to"));
            }catch(DateTimeParseException ex){
                errorMsgs.add("to parameter is not in the required yyyy-mm-dd format");
                logger.log(Level.SEVERE, "", ex);
            }
            if ( !errorMsgs.isEmpty() ){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("error", errorMsgs);
                response.getWriter().write(AppConstants.GSON.toJson(responseMap));
                response.getWriter().flush();
                return;
            }
            
            JSONObject scheduledEntities = 
                     ScheduleDAO.getScheduledEntities(userId, fromDate, toDate);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(AppConstants.GSON.toJson(scheduledEntities));
            response.getWriter().flush();
        } catch (SQLException ex) {
            Logger.getLogger(GetScheduledEntitiesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            Logger.getLogger(GetScheduledEntitiesServlet.class.getName()).log(Level.SEVERE, null, e);
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
