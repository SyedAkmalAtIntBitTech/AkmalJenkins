/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.intbit.AppConstants;
import com.intbit.dao.ScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "GetScheduledEmailDetailServlet", urlPatterns = {"/GetScheduledEmailDetail"})
public class GetScheduledEmailDetailServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetScheduledEmailDetailServlet.class.getName());

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
        HttpSession session = request.getSession();
        if (session.getAttribute("UID") == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "User is not logged in");
            response.getWriter().write(AppConstants.GSON.toJson(error));
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().flush();
            response.setContentType("application/json");
            return;
        }
        Integer userId = Integer.parseInt(session.getAttribute("UID").toString());
        if ( StringUtils.isEmpty(request.getParameter("schedule_id")) ){
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Schedule id is missing");
            response.getWriter().write(AppConstants.GSON.toJson(error));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().flush();
            response.setContentType("application/json");
            return;
        }
        
        try{
            Integer scheduleEmailId = Integer.parseInt(request.getParameter("schedule_id"));
            Map<String, Object> scheduleEmailDetails = 
                    ScheduleDAO.getScheduleEmailDetails(userId, scheduleEmailId);
            response.getWriter().write(AppConstants.GSON.toJson(scheduleEmailDetails));
            response.getWriter().flush();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
        }catch(NumberFormatException ex){
            logger.log(Level.SEVERE, null, ex);
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Schedule id cannot be parsed to integer");
            response.getWriter().write(AppConstants.GSON.toJson(error));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().flush();
            response.setContentType("application/json");
        } catch (SQLException ex) {
            Logger.getLogger(GetScheduledEmailDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
