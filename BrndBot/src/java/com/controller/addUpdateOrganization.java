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
import javax.servlet.RequestDispatcher;
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
public class addUpdateOrganization extends HttpServlet {
        sqlMethods SM = new sqlMethods();
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PreparedStatement ps = null;
        StringBuffer sb = new StringBuffer();
         SM.session = request.getSession(true);
        
        try{

            try 
            {
        //      BufferedReader reader = request.getReader();
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
                
            String Company = (String)joUser.get("company") ;
            String org_id = (String)joUser.get("org");

            System.out.println(Company);
            
            SM.setConnection();
            String emailid = (String)SM.session.getAttribute("EmailID");
            Integer idno = SM.getUserID(emailid);
            
            String org_name = SM.getOrganizationName(Integer.parseInt(org_id));
            SM.session.setAttribute("org_name", org_name);
            SM.session.setAttribute("company", Company);
            
            SM.updateUsersOrg(idno, Integer.parseInt(org_id), Company);
            SM.con.close();

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
