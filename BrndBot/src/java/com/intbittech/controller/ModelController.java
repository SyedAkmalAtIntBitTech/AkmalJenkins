/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailModel;
import com.intbittech.model.ImageModel;
import com.intbittech.model.PrintModel;
import com.intbittech.model.SubCategory;
import com.intbittech.model.SubCategoryEmailModel;
import com.intbittech.model.SubCategoryImageModel;
import com.intbittech.model.SubCategoryPrintModel;
import com.intbittech.modelmappers.EmailModelDetails;
import com.intbittech.modelmappers.ImageModelDetails;
import com.intbittech.modelmappers.PrintModelDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailModelService;
import com.intbittech.services.ImageModelService;
import com.intbittech.services.PrintModelService;
import com.intbittech.services.SubCategoryEmailModelService;
import com.intbittech.services.SubCategoryImageModelService;
import com.intbittech.services.SubCategoryPrintModelService;
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
import sun.misc.MessageUtils;

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
    private SubCategoryImageModelService subCategoryImageModelService;

    @Autowired
    private MessageSource messageSource;

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
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getAllNonAddedEmailModels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonAddedEmailModels(@RequestParam("subCategoryId") Integer subCategoryId) {
        GenericResponse<EmailModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<EmailModel> emailModelList = emailModelService.getAllNonAddedEmailModels(subCategoryId);
            List<EmailModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailModel emailModelsObject : emailModelList) {
                EmailModelDetails emailModelDetails = new EmailModelDetails();
                emailModelDetails.setEmailModelId(emailModelsObject.getEmailModelId());
                emailModelDetails.setEmailModelName(emailModelsObject.getEmailModelName());
                emailModelDetailsList.add(emailModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getAllNonAddedImageModels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonAddedImageModels(@RequestParam("subCategoryId") Integer subCategoryId) {
        GenericResponse<ImageModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<ImageModel> imageModelList = imageModelService.getAllNonAddedImageModels(subCategoryId);
            List<ImageModelDetails> imageModelDetailsList = new ArrayList<>();
            for (ImageModel imageModelsObject : imageModelList) {
                ImageModelDetails imageModelDetails = new ImageModelDetails();
                imageModelDetails.setImageModelId(imageModelsObject.getImageModelId());
                imageModelDetails.setImageModelName(imageModelsObject.getImageModelName());
                imageModelDetailsList.add(imageModelDetails);
            }

            genericResponse.setDetails(imageModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getAllNonAddedPrintModels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonAddedPrintModels(@RequestParam("subCategoryId") Integer subCategoryId) {
        GenericResponse<PrintModelDetails> genericResponse = new GenericResponse<>();
        try {
            List<PrintModel> printModelList = printModelService.getAllNonAddedPrintModels(subCategoryId);
            List<PrintModelDetails> printModelDetailsList = new ArrayList<>();
            for (PrintModel printModelsObject : printModelList) {
                PrintModelDetails printModelDetails = new PrintModelDetails();
                printModelDetails.setPrintModelId(printModelsObject.getPrintModelId());
                printModelDetails.setPrintModelName(printModelsObject.getPrintModelName());
                printModelDetailsList.add(printModelDetails);
            }

            genericResponse.setDetails(printModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print templates retrieved successfully."));
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
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print templates retrieved successfully."));
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
            List<SubCategoryImageModel> SubCategoryImageModelList = subCategoryImageModelService.getAllSubCategoryImageModel(subCategoryId);
            List<ImageModelDetails> imageModelDetailsList = new ArrayList<>();
            for (SubCategoryImageModel imageModelsObject : SubCategoryImageModelList) {
                ImageModelDetails imageModelDetails = new ImageModelDetails();
                imageModelDetails.setImageModelId(imageModelsObject.getFkImageModelId().getImageModelId());
                imageModelDetails.setImageModelName(imageModelsObject.getFkImageModelId().getImageModelName());
                imageModelDetails.setSubCategoryImageModelId(imageModelsObject.getSubCategoryImageModelId());
                imageModelDetailsList.add(imageModelDetails);
            }

            genericResponse.setDetails(imageModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image templates retrieved successfully."));
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
            List<EmailModel> emailModelList = emailModelService.getAllEmailModel();
            List<EmailModelDetails> emailModelDetailsList = new ArrayList<>();
            for (EmailModel emailModelObject : emailModelList) {
                EmailModelDetails emailModelDetails = new EmailModelDetails();
                emailModelDetails.setEmailModelId(emailModelObject.getEmailModelId());
                emailModelDetails.setEmailModelName(emailModelObject.getEmailModelName());
                emailModelDetails.setImageFileName(emailModelObject.getImageFileName());
                emailModelDetails.setHtmlData(emailModelObject.getHtmlData());
                emailModelDetailsList.add(emailModelDetails);
            }

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All email templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getEmailModelById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getEmailModelById(@RequestParam("emailModelId") Integer emailModelId) {
        GenericResponse<EmailModelDetails> genericResponse = new GenericResponse<>();
        try {
            EmailModel emailModel = emailModelService.getByEmailModelId(emailModelId);
            List<EmailModelDetails> emailModelDetailsList = new ArrayList<>();

            EmailModelDetails emailModelDetails = new EmailModelDetails();
            emailModelDetails.setEmailModelId(emailModel.getEmailModelId());
            emailModelDetails.setEmailModelName(emailModel.getEmailModelName());
            emailModelDetails.setImageFileName(emailModel.getImageFileName());
            emailModelDetails.setHtmlData(emailModel.getHtmlData());
            emailModelDetailsList.add(emailModelDetails);

            genericResponse.setDetails(emailModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email template retrieved successfully."));
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
            List<ImageModel> imageModelList = imageModelService.getAllImageModel();
            List<ImageModelDetails> imageModelDetailsList = new ArrayList<>();
            for (ImageModel imageModelObject : imageModelList) {
                ImageModelDetails imageModelDetails = new ImageModelDetails();
                imageModelDetails.setImageModelId(imageModelObject.getImageModelId());
                imageModelDetails.setImageModelName(imageModelObject.getImageModelName());
                imageModelDetails.setLayoutFileName(imageModelObject.getLayoutFileName());
                imageModelDetails.setModelFileName(imageModelObject.getModelFileName());
                imageModelDetails.setImageFileName(imageModelObject.getImageFileName());
                imageModelDetailsList.add(imageModelDetails);
            }

            genericResponse.setDetails(imageModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All image templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getImageModelById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getImageModelById(@RequestParam("imageModelId") Integer imageModelId) {
        GenericResponse<ImageModelDetails> genericResponse = new GenericResponse<>();
        try {
            ImageModel imageModel = imageModelService.getByImageModelId(imageModelId);
            List<ImageModelDetails> imageModelDetailsList = new ArrayList<>();

            ImageModelDetails imageModelDetails = new ImageModelDetails();
            imageModelDetails.setImageModelId(imageModel.getImageModelId());
            imageModelDetails.setImageModelName(imageModel.getImageModelName());
            imageModelDetails.setLayoutFileName(imageModel.getLayoutFileName());
            imageModelDetails.setModelFileName(imageModel.getModelFileName());
            imageModelDetails.setImageFileName(imageModel.getImageFileName());
            imageModelDetailsList.add(imageModelDetails);

            genericResponse.setDetails(imageModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image template retrieved successfully."));
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
            List<PrintModel> printModelList = printModelService.getAllPrintModel();
            List<PrintModelDetails> printModelDetailsList = new ArrayList<>();
            for (PrintModel printModelObject : printModelList) {
                PrintModelDetails PrintModelDetails = new PrintModelDetails();
                PrintModelDetails.setPrintModelId(printModelObject.getPrintModelId());
                PrintModelDetails.setPrintModelName(printModelObject.getPrintModelName());
                PrintModelDetails.setLayoutFileName(printModelObject.getLayoutFileName());
                PrintModelDetails.setModelFileName(printModelObject.getModelFileName());
                PrintModelDetails.setImageFileName(printModelObject.getImageFileName());
                PrintModelDetails.setSubCategoryPrintModelId(printModelObject.getPrintModelId());
                printModelDetailsList.add(PrintModelDetails);
            }

            genericResponse.setDetails(printModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("All print templates retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getPrintModelById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getPrintModelById(@RequestParam("printModelId") Integer printModelId) {
        GenericResponse<PrintModelDetails> genericResponse = new GenericResponse<>();
        try {
            PrintModel printModel = printModelService.getByPrintModelId(printModelId);
            List<PrintModelDetails> printModelDetailsList = new ArrayList<>();

            PrintModelDetails PrintModelDetails = new PrintModelDetails();
            PrintModelDetails.setPrintModelId(printModel.getPrintModelId());
            PrintModelDetails.setPrintModelName(printModel.getPrintModelName());
            PrintModelDetails.setLayoutFileName(printModel.getLayoutFileName());
            PrintModelDetails.setModelFileName(printModel.getModelFileName());
            PrintModelDetails.setImageFileName(printModel.getImageFileName());
            printModelDetailsList.add(PrintModelDetails);

            genericResponse.setDetails(printModelDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print template retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "saveEmailModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveEmailModel(@RequestBody EmailModelDetails emailModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailModel emailModel = new EmailModel();
            emailModel.setEmailModelName(emailModelDetails.getEmailModelName());
            emailModel.setHtmlData(emailModelDetails.getHtmlData());
            String storableImageFileName = null;
            try {
                storableImageFileName = FileHandlerUtil.saveAdminEmailTemplatesImage(emailModelDetails.getImageFileName(),
                        emailModelDetails.getImageFileData());
            } catch (Throwable throwable) {
                logger.error(throwable);
                throw new ProcessFailed(messageSource.getMessage("image_not_save", null, Locale.US));
            }
            emailModel.setImageFileName(storableImageFileName);
            try {
                emailModelService.save(emailModel);
            } catch (Throwable throwable) {
                //in case if file store into file system but did'nt store in DB.
                if (storableImageFileName != null) {
                    FileHandlerUtil.deleteAdminEmailTemplatesImage(storableImageFileName);
                }
                throw throwable;
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailTemplate_create_sucess", null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "editEmailModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> editEmailModel(@RequestBody EmailModelDetails emailModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailModel emailModel = emailModelService.getByEmailModelId(emailModelDetails.getEmailModelId());
            emailModel.setEmailModelName(emailModelDetails.getEmailModelName());
            emailModel.setHtmlData(emailModelDetails.getHtmlData());
            String oldImageFileName = emailModel.getImageFileName();
            String newImageFileName = emailModelDetails.getImageFileName();
            String storableImageFileName = null;
            if (newImageFileName.length() != 0 && newImageFileName != null) {
                try {
                    storableImageFileName = FileHandlerUtil.saveAdminEmailTemplatesImage(newImageFileName,
                            emailModelDetails.getImageFileData());
                    newImageFileName = storableImageFileName;
                    emailModel.setImageFileName(newImageFileName);
                } catch (Throwable throwable) {
                    logger.error(throwable);
                    throw new ProcessFailed(messageSource.getMessage("image_not_update", null, Locale.US));
                }
            }
            try {
                emailModelService.update(emailModel);
                if(storableImageFileName != null){
                    FileHandlerUtil.deleteAdminEmailTemplatesImage(oldImageFileName);
                }
                
            } catch (Throwable throwable) {
                //in case if file store into file system but did'nt store in DB.
                if (storableImageFileName != null) {
                    //rollback operation for file system.
                    FileHandlerUtil.deleteAdminEmailTemplatesImage(newImageFileName);
                }
                throw throwable;
            }

            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailTemplate_update_sucess", null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteEmailModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteEmailModel(@RequestParam("emailModelId") Integer emailModelId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            EmailModel emailModel = emailModelService.getByEmailModelId(emailModelId);
            emailModelService.delete(emailModelId);
            FileHandlerUtil.deleteAdminEmailTemplatesImage(emailModel.getImageFileName());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("emailTemplate_delete_sucess", null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "saveSubCategoryEmailModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveSubCategoryEmailModel(@RequestBody EmailModelDetails emailModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            SubCategoryEmailModel subCategoryEmailModel = new SubCategoryEmailModel();
            EmailModel emailModel = new EmailModel();
            emailModel.setEmailModelId(emailModelDetails.getEmailModelId());

            SubCategory subCategory = new SubCategory();
            subCategory.setSubCategoryId(emailModelDetails.getSubCategoryId());
            subCategoryEmailModel.setFkSubCategoryId(subCategory);
            subCategoryEmailModel.setFkEmailModelId(emailModel);

            subCategoryEmailModelService.save(subCategoryEmailModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email template related to subcategroy successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteSubCategoryEmailModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteSubCategoryEmailModel(@RequestParam("subCategoryEmailModelID") Integer subCategoryEmailModelID) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            subCategoryEmailModelService.delete(subCategoryEmailModelID);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email template relation to subcategroy deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "saveSubCategoryPrintModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveSubCategoryPrintModel(@RequestBody PrintModelDetails printModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            SubCategoryPrintModel subCategoryPrintModel = new SubCategoryPrintModel();
            PrintModel printModel = new PrintModel();
            SubCategory subCategory = new SubCategory();
            printModel.setPrintModelId(printModelDetails.getPrintModelId());
            subCategory.setSubCategoryId(printModelDetails.getSubCategoryId());
            subCategoryPrintModel.setFkPrintModelId(printModel);
            subCategoryPrintModel.setFkSubCategoryId(subCategory);
            subCategoryPrintModelService.save(subCategoryPrintModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print template related to subcategory successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteSubCategoryPrintModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteSubCategoryPrintModel(@RequestParam("subCategoryPrintModelId") Integer subCategoryPrintModelId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            subCategoryPrintModelService.delete(subCategoryPrintModelId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print template relation to subcategory deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "saveSubCategoryImageModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveSubCategoryImageModel(@RequestBody ImageModelDetails imageModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            SubCategoryImageModel subCategoryImageModel = new SubCategoryImageModel();
            ImageModel imageModel = new ImageModel();
            SubCategory subCategory = new SubCategory();
            imageModel.setImageModelId(imageModelDetails.getImageModelId());
            subCategory.setSubCategoryId(imageModelDetails.getSubCategoryId());
            subCategoryImageModel.setFkImageModelId(imageModel);
            subCategoryImageModel.setFkSubCategoryId(subCategory);
            subCategoryImageModelService.save(subCategoryImageModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image template related to subcategory successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteSubCategoryImageModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteSubCategoryImageModel(@RequestParam("subCategoryImageModelId") Integer subCategoryImageModelId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            subCategoryImageModelService.delete(subCategoryImageModelId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image template relation to subcategory deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "savePrintModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> savePrintModel(@RequestBody PrintModelDetails printModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            PrintModel printlModel = new PrintModel();
            printlModel.setPrintModelName(printModelDetails.getPrintModelName());
            printlModel.setModelFileName(printModelDetails.getModelFileName());
            printlModel.setLayoutFileName(printModelDetails.getLayoutFileName());
            printlModel.setImageFileName(printModelDetails.getImageFileName());
            printModelService.save(printlModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print template created successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "editPrintModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> editPrintModel(@RequestBody PrintModelDetails printModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            PrintModel printlModel = new PrintModel();
            printlModel.setPrintModelId(printModelDetails.getPrintModelId());
            printlModel.setPrintModelName(printModelDetails.getPrintModelName());
            printlModel.setModelFileName(printModelDetails.getModelFileName());
            printlModel.setLayoutFileName(printModelDetails.getLayoutFileName());
            printlModel.setImageFileName(printModelDetails.getImageFileName());
            printModelService.update(printlModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print template updated successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deletePrintModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deletePrintModel(@RequestParam("printModelId") Integer printModelId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            printModelService.delete(printModelId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Print template deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "saveImageModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveImageModel(@RequestBody ImageModelDetails imageModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            ImageModel imageModel = new ImageModel();
            imageModel.setImageModelName(imageModelDetails.getImageModelName());
            imageModel.setLayoutFileName(imageModelDetails.getLayoutFileName());
            imageModel.setModelFileName(imageModelDetails.getModelFileName());
            imageModel.setImageFileName(imageModelDetails.getImageFileName());
            imageModelService.save(imageModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image template created successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateImageModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateImageModel(@RequestBody ImageModelDetails imageModelDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            ImageModel imageModel = new ImageModel();
            imageModel.setImageModelId(imageModelDetails.getImageModelId());
            imageModel.setImageModelName(imageModelDetails.getImageModelName());
            imageModel.setLayoutFileName(imageModelDetails.getLayoutFileName());
            imageModel.setModelFileName(imageModelDetails.getModelFileName());
            imageModel.setImageFileName(imageModelDetails.getImageFileName());
            imageModelService.update(imageModel);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image template updated successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteImageModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteImageModel(@RequestParam("imageModelId") Integer imageModelId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            imageModelService.delete(imageModelId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Image template deleted successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
