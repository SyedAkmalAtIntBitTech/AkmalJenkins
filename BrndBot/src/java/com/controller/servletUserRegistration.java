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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PreparedStatement ps = null;
        StringBuffer sb = new StringBuffer();
        SM.session = request.getSession(true);
        try{

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
            
//            rd = request.getRequestDispatcher("/organization.jsp");

            String User_id = (String)joUser.get("emailid");
            String password = (String)joUser.get("confirmPassword");

            String HashPass = HP.hashPass(User_id, password);
            System.out.println(HashPass); 
            SM.setConnection();

            SM.addUsers(User_id, HashPass);
            SM.session.setAttribute("EmailID", User_id);

            SM.con.close();
                
/*            response.setContentType("text/html");
            response.setHeader("Cache-control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
            
        //List of allowed origins
        List<String> incomingURLs = Arrays.asList(getServletContext().getInitParameter("incomingURLs").trim().split(","));
        String Origin = getServletContext().getInitParameter("incomingURLs");
        // Get client's origin
        
        // Get client's IP address
        String ipAddress = request.getHeader("x-forwarded-for");
//        if (ipAddress == null) {
//            ipAddress = request.getRemoteAddr();
//        }  
        String clientOrigin = request.getHeader("Origin");
        
        int myIndex = incomingURLs.indexOf(clientOrigin);
        //if the client origin is found in our list then give access
        //if you don't want to check for origin and want to allow access
        //to all incoming request then change the line to this
        //response.setHeader("Access-Control-Allow-Origin", "*");
//        if(myIndex != -1){
            response.setHeader("Access-Control-Allow-Origin", Origin);
            response.setHeader("Access-Control-Allow-Methods", "POST");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Max-Age", "86400"); 
            
//        }
/*            // New location to be redirected
            String site = new String("http://localhost:8084/BrndBotUsingJson/UploadLogo.jsp");

            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);                */
//            response.sendRedirect(request.getContextPath()+"/UploadLogo.jsp");
//            rd.forward(request, response);
                
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
        
/*        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletUserRegistration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletUserRegistration at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

    }
*/
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
