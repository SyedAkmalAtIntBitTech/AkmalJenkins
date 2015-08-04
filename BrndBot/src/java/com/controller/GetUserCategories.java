/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import pojos.TblCategory;

/**
 *
 * @author intbit
 */
public class GetUserCategories extends BrndBotBaseHttpServlet {

    
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
        JSONArray jsonarr = new JSONArray();
        PreparedStatement prepared_statement = null;
    ResultSet result_set = null;

        getSqlMethodsInstance().session = request.getSession(true);
        try {
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

            Integer organization_id = getSqlMethodsInstance().getOrganizationID(user_id);
            String query = "Select * from tbl_category where organization_id='" + organization_id + "' Order By id ASC";
            prepared_statement = getSqlMethodsInstance().getConnection().prepareStatement(query);

            result_set = prepared_statement.executeQuery();

            while (result_set.next()) {
                TblCategory categories = new TblCategory();
                categories.setId(result_set.getInt("id"));
                categories.setOrganizationId(result_set.getInt("organization_Id"));
                categories.setCategoryName(result_set.getString("category_name"));
                categories.setImage_name(result_set.getString("image_name"));
                jsonarr.add(categories);
            }

            String json = new Gson().toJson(jsonarr);
            response.setContentType("application/json");
            response.getWriter().write(json);
            
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(getSqlMethodsInstance().error);
        }finally {
            out.close();
            getSqlMethodsInstance().close(result_set, prepared_statement);
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
