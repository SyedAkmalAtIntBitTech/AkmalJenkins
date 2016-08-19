/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.AppConstants;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailListService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/emaillist")
public class EmailListController {
    
    private final Logger logger = Logger.getLogger(EmailListController.class);
    
    @Autowired
    EmailListService emailListService;
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getEmailList(@RequestParam("emailListName") String emailListName, HttpServletRequest request,
            HttpServletResponse response,@RequestParam("companyId") Integer companyId) {
        GenericResponse<String> transactionResponse = new GenericResponse();
        
        try {
            String data = emailListService.getEmailList(request, companyId, emailListName);
            transactionResponse.addDetail(data);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("list_success", new String[]{}, Locale.US)));
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> setEmailList(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);

            Boolean result = emailListService.setEmailList(requestBodyMap, userCompanyIds.getCompanyId());
            if (result) {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("save_success", new String[]{}, Locale.US)));
            } else {
                logger.debug("Unable to save email list");
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(messageSource.getMessage("save_fail", new String[]{}, Locale.US)));
            }
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
}
