/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.email;

import com.google.gson.Gson;
import com.intbittech.email.mandrill.MandrillApiHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mohamed
 */
@WebServlet(name = "GetEmailDetailServlet", urlPatterns = {"/GetEmailDetail"})
public class GetEmailDetailServlet extends HttpServlet {

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
        try {
            String mandrillEmailId = request.getParameter("mandrill_email_id");
            if(StringUtils.isEmpty(mandrillEmailId)){
                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("error", "mandrill_email_id is required request parameter");
                response.getWriter().write(new Gson().toJson(responseMap));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            response.getWriter().write(
                    new Gson().toJson(MandrillApiHandler.getEmailDetails(mandrillEmailId)));
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        } catch (URISyntaxException ex) {
            Logger.getLogger(GetEmailDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
