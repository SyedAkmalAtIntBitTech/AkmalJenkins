/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author AR
 */
public class SetEmailLists extends BrndBotBaseHttpServlet {

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
        JSONObject responseObject = new JSONObject();
        String dataresponse = "false";
        StringBuffer string_buffer = new StringBuffer();
        try {
            
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                string_buffer.append(line);
            }
            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject json_update = null;
            json_update = (org.json.simple.JSONObject) parser.parse(string_buffer.toString());
            String queryParameter = (String) json_update.get("update");

            getSqlMethodsInstance().session = request.getSession();
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

            if (queryParameter.equalsIgnoreCase("addUpdateEmailList")) {
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                String emailAddresses = (String)json_update.get(IConstants.kEmailAddressesKey);
                org.json.simple.JSONObject json_user_preferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
                
                Boolean result = updateEmailListPreference(user_id, emailListName, emailAddresses);
                responseObject.put(queryParameter, result);
                if (result){
                    dataresponse = "true";
                }
            } else if (queryParameter.equalsIgnoreCase("deleteEmailInEmailList")) {
                String emailListName = request.getParameter(IConstants.kEmailListNameKey);
                String emailAddress = request.getParameter(IConstants.kEmailAddressKey);
//                Boolean result = deleteEmailFromEmailList(user_id, emailListName, emailAddress);
//                responseObject.put(queryParameter, result);
            } else if (queryParameter.equalsIgnoreCase("deleteEmailList")) {
                String emailListName = request.getParameter(IConstants.kEmailListNameKey);
//                Boolean result = deleteEmailList(user_id, emailListName);
//                responseObject.put(queryParameter, result);
            }
        } catch (SQLException | JSONException | ParseException e) {
            try {
                responseObject.put("Error", "Request unsuccessfull");
            } catch (Exception ex){
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", getSqlMethodsInstance().error));
            }    
        } finally {
            getSqlMethodsInstance().closeConnection();
        }

//        String json = new Gson().toJson(responseObject);
//        response.setContentType("application/json");
        response.getWriter().write(dataresponse);
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

    private Boolean updateEmailListPreference(Integer user_id, String emailListName, String emailAddresses) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);

        if (emailListArrayJSON.length() == 0){
                org.json.simple.JSONArray json_user_email_addresses_array = new org.json.simple.JSONArray();
                JSONObject json_user_preferences_email = new JSONObject();
                JSONObject json_user_preferences_listnames = new JSONObject();
                String array_email_list[] = emailAddresses.split(",");

                for (int i = 0; i< array_email_list.length; i++){
                    json_user_email_addresses_array.add(array_email_list[i]);
                }

                json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
                json_user_preferences_email.put(IConstants.kEmailAddressesKey, emailAddresses);
                emailListArrayJSON.put(json_user_preferences_email);
                return AddEmailListUserPreference(user_id, emailListArrayJSON);
        }else{
                org.json.simple.JSONArray json_user_email_addresses_array = new org.json.simple.JSONArray();
                JSONObject json_user_preferences_email = new JSONObject();
                org.json.JSONObject emailListJSONObject = new org.json.JSONObject();
                String array_email_list[] = emailAddresses.split(",");

                for (int i = 0; i< array_email_list.length; i++){
                    json_user_email_addresses_array.add(array_email_list[i]);
                }

                Boolean foundEmailListToUpdate = false;
                for (int i = 0; i < emailListArrayJSON.length(); i++) {
                    emailListJSONObject = emailListArrayJSON.getJSONObject(i);
                    String currentListName = emailListJSONObject.getString(IConstants.kEmailListNameKey);
                    if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                        if (emailListName.equals(currentListName)) {
                            emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                            emailListArrayJSON.put(i, emailListJSONObject);
                            foundEmailListToUpdate = true;
                            break;
                        }
                    }
                }
                
                if(!foundEmailListToUpdate)
                {
                    emailListJSONObject = new org.json.JSONObject();
                    emailListJSONObject.put(IConstants.kEmailListNameKey, emailListName);
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                    emailListArrayJSON.put(emailListJSONObject);
                }
                
                return updateEmailListUserPreference(user_id, emailListArrayJSON);
        
        }
    }

    private Boolean deleteEmailFromEmailList(Integer user_id, String emailListName, String emailid) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = (JSONArray) getSqlMethodsInstance().getEmailListsPreferences(user_id);
        
        for (int i = 0; i < emailListArrayJSON.length(); i++) {
            org.json.JSONObject emailListJSONObject = emailListArrayJSON.getJSONObject(i);
            String currentListName = emailListJSONObject.getString(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    String emailAddresses = emailListJSONObject.getString(IConstants.kEmailAddressesKey);
                    emailAddresses = emailAddresses.replace(emailid+",", "");
                    emailAddresses = emailAddresses.replace(emailid, "");
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                    emailListArrayJSON.put(i,emailListJSONObject);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }
//
    private Boolean deleteEmailList(Integer user_id, String emailListName) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);
        for (int i = 0; i < emailListArrayJSON.length(); i++) {
            org.json.JSONObject emailListJSONObject = emailListArrayJSON.getJSONObject(i);
            String currentListName = emailListJSONObject.getString(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    emailListArrayJSON.remove(i);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }

    private Boolean AddEmailListUserPreference(Integer user_id, JSONArray json_user_preferences_emails) throws SQLException {
        org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }

    private Boolean updateEmailListUserPreference(Integer user_id, org.json.JSONArray json_user_preferences_emails) throws SQLException {
        org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }
}