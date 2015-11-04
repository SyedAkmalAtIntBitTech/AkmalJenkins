/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.SqlMethods;
import com.intbit.marketing.model.TblUserPreferences;
import com.intbit.marketing.service.UserPreferencesService;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author AR
 */
public class PostToTwitter {
     @Autowired 
    public UserPreferencesService userPreferencesService;

    public static String postStatus(String twitterAccessToken, String twitterTokenSecret, String text, String shortURL, String fileImagePath, Integer userId, String htmlString, String getImageFile) {
        String returnMessage = "";
        try {

            String shortUrl = "";
            ConfigurationBuilder twitterConfigBuilder = new ConfigurationBuilder();
            twitterConfigBuilder.setDebugEnabled(true);
            twitterConfigBuilder.setOAuthConsumerKey("K7TJ3va8cyAeh6oN3Hia91S2o");
            twitterConfigBuilder.setOAuthConsumerSecret("IWUt2aDVTHgUc8N0qI0cF1Z1dTAEQ7CSgnBymZNr3BPSmtkNHL");
            twitterConfigBuilder.setOAuthAccessToken(twitterAccessToken);
            twitterConfigBuilder.setOAuthAccessTokenSecret(twitterTokenSecret);

            Twitter twitter = new TwitterFactory(twitterConfigBuilder.build()).getInstance();
            String statusMessage = text.replace("bit.ly/1XOkJo", "");
            shortUrl = shortURL;
            if (shortUrl.length() > 0) {
                String StatusMessageWithoutUrl = statusMessage.substring(0, statusMessage.length());
                if (StatusMessageWithoutUrl.length() + shortUrl.length() < 140) {
                    statusMessage = StatusMessageWithoutUrl + " " + shortUrl;
                } else {
                    int urlLength = shortUrl.length() + 1;
                    int statusLength = 115 - urlLength;
                    statusMessage = StatusMessageWithoutUrl.substring(0, statusLength);
                    statusMessage = statusMessage + " " + shortUrl;
                }
            }
            File file = new File(fileImagePath);
            int count = statusMessage.length();
            StatusUpdate status = new StatusUpdate(statusMessage);
            // set the image to be uploaded here.

            status.setMedia(file);
            twitter.updateStatus(status);
            try {
                SqlMethods sql_methods = new SqlMethods();
                sql_methods.setSocialPostHistory(userId, htmlString, false, true, getImageFile, null);
            } catch (Exception ex) {
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getCause());
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
            returnMessage = "success";

        } catch (TwitterException te) {
            
            returnMessage = "Twitter Exception: " + te.getMessage();
            Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, te.getCause());
            Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, te.getMessage());

        } catch (Exception e) {
            returnMessage = "Twitter Exception: " + e.getMessage();
            Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return returnMessage;
    }
    public  HashMap<String,String>getTwitterUserPreferences(Integer userId) throws Throwable{

    TblUserPreferences userPreferences = userPreferencesService.getById(userId);
        String userPreferencesJson = userPreferences.getUserPreferences();
        JSONObject jsonObject = (JSONObject)new JSONParser().parse(userPreferencesJson);
        HashMap<String,String> hashUserPerfernce = new HashMap<String,String>();
        hashUserPerfernce.put("twitter_access_token", jsonObject.get("twitter_access_token").toString());
        hashUserPerfernce.put("twitter_access_token_secret", jsonObject.get("twitter_access_token_secret").toString());
        hashUserPerfernce.put("TwitterLoggedIn", jsonObject.get("TwitterLoggedIn").toString());
        hashUserPerfernce.put("twitter_user_name", jsonObject.get("twitter_user_name").toString());
       return hashUserPerfernce;
    }
    public  String getTwitterAccessToken(Integer userId) throws Throwable{
       HashMap<String , String> hashMap= getTwitterUserPreferences(userId);
        if(hashMap != null){
            return hashMap.get("twitter_access_token");
        }
        return "";
    }
    public  String getTwitterAccessTokenSecret(Integer userId) throws Throwable{
         HashMap<String , String> hashMap= getTwitterUserPreferences(userId);
         if(hashMap != null){
             return hashMap.get("twitter_access_token_secret");
         }
         return "";
         
    }

}
