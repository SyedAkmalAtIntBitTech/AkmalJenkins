/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.look;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import model.brandpersonality;

/**
 *
 * @author intbit
 */
public class GetBrandPersonality extends HttpServlet {

    SqlMethods sqlmethods = new SqlMethods();
    String query_string = "", Query1 = "";
    PreparedStatement prepared_statement;
    ResultSet result_set;

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
        JSONObject jsonobject = new JSONObject();
        JSONArray jsonarray = new JSONArray();
        JSONArray jsonarray1 = new JSONArray();

        sqlmethods.session = request.getSession();
        try {
            sqlmethods.setDatabaseConnection();
            String look_id = (String) sqlmethods.session.getAttribute("LookID");

            query_string = "Select * from tbl_brand_personality where look_id='" + look_id + "'";
            prepared_statement = sqlmethods.con.prepareStatement(query_string);

            result_set = prepared_statement.executeQuery();
            while (result_set.next()) {
                brandpersonality brand_personality = new brandpersonality();

                int id = result_set.getInt("id");
                String brand_name = result_set.getString("brand_name");

                brand_personality.setId(id);
                brand_personality.setBrand_name(brand_name);
                jsonarray.add(brand_personality);
            }
            result_set.close();
            prepared_statement.close();
            jsonobject.put("first", jsonarray);
            String json = new Gson().toJson(jsonobject);
            response.setContentType("application/json");
            out.write(json);
            sqlmethods.con.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(sqlmethods.error);
        }finally {
            out.close();
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
