/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Activity;
import com.intbittech.model.ActivityLog;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.ActivityLogDetails;
import com.intbittech.responsemappers.ActivityLogResponse;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ActivityLogService;
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
public class ActivityLogController {
    private final static Logger logger = Logger.getLogger(ActivityLogController.class);
    @Autowired
    private ActivityLogService activityLogService;
    
     @RequestMapping(value = "saveActivityLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveActivityLog(@RequestBody ActivityLogDetails activityLogDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            ActivityLog activityLog = new ActivityLog();
            Activity activity = new Activity();
            activity.setActivityId(activityLogDetails.getActivityId());
            activityLog.setFkActivityId(activity);
            ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
            scheduledEntityList.setScheduledEntityListId(activityLogDetails.getScheduledEntityId());
            activityLog.setFkScheduledEntityid(scheduledEntityList);
            Users users = new Users();
            users.setUserId(activityLogDetails.getAssignedTo());
            activityLog.setAssignedTo(users);
            Users userObject = new Users();
            userObject.setUserId(1);//To-do to get user id from UI
            activityLog.setCreatedBy(userObject);
            activityLogService.save(activityLog);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity log created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "getAllActivityLog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllActivityLog() {
         GenericResponse<ActivityLogResponse> genericResponse = new GenericResponse();
        try {
                List<ActivityLogResponse>activityLogResponseList = activityLogService.getAllActivityLog();
            genericResponse.setDetails(activityLogResponseList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllActivityLogByScheduledEntityListId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllActivityLogByScheduledEntityListId(@RequestParam("scheduledEntityListId")Integer scheduledEntityListId ) {
         GenericResponse<ActivityLogResponse> genericResponse = new GenericResponse();
        try {
                List<ActivityLogResponse>activityLogResponseList = activityLogService.getAllActivityLogByScheduledEntityListId(scheduledEntityListId);
            genericResponse.setDetails(activityLogResponseList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
}
