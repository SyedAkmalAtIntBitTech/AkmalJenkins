/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.MarketingAction;
import com.intbittech.model.MarketingCategory;
import com.intbittech.model.MarketingCategoryProgram;
import com.intbittech.model.MarketingProgram;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.model.OrganizationMarketingCategoryLookup;
import com.intbittech.modelmappers.MarketingActionDetails;
import com.intbittech.modelmappers.MarketingActionsObjectDetails;
import com.intbittech.modelmappers.MarketingCategoryDetails;
import com.intbittech.modelmappers.MarketingCategoryProgramDetails;
import com.intbittech.modelmappers.MarketingProgramActionsDetails;
import com.intbittech.modelmappers.MarketingProgramDetails;
import com.intbittech.modelmappers.OrganizationCompanyDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.services.MarketingActionService;
import com.intbittech.services.MarketingCategoryProgramService;
import com.intbittech.services.MarketingCategoryService;
import com.intbittech.services.MarketingProgramService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.StringUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
 * @author intbit
 */
@RestController
public class MarketingController {
    
    private Logger logger = Logger.getLogger(BlockModelController.class);
    
    @Autowired
    private MarketingCategoryService marketingCategoryService;
    
    @Autowired
    private MarketingProgramService marketingProgramService;
    
    @Autowired
    private MarketingActionService marketingActionService;
    
    @Autowired
    private MarketingCategoryProgramService marketingCategoryProgramService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "getMarketingProgramsByCategoryId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getMarketingProgramsByCategoryId(@RequestParam("marketingCategoryId") Integer marketingCategoryId) {
        GenericResponse<MarketingCategoryProgramDetails> genericResponse = new GenericResponse<>();
        try {
            List<MarketingCategoryProgram> marketingCategoryProgramList = marketingCategoryProgramService.getMarketingProgramsByCategoryId(marketingCategoryId);
            List<MarketingCategoryProgramDetails> marketingProgramDetailsList = new ArrayList<>();
            for(MarketingCategoryProgram marketingCategoryProgramObject : marketingCategoryProgramList) {
                MarketingCategoryProgramDetails marketingProgramDetails = new MarketingCategoryProgramDetails();
                marketingProgramDetails.setMarketingProgramId(marketingCategoryProgramObject.getFkMarketingProgram().getMarketingProgramId());
                marketingProgramDetails.setMarketingProgramName(marketingCategoryProgramObject.getFkMarketingProgram().getMarketingProgramName());
                marketingProgramDetails.setMarketingCategoryProgramId(marketingCategoryProgramObject.getMarketingCategoryProgramId());
                marketingProgramDetails.setHtmlData(marketingCategoryProgramObject.getFkMarketingProgram().getHtmlData());
                marketingProgramDetailsList.add(marketingProgramDetails);
            }
            genericResponse.setDetails(marketingProgramDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_get_all",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllNonAddedMarketingPrograms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonAddedMarketingPrograms(@RequestParam("marketingCategoryId") Integer marketingCategoryId) {
        GenericResponse<MarketingProgramDetails> genericResponse = new GenericResponse<>();
        try {
            List<MarketingProgram> marketingProgramList = marketingCategoryProgramService.getAllNonAddedMarketingPrograms(marketingCategoryId);
            List<MarketingProgramDetails> marketingProgramDetailsList = new ArrayList<>();
            for(MarketingProgram marketingProgramObject : marketingProgramList) {
                MarketingProgramDetails marketingProgramDetails = new MarketingProgramDetails();
                marketingProgramDetails.setMarketingProgramId(marketingProgramObject.getMarketingProgramId());
                marketingProgramDetails.setMarketingProgramName(marketingProgramObject.getMarketingProgramName());
                marketingProgramDetailsList.add(marketingProgramDetails);
            }
            genericResponse.setDetails(marketingProgramDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_get_all",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllMarketingCategoryByOrganizationId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllMarketingCategoryByOrganizationId(@RequestParam("organizationId") Integer organizationId) {
        GenericResponse<MarketingCategoryDetails> genericResponse = new GenericResponse<>();
        try {
            List<OrganizationMarketingCategoryLookup> organizationMarketingCategoryList = marketingCategoryService.getByOrganizationId(organizationId);
            List<MarketingCategoryDetails> marketingCategoryDetailsList = new ArrayList<>();
            for (OrganizationMarketingCategoryLookup organizationMarketingCategoryObject : organizationMarketingCategoryList) {
                MarketingCategoryDetails marketingCategoryDetails = new MarketingCategoryDetails();
                marketingCategoryDetails.setMarketingCategoryId(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryId());
                marketingCategoryDetails.setMarketingCategoryName(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryName());
                marketingCategoryDetails.setOrganizationId(organizationMarketingCategoryObject.getFkOrganizationId().getOrganizationId());
                marketingCategoryDetailsList.add(marketingCategoryDetails);
            }
            genericResponse.setDetails(marketingCategoryDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingCategory_get_all",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveMarketingCategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveMarketingCategory(@RequestBody MarketingCategoryDetails marketingCategoryDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            marketingCategoryService.saveMarketingCategory(marketingCategoryDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingCategory_save",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteMarketingCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteMarketingCategory(@RequestParam("marketingCategoryId") Integer marketingCategoryId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            marketingCategoryService.delete(marketingCategoryId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingCategory_delete",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getByMarketingCategoryId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getByMarketingCategoryId(@RequestParam("marketingCategoryId") Integer marketingCategoryId) {
        GenericResponse<MarketingCategoryDetails> genericResponse = new GenericResponse<>();
        try {
            MarketingCategory marketingCategory = marketingCategoryService.getByMarketingCategoryId(marketingCategoryId);
            List<MarketingCategoryDetails> marketingCategoryDetailsList = new ArrayList<>();
            MarketingCategoryDetails marketingCategoryDetails = new MarketingCategoryDetails();
            marketingCategoryDetails.setMarketingCategoryId(marketingCategory.getMarketingCategoryId());
            marketingCategoryDetails.setMarketingCategoryName(marketingCategory.getMarketingCategoryName());
            marketingCategoryDetailsList.add(marketingCategoryDetails);
            genericResponse.setDetails(marketingCategoryDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingCategory_get",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
     @RequestMapping(value = "getAllMarketingPrograms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllMarketingPrograms() {
        GenericResponse<MarketingProgramDetails> genericResponse = new GenericResponse<>();
        try {
            List<MarketingProgram> marketingProgramList = marketingProgramService.getAllMarketingPrograms();
            List<MarketingProgramDetails> marketingProgramDetailsList = new ArrayList<>();
            for(MarketingProgram marketingProgramObject : marketingProgramList) {
                MarketingProgramDetails marketingProgramDetails = new MarketingProgramDetails();
                marketingProgramDetails.setMarketingProgramId(marketingProgramObject.getMarketingProgramId());
                marketingProgramDetails.setMarketingProgramName(marketingProgramObject.getMarketingProgramName());
                marketingProgramDetailsList.add(marketingProgramDetails);
            }
            genericResponse.setDetails(marketingProgramDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_get_all",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveMarketingProgramActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveMarketingProgramActions(@RequestBody MarketingProgramActionsDetails marketingProgramActionsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            marketingProgramService.saveMarketingProgramActions(marketingProgramActionsDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_save",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "updateMarketingProgramActions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateMarketingProgramActions(@RequestBody MarketingProgramActionsDetails marketingProgramActionsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            marketingProgramService.updateMarketingProgramActions(marketingProgramActionsDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_update",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveMarketingCategoryProgram", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveMarketingCategoryProgram(@RequestBody MarketingCategoryProgramDetails marketingCategoryProgramDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            MarketingCategoryProgram marketingCategoryProgram = new MarketingCategoryProgram();
            MarketingCategory marketingCategory = new MarketingCategory();
            marketingCategory.setMarketingCategoryId(marketingCategoryProgramDetails.getMarketingCategoryId());
            
            MarketingProgram marketingProgram = new MarketingProgram();
            marketingProgram.setMarketingProgramId(marketingCategoryProgramDetails.getMarketingProgramId());
            
            marketingCategoryProgram.setFkMarketingCategory(marketingCategory);
            marketingCategoryProgram.setFkMarketingProgram(marketingProgram);
            
            marketingCategoryProgramService.save(marketingCategoryProgram);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_save",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteMarketingCategoryProgram", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteMarketingCategoryProgram(@RequestParam("marketingCategoryProgramId") Integer marketingCategoryProgramId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            marketingCategoryProgramService.delete(marketingCategoryProgramId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_delete",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getMarketingProgramActionsById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getMarketingProgramActionsById(@RequestParam("marketingProgramId") Integer marketingProgramId) {
        GenericResponse<MarketingProgramActionsDetails> genericResponse = new GenericResponse<>();
        try {
            MarketingProgram marketingProgram = marketingProgramService.getByMarketingProgramId(marketingProgramId);
            MarketingAction marketingAction = marketingActionService.getByMarketingActionByProgramId(marketingProgramId);
            List<MarketingProgramActionsDetails> marketingProgramActionsDetailsList = new ArrayList<>();
            MarketingProgramActionsDetails marketingProgramActionsDetails = new MarketingProgramActionsDetails();
            marketingProgramActionsDetails.setMarketingProgramId(marketingProgram.getMarketingProgramId());
            marketingProgramActionsDetails.setMarketingProgramName(marketingProgram.getMarketingProgramName());
            marketingProgramActionsDetails.setHtmlData(marketingProgram.getHtmlData());
            marketingProgramActionsDetails.setMarketingActionId(marketingAction.getMarketingActionId());
            List<MarketingActionsObjectDetails> marketingActionsObjectDetailses = StringUtility.jsonStringToObjectList(marketingAction.getJsonTemplate());
            List<MarketingActionDetails> marketingActions = marketingActionsObjectDetailses.get(0).getActions();
            marketingProgramActionsDetails.setMarketingActions(marketingActions);
            marketingProgramActionsDetailsList.add(marketingProgramActionsDetails);
            genericResponse.setDetails(marketingProgramActionsDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingProgram_get_all",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getCompanyMarketingCategories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getCompanyMarketingCategories() {
        GenericResponse<MarketingCategoryDetails> genericResponse = new GenericResponse<>();
        try {
            //TODO Haider remove this and add companyId from session
            Integer companyId = 1;
            List<OrganizationCompanyLookup> organizationCompanyDetail = new ArrayList<>();
            organizationCompanyDetail = companyService.getAllOrganizationsByCompanyId(companyId);
            List<OrganizationCompanyDetails> organizationCompanyDetailsList = new ArrayList<>();
            Integer organizationCompanySize = 1;
            if(organizationCompanyDetail!=null)
                organizationCompanySize = organizationCompanyDetail.size()+1;
            Integer[] organizationIds = new Integer[organizationCompanySize];
            Integer i =0;
            organizationIds[i++] = 0;
            if(organizationCompanyDetail!=null)
            {
                for(OrganizationCompanyLookup organizationObject : organizationCompanyDetail) {
                    organizationIds[i++] = organizationObject.getFkOrganizationId().getOrganizationId();
                }
            }
            
            List<OrganizationMarketingCategoryLookup> organizationMarketingCategoryList = marketingCategoryService.getByOrganizationIds(organizationIds);
            List<MarketingCategoryDetails> marketingCategoryDetailsList = new ArrayList<>();
            if (organizationMarketingCategoryList != null) {
            for (OrganizationMarketingCategoryLookup organizationMarketingCategoryObject : organizationMarketingCategoryList) {
                MarketingCategoryDetails marketingCategoryDetails = new MarketingCategoryDetails();
                marketingCategoryDetails.setMarketingCategoryId(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryId());
                marketingCategoryDetails.setMarketingCategoryName(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryName());
                marketingCategoryDetails.setOrganizationId(organizationMarketingCategoryObject.getFkOrganizationId().getOrganizationId());
                marketingCategoryDetailsList.add(marketingCategoryDetails);
            }
            }
            
            genericResponse.setDetails(marketingCategoryDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("marketingCategory_get_all",new String[]{}, Locale.US)));
            
            } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
}
