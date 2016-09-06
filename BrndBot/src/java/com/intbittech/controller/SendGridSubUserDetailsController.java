/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Company;
import com.intbittech.model.SendGridSubUserDetails;
import com.intbittech.model.Users;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.SendGridSubUserDetailsService;
import com.intbittech.utility.ErrorHandlingUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ajit @ Intbit
 */
@Controller
public class SendGridSubUserDetailsController {
    
    private final static Logger logger = Logger.getLogger(SendGridSubUserDetailsController.class);
   @Autowired
   private SendGridSubUserDetailsService sendGridSubUserDetailsService;
    @RequestMapping(value = "saveSendGridSubUserDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveSendGridSubUserDetails() {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            SendGridSubUserDetails sendGridSubUserDetails = new SendGridSubUserDetails();
            sendGridSubUserDetails.setSendGridUserId("demo");
            Company company = new Company();
            company.setCompanyId(2);
           sendGridSubUserDetails.setFkCompanyId(company);
           Users user = new Users();
           user.setUserId(1);
           sendGridSubUserDetails.setFkUserId(user);
           sendGridSubUserDetailsService.save(sendGridSubUserDetails);
          transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Send grid sub userDetails created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
}
