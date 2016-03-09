/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.SubCategory;
import com.intbittech.model.SubCategoryExternalSource;
import com.intbittech.modelmappers.SubCategoryDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.SubCategoryService;
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
 * @author ajit
 */
@Controller
public class SubCategoryController {

    private static Logger logger = Logger.getLogger(SubCategoryController.class);
    @Autowired
    private SubCategoryService subCategoryService;

    @RequestMapping(value = "saveSubCategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveSubCategory(@RequestBody SubCategoryDetails subCategoryDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            subCategoryService.SaveSubCategory(subCategoryDetails);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Sub category created successfully"));
        } catch (Throwable ex) {
            logger.error(ex);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getSubCategoryById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getSubCategoryById(@RequestParam("subCategoryId") Integer subCategoryId) {
        GenericResponse<SubCategoryDetails> genericResponse = new GenericResponse<>();
        try {
            SubCategory subCategory = subCategoryService.getSubCategoryById(subCategoryId);
            List<SubCategoryDetails> subCategoryDetailsList = new ArrayList<>();
            
            SubCategoryDetails subCategoryDetails = new SubCategoryDetails();
            subCategoryDetails.setCategoryId(subCategory.getSubCategoryId());
            subCategoryDetails.setSubCategoryName(subCategory.getSubCategoryName());
            subCategoryDetails.setSubCategoryId(subCategory.getFkCategoryId().getCategoryId());
            subCategoryDetails.setSubCategoryName(subCategory.getFkCategoryId().getCategoryName());
            
            subCategoryDetailsList.add(subCategoryDetails);
            
            genericResponse.setDetails(subCategoryDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Sub Category retrieved successfully."));
            
            
       } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteSubCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteSubCategory(@RequestParam("subCategoryId") Integer subCategoryId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            subCategoryService.delete(subCategoryId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Sub Category deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllSubCategoriesByCategoryId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllSubCategoriesByCategoryId(@RequestParam("categoryId") Integer categoryId) {
        GenericResponse<SubCategoryDetails> genericResponse = new GenericResponse<>();
        try {
            List<SubCategoryExternalSource> subCategoryExternalSourceList = subCategoryService.getAllSubCategoriesByCategoryID(categoryId);
            List<SubCategoryDetails> subCategoryDetailsList = new ArrayList<>();
            for (SubCategoryExternalSource subCategoryExternalSourceObject : subCategoryExternalSourceList)
            {
                SubCategoryDetails subCategoryDetails = new SubCategoryDetails();
                subCategoryDetails.setCategoryId(subCategoryExternalSourceObject.getFkSubCategoryId().getFkCategoryId().getCategoryId());
                subCategoryDetails.setExternalSourceId(subCategoryExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceId().getExternalSourceId());
                subCategoryDetails.setExternalSourceKeywordId(subCategoryExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceKeywordId().getExternalSourceKeywordId());
                subCategoryDetails.setExternalSourceKeywordName(subCategoryExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceKeywordId().getExternalSourceKeywordName());
                subCategoryDetails.setExternalSourceName(subCategoryExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceId().getExternalSourceName());
                subCategoryDetails.setSubCategoryId(subCategoryExternalSourceObject.getFkSubCategoryId().getSubCategoryId());
                subCategoryDetails.setSubCategoryName(subCategoryExternalSourceObject.getFkSubCategoryId().getSubCategoryName());
                subCategoryDetailsList.add(subCategoryDetails);
            }
            
             genericResponse.setDetails(subCategoryDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Sub categories retrieved successfully."));
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
}
