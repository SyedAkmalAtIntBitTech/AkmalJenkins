/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.SqlMethods;
import com.intbit.marketing.model.TblUserPreferences;
import com.intbit.marketing.service.UserPreferencesService;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Media;
import facebook4j.PhotoUpdate;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AR
 */
class PostToFacebook {
    @Autowired 
    public UserPreferencesService userPreferencesService;

    public static String postStatus(String accessToken, String title, String file_image_path, String posttext, String imagePostURL, String getImageFile, String url, String description, Integer user_id, String htmlString) throws MalformedURLException {

        String returnMessage = "success";
        try {
            Facebook facebook = new FacebookFactory().getInstance();
            facebook.setOAuthAppId("592852577521569", "a87cc0c30d792fa5dd0aaef6b43994ef");
            facebook.setOAuthPermissions("publish_actions, publish_pages,manage_pages");
            facebook.setOAuthAccessToken(new AccessToken(accessToken));

            if (title == "") {

                Media media = new Media(new File(file_image_path));
                PhotoUpdate update = new PhotoUpdate(media);
                update.message(posttext);
                facebook.postPhoto(update);
            } else {
                logger.info(title);
                PostUpdate post = new PostUpdate(posttext)
                        .picture(new URL(imagePostURL + "DownloadImage?image_type=LAYOUT_IMAGES&image_name=" + getImageFile))
                        .name(title)
                        .link(new URL(url))
                        .description(description);
                facebook.postFeed(post);
            }
            try {
                SqlMethods sqlMethods = new SqlMethods();
                sqlMethods.setSocialPostHistory(user_id, htmlString, false, true, getImageFile, null);
            } catch (Exception ex) {
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getCause());
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }

        } catch (FacebookException e) {
            returnMessage = "FB Exception:" + e.getMessage();
            Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, e.getCause());
            Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return returnMessage;
    }
    public  HashMap<String,String> getFacebookUserPreferences(Integer userId) throws Throwable{
        TblUserPreferences userPreferences = userPreferencesService.getById(userId);
        String userPreferencesJson = userPreferences.getUserPreferences();
        JSONObject jsonObject = (JSONObject)new JSONParser().parse(userPreferencesJson);
        HashMap<String,String> hashUserPerfernce = new HashMap<String,String>();
        hashUserPerfernce.put("fb_default_page_access_token", jsonObject.get("fb_default_page_access_token").toString());
        hashUserPerfernce.put("FacebookLoggedIn", jsonObject.get("FacebookLoggedIn").toString());
        hashUserPerfernce.put("fb_default_page_name", jsonObject.get("fb_default_page_name").toString());
       return hashUserPerfernce;
    }
    public  String getFacebookAccessToken(Integer userId) throws Throwable{
        HashMap<String , String> hashMap = getFacebookUserPreferences(userId);
        if(hashMap != null){
            return hashMap.get("fb_default_page_access_token");
        }
        return "";
    }
   
}