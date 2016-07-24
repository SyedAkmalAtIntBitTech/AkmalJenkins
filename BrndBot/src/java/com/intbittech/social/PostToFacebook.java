/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.social;

import com.controller.ApplicationContextListener;
import com.controller.SqlMethods;
import com.intbittech.AppConstants;
import com.intbittech.exception.ProcessFailed;
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
import javax.servlet.ServletContext;
import com.intbittech.utility.Utility;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Service;

/**
 *
 * @author AR
 */
@Service
public class PostToFacebook {

    private final static Logger logger = Logger.getLogger(PostToFacebook.class);

    public static String postStatus(String title, String fileImagePath, String posttext, String imagePostURL, String getImageFile, String url, String description, String imageType, Integer companyID, String htmlString, String accessToken) {
        String returnMessage = "success";
        String status = "";
        try {
            Facebook facebook = new FacebookFactory().getInstance();
            facebook.setOAuthAppId(AppConstants.facebookString1, AppConstants.facebookString2);
            facebook.setOAuthPermissions("publish_actions, publish_pages,manage_pages");
            facebook.setOAuthAccessToken(new AccessToken(accessToken));
            /* change the context path while uploading the war file */
            ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
            String context_real_path = servletContext.getRealPath("");
            context_real_path += "/static_resources";
            String imageContextPath = Utility.getServerName(context_real_path);
            logger.info("message while facebook post:" + imageContextPath);

            if (title.equals("")) {
                if (!imageType.isEmpty()) {
                    Media media;
                    if (imageType.equals("url")) {
                        media = new Media("xyz", new URL(fileImagePath).openStream());
                    } else {
                        media = new Media(new File(fileImagePath));
                    }
                    PhotoUpdate update = new PhotoUpdate(media);
                    update.message(posttext);
                    status = facebook.postPhoto(update);
                } else {
                    PostUpdate post = new PostUpdate(posttext);
                    status = facebook.postFeed(post);
                }

            } else {
                logger.info("title:" + title);
                if (!imageType.isEmpty()) {
                    if (imageType.equals("layout")) {
                        PostUpdate post = new PostUpdate(posttext)
                                .picture(new URL(imageContextPath + "DownloadImage?image_type=LAYOUT_IMAGES&image_name=" + getImageFile))
                                .name(title)
                                .link(new URL(url))
                                .description(description);
                        status = facebook.postFeed(post);

                    } else if (imageType.equals("gallery")) {
                        PostUpdate post = new PostUpdate(posttext)
                                .picture(new URL(imageContextPath + "downloadImage?imageType=GALLERY&imageName=" + getImageFile + "&companyId=" + companyID))
                                .name(title)
                                .link(new URL(url))
                                .description(description);
                        status = facebook.postFeed(post);
                    } else if (imageType.equals("url")) {
                        PostUpdate post = new PostUpdate(posttext)
                                .picture(new URL(getImageFile))
                                .name(title)
                                .link(new URL(url))
                                .description(description);
                        status = facebook.postFeed(post);
                    }
                    if (!(status.equals(""))) {
                        status = returnMessage;
                    }
                } else {
                    PostUpdate post = new PostUpdate(posttext)
                            .name(title)
                            .link(new URL(url))
                            .description(description);
                    status = facebook.postFeed(post);
                }

            }
            logger.info("message while facebook post:" + status);
            try {
                SqlMethods sqlMethods = new SqlMethods();
                sqlMethods.setSocialPostHistory(companyID, htmlString, false, true, imageType, getImageFile, null);
            } catch (Exception ex) {
                logger.error(ex);
            }

        } catch (FacebookException te) {
            te.printStackTrace();
            logger.error(te.getMessage());
            throw new ProcessFailed("Could post on facebook");
        } catch (Exception e) {
//            logger.log(Priority.ERROR ,"error message", e);
            e.printStackTrace();
            throw new ProcessFailed("Could post on facebook");
        }
        return returnMessage;
    }

    public static String postStatus(String title,
            String file_image_path, String posttext,
            String imagePostURL, String getImageFile,
            String url, String description, String imageType,
            Integer companyID, String htmlString) throws MalformedURLException, IOException, Throwable {
        return postStatus(title, file_image_path, posttext, imagePostURL, getImageFile, url, description, imageType, companyID, htmlString, getFacebookAccessToken(companyID));

    }

    private static HashMap<String, String> getFacebookCompanyPreferences(Integer companyId) throws Throwable {
        CompanyPreferencesFacebook companyPreferencesFacebook = new CompanyPreferencesFacebook();
        return companyPreferencesFacebook.getCompanyPreferenceForAccessToken(companyId);
    }

    private static String getFacebookAccessToken(Integer companyId) throws Throwable {
        HashMap<String, String> hashMap = getFacebookCompanyPreferences(companyId);
        if (hashMap != null) {
            return hashMap.get("fb_default_page_access_token");
        }
        return "";
    }

}
