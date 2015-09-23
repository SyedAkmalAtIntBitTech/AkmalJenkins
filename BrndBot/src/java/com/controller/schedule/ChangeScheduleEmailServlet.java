/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.intbit.AppConstants;
import com.intbit.TemplateStatus;
import com.intbit.dao.ScheduleDAO;
import com.intbit.util.AuthenticationUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author development
 */
public class ChangeScheduleEmailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if ( !AuthenticationUtil.isUserLoggedIn(request)){
                AuthenticationUtil.printAuthErrorToResponse(response);
                return;
            }
            Integer userId = AuthenticationUtil.getUUID(request);
            Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            String type = (String)requestBodyMap.get("type");
            String schedule_ids = (String)requestBodyMap.get("schedule_ids");
            if (type.equalsIgnoreCase("update")){
//                 Map<String, Integer> idMap = ScheduleDAO.updateToScheduledEmailList(
//                    schedule_id,
//                    userId,
//                    requestBodyMap.get("email_subject").toString(),
//                    requestBodyMap.get("email_body").toString(),
//                    requestBodyMap.get("from_email_address").toString(),
//                    requestBodyMap.get("email_list").toString(),
//                    requestBodyMap.get("from_name").toString(),
//                    requestBodyMap.get("reply_to_email_address").toString(),
//                    requestBodyMap.get("to_email_addresses").toString().split(","),
//                    requestBodyMap.get("schedule_title").toString(),
//                    scheduleDesc, 
//                    new Timestamp(schedule.longValue()),
//                    TemplateStatus.template_saved.toString()
//            );
            }else if (type.equalsIgnoreCase("delete")){
                ScheduleDAO.deleteEmailSchedules(userId, schedule_ids);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            }
        }catch (Exception ex){
            Logger.getLogger(ScheduleEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
