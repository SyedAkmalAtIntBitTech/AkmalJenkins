/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.OrganizationMarketingCategoryLookup;
import com.intbittech.modelmappers.MarketingCategoryDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.services.MarketingCategoryService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author intbit
 */
@RestController
public class MarketingController {
    
    private Logger logger = Logger.getLogger(BlockModelController.class);
    
    @Autowired
    private MarketingCategoryService marketingCategoryService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "getAllMarketingCategoryByOrganizationId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllMarketingCategoryByOrganizationId(@RequestParam("organizationId") Integer organizationId) {
        GenericResponse<MarketingCategoryDetails> genericResponse = new GenericResponse<>();
        try {
            List<OrganizationMarketingCategoryLookup> organizationMarketingCategoryList = marketingCategoryService.getByMarketingCategoriesByOrganizationId(organizationId);
            List<MarketingCategoryDetails> marketingCategoryDetailsList = new ArrayList<>();
            for (OrganizationMarketingCategoryLookup organizationMarketingCategoryObject : organizationMarketingCategoryList) {
                MarketingCategoryDetails marketingCategoryDetails = new MarketingCategoryDetails();
                marketingCategoryDetails.setMarketingCategoryId(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryId());
                marketingCategoryDetails.setMarketingCategoryName(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryName());
                marketingCategoryDetailsList.add(marketingCategoryDetails);
            }
            genericResponse.setDetails(marketingCategoryDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_get_all",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
}
