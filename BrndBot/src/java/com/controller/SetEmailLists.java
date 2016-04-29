/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EmailInfo;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author AR
 */
public class SetEmailLists extends BrndBotBaseHttpServlet {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
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

            if (queryParameter.equalsIgnoreCase("addEmailList")){
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                String emailDefaultName = (String)json_update.get(IConstants.kEmailListDefaultFromName);
                String emailListDescription = (String)json_update.get(IConstants.kEmailListListDescription);
                
                Boolean result = addEmailListPreference(user_id, emailListName, emailDefaultName, emailListDescription);
                if (result){
                    dataresponse = "true";
                }
            }else if (queryParameter.equalsIgnoreCase("UpdateEmailList")) {
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                String emailAddresses = (String)json_update.get(IConstants.kEmailAddressesKey);
                
                Boolean result = updateEmailListPreference(user_id, emailListName, emailAddresses);
                if (result){
                    dataresponse = "true";
                }
            } else if (queryParameter.equalsIgnoreCase("deleteEmailInEmailList")) {
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                String emailAddress = (String)json_update.get(IConstants.kEmailAddressesKey);
                String emails[] = emailAddress.split(",");
                Boolean result = false;                
                for(int i = 0; i< emails.length; i++){
                    result = deleteEmailFromEmailList(user_id, emailListName, emails[i]);
                }
                if (result){
                    dataresponse = "true";
                }
            } else if (queryParameter.equalsIgnoreCase("deleteEmailList")) {
                String emailListName = request.getParameter(IConstants.kEmailListNameKey);
                Boolean result = deleteEmailList(user_id, emailListName);
                if (result){
                    dataresponse = "true";
                }
            } else if (queryParameter.equalsIgnoreCase("deleteAllEmailsFromList")){
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                
                boolean result = deleteAllEmailsFromEmailList(user_id, emailListName);
                if (result){
                    dataresponse = "true";
                }
            } else if (queryParameter.equalsIgnoreCase("updateEmailID")){
                
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                String emailAddress = (String)json_update.get(IConstants.kEmailAddressKey);
                String lastName = (String)json_update.get(IConstants.kEmailLastNameKey);
                String firstName = (String)json_update.get(IConstants.kEmailFirstNameKey);
                String emailUID = (String)json_update.get(IConstants.kEmailUIDKey);
                
                EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName);
                email_info.setId(emailUID);
                
                boolean result = addUpdateEmailListPreferenceForEmailID(user_id, emailListName, email_info);
                if (result){
                    dataresponse = "true";
                }
            }else if (queryParameter.equalsIgnoreCase("addEmailID")){
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                String emailAddress = (String)json_update.get(IConstants.kEmailAddressKey);
                String lastName = (String)json_update.get(IConstants.kEmailLastNameKey);
                String firstName = (String)json_update.get(IConstants.kEmailFirstNameKey);

                EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName, dateFormat.format(new Date()));

                boolean result = addEmailIDToEmailList(user_id, emailListName, email_info);
                if (result){
                    dataresponse = "true";
                }
                    
            }else if (queryParameter.equalsIgnoreCase("checkAvailability")){
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                String emailAddress = (String)json_update.get(IConstants.kEmailAddressKey);
                String lastName = (String)json_update.get(IConstants.kEmailLastNameKey);
                String firstName = (String)json_update.get(IConstants.kEmailFirstNameKey);

                EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName);

                boolean availability = checkAvailability(user_id, emailListName, email_info);
                if (availability){
                    dataresponse = "true";
                }else {
                    dataresponse = "false";
                }
            }
            else if (queryParameter.equalsIgnoreCase("deleteAllEmailLists")){
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                Boolean result = deleteAllEmailList(user_id, emailListName);
                if (result){
                    dataresponse = "true";
                }
            }
            else if (queryParameter.equalsIgnoreCase("deleteEmailLists")){
                String emailListName = (String)json_update.get(IConstants.kEmailListNameKey);
                Boolean result = deleteEmailList(user_id, emailListName);
                if (result){
                    dataresponse = "true";
                }
            }
        } catch (SQLException | JSONException | ParseException e) {
            try {
                responseObject.put("Error", "Request unsuccessfull");
            } catch (Exception ex){
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while update the email list:", getSqlMethodsInstance().error));
            }    
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
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
    
    
    private boolean addEmailListPreference(Integer user_id, String emailListName, String defaultName, String listDescription)throws SQLException{
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        UUID uniqueKey = UUID.randomUUID();
        
        if (emailListArrayJSON.size() != 0){
                JSONObject json_user_preferences_email = new JSONObject();

                json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
                json_user_preferences_email.put(IConstants.kEmailAddressesKey, new JSONArray());
                json_user_preferences_email.put(IConstants.kEmailListDefaultFromName, defaultName);
                json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);
                json_user_preferences_email.put(IConstants.kEmailListID, uniqueKey.toString());
                json_user_preferences_email.put(IConstants.kEmailListAddedDate, dateFormat.format(new Date()));

                emailListArrayJSON.add(json_user_preferences_email);
                return AddEmailListUserPreference(user_id, emailListArrayJSON);
        }else {
                JSONObject json_user_preferences_email = new JSONObject();

                json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
                json_user_preferences_email.put(IConstants.kEmailAddressesKey, new JSONArray());
                
                json_user_preferences_email.put(IConstants.kEmailListDefaultFromName, defaultName);
                json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);
                json_user_preferences_email.put(IConstants.kEmailListID, uniqueKey.toString());
                json_user_preferences_email.put(IConstants.kEmailListAddedDate, dateFormat.format(new Date()));
                json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);

                org.json.simple.JSONArray emailListArray = new org.json.simple.JSONArray();
                emailListArray.add(json_user_preferences_email);
                return AddEmailListUserPreference(user_id, emailListArray);
        }
    }

    private boolean updateEmailListPreference(Integer user_id, String emailListName, String emailAddresses) throws JSONException, SQLException {
        
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();
        
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String currentListName = (String)emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    String emailAddressesSplit[] = emailAddresses.split(",");
                    
                    Set<String> receivedEmailAddressesSet = new HashSet<String>(Arrays.asList(emailAddressesSplit));
                    
                    //Cleaning up the set for existing values.
                    for (Object emailAddressObject : emailAddressesJSONArray) {
                        JSONObject emailAddressJSONObject = (JSONObject) emailAddressObject;
                        EmailInfo email_info = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                        
                        String emailAddress = email_info.getEmailAddress();
                        if (receivedEmailAddressesSet.contains(emailAddress)) {
                            receivedEmailAddressesSet.remove(emailAddress);
                        }
                    }
                    
                    JSONArray emailAddressesJSON = new JSONArray();
                    for (String emailAddress : receivedEmailAddressesSet) {

                        /* id generated randomly*/
                        EmailInfo email_info = new EmailInfo(emailAddress, dateFormat.format(new Date()));

                        try {
                            emailAddressesJSONArray.add(email_info.getEmailInfoJSONObject());
                        } catch (ParseException ex) {
                            Logger.getLogger(SetEmailLists.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddressesJSONArray);
                    emailListArrayJSON.set(i, emailListJSONObject);
                    break;
                }
            }
        }

        return updateEmailListUserPreference(user_id, emailListArrayJSON);
        
    }

    private boolean addEmailIDToEmailList(Integer user_id, String emailListName, EmailInfo email_info) throws JSONException, SQLException, ParseException {
        
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();
        
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String currentListName = (String)emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    
                    emailAddressesJSONArray.add(email_info.getEmailInfoJSONObject());
                    
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddressesJSONArray);
                    emailListArrayJSON.set(i, emailListJSONObject);
                    break;
                }
            }
        }

        return updateEmailListUserPreference(user_id, emailListArrayJSON);
        
    }
    
    private boolean addUpdateEmailListPreferenceForEmailID(Integer user_id, String emailListName, EmailInfo email_info) throws JSONException, SQLException, ParseException {
        
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();
        
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String currentListName = (String)emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    for (int j = 0 ; j < emailAddressesJSONArray.size(); j++){
                        JSONObject emailAddressJSONObject = (JSONObject)emailAddressesJSONArray.get(j);
                        
                        EmailInfo email_info_from_database = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                        String UUID = email_info.getId();
                        String UUID_from_db = email_info_from_database.getId();
                        if (UUID.equalsIgnoreCase(UUID_from_db)){
                            
                            email_info_from_database.setFirstName(email_info.getFirstName());
                            email_info_from_database.setLastName(email_info.getLastName());
                            email_info_from_database.setEmailAddress(email_info.getEmailAddress());
                            emailAddressesJSONArray.set(j, email_info_from_database.getEmailInfoJSONObject());
                            break;
                        }
                    }
                    
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddressesJSONArray);
                    emailListArrayJSON.set(i, emailListJSONObject);
                    break;
                }
            }
        }

        return updateEmailListUserPreference(user_id, emailListArrayJSON);
        
    }
    
    private boolean deleteAllEmailsFromEmailList(Integer user_id, String emailListName) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = (org.json.simple.JSONArray) getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String currentListName = (String)emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, new JSONArray());
                    emailListArrayJSON.remove(i);
                    emailListArrayJSON.add(i,emailListJSONObject);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }

    private boolean deleteEmailFromEmailList(Integer user_id, String emailListName, String emailid) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = (org.json.simple.JSONArray) getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String currentListName = (String)emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    
                    JSONArray emailAddressesJSONArray = (JSONArray)emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    for (Object emailAddressesJSONArrayItem : emailAddressesJSONArray) {
                        JSONObject emailAddressJSONObject = (JSONObject) emailAddressesJSONArrayItem;
                        String emailAddress = (String) emailAddressJSONObject.get("id");
                        if (emailAddress.equalsIgnoreCase(emailid)) {
                            emailAddressesJSONArray.remove(emailAddressesJSONArrayItem);
                            break;
                        }
                    }
                    
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddressesJSONArray);
                    emailListArrayJSON.remove(i);
                    emailListArrayJSON.add(i,emailListJSONObject);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }

    
    
    private Boolean deleteAllEmailList(Integer user_id, String emailListName) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);

            String emailAddressesSplit[] = emailListName.split(",");
            
            Set<String> receivedEmailListNames = new HashSet<String>(Arrays.asList(emailAddressesSplit));

            for (String emailList : receivedEmailListNames) {
                
                String emailLists = emailList;
                for (int j = 0; j< emailListArrayJSON.size(); j++){
                    JSONObject emailListJSONObject = (JSONObject)emailListArrayJSON.get(j);
                    String id = (String)emailListJSONObject.get(IConstants.kEmailListID);

                    if (!emailListName.isEmpty() && id!=null) {
                        if(emailLists.equals(id))
                        {
                            emailListArrayJSON.remove(j);
                        }
                    }
                }
            }         
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }
    
    private Boolean deleteEmailList(Integer user_id, String emailListName) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String emailAddressesSplit[] = emailListName.split(",");
            String id = (String)emailListJSONObject.get(IConstants.kEmailListID);
            if (!emailAddressesSplit[0].isEmpty() && id!=null) {
                if (emailAddressesSplit[0].equals(id)) {
                    emailListArrayJSON.remove(i);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(user_id, emailListArrayJSON);
    }

    private Boolean AddEmailListUserPreference(Integer user_id, org.json.simple.JSONArray json_user_preferences_emails) throws SQLException {
//        org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
//        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return  false;//getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }

    private Boolean updateEmailListUserPreference(Integer user_id, org.json.simple.JSONArray json_user_preferences_emails) throws SQLException {
        //org.json.simple.JSONObject userPreferences = getSqlMethodsInstance().getJSONUserPreferences(user_id);
        //userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return  false;//getSqlMethodsInstance().updateJSONUserPreference(user_id, userPreferences);
    }

    private boolean checkAvailability(Integer user_id, String emailListName, EmailInfo email_info) {
        
        org.json.simple.JSONArray emailListArrayJSON = getSqlMethodsInstance().getEmailListsPreferences(user_id, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();
        
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject)emailListArrayJSON.get(i);
            String currentListName = (String)emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    for (int j = 0 ; j < emailAddressesJSONArray.size(); j++){
                        JSONObject emailAddressJSONObject = (JSONObject)emailAddressesJSONArray.get(j);
                        
                        EmailInfo email_info_from_database = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                        String email_address = email_info.getEmailAddress();
                        String email_address_from_db = email_info_from_database.getEmailAddress();
                        if (email_address.equalsIgnoreCase(email_address_from_db)){
                            return true;
                        }
                    }
                }
            }
        }        
        return false;
    }
}