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
public class GetEmailLists extends BrndBotBaseHttpServlet {


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

            String queryParameter = request.getParameter("query");
            if (queryParameter.equalsIgnoreCase("allEmailListNames")) {
                String emailListNames = getEmailListNames(user_id);
                responseObject.put(queryParameter, emailListNames);
            } else if (queryParameter.equalsIgnoreCase("emailsForEmailList")) {
                String emailListName = request.getParameter(IConstants.kEmailListNameKey);
                String emailIds = getEmailIds(user_id, emailListName);
                responseObject.put(IConstants.kEmailListNameKey, emailListName);
                responseObject.put(IConstants.kEmailAddressesKey, emailIds);
            } else if (queryParameter.equalsIgnoreCase("allEmailListWithAddresses")) {
                JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);
                responseObject.put(queryParameter, emailListArrayJSON);
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

    private String getEmailIds(Integer user_id, String emailListName) throws JSONException, ClassNotFoundException, SQLException {
        String emailIDs = "";
        JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);
        for (int i = 0; i < emailListArrayJSON.length(); i++) {
            JSONObject emailListJSONObject = emailListArrayJSON.getJSONObject(i);
            String currentListName = emailListJSONObject.getString(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    emailIDs = emailListJSONObject.getString(IConstants.kEmailAddressesKey);
                }
            }
        }
        return emailIDs;
    }

    private String getEmailListNames(Integer user_id) throws JSONException, ClassNotFoundException, SQLException {
        JSONArray emailListNamesJSON = new JSONArray();
        JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id);
        for (int i = 0; i < emailListArrayJSON.length(); i++) {
            JSONObject emailListJSONObject = emailListArrayJSON.getJSONObject(i);
            emailListNamesJSON.put(emailListJSONObject.get(IConstants.kEmailListNameKey));
        }
        return emailListNamesJSON.toString();
    }

}
