/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Organization;
import com.intbittech.model.RecurringEmailTemplate;
import com.intbittech.responsemappers.ContainerResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.intbittech.services.RecurringEmailTemplateService;
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
import com.intbittech.model.OrganizationRecurringEmailLookup;
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
public class RecurringEmailController 
{
      private Logger logger = Logger.getLogger(RecurringEmailController.class);
  
      
      @Autowired
      private RecurringEmailTemplateService recurringEmailTemplateService;
    
    
      @Autowired
      private MessageSource messageSource;
    
     @RequestMapping(value = "saveRecurringEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveRecurringEmail(@RequestBody RecurringEmailDetails recurringEmailDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            RecurringEmailTemplate recurringEmailTemplate =  new RecurringEmailTemplate();
            recurringEmailTemplate.setTemplateName(recurringEmailDetails.getTemplateName());
            recurringEmailTemplate.setHtmlData(recurringEmailDetails.getHtmlData());
            recurringEmailTemplateService.save(recurringEmailTemplate);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_email_created", new String[]{}, Locale.US)));
            

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
        
        @RequestMapping(value = "saveOrganizationRecurringEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveOrganizationRecurringEmail(@RequestBody RecurringEmailDetails recurringEmailDetailsOrg) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
              
             OrganizationRecurringEmailLookup organizationRecurringEmailLookup =new OrganizationRecurringEmailLookup();
             Organization organization = new Organization();
            
             organizationRecurringEmailLookup.setFkOrganizationId(organization);

             RecurringEmailTemplate recurringEmailTemplate=new RecurringEmailTemplate();
             recurringEmailTemplate.setRecurringEmailTemplateId(recurringEmailTemplate.getRecurringEmailTemplateId());
             organizationRecurringEmailLookup.setFkRecurringEmailTemplateId(recurringEmailTemplate);
             organizationRecurringEmailLookup.setOrganizationRecurringEmailLookupId(recurringEmailDetailsOrg.getOrganizationRecurringEmailLookupId());
             
             recurringEmailTemplateService.saveRecurringEmailOrganization(organizationRecurringEmailLookup);
             transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_organization_created", new String[]{}, Locale.US)));
            

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
        
        
         
    @RequestMapping(value = "deleteRecurringEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteRecurringEmail(@RequestParam("recurringEmailTemplateId") Integer recurringEmailTemplateId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            recurringEmailTemplateService.delete(recurringEmailTemplateId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_email_deleted", new String[]{}, Locale.US)));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
    
    
    
     @RequestMapping(value = "deleteOrganizationRecurringEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteOrganizationRecurringEmail(@RequestParam("organizationRecurringEmailLookupId") Integer organizationRecurringEmailLookupId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            recurringEmailTemplateService.delete(organizationRecurringEmailLookupId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_organization_deleted", new String[]{}, Locale.US)));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
   }
    
    
    
     @RequestMapping(value = "updateRecurringEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateRecurringEmail(@RequestParam("recurringEmailTemplateId") Integer recurringEmailTemplateId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            RecurringEmailTemplate recurringEmailTemplate =  new RecurringEmailTemplate();
            recurringEmailTemplate.setRecurringEmailTemplateId(recurringEmailTemplate.getRecurringEmailTemplateId());
            recurringEmailTemplate.setTemplateName(recurringEmailTemplate.getTemplateName());
            recurringEmailTemplate.setHtmlData(recurringEmailTemplate.getHtmlData());
            recurringEmailTemplateService.update(recurringEmailTemplate);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_email_updated", new String[]{}, Locale.US)));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
   }
    
      @RequestMapping(value = "getRecurringEmailTemplateById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getRecurringEmailTemplateById(@RequestParam("recurringEmailTemplateId") Integer recurringEmailTemplateId) {
        GenericResponse<RecurringEmailDetails> genericResponse = new GenericResponse<>();
        try
        {
            RecurringEmailTemplate recurringEmailTemplate = recurringEmailTemplateService.getRecurringEmailTemplateById(recurringEmailTemplateId);
            List<RecurringEmailDetails> recurringEmailDetailsList = new ArrayList<>();
            RecurringEmailDetails recurringEmailDetails = new RecurringEmailDetails();
            recurringEmailDetails.setRecurringEmailTemplateId(recurringEmailTemplate.getRecurringEmailTemplateId());
            recurringEmailDetails.setTemplateName(recurringEmailTemplate.getTemplateName());
            recurringEmailDetails.setHtmlData(recurringEmailTemplate.getHtmlData());
            recurringEmailDetailsList.add(recurringEmailDetails);
            genericResponse.setDetails(recurringEmailDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_email_retreived", new String[]{}, Locale.US)));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
    
    
      @RequestMapping(value = "getAllRecurringByOrganizationId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllRecurringByOrganizationId(@RequestParam("organizationId") Integer organizationId) {
        GenericResponse<RecurringEmailDetails> genericResponse = new GenericResponse<>();
        try 
        {
            List<RecurringEmailDetails> recurringEmailDetailsList = new ArrayList<>();
            List<OrganizationRecurringEmailLookup> organizationRecurringEmailList = recurringEmailTemplateService.getAllRecurringByOrganizationId(organizationId);
            for(OrganizationRecurringEmailLookup organizationRecurringEmailLookupObject : organizationRecurringEmailList) {
                RecurringEmailDetails recurringEmailDetails = new RecurringEmailDetails();
                recurringEmailDetails.setOrganizationRecurringEmailLookupId(organizationRecurringEmailLookupObject.getOrganizationRecurringEmailLookupId());
                recurringEmailDetails.setOrganizationId(organizationRecurringEmailLookupObject.getFkOrganizationId().getOrganizationId());
                recurringEmailDetails.setRecurringEmailTemplateId(organizationRecurringEmailLookupObject.getFkRecurringEmailTemplateId().getRecurringEmailTemplateId());
                recurringEmailDetails.setTemplateName(organizationRecurringEmailLookupObject.getFkRecurringEmailTemplateId().getTemplateName());
               recurringEmailDetailsList.add(recurringEmailDetails);
            }
               
            genericResponse.setDetails(recurringEmailDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_organization_retrived", new String[]{}, Locale.US)));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
    
    
    
    @RequestMapping(value = "getAllNonRecurringEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonRecurringEmail(@RequestParam("recurringEmailTemplateId") Integer recurringEmailTemplateId) {
        GenericResponse<RecurringEmailDetails> genericResponse = new GenericResponse<>();
        try
        {
             List<RecurringEmailDetails> recurringEmailDetailsList = new ArrayList<>();
             List< RecurringEmailTemplate> recurringEmailTemplateList = recurringEmailTemplateService.getAllNonRecurringEmail(recurringEmailTemplateId);
           
             for(RecurringEmailTemplate recurringEmailTemplateObject: recurringEmailTemplateList)  
             {
                    RecurringEmailDetails recurringEmailDetails = new RecurringEmailDetails();
                    recurringEmailDetails.setHtmlData(recurringEmailTemplateObject.getHtmlData());
                    recurringEmailDetails.setRecurringEmailTemplateId(recurringEmailTemplateObject.getRecurringEmailTemplateId());
                    recurringEmailDetails.setTemplateName(recurringEmailTemplateObject.getTemplateName());
                    recurringEmailDetailsList.add(recurringEmailDetails);
             }
              genericResponse.setDetails(recurringEmailDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("recurring_organization_retrived", new String[]{}, Locale.US)));
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse),HttpStatus.ACCEPTED);
    }
      
    
  }
    
     


  
   

   
    
    

     
    
    

