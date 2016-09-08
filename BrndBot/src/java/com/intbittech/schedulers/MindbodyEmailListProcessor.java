/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.schedulers;

import com.intbit.ConnectionManager;
import com.intbittech.component.SpringContextBridge;
import com.intbittech.divtohtml.StringUtil;
import com.intbittech.enums.EmailListTypeConstants;
import com.intbittech.mindbody.MindBodyClass;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.Contacts;
import com.intbittech.model.EmailInfo;
import com.intbittech.model.EmailList;
import com.intbittech.modelmappers.AddEmailListDetails;
import com.intbittech.modelmappers.ContactDetails;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.ContactsService;
import com.intbittech.services.EmailListService;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.Utility;
import com.mindbodyonline.clients.api._0_5Client.Client;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author AR
 */
public class MindbodyEmailListProcessor implements Runnable {

    private static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(MindbodyEmailListProcessor.class));
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private volatile boolean running = true;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //This is added just in case the application server itself shutdowns. That time the contextDestroyed is not called.
    void startThread() {
        running = true;
    }
    
    public void terminateThread() {
        running = false;
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        if (running) {
            try {
                logger.log(Level.INFO, "Scheduler at " + new Date());
                logger.log(Level.INFO, "Started the automation");
                startProcessing();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception Interupted the thread of Mindbody email list."+e.getMessage());
            }
        }
    }

    public void startProcessing() throws ParseException {

        CompanyPreferencesService companyPreferencesService = SpringContextBridge.services().getCompanyPreferencesService();
        List<CompanyPreferences> companyPreferencesList = companyPreferencesService.getAll();

        for (CompanyPreferences companyPreferences : companyPreferencesList) {
            if (!StringUtil.isEmpty(companyPreferences.getCompanyLocation())) {
                Integer value = Integer.parseInt(companyPreferences.getCompanyLocation());
                processEachRowNew(companyPreferences.getFkCompanyId().getCompanyId(), value);
            }
        }        
        
    }
    
    public void processEachRowNew(Integer companyId, Integer companyLocation) throws ParseException {
        int[] siteids = new int[]{companyLocation};
        MindBodyClass mind_body_class = new MindBodyClass(siteids);
        HashMap<String, List<Client>> clientIndexesHashmap = new HashMap<String, List<Client>>();
        try {
            long startTime = System.currentTimeMillis();
            logger.log(Level.INFO, "Started working on getting email lists for :" + siteids[0] + " - " + companyId);
            try {
                clientIndexesHashmap = mind_body_class.getAllClientIndexes();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Mindbody email list processor", ex);
            }
            logger.log(Level.INFO, "Ended working on getting email lists for :" + siteids[0] + " Processing time:" + ((System.currentTimeMillis() - startTime) / 1000.0) + " Email Indexes Present:" + clientIndexesHashmap.keySet().size());
            
            for (String email_list_name : clientIndexesHashmap.keySet()) {
                List email_client = (List<Client>) clientIndexesHashmap.get(email_list_name);
                Integer emailListId = 0;
                try {
                    EmailListService emailListService = SpringContextBridge.services().getEmailListService();
                    EmailList emailList = emailListService.getEmailListByCompanyIdAndEmailListName(companyId, email_list_name);
                    
                    if(emailList == null) {
                        AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                        addEmailListDetails.setCompanyId(companyId);
                        addEmailListDetails.setEmailListName(email_list_name);
                        addEmailListDetails.setEmailListType(EmailListTypeConstants.Mindbody.name());
                        emailListId = emailListService.save(addEmailListDetails);
                    } else {
                        emailListId = emailList.getEmailListId();
                    }
                    
                    
                    
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Mindbody email list processor", ex);
                }
                for (int i = 0; i < email_client.size(); i++) {
                    Client client = (Client) email_client.get(i);
                    String email_id = client.getEmail();
                    ContactsService contactsService = SpringContextBridge.services().getContactsService();
                    ContactDetails contactDetails = new ContactDetails();
                    contactDetails.setCompanyId(companyId);
                    contactDetails.setEmailAddress(email_id);
                    contactDetails.setFirstName(client.getFirstName());
                    contactDetails.setLastName(client.getLastName());
                    contactDetails.setEmailListId(emailListId);
                    contactsService.addContact(contactDetails);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Mindbody email list processor", e);
        }
    }

    public void processEachRow(Integer companyId, Integer companyLocation) throws ParseException {
        JSONArray json_email_array = new JSONArray();

        int[] siteids = new int[]{companyLocation};
        MindBodyClass mind_body_class = new MindBodyClass(siteids);
        HashMap<String, List<Client>> clientIndexesHashmap = new HashMap<String, List<Client>>();
        CompanyPreferencesService companyPreferencesService = SpringContextBridge.services().getCompanyPreferencesService();
        CompanyPreferences companyPreferences = companyPreferencesService.getByCompanyId(companyId);
        Map<String, String> unsubscribedEmailsMap = companyPreferencesService.getUnsubscribedEmailsMap(companyPreferences);

        try {
            long startTime = System.currentTimeMillis();
            logger.log(Level.INFO, "Started working on getting email lists for :" + siteids[0] + " - " + companyId);
            //String key is the Email List Name. And the value is the clients associated with it.
            try {
                clientIndexesHashmap = mind_body_class.getAllClientIndexes();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Mindbody email list processor", ex);
            }
            logger.log(Level.INFO, "Ended working on getting email lists for :" + siteids[0] + " Processing time:" + ((System.currentTimeMillis() - startTime) / 1000.0) + " Email Indexes Present:" + clientIndexesHashmap.keySet().size());
                //convert clientIndexesHashmap to JSONObject and update table
            //Email list name : [clientEmailAddresses].
            //iterates through every emaillist
            for (String email_list_name : clientIndexesHashmap.keySet()) {
                JSONObject savedMindBodyEmailList = getMindBodySavedEmailList(email_list_name, companyId);
                
                
                org.json.JSONObject json_email_object = new org.json.JSONObject();
                List email_client = (List<Client>) clientIndexesHashmap.get(email_list_name);
                org.json.JSONArray json_array_emailclient = new org.json.JSONArray();
                //iterates throught every email in emaillist
                for (int i = 0; i < email_client.size(); i++) {
                    Client client = (Client) email_client.get(i);
                    String email_id = client.getEmail();
                    EmailInfo email_info = checkIfExistingInSavedEmailList(savedMindBodyEmailList, client);

                    if (!Utility.checkIfUnsubscribed(email_id, unsubscribedEmailsMap)) {
                        json_array_emailclient.put(email_info.getEmailInfoJSONObject());
                    }
                }

                json_email_object.put(IConstants.kEmailListNameKey, MBEmailListName(email_list_name));
                json_email_object.put(IConstants.kEmailAddressesKey, json_array_emailclient);
                json_email_array.add(json_email_object);

            }

            if (json_email_array.size() >= 0) {
                updateUserPreferencesTable(companyId, json_email_array);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Mindbody email list processor", e);
        }
//        }
    }

    private void updateUserPreferencesTable(Integer companyId, JSONArray mindbodyEmails) throws Exception {
        EmailListService emailListService = SpringContextBridge.services().getEmailListService();
        Map<String, Object> map = new HashMap<>();
        map.put("update", "updateAllCompanyMindbodyListForStudioId");
        map.put(IConstants.kEmailListMindbodyKey, mindbodyEmails);
        emailListService.setEmailList(map, companyId);
    }
    
    private String MBEmailListName(String name) {
        return "Mindbody - " + name;
    }

    private JSONObject getMindBodySavedEmailList(String email_list_name, Integer companyId) throws Exception {
        EmailListService emailListService = SpringContextBridge.services().getEmailListService();

        String savedEmailListsString = emailListService.getEmailList("emailsForEmailList", companyId, MBEmailListName(email_list_name));
        JSONParser jsonParser = new JSONParser();
        if (!StringUtil.isEmpty(savedEmailListsString)) {
            JSONObject savedEmailJSONObject = (JSONObject) jsonParser.parse(savedEmailListsString);
            return savedEmailJSONObject;
        }
        return null;
    }

    private EmailInfo checkIfExistingInSavedEmailList(JSONObject savedMindBodyEmailList, Client client) {
        JSONArray emailAddressesJSONArray = (JSONArray) savedMindBodyEmailList.get(IConstants.kEmailMindbodyEmailAddresses);
        if (emailAddressesJSONArray != null && emailAddressesJSONArray.size() > 0) {
            for (Object emailAddressesObject : emailAddressesJSONArray) {
                JSONObject emailAddressJSONObject = (JSONObject) emailAddressesObject;
                EmailInfo savedEmailModel = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                if (StringUtil.isEqualIgnoreCase(savedEmailModel.getEmailAddress(), client.getEmail())) {
                    //This is for backward checking
                    if (StringUtil.isEmpty(savedEmailModel.getAddedDate())) {
                        savedEmailModel.setAddedDate(dateFormat.format(new Date()));
                    }
                    return savedEmailModel;
                }
            }
        }

        EmailInfo emailModel = new EmailInfo(client.getEmail(), client.getFirstName(), client.getLastName(), dateFormat.format(new Date()));
        return emailModel;
    }
    
}
