/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;
import com.intbittech.model.EmailBlockModel;
import com.intbittech.model.EmailBlockModelLookup;
import com.intbittech.modelmappers.EmailBlockModelDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailBlockModelLookupService;
import com.intbittech.services.EmailBlockModelService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.FileHandlerUtil;
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
 * @author ilyas
 */
@RestController
public class BlockModelController {
    
    private Logger logger = Logger.getLogger(BlockModelController.class);
    
    @Autowired
    private EmailBlockModelService emailBlockModelService;
    
    
    
    @Autowired
    private EmailBlockModelLookupService emailBlockModelLookupService;
    
     @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "getAllEmailBlockModelById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailBlockModelById(@RequestParam("emailBlockId") Integer emailBlockId) {
        GenericResponse<EmailBlockModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailBlockModelLookup> emailBlockModelLookupList = emailBlockModelLookupService.getAllEmailBlockModel(emailBlockId);
            List<EmailBlockModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailBlockModelLookup emailBlockModelLookupObject : emailBlockModelLookupList) {
                EmailBlockModelDetails emailBlockModelDetails = new EmailBlockModelDetails();
                emailBlockModelDetails.setEmailBlockId(emailBlockModelLookupObject.getFkEmailBlockId().getEmailBlockId());
                emailBlockModelDetails.setEmailBlockModelName(emailBlockModelLookupObject.getFkEmailBlockModelId().getEmailBlockModelName());
                emailBlockModelDetails.setEmailBlockModelLookupId(emailBlockModelLookupObject.getEmailBlockModelLookupId());
                emailModelDetailsList.add(emailBlockModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getAllEmailBlockModelsByBlockId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailBlockModelsByBlockId(@RequestParam("emailBlockId") Integer emailBlockId) {
        GenericResponse<EmailBlockModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailBlockModelLookup> emailBlockModelLookupList = emailBlockModelLookupService.getAllEmailBlockModel(emailBlockId);
            List<EmailBlockModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailBlockModelLookup emailBlockModelLookupObject : emailBlockModelLookupList) {
                EmailBlockModelDetails emailBlockModelDetails = new EmailBlockModelDetails();
                emailBlockModelDetails.setEmailBlockId(emailBlockModelLookupObject.getFkEmailBlockId().getEmailBlockId());
                emailBlockModelDetails.setModelId(emailBlockModelLookupObject.getFkEmailBlockModelId().getEmailBlockModelId());
                emailBlockModelDetails.setEmailBlockModelName(emailBlockModelLookupObject.getFkEmailBlockModelId().getEmailBlockModelName());
                emailBlockModelDetails.setHtmlData(emailBlockModelLookupObject.getFkEmailBlockModelId().getHtmlData());
                emailBlockModelDetails.setImageFileName(emailBlockModelLookupObject.getFkEmailBlockModelId().getImageFileName());
                //TODO Ilyas send image data
                emailBlockModelDetails.setEmailBlockModelLookupId(emailBlockModelLookupObject.getEmailBlockModelLookupId());
                emailModelDetailsList.add(emailBlockModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getAllEmailBlockModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllEmailBlockModel() {
        GenericResponse<EmailBlockModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailBlockModel> emailBlockModelList = emailBlockModelService.getAllEmailBlockModel();
            List<EmailBlockModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailBlockModel emailBlockModelObject : emailBlockModelList) {
                EmailBlockModelDetails emailBlockModelDetails = new EmailBlockModelDetails();
                emailBlockModelDetails.setModelId(emailBlockModelObject.getEmailBlockModelId());
                emailBlockModelDetails.setEmailBlockModelName(emailBlockModelObject.getEmailBlockModelName());
                emailModelDetailsList.add(emailBlockModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "getEmailBlockModelById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getEmailBlockModelById(@RequestParam("emailBlockModelId") Integer emailBlockModelId) {
        GenericResponse<EmailBlockModelDetails> genericResponse = new GenericResponse<>();
        try {
            EmailBlockModel emailBlockModel = emailBlockModelService.getByEmailBlockModelId(emailBlockModelId);
            List<EmailBlockModelDetails> emailBlockModelDetailsList = new ArrayList<>();
            EmailBlockModelDetails emailBlockModelDetails = new EmailBlockModelDetails();
            emailBlockModelDetails.setModelId(emailBlockModel.getEmailBlockModelId());
            emailBlockModelDetails.setEmailBlockModelName(emailBlockModel.getEmailBlockModelName());
            emailBlockModelDetails.setHtmlData(emailBlockModel.getHtmlData());
            emailBlockModelDetails.setImageFileData(FileHandlerUtil.getAdminEmailBlockModelImageBase64(emailBlockModel.getImageFileName()));
            emailBlockModelDetails.setImageFileName(emailBlockModel.getImageFileName());
            emailBlockModelDetailsList.add(emailBlockModelDetails);
            
            genericResponse.setDetails(emailBlockModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block template retrieved successfully."));
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllNonAddedEmailBlockModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonAddedEmailBlockModel(@RequestParam("emailBlockId") Integer emailBlockId) {
        GenericResponse<EmailBlockModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailBlockModel> emailBlockModelList = emailBlockModelService.getAllNonAddedEmailBlockModels(emailBlockId);
            List<EmailBlockModelDetails> emailBlockModelDetailsList = new ArrayList<>();
            for (EmailBlockModel emailModelsObject : emailBlockModelList) {
                EmailBlockModelDetails emailBlockModelDetails = new EmailBlockModelDetails();
                emailBlockModelDetails.setModelId(emailModelsObject.getEmailBlockModelId());
                emailBlockModelDetails.setEmailBlockModelName(emailModelsObject.getEmailBlockModelName());
                emailBlockModelDetailsList.add(emailBlockModelDetails);
            }

            genericResponse.setDetails(emailBlockModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
    
    @RequestMapping(value = "saveBlockModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveBlockModel(@RequestBody EmailBlockModelDetails emailBlockModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailBlockModel emailBlockModel = new EmailBlockModel();
            emailBlockModel.setEmailBlockModelName(emailBlockModelDetails.getEmailBlockModelName());
            emailBlockModel.setHtmlData(emailBlockModelDetails.getHtmlData());
            String storableImageFileName = null;
            try {
                storableImageFileName = FileHandlerUtil.saveAdminEmailBlockModelImage(emailBlockModelDetails.getImageFileName(),
                        emailBlockModelDetails.getImageFileData());
            } catch (Throwable throwable) {
                logger.error(throwable);
                throw new ProcessFailed(messageSource.getMessage("image_not_save", null, Locale.US));
            }
            emailBlockModel.setImageFileName(storableImageFileName);
            try{
                emailBlockModelService.save(emailBlockModel);
            }
            catch (Throwable throwable) {
                //in case if file store into file system but did'nt store in DB.
                if(storableImageFileName != null){
                   FileHandlerUtil.deleteAdminEmailBlockModelImage(storableImageFileName);
                }
                throw throwable;
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailBlock_template_create_sucess", null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveEmailBlockModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveEmailBlockModel(@RequestBody EmailBlockModelDetails emailBlockModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailBlockModelLookup emailBlockModelLookup = new EmailBlockModelLookup();
            EmailBlock emailBlock = new EmailBlock();
            emailBlock.setEmailBlockId(emailBlockModelDetails.getEmailBlockId());
            
            EmailBlockModel emailBlockModel = new EmailBlockModel();
            emailBlockModel.setEmailBlockModelId(emailBlockModelDetails.getModelId());
            emailBlockModelLookup.setFkEmailBlockId(emailBlock);
            emailBlockModelLookup.setFkEmailBlockModelId(emailBlockModel);
            emailBlockModelLookupService.save(emailBlockModelLookup);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block template relation saved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "updateBlockModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateBlockModel(@RequestBody EmailBlockModelDetails emailBlockModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailBlockModel emailBlockModel = emailBlockModelService.getByEmailBlockModelId(emailBlockModelDetails.getModelId());
            
            emailBlockModel.setEmailBlockModelName(emailBlockModelDetails.getEmailBlockModelName());
            emailBlockModel.setHtmlData(emailBlockModelDetails.getHtmlData());
            String oldImageFileName = emailBlockModel.getImageFileName();
            String newImageFileName = emailBlockModelDetails.getImageFileName();
            String storableImageFileName = null;
            if (newImageFileName.length() != 0 && newImageFileName != null) {
                try {
                    storableImageFileName = FileHandlerUtil.saveAdminEmailBlockModelImage(newImageFileName,
                            emailBlockModelDetails.getImageFileData());
                    newImageFileName = storableImageFileName;
                    emailBlockModel.setImageFileName(newImageFileName);
                } catch (Throwable throwable) {
                    logger.error(throwable);
                    throw new ProcessFailed(messageSource.getMessage("image_not_update", null, Locale.US));
                }
            }
            try {
                emailBlockModelService.update(emailBlockModel);
                if(storableImageFileName != null){
                    FileHandlerUtil.deleteAdminEmailBlockModelImage(oldImageFileName);
                }
                
            } catch (Throwable throwable) {
                //in case if file store into file system but did'nt store in DB.
                if (storableImageFileName != null) {
                    //rollback operation for file system.
                    FileHandlerUtil.deleteAdminEmailBlockModelImage(newImageFileName);
                }
                throw throwable;
            }
            
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailBlock_template_update_sucess", null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteBlockModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteBlockModel(@RequestParam("emailBlockModelId") Integer emailBlockModelId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
             EmailBlockModel emailBlockModel = emailBlockModelService.getByEmailBlockModelId(emailBlockModelId);
            emailBlockModelService.delete(emailBlockModelId);
            FileHandlerUtil.deleteAdminEmailBlockModelImage(emailBlockModel.getImageFileName());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailBlock_template_update_sucess",null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "deleteEmailBlockModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteEmailBlockModel(@RequestParam("emailBlockModelLookupId") Integer emailBlockModelLookupId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            
            emailBlockModelLookupService.delete(emailBlockModelLookupId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email block template relation deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    
    
}
