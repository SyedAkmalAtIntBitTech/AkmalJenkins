/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import model.ClassImages;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Syed
 */
public class GetUserImages extends HttpServlet {
    SqlMethods sqlmethods = new SqlMethods();
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
        JSONObject json_ob = new JSONObject();
        JSONArray json_arr = new JSONArray();
        sqlmethods.session = request.getSession();
        PreparedStatement prepared_statement;
        ResultSet result_set;
        String image_name;
        Integer user_id, id;
        try {
            user_id = (Integer)sqlmethods.session.getAttribute("UID");
            
            String query = "Select * from tbl_user_images where user_id="+user_id;
            sqlmethods.setDatabaseConnection();
            prepared_statement = sqlmethods.con.prepareStatement(query);
            result_set = prepared_statement.executeQuery();
            
            while(result_set.next()){
                ClassImages cli = new ClassImages();
                id = result_set.getInt("id");
                user_id = result_set.getInt("user_id");
                image_name = result_set.getString("image_name");
                cli.setId(id);
                cli.setUser_id(user_id);
                cli.setImage_name(image_name);
                json_arr.add(cli);
            }
//        String jsonText = obj.toJSONString();        
        String json = new Gson().toJson(json_arr);
        response.setContentType("application/json");
        response.getWriter().write(json);
        result_set.close();
        prepared_statement.close();
        sqlmethods.con.close();
            
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
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