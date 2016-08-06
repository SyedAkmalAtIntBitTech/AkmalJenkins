/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Company;
import com.intbittech.model.Franchise;
import com.intbittech.model.UserProfile;
import com.intbittech.modelmappers.FranchiseDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.FranchiseService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
 * @author AR
 */
@RestController

public class FranchiseController {
    
    private Logger logger = Logger.getLogger(FranchiseController.class);

    @Autowired
    private FranchiseService franchiseService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "getAllFranchises", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllFranchises() {
        GenericResponse<Franchise> genericResponse = new GenericResponse<>();
        try {
            List<Franchise> franchises = franchiseService.getAllFranchises();
            genericResponse.setDetails(franchises);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getFranchisesForCompanyId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getFranchisesForCompanyId(@RequestParam("companyId") Integer companyId) {
        GenericResponse<Franchise> genericResponse = new GenericResponse<>();
        try {
            List<Franchise> franchises = franchiseService.getFranchisesForCompanyId(companyId);
            genericResponse.setDetails(franchises);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getCompaniesForFranchiseId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getCompaniesForFranchiseId(@RequestParam("franchiseId") Integer franchiseId) {
        GenericResponse<Company> genericResponse = new GenericResponse<>();
        try {
            List<Company> companys = franchiseService.getCompaniesForFranchises(franchiseId);
            genericResponse.setDetails(companys);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    //This method will be called in the admin of BrndBot. Not in user experience.
    @RequestMapping(value = "activateCompanyAsFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> activateCompanyAsFranchise(@RequestParam("companyId") Integer companyId, @RequestParam("franchiseId") Integer franchiseId) {
                TransactionResponse transactionResponse = new TransactionResponse();
        try {
            franchiseService.activateCompanyAsFranchise(companyId, franchiseId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block created successfully."));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "associateCompanyToFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> associateCompanyToFranchise(@RequestParam("companyId") Integer companyId, @RequestParam("franchiseId") Integer franchiseId) {
                TransactionResponse transactionResponse = new TransactionResponse();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            franchiseService.associateCompanyToFranchise(companyId, userProfile.getUser(), franchiseId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block created successfully."));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "removeCompanyFromFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> removeCompanyFromFranchise(@RequestParam("companyId") Integer companyId, @RequestParam("franchiseId") Integer franchiseId) {
                TransactionResponse transactionResponse = new TransactionResponse();
        try {
            franchiseService.removeCompanyFromFranchise(companyId, franchiseId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block created successfully."));
            
        } catch(Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveFranchise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveFranchise(@RequestBody FranchiseDetails franchiseDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            franchiseService.saveFranchise(franchiseDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Franchise created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateFranchise", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateFranchise(@RequestBody FranchiseDetails franchiseDetails, @RequestParam("franchiseId") Integer franchiseId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            franchiseService.updateFranchise(franchiseDetails, franchiseId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Franchise updated successfully"));

        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteFranchise(@RequestParam("franchiseId") Integer franchiseId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            franchiseService.deleteFranchise(franchiseId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Franchise deleted successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
}
