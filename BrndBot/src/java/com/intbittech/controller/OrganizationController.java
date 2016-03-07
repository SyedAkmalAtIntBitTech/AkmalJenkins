/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationType;
import com.intbittech.modelmappers.OrganizationDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.OrganizationService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ilyas
 */
@RestController
public class OrganizationController {

    private static Logger logger = Logger.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "getAllOrganizations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllOrganizations() {
        GenericResponse<OrganizationDetails> genericResponse = new GenericResponse<>();
        try {
            List<Organization> organizationList = organizationService.getAllOrganizations();
            List<OrganizationDetails> organizationDetailsList = new ArrayList<>();
            for (Organization organizationObject : organizationList) {
                OrganizationDetails organizationDetails = new OrganizationDetails();
                organizationDetails.setOrganizationId(organizationObject.getOrganizationId());
                organizationDetails.setOrganizationName(organizationObject.getOrganizationName());
                organizationDetails.setOrganizationTypeId(organizationObject.getFkOrganizationTypeId().getOrganizationTypeId());
                organizationDetails.setOrganizationTypeName(organizationObject.getFkOrganizationTypeId().getOrganizationTypeName());
                organizationDetailsList.add(organizationDetails);
            }

            genericResponse.setDetails(organizationDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Organizations retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "saveOrganization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveOrganization(@RequestBody OrganizationDetails organizationDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Organization organization = new Organization();
            organization.setOrganizationName(organizationDetails.getOrganizationName());
            OrganizationType organizationType = new OrganizationType();
            organizationType.setOrganizationTypeId(organizationDetails.getOrganizationTypeId());
            organization.setFkOrganizationTypeId(organizationType);
            organizationService.save(organization);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Organization created successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateOrganization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateOrganization(@RequestBody OrganizationDetails organizationDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Organization organization = organizationService.getById(organizationDetails.getOrganizationId());
            organization.setOrganizationName(organizationDetails.getOrganizationName());
            OrganizationType organizationType = new OrganizationType();
            organizationType.setOrganizationTypeId(organizationDetails.getOrganizationTypeId());
            organization.setFkOrganizationTypeId(organizationType);
            organizationService.update(organization);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Organization updated successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteOrganization", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteOrganizaion(@RequestParam("organizationId") Integer organizationId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            organizationService.delete(organizationId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Organization deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getOrganizationById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllOrganizationById(@RequestParam("organizationId") Integer organizationId) {
        GenericResponse<OrganizationDetails> genericResponse = new GenericResponse<>();
        try {
            Organization organization = organizationService.getById(organizationId);
            List<OrganizationDetails> organizationDetailsList = new ArrayList<>();

            OrganizationDetails organizationDetails = new OrganizationDetails();
            organizationDetails.setOrganizationId(organization.getOrganizationId());
            organizationDetails.setOrganizationName(organization.getOrganizationName());
            organizationDetails.setOrganizationTypeId(organization.getFkOrganizationTypeId().getOrganizationTypeId());
            organizationDetailsList.add(organizationDetails);

            genericResponse.setDetails(organizationDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Organization retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
}
