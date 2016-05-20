/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.social;

import com.controller.SqlMethods;
import com.intbittech.controller.SignupController;
import com.intbittech.exception.ProcessFailed;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author AR
 */
@Service
public class PostToTwitter {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SignupController.class);

    public static String postStatus(String image_type, String text, String shortURL, String fileImagePath, Integer companyId, String htmlString, String getImageFile) throws Throwable {
        String returnMessage = "";
        try {
            ConfigurationBuilder twitterConfigBuilder = new ConfigurationBuilder();
            twitterConfigBuilder.setDebugEnabled(true);
            twitterConfigBuilder.setOAuthConsumerKey("K7TJ3va8cyAeh6oN3Hia91S2o");
            twitterConfigBuilder.setOAuthConsumerSecret("IWUt2aDVTHgUc8N0qI0cF1Z1dTAEQ7CSgnBymZNr3BPSmtkNHL");
            twitterConfigBuilder.setOAuthAccessToken(getTwitterAccessToken(companyId));
            twitterConfigBuilder.setOAuthAccessTokenSecret(getTwitterAccessTokenSecret(companyId));

            Twitter twitter = new TwitterFactory(twitterConfigBuilder.build()).getInstance();
            String statusMessage = text;
            if (shortURL != null) {
                 statusMessage = statusMessage + " " + shortURL;
            }
            StatusUpdate status = new StatusUpdate(statusMessage);
            if (!image_type.isEmpty()) {
                if (image_type.equals("url")) {
                    status.setMedia("xyz", new URL(fileImagePath).openStream());
                } else {
                    File file = new File(getImageFile);

                    // set the image to be uploaded here.
                    status.setMedia(file);
                }
            }
            twitter.updateStatus(status);

            try {
                SqlMethods sql_methods = new SqlMethods();
                sql_methods.setSocialPostHistory(companyId, htmlString, false, true, image_type, fileImagePath, null);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                throw new ProcessFailed("Could not insert record for a twitter post");
            }
            returnMessage = "success";

        } catch (TwitterException te) {
            logger.error(te.getMessage());
            throw new ProcessFailed("Could post on twitter");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ProcessFailed("Could post on twitter");
        }
        return returnMessage;
    }

    public static HashMap<String, String> getTwitterCompanyPreferences(Integer companyId) throws Throwable {
        CompanyPreferencesTwitter companyPreferencesTwitter = new CompanyPreferencesTwitter();
        return companyPreferencesTwitter.getCompanyPreferenceForAccessToken(companyId);
    }

    public static String getTwitterAccessToken(Integer companyId) throws Throwable {
        HashMap<String, String> hashMap = getTwitterCompanyPreferences(companyId);
        if (hashMap != null) {
            return hashMap.get("twitter_access_token");
        }
        return "";
    }

    public static String getTwitterAccessTokenSecret(Integer companyId) throws Throwable {
        HashMap<String, String> hashMap = getTwitterCompanyPreferences(companyId);
        if (hashMap != null) {
            return hashMap.get("twitter_access_token_secret");
        }
        return "";

    }

}
