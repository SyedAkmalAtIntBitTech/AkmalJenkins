/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.modelmappers.CompanyAllDetails;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
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
 * @author ilyas
 */
@RestController
public class CompanyController {
    
    private Logger logger = Logger.getLogger(CompanyController.class);
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "getAllCompanies",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllCompanies() {
        GenericResponse<CompanyDetails> genericResponse = new GenericResponse<CompanyDetails>();
        try {
            List<OrganizationCompanyLookup> organizationCompaniesList =  companyService.getAllOrganizationCompanies();
            List<CompanyDetails> companyDetailsList = new ArrayList<>();
            for(OrganizationCompanyLookup organizationCompanyObject : organizationCompaniesList)
            {
                CompanyDetails companyDetails = new CompanyDetails();
                companyDetails.setCompanyId(organizationCompanyObject.getFkCompanyId().getCompanyId());
                companyDetails.setCompanyName(organizationCompanyObject.getFkCompanyId().getCompanyName());
                companyDetails.setOrganizationName(organizationCompanyObject.getFkOrganizationId().getOrganizationName());
                companyDetailsList.add(companyDetails);
            }
            genericResponse.setDetails(companyDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("company_get_all",new String[]{}, Locale.US)));
            
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getCompanyDetailsById",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getCompanyDetailsById(@RequestParam("companyId") Integer companyId) {
        GenericResponse<CompanyAllDetails> genericResponse = new GenericResponse<CompanyAllDetails>();
        try {
            OrganizationCompanyLookup organizationCompany =  companyService.getOrganizationCompanyById(companyId);
            List<CompanyAllDetails> companyDetailsList = new ArrayList<>();
            
            CompanyAllDetails companyAllDetails = new CompanyAllDetails();
            companyAllDetails.setCompanyId(organizationCompany.getFkCompanyId().getCompanyId());
            companyAllDetails.setCompanyName(organizationCompany.getFkCompanyId().getCompanyName());
            companyAllDetails.setOrganizationId(organizationCompany.getFkOrganizationId().getOrganizationId());
            companyAllDetails.setOrganizationName(organizationCompany.getFkOrganizationId().getOrganizationName());
            
            companyDetailsList.add(companyAllDetails);
            
            List<OrganizationCompanyLookup> organizationCompanyDetail = companyService.getAllOrganizationCompanyById(companyId);
            List<Organization> organizationList = new ArrayList<>();
//            for(OrganizationCompanyLookup organizationObject)


            
            
            genericResponse.setDetails(companyDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyDetails_get",new String[]{}, Locale.US)));
            
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
}
