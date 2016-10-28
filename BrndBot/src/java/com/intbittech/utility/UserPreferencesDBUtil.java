/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.intbittech.component.SpringContextBridge;
import com.intbittech.divtohtml.StringUtil;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.UserPreferencesJson;
import com.intbittech.services.UserPreferencesService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sandeep
 */
public class UserPreferencesDBUtil {

    public static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(CompanyPreferencesDBUtil.class));

    public static void usersListJSONToTable() {
        logger.log(Level.INFO, "Started userPreferencesDBUtil @: " + new Date());
        Runnable myRunnable = new Runnable() {
            public void run() {
                synchronized (this) {
                    try {
                        wait(5000);
                        UserPreferencesService userPreferencesService = SpringContextBridge.services().getUserPreferencesService();
                        List<Users> usersList = userPreferencesService.getAll();

                        for (Users users : usersList) {
                            processEachRow(users);
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

    public static void processEachRow(Users users) throws ParseException {
        try {
            String userPreferenceJson = users.getUserPreferences();
            JSONParser jsonParser = new JSONParser();
            UserPreferencesJson userPreferencesJson = new UserPreferencesJson();
            if (!StringUtil.isEmpty(userPreferenceJson)) {
                JSONObject userPreferenceJSONObject = (JSONObject) jsonParser.parse(userPreferenceJson);
                if (userPreferenceJSONObject.containsKey("profileColor")) {
                    userPreferencesJson.setProfileColor(userPreferenceJSONObject.get("profileColor").toString());
                }

            } else {
                userPreferencesJson.setProfileColor(Utility.getRandomColor());
            }
            UserPreferencesService userPreferencesService = SpringContextBridge.services().getUserPreferencesService();
            userPreferencesService.setUserPreferencesJson(userPreferencesJson,users);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Email list processor", e);
        }
    }

}
