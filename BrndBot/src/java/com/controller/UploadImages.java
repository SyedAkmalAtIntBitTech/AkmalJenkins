
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbit.AppConstants;
import com.intbit.FileUploadUtil;
import com.intbit.util.ServletUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author Syed
 */
public class UploadImages extends BrndBotBaseHttpServlet {

    private static final Logger logger = Logger.getLogger(UploadImages.class.getName());

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            Integer user_id = (Integer) request.getSession().getAttribute("UID");
            String pathSuffix = "";
            String fileName = "";
            String link = "";
            String styleImage="";
           String imageURL=ServletUtil.getServerName(request.getServletContext());
            if(user_id != null){
                //User upload
                pathSuffix = AppConstants.USER_IMAGE_HOME + File.separator + user_id;
                fileName = FileUploadUtil.uploadFile(pathSuffix, request);
                getSqlMethodsInstance().AddImages(user_id, fileName);
                link = ""+imageURL+"DownloadImage?image_type=GALLERY&user_id=" + user_id + "&image_name=" + fileName;
                
            } else {
                //Admin upload for editor
                pathSuffix = AppConstants.ADMIN_LAYOUT_BACKGROUNDIMAGES_HOME + File.separator ;
                fileName = FileUploadUtil.uploadFile(pathSuffix, request);
                link = ""+imageURL+"DownloadImage?image_type=ADMIN_LAYOUT_BACKGROUNDIMAGES&user_id=null&image_name=" + fileName;
            }
            JSONObject json = new JSONObject();
            json.put("link", link);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            logger.info(json.toString());
            response.getWriter().write(json.toString());  
        } catch (Exception ex) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(ex, "Exception while updating org name:", getSqlMethodsInstance().error));
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