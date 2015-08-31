/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.email;

import com.controller.BrndBotBaseHttpServlet;
import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.IConstants;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author development
 */
public class EmailSettingsServlet extends BrndBotBaseHttpServlet {

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
        JSONObject responseObject = new JSONObject();
        String dataresponse = "false";
        StringBuffer string_buffer = new StringBuffer();
        getSqlMethodsInstance().session = request.getSession(true);

        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject json_email_settings_update = null;
            json_email_settings_update = (JSONObject) parser.parse(string_buffer.toString());
            String queryParameter = (String) json_email_settings_update.get("type");
            
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

            JSONObject json_user_preferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
            if (queryParameter.equalsIgnoreCase("add")){
                String from_address = (String) json_email_settings_update.get("from_address");
                String reply_email_address = (String) json_email_settings_update.get("reply_email_address");
                JSONObject json_object = new JSONObject();
                json_object.put(IConstants.kEmailFromAddress, from_address);
                json_object.put(IConstants.kEmailReplyAddress, reply_email_address);
                json_user_preferences.put(IConstants.kEmailSettings, json_object);
                getSqlMethodsInstance().updateJSONUserPreference(user_id, json_user_preferences);
                dataresponse = "true";
                response.getWriter().write(dataresponse);
            }else if (queryParameter.equalsIgnoreCase("get")){
                JSONObject json_object = (JSONObject)json_user_preferences.get(IConstants.kEmailSettings);
                response.getWriter().write(new Gson().toJson(json_object));
            }
            
        }catch(Exception e){
            e.printStackTrace();
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "exception while the setting the email settings:", getSqlMethodsInstance().error));
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
