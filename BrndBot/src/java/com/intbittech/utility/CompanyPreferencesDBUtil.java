/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.utility;

import com.intbittech.component.SpringContextBridge;
import com.intbittech.divtohtml.StringUtil;
import com.intbittech.enums.EmailListTypeConstants;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.modelmappers.CompanyPreferencesJson;
import com.intbittech.modelmappers.CompanyProfile;
import com.intbittech.modelmappers.EmailSettings;
import com.intbittech.modelmappers.FacebookDataDetails;
import com.intbittech.modelmappers.OnBoarding;
import com.intbittech.modelmappers.TwitterDataDetails;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.UnsubscribedEmailsService;
import static com.intbittech.utility.EmailListDBUtility.logger;
import static com.intbittech.utility.EmailListDBUtility.processEachRow;
import static com.intbittech.utility.EmailListDBUtility.processEmailListAndContacts;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sandeep
 */
public class CompanyPreferencesDBUtil {

    public static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(CompanyPreferencesDBUtil.class));

    public static void companyPreferenceListJSONToTable() {
        logger.log(Level.INFO, "Started CompanyPreferencesDBUtil @: " + new Date());
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
            String companyPreferenceJson = companyPreferences.getCompanyPreferences();
            JSONParser jsonParser = new JSONParser();
            if (!StringUtil.isEmpty(companyPreferenceJson)) {
                JSONObject companyPreferenceJSONObject = (JSONObject) jsonParser.parse(companyPreferenceJson);
                CompanyPreferencesJson companyPreferencesJson = new CompanyPreferencesJson();
                if (companyPreferenceJSONObject.containsKey("emailSettings")) {
                    EmailSettings emailSettings = new EmailSettings();
                    JSONObject emailSettingsJSONObject = (JSONObject) jsonParser.parse(companyPreferenceJSONObject.get("emailSettings").toString());
                    emailSettings.setFromAddress((String) emailSettingsJSONObject.get("from_address"));
                    emailSettings.setReplyEmailAddress((String) emailSettingsJSONObject.get("reply_email_address"));
                    emailSettings.setFromName((String) emailSettingsJSONObject.get("from_name"));
                    companyPreferencesJson.setEmailSettings(emailSettings);
                }
                if (companyPreferenceJSONObject.containsKey("userProfile")) {
                    CompanyProfile companyProfile = new CompanyProfile();
                    JSONObject companyProfileJSONObject = (JSONObject) jsonParser.parse(companyPreferenceJSONObject.get("userProfile").toString());
                    companyProfile.setAddress((String) companyProfileJSONObject.get("address"));
                    companyProfile.setFacebookUrl((String) companyProfileJSONObject.get("facebookUrl"));
                    companyProfile.setInstagramUrl((String) companyProfileJSONObject.get("instagramUrl"));
                    companyProfile.setTwitterUrl((String) companyProfileJSONObject.get("twitterUrl"));
                    companyProfile.setWebsiteUrl((String) companyProfileJSONObject.get("websiteUrl"));
                    companyPreferencesJson.setCompanyProfile(companyProfile);

                }
                if (companyPreferenceJSONObject.containsKey("colors")) {
                    List coloList = (List) companyPreferenceJSONObject.get("colors");
                    companyPreferencesJson.setColors(coloList);
                }
                if (companyPreferenceJSONObject.containsKey("Twitter")) {
                    TwitterDataDetails twitterDataDetails = new TwitterDataDetails();
                    JSONObject twitterDetailsJSONObject = (JSONObject) jsonParser.parse(companyPreferenceJSONObject.get("Twitter").toString());
                    twitterDataDetails.setTwitterAccessToken((String) twitterDetailsJSONObject.get("twitter_access_token"));
                    twitterDataDetails.setTwitterAccessTokenSecret((String) twitterDetailsJSONObject.get("twitter_access_token_secret"));
                    twitterDataDetails.setTwitterLoggedIn((String) twitterDetailsJSONObject.get("TwitterLoggedIn"));
                    twitterDataDetails.setTwitterUserName((String) twitterDetailsJSONObject.get("twitter_user_name"));
                    companyPreferencesJson.setTwitter(twitterDataDetails);
                }
                if (companyPreferenceJSONObject.containsKey("Facebook")) {
                    FacebookDataDetails facebookDataDetails = new FacebookDataDetails();
                    JSONObject facebookDetailsJSONObject = (JSONObject) jsonParser.parse(companyPreferenceJSONObject.get("Facebook").toString());
                    facebookDataDetails.setFbUserProfileName((String) facebookDetailsJSONObject.get("fb_user_profile_name"));
                    facebookDataDetails.setFbDefaultPageAccessToken((String) facebookDetailsJSONObject.get("fb_default_page_access_token"));
                    facebookDataDetails.setFacebookLoggedIn((String) facebookDetailsJSONObject.get("FacebookLoggedIn"));
                    facebookDataDetails.setFbDefaultPageName((String) facebookDetailsJSONObject.get("fb_default_page_name"));
                    companyPreferencesJson.setFacebook(facebookDataDetails);
                }
                if (companyPreferenceJSONObject.containsKey("onBoarding")) {
                    OnBoarding onBoarding = new OnBoarding();
                    JSONObject onBoardingDetailsJSONObject = (JSONObject) jsonParser.parse(companyPreferenceJSONObject.get("onBoarding").toString());
                    onBoarding.setColorPallete((String) onBoardingDetailsJSONObject.get("ColorPallete"));
                    onBoarding.setCompanyName((String) onBoardingDetailsJSONObject.get("CompanyName"));
                    onBoarding.setExternalSources((String) onBoardingDetailsJSONObject.get("ExternalSources"));
                    onBoarding.setLogo((String) onBoardingDetailsJSONObject.get("Logo"));
                    onBoarding.setTotal((String) onBoardingDetailsJSONObject.get("Total"));
                    companyPreferencesJson.setOnBoarding(onBoarding);

                } else {
                    OnBoarding onBoarding = new OnBoarding();
                    onBoarding.setColorPallete("true");
                    onBoarding.setCompanyName("true");
                    onBoarding.setExternalSources("true");
                    onBoarding.setLogo("true");
                    onBoarding.setTotal("true");
                    companyPreferencesJson.setOnBoarding(onBoarding);
                }
                CompanyPreferencesService companyPreferencesService = SpringContextBridge.services().getCompanyPreferencesService();
                companyPreferencesService.setCompanyPreferencesJson(companyPreferencesJson,companyPreferences);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Email list processor", e);
        }
    }

}
