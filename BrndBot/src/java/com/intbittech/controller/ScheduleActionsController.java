/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.AppConstants;
import com.intbittech.enums.ScheduledEntityType;
import com.intbittech.marketing.service.ScheduledEntityListService;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.UpdateActionDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ScheduleActionsService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.MapUtility;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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
    @Autowired
    private ScheduledEntityListService scheduledEntityListService;
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/getActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getActions(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            String data = actionsService.getActions(requestBodyMap, userCompanyIds.getCompanyId());
            transactionResponse.addDetail(AppConstants.GSON.toJson(data));
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));

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
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            List<String> errors = validateScheduleEmailRequestBody(requestBodyMap);
            if (errors.isEmpty()) {
                Map<String, Integer> data = actionsService.scheduleEmail(requestBodyMap, userCompanyIds.getCompanyId(),userCompanyIds.getUserId());
                transactionResponse.addDetail(AppConstants.GSON.toJson(data));
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
            } else {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(errors));
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
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);

            List<String> errors = validateScheduleEmailActionsRequestBody(requestBodyMap);
            if (errors.isEmpty()) {
                Map<String, Integer> data = actionsService.scheduleEmailActions(requestBodyMap, userCompanyIds.getCompanyId());
                transactionResponse.addDetail(AppConstants.GSON.toJson(data));
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
            } else {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(errors));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/scheduleSocialPostActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> scheduleSocialPostActions(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> transactionResponse = new GenericResponse();

        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            List<String> errors = validateRequestBodyList(requestBodyMap);

            userCompanyIds.setCompanyId((userCompanyIds.getCompanyId()));
            userCompanyIds.setUserId(userCompanyIds.getUserId());
            String type = requestBodyMap.get("type").toString();
            String metadataString = requestBodyMap.get("metadata").toString();

            if (errors.isEmpty()) {
                List<Map<String, Integer>> responseData = actionsService.scheduleSocialPostActions(requestBodyMap, userCompanyIds.getCompanyId());
                transactionResponse.addDetail(AppConstants.GSON.toJson(responseData));
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
            } else {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(errors));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/scheduleSocialPost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> scheduleSocialPost(HttpServletRequest request,
            HttpServletResponse response) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        UserCompanyIds userCompanyIds = new UserCompanyIds();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            List<String> errors = validateRequestBodyList(requestBodyMap);
            UserCompanyIds userCompany = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            userCompanyIds.setCompanyId(userCompany.getCompanyId());
            userCompanyIds.setUserId(userCompany.getUserId());
            String type = requestBodyMap.get("type").toString();
            String metadataString = requestBodyMap.get("metadata").toString();
            errors.addAll(validateMetadata(metadataString, type));

            if (errors.isEmpty()) {
                List<Map<String, Integer>> responseData = actionsService.scheduleSocialPost(requestBodyMap, userCompanyIds.getCompanyId(),userCompanyIds.getUserId());
                transactionResponse.addDetail(AppConstants.GSON.toJson(responseData));
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
            } else {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(errors));
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

    private List<String> validateRequestBodyList(Map<String, Object> requestBodyMap) {
        List<String> errorMessages = new ArrayList<>();

        errorMessages.addAll(validateScheduleSocialPostActionsRequestBody(requestBodyMap));

        return errorMessages;
    }

    private List<String> validateScheduleSocialPostActionsRequestBody(Map<String, Object> requestBody) {
        List<String> errorMsgs = new ArrayList<>();
        if (!MapUtility.mapContainsKey(requestBody, "image_name")) {
            errorMsgs.add("image_name is missing");
        }

        if (!MapUtility.mapContainsKey(requestBody, "type")) {
            errorMsgs.add("type is missing");
        } else {
            try {
                ScheduledEntityType.valueOf(requestBody.get("type").toString());
            } catch (IllegalArgumentException ex) {
                errorMsgs.add("Unsupported type value: " + requestBody.get("type").toString());
            }
        }

//        if (!MapUtility.mapContainsKey(requestBody, "token_data")) {
//            errorMsgs.add("token_data JSON is missing");
//        }

        if (!MapUtility.mapContainsKey(requestBody, "metadata")) {
            errorMsgs.add("metadata JSON is missing");
        }

        return errorMsgs;
    }

    private List<String> validateMetadata(String metadataString, String postType) {
        List<String> errorMsgs = validateJsonData(metadataString,
                " is missing in metadata JSON Object", () -> {
                    Set<String> requiredKeys = new HashSet<>();
                    if (postType.contains(ScheduledEntityType.Facebook.toString())) {
                        requiredKeys.add("post_text");
                        requiredKeys.add("url");
//                requiredKeys.add("description");
                    } else if (postType.contains(ScheduledEntityType.Twitter.toString())) {
                        requiredKeys.add("text");
                    }
                    return requiredKeys;
                });

        return errorMsgs;
    }

    private List<String> validateJsonData(String jsonDataString,
            String errorMsgSuffix,
            Supplier<Set<String>> requiredKeysBuilder) {
        List<String> errorMsgs = new ArrayList<>();

        Map<String, Object> jsonDataMap
                = AppConstants.GSON.fromJson(jsonDataString, Map.class);

        Set<String> requiredKeys = requiredKeysBuilder.get();

        Set<String> keySet = jsonDataMap.keySet();
        requiredKeys.stream().filter((key) -> (!keySet.contains(key))).forEach((key) -> {
            errorMsgs.add(key + errorMsgSuffix);
        });

        return errorMsgs;
    }
    
     @RequestMapping(value = "/updateActionAssignedTo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateActionAssignedTo(@RequestBody UpdateActionDetails updateActionDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
           ScheduledEntityList scheduledEntityList = scheduledEntityListService.getById(updateActionDetails.getScheduleId());
            Users users = usersService.getUserById(updateActionDetails.getUserAssignToId());
            users.setUserId(updateActionDetails.getUserAssignToId());
            scheduledEntityList.setAssignedTo(users);
            scheduledEntityList.setUpdatedAt(new Date());
            scheduledEntityListService.update(scheduledEntityList);
            transactionResponse.setId(Utility.getFirstTwoCharactersOfName(scheduledEntityList.getAssignedTo().getFirstName(), scheduledEntityList.getAssignedTo().getLastName()));
            transactionResponse.setMessage(scheduledEntityList.getAssignedTo().getFirstName() + " " + scheduledEntityList.getAssignedTo().getLastName());
            
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Action updated successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
}
