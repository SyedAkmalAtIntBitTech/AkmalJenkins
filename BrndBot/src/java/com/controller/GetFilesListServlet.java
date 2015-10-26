/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.ImageType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Mohamed
 */
public class GetFilesListServlet extends BrndBotBaseHttpServlet {

    private final Gson gson = new Gson();

    /**
     * Process the request for downloading image. This requires two required
     * parameters- image_type and image_name
     *
     * If image_type = GALLERY then we would need the user_id as well.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String imageTypeStr = request.getParameter("image_type");
        try {
            ImageType imageTypeEnum = ImageType.valueOf(imageTypeStr);
            String imageBasePath = "";
            String userId = "";
            switch (imageTypeEnum) {
                case GALLERY:
                    userId = request.getParameter("user_id");
                    if (userId == null || "".equals("user_id")) {
                        Map<String, String> responseMap = new HashMap<>();
                        responseMap.put("error", "user_id is required to view Gallery Images");
                        response.getWriter().write(new Gson().toJson(responseMap));
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                    imageBasePath = AppConstants.USER_IMAGE_HOME + File.separator + userId;
                    break;
                case LOOKS:
                    imageBasePath = AppConstants.LOOK_IMAGES_HOME;
                    break;
                case BRAND_PERSONALITY:
                    imageBasePath = AppConstants.BRAND_IMAGES_HOME;
                    break;
                case ORG_CATEGORIES:
                    String orgId = request.getParameter("org_id");
                    if (orgId == null || "".equals(orgId)) {
                        Map<String, String> responseMap = new HashMap<>();
                        responseMap.put("error", "org_id is required to view Category Images");
                        response.getWriter().write(new Gson().toJson(responseMap));
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                    imageBasePath = AppConstants.ORG_CATEGORIES_HOME + File.separator + orgId;
                    break;
                case LAYOUT_IMAGES:
                    imageBasePath = AppConstants.LAYOUT_IMAGES_HOME;
                    break;
                case HTML_IMAGES:
                    imageBasePath = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH;
                    break;
                case ADMIN_LAYOUT_BACKGROUNDIMAGES:
                    imageBasePath = AppConstants.ADMIN_LAYOUT_BACKGROUNDIMAGES_HOME;
                    break;
                case USER_LOGO:
                    userId = request.getParameter("user_id");
                    if (userId == null || "".equals("user_id")) {
                        Map<String, String> responseMap = new HashMap<>();
                        responseMap.put("error", "user_id is required to view Gallery Images");
                        response.getWriter().write(new Gson().toJson(responseMap));
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                    imageBasePath = AppConstants.USER_LOGO + File.separator + userId + File.separator + "logo";
                    break;
                case PDF_FILES_PATH:  
                    imageBasePath = AppConstants.PDF_FILES_PATH;
                    break;
            }
            
            File imageBaseFolder = new File(imageBasePath);
            JSONArray imageurlJsonArray=new JSONArray();
            for(File imageFile:imageBaseFolder.listFiles()){
               
                String imageFilePath = "/BrndBot/DownloadImage?image_type="+imageTypeEnum+"&user_id="+userId+"&image_name="+imageFile.getName();
                JSONObject imagejsonObject= new JSONObject();
                imagejsonObject.put("url", imageFilePath);
                imagejsonObject.put("thumb", imageFilePath);
                imageurlJsonArray.add(imagejsonObject);
            }
        logger.info("image url array s"+imageurlJsonArray.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(new Gson().toJson(imageurlJsonArray));

        } catch (IllegalArgumentException ex) {
            logger.log(Level.SEVERE, util.Utility.logMessage(ex, "Invalid Image Type:", getSqlMethodsInstance().error));

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "Invalid Image Type. Supported Image types are: "
                    + Arrays.asList(ImageType.values()).stream().map(type -> type.toString()).collect(Collectors.joining(", ")));
            response.getWriter().write(new Gson().toJson(responseMap));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, util.Utility.logMessage(ex, "Major Exception", getSqlMethodsInstance().error));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
