/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.BrndBotBaseHttpServlet;
import com.controller.SqlMethods;
import com.google.gson.Gson;
import com.intbit.util.CustomStyles;
import com.intbittech.AppConstants;
import com.intbittech.externalcontent.ExternalContentProcessor;
import com.intbittech.model.Address;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.modelmappers.EmailListDetails;
import com.intbittech.model.InvitedUsers;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.AddressDetails;
import com.intbittech.modelmappers.CompanyColorsDetails;
import com.intbittech.modelmappers.FooterDetails;
import com.intbittech.modelmappers.InviteDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.schedulers.MindbodyEmailListProcessor;
import com.intbittech.services.AddressService;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.CompanyService;
import com.intbittech.services.ContactEmailListLookupService;
import com.intbittech.services.EmailListService;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.services.UnsubscribedEmailsService;
import com.intbittech.services.UsersInviteService;
import com.intbittech.services.UsersService;
import com.intbittech.social.CompanyPreferencesFacebook;
import com.intbittech.social.CompanyPreferencesTwitter;
import com.intbittech.utility.EmailValidator;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.ServletUtil;
import com.intbittech.utility.StringUtility;
import com.intbittech.utility.Utility;
import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import java.io.BufferedReader;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 *
 * @author sandeep
 */
@Controller
@RequestMapping(value = "/settings")
public class SettingsController extends BrndBotBaseHttpServlet {

    private final static Logger logger = Logger.getLogger(SettingsController.class);

    @Autowired
    ForgotPasswordService forgotPasswordService;

    @Autowired
    UsersInviteService usersInviteService;

    @Autowired
    UsersService usersService;

    @Autowired
    CompanyService companyService;
    
    @Autowired
    AddressService addressService;
    
    @Autowired
    UnsubscribedEmailsService unsubscribedEmailsService;
    
    @Autowired
    ContactEmailListLookupService contactEmailListLookupService;
    
    @Autowired
    private MessageSource messageSource;

    private Twitter twitter;
    private RequestToken requestToken;
    private Facebook facebook;

    @Autowired
    private CompanyPreferencesService companyPreferencesService;
    @Autowired
    private EmailListService emailListService;

    @RequestMapping(value = "/sendInvitation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> sendInvitation(@RequestBody InviteDetails inviteDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            boolean returnMessage = usersService.saveNonExistingUser(inviteDetails);
            if (returnMessage) {
                transactionResponse.setMessage(messageSource.getMessage("invitation_check_mail", new String[]{}, Locale.US));
            } else {
                transactionResponse.setMessage(messageSource.getMessage("invitation_failure", new String[]{}, Locale.US));
            }

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getLocalizedMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/resendInvitation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> resendInvitation(@RequestParam("inviteId") Integer inviteId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            boolean returnMessage = usersInviteService.reSendInvitation(inviteId);
            if (returnMessage) {
                transactionResponse.setMessage(messageSource.getMessage("invitation_check_mail", new String[]{}, Locale.US));
            } else {
                transactionResponse.setMessage(messageSource.getMessage("invitation_failure", new String[]{}, Locale.US));
            }

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/editUserRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> editUserRole(@RequestBody InviteDetails inviteDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            boolean returnMessage = usersService.updateRole(inviteDetails);
            if (returnMessage) {
                transactionResponse.setMessage(messageSource.getMessage("details_updated", new String[]{}, Locale.US));
            } else {
                transactionResponse.setMessage(messageSource.getMessage("update_failure", new String[]{}, Locale.US));
            }

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/removeUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> removeUser(@RequestParam("inviteId") Integer inviteId, @RequestBody UserCompanyIds userCompanyIds) {
        TransactionResponse transactionResponse = new TransactionResponse();
        boolean flag = false;
        try {

            boolean returnMessage = usersInviteService.removeUsersByInviteIdAndCompanyId(inviteId, userCompanyIds.getCompanyId());
            if (returnMessage){
                transactionResponse.setMessage(messageSource.getMessage("user_removed", new String[]{}, Locale.US));
            }else{

                transactionResponse.setMessage(messageSource.getMessage("user_remove_failure", new String[]{}, Locale.US));
            }

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(messageSource.getMessage("user_remove_failure", new String[]{}, Locale.US)));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getInvitedUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getInvitedUsers(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("userId") Integer userId, @RequestParam("companyId") Integer companyId) {
        GenericResponse<InvitedUsers> genericResponse = new GenericResponse<>();

        try {
            Users user = usersService.getUserById(userId);
            Company company = companyService.getCompanyById(companyId);
            List<InvitedUsers> invitedUsers = usersInviteService.getInvitedUsers(user, company);
            genericResponse.setDetails(invitedUsers);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getColors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColors(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("companyId") Integer companyId) {
        GenericResponse<String> genericResponse = new GenericResponse<>();

        try {
            Company company = companyService.getCompanyById(companyId);
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
            Company company = companyService.getCompanyById(companyColorsDetails.getCompanyId());
            companyPreferencesService.setColors(companyColorsDetails, company);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyCategories_color_update", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/saveAddress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveAddress(@RequestBody AddressDetails addressDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Company company = companyService.getCompanyById(addressDetails.getCompanyId());
            addressService.save(addressDetails, company);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("saveAddress_success", new String[]{}, Locale.US)));
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
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String from_address = (String) requestBodyMap.get("from_address");
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            Company company = companyService.getCompanyById(userCompanyIds.getCompanyId());
            String reply_email_address = (String) requestBodyMap.get("reply_email_address");
            String from_name = (String) requestBodyMap.get("from_name");
            JSONObject json_object = new JSONObject();
            json_object.put(IConstants.kEmailFromAddress, from_address);
            json_object.put(IConstants.kEmailReplyAddress, reply_email_address);
            json_object.put(IConstants.kFromName, from_name);
            companyPreferencesService.updateEmailSettings(json_object, company);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getEmailSettings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getEmailSettings(@RequestParam("companyId") Integer companyId) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            Company company = companyService.getCompanyById(companyId);
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
    public ResponseEntity<ContainerResponse> getAllPreferences(@RequestParam("companyId") Integer companyId) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            Company company = companyService.getCompanyById(companyId);
            CompanyPreferences companyPreferences = companyPreferencesService.getByCompany(company);
            JSONParser parser = new JSONParser();
            JSONObject jSONObject = (JSONObject) parser.parse(companyPreferences.getCompanyPreferences());
            
            if(companyPreferences.getFkAddressId()!=null)
            {
                JSONObject addressJSONObject = new JSONObject();
                addressJSONObject.put("addressLine1", companyPreferences.getFkAddressId().getAddressLine1());
                addressJSONObject.put("addressLine2", companyPreferences.getFkAddressId().getAddressLine2());
                addressJSONObject.put("city", companyPreferences.getFkAddressId().getCity());
                addressJSONObject.put("state", companyPreferences.getFkAddressId().getState());
                addressJSONObject.put("zipCode", companyPreferences.getFkAddressId().getZipcode());
                addressJSONObject.put("country", companyPreferences.getFkAddressId().getCountry());
                JSONArray addressJSONArray = new JSONArray();
                addressJSONArray.add(addressJSONObject);
                jSONObject.put("companyAddress", addressJSONArray);
            }
            genericResponse.addDetail(new Gson().toJson(jSONObject));
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getGlobalAndUserColors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getGlobalAndUserColors(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("companyId") Integer companyId) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            Company company = companyService.getCompanyById(companyId);
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

    @RequestMapping(value = "/changeLogo", method = RequestMethod.POST)
    public ResponseEntity<ContainerResponse> changeLogo(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            Company company = companyService.getCompanyById(userCompanyIds.getCompanyId());

            String filePath = null;
            String fileName = null, fieldName = null, uploadType = null;

            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;

            String uploadPath = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH;

            // Verify the content type
            String contentType = request.getContentType();

            if ((contentType.indexOf("multipart/form-data") >= 0)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                //factory.setRepository(new File(AppConstants.TMP_FOLDER));
                factory.setRepository(new File("/home"));

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

                        fileName = AppConstants.COMPANY_LOGO_FILENAME;

                        if (uploadType.equals("update")) {
//                                    String file_name_to_delete = getSqlMethodsInstance().getLogofileName(UID);
//                                    String filePath_to_delete = uploadPath + File.separator + file_name_to_delete;

//                                    File deletefile = new File(filePath_to_delete);
//                                    deletefile.delete();
                        }

                        filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        if (storeFile.exists()) {
                            storeFile.delete();
                        }
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
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);

            Integer companyId = userCompanyIds.getCompanyId();
            String default_access_token = (String) requestBodyMap.get("access_token");
            String method_type = (String) requestBodyMap.get("access_token_method");
            String fb_user_profile_name = (String) requestBodyMap.get("fb_user_profile_name");
            String default_page_name = (String) requestBodyMap.get("default_page_name");
            String settings = (String) requestBodyMap.get("settings");
            String outputJson = "Success";
            CompanyPreferencesFacebook companyPreferencesFacebookService = new CompanyPreferencesFacebook();

            if (!StringUtility.isEmpty(method_type) && method_type.equals("getAccessToken")) {
                JSONObject fb_details = companyPreferencesFacebookService.getCompanyPreferenceForAccessToken(companyId);

                if (fb_details.size() != 0) {

                    default_access_token = (String) fb_details.get("fb_default_page_access_token");
                    fb_user_profile_name = (String) fb_details.get("user_profile_page");
                    default_page_name = (String) fb_details.get("fb_default_page_name");
                    if (default_access_token != "") {
                        default_access_token = "true";
                    }
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

    @RequestMapping(value = "/twitterDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> twitterDetails(HttpServletRequest request,
            HttpServletResponse response) {

        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, String> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);

            String method_type = (String) requestBodyMap.get("access_token_method");
            String access_token = (String) requestBodyMap.get("twitter_access_tokens");
            String settings = (String) requestBodyMap.get("settings");
            String twitter_data = "";
            JSONObject json_twitter = new JSONObject();
            String twitter_access_token = "";
            String twitter_access_token_secret = "", twitter_user_name = "";
            String access_token_secret = "";
            String user_name = "";

            String outputJson = "Success";

            if (!StringUtility.isEmpty(access_token)) {
                String access[] = access_token.split(",");
                access_token = access[0];
                access_token_secret = access[1];
                user_name = access[2];
            }
            CompanyPreferencesTwitter companyPreferencesTwitterService = new CompanyPreferencesTwitter();
            if (!StringUtility.isEmpty(method_type) && method_type.equalsIgnoreCase("setAccessToken")) {
                companyPreferencesTwitterService.updatePreference(userCompanyIds.getCompanyId(), access_token, access_token_secret, user_name);
            } else if (!StringUtility.isEmpty(method_type) && method_type.equalsIgnoreCase("getAccessToken")) {
                json_twitter = companyPreferencesTwitterService.getCompanyPreferenceForAccessToken(userCompanyIds.getCompanyId());
                if (json_twitter.size() != 0) {
                    twitter_access_token = (String) json_twitter.get("twitter_access_token");
                    twitter_access_token_secret = (String) json_twitter.get("twitter_access_token_secret");
                    twitter_user_name = (String) json_twitter.get("twitter_user_name");
                    twitter_data = twitter_access_token + "," + twitter_access_token_secret + "," + twitter_user_name;
                }

                if (!StringUtility.isEmpty(settings)) {
                    if (json_twitter.get("TwitterLoggedIn") == null) {
                        json_twitter.put("TwitterLoggedIn", "false");
                        json_twitter.put("twitter_user_name", "twitter not configured");
                    }
                    outputJson = new Gson().toJson(json_twitter);
                } else {
                    outputJson = twitter_data;
                }
            } else if (!StringUtility.isEmpty(method_type) && method_type.equalsIgnoreCase("clearTwitterDetails")) {
                companyPreferencesTwitterService.deletePreferences(userCompanyIds.getCompanyId());
            }
            transactionResponse.setMessage(outputJson);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/fbAuthURL", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> fbLogin(HttpServletRequest request) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        String hostURL = ServletUtil.getServerName(request.getServletContext());
        try {
            Map<String, String> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String redirectUrl = requestBodyMap.get("redirectUrl");
            facebook = new FacebookFactory().getInstance();
            facebook.setOAuthAppId(AppConstants.facebookString1, AppConstants.facebookString2);
            facebook.setOAuthPermissions(AppConstants.facebookPermissions);
            genericResponse.addDetail(facebook.getOAuthAuthorizationURL(hostURL + "" + redirectUrl));
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("Success", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/fbGetToken/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> fbGetToken(@PathVariable(value = "code") String code, HttpServletRequest request) {
        GenericResponse<JSONObject> genericResponse = new GenericResponse<>();
        try {
            facebook.getOAuthAccessToken(code);
            String user_name = facebook.getName();

            ResponseList<Account> accounts = facebook.getAccounts();

            logger.info("FB Accounts:" + accounts.size());

            JSONArray jsonarray = new JSONArray();
            for (int i = 0; i < accounts.size(); i++) {
                Account yourPageAccount = accounts.get(i);  // if index 0 is your page account.
                String pageAccessToken = yourPageAccount.getAccessToken();
                String pageId = yourPageAccount.getId();
                String profilepicture = facebook.getPagePictureURL(pageId).toString();
                logger.info(yourPageAccount.getName() + " - " + pageAccessToken);
                facebook.setOAuthAccessToken(new facebook4j.auth.AccessToken(pageAccessToken));
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("pageAccessToken", pageAccessToken);
                jsonObject.put("profileName", yourPageAccount.getName());
                jsonObject.put("profilPicture", profilepicture);
                jsonarray.add(jsonObject);
            }
            JSONObject responseJSONObject = new JSONObject();
            responseJSONObject.put("fbPages", jsonarray);
            responseJSONObject.put("user_profile_name", user_name);

            genericResponse.addDetail(responseJSONObject);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("Success", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/twitterAuthURL", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> twitterLogin() {
        GenericResponse<String> genericResponse = new GenericResponse<>();

        try {
            twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer(AppConstants.twitterString1, AppConstants.twitterString2);
            //twitter.setOAuthConsumer("G6fPQU023izaVT8RtAurlHmUW", "d6jMSiwI9XqVbNDMQ4XJmIzD9XrKwZC5mKjrbujepTOqgrnMEW");
            requestToken = twitter.getOAuthRequestToken();
            genericResponse.addDetail(requestToken.getAuthenticationURL());
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("Success", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/twitterGetToken/{pin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> twitterGetToken(@RequestParam("companyId") Integer companyId, @PathVariable(value = "pin") String pin) throws NamingException, SQLException {
        GenericResponse<String> genericResponse = new GenericResponse<>();

        try {
            AccessToken accessToken = null;
            CompanyPreferencesTwitter companyPreferencesTwitterService = new CompanyPreferencesTwitter();

            if (pin.length() > 0) {
                accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            } else {
                accessToken = twitter.getOAuthAccessToken();
            }
            twitter4j.User user = twitter.showUser(twitter.getScreenName());
            String user_name = user.getName();
            String access_token = accessToken.getToken();
            String access_token_secret = accessToken.getTokenSecret();
            companyPreferencesTwitterService.updatePreference(companyId, access_token, access_token_secret, user_name);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("Success", new String[]{}, Locale.US)));
        } catch (TwitterException | IllegalStateException | NoSuchMessageException throwable) {
            logger.error(throwable);
            logger.debug("Unable to get twitter token.");
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/setFooter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> setFooter(@RequestBody FooterDetails footerDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Company company = companyService.getCompanyById(footerDetails.getCompanyId());
            companyPreferencesService.setFooterDetails(footerDetails, company);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyCategories_color_update", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/unsubscribeEmails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> unsubscribeEmails(HttpServletRequest request,@RequestBody EmailListDetails emailList) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            Runnable myRunnable = new Runnable() {
                public void run() {
                    try {
                        Map<String, String> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

                        UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
                        ExternalContentProcessor externalContentProcessor = new ExternalContentProcessor(userCompanyIds.getCompanyId());
                        int partitionSize = 150;
                        List<String> partitionEmail = new ArrayList<>();
                        EmailValidator emailValidator = new EmailValidator();
                        for (int i = 0; i < emailList.getEmailList().size(); i++) {
                            if (!StringUtility.isEmpty(emailList.getEmailList().get(i)) && emailValidator.validate(emailList.getEmailList().get(i))) {
                                partitionEmail.add(emailList.getEmailList().get(i));
                            }
                            if (i != 0 && i % partitionSize == 0) {
                                externalContentProcessor.searchEmailAndUpdateEmailOptIn(partitionEmail);
                                partitionEmail = new ArrayList<>();
                            }
                        }
                        SqlMethods sqlMethods = new SqlMethods();
                        Integer studioId = sqlMethods.getStudioID(userCompanyIds.getCompanyId());
                        MindbodyEmailListProcessor mindbodyEmailListProcessor = new MindbodyEmailListProcessor();
                        mindbodyEmailListProcessor.processEachRow(userCompanyIds.getCompanyId(), studioId);
                    } catch (Throwable throwable) {
                        logger.error(throwable);
//                        transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
                    }
                }
            };
            Thread thread = new Thread(myRunnable);
            thread.start();
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("unsubscribeEmails_success", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/saveUnsubscribeEmails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveUnsubscribeEmails(HttpServletRequest request,@RequestBody EmailListDetails emailList) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
//            Map<String, String> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

//            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
//            companyPreferencesService.saveUnsubscribeEmails(userCompanyIds.getCompanyId(), emailList.getEmailList());
            unsubscribedEmailsService.save(emailList.getCompanyId(), emailList.getEmailList());
            Runnable myRunnable = new Runnable() {
                public void run() {
                    try {
                        //Todo ilyas refactor this
                        CompanyPreferences companyPreferences = companyPreferencesService.getByCompanyId(emailList.getCompanyId());
                        Integer studioId = Integer.parseInt(companyPreferences.getCompanyLocation());
                        MindbodyEmailListProcessor mindbodyEmailListProcessor = new MindbodyEmailListProcessor();
                        mindbodyEmailListProcessor.processEachRow(emailList.getCompanyId(), studioId);
//                        emailListService.updateUnsubscribedUserEmailLists(companyPreferences);
                        contactEmailListLookupService.updateUnsubscribedUserEmailLists(emailList.getCompanyId());
                            
                    } catch (Throwable throwable) {

                    }
                }
            };
            Thread thread = new Thread(myRunnable);
            thread.start();
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("unsubscribeEmails_success", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
}
