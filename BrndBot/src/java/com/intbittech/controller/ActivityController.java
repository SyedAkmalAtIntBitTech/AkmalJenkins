/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Activity;
import com.intbittech.modelmappers.ActivityDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ActivityService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ajit @ Intbit
 */
@Controller
public class ActivityController {

    private final static Logger logger = Logger.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "saveActivity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveActivity(@RequestBody ActivityDetails activityDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Activity activity = new Activity();
            activity.setActivityName(activityDetails.getActvityName());
            activityService.save(activity);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateActivity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateActivity(@RequestBody ActivityDetails activityDetails, @RequestParam("activityId") Integer activityId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Activity activity = activityService.getActivityByActivityId(activityId);
            activity.setActivityName(activityDetails.getActvityName());
            activityService.update(activity);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity updated successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllActivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllActivity() {
         GenericResponse<Activity> genericResponse = new GenericResponse();
        try {
            List<Activity> activityList = activityService.getAllActivity();
            genericResponse.setDetails(activityList);
            
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteActivity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteActivity(@RequestParam("activityId") Integer activityId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
              activityService.delete(activityId);
            
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity deleted successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
