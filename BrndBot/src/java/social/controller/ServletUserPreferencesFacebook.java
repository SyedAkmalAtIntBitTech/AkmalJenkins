/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

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
        JSONObject fb_details = new JSONObject();
        String default_access_token = "", method_type = "", fb_user_profile_name = "", settings = "";
        String default_page_name = "";

        try {
            user_preferences = new UserPreferencesFacebook();
            Integer user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
            if (request.getParameter("access_token") != null) {
                default_access_token = request.getParameter("access_token");
            }
            if (request.getParameter("access_token_method") != null) {
                method_type = request.getParameter("access_token_method");
            }
            
            if (request.getParameter("fb_user_profile_name") != null) {
                fb_user_profile_name = request.getParameter("fb_user_profile_name");
            }

            if (request.getParameter("default_page_name") != null) {
                default_page_name = request.getParameter("default_page_name");
            }
            
//            switch (method_type) {
//                case "setAccessToken":
//                    user_preferences.updatePreference(user_id, default_access_token, fb_user_profile_name);
//                    break;
//                case "getAccessToken":
//                    break;
//            }
            
            if (method_type.equals("getAccessToken")) {
                fb_details = user_preferences.getUserPreferenceForAccessToken(user_id);
                
                if (fb_details.size() != 0 ){
                    
                    default_access_token = (String)fb_details.get("fb_default_page_access_token");
                    fb_user_profile_name = (String)fb_details.get("user_profile_page");
                    default_page_name = (String)fb_details.get("fb_default_page_name");
                }

                if (request.getParameter("settings") != null) {
                    settings = request.getParameter("settings");
                    
                    if (fb_details.get("FacebookLoggedIn") == null){
                        fb_details.put("FacebookLoggedIn", "false");
                        fb_details.put("user_profile_page", "fb not configured");
                    }
                        String jsonn = new Gson().toJson(fb_details);
                        response.setContentType("application/json");
                        out.write(jsonn);
                    
                }else {
                    response.setContentType("application/plain");
                    out.write(default_access_token+","+fb_user_profile_name+","+default_page_name);
                }
                
            }else if(method_type.equals("setAccessToken")){
                    user_preferences.updatePreference(user_id, default_access_token, fb_user_profile_name, default_page_name);
            }else if(method_type.equals("clearFacebookDetails")){
                    user_preferences.deletePreferences(user_id);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
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
