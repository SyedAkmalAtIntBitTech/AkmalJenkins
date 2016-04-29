/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.IConstants;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.util.CustomStyles;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.UserProfile;
import com.intbittech.modelmappers.CompanyColorsDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyPreferencesFacebookService;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.StringUtility;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sandeep
 */
@Controller
@RequestMapping(value = "/settings")
public class SettingsController extends BrndBotBaseHttpServlet {

    private final static Logger logger = Logger.getLogger(SettingsController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CompanyPreferencesService companyPreferencesService;

    @Autowired
    private CompanyPreferencesFacebookService companyPreferencesFacebookService;
    
//    @Autowired
//    private CompanyPreferencesTwitterService companyPreferencesTwitterService;

    @RequestMapping(value = "/getColors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColors(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();

        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            List<String> colorArray = companyPreferencesService.getColors(company);
            genericResponse.setDetails(colorArray);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/setColors", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> setColors(@RequestBody CompanyColorsDetails companyColorsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            companyPreferencesService.setColors(companyColorsDetails, company);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyCategories_color_update", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/saveEmailSettings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveEmailSettings(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String from_address = (String) requestBodyMap.get("from_address");
            String reply_email_address = (String) requestBodyMap.get("reply_email_address");
            JSONObject json_object = new JSONObject();
            json_object.put(IConstants.kEmailFromAddress, from_address);
            json_object.put(IConstants.kEmailReplyAddress, reply_email_address);
            companyPreferencesService.updateEmailSettings(json_object, company);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getEmailSettings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getEmailSettings() {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            JSONObject jsonObject = companyPreferencesService.getEmailSettings(company);
            genericResponse.addDetail(new Gson().toJson(jsonObject));
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getAllPreferences", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllPreferences() {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            CompanyPreferences companyPreferences = companyPreferencesService.getByCompany(company);
            genericResponse.addDetail(companyPreferences.getCompanyPreferences());
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getGlobalAndUserColors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getGlobalAndUserColors(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            List<String> colorArray = companyPreferencesService.getColors(company);
            JSONArray adminColors = getCustomColorFromFile(request);
            adminColors.stream().forEach((adminColor) -> {
                colorArray.add(adminColor.toString());
            });
            genericResponse.setDetails(colorArray);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    private JSONArray getCustomColorFromFile(HttpServletRequest request) {
        JSONObject colorFromFile = CustomStyles.getCustomColorsJson(request);
        return (JSONArray) colorFromFile.get("admincustomcolors");
    }

    //TODO Ilyas to check the path
    @RequestMapping(value = "/changeLogo", method = RequestMethod.POST)
    public ResponseEntity<ContainerResponse> changeLogo(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            String filePath = null;
            String fileName = null, fieldName = null, uploadType = null;

            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            String uploadPath = AppConstants.USER_LOGO;

            // Verify the content type
            String contentType = request.getContentType();

            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File(AppConstants.TMP_FOLDER));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax(maxFileSize);

                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (fi.isFormField()) {
                        fieldName = fi.getFieldName();
                        if (fieldName.equals("upload")) {
                            uploadType = fi.getString();
                        }

                    } else {
                        // Get the uploaded file parameters
                        fieldName = fi.getFieldName();
                        fileName = fi.getName();

                        uploadPath = uploadPath + File.separator + company.getCompanyId() + File.separator + "logo";

                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        fileName = fileName + "_" + company.getCompanyId();

                        if (uploadType.equals("update")) {
//                                    String file_name_to_delete = getSqlMethodsInstance().getLogofileName(UID);
//                                    String filePath_to_delete = uploadPath + File.separator + file_name_to_delete;

//                                    File deletefile = new File(filePath_to_delete);
//                                    deletefile.delete();
                        }

                        filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        fi.write(storeFile);
                    }
                }
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/facebookDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> facebookDetails(HttpServletRequest request,
            HttpServletResponse response) {

        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, String> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String default_access_token = (String) requestBodyMap.get("access_token");
            String method_type = (String) requestBodyMap.get("access_token_method");
            String fb_user_profile_name = (String) requestBodyMap.get("fb_user_profile_name");
            String default_page_name = (String) requestBodyMap.get("default_page_name");
            String settings = (String) requestBodyMap.get("settings");
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            Integer companyId = company.getCompanyId();
            String outputJson = "Success";
            if (!StringUtility.isEmpty(method_type) && method_type.equals("getAccessToken")) {
                JSONObject fb_details = companyPreferencesFacebookService.getCompanyPreferenceForAccessToken(companyId);

                if (fb_details.size() != 0) {

                    default_access_token = (String) fb_details.get("fb_default_page_access_token");
                    fb_user_profile_name = (String) fb_details.get("user_profile_page");
                    default_page_name = (String) fb_details.get("fb_default_page_name");
                }

                if (!StringUtility.isEmpty(settings)) {

                    if (fb_details.get("FacebookLoggedIn") == null) {
                        fb_details.put("FacebookLoggedIn", "false");
                        fb_details.put("user_profile_page", "fb not configured");
                    }
                    outputJson = new Gson().toJson(fb_details);
                } else {
                    outputJson = default_access_token + "," + fb_user_profile_name + "," + default_page_name;
                }

            } else if (!StringUtility.isEmpty(method_type) && method_type.equals("setAccessToken")) {
                companyPreferencesFacebookService.updatePreference(companyId, default_access_token, fb_user_profile_name, default_page_name);
            } else if (!StringUtility.isEmpty(method_type) && method_type.equals("clearFacebookDetails")) {
                companyPreferencesFacebookService.deletePreferences(companyId);
            }
            transactionResponse.setMessage(outputJson);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

//    @RequestMapping(value = "/twitterDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ContainerResponse> twitterDetails(HttpServletRequest request,
//            HttpServletResponse response) {
//
//        TransactionResponse transactionResponse = new TransactionResponse();
//        try {
//            Map<String, String> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
//
//            String method_type = (String) requestBodyMap.get("access_token_method");
//            String access_token = (String) requestBodyMap.get("twitter_access_tokens");
//            String settings = (String) requestBodyMap.get("settings");
//            String twitter_data = "";
//            JSONObject json_twitter = new JSONObject();
//            String twitter_access_token = "";
//            String twitter_access_token_secret = "", twitter_user_name = "";
//            String access_token_secret = "";
//            String user_name = "";
//            
//            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
//            Company company = userProfile.getUser().getFkCompanyId();
//            Integer companyId = company.getCompanyId();
//            String outputJson = "Success";
//
//            if (!StringUtility.isEmpty(access_token)) {
//                String access[] = access_token.split(",");
//                access_token = access[0];
//                access_token_secret = access[1];
//                user_name = access[2];
//            }
//
//            if (!StringUtility.isEmpty(method_type) && method_type.equalsIgnoreCase("setAccessToken")) {
//                companyPreferencesTwitterService.updatePreference(companyId, access_token, access_token_secret, user_name);
//            } else if (!StringUtility.isEmpty(method_type) && method_type.equalsIgnoreCase("getAccessToken")) {
//                json_twitter = companyPreferencesTwitterService.getCompanyPreferenceForAccessToken(companyId);
//                if (json_twitter.size() != 0) {
//                    twitter_access_token = (String) json_twitter.get("twitter_access_token");
//                    twitter_access_token_secret = (String) json_twitter.get("twitter_access_token_secret");
//                    twitter_user_name = (String) json_twitter.get("twitter_user_name");
//                    twitter_data = twitter_access_token + "," + twitter_access_token_secret + "," + twitter_user_name;
//                }
//
//                if (!StringUtility.isEmpty(settings)) {
//                    if (json_twitter.get("TwitterLoggedIn") == null) {
//                        json_twitter.put("TwitterLoggedIn", "false");
//                        json_twitter.put("twitter_user_name", "twitter not configured");
//                    }
//                    outputJson = new Gson().toJson(json_twitter);
//                } else {
//                    outputJson = twitter_data;
//                }
//            } else if (!StringUtility.isEmpty(method_type) && method_type.equalsIgnoreCase("clearTwitterDetails")) {
//                companyPreferencesTwitterService.deletePreferences(companyId);
//            }
//            transactionResponse.setMessage(outputJson);
//            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
//        } catch (Throwable throwable) {
//            logger.error(throwable);
//            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
//        }
//        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
//    }
}
