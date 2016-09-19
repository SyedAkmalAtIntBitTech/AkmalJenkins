/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.modelmappers.PushedScheduledActionCompaniesDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.PushedScheduledActionCompaniesService;
import com.intbittech.utility.ErrorHandlingUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ajit @ Intbit
 */
@Controller
@RequestMapping(value = "/pushedActions")
public class PushedScheduledActionCompaniesController {
    
   private final static Logger logger = Logger.getLogger(PushedScheduledActionCompaniesController.class);

    @Autowired
    private PushedScheduledActionCompaniesService pushedScheduledActionCompaniesService;
    
    
     @RequestMapping(value = "/savePushedScheduledActionCompanies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveActivity(@RequestBody PushedScheduledActionCompaniesDetails pushedScheduledActionCompaniesDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
           pushedScheduledActionCompaniesService.savePushedScheduledActionCompanies(pushedScheduledActionCompaniesDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Pushed scheduled action companies created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    
}
