/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Company;
import com.intbittech.model.SendGridSubUserDetails;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.SendGridSubUserDetailsRequest;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.SendGridSubUserDetailsService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
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
public class SendGridSubUserDetailsController {

    private final static Logger logger = Logger.getLogger(SendGridSubUserDetailsController.class);
    @Autowired
    private SendGridSubUserDetailsService sendGridSubUserDetailsService;

    @RequestMapping(value = "saveSendGridSubUserDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveSendGridSubUserDetails(@RequestBody SendGridSubUserDetailsRequest sendGridSubUserDetailsRequest) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            SendGridSubUserDetails sendGridSubUserDetails = new SendGridSubUserDetails();
            sendGridSubUserDetails.setIps(sendGridSubUserDetailsRequest.getIps());
            sendGridSubUserDetails.setSendGridUserId(sendGridSubUserDetailsRequest.getSendGridUserId());
            Users users = new Users();
            users.setUserId(sendGridSubUserDetailsRequest.getUserId());
            sendGridSubUserDetails.setFkUserId(users);
            Company company = new Company();
            company.setCompanyId(sendGridSubUserDetailsRequest.getCompanyId());
            sendGridSubUserDetails.setFkCompanyId(company);
            sendGridSubUserDetailsService.save(sendGridSubUserDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Send grid sub userDetails created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateSendGridSubUserDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateSendGridSubUserDetails(@RequestBody SendGridSubUserDetailsRequest sendGridSubUserDetailsRequest, @RequestParam("sendGridSubUserDetailsId") Integer sendGridSubUserDetailsId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            SendGridSubUserDetails sendGridSubUserDetails = sendGridSubUserDetailsService.getBySendGridSubUserDetailsId(sendGridSubUserDetailsId);
            sendGridSubUserDetails.setIps(sendGridSubUserDetailsRequest.getIps());
            sendGridSubUserDetails.setSendGridUserId(sendGridSubUserDetailsRequest.getSendGridUserId());
            Users users = new Users();
            users.setUserId(sendGridSubUserDetailsRequest.getUserId());
            sendGridSubUserDetails.setFkUserId(users);
            Company company = new Company();
            company.setCompanyId(sendGridSubUserDetailsRequest.getCompanyId());
            sendGridSubUserDetails.setFkCompanyId(company);
            sendGridSubUserDetailsService.save(sendGridSubUserDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Send grid sub userDetails updated successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteSendGridSubUserDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteSendGridSubUserDetails(@RequestParam("sendGridSubUserDetailsId") Integer sendGridSubUserDetailsId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            sendGridSubUserDetailsService.delete(sendGridSubUserDetailsId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Send grid sub userDetails deleted successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getSendGridSubUserDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getSendGridSubUserDetails(@RequestParam("sendGridSubUserDetailsId") Integer sendGridSubUserDetailsId) {
        GenericResponse<SendGridSubUserDetails> genericResponse = new GenericResponse<>();
        try {

            SendGridSubUserDetails sendGridSubUserDetails = sendGridSubUserDetailsService.getBySendGridSubUserDetailsId(sendGridSubUserDetailsId);
            List<SendGridSubUserDetails> gridSubUserDetailsList = new ArrayList<SendGridSubUserDetails>();
            gridSubUserDetailsList.add(sendGridSubUserDetails);
            genericResponse.setDetails(gridSubUserDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Send grid sub userDetails retrieved successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
}
