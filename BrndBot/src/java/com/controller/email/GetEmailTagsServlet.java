/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.email;

import com.google.gson.Gson;
import com.intbit.dao.EmailHistoryDAO;
import email.mandrill.MandrillApiHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Mohamed
 */
@WebServlet(name = "GetEmailTagsServlet", urlPatterns = {"/GetEmailTags"})
public class GetEmailTagsServlet extends HttpServlet {

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
        List<Map<String, Object>> tagsFromMandrill = MandrillApiHandler.getTags();
        List<Map<String, Object>> tagsFromMandrillForUser = new ArrayList<>();
        
        HttpSession session = request.getSession();
        if ( session.getAttribute("UID") == null || 
                StringUtils.isEmpty(session.getAttribute("UID").toString())){
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "user is not logged in");
            response.getWriter().write(new Gson().toJson(responseMap));
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        int userId = Integer.parseInt(session.getAttribute("UID").toString());
        Set<String> tagsForUser = EmailHistoryDAO.getTagsForUser(userId);
        for(Map<String,Object> mTag : tagsFromMandrill){
            if ( mTag.get("tag") != null){
                if(tagsForUser.contains(mTag.get("tag").toString())){
                    tagsFromMandrillForUser.add(mTag);
                }
            }
        }
        response.getWriter().write(
                new Gson().toJson(tagsFromMandrillForUser));
        
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_OK);
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
