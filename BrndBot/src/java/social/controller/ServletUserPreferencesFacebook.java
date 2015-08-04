/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.SqlMethods;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;

/**
 *
 * @author intbit
 */
public class ServletUserPreferencesFacebook extends HttpServlet {
    SqlMethods sql_methods = new SqlMethods();
    UserPreferencesFacebook user_preferences = new UserPreferencesFacebook();
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
        sql_methods.session = request.getSession();
        String access_token_from_table = "";
        String access_token = "", method_type = "";
        try {
            Integer user_id = (Integer)sql_methods.session.getAttribute("UID");

//            Integer user_id = 40;
            sql_methods.setDatabaseConnection();
            
            if (request.getParameter("access_token") != null){
                access_token = request.getParameter("access_token");
            }
            if (request.getParameter("access_token_method") != null){
                method_type = request.getParameter("access_token_method");
            }
            
            if (method_type.equals("setAccessToken") ){
                user_preferences.updatePreference(user_id, access_token);
            }else if (method_type.equals("getAccessToken")){
                access_token_from_table = user_preferences.getUserPreferenceForAccessToken(user_id);
            }
            
            sql_methods.con.close();
            
            if (method_type.equals("getAccessToken") ){
                    response.setContentType("application/plain");
                    out.write(access_token_from_table);
           }
            
        }catch (Exception e){
            System.out.println(e.getCause());
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