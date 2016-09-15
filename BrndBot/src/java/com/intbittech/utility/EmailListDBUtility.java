/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.controller.SocialPostScheduler;
import static com.controller.SocialPostScheduler.logger;
import com.controller.SqlMethods;
import com.intbittech.component.SpringContextBridge;
import com.intbittech.divtohtml.StringUtil;
import com.intbittech.enums.EmailListTypeConstants;
import com.intbittech.mindbody.MindBodyClass;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.EmailInfo;
import com.intbittech.model.EmailList;
import com.intbittech.modelmappers.AddEmailListDetails;
import com.intbittech.modelmappers.ContactDetails;
import com.intbittech.schedulers.MindbodyEmailListProcessor;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.ContactsService;
import com.intbittech.services.EmailListService;
import com.intbittech.services.UnsubscribedEmailsService;
import com.mindbodyonline.clients.api._0_5Client.Client;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ilyas
 */
public class EmailListDBUtility {

    public static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(EmailListDBUtility.class));

    public static void emailListJSONToTable() {
        logger.log(Level.INFO, "Started EmailListDBUtility @: " + new Date());
        Runnable myRunnable = new Runnable() {
            public void run() {
                synchronized (this) {
                    try {
                        wait(5000);
                        CompanyPreferencesService companyPreferencesService = SpringContextBridge.services().getCompanyPreferencesService();
                        List<CompanyPreferences> companyPreferencesList = companyPreferencesService.getAll();

                        for (CompanyPreferences companyPreferences : companyPreferencesList) {
                            processEachRow(companyPreferences);
                        }
                    } catch (Throwable throwable) {
                        logger.log(Level.SEVERE, throwable.getMessage());

                    }
                }
            }
        };
        Thread thread = new Thread(myRunnable);
        thread.start();
    }

    public static void processEachRow(CompanyPreferences companyPreferences) throws ParseException {
        try {
            logger.log(Level.INFO, "Processing CompanyId:" + companyPreferences.getFkCompanyId().getCompanyId());
            String emailListJSON = companyPreferences.getEmailList();
            Integer companyId = companyPreferences.getFkCompanyId().getCompanyId();
            String unsubscribeEMailsJSON = companyPreferences.getUnsubscribeEmails();
            JSONParser jsonParser = new JSONParser();

            if (!StringUtil.isEmpty(unsubscribeEMailsJSON)) {
                JSONObject savedEmailJSONObject = (JSONObject) jsonParser.parse(unsubscribeEMailsJSON);
                JSONArray unsubscribedEmailListJSONARRAY = (JSONArray) savedEmailJSONObject.get(IConstants.kUnsubscribeEmails);
                if (unsubscribedEmailListJSONARRAY != null && unsubscribedEmailListJSONARRAY.size() > 0) {
                    List<String> unsubscribedEmailList = new ArrayList<>();
                    for (int i = 0; i < unsubscribedEmailListJSONARRAY.size(); i++) {
                        unsubscribedEmailList.add((String) unsubscribedEmailListJSONARRAY.get(i));
                    }
                    UnsubscribedEmailsService unsubscribedEmailsService = SpringContextBridge.services().getUnsubscribedEmailsService();
                    unsubscribedEmailsService.save(companyId, unsubscribedEmailList);

                }
            }

            if (!StringUtil.isEmpty(emailListJSON)) {
                JSONObject savedEmailJSONObject = (JSONObject) jsonParser.parse(emailListJSON);
                JSONArray userEmailListJSONARRAY = (JSONArray) savedEmailJSONObject.get(IConstants.kEmailListUserKey);
                JSONArray mindBodyEmailListJSONARRAY = (JSONArray) savedEmailJSONObject.get(IConstants.kEmailListMindbodyKey);
                if (userEmailListJSONARRAY != null && userEmailListJSONARRAY.size() > 0) {
                    processEmailListAndContats(userEmailListJSONARRAY, companyId, EmailListTypeConstants.General.name());
                }
                if (mindBodyEmailListJSONARRAY != null && mindBodyEmailListJSONARRAY.size() > 0) {
                    processEmailListAndContats(mindBodyEmailListJSONARRAY, companyId, EmailListTypeConstants.Mindbody.name());
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Email list processor", e);
        }
    }

    public static void processEmailListAndContats(JSONArray emailListJSONARRAY, Integer companyId, String emailListType) throws java.text.ParseException {
        if (emailListJSONARRAY != null && emailListJSONARRAY.size() > 0) {
            for (Object emailListObject : emailListJSONARRAY) {
                JSONObject emailListJSONObject = (JSONObject) emailListObject;
                String emailListName = (String) emailListJSONObject.get(IConstants.kEmailListNameKey);
                String defaultFromName = (String) emailListJSONObject.get(IConstants.kEmailListDefaultFromName);
                String emailListAddedDateString = (String) emailListJSONObject.get(IConstants.kEmailListAddedDate);
                String emailListDescription = (String) emailListJSONObject.get(IConstants.kEmailListDescription);
                JSONArray emailAddressesJSONArray = (JSONArray) emailListJSONObject.get(IConstants.kEmailAddressesKey);
                if (!StringUtil.isEmpty(emailListName)) {
                    //Add emailListName
                    EmailListService emailListService = SpringContextBridge.services().getEmailListService();
                    EmailList emailList = emailListService.getEmailListByCompanyIdAndEmailListName(companyId, emailListName);
                    Integer emailListId = 0;
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    if (emailList == null) {
                        AddEmailListDetails addEmailListDetails = new AddEmailListDetails();
                        addEmailListDetails.setEmailListName(emailListName);
                        addEmailListDetails.setCompanyId(companyId);
                        if (!StringUtil.isEmpty(defaultFromName)) {
                            addEmailListDetails.setDefaultFromAddress(defaultFromName);
                        }
                        if (!StringUtil.isEmpty(emailListDescription)) {
                            addEmailListDetails.setEmailListDescription(emailListDescription);
                        }
                        addEmailListDetails.setEmailListType(emailListType);

                        if (!StringUtil.isEmpty(emailListAddedDateString)) {
                            Date emailListAddedDate = format.parse(emailListAddedDateString);
                            addEmailListDetails.setCreatedDate(emailListAddedDate);
                        }

                        emailListId = emailListService.save(addEmailListDetails);
                    } else {
                        emailListId = emailList.getEmailListId();
                    }

                    if (emailAddressesJSONArray != null && emailAddressesJSONArray.size() > 0) {
                        //save emailAddresses
                        for (Object emailAddressObject : emailAddressesJSONArray) {
                            ContactDetails contactDetails = new ContactDetails();
                            JSONObject emailAddressJSONObject = (JSONObject) emailAddressObject;
                            EmailInfo savedEmailModel = EmailInfo.fromJSON(emailAddressJSONObject.toString());
                            contactDetails.setEmailAddress(savedEmailModel.getEmailAddress());
                            contactDetails.setFirstName(savedEmailModel.getFirstName());
                            contactDetails.setLastName(savedEmailModel.getLastName());
                            Date contactAddedDate = format.parse(savedEmailModel.getAddedDate());
                            contactDetails.setAddedDate(contactAddedDate);
                            contactDetails.setEmailListId(emailListId);
                            contactDetails.setCompanyId(companyId);
                            ContactsService contactsService = SpringContextBridge.services().getContactsService();
                            contactsService.addContact(contactDetails);
                        }

                    }
                }

            }
        }
    }

}
