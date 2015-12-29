/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author intbit
 */
public class SetUserPreferences extends BrndBotBaseHttpServlet {

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
        StringBuffer string_buffer = new StringBuffer();
        StringBuilder string_builder = new StringBuilder();
        
        getSqlMethodsInstance().session = request.getSession(true);
        
        try {
             BufferedReader reader = request.getReader();
             String line = null;
              while ((line = reader.readLine()) != null)
              {
                string_builder.append(line);
              }

            JSONParser parser = new JSONParser();
            JSONObject json_user_preferences = null;
            
            String str = string_builder.toString();
            String str_new = str.replace("&quot;", "\"");
            json_user_preferences = (JSONObject) parser.parse(str_new);
            
            Integer user_id = (Integer)getSqlMethodsInstance().session.getAttribute("UID");
            Integer brand_id = (Integer)getSqlMethodsInstance().session.getAttribute("brandID");
            Integer look_id = (Integer)getSqlMethodsInstance().session.getAttribute("LookID");
            String studio_id = (String)getSqlMethodsInstance().session.getAttribute("studioID");
            String org_id = (String)getSqlMethodsInstance().session.getAttribute("org_id");
                    
            String type = (String)json_user_preferences.get("type");

            if (type.equalsIgnoreCase("update")){
                JSONObject json_user_preferences_from_database = getSqlMethodsInstance().getJSONUserPreferences(user_id);
                
                json_user_preferences_from_database.put(IConstants.kColor1, json_user_preferences.get(IConstants.kColor1));
                json_user_preferences_from_database.put(IConstants.kColor2, json_user_preferences.get(IConstants.kColor2));
                json_user_preferences_from_database.put(IConstants.kColor3, json_user_preferences.get(IConstants.kColor3));
                json_user_preferences_from_database.put(IConstants.kColor4, json_user_preferences.get(IConstants.kColor4));
                json_user_preferences_from_database.put(IConstants.kColor5, json_user_preferences.get(IConstants.kColor5));
                json_user_preferences_from_database.put(IConstants.kColor6, json_user_preferences.get(IConstants.kColor6));

                getSqlMethodsInstance().updateJSONUserPreference(user_id, json_user_preferences_from_database);

            }else {

                getSqlMethodsInstance().session.setAttribute("Checked", "true");

                Integer font_theme_id = getSqlMethodsInstance().getFontthemeid(brand_id.toString());
                getSqlMethodsInstance().addUserPreferences(user_id, Integer.parseInt(brand_id.toString()), 
                                                           font_theme_id,  studio_id, 
                                                           Integer.parseInt(look_id.toString()), 
                                                           Integer.parseInt(org_id), json_user_preferences);
                
            }
        }catch(Exception e){
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while setting the user preferences name:", getSqlMethodsInstance().error));

            out.write(getSqlMethodsInstance().error);
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
