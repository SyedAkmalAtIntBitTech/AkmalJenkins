/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.email;

import com.google.gson.Gson;
import com.intbit.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohamed
 */
// To-Do Ilyas/AR  remove or refactor ??
public class GetEmailHistoryDetailServlet extends HttpServlet {

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
        String emailHistoryId = request.getParameter("history_id");
        if ( emailHistoryId == null || "".equals(emailHistoryId)){
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "history_id is required");
            response.getWriter().write(new Gson().toJson(responseMap));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        try{
            int emailHistoryIdInt = Integer.parseInt(emailHistoryId);
            String sql = "SELECT * FROM tbl_email_mandrill_history WHERE email_history_id = ?";
            ConnectionManager connectionManager = ConnectionManager.getInstance();
            try(Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, emailHistoryIdInt);
                List<Map<String, Object>> emailIdsInEmailHistory = new ArrayList<>();
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Map<String, Object> emailIdStatus = new HashMap<>();
                        emailIdStatus.put("email_history_id", rs.getInt("email_history_id"));
                        emailIdStatus.put("to_email", rs.getString("to_email"));
                        emailIdStatus.put("mandrill_email_id", rs.getString("mandrill_email_id"));
                        emailIdStatus.put("status", rs.getString("status"));
                        emailIdStatus.put("reject_reason", rs.getString("reject_reason"));
                        emailIdsInEmailHistory.add(emailIdStatus);
                    }
                }
                response.getWriter().write(new Gson().toJson(emailIdsInEmailHistory));
                response.getWriter().flush();
                response.setStatus(HttpServletResponse.SC_OK);
                return;
                
            } catch (SQLException ex) {
                Logger.getLogger(GetEmailHistoryDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(NumberFormatException nfe){
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "Exception while parsing email_history_id to integer");
            response.getWriter().write(new Gson().toJson(responseMap));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
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
