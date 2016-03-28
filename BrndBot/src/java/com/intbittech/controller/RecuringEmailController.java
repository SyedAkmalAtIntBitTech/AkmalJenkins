/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Organization;
import com.intbittech.model.RecuringEmailTemplate;
import com.intbittech.responsemappers.ContainerResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.intbittech.services.RecuringEmailTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.utility.ErrorHandlingUtil;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import com.intbittech.modelmappers.RecurringEmailDetails;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.intbittech.model.OrganizationRecuringEmailLookup;
import com.intbittech.responsemappers.GenericResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;

        


/**
 *
 * @author Mohammed-Tameem
 */

@RestController
public class RecuringEmailController 
{
      private Logger logger = Logger.getLogger(RecuringEmailController.class);
  
      
      @Autowired
      private RecuringEmailTemplateService recuringEmailTemplateService;
    
    
      @Autowired
      private MessageSource messageSource;
    
     @RequestMapping(value = "saveRecuringEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveRecuringEmail(@RequestBody RecurringEmailDetails recuringEmailDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            RecuringEmailTemplate recuringEmailTemplate =  new RecuringEmailTemplate();
            recuringEmailTemplate.setTemplateName(recuringEmailDetails.getTemplateName());
            recuringEmailTemplate.setHtmlData(recuringEmailDetails.getHtmlData());
            recuringEmailTemplateService.save(recuringEmailTemplate);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_email_created", new String[]{}, Locale.US)));
            

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
        
        @RequestMapping(value = "saveOrganizationRecuringEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveOrganizationRecuringEmail(@RequestBody RecurringEmailDetails recuringEmailDetailsOrg) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
              
             OrganizationRecuringEmailLookup organizationRecuringEmailLookup =new OrganizationRecuringEmailLookup();
             Organization organization = new Organization();
            
             organizationRecuringEmailLookup.setFkOrganizationId(organization);

             RecuringEmailTemplate recuringEmailTemplate=new RecuringEmailTemplate();
             recuringEmailTemplate.setRecuringEmailTemplateId(recuringEmailTemplate.getRecuringEmailTemplateId());
             organizationRecuringEmailLookup.setFkRecuringEmailTemplateId(recuringEmailTemplate);
             organizationRecuringEmailLookup.setOrganizationRecuringEmailLookupId(recuringEmailDetailsOrg.getOrganizationRecuringEmailLookupId());
             
             recuringEmailTemplateService.saveRecuringEmailOrganization(organizationRecuringEmailLookup);
             transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_organization_created", new String[]{}, Locale.US)));
            

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
        
        
         
    @RequestMapping(value = "deleteRecuringEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteRecuringEmail(@RequestParam("recuringEmailTemplateId") Integer recuringEmailTemplateId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            recuringEmailTemplateService.delete(recuringEmailTemplateId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_email_deleted", new String[]{}, Locale.US)));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
    
    
    
     @RequestMapping(value = "deleteOrganizationRecuringEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteOrganizationRecuringEmail(@RequestParam("organizationRecuringEmailLookupId") Integer organizationRecuringEmailLookupId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            recuringEmailTemplateService.delete(organizationRecuringEmailLookupId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_organization_deleted", new String[]{}, Locale.US)));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
   }
    
    
    
     @RequestMapping(value = "updateRecuringEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateRecuringEmail(@RequestParam("recuringEmailTemplateId") Integer recuringEmailTemplateId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            RecuringEmailTemplate recuringEmailTemplate =  new RecuringEmailTemplate();
            recuringEmailTemplate.setRecuringEmailTemplateId(recuringEmailTemplate.getRecuringEmailTemplateId());
            recuringEmailTemplate.setTemplateName(recuringEmailTemplate.getTemplateName());
            recuringEmailTemplate.setHtmlData(recuringEmailTemplate.getHtmlData());
            recuringEmailTemplateService.update(recuringEmailTemplate);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_email_updated", new String[]{}, Locale.US)));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
   }
    
      @RequestMapping(value = "getRecuringEmailTemplateById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getRecuringEmailTemplateById(@RequestParam("recuringEmailTemplateId") Integer recuringEmailTemplateId) {
        GenericResponse<RecurringEmailDetails> genericResponse = new GenericResponse<>();
        try
        {
            RecuringEmailTemplate recuringEmailTemplate = recuringEmailTemplateService.getRecuringEmailTemplateById(recuringEmailTemplateId);
            List<RecurringEmailDetails> recuringEmailDetailsList = new ArrayList<>();
            RecurringEmailDetails recuringEmailDetails = new RecurringEmailDetails();
            recuringEmailDetails.setRecuringEmailTemplateId(recuringEmailTemplate.getRecuringEmailTemplateId());
            recuringEmailDetails.setTemplateName(recuringEmailTemplate.getTemplateName());
            recuringEmailDetails.setHtmlData(recuringEmailTemplate.getHtmlData());
            recuringEmailDetailsList.add(recuringEmailDetails);
            genericResponse.setDetails(recuringEmailDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_email_retreived", new String[]{}, Locale.US)));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
    
    
      @RequestMapping(value = "getAllRecuringByOrganizationId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllRecuringByOrganizationId(@RequestParam("organizationRecuringEmailLookupId") Integer organizationRecuringEmailLookupId) {
        GenericResponse<RecurringEmailDetails> genericResponse = new GenericResponse<>();
        try
        {
            List<RecurringEmailDetails> recuringEmailDetailsList = new ArrayList<>();
            List<OrganizationRecuringEmailLookup> organizationRecuringEmailList = recuringEmailTemplateService.getAllRecuringByOrganizationId(organizationRecuringEmailLookupId);
            for(OrganizationRecuringEmailLookup organizationRecuringEmailLookupObject : organizationRecuringEmailList) {
                RecurringEmailDetails recuringEmailDetails = new RecurringEmailDetails();
                recuringEmailDetails.setOrganizationRecuringEmailLookupId(organizationRecuringEmailLookupObject.getOrganizationRecuringEmailLookupId());
                recuringEmailDetails.setOrganizationId(organizationRecuringEmailLookupObject.getFkOrganizationId().getOrganizationId());
                recuringEmailDetails.setRecuringEmailTemplateId(organizationRecuringEmailLookupObject.getFkRecuringEmailTemplateId().getRecuringEmailTemplateId());
              
               recuringEmailDetailsList.add(recuringEmailDetails);
            }
               
            genericResponse.setDetails(recuringEmailDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_organization_retrived", new String[]{}, Locale.US)));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
    
    
    
    @RequestMapping(value = "getAllNonRecurringEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonRecurringEmail(@RequestParam("recuringEmailTemplateId") Integer recuringEmailTemplateId) {
        GenericResponse<RecurringEmailDetails> genericResponse = new GenericResponse<>();
        try
        {
             List<RecurringEmailDetails> recuringEmailDetailsList = new ArrayList<>();
             List< RecuringEmailTemplate> recuringEmailTemplateList = recuringEmailTemplateService.getAllNonRecuringEmail(recuringEmailTemplateId);
           
             for(RecuringEmailTemplate recuringEmailTemplateObject: recuringEmailTemplateList)  
             {
                    RecurringEmailDetails recuringEmailDetails = new RecurringEmailDetails();
                    recuringEmailDetails.setHtmlData(recuringEmailTemplateObject.getHtmlData());
                    recuringEmailDetails.setRecuringEmailTemplateId(recuringEmailTemplateObject.getRecuringEmailTemplateId());
                    recuringEmailDetailsList.add(recuringEmailDetails);
             }
              genericResponse.setDetails(recuringEmailDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recuring_organization_retrived", new String[]{}, Locale.US)));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
      
    
  }
    
     


  
   

   
    
    

     
    
    

