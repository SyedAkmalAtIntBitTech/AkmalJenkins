/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.IConstants;
import com.controller.SqlMethods;
import com.google.gson.Gson;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.services.EmailListService;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import model.EmailInfo;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailListServiceImpl implements EmailListService {

    private final static Logger logger = Logger.getLogger(EmailListServiceImpl.class);
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private MessageSource messageSource;

    SqlMethods sql_methods = new SqlMethods();

    @Override
    public String getEmailList(HttpServletRequest request, Integer companyId) throws Exception {

        JSONObject responseObject = new JSONObject();
        org.json.simple.JSONArray emailListNames = new org.json.simple.JSONArray();
        org.json.simple.JSONArray emailListNames_mindbody = new org.json.simple.JSONArray();

        String queryParameter = (String) request.getParameter("update");

        if (queryParameter.equalsIgnoreCase("allEmailListNames")) {
            emailListNames = getEmailListNames(companyId);
            emailListNames_mindbody = getEmailListNames_mindbody(companyId);
            responseObject.put(IConstants.kEmailListUserKey, emailListNames);
            responseObject.put(IConstants.kEmailListMindbodyKey, emailListNames_mindbody);

        } else if (queryParameter.equalsIgnoreCase("emailsForEmailList")) {
            String emailListName = (String) request.getParameter("list_name");
            org.json.simple.JSONArray json_email_ids = getEmailIds(companyId, emailListName);
            org.json.simple.JSONArray json_email_ids_mindbody = getEmailIds_mindbody(companyId, emailListName);
            responseObject.put(IConstants.kEmailListNameKey, emailListName);

            responseObject.put(IConstants.kEmailListMindbodyKey + "_" + IConstants.kEmailAddressesKey, json_email_ids_mindbody);
            responseObject.put(IConstants.kEmailListUserKey + "_" + IConstants.kEmailAddressesKey, json_email_ids);

        } else if (queryParameter.equalsIgnoreCase("allEmailListWithAddresses")) {
            org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
            org.json.simple.JSONArray emailListArrayJSONMindbody = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListMindbodyKey);
            JSONObject emailList_jsonobject = new JSONObject();
            emailList_jsonobject.put(IConstants.kEmailListUserKey, emailListArrayJSON);
            emailList_jsonobject.put(IConstants.kEmailListMindbodyKey, emailListArrayJSONMindbody);
            responseObject.put(queryParameter, emailList_jsonobject);
        } else if (queryParameter.equalsIgnoreCase("allEmailListWithNoOfContacts")) {
            org.json.simple.JSONArray emailListArrayFromUsers = (org.json.simple.JSONArray) sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
            org.json.simple.JSONArray emailListArrayJSONMindbody = (org.json.simple.JSONArray) sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListMindbodyKey);
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

        String json = new Gson().toJson(responseObject);
        return json;
    }

    @Override
    public Boolean setEmailList(Map<String, Object> requestBodyMap, Integer companyId) throws JSONException, SQLException, ParseException {
        Boolean dataresponse = false;

        String queryParameter = (String) requestBodyMap.get("update");

        if (queryParameter.equalsIgnoreCase("addEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailDefaultName = (String) requestBodyMap.get(IConstants.kEmailListDefaultFromName);
            String emailListDescription = (String) requestBodyMap.get(IConstants.kEmailListListDescription);

            dataresponse = addEmailListPreference(companyId, emailListName, emailDefaultName, emailListDescription);
        } else if (queryParameter.equalsIgnoreCase("UpdateEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddresses = (String) requestBodyMap.get(IConstants.kEmailAddressesKey);

            dataresponse = updateEmailListPreference(companyId, emailListName, emailAddresses);
        } else if (queryParameter.equalsIgnoreCase("deleteEmailInEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressesKey);
            String emails[] = emailAddress.split(",");
            Boolean result = false;
            for (int i = 0; i < emails.length; i++) {
                result = deleteEmailFromEmailList(companyId, emailListName, emails[i]);
            }
            if (result) {
                dataresponse = true;
            }
        } else if (queryParameter.equalsIgnoreCase("deleteEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            dataresponse = deleteEmailList(companyId, emailListName);
        } else if (queryParameter.equalsIgnoreCase("deleteAllEmailsFromList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);

            dataresponse = deleteAllEmailsFromEmailList(companyId, emailListName);
        } else if (queryParameter.equalsIgnoreCase("updateEmailID")) {

            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressKey);
            String lastName = (String) requestBodyMap.get(IConstants.kEmailLastNameKey);
            String firstName = (String) requestBodyMap.get(IConstants.kEmailFirstNameKey);
            String emailUID = (String) requestBodyMap.get(IConstants.kEmailUIDKey);

            EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName);
            email_info.setId(emailUID);

            dataresponse = addUpdateEmailListPreferenceForEmailID(companyId, emailListName, email_info);
        } else if (queryParameter.equalsIgnoreCase("addEmailID")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressKey);
            String lastName = (String) requestBodyMap.get(IConstants.kEmailLastNameKey);
            String firstName = (String) requestBodyMap.get(IConstants.kEmailFirstNameKey);

            EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName, dateFormat.format(new Date()));

            dataresponse = addEmailIDToEmailList(companyId, emailListName, email_info);
        } else if (queryParameter.equalsIgnoreCase("checkAvailability")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressKey);
            String lastName = (String) requestBodyMap.get(IConstants.kEmailLastNameKey);
            String firstName = (String) requestBodyMap.get(IConstants.kEmailFirstNameKey);

            EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName);

            dataresponse = checkAvailability(companyId, emailListName, email_info);
        } else if (queryParameter.equalsIgnoreCase("deleteAllEmailLists")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            dataresponse = deleteAllEmailList(companyId, emailListName);
        } else if (queryParameter.equalsIgnoreCase("deleteEmailLists")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            dataresponse = deleteEmailList(companyId, emailListName);
            
        }
        return dataresponse;
    }

    private org.json.simple.JSONArray getEmailIds(Integer companyId, String emailListName) throws JSONException, ClassNotFoundException, SQLException {
        org.json.simple.JSONArray emailIDSJSONArray = new org.json.simple.JSONArray();
        org.json.simple.JSONArray json_email_address = new org.json.simple.JSONArray();
        try {
            org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
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
            logger.error(e);
        }
        return json_email_address;
    }

    private org.json.simple.JSONArray getEmailIds_mindbody(Integer companyId, String emailListName) throws JSONException, ClassNotFoundException, SQLException {
        org.json.simple.JSONArray json_email_address = new org.json.simple.JSONArray();
        try {

            org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListMindbodyKey);
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
            logger.error(e);
        }
        return json_email_address;
    }

    private org.json.simple.JSONArray getEmailListNames(Integer companyId) throws JSONException, ClassNotFoundException, SQLException {
        JSONArray emailListNamesJSON = new JSONArray();
        org.json.simple.JSONArray emailListNamesjson = new org.json.simple.JSONArray();
        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            emailListNamesJSON.put(emailListJSONObject.get(IConstants.kEmailListNameKey));
            emailListNamesjson.add(emailListJSONObject.get(IConstants.kEmailListNameKey));
        }
        return emailListNamesjson;
    }

    private org.json.simple.JSONArray getEmailListNames_mindbody(Integer companyId) throws JSONException, ClassNotFoundException, SQLException {
        JSONArray emailListNamesJSON = new JSONArray();
        org.json.simple.JSONArray emailListNamesjson = new org.json.simple.JSONArray();
        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListMindbodyKey);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            emailListNamesJSON.put(emailListJSONObject.get(IConstants.kEmailListNameKey));
            emailListNamesjson.add(emailListJSONObject.get(IConstants.kEmailListNameKey));
        }
        return emailListNamesjson;
    }

    private boolean addEmailListPreference(Integer companyId, String emailListName, String defaultName, String listDescription) throws SQLException {
        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
        UUID uniqueKey = UUID.randomUUID();

        if (emailListArrayJSON.size() != 0) {
            JSONObject json_user_preferences_email = new JSONObject();

            json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
            json_user_preferences_email.put(IConstants.kEmailAddressesKey, new org.json.simple.JSONArray());
            json_user_preferences_email.put(IConstants.kEmailListDefaultFromName, defaultName);
            json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);
            json_user_preferences_email.put(IConstants.kEmailListID, uniqueKey.toString());
            json_user_preferences_email.put(IConstants.kEmailListAddedDate, dateFormat.format(new Date()));

            emailListArrayJSON.add(json_user_preferences_email);
            return AddEmailListUserPreference(companyId, emailListArrayJSON);
        } else {
            JSONObject json_user_preferences_email = new JSONObject();

            json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
            json_user_preferences_email.put(IConstants.kEmailAddressesKey, new org.json.simple.JSONArray());

            json_user_preferences_email.put(IConstants.kEmailListDefaultFromName, defaultName);
            json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);
            json_user_preferences_email.put(IConstants.kEmailListID, uniqueKey.toString());
            json_user_preferences_email.put(IConstants.kEmailListAddedDate, dateFormat.format(new Date()));
            json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);

            org.json.simple.JSONArray emailListArray = new org.json.simple.JSONArray();
            emailListArray.add(json_user_preferences_email);
            return AddEmailListUserPreference(companyId, emailListArray);
        }
    }

    private boolean updateEmailListPreference(Integer companyId, String emailListName, String emailAddresses) throws JSONException, SQLException {

        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    org.json.simple.JSONArray emailAddressesJSONArray = (org.json.simple.JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
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

                    org.json.simple.JSONArray emailAddressesJSON = new org.json.simple.JSONArray();
                    for (String emailAddress : receivedEmailAddressesSet) {

                        /* id generated randomly*/
                        EmailInfo email_info = new EmailInfo(emailAddress, dateFormat.format(new Date()));

                        try {
                            emailAddressesJSONArray.add(email_info.getEmailInfoJSONObject());
                        } catch (ParseException ex) {
                            logger.debug(ex);
                        }
                    }
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddressesJSONArray);
                    emailListArrayJSON.set(i, emailListJSONObject);
                    break;
                }
            }
        }

        return updateEmailListUserPreference(companyId, emailListArrayJSON);

    }

    private boolean addEmailIDToEmailList(Integer companyId, String emailListName, EmailInfo email_info) throws JSONException, SQLException, ParseException {

        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    org.json.simple.JSONArray emailAddressesJSONArray = (org.json.simple.JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);

                    emailAddressesJSONArray.add(email_info.getEmailInfoJSONObject());

                    emailListJSONObject.put(IConstants.kEmailAddressesKey, emailAddressesJSONArray);
                    emailListArrayJSON.set(i, emailListJSONObject);
                    break;
                }
            }
        }

        return updateEmailListUserPreference(companyId, emailListArrayJSON);

    }

    private boolean addUpdateEmailListPreferenceForEmailID(Integer companyId, String emailListName, EmailInfo email_info) throws JSONException, SQLException, ParseException {

        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    org.json.simple.JSONArray emailAddressesJSONArray = (org.json.simple.JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    for (int j = 0; j < emailAddressesJSONArray.size(); j++) {
                        JSONObject emailAddressJSONObject = (JSONObject) emailAddressesJSONArray.get(j);

                        EmailInfo email_info_from_database = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                        String UUID = email_info.getId();
                        String UUID_from_db = email_info_from_database.getId();
                        if (UUID.equalsIgnoreCase(UUID_from_db)) {

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

        return updateEmailListUserPreference(companyId, emailListArrayJSON);

    }

    private boolean deleteAllEmailsFromEmailList(Integer companyId, String emailListName) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = (org.json.simple.JSONArray) sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, new org.json.simple.JSONArray());
                    emailListArrayJSON.remove(i);
                    emailListArrayJSON.add(i, emailListJSONObject);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(companyId, emailListArrayJSON);
    }

    private boolean deleteEmailFromEmailList(Integer companyId, String emailListName, String emailid) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = (org.json.simple.JSONArray) sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    org.json.simple.JSONArray emailAddressesJSONArray = (org.json.simple.JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
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
                    emailListArrayJSON.add(i, emailListJSONObject);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(companyId, emailListArrayJSON);
    }

    private Boolean deleteAllEmailList(Integer companyId, String emailListName) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);

        String emailAddressesSplit[] = emailListName.split(",");

        Set<String> receivedEmailListNames = new HashSet<String>(Arrays.asList(emailAddressesSplit));

        for (String emailList : receivedEmailListNames) {

            String emailLists = emailList;
            for (int j = 0; j < emailListArrayJSON.size(); j++) {
                JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(j);
                String id = (String) emailListJSONObject.get(IConstants.kEmailListID);

                if (!emailListName.isEmpty() && id != null) {
                    if (emailLists.equals(id)) {
                        emailListArrayJSON.remove(j);
                    }
                }
            }
        }
        return updateEmailListUserPreference(companyId, emailListArrayJSON);
    }

    private Boolean deleteEmailList(Integer companyId, String emailListName) throws JSONException, SQLException {
        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String emailAddressesSplit[] = emailListName.split(",");
            String id = (String) emailListJSONObject.get(IConstants.kEmailListID);
            if (!emailAddressesSplit[0].isEmpty() && id != null) {
                if (emailAddressesSplit[0].equals(id)) {
                    emailListArrayJSON.remove(i);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(companyId, emailListArrayJSON);
    }

    private Boolean AddEmailListUserPreference(Integer companyId, org.json.simple.JSONArray json_user_preferences_emails) throws SQLException {
        org.json.simple.JSONObject userPreferences = sql_methods.getJSONUserPreferences(companyId);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return sql_methods.updateJSONUserPreference(companyId, userPreferences);
    }

    private Boolean updateEmailListUserPreference(Integer companyId, org.json.simple.JSONArray json_user_preferences_emails) throws SQLException {
        org.json.simple.JSONObject userPreferences = sql_methods.getJSONUserPreferences(companyId);
        userPreferences.put(IConstants.kEmailAddressUserPreferenceKey, json_user_preferences_emails);
        return sql_methods.updateJSONUserPreference(companyId, userPreferences);
    }

    private boolean checkAvailability(Integer companyId, String emailListName, EmailInfo email_info) {

        org.json.simple.JSONArray emailListArrayJSON = sql_methods.getEmailListsPreferences(companyId, IConstants.kEmailListUserKey);
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    org.json.simple.JSONArray emailAddressesJSONArray = (org.json.simple.JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                    for (int j = 0; j < emailAddressesJSONArray.size(); j++) {
                        JSONObject emailAddressJSONObject = (JSONObject) emailAddressesJSONArray.get(j);

                        EmailInfo email_info_from_database = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                        String email_address = email_info.getEmailAddress();
                        String email_address_from_db = email_info_from_database.getEmailAddress();
                        if (email_address.equalsIgnoreCase(email_address_from_db)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
