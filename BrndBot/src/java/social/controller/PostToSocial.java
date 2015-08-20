/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.intbit.AppConstants;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author sandeep-kumar
 */
public class PostToSocial extends BrndBotBaseHttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Facebook facebook;

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        boolean face = false;
        boolean twit = false;
        try {
            getSqlMethodsInstance().session = request.getSession();
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            String htmlString = (String)getSqlMethodsInstance().session.getAttribute("htmlString");
            String isFacebook = request.getParameter("isFacebook");
            String isTwitter = request.getParameter("isTwitter");
            String getImageFile = request.getParameter("imageToPost");
            String getFile = request.getParameter("imagePost");
            String file_image_path = AppConstants.LAYOUT_IMAGES_HOME + getImageFile;
//            String file_image_path = getServletContext().getRealPath("") + "/temp/"+getImageFile;
            String imagePostURL=request.getRequestURL().toString().replace("PostToSocial", "");
            //String imagePostURL = AppConstants.LAYOUT_IMAGES_HOME + getImageFile;
        if (isFacebook.equalsIgnoreCase("true")) {
            
            String accessToken = request.getParameter("accesstoken");
            String posttext = request.getParameter("postText");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String url = request.getParameter("url");
            
            facebook = new FacebookFactory().getInstance();
            facebook.setOAuthAppId("213240565487592", "823a21d2cc734a2de158daf9d57650e8");
            facebook.setOAuthPermissions("publish_actions, publish_pages,manage_pages");
            facebook.setOAuthAccessToken(new AccessToken(accessToken));
            if (title == "") {
                PostUpdate post = new PostUpdate(posttext)
                        .picture(new URL(imagePostURL + "BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name="+getImageFile));
                facebook.postFeed(post);
            } else {
                PostUpdate post = new PostUpdate(posttext)
                        .picture(new URL(imagePostURL + "BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name="+getImageFile))
                        .name(title)
                        .link(new URL(url))
                        .description(description);
                facebook.postFeed(post);
            }
            try {
                
            getSqlMethodsInstance().setSocialPostHistory(user_id, htmlString, false, true, getImageFile);
            }catch (Exception ex){
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getCause());
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
        }
        if (isTwitter.equalsIgnoreCase("true")) {

            try {

                twitter4j.auth.AccessToken accTok = null;

                ConfigurationBuilder twitterConfigBuilder = new ConfigurationBuilder();
                twitterConfigBuilder.setDebugEnabled(true);
                twitterConfigBuilder.setOAuthConsumerKey("V9n3gzTRP7EwcRqW4OZAZS13l");
                twitterConfigBuilder.setOAuthConsumerSecret("7qnu29n7vLRPIFmkPI14yD3EhyYHUJzhe39dfHBnF67KAgDHgV");
                twitterConfigBuilder.setOAuthAccessToken(request.getParameter("twittweraccestoken"));
                twitterConfigBuilder.setOAuthAccessTokenSecret(request.getParameter("twitterTokenSecret"));

                Twitter twitter = new TwitterFactory(twitterConfigBuilder.build()).getInstance();
                String statusMessage = request.getParameter("text");
//                String file_image_path = getServletContext().getRealPath("") + "/images/Blackandwhite.jpg";
                File file = new File(file_image_path);

                StatusUpdate status = new StatusUpdate(statusMessage);
                status.setMedia(file); // set the image to be uploaded here.
                twitter.updateStatus(status);
                try {
                    getSqlMethodsInstance().setSocialPostHistory(user_id, htmlString, false, true, getImageFile);
                    }catch (Exception ex){
                        Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getCause());
                        Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                    }

            } catch (TwitterException te) {

                PrintWriter out1 = response.getWriter();
                out1.println("Twitter Exception: " + te.getMessage());
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, te.getCause());
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, te.getMessage());
            
            } catch (Exception e) {
                PrintWriter out1 = response.getWriter();
                out1.println("Exception: " + e.getMessage());
            }

        }
        } catch (FacebookException e) {
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, e.getCause());
                Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
