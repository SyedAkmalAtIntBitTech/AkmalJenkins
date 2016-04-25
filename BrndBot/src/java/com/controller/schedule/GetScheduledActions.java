/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.schedule;

import com.intbit.AppConstants;
import com.intbit.ScheduledEntityType;
import com.intbittech.dao.impl.ScheduleDAO;
import com.intbittech.dao.impl.ScheduleSocialPostDAO;
import com.intbit.util.AuthenticationUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

/**
 *
 * @author development
 */
public class GetScheduledActions extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        JSONArray json_array = new JSONArray();
        try {
            if ( !AuthenticationUtil.isUserLoggedIn(request)){
                AuthenticationUtil.printAuthErrorToResponse(response);
                return;
            }
            Integer userId = AuthenticationUtil.getUUID(request);            
            String type = request.getParameter("type");
            String program_id = request.getParameter("programid");
            
            if (type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString())){
                if ((program_id != null) && !(program_id.equals("undefined"))){
                    json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(userId, Integer.parseInt(program_id));
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(AppConstants.GSON.toJson(json_array));
                    response.getWriter().flush();
                }
            }else if(type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString())){
                if ((program_id != null) && !(program_id.equals("undefined"))){
                    json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(userId, Integer.parseInt(program_id));
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(AppConstants.GSON.toJson(json_array));
                    response.getWriter().flush();
                }
            }else if(type.equalsIgnoreCase(ScheduledEntityType.Email.toString())){
                if ((program_id != null) && !(program_id.equals("undefined"))){
                    json_array = ScheduleDAO.getScheduledActions(userId, Integer.parseInt(program_id));
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(AppConstants.GSON.toJson(json_array));
                    response.getWriter().flush();
                }
            }else if(type.equalsIgnoreCase("social")){
                JSONArray json_social = new JSONArray();
                json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(userId, Integer.parseInt(program_id));
                for (int i = 0; i< json_array.size(); i++){
                    json_social.add(json_array.get(i));
                }
                json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(userId, Integer.parseInt(program_id));
                for (int i = 0; i< json_array.size(); i++){
                    json_social.add(json_array.get(i));
                }
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(AppConstants.GSON.toJson(json_social));
                response.getWriter().flush();
            }
            
        }catch (Exception sq){
            System.console().writer().write(sq.getLocalizedMessage());
            System.console().writer().write(sq.getMessage());
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
