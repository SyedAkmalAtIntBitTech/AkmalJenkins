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
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.UserProfile;
import com.intbittech.modelmappers.CompanyColorsDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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

    @RequestMapping(value = "/getColorsFromLogo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColorsFromLogo() {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Company company = userProfile.getUser().getFkCompanyId();
            CompanyPreferences companyPreferences = companyPreferencesService.getByCompany(company);
            if(companyPreferences == null) {
                companyPreferences.setFkCompanyId(company);
            }
            genericResponse.addDetail(companyPreferences.getCompanyPreferences());
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
}
