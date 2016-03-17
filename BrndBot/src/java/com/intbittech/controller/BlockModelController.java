/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.EmailBlockModel;
import com.intbittech.model.EmailBlockModelLookup;
import com.intbittech.modelmappers.EmailBlockModelDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailBlockModelLookupService;
import com.intbittech.services.EmailBlockModelService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ilyas
 */
public class BlockModelController {
    
    private Logger logger = Logger.getLogger(BlockModelController.class);
    
    @Autowired
    private EmailBlockModelService emailBlockModelService;
    
    
    
    @Autowired
    private EmailBlockModelLookupService emailBlockModelLookupService;
    
    @RequestMapping(value = "getAllEmailBlockModelById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailBlockModelById(@RequestParam("emailBlockId") Integer emailBlockId) {
        GenericResponse<EmailBlockModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailBlockModelLookup> emailBlockModelLookupList = emailBlockModelLookupService.getAllEmailBlockModel(emailBlockId);
            List<EmailBlockModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailBlockModelLookup emailBlockModelLookupObject : emailBlockModelLookupList) {
                EmailBlockModelDetails emailBlockModelDetails = new EmailBlockModelDetails();
                emailBlockModelDetails.setEmailBlockId(emailBlockModelLookupObject.getFkEmailBlockId().getEmailBlockId());
                emailBlockModelDetails.setEmailBlockModelName(emailBlockModelLookupObject.getFkEmailBlockModelId().getEmailBlockModelName());
                emailBlockModelDetails.setEmailBlockModelLookupId(emailBlockModelLookupObject.getEmailBlockModelLookupId());
                emailModelDetailsList.add(emailBlockModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getAllEmailBlockModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailBlockModel() {
        GenericResponse<EmailBlockModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailBlockModel> emailBlockModelList = emailBlockModelService.getAllEmailBlockModel();
            List<EmailBlockModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailBlockModel emailBlockModelObject : emailBlockModelList) {
                EmailBlockModelDetails emailBlockModelDetails = new EmailBlockModelDetails();
                emailBlockModelDetails.setEmailBlockModelId(emailBlockModelObject.getEmailBlockModelId());
                emailBlockModelDetails.setEmailBlockModelName(emailBlockModelObject.getEmailBlockModelName());
                emailModelDetailsList.add(emailBlockModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "saveEmailBlockModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveEmailBlockModel(@RequestBody EmailBlockModelDetails emailBlockModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailBlockModel emailBlockModel = new EmailBlockModel();
            emailBlockModel.setEmailBlockModelName(emailBlockModelDetails.getEmailBlockModelName());
            emailBlockModel.setHtmlData(emailBlockModelDetails.getHtmlData());
            emailBlockModel.setImageFileName(emailBlockModelDetails.getImageFileName());
            emailBlockModelService.save(emailBlockModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block template saved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "updateEmailBlockModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateEmailBlockModel(@RequestBody EmailBlockModelDetails emailBlockModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailBlockModel emailBlockModel = new EmailBlockModel();
            emailBlockModel.setEmailBlockModelId(emailBlockModelDetails.getEmailBlockModelId());
            emailBlockModel.setEmailBlockModelName(emailBlockModelDetails.getEmailBlockModelName());
            emailBlockModel.setHtmlData(emailBlockModelDetails.getHtmlData());
            emailBlockModel.setImageFileName(emailBlockModelDetails.getImageFileName());
            emailBlockModelService.update(emailBlockModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block template updated successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteEmailBlockModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteEmailBlockModel(@RequestParam("emailBlockModelId") Integer emailBlockModelId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            
            emailBlockModelService.delete(emailBlockModelId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block template deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
}
