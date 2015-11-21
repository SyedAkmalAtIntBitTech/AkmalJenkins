/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.intbit.AppConstants;
import com.intbit.util.ServletUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        boolean face = false;
        boolean twit = false;
        try {
            getSqlMethodsInstance().session = request.getSession();
            Integer user_id = (Integer) getSqlMethodsInstance().session.getAttribute("UID");
            String htmlString = (String) getSqlMethodsInstance().session.getAttribute("htmlString");
            String isFacebook = request.getParameter("isFacebook");
            String isTwitter = request.getParameter("isTwitter");
            String getImageFile = request.getParameter("imageToPost");
            String getFile = request.getParameter("imagePost");
            String url = request.getParameter("url");

            String file_image_path = AppConstants.LAYOUT_IMAGES_HOME + File.separator + getImageFile;

//            String file_image_path = getServletContext().getRealPath("") + "/temp/"+getImageFile;
            String imagePostURL = ServletUtil.getServerName(request.getServletContext());
            //String imagePostURL = AppConstants.LAYOUT_IMAGES_HOME + getImageFile;
            if (isFacebook.equalsIgnoreCase("true")) {

                String accessToken = request.getParameter("accesstoken");
                String posttext = request.getParameter("postText");
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String url1 = request.getParameter("url");
                String returnMessage = PostToFacebook.postStatus(accessToken, title, file_image_path, posttext, imagePostURL, getImageFile, url1, description, user_id, htmlString);

            }
            if (isTwitter.equalsIgnoreCase("true")) {

                String twitterAccessToken = request.getParameter("twittweraccestoken");
                String twitterTokenSecret = request.getParameter("twitterTokenSecret");
                String text = request.getParameter("text");
                String shortURL = request.getParameter("shorturl");
                PrintWriter out1 = response.getWriter();
                String returnMessage = PostToTwitter.postStatus(twitterAccessToken, twitterTokenSecret, text, shortURL, file_image_path, user_id, htmlString, getImageFile);
                out1.println(returnMessage);

            }

        } catch (Exception e) {
            
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
