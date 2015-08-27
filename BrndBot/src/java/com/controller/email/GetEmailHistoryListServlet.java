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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohamed
 */
public class GetEmailHistoryListServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        List<Map<String, Object>> emailHistoryList = new ArrayList<>();
        if ( session.getAttribute("UID") != null){
            Integer userId = Integer.parseInt(session.getAttribute("UID").toString());
            String sql = "SELECT * FROM tbl_emailsenthistory WHERE user_id = ? ORDER BY timesent DESC";
            try(Connection conn = connectionManager.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, userId);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Map<String, Object> emailHistoryMap = new HashMap<>();
                        emailHistoryMap.put("history_id", rs.getInt("id"));
                        emailHistoryMap.put("user_id", rs.getInt("user_id"));
                        emailHistoryMap.put("sent_on", rs.getTimestamp("timesent").getTime());
                        emailHistoryMap.put("email_content", rs.getString("contenthtml"));
                        emailHistoryMap.put("from_address", rs.getString("emailaddress"));
                        emailHistoryMap.put("email_list_name", rs.getString("emaillistname"));
                        emailHistoryList.add(emailHistoryMap);
                    }
                }
                response.getWriter().write(new Gson().toJson(emailHistoryList));
                response.getWriter().flush();
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException ex) {
                Logger.getLogger(GetEmailHistoryListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "user is not logged in");
            response.getWriter().write(new Gson().toJson(responseMap));
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
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
