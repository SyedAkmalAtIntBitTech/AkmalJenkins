/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit;

import com.controller.BrndBotBaseHttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ExtendedPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.FacebookType;
/**
 *
 * @author sandeep-kumar
 */
@WebServlet(name = "Fblogin", urlPatterns = {"/Fblogin"})
public class Fblogin extends BrndBotBaseHttpServlet {

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
        try  
        (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         System.out.println("hi");
         String MY_ACCESS_TOKEN = "CAACEdEose0cBAHgOnGKwWZBRRHUj9ZAZCUlZANlePFlDZCxjAsKtbD7NxHdKD44PAeiLYNmzerSWU2MZCTC8KC6hZAg71te5rA9arhggulnbmjZCkghuGVvSrjUmbx6hvMaUt0DQs4CZALQFpZA39drGXMz2sS3g1ytdSMFIdkBZAMZCZC6fOmBZB9x89nm8ro8PB7Cg8PwiCskQtMBkgUXcX6ZCJrs";
	 String MY_APP_ID = "1572856859649560";
	 String REDIRECT_URL = "https://www.facebook.com/connect/login_success.html";
	 String APP_SECRET = "997c5f81d6c69922e49f61121a3af3e9";
          System.out.println("hi");
	 String PAGE_NAME = "541233806015717"; 
         String Page_Id = "541233806015717"; 
         
   
         ScopeBuilder scopeBuilder = new ScopeBuilder();
         scopeBuilder.addPermission(ExtendedPermissions.PUBLISH_PAGES);
	 scopeBuilder.addPermission(ExtendedPermissions.PUBLISH_ACTIONS);			
	 scopeBuilder.addPermission(ExtendedPermissions.MANAGE_PAGES);
		
	FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.VERSION_2_3); 
            
        FacebookType publishMessageResponse
                    = facebookClient.publish("me/feed", FacebookType.class,
                            Parameter.with("message", " After each 10 min j2111ava121 code"));

            System.out.println("Published message ID: " + publishMessageResponse.getId());     
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Fblogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Fblogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
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
