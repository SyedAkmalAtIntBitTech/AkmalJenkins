/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;


import com.intbittech.model.EmailBlock;
import com.intbittech.model.EmailBlockExternalSource;
import com.intbittech.model.EmailModel;
import com.intbittech.model.ImageModel;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationEmailBlockLookup;
import com.intbittech.model.PrintModel;
import com.intbittech.model.SubCategoryEmailModel;
import com.intbittech.model.SubCategoryImageModel;
import com.intbittech.model.SubCategoryPrintModel;
import com.intbittech.modelmappers.EmailBlockDetails;
import com.intbittech.modelmappers.EmailModelDetails;
import com.intbittech.modelmappers.ImageModelDetails;
import com.intbittech.modelmappers.OrganizationDetails;
import com.intbittech.modelmappers.PrintModelDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailBlockService;
import com.intbittech.services.EmailModelService;
import com.intbittech.services.ImageModelService;
import com.intbittech.services.PrintModelService;
import com.intbittech.services.SubCategoryEmailModelService;
import com.intbittech.services.SubCategoryImageModelService;
import com.intbittech.services.SubCategoryPrintModelService;
import com.intbittech.utility.ErrorHandlingUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.pool2.PoolUtils;
import org.hibernate.Transaction;
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
 * @author Akmal
 */
@RestController
public class ModelController {
    private Logger logger = Logger.getLogger(ModelController.class);
    
    @Autowired
    private EmailModelService emailModelService;
    
    @Autowired
    private ImageModelService imageModelService;
    
    @Autowired
    private PrintModelService printModelService;
    
    @Autowired
    private SubCategoryEmailModelService subCategoryEmailModelService;
    
    @Autowired
    private SubCategoryPrintModelService subCategoryPrintModelService;
    
    @Autowired
    private SubCategoryImageModelService SubCategoryImageModelService;
    

    
    
  @RequestMapping(value = "getAllEmailModelBySubCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailModelBySubCategory(@RequestParam("subCategoryId") Integer subCategoryId) {
        GenericResponse<EmailModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<SubCategoryEmailModel> subCategoryEmailModelList = subCategoryEmailModelService.getAllSubCategoryEmailModel(subCategoryId);
            List<EmailModelDetails> emailModelDetailsList = new ArrayList<>();
            for (SubCategoryEmailModel emailModelsObject : subCategoryEmailModelList) {
                EmailModelDetails emailModelDetails = new EmailModelDetails();
                emailModelDetails.setEmailModelId(emailModelsObject.getFkEmailModelId().getEmailModelId());
                emailModelDetails.setEmailModelName(emailModelsObject.getFkEmailModelId().getEmailModelName());
                emailModelDetails.setSubCategoryEmailModelId(emailModelsObject.getSubCategoryEmailModelId());
                emailModelDetailsList.add(emailModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email models retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getAllPrintModelBySubCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllPrintModelBySubCategory(@RequestParam("subCategoryId") Integer subCategoryId) {
        GenericResponse<PrintModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<SubCategoryPrintModel> subCategoryPrintModelList = subCategoryPrintModelService.getAllSubCategoryPrintModel(subCategoryId);
            List<PrintModelDetails> printModelDetailsList = new ArrayList<>();
            for (SubCategoryPrintModel printModelsObject : subCategoryPrintModelList) {
                PrintModelDetails printModelDetails = new PrintModelDetails();
                printModelDetails.setPrintModelId(printModelsObject.getFkPrintModelId().getPrintModelId());
                printModelDetails.setPrintModelName(printModelsObject.getFkPrintModelId().getPrintModelName());
                printModelDetails.setSubCategoryPrintModelId(printModelsObject.getSubCategoryPrintModelId());
                printModelDetailsList.add(printModelDetails);
            }

            genericResponse.setDetails(printModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print models retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getAllImageModelBySubCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllImageModelBySubCategory(@RequestParam("subCategoryId") Integer subCategoryId) {
        GenericResponse<ImageModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<SubCategoryImageModel> SubCategoryImageModelList = SubCategoryImageModelService.getAllSubCategoryImageModel(subCategoryId);
            List<ImageModelDetails> imageModelDetailsList = new ArrayList<>();
            for (SubCategoryImageModel imageModelsObject : SubCategoryImageModelList) {
                ImageModelDetails imageModelDetails = new ImageModelDetails();
                imageModelDetails.setImageModelId(imageModelsObject.getFkImageModelId().getImageModelId());
                imageModelDetails.setImageModelName(imageModelsObject.getFkImageModelId().getImageModelName());
                imageModelDetails.setSubCategoryImageModelId(imageModelsObject.getSubCategoryImageModelId());
                imageModelDetailsList.add(imageModelDetails);
            }

            genericResponse.setDetails(imageModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image models retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
     @RequestMapping(value = "getAllEmailModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailModel() {
        GenericResponse<EmailModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailModel> EmailModelList = emailModelService.getAllEmailModel();
            List<EmailModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailModel emailModelObject : EmailModelList) {
                EmailModelDetails emailModelDetails = new EmailModelDetails();
                emailModelDetails.setEmailModelId(emailModelObject.getEmailModelId());
                emailModelDetails.setEmailModelName(emailModelObject.getEmailModelName());
                emailModelDetails.setSubCategoryEmailModelId(emailModelObject.getEmailModelId());
                emailModelDetailsList.add(emailModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All Email models retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
     @RequestMapping(value = "getAllImageModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllImageModel() {
        GenericResponse<ImageModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<ImageModel> ImageModelList = imageModelService.getAllImageModel();
            List<ImageModelDetails> imageModelDetailsList = new ArrayList<>();
            for (ImageModel imageModelObject : ImageModelList) {
                ImageModelDetails imageModelDetails = new ImageModelDetails();
                imageModelDetails.setImageModelId(imageModelObject.getImageModelId());
                imageModelDetails.setImageModelName(imageModelObject.getImageModelName());
                imageModelDetails.setSubCategoryImageModelId(imageModelObject.getImageModelId());
                imageModelDetailsList.add(imageModelDetails);
            }

            genericResponse.setDetails(imageModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All Image models retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
     @RequestMapping(value = "getAllPrintModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllPrintModel() {
        GenericResponse<PrintModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<PrintModel> PrintModelList = printModelService.getAllPrintModel();
            List<PrintModelDetails> printModelDetailsList = new ArrayList<>();
            for (PrintModel printModelObject : PrintModelList) {
                PrintModelDetails PrintModelDetails = new PrintModelDetails();
                PrintModelDetails.setPrintModelId(printModelObject.getPrintModelId());
                PrintModelDetails.setPrintModelName(printModelObject.getPrintModelName());
                PrintModelDetails.setSubCategoryPrintModelId(printModelObject.getPrintModelId());
                printModelDetailsList.add(PrintModelDetails);
            }

            genericResponse.setDetails(printModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All Print models retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
   
}
