/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.ScheduledEntityType;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EmailIds;
import model.EmailInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author AR
 */
public class GetEmailLists extends BrndBotBaseHttpServlet {

    SqlMethods sql_methods = new SqlMethods();

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
        StringBuffer string_buffer = new StringBuffer();
        org.json.simple.JSONArray emailListNames = new org.json.simple.JSONArray();
        org.json.simple.JSONArray emailListNames_mindbody = new org.json.simple.JSONArray();
        try {

            String queryParameter = request.getParameter("update");
            sql_methods.session = request.getSession();
            Integer user_id = (Integer) sql_methods.session.getAttribute("UID");

            if (queryParameter.equalsIgnoreCase("allEmailListNames")) {
                emailListNames = getEmailListNames(user_id);
                emailListNames_mindbody = getEmailListNames_mindbody(user_id);
                responseObject.put(IConstants.kEmailListUserKey, emailListNames);
                responseObject.put(IConstants.kEmailListMindbodyKey, emailListNames_mindbody);

            } else if (queryParameter.equalsIgnoreCase("emailsForEmailList")) {
                String emailListName = request.getParameter("list_name");
                org.json.simple.JSONArray json_email_ids = getEmailIds(user_id, emailListName);
                org.json.simple.JSONArray json_email_ids_mindbody = getEmailIds_mindbody(user_id, emailListName);
                responseObject.put(IConstants.kEmailListNameKey, emailListName);

                responseObject.put(IConstants.kEmailListMindbodyKey + "_" + IConstants.kEmailAddressesKey, json_email_ids_mindbody);
                responseObject.put(IConstants.kEmailListUserKey + "_" + IConstants.kEmailAddressesKey, json_email_ids);

            } else if (queryParameter.equalsIgnoreCase("allEmailListWithAddresses")) {
                org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
                org.json.simple.JSONArray emailListArrayJSONMindbody = sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListMindbodyKey);
                JSONObject emailList_jsonobject = new JSONObject();
                emailList_jsonobject.put(IConstants.kEmailListUserKey, emailListArrayJSON);
                emailList_jsonobject.put(IConstants.kEmailListMindbodyKey, emailListArrayJSONMindbody);
                responseObject.put(queryParameter, emailList_jsonobject);
            } else if (queryParameter.equalsIgnoreCase("allEmailListWithNoOfContacts")) {
                org.json.simple.JSONArray emailListArrayFromUsers = (org.json.simple.JSONArray) sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
                org.json.simple.JSONArray emailListArrayJSONMindbody = (org.json.simple.JSONArray) sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListMindbodyKey);
                org.json.simple.JSONArray emailListArrayToUI = new org.json.simple.JSONArray();
                org.json.simple.JSONArray emailListArrayToUIMindbody = new org.json.simple.JSONArray();

                for (int i = 0; i < emailListArrayFromUsers.size(); i++) {
                    org.json.simple.JSONObject emailListObject = new org.json.simple.JSONObject();
                    org.json.simple.JSONObject json_object = (org.json.simple.JSONObject) emailListArrayFromUsers.get(i);
                    emailListObject.put(IConstants.kEmailListNameKey, json_object.get(IConstants.kEmailListNameKey));
                    org.json.simple.JSONArray emailAddressArray = (org.json.simple.JSONArray) json_object.get(IConstants.kEmailAddressesKey);
                    emailListObject.put("noofcontants", emailAddressArray.size());

                    emailListObject.put(IConstants.kEmailListDefaultFromName, json_object.get(IConstants.kEmailListDefaultFromName));
                    emailListObject.put(IConstants.kEmailListListDescription, json_object.get(IConstants.kEmailListListDescription));
                    emailListObject.put(IConstants.kEmailListID, json_object.get(IConstants.kEmailListID));
                    emailListObject.put(IConstants.kEmailListAddedDate, json_object.get(IConstants.kEmailListAddedDate));
                    
                    emailListArrayToUI.add(emailListObject);
                }

                for (int i = 0; i < emailListArrayJSONMindbody.size(); i++) {
                    org.json.simple.JSONObject emailListObject = new org.json.simple.JSONObject();
                    org.json.simple.JSONObject json_object = (org.json.simple.JSONObject) emailListArrayJSONMindbody.get(i);
                    emailListObject.put(IConstants.kEmailListNameKey, json_object.get(IConstants.kEmailListNameKey));
                    org.json.simple.JSONArray emails = (org.json.simple.JSONArray) json_object.get(IConstants.kEmailAddressesKey);
                    if (!emails.equals("")) {
//                        String email_addresses[] = json_object.get(IConstants.kEmailAddressesKey).toString().split(",");
                        emailListObject.put("noofcontants", emails.size());
                    } else if (emails.equals("")) {
                        emailListObject.put("noofcontants", "0");
                    }

                    emailListObject.put(IConstants.kEmailListDefaultFromName, json_object.get(IConstants.kEmailListDefaultFromName));
                    emailListObject.put(IConstants.kEmailListListDescription, json_object.get(IConstants.kEmailListListDescription));

                    emailListArrayToUIMindbody.add(emailListObject);
                }
                JSONObject json_email_contacts = new JSONObject();
                json_email_contacts.put(IConstants.kEmailListUserKey, emailListArrayToUI);
                json_email_contacts.put(IConstants.kEmailListMindbodyKey, emailListArrayToUIMindbody);
                responseObject.put(queryParameter, json_email_contacts);
            }
        } catch (ClassNotFoundException | SQLException | JSONException e) {
            try {
                responseObject.put("Error", "Request unsuccessfull");
            } catch (Exception ex) {
                logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating email list:", getSqlMethodsInstance().error));
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, util.Utility.logMessage(ex, "Exception while updating email list:", getSqlMethodsInstance().error));
        } finally {
            getSqlMethodsInstance().closeConnection();
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

    private org.json.simple.JSONArray getEmailIds(Integer user_id, String emailListName) throws JSONException, ClassNotFoundException, SQLException {
        org.json.simple.JSONArray emailIDSJSONArray = new org.json.simple.JSONArray();
        org.json.simple.JSONArray json_email_address = new org.json.simple.JSONArray();
        try {
            org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
            for (int i = 0; i < emailListArrayJSON.size(); i++) {
                JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
                String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
                if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                    if (emailListName.equals(currentListName)) {
                        emailIDSJSONArray = (org.json.simple.JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                        break;
                    }
                }
            }
            for (int i = 0; i < emailIDSJSONArray.size(); i++) {
                JSONObject emailAddressJSONObject = (JSONObject) emailIDSJSONArray.get(i);
                EmailInfo email_model = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                json_email_address.add(email_model.getEmailInfoJSONObject());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }
        return json_email_address;
    }

    private org.json.simple.JSONArray getEmailIds_mindbody(Integer user_id, String emailListName) throws JSONException, ClassNotFoundException, SQLException {
        org.json.simple.JSONArray json_email_address = new org.json.simple.JSONArray();
        try {

            org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListMindbodyKey);
            for (int i = 0; i < emailListArrayJSON.size(); i++) {
                JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
                String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
                if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                    if (emailListName.equals(currentListName)) {
                        json_email_address = (org.json.simple.JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);
        }
        return json_email_address;
    }

    private org.json.simple.JSONArray getEmailListNames(Integer user_id) throws JSONException, ClassNotFoundException, SQLException {
        JSONArray emailListNamesJSON = new JSONArray();
        org.json.simple.JSONArray emailListNamesjson = new org.json.simple.JSONArray();
        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            emailListNamesJSON.put(emailListJSONObject.get(IConstants.kEmailListNameKey));
            emailListNamesjson.add(emailListJSONObject.get(IConstants.kEmailListNameKey));
        }
        return emailListNamesjson;
    }

    private org.json.simple.JSONArray getEmailListNames_mindbody(Integer user_id) throws JSONException, ClassNotFoundException, SQLException {
        JSONArray emailListNamesJSON = new JSONArray();
        org.json.simple.JSONArray emailListNamesjson = new org.json.simple.JSONArray();
        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(user_id, IConstants.kEmailListMindbodyKey);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            emailListNamesJSON.put(emailListJSONObject.get(IConstants.kEmailListNameKey));
            emailListNamesjson.add(emailListJSONObject.get(IConstants.kEmailListNameKey));
        }
        return emailListNamesjson;
    }

}
