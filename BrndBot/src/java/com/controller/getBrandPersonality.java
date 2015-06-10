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
public class getBrandPersonality extends HttpServlet {
sqlMethods SM = new sqlMethods();
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
        String Query="", Query1="";
        PreparedStatement ps;
        SM.session = request.getSession();
        
        ResultSet rs;
        JSONObject JO = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONArray arr1 = new JSONArray();
            
        try 
        {
        SM.setConnection();
        String lookID = (String)SM.session.getAttribute("LookID");

        Query = "Select * from tbl_brand_personality where look_id='"+lookID+"'";
        ps = SM.con.prepareStatement(Query);

        rs = ps.executeQuery();
        while (rs.next()){
            brandpersonality bP = new brandpersonality();

            int id = rs.getInt("id");
            String brand_name = rs.getString("brand_name");

            bP.setId(id);
            bP.setBrand_name(brand_name);
            arr.add(bP);
        }
        rs.close();
        ps.close();
        JO.put("first", arr); 
    }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
    }

            String json = new Gson().toJson(JO);
            response.setContentType("application/json");
            response.getWriter().write(json);
    
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
