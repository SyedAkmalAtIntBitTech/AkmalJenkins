/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.SqlMethods;
import com.intbittech.AppConstants;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.utility.FileUploadUtil;
import com.intbittech.utility.ServletUtil;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sandeep
 */
@Controller
public class UploadImageController extends BrndBotBaseHttpServlet {

    @RequestMapping(value = "/UploadByAdmin", method = RequestMethod.POST)
    public void UploadImageByAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pathSuffix = "";
            String fileName = "";
            String link = "";
            String imageURL = ServletUtil.getServerName(request.getServletContext());
            //Admin upload for editor
            pathSuffix = AppConstants.BASE_ADMIN_GLOBAL_IMAGE_UPLOAD_PATH;
            fileName = FileUploadUtil.uploadFile(pathSuffix, request);
            link = "" + imageURL + "downloadImage?imageType=GLOBAL_IMAGE&companyId=0&imageName=" + fileName;
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

    @RequestMapping(value = "/UploadByUser", method = RequestMethod.POST)
    public void UploadImageByUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
 
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);

            String pathSuffix = "";
            String fileName = "";
            String link = "";
            String imageURL = ServletUtil.getServerName(request.getServletContext());
            if (userCompanyIds.getCompanyId() != null) {
               SqlMethods sql_methods = new SqlMethods();
                pathSuffix = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + userCompanyIds.getCompanyId() + File.separator + AppConstants.GALLERY_FOLDERNAME;
                fileName = FileUploadUtil.uploadFile(pathSuffix, request);
                sql_methods.AddImages(userCompanyIds.getCompanyId(), imageURL);
                link = "" + imageURL + "downloadImage?imageType=GALLERY&companyId=" + userCompanyIds.getCompanyId() + "&imageName=" + fileName;
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
}
