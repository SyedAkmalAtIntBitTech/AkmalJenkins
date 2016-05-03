/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.IConstants;
import com.google.gson.Gson;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.EmailListService;
import com.intbittech.utility.StringUtility;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import model.EmailInfo;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

    enum EmailListType {

        Regular, Mindbody
    };

    private final static Logger logger = Logger.getLogger(EmailListServiceImpl.class);
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CompanyPreferencesService companyPreferencesService;
    private CompanyPreferences companyPreferences;

    @Override
    public String getEmailList(HttpServletRequest request, Integer companyId, String emailListName) throws Exception {

        JSONObject responseObject = new JSONObject();
        JSONArray emailListNames = new JSONArray();
        JSONArray emailListNames_mindbody = new JSONArray();

        companyPreferences = companyPreferencesService.getByCompanyId(companyId);
        JSONParser jsonParser = new JSONParser();
        JSONObject emailListJSONObject = (JSONObject) jsonParser.parse(companyPreferences.getEmailList());

        String queryParameter = (String) request.getParameter("update");

        if (queryParameter.equalsIgnoreCase("allEmailListNames")) {
            emailListNames = getEmailListNames(emailListJSONObject, EmailListType.Regular);
            emailListNames_mindbody = getEmailListNames(emailListJSONObject, EmailListType.Mindbody);
            responseObject.put(IConstants.kEmailListUserKey, emailListNames);
            responseObject.put(IConstants.kEmailListMindbodyKey, emailListNames_mindbody);

        } else if (queryParameter.equalsIgnoreCase("emailsForEmailList")) {
            if (!StringUtility.isEmpty(emailListName)) {
                JSONArray json_email_ids = getEmailIds(emailListJSONObject, emailListName, EmailListType.Regular);
                JSONArray json_email_ids_mindbody = getEmailIds(emailListJSONObject, emailListName, EmailListType.Mindbody);
                responseObject.put(IConstants.kEmailListNameKey, emailListName);

                responseObject.put(IConstants.kEmailListMindbodyKey + "_" + IConstants.kEmailAddressesKey, json_email_ids_mindbody);
                responseObject.put(IConstants.kEmailListUserKey + "_" + IConstants.kEmailAddressesKey, json_email_ids);
            }
        } else if (queryParameter.equalsIgnoreCase("allEmailListWithAddresses")) {
            JSONArray emailListArrayJSON = getEmailListForType(emailListJSONObject, EmailListType.Regular);
            JSONArray emailListArrayJSONMindbody = getEmailListForType(emailListJSONObject, EmailListType.Mindbody);
            JSONObject emailList_jsonobject = new JSONObject();
            emailList_jsonobject.put(IConstants.kEmailListUserKey, emailListArrayJSON);
            emailList_jsonobject.put(IConstants.kEmailListMindbodyKey, emailListArrayJSONMindbody);
            responseObject.put(queryParameter, emailList_jsonobject);
        } else if (queryParameter.equalsIgnoreCase("allEmailListWithNoOfContacts")) {
            JSONArray emailListArrayFromUsers = getEmailListForType(emailListJSONObject, EmailListType.Regular);
            JSONArray emailListArrayJSONMindbody = getEmailListForType(emailListJSONObject, EmailListType.Mindbody);
            JSONArray emailListArrayToUI = new JSONArray();
            JSONArray emailListArrayToUIMindbody = new JSONArray();

            for (int i = 0; i < emailListArrayFromUsers.size(); i++) {
                JSONObject emailListObject = new JSONObject();
                JSONObject json_object = (JSONObject) emailListArrayFromUsers.get(i);
                emailListObject.put(IConstants.kEmailListNameKey, json_object.get(IConstants.kEmailListNameKey));
                JSONArray emailAddressArray = (JSONArray) json_object.get(IConstants.kEmailAddressesKey);
                emailListObject.put("noofcontants", emailAddressArray.size());

                emailListObject.put(IConstants.kEmailListDefaultFromName, json_object.get(IConstants.kEmailListDefaultFromName));
                emailListObject.put(IConstants.kEmailListListDescription, json_object.get(IConstants.kEmailListListDescription));
                emailListObject.put(IConstants.kEmailListID, json_object.get(IConstants.kEmailListID));
                emailListObject.put(IConstants.kEmailListAddedDate, json_object.get(IConstants.kEmailListAddedDate));

                emailListArrayToUI.add(emailListObject);
            }
            if(emailListArrayJSONMindbody!=null){
                for (int i = 0; i < emailListArrayJSONMindbody.size(); i++) {
                    JSONObject emailListObject = new JSONObject();
                    JSONObject json_object = (JSONObject) emailListArrayJSONMindbody.get(i);
                    emailListObject.put(IConstants.kEmailListNameKey, json_object.get(IConstants.kEmailListNameKey));
                    JSONArray emails = (JSONArray) json_object.get(IConstants.kEmailAddressesKey);
                    if (!emails.equals("")) {
                        emailListObject.put("noofcontants", emails.size());
                    } else if (emails.equals("")) {
                        emailListObject.put("noofcontants", "0");
                    }

                    emailListObject.put(IConstants.kEmailListDefaultFromName, json_object.get(IConstants.kEmailListDefaultFromName));
                    emailListObject.put(IConstants.kEmailListListDescription, json_object.get(IConstants.kEmailListListDescription));

                    emailListArrayToUIMindbody.add(emailListObject);
                }
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
    public Boolean setEmailList(Map<String, Object> requestBodyMap, Integer companyId) throws SQLException, ParseException, JSONException {
        Boolean dataresponse = false;

        CompanyPreferences companyPreferences = companyPreferencesService.getByCompanyId(companyId);
        JSONParser jsonParser = new JSONParser();
        JSONObject emailListJSONObject = (JSONObject) jsonParser.parse(companyPreferences.getEmailList());

        String queryParameter = (String) requestBodyMap.get("update");

        if (queryParameter.equalsIgnoreCase("addEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailDefaultName = (String) requestBodyMap.get(IConstants.kEmailListDefaultFromName);
            String emailListDescription = (String) requestBodyMap.get(IConstants.kEmailListListDescription);

            dataresponse = addEmailListPreference(emailListJSONObject, emailListName, emailDefaultName, emailListDescription);
        } else if (queryParameter.equalsIgnoreCase("UpdateEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddresses = (String) requestBodyMap.get(IConstants.kEmailAddressesKey);

            dataresponse = updateEmailListPreference(emailListJSONObject, emailListName, emailAddresses);
        } else if (queryParameter.equalsIgnoreCase("deleteEmailInEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressesKey);
            String emails[] = emailAddress.split(",");
            Boolean result = false;
            for (int i = 0; i < emails.length; i++) {
                result = deleteEmailFromEmailList(emailListJSONObject, emailListName, emails[i]);
            }
            if (result) {
                dataresponse = true;
            }
        } else if (queryParameter.equalsIgnoreCase("deleteEmailList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            dataresponse = deleteEmailList(emailListJSONObject, emailListName);
        } else if (queryParameter.equalsIgnoreCase("deleteAllEmailsFromList")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);

            dataresponse = deleteAllEmailsFromEmailList(emailListJSONObject, emailListName);
        } else if (queryParameter.equalsIgnoreCase("updateEmailID")) {

            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressKey);
            String lastName = (String) requestBodyMap.get(IConstants.kEmailLastNameKey);
            String firstName = (String) requestBodyMap.get(IConstants.kEmailFirstNameKey);
            String emailUID = (String) requestBodyMap.get(IConstants.kEmailUIDKey);

            EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName);
            email_info.setId(emailUID);

            dataresponse = addUpdateEmailListPreferenceForEmailID(emailListJSONObject, companyId, emailListName, email_info);
        } else if (queryParameter.equalsIgnoreCase("addEmailID")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressKey);
            String lastName = (String) requestBodyMap.get(IConstants.kEmailLastNameKey);
            String firstName = (String) requestBodyMap.get(IConstants.kEmailFirstNameKey);

            EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName, dateFormat.format(new Date()));

            dataresponse = addEmailIDToEmailList(emailListJSONObject, emailListName, email_info);
        } else if (queryParameter.equalsIgnoreCase("checkAvailability")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            String emailAddress = (String) requestBodyMap.get(IConstants.kEmailAddressKey);
            String lastName = (String) requestBodyMap.get(IConstants.kEmailLastNameKey);
            String firstName = (String) requestBodyMap.get(IConstants.kEmailFirstNameKey);

            EmailInfo email_info = new EmailInfo(emailAddress, firstName, lastName);

            dataresponse = checkAvailability(emailListJSONObject, emailListName, email_info);
        } else if (queryParameter.equalsIgnoreCase("deleteAllEmailLists")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            dataresponse = deleteAllEmailList(emailListJSONObject, emailListName);
        } else if (queryParameter.equalsIgnoreCase("deleteEmailLists")) {
            String emailListName = (String) requestBodyMap.get(IConstants.kEmailListNameKey);
            dataresponse = deleteEmailList(emailListJSONObject, emailListName);

        }
        return dataresponse;
    }

    private JSONArray getEmailIds(JSONObject emailListsJSONObject, String emailListName, EmailListType emailListType) throws ClassNotFoundException, SQLException {
        JSONArray json_email_address = new JSONArray();
        JSONArray emailListToProcess = getEmailListForType(emailListsJSONObject, emailListType);

        try {
            JSONArray emailListInContext = new JSONArray();
            for (int i = 0; i < emailListToProcess.size(); i++) {
                JSONObject emailListJSONObject = (JSONObject) emailListToProcess.get(i);
                String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
                if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                    if (emailListName.equals(currentListName)) {
                        emailListInContext = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                        break;
                    }
                }
            }

            for (int i = 0; i < emailListInContext.size(); i++) {
                JSONObject emailAddressJSONObject = (JSONObject) emailListInContext.get(i);
                EmailInfo email_model = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                json_email_address.add(email_model.getEmailInfoJSONObject());
            }

        } catch (Exception e) {
            logger.error(e);
        }
        return json_email_address;
    }

    private JSONArray getEmailListForType(JSONObject emailListJSONObject, EmailListType emailListType) {
        JSONArray emailListArrayJSON = new JSONArray();

        switch (emailListType) {
            case Regular:
                emailListArrayJSON = (JSONArray) emailListJSONObject.get(IConstants.kEmailListUserKey);
                break;
            case Mindbody:
                emailListArrayJSON = (JSONArray) emailListJSONObject.get(IConstants.kEmailListMindbodyKey);
                break;
            default:
        }
        return emailListArrayJSON;
    }
    private JSONArray getEmailListNames(JSONObject emailListJSONObject, EmailListType emailListType) throws ClassNotFoundException, SQLException {
        JSONArray emailListNamesJSON = new JSONArray();
        JSONArray emailListToProcess = getEmailListForType(emailListJSONObject, emailListType);
        for (int i = 0; i < emailListToProcess.size(); i++) {
            JSONObject emailObject = (JSONObject) emailListToProcess.get(i);
            emailListNamesJSON.add(emailObject.get(IConstants.kEmailListNameKey));
        }
        return emailListNamesJSON;
    }

    private boolean addEmailListPreference(JSONObject emailListsJSONObject, String emailListName, String defaultName, String listDescription) throws SQLException {
        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);
        UUID uniqueKey = UUID.randomUUID();

        if (emailListArrayJSON.size() != 0) {
            JSONObject json_user_preferences_email = new JSONObject();

            json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
            json_user_preferences_email.put(IConstants.kEmailAddressesKey, new JSONArray());
            json_user_preferences_email.put(IConstants.kEmailListDefaultFromName, defaultName);
            json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);
            json_user_preferences_email.put(IConstants.kEmailListID, uniqueKey.toString());
            json_user_preferences_email.put(IConstants.kEmailListAddedDate, dateFormat.format(new Date()));

            emailListArrayJSON.add(json_user_preferences_email);
            return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);
        } else {
            JSONObject json_user_preferences_email = new JSONObject();

            json_user_preferences_email.put(IConstants.kEmailListNameKey, emailListName);
            json_user_preferences_email.put(IConstants.kEmailAddressesKey, new JSONArray());

            json_user_preferences_email.put(IConstants.kEmailListDefaultFromName, defaultName);
            json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);
            json_user_preferences_email.put(IConstants.kEmailListID, uniqueKey.toString());
            json_user_preferences_email.put(IConstants.kEmailListAddedDate, dateFormat.format(new Date()));
            json_user_preferences_email.put(IConstants.kEmailListDescription, listDescription);

            JSONArray emailListArray = new JSONArray();
            emailListArray.add(json_user_preferences_email);
            return updateEmailListUserPreference(emailListsJSONObject, emailListArray);
        }
    }

    private boolean updateEmailListPreference(JSONObject emailListsJSONObject, String emailListName, String emailAddresses) throws JSONException, SQLException {

        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);

        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
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

        return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);

    }

    private boolean addEmailIDToEmailList(JSONObject emailListsJSONObject, String emailListName, EmailInfo email_info) throws JSONException, SQLException, ParseException {

        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
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

        return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);

    }

    private boolean addUpdateEmailListPreferenceForEmailID(JSONObject emailListsJSONObject, Integer companyId, String emailListName, EmailInfo email_info) throws JSONException, SQLException, ParseException {

        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
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

        return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);

    }

    private boolean deleteAllEmailsFromEmailList(JSONObject emailListsJSONObject, String emailListName) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {
                    emailListJSONObject.put(IConstants.kEmailAddressesKey, new JSONArray());
                    emailListArrayJSON.remove(i);
                    emailListArrayJSON.add(i, emailListJSONObject);
                    break;
                }
            }
        }
        return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);
    }

    private boolean deleteEmailFromEmailList(JSONObject emailListsJSONObject, String emailListName, String emailid) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            JSONObject emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
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
        return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);
    }

    private Boolean deleteAllEmailList(JSONObject emailListsJSONObject, String emailListName) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);

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
        return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);
    }

    private Boolean deleteEmailList(JSONObject emailListsJSONObject, String emailListName) throws JSONException, SQLException {
        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);
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
        return updateEmailListUserPreference(emailListsJSONObject, emailListArrayJSON);
    }

    private Boolean updateEmailListUserPreference(JSONObject emailListsJSONObject, JSONArray json_user_preferences_emails) throws SQLException {
        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);
        emailListArrayJSON.add(json_user_preferences_emails);
        emailListsJSONObject.put(IConstants.kEmailListUserKey, emailListArrayJSON);
        return saveEmailPreferences(emailListsJSONObject);
    }

    private boolean checkAvailability(JSONObject emailListsJSONObject, String emailListName, EmailInfo email_info) {

        JSONArray emailListArrayJSON = getEmailListForType(emailListsJSONObject, EmailListType.Regular);
        JSONObject emailListJSONObject = new JSONObject();

        for (int i = 0; i < emailListArrayJSON.size(); i++) {
            emailListJSONObject = (JSONObject) emailListArrayJSON.get(i);
            String currentListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
            if (!emailListName.isEmpty() && !currentListName.isEmpty()) {
                if (emailListName.equals(currentListName)) {

                    JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
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

    private Boolean saveEmailPreferences(JSONObject emailListsObject) {
        companyPreferences.setEmailList(emailListsObject.toJSONString());
        companyPreferencesService.updatePreferences(companyPreferences);
        return true;
    }

}