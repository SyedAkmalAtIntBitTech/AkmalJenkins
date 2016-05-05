/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbit.AppConstants;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.io.File;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import social.controller.PostToFacebook;
import social.controller.PostToTwitter;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/socialPost")
public class SocialPostController {

    private final static Logger logger = Logger.getLogger(SocialPostController.class);

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/postToFacebook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> postToFacebook(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            String accessToken = (String) requestBodyMap.get("accessToken");
            String title = (String) requestBodyMap.get("title");
            String file_image_path = (String) requestBodyMap.get("file_image_path");
            String posttext = (String) requestBodyMap.get("postText");
            String imagePostURL = (String) requestBodyMap.get("imagePostURL");
            String getImageFile = (String) requestBodyMap.get("imageToPost");
            String url = (String) requestBodyMap.get("url");
            String description = (String) requestBodyMap.get("description");
            String imageType = (String) requestBodyMap.get("imageType");
            String htmlString = (String) requestBodyMap.get("htmlString");
            String fileImagePath = getImageTypePrefix(imageType, companyId, getImageFile);
            String status = PostToFacebook.postStatus(title, fileImagePath, posttext, imagePostURL, getImageFile, url, description, imageType, companyId, htmlString,accessToken);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(status));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/postToTwitter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> postToTwitter(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();

            String text = (String) requestBodyMap.get("text");
            String shortURL = (String) requestBodyMap.get("shorturl");
            String fileImagePath = (String) requestBodyMap.get("imageToPost");
            String htmlString = (String) requestBodyMap.get("htmlString");
            String imageType = (String) requestBodyMap.get("imageType");
            String getImageFile = getImageTypePrefix(imageType, companyId, fileImagePath);
            

//            String fileImagePath = getImageTypePrefix(image_type, companyId, getImageFile);

            String status = PostToTwitter.postStatus(imageType, text, shortURL, fileImagePath, companyId, htmlString, getImageFile);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(status));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    private String getImageTypePrefix(String imageType, Integer companyId, String getImageFile) {
        String file_image_path = "";
        if (imageType.equals("layout")) {
            file_image_path = com.intbit.AppConstants.LAYOUT_IMAGES_HOME + File.separator + getImageFile;
        } else if (imageType.equalsIgnoreCase("gallery")) {
            file_image_path = com.intbit.AppConstants.USER_IMAGE_HOME + File.separator + companyId + File.separator + getImageFile;
        } else if (imageType.equals("url")) {
            file_image_path = getImageFile;
        }
        return file_image_path;
    }
}
