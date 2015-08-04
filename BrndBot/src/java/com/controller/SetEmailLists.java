/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject responseObject = new JSONObject();

        try {
            getSqlMethodsInstance().session = request.getSession();
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");

            String queryParameter = request.getParameter("update");
            if (queryParameter.equalsIgnoreCase("addUpdateEmailList")) {
                String emailListName = request.getParameter(IConstants.kEmailListNameKey);
                String emailAddresses = request.getParameter(IConstants.kEmailAddressesKey);
                Boolean result = updateEmailListPreference(user_id, emailListName, emailAddresses);
                responseObject.put(queryParameter, result);
            } else if (queryParameter.equalsIgnoreCase("deleteEmailInEmailList")) {
                String emailListName = request.getParameter(IConstants.kEmailListNameKey);
                String emailAddress = request.getParameter(IConstants.kEmailAddressKey);
                Boolean result = deleteEmailFromEmailList(user_id, emailListName, emailAddress);
                responseObject.put(queryParameter, result);
            } else if (queryParameter.equalsIgnoreCase("deleteEmailList")) {
                String emailListName = request.getParameter(IConstants.kEmailListNameKey);
                Boolean result = deleteEmailList(user_id, emailListName);
                responseObject.put(queryParameter, result);
            }
        } catch (Exception e) {
            try {
                responseObject.put("Error", "Request unsuccessfull");
            } catch (JSONException ex) {
                Logger.getLogger(GetEmailLists.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String json = new Gson().toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(json);
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
        for (int i = 0; i < emailListArrayJSON.length(); i++) {
            JSONObject emailListJSONObject = emailListArrayJSON.getJSONObject(i);
            String currentListName = emailListJSONObject.getString(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }

    private Boolean deleteEmailFromEmailList(Integer user_id, String emailListName, String emailAddress) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);
        for (int i = 0; i < emailListArrayJSON.length(); i++) {
            JSONObject emailListJSONObject = emailListArrayJSON.getJSONObject(i);
            String currentListName = emailListJSONObject.getString(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    String emailAddresses = emailListJSONObject.getString(IConstants.kEmailAddressesKey);
                    emailAddresses = emailAddresses.replace(emailAddress+",", "");
                    emailAddresses = emailAddresses.replace(emailAddress, "");
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddresses);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }

    private Boolean deleteEmailList(Integer user_id, String emailListName) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);
        for (int i = 0; i < emailListArrayJSON.length(); i++) {
            JSONObject emailListJSONObject = emailListArrayJSON.getJSONObject(i);
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

    private Boolean updateEmailListUserPreference(Integer user_id, JSONArray emailListsPreference) throws SQLException {
        org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, emailListsPreference);
        return getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }
}
