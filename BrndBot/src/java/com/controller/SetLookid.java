/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author intbit
 */
public class SetLookid extends BrndBotBaseHttpServlet {


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
        RequestDispatcher request_dispatcher;
        StringBuffer string_buffered;
        string_buffered = new StringBuffer();
        PrintWriter out = response.getWriter();
        getSqlMethodsInstance().session = request.getSession();

        try {
            String LookID = request.getParameter("LookID");
            String type = request.getParameter("type");
            
            if (type.equalsIgnoreCase("insert")){
                getSqlMethodsInstance().session.setAttribute("LookID", LookID);
                request_dispatcher = request.getRequestDispatcher("/personality.jsp");
                request_dispatcher.forward(request, response);
            }else if(type.equalsIgnoreCase("update")){
                Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
                getSqlMethodsInstance().updateLooks(user_id, Integer.parseInt(LookID));
                out.write("true");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));

            out.println(getSqlMethodsInstance().error);
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
