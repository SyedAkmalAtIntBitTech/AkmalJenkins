/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author intbit
 */
public class ServletUserPreferencesFacebook extends BrndBotBaseHttpServlet {

    UserPreferencesFacebook user_preferences;

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
        PrintWriter out = response.getWriter();
        getSqlMethodsInstance().session = request.getSession();
        String access_token_from_table = "";
        String access_token = "", method_type = "";
        try {
            user_preferences = new UserPreferencesFacebook();
            Integer user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
            if (request.getParameter("access_token") != null) {
                access_token = request.getParameter("access_token");
            }
            if (request.getParameter("access_token_method") != null) {
                method_type = request.getParameter("access_token_method");
            }

            if (method_type.equals("setAccessToken")) {
                user_preferences.updatePreference(user_id, access_token);
            } else if (method_type.equals("getAccessToken")) {
                access_token_from_table = user_preferences.getUserPreferenceForAccessToken(user_id);
            }

            if (method_type.equals("getAccessToken")) {
                response.setContentType("application/plain");
                out.write(access_token_from_table);
            }

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }finally {
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
