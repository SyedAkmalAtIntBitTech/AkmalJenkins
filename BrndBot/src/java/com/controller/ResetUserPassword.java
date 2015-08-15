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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class ResetUserPassword extends BrndBotBaseHttpServlet {

    GenerateHashPassword generate_hash_password = new GenerateHashPassword();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        String userid = "false";
        StringBuffer string_buffer = new StringBuffer();
        String hashURL = "";
        String password = "";
        String confirmpassword = "";

        PrintWriter out = response.getWriter();
        try {

            getSqlMethodsInstance().session = request.getSession(true);

            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject joUser = null;
            joUser = (JSONObject) parser.parse(string_buffer.toString());
            
            String type = (String)joUser.get("type");

            if (type.equalsIgnoreCase("update")){
                password = (String) joUser.get("password");
                confirmpassword = (String) joUser.get("confirmpassword");
                Integer user = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
                userid = String.valueOf(user);
            }else{
                hashURL = (String) joUser.get("hashURL");
                password = (String) joUser.get("password");
                confirmpassword = (String) joUser.get("confirmpassword");
                userid = getSqlMethodsInstance().checkResetStatus(hashURL);
            }

            String hashPass = generate_hash_password.hashPass(password);
            
            if (userid.equals("false")) {
                out.write("false");
            } else {
                getSqlMethodsInstance().resetPassword(userid, hashPass);
                out.write("true");
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            out.write(getSqlMethodsInstance().error);
        }finally {
            out.close();
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
