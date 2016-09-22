/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.PushedScheduledActionCompanies;
import com.intbittech.model.PushedScheduledEntityList;
import com.intbittech.modelmappers.AssociatedAccountDetails;
import com.intbittech.modelmappers.PushedActionDetails;
import com.intbittech.modelmappers.PushedScheduledActionCompaniesDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.PushedScheduledActionCompaniesService;
import com.intbittech.services.PushedScheduledEntityListService;
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
@RequestMapping(value = "/pushedActions")
public class PushedScheduledActionCompaniesController {

    private final static Logger logger = Logger.getLogger(PushedScheduledActionCompaniesController.class);

    @Autowired
    private PushedScheduledActionCompaniesService pushedScheduledActionCompaniesService;

    @Autowired
    private PushedScheduledEntityListService pushedScheduledEntityListService;

    @RequestMapping(value = "/savePushedScheduledActionCompanies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> savePushedScheduledActionCompanies(@RequestBody PushedScheduledActionCompaniesDetails pushedScheduledActionCompaniesDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            pushedScheduledActionCompaniesService.savePushedScheduledActionCompanies(pushedScheduledActionCompaniesDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Pushed scheduled action companies created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getAllPushedActionForFranchise", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllPushedActionForFranchise(@RequestParam("franchiseId") Integer franchiseId) {
        GenericResponse<PushedActionDetails> genericResponse = new GenericResponse<>();
        try {
            List<PushedActionDetails> pushedActionDetailsList = new ArrayList<>();
            List<PushedScheduledEntityList> pushedScheduledEntityList = pushedScheduledEntityListService.getAllPushedScheduledEntityListIdByFranchiseId(franchiseId);
            for (PushedScheduledEntityList pushedScheduledEntityListObject : pushedScheduledEntityList) {
                PushedActionDetails pushedActionDetails = new PushedActionDetails();
                pushedActionDetails.setPushedActionName(pushedScheduledEntityListObject.getFkScheduledEntityListId().getScheduleTitle());
                pushedActionDetails.setScheduledEntityListId(pushedScheduledEntityListObject.getFkScheduledEntityListId().getScheduledEntityListId());
                pushedActionDetails.setEntityId(pushedScheduledEntityListObject.getFkScheduledEntityListId().getEntityId());
                pushedActionDetails.setPushedactionDateTime(pushedScheduledEntityListObject.getFkScheduledEntityListId().getScheduleTime().getTime());
                pushedActionDetailsList.add(pushedActionDetails);
            }
            genericResponse.setDetails(pushedActionDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All pushed action for franchise retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
     @RequestMapping(value = "/getAllAssociatedAccountForScheduledEntity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllAssociatedAccountForScheduledEntity(@RequestParam("scheduledEntityId") Integer scheduledEntityId) {
        GenericResponse<AssociatedAccountDetails> genericResponse = new GenericResponse<>();
        try {
            List<AssociatedAccountDetails> associatedAccountDetailsList = new ArrayList<>();
            List<PushedScheduledActionCompanies> PushedScheduledActionCompaniesList = pushedScheduledActionCompaniesService.getAllPushedScheduledActionCompaniesByScheduledEntityListId(scheduledEntityId);
              for (PushedScheduledActionCompanies pushedScheduledActionCompanies : PushedScheduledActionCompaniesList) {
                AssociatedAccountDetails associatedAccountDetails = new AssociatedAccountDetails();
                associatedAccountDetails.setCompanyId(pushedScheduledActionCompanies.getFkCompanyId().getCompanyId());
                associatedAccountDetails.setCompanyName(pushedScheduledActionCompanies.getFkCompanyId().getCompanyName());
                associatedAccountDetails.setStatus(pushedScheduledActionCompanies.getStatus());
            }
          
            genericResponse.setDetails(associatedAccountDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All associated account for scheduled entity retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

}
