/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.ApplicationContextListener;
import static com.controller.BrndBotBaseHttpServlet.logger;
import com.controller.SqlMethods;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Media;
import facebook4j.PhotoUpdate;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import util.Utility;

/**
 *
 * @author AR
 */
public class PostToFacebook {
    public static String path = "";

    public static String postStatus(String accessToken, String title, 
            String file_image_path, String posttext, 
            String imagePostURL, String getImageFile, 
            String url, String description, String imageType,
            Integer user_id, String htmlString) throws MalformedURLException, IOException {

        String returnMessage = "success";
        ServletContext context = null;
        String status = "";
        try {
            Facebook facebook = new FacebookFactory().getInstance();
            facebook.setOAuthAppId("592852577521569", "a87cc0c30d792fa5dd0aaef6b43994ef");
            facebook.setOAuthPermissions("publish_actions, publish_pages,manage_pages");
            facebook.setOAuthAccessToken(new AccessToken(accessToken));
            /* change the context path while uploading the war file */
            ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
            String context_real_path = servletContext.getRealPath("");
            String imageContextPath = Utility.getServerName(context_real_path);
            Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, "message while facebook post:"+imageContextPath);
            
            if (title.equals("")) {

                Media media;
                if (imageType.equals("url")){
                    media = new Media("xyz",new URL(file_image_path).openStream());
                }
                else{
                    media = new Media(new File(file_image_path));
                }
                PhotoUpdate update = new PhotoUpdate(media);
                update.message(posttext);
                status = facebook.postPhoto(update);
            } else {
                logger.info("title:"+title);
                if (imageType.equals("layout")){
                    PostUpdate post = new PostUpdate(posttext)
                            .picture(new URL(imageContextPath + "DownloadImage?image_type=LAYOUT_IMAGES&image_name="+getImageFile))
                            .name(title)
                            .link(new URL(url))
                            .description(description);
                status = facebook.postFeed(post);
                    
                }else if (imageType.equals("gallery")){
                    PostUpdate post = new PostUpdate(posttext)
                            .picture(new URL(imageContextPath + "DownloadImage?image_type=GALLERY&image_name="+getImageFile+"&user_id="+user_id))
                            .name(title)
                            .link(new URL(url))
                            .description(description);
                status = facebook.postFeed(post);
                }else if (imageType.equals("url")){
                    PostUpdate post = new PostUpdate(posttext)
                            .picture(new URL(getImageFile))
                            .name(title)
                            .link(new URL(url))
                            .description(description);
                status = facebook.postFeed(post);
                }
                if (!(status.equals(""))){
                    status = returnMessage;
                }
            }
            Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, "message while facebook post:"+status);
            try {
                SqlMethods sqlMethods = new SqlMethods();
                sqlMethods.setSocialPostHistory(user_id, htmlString, false, true,imageType, getImageFile, null);
            } catch (Exception ex) {
                Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, null, ex.getCause());
                Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }

        } catch (FacebookException e) {
            returnMessage = "FB Exception:" + e.getMessage();
            Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, null, e.getCause());
            Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, null, e.getMessage());
        } catch (Exception ex){
            returnMessage = "Exception:" + ex.getMessage();
            System.out.println(ex);
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, null, ex.getStackTrace());
            Logger.getLogger(PostToFacebook.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return returnMessage;
    }
    public  HashMap<String,String> getFacebookCompanyPreferences(Integer companyId) throws Throwable{
        CompanyPreferencesFacebook companyPreferencesFacebook = new CompanyPreferencesFacebook();
        return companyPreferencesFacebook.getCompanyPreferenceForAccessToken(companyId);
    }
    public  String getFacebookAccessToken(Integer companyId) throws Throwable{
        HashMap<String , String> hashMap = getFacebookCompanyPreferences(companyId);
        if(hashMap != null){
            return hashMap.get("fb_default_page_access_token");
        }
        return "";
    }
   
}
