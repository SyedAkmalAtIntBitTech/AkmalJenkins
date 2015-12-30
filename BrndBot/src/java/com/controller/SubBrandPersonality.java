/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class SubBrandPersonality extends BrndBotBaseHttpServlet {
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
        RequestDispatcher request_dispatcher;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        getSqlMethodsInstance().session = request.getSession();
        StringBuffer string_buffered;
        string_buffered = new StringBuffer();
        try {
            List brand = new ArrayList();
            
            BufferedReader reader = request.getReader();
             String line = null;
              while ((line = reader.readLine()) != null)
              {
                string_buffered.append(line);
              }

            JSONParser parser = new JSONParser();
            JSONObject json_look = null;
            json_look = (JSONObject) parser.parse(string_buffered.toString());
            
            String type = (String)json_look.get("type");
            String lookID = (String)json_look.get("lookID");
            
            if (type.equalsIgnoreCase("insert")){
                brand = getSqlMethodsInstance().getBrandIDFromBrands(Integer.parseInt(lookID));
                
                getSqlMethodsInstance().session.setAttribute("brandID", brand.get(0));
                getSqlMethodsInstance().session.setAttribute("brandName", brand.get(1));
                
                out.write("true");
//                request_dispatcher = request.getRequestDispatcher("/uploadlogo.jsp");
//                request_dispatcher.forward(request, response);
            }else if(type.equalsIgnoreCase("update")){
                Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
                Integer brand_id = (Integer) brand.get(0);
                getSqlMethodsInstance().updateBrandPersonality(user_id, brand_id);
                out.write("true");
            }
            

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while reading the Subbrand personality", getSqlMethodsInstance().error));

            out.println(getSqlMethodsInstance().error);
        }
        finally {
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
