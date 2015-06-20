/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static com.controller.sqlMethods.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author intbit
 */
public class authentication extends HttpServlet {
        sqlMethods SM = new sqlMethods();
        generateHashPassword HP = new generateHashPassword();
        RequestDispatcher rd;

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
            throws ServletException, IOException, SQLException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        generateHashPass hash = new generateHashPass("ABCDEFGHI");

        PrintWriter out = response.getWriter();
        boolean check = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer();
        SM.session = request.getSession(true);
        
        try {
            
            try 
            {
              BufferedReader reader = request.getReader();
//              BufferedReader reader = request.getReader();
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
            String password = (String)joUser.get("password");
            
//            String HashPass = hash.encrypt(password);
            String hassPass = HP.hashPass(User_id, password);
            
            SM.setConnection();

            check = SM.checkAvailability(User_id, hassPass);
            Integer UID = SM.getUserID(User_id);
            Integer org_id = SM.getOrganizationID(UID);
            
            String org_name = SM.getOrganizationName(org_id);
            SM.session.setAttribute("org_name", org_name);
            
            response.setContentType("text/html");
            if (check){
                SM.session.setAttribute("UID", UID);
                SM.session.setAttribute("Checked", "true");
                out.write("true");
            }else{
                SM.session.setAttribute("Checked", "false");
                out.write("false");
            }  
            SM.con.close();
            out.close();            
        }catch(SQLException e){
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
            throws ServletException, IOException  {
        try{
            processRequest(request, response);
        }catch (Exception x){}
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
        }catch (Exception x){}
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