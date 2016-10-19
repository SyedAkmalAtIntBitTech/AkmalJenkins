/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.EmailList;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.EmailListTagLookup;
import com.intbittech.model.FranchiseEmailListTagLookup;
import com.intbittech.modelmappers.EmailListTagDetails;
import com.intbittech.modelmappers.TagAndEmailListDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailListTagLookupService;
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
import org.springframework.web.bind.annotation.RequestBody;
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
    private EmailListTagLookupService emailListTagLookupService;
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
    
    @RequestMapping(value = "/saveEmailListTag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveEmailListTag(@RequestBody EmailListTagDetails emailListTagDetails){
        TransactionResponse transactionResponse = new TransactionResponse();
        try{
            emailListTagService.saveEmailListTag(emailListTagDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list tag created successfully"));
        }catch (Throwable throwable){
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/saveOrUpdateTagAndEmailList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveOrUpdateTagAndEmailList(@RequestBody TagAndEmailListDetails tagAndEmailListDetails){
        TransactionResponse transactionResponse = new TransactionResponse();
        try{
            EmailListTagLookup emailListTagLookup = new EmailListTagLookup();
            if(tagAndEmailListDetails.getEmailListTagId()!=null) {
                emailListTagLookup.setEmailListTagId(tagAndEmailListDetails.getEmailListTagId());
            }
            EmailListTag emailListTag = new EmailListTag();
            emailListTag.setTagId(tagAndEmailListDetails.getTagId());
            emailListTagLookup.setFkEmailListTagId(emailListTag);
            
            EmailList emailList = new EmailList();
            emailList.setEmailListId(tagAndEmailListDetails.getEmailListId());
            emailListTagLookup.setFkEmailListId(emailList);
            
            emailListTagLookupService.saveOrUpdate(emailListTagLookup);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Tag and email list associated successfully"));
        }catch (Throwable throwable){
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/deleteEmailListTagsForFranchise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteEmailListTagsForFranchise(@RequestParam("franchiseEmailListTagLookupId") Integer franchiseEmailListTagLookupId){
        TransactionResponse transactionResponse = new TransactionResponse();
        try{
            franchiseEmailListTagLookupService.delete(franchiseEmailListTagLookupId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list tag deleted successfully"));
        }catch (Throwable throwable){
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getAllEmailListTagForFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListTagForFranchise(@RequestParam("franchiseId") Integer franchiseId) {
         GenericResponse<FranchiseEmailListTagLookup> genericResponse = new GenericResponse();
        try {
           List<FranchiseEmailListTagLookup> emailListTagList = franchiseEmailListTagLookupService.getAllEmailListTagForFranchise(franchiseId);
            genericResponse.setDetails(emailListTagList);
            
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list tag for franchise retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/getAllEmailListsAndTagsForFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailListsAndTagsForFranchise(@RequestParam("franchiseId") Integer franchiseId, @RequestParam("companyId") Integer companyId) {
         GenericResponse<TagAndEmailListDetails> genericResponse = new GenericResponse();
        try {
           List<TagAndEmailListDetails> emailListAndTagList = franchiseEmailListTagLookupService.getAllEmailListsAndTagsForFranchise(franchiseId, companyId);
            genericResponse.setDetails(emailListAndTagList);
            
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email list tag for franchise retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
}
