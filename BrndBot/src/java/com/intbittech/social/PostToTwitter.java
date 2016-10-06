/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.social;

import com.controller.SqlMethods;
import com.intbittech.controller.SignupController;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.modelmappers.TwitterDataDetails;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.CompanyService;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class PostToTwitter {

    @Autowired
    private CompanyPreferencesService companyPreferencesService;
    @Autowired
    CompanyService companyService;

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SignupController.class);

    public String postStatus(String image_type, String text, String shortURL, String fileImagePath, Integer companyId, String htmlString, String getImageFile) throws Throwable {
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
    public String getTwitterAccessToken(Integer companyId) throws Throwable {
        Company company = companyService.getCompanyById(companyId);
        TwitterDataDetails twitterDataDetails = companyPreferencesService.getTwitterDetails(company);
        return twitterDataDetails.getTwitterAccessToken();
    }

    public String getTwitterAccessTokenSecret(Integer companyId) throws Throwable {
        Company company = companyService.getCompanyById(companyId);
        TwitterDataDetails twitterDataDetails =companyPreferencesService.getTwitterDetails(company);
        return twitterDataDetails.getTwitterAccessTokenSecret();
    }

}
