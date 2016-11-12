package com.intbittech.controller;

import com.intbittech.externalcontent.MindbodyExternalContentFactory;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.StringUtility;
import com.mindbody.source.RevenueCategory;
import com.mindbody.source.RevenueCategoryResponse;
import com.mindbodyonline.clients.api._0_5.GetLocationsResult;
import com.mindbodyonline.clients.api._0_5.GetProgramsResult;
import com.mindbodyonline.clients.api._0_5.Location;
import com.mindbodyonline.clients.api._0_5.Program;
import com.mindbodyonline.clients.api._0_5.StatusCode;
import com.mindbodyonline.clients.api._0_5Sale.GetServicesResult;
import com.mindbodyonline.clients.api._0_5Sale.Service;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
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
            RevenueCategoryResponse response = externalContentFactory.getRevenueCategories(revenueType);
            if (response.getStatus() == StatusCode.SUCCESS) {
                genericResponse.setDetails((List<RevenueCategory>) response.getRows());  
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success", new String[]{}, Locale.US)));
            } else {
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(response.getMessage()));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    //scheduleType values     ALL, DROP_IN, ENROLLMENT, APPOINTMENT, RESOURCE, MEDIA, ARRIVAL
    @RequestMapping(value = "/getServiceCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getServiceCategory(@RequestParam("companyId") Integer companyId, @RequestParam("scheduleType") String scheduleType,@RequestParam("onlineOnly") String onlineOnly) {
        GenericResponse<Program> genericResponse = new GenericResponse<Program>();
        try {
            MindbodyExternalContentFactory externalContentFactory = new MindbodyExternalContentFactory(companyId);
            GetProgramsResult response = externalContentFactory.getServiceCategories(scheduleType, StringUtility.safeBoolean(onlineOnly));
            if (response.getStatus() == StatusCode.SUCCESS) {
                genericResponse.setDetails((List<Program>) response.getPrograms().getProgram());  
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success", new String[]{}, Locale.US)));
            } else {
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(response.getMessage()));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    //Array of program ids split by |
    @RequestMapping(value = "/getPricingOption", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getPricingOption(@RequestParam("companyId") Integer companyId, @RequestParam("programIds") String programIds) {
        GenericResponse<Service> genericResponse = new GenericResponse<Service>();
        try {
            if (!StringUtility.isEmpty(programIds)) {
                MindbodyExternalContentFactory externalContentFactory = new MindbodyExternalContentFactory(companyId);
                String[] programIdList = programIds.split(Pattern.quote("|"));
                GetServicesResult response = externalContentFactory.getPricingOptions(programIdList);
                if (response.getStatus() == com.mindbodyonline.clients.api._0_5Sale.StatusCode.SUCCESS) {
                    genericResponse.setDetails((List<Service>) response.getServices().getService());
                    genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success", new String[]{}, Locale.US)));
                } else {
                    genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(response.getMessage()));
                }
            } else {
                genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation("No program ids sent"));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/getSiteLocations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getSiteLocations(@RequestParam("companyId") Integer companyId) {
        GenericResponse<Location> genericResponse = new GenericResponse<Location>();
        try {
            MindbodyExternalContentFactory externalContentFactory = new MindbodyExternalContentFactory(companyId);
                GetLocationsResult response = externalContentFactory.getSiteLocations();
                if (response.getStatus() == StatusCode.SUCCESS) {
                    genericResponse.setDetails((List<Location>) response.getLocations().getLocation());
                    genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("data_success", new String[]{}, Locale.US)));
                } else {
                    genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(response.getMessage()));
                }

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
}
