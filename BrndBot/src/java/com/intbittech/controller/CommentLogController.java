/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.CommentLog;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.ActivityLogDetails;
import com.intbittech.modelmappers.CommentLogDetails;
import com.intbittech.responsemappers.CommentActivityLogResponse;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ActivityLogService;
import com.intbittech.services.CommentLogService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.IConstants;
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
@RequestMapping(value = "/comment")
public class CommentLogController {

    private final static Logger logger = Logger.getLogger(CommentLogController.class);
    @Autowired
    private CommentLogService commentLogService;
    @Autowired
    private ActivityLogService activityLogService;

    @RequestMapping(value = "/saveActionComment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveActionComment(@RequestBody CommentLogDetails commentLogDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            CommentLog commentLog = new CommentLog();

            ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
            scheduledEntityList.setScheduledEntityListId(commentLogDetails.getScheduleId());
            commentLog.setFkScheduledEntityid(scheduledEntityList);
            Users userObject = new Users();
            userObject.setUserId(commentLogDetails.getUserId());
            commentLog.setCommentedBy(userObject);
            commentLog.setComment(commentLogDetails.getComment());
            commentLogService.save(commentLog);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Action comment created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getAllCommentLog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllCommentLog() {
        GenericResponse<CommentActivityLogResponse> genericResponse = new GenericResponse();
        try {
            List<CommentActivityLogResponse> commentLogResponseList = commentLogService.getAllCommentLog();
            genericResponse.setDetails(commentLogResponseList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getAllCommentByActionId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllCommentLogByActionId(@RequestParam("scheduleId") Integer scheduleId, @RequestParam("userId") Integer userId) {
        GenericResponse<CommentActivityLogResponse> genericResponse = new GenericResponse();

        try {
            List<CommentActivityLogResponse> commentLogResponseList = commentLogService.getAllCommentLogByScheduledEntityListId(scheduleId, userId);
            genericResponse.setDetails(commentLogResponseList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/deleteActionComment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteActionComment(@RequestParam("commentId") Integer commentId, @RequestParam("userId") Integer userId) {

        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            CommentLog commentLog = commentLogService.getCommentLogByCommentLogId(commentId);
            commentLogService.delete(commentId);
            ActivityLogDetails activityLogDetails = new ActivityLogDetails();
            activityLogDetails.setActivityId(IConstants.ACTIVITY_DELETED_COMMENT_ACTION_ID);
            activityLogDetails.setScheduledEntityId(commentLog.getFkScheduledEntityid().getScheduledEntityListId());
            activityLogDetails.setCreatedBy(userId);
            activityLogService.saveActivityLog(activityLogDetails);

            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Comment deleted successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
