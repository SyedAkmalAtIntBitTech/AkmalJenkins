/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.EmailBlock;
import com.intbittech.model.EmailBlockExternalSource;
import com.intbittech.model.OrganizationEmailBlockLookup;
import com.intbittech.modelmappers.EmailBlockDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailBlockService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.pool2.PoolUtils;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ilyas
 */
@RestController
public class EmailBlockController {
    private Logger logger = Logger.getLogger(EmailBlockController.class);
    
    @Autowired
    private EmailBlockService emailBlockService;
    
    @RequestMapping(value = "saveEmailBlock", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveEmailBlock(@RequestBody EmailBlockDetails emailBlockDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            emailBlockService.saveEmailBlock(emailBlockDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block created successfully."));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteEmailBlock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteEmailModel(@RequestParam("emailBlockId") Integer emailBlockId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            emailBlockService.delete(emailBlockId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block deleted successfully."));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllEmailBlocksByOrganizationId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailBlocksByOrganizationId(@RequestParam("organizationId") Integer organizationId) {
        GenericResponse<EmailBlockDetails> genericResponse = new GenericResponse<>();
        try
        {
            List<EmailBlockDetails> emailBlockDetailsList = new ArrayList<>();
            List<OrganizationEmailBlockLookup> organizationEmailBlockList = emailBlockService.getAllOrganizationEmailBlock(organizationId);
            for(OrganizationEmailBlockLookup organizationEmailBlockObject : organizationEmailBlockList) {
                EmailBlockDetails emailBlockDetails = new EmailBlockDetails();
                emailBlockDetails.setEmailBlockId(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
                emailBlockDetails.setEmailBlockName(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockName());
                emailBlockDetails.setOrganizationId(organizationEmailBlockObject.getFkOrganizationId().getOrganizationId());
                List<EmailBlockExternalSource> emailBlockExternalSourceList = emailBlockService.getAllEmailBlockExternalSource(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
                if(emailBlockExternalSourceList != null){
                    EmailBlockExternalSource emailBlockExternalSourceObject = emailBlockExternalSourceList.get(0);
                    emailBlockDetails.setExternalSourceName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceId().getExternalSourceName());
                    emailBlockDetails.setExternalSourceKeywordName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceKeywordId().getExternalSourceKeywordName());
                }
                emailBlockDetailsList.add(emailBlockDetails);
            }
            genericResponse.setDetails(emailBlockDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email blocks retrieved successfully."));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllEmailBlocksById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailBlocksById(@RequestParam("emailBlockId") Integer emailBlockId) {
        GenericResponse<EmailBlockDetails> genericResponse = new GenericResponse<>();
        try
        {
            EmailBlock emailBlock = emailBlockService.getByEmailBlockId(emailBlockId);
            List<EmailBlockDetails> emailBlockDetailsList = new ArrayList<>();
            EmailBlockDetails emailBlockDetails = new EmailBlockDetails();
            emailBlockDetails.setEmailBlockId(emailBlock.getEmailBlockId());
            emailBlockDetails.setEmailBlockName(emailBlock.getEmailBlockName());
            emailBlockDetailsList.add(emailBlockDetails);
            genericResponse.setDetails(emailBlockDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block retrieved successfully."));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
}
