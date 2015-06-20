/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author intbit
 */
public class servletUserRegistration extends HttpServlet {
        sqlMethods SM = new sqlMethods();
//        HttpSession session;
        generateHashPassword HP = new generateHashPassword();
        RequestDispatcher rd;
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        generateHashPass hash = new generateHashPass("ABCDEFGHI");
        PrintWriter out = response.getWriter();
        PreparedStatement ps = null;
        StringBuffer sb = new StringBuffer();
        boolean check = true;
        SM.session = request.getSession(true);
        try{

            try 
            {
              BufferedReader reader = request.getReader();
             String line = null;
              while ((line = reader.readLine()) != null)
              {
                sb.append(line);
              }
            } catch (Exception e) { e.printStackTrace(); }

            JSONParser parser = new JSONParser();
            JSONObject joUser = null;
            try
            {
              joUser = (JSONObject) parser.parse(sb.toString());
            } catch (ParseException e) { e.printStackTrace(); }
            
            String User_id = (String)joUser.get("emailid");
            String password = (String)joUser.get("confirmPassword");
            
//            String HashPass = hash.encrypt(password);
            String HashPass = HP.hashPass(User_id, password);
//            String DPass = hash.decrypt(HashPass);
//            System.out.println(DPass);
            SM.setConnection();
            check = SM.checkForDuplicateUser(User_id);
            response.setContentType("text/html");
            
            if (check){
                out.write("false");
            }else{
                SM.addUsers(User_id, HashPass);
                SM.session.setAttribute("EmailID", User_id);
                SM.con.close();
            }
            
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
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
        try{
            processRequest(request, response);
        }catch (Exception e){}
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
        try{
            processRequest(request, response);
        }catch (Exception e){}
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
