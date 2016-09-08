/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.CommentLog;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.CommentLogDetails;
import com.intbittech.responsemappers.CommentLogResponse;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CommentLogService;
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
public class CommentLogController {
    
    private final static Logger logger = Logger.getLogger(CommentLogController.class);
    @Autowired
    private CommentLogService commentLogService;
    
      @RequestMapping(value = "saveCommentLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCommentLog(@RequestBody CommentLogDetails commentLogDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            CommentLog commentLog = new CommentLog();
           
            ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
            scheduledEntityList.setScheduledEntityListId(commentLogDetails.getScheduledEntityListId());
            commentLog.setFkScheduledEntityid(scheduledEntityList);
            Users userObject = new Users();
            userObject.setUserId(1);//To-do to get user id from UI
            commentLog.setCommentedBy(userObject);
            commentLog.setComment(commentLogDetails.getComment());
            commentLogService.save(commentLog);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Comment log created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
}
     @RequestMapping(value = "getAllCommentLog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllCommentLog() {
         GenericResponse<CommentLogResponse> genericResponse = new GenericResponse();
        try {
                List<CommentLogResponse> commentLogResponseList = commentLogService.getAllCommentLog();
            genericResponse.setDetails(commentLogResponseList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllCommentLogByScheduledEntityListId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllCommentLogByScheduledEntityListId(@RequestParam("scheduledEntityListId")Integer scheduledEntityListId ) {
         GenericResponse<CommentLogResponse> genericResponse = new GenericResponse();
        try {
                List<CommentLogResponse> commentLogResponseList = commentLogService.getAllCommentLogByScheduledEntityListId(scheduledEntityListId);
            genericResponse.setDetails(commentLogResponseList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Activity retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
     @RequestMapping(value = "deleteCommentLog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteCommentLog(@RequestParam("commentLogId") Integer commentLogId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
              commentLogService.delete(commentLogId);
            
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Comment deleted successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
}