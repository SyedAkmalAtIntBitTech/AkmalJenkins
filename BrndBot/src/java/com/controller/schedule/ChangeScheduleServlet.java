/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.controller.ApplicationContextListener;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import com.intbit.dao.ScheduleDAO;
import com.intbit.dao.ScheduleSocialPostDAO;
import com.intbit.util.AuthenticationUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author development
 */
public class ChangeScheduleServlet extends HttpServlet {

    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final Logger logger = Logger.getLogger(ScheduleDAO.class.getName());

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
            if (!AuthenticationUtil.isUserLoggedIn(request)) {
                AuthenticationUtil.printAuthErrorToResponse(response);
                return;
            }
            Integer userId = AuthenticationUtil.getUUID(request);
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String type = (String) requestBodyMap.get("type");
            if (type.equalsIgnoreCase("updatesocial")) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String entity_id = (String) requestBodyMap.get("entity_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());
                String metadataString = requestBodyMap.get("metadata").toString();

                String schedule_desc = (String) requestBodyMap.get("schedule_desc");

                ScheduleSocialPostDAO.updateScheduleSocialPostDetails(userId,
                        Integer.parseInt(schedule_id),
                        Integer.parseInt(entity_id),
                        schedule_title,
                        scheduleTimeStamp,
                        schedule_desc,
                        AppConstants.GSON.fromJson(metadataString, Map.class)
                );
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("updateemail")) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String entity_id = (String) requestBodyMap.get("entity_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());

                ScheduleDAO.updateScheduleEmailDetails(userId,
                        Integer.parseInt(schedule_id),
                        Integer.parseInt(entity_id),
                        requestBodyMap.get("email_subject").toString(),
                        requestBodyMap.get("from_email_address").toString(),
                        requestBodyMap.get("email_list").toString(),
                        requestBodyMap.get("reply_to_email_address").toString(),
                        requestBodyMap.get("to_email_addresses").toString().split(","),
                        schedule_title,
                        scheduleTimeStamp
                );
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();

            } else if (type.equalsIgnoreCase("deleteSelected")) {
                String schedule_ids = (String) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");

                ScheduleDAO.deleteSchedules(userId, schedule_ids);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("delete")) {
                Double schedule_ids = (Double) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");
                ScheduleDAO.deleteSchedule(userId, schedule_ids.intValue());
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("removetemplate")) {
                Double schedule_ids = (Double) requestBodyMap.get("schedule_ids");
                String entity_type = (String) requestBodyMap.get("entity_type");
                String is_recurring = (String) requestBodyMap.get("isRecurring");
                ScheduleDAO.removeSavedTemplate(userId, schedule_ids.intValue());
                
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase(ScheduledEntityType.Reminder.toString())) {
                String schedule_id = (String) requestBodyMap.get("schedule_id");
                String schedule_title = (String) requestBodyMap.get("schedule_title");
                String schedule_desc = (String) requestBodyMap.get("schedule_desc");
                String status = (String) requestBodyMap.get("status");
                Double schedule = (Double) requestBodyMap.get("schedule_time");

                Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());
                ScheduleDAO.updateNoteDetails(userId, Integer.parseInt(schedule_id),
                        schedule_title, schedule_desc, status, scheduleTimeStamp);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else if (type.equalsIgnoreCase("updateSchedule")) {
                String schedule_id = (String) requestBodyMap.get("scheduleid");
                String entity_id = (String) requestBodyMap.get("entityid");

                try (Connection conn = connectionManager.getConnection()) {
                    Integer id = ScheduleDAO.updateScheduleEntityList(Integer.parseInt(entity_id),
                            Integer.parseInt(schedule_id),
                            TemplateStatus.complete.toString(), conn);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("true");
                    response.getWriter().flush();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, util.Utility.logMessage(e,
                            "Exception while updating the schedule:", null), e);
                }
            }
        } catch (Exception ex) {
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
