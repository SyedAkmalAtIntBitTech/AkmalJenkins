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
import model.TblSubCategories;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author intbit
 */
public class GetSubCategories extends HttpServlet {
SqlMethods sqlmethods = new SqlMethods();
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
        StringBuffer string_buffer = new StringBuffer();
        sqlmethods.session = request.getSession(true);
        JSONArray jsonarr = new JSONArray();
        
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject joUser = null;
            joUser = (JSONObject) parser.parse(string_buffer.toString());

            String category_id = (String) joUser.get("CategoryID");
            sqlmethods.setDatabaseConnection();
            String query = "select * from tbl_sub_category where category_id='" + category_id + "'";
            prepared_statement = sqlmethods.con.prepareStatement(query);
            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                TblSubCategories TS = new TblSubCategories();
                TS.setCategory_id(result_set.getString("category_id"));
                TS.setSub_category_name(result_set.getString("sub_category_name"));
                jsonarr.add(TS);
            }
            result_set.close();
            prepared_statement.close();
            sqlmethods.con.close();

            String jsonn = new Gson().toJson(jsonarr);
            response.setContentType("application/json");
            out.write(jsonn);
        } catch (ParseException e) {
            out.write(sqlmethods.error);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            out.write(sqlmethods.error);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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
