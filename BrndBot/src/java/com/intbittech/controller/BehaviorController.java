package com.intbittech.controller;

import com.intbittech.externalcontent.MindbodyExternalContentFactory;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.utility.ErrorHandlingUtil;
import com.mindbody.source.RevenueCategory;
import com.mindbody.source.RevenueCategoryResponse;
import com.mindbodyonline.clients.api._0_5.StatusCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

/**
 *
 * @author ilyas
 */
@Controller
@RequestMapping(value = "/behavior")
public class BehaviorController {
    
    private final Logger logger = Logger.getLogger(BehaviorController.class);
    
    @Autowired
    private MessageSource messageSource;
    
    //Revenue type can be either 'Service' or 'Product'
    @RequestMapping(value = "/getRevenueCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getRevenueCategory(@RequestParam("companyId") Integer companyId, @RequestParam("revenueType") String revenueType) {
        GenericResponse<RevenueCategory> genericResponse = new GenericResponse<RevenueCategory>();
        try {
            MindbodyExternalContentFactory externalContentFactory = new MindbodyExternalContentFactory(companyId);
            RevenueCategoryResponse revenueCategoryResponse = externalContentFactory.getRevenueCategories(revenueType);
            if (revenueCategoryResponse.getStatus() == StatusCode.SUCCESS) {
                genericResponse.setDetails((List<RevenueCategory>) revenueCategoryResponse.getRows());                
            } else {
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(revenueCategoryResponse.getMessage()));
            }
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("revenueCategory_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/getServiceCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getServiceCategory() {
        GenericResponse<String> genericResponse = new GenericResponse<String>();
        try {
            List<String> serviceCategoryList = new ArrayList<>();
            genericResponse.setDetails(serviceCategoryList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("serviceCategory_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/getPricingOption", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getPricingOption() {
        GenericResponse<String> genericResponse = new GenericResponse<String>();
        try {
            List<String> pricingOptionList = new ArrayList<>();
            genericResponse.setDetails(pricingOptionList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("pricingOption_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/getDollarAmount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getDollarAmount() {
        GenericResponse<String> genericResponse = new GenericResponse<String>();
        try {
            List<String> dollarAmountList = new ArrayList<>();
            genericResponse.setDetails(dollarAmountList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("dollarAmount_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
}
