/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.EmailListType;
import com.intbittech.modelmappers.EmailListTypeDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailListTypeService;
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
 * @author ajit
 */
@Controller
public class EmailListTypeController {

    private static Logger logger = Logger.getLogger(EmailListTypeController.class);
    @Autowired
    private EmailListTypeService emailListTypeService;

    @RequestMapping(value = "saveEmailListType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveEmailListType(@RequestBody EmailListTypeDetails emailListTypeDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailListType emailListType = new EmailListType();
            emailListType.setTypeName(emailListTypeDetails.getEmailListTypeName());
            emailListTypeService.save(emailListType);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list type created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "updateEmailListType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateEmailListType(@RequestBody EmailListTypeDetails emailListTypeDetails,@RequestParam("emailListTypeId") Integer emailListTypeId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailListType emailListType = new EmailListType();
            emailListType.setTypeName(emailListTypeDetails.getEmailListTypeName());
            emailListTypeService.save(emailListType);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list type updated successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
     @RequestMapping(value = "getAllEmailListType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListType() {
         GenericResponse<EmailListType> genericResponse = new GenericResponse();
        try {
            List<EmailListType> emailListTypeList = emailListTypeService.getAllEmailListType();
            genericResponse.setDetails(emailListTypeList);
            
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list type retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

}
