/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Company;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ilyas
 */
@RestController
public class OnboardingController {
    
    private Logger logger = Logger.getLogger(OnboardingController.class);
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "isUserUnique",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getUserUnique(@RequestBody UserDetails usersDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Users user = new Users();
            user.setUserName(usersDetails.getUserName());
            Boolean returnMessage = usersService.checkUniqueUser(user);
            transactionResponse.setMessage(returnMessage.toString());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_is_unique",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveUser(@RequestBody UserDetails usersDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Users user = new Users();
            user.setUserName(usersDetails.getUserName());
            user.setUserPassword(usersDetails.getUserPassword());
            user.setCreatedDate(new Date());
            
            Integer returnMessage = usersService.save(user);
            transactionResponse.setMessage(returnMessage.toString());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_save",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveCompany",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCompany(@RequestBody CompanyDetails companyDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
           companyService.saveCompany(companyDetails);
           transactionResponse.setMessage(messageSource.getMessage("company_save", new String[]{}, Locale.US));
           transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_save",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    
}
