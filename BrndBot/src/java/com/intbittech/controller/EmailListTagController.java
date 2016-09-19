/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.EmailListTag;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.services.EmailListTagService;
import com.intbittech.services.FranchiseEmailListTagLookupService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ajit @ Intbit
 */
@Controller
@RequestMapping(value = "/emailListTag")
public class EmailListTagController {
    
    private final static Logger logger = Logger.getLogger(EmailListTagController.class);
    @Autowired
    private EmailListTagService emailListTagService;
    @Autowired
    private FranchiseEmailListTagLookupService franchiseEmailListTagLookupService;
    
     @RequestMapping(value = "/getAllEmailListTag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListTag() {
         GenericResponse<EmailListTag> genericResponse = new GenericResponse();
        try {
           List<EmailListTag> emailListTagList = emailListTagService.getAllEmailListTag();
            genericResponse.setDetails(emailListTagList);
            
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list tag retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
     @RequestMapping(value = "/getAllEmailListTagForFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListTagForFranchise(@RequestParam("franchiseId")Integer franchiseId) {
         GenericResponse<EmailListTag> genericResponse = new GenericResponse();
        try {
           List<EmailListTag> emailListTagList = franchiseEmailListTagLookupService.getAllEmailListTagForFranchise(Integer.SIZE);
            genericResponse.setDetails(emailListTagList);
            
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list tag for franchise retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
}
