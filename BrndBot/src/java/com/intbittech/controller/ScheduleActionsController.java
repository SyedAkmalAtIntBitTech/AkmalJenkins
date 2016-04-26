/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbit.AppConstants;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.services.ScheduleActionsService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.MapUtility;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/actions")
public class ScheduleActionsController {

    private final static Logger logger = Logger.getLogger(ScheduleActionsController.class);

    @Autowired
    private ScheduleActionsService actionsService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/getActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getActions(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            String data = actionsService.getActions(requestBodyMap, companyId);
            transactionResponse.addDetail(AppConstants.GSON.toJson(data));
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/scheduleEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> scheduleEmail(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            List<String> errors = validateScheduleEmailRequestBody(requestBodyMap);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(errors));
            if (!errors.isEmpty()) {
                UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
                Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
                Map<String, Integer> data = actionsService.scheduleEmail(requestBodyMap, companyId);
                transactionResponse.addDetail(AppConstants.GSON.toJson(data));
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/scheduleEmailActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> scheduleActions(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            List<String> errors = validateScheduleEmailActionsRequestBody(requestBodyMap);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(errors));
            if (!errors.isEmpty()) {
                UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
                Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
                Map<String, Integer> data = actionsService.scheduleEmailActions(requestBodyMap, companyId);
                transactionResponse.addDetail(AppConstants.GSON.toJson(data));
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    private List<String> validateScheduleEmailRequestBody(Map<String, Object> requestBodyMap) {
        List<String> errorMsgs = new ArrayList<>();

        if (requestBodyMap == null) {
            errorMsgs.add("Request body is missing");
        } else {

            if (!MapUtility.mapContainsKey(requestBodyMap, "email_subject")) {
                errorMsgs.add("Email subject is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "to_email_addresses")) {
                errorMsgs.add("To email address is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "email_list")) {
                errorMsgs.add("Email List name is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "reply_to_email_address")) {
                errorMsgs.add("Reply to email address is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "from_email_address")) {
                errorMsgs.add("From email address is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "from_name")) {
                errorMsgs.add("From name is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "schedule_title")) {
                errorMsgs.add("Schedule title is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "schedule_time")) {
                errorMsgs.add("Schedule time is missing");
            }
        }
        return errorMsgs;
    }

    private List<String> validateScheduleEmailActionsRequestBody(Map<String, Object> requestBodyMap) {
        List<String> errorMsgs = new ArrayList<>();
        if (requestBodyMap == null) {
            errorMsgs.add("Request body is missing");
        } else {
            if (!MapUtility.mapContainsKey(requestBodyMap, "email_subject")) {
                errorMsgs.add("Email subject is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "to_email_addresses")) {
                errorMsgs.add("To email address is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "email_body")) {
                errorMsgs.add("Email body is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "email_list")) {
                errorMsgs.add("Email List name is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "reply_to_email_address")) {
                errorMsgs.add("Reply to email address is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "from_email_address")) {
                errorMsgs.add("From email address is missing");
            }
            if (!MapUtility.mapContainsKey(requestBodyMap, "from_name")) {
                errorMsgs.add("From name is missing");
            }
        }
        return errorMsgs;
    }
}
