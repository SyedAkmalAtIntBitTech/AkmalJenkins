/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.FileUploadUtil;
import com.intbit.util.ServletUtil;
import com.intbittech.model.UserProfile;
import com.intbittech.utility.UserSessionUtil;
import java.io.File;
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
            pathSuffix = AppConstants.ADMIN_LAYOUT_BACKGROUNDIMAGES_HOME + File.separator;
            fileName = FileUploadUtil.uploadFile(pathSuffix, request);
            link = "" + imageURL + "downloadImage?imageType=ADMIN_LAYOUT_BACKGROUNDIMAGES&user_id=null&imageName=" + fileName;
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
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            String pathSuffix = "";
            String fileName = "";
            String link = "";
            String imageURL = ServletUtil.getServerName(request.getServletContext());
            if (companyId != null) {
               SqlMethods sql_methods = new SqlMethods();
                pathSuffix = AppConstants.USER_IMAGE_HOME + File.separator + companyId;
                fileName = FileUploadUtil.uploadFile(pathSuffix, request);
                sql_methods.AddImages(companyId, imageURL);
                link = "" + imageURL + "downloadImage?imageType=GALLERY&companyId=" + companyId + "&imageName=" + fileName;
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
