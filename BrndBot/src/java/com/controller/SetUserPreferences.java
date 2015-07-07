/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author intbit
 */
public class SetUserPreferences extends HttpServlet {
        SqlMethods sqlmethods = new SqlMethods();
        StringBuffer string_buffer = new StringBuffer();

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
        sqlmethods.session = request.getSession(true);
        
        try {
             BufferedReader reader = request.getReader();
             String line = null;
              while ((line = reader.readLine()) != null)
              {
                string_buffer.append(line);
              }

            JSONParser parser = new JSONParser();
            JSONObject joUser = null;
            joUser = (JSONObject) parser.parse(string_buffer.toString());
            
            String color1 = (String)joUser.get("finalcolor1");
            String color2 = (String)joUser.get("finalcolor2");
            String color3 = (String)joUser.get("finalcolor3");
            String color4 = (String)joUser.get("finalcolor4");
            String color5 = (String)joUser.get("finalcolor5");
            String color6 = (String)joUser.get("finalcolor6");
            
            Integer user_id = (Integer)sqlmethods.session.getAttribute("UID");
            String brand_id = (String)sqlmethods.session.getAttribute("brandID");
            String look_id = (String)sqlmethods.session.getAttribute("LookID");
            String studio_id = (String)sqlmethods.session.getAttribute("studioID");
            sqlmethods.session.setAttribute("Checked", "true");

            sqlmethods.setDatabaseConnection();
            Integer font_theme_id = sqlmethods.getFontthemeid(brand_id);
            sqlmethods.addUserPreferences(user_id, Integer.parseInt(brand_id), font_theme_id,  studio_id, Integer.parseInt(look_id), color1, color2, color3, color4, color5, color6);
            sqlmethods.con.close();
        }catch(Exception e){
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
