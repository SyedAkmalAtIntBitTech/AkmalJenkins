/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import com.controller.BrndBotBaseHttpServlet;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

/**
 *
 * @author intbit
 */
public class ServletGetStyles extends BrndBotBaseHttpServlet {

    String query = "", query1 = "";

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
        PreparedStatement prepared_statement = null;
        ResultSet result_set = null;
        String xml_files;
        JSONArray jsonarr = new JSONArray();
        getSqlMethodsInstance().session = request.getSession(true);

        try {
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

            String query_string = "Select layout from tbl_model where user_id=" + user_id + "";

            prepared_statement = getSqlMethodsInstance().getConnection().prepareStatement(query_string);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                jsonarr.add(result_set.getString("layout"));
            }
            String json = new Gson().toJson(jsonarr);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
            getSqlMethodsInstance().closeConnection();

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
