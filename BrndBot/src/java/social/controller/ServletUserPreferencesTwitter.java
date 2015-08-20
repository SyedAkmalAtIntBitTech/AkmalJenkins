/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import static com.controller.BrndBotBaseHttpServlet.logger;
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
public class ServletUserPreferencesTwitter extends BrndBotBaseHttpServlet {

    UserPreferencesTwitter user_preferences;

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
        String twitter_data = "";
        JSONObject json_twitter = new JSONObject();
        String access_token = "", access_token_secret = "", method_type = "", user_name = "";
        String twitter_access_token = "";
        String twitter_access_token_secret = "", twitter_user_name = "";

        try {
            user_preferences = new UserPreferencesTwitter();
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

            if (request.getParameter("twitter_access_tokens") != null) {
                access_token = request.getParameter("twitter_access_tokens");

                String access[] = access_token.split(",");

                access_token = access[0];
                access_token_secret = access[1];
                user_name = access[2];
            }
            if (request.getParameter("access_token_method") != null) {
                method_type = request.getParameter("access_token_method");
            }

            switch (method_type) {
                case "setAccessToken":
                    user_preferences.updatePreference(user_id, access_token, access_token_secret, user_name);
                    break;
                case "getAccessToken":
                    json_twitter = user_preferences.getUserPreferenceForAccessToken(user_id);
                    if (json_twitter.size() != 0){
                        twitter_access_token = (String) json_twitter.get("twitter_access_token");
                        twitter_access_token_secret = (String) json_twitter.get("twitter_access_token_secret");
                        twitter_user_name = (String)json_twitter.get("twitter_user_name");
                        twitter_data = twitter_access_token +","+ twitter_access_token_secret+","+twitter_user_name;
                    }
                    
                    if (request.getParameter("settings") != null) {
                        
                        
                    if (json_twitter.get("TwitterLoggedIn") == null){
                        json_twitter.put("TwitterLoggedIn", "false");
                        json_twitter.put("twitter_user_name", "twitter not configured");
                    }

                        String jsonn = new Gson().toJson(json_twitter);
                        response.setContentType("application/json");
                        out.write(jsonn);

                    }else {
                        response.setContentType("application/plain");
                        out.write(twitter_data);
                }   break;
                case "clearTwitterDetails":
                    user_preferences.deletePreferences(user_id);
                    break;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
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
