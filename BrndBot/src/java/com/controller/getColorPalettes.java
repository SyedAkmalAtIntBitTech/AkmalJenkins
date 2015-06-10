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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.TblColors;

/**
 *
 * @author intbit
 */
public class getColorPalettes extends HttpServlet {
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
        String Query = "", Query1 = "";
        String theme = "";
        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();
//        JSONArray jarr = new JSONArray();
        Integer i = 1;
        PreparedStatement ps, ps1;
        ResultSet rs, rs1;
        
        try {
            /* TODO output your page here. You may use following sample code. */
                SM.setConnection();
                Query = "Select color1,color2,color3,color4,color5 from tbl_brand_color_theme";
                ps = SM.con.prepareStatement(Query);
                rs = ps.executeQuery();
                while (rs.next()){
                        TblColors TC = new TblColors();
                        JSONArray jarr = new JSONArray();

                        TC = SM.getColors(rs.getInt(1));
                        jarr.add(TC);

                        TC = SM.getColors(rs.getInt(2));
                        jarr.add(TC);

                        TC = SM.getColors(rs.getInt(3));
                        jarr.add(TC);
                        
                        TC = SM.getColors(rs.getInt(4));
                        jarr.add(TC);

                        TC = SM.getColors(rs.getInt(5));
                        jarr.add(TC);
                        theme = "theme" + String.valueOf(i);
                        json1.put(theme, jarr);
                        i++;
                }

                String jsonn = new Gson().toJson(json1);
                response.setContentType("application/json");
                response.getWriter().write(jsonn);
                
        }catch(Exception e){
            
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
