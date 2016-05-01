/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.AppConstants;
import com.intbittech.ImageType;
import com.intbittech.model.GlobalColors;
import com.intbittech.model.GlobalFonts;
import com.intbittech.model.GlobalImages;
import com.intbittech.modelmappers.GlobalColorsDetails;
import com.intbittech.modelmappers.GlobalFontsDetails;
import com.intbittech.modelmappers.GlobalImageDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.GlobalColorsService;
import com.intbittech.services.GlobalFontsService;
import com.intbittech.services.GlobalImagesService;
import com.intbittech.utility.DateTimeUtil;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.FileHandlerUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
public class AssestsController {

    private Logger logger = Logger.getLogger(AssestsController.class);

    @Autowired
    private GlobalColorsService globalColorsService;

    @Autowired
    private GlobalFontsService globalFontsService;

    @Autowired
    private GlobalImagesService globalImagesService;

    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "downloadImage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void downloadImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("imageName") String imageName, @RequestParam("imageType") String imageType, @RequestParam("companyId") Integer companyId) {
        try {
        ImageType imageTypeEnum = ImageType.valueOf(imageType);
        String imageBasePath = "";
        switch (imageTypeEnum) {
                case GALLERY:
                    imageBasePath = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId + File.separator + AppConstants.GALLERY_FOLDERNAME;
                    break;
                case COMPANY_LOGO:
                    imageBasePath = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId + File.separator + AppConstants.LOGO_FOLDERNAME;
                    break;
                case GLOBAL_IMAGE:
                    imageBasePath = AppConstants.BASE_ADMIN_GLOBAL_IMAGE_UPLOAD_PATH;
                    break;
                case EMAIL_TEMPLATE_IMAGE:
                    imageBasePath = AppConstants.BASE_ADMIN_EMAIL_TEMPLATE_IMAGE_UPLOAD_PATH;
                    break;
                case EMAIL_BLOCK_TEMPLATE_IMAGE:
                    imageBasePath = AppConstants.BASE_ADMIN_EMAIL_BLOCK_TEMPLATE_IMAGE_UPLOAD_PATH;
                    break;
        }
        String finalImagePath = imageBasePath + File.separator + imageName;
        String contentType = request.getServletContext().getMimeType(imageName);
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "inline;filename=" + imageName);
            File file = new File(finalImagePath);
            response.setContentLength((int) file.length());
            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            try (FileInputStream fileInputStream = new FileInputStream(finalImagePath)) {
                try (OutputStream out = response.getOutputStream()) {
                    int i;
                    while ((i = fileInputStream.read(buf)) >= 0) {
                        out.write(buf, 0, i);
                    }
                    out.flush();
                }
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch(Throwable throwable) {
            logger.error(throwable);
        }
    }
    
    @RequestMapping(value = "getImageList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getImageList(HttpServletRequest request, HttpServletResponse response, @RequestParam("imageType") String imageType, @RequestParam("companyId") Integer companyId) {
        try {
        ImageType imageTypeEnum = ImageType.valueOf(imageType);
        String imageBasePath = "";
//        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
//        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
        switch (imageTypeEnum) {
                case GALLERY:
                    imageBasePath = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId + File.separator + AppConstants.GALLERY_FOLDERNAME;
                    break;
                case GLOBAL_IMAGE:
                    imageBasePath = AppConstants.BASE_ADMIN_GLOBAL_IMAGE_UPLOAD_PATH;
        }
        File imageBaseFolder = new File(imageBasePath);
        JSONArray imageurlJsonArray=new JSONArray();
        for(File imageFile:imageBaseFolder.listFiles()){

            String imageFilePath = "/BrndBot/downloadImage?imageType="+imageTypeEnum+"&imageName="+imageFile.getName()+"&companyId="+companyId;
            JSONObject imagejsonObject= new JSONObject();
            imagejsonObject.put("url", imageFilePath);
            imagejsonObject.put("thumb", imageFilePath);
            imageurlJsonArray.add(imagejsonObject);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(AppConstants.GSON.toJson(imageurlJsonArray));
        } catch(Throwable throwable) {
            logger.error(throwable);
        }
    }

    @RequestMapping(value = "getColorThemeById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColorThemeById(@RequestParam("globalColorsId") Integer globalColorsId) {
        GenericResponse<GlobalColorsDetails> genericResponse = new GenericResponse<>();
        try {
            GlobalColors globalColors = globalColorsService.getGlobalColorsById(globalColorsId);
            List<GlobalColorsDetails> globalColorsDetailsList = new ArrayList<>();
            GlobalColorsDetails globalColorsDetails = new GlobalColorsDetails();
            globalColorsDetails.setGlobalColorsId(globalColors.getGlobalColorsId());
            globalColorsDetails.setColorName(globalColors.getColorName());
            globalColorsDetails.setColor1(globalColors.getColor1());
            globalColorsDetails.setColor2(globalColors.getColor2());
            globalColorsDetails.setColor3(globalColors.getColor3());
            globalColorsDetails.setColor4(globalColors.getColor4());
            globalColorsDetailsList.add(globalColorsDetails);

            genericResponse.setDetails(globalColorsDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalColors_get_id", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getGlobalImageById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getGlobalImageById(@RequestParam("globalImageId") Integer globalImageId) {
        GenericResponse<GlobalImageDetails> genericResponse = new GenericResponse<>();
        try {
            GlobalImages globalImages = globalImagesService.getGlobalImagesById(globalImageId);
            List<GlobalImageDetails> globalImagesDetailsList = new ArrayList<>();
            GlobalImageDetails globalImageDetails = new GlobalImageDetails();
            globalImageDetails.setCreatedDate(globalImages.getCreateDate());
            globalImageDetails.setGlobalImageId(globalImages.getGlobalImagesId());
            globalImageDetails.setImageName(globalImages.getImageName());
            globalImageDetails.setImageData(FileHandlerUtil.getAdminGlobalImageBase64(globalImages.getImageName()));
            globalImagesDetailsList.add(globalImageDetails);
            genericResponse.setDetails(globalImagesDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_get_id", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getFontById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getFontById(@RequestParam("globalFontsId") Integer globalFontsId) {
        GenericResponse<GlobalFontsDetails> genericResponse = new GenericResponse<>();
        try {
            GlobalFonts globalFonts = globalFontsService.getGlobalFontsById(globalFontsId);
            List<GlobalFontsDetails> globalFontsDetailsList = new ArrayList<>();
            GlobalFontsDetails globalFontsDetails = new GlobalFontsDetails();
            globalFontsDetails.setGlobalFontsId(globalFonts.getGlobalFontsId());
            globalFontsDetails.setFontName(globalFonts.getFontName());
            globalFontsDetails.setFontFamilyName(globalFonts.getFontFamilyName());
            globalFontsDetails.setFileName(globalFonts.getFileName());
            globalFontsDetailsList.add(globalFontsDetails);

            genericResponse.setDetails(globalFontsDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalFonts_get_id", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getAllColorThemes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllColorThemes() {
        GenericResponse<GlobalColorsDetails> genericResponse = new GenericResponse<>();
        try {
            List<GlobalColors> globalColorsList = globalColorsService.getAllGlobalColors();
            List<GlobalColorsDetails> globalColorsDetailsList = new ArrayList<>();
            for (GlobalColors globalColorsObject : globalColorsList) {
                GlobalColorsDetails globalColorsDetails = new GlobalColorsDetails();
                globalColorsDetails.setGlobalColorsId(globalColorsObject.getGlobalColorsId());
                globalColorsDetails.setColorName(globalColorsObject.getColorName());
                globalColorsDetails.setColor1(globalColorsObject.getColor1());
                globalColorsDetails.setColor2(globalColorsObject.getColor2());
                globalColorsDetails.setColor3(globalColorsObject.getColor3());
                globalColorsDetails.setColor4(globalColorsObject.getColor4());
                globalColorsDetailsList.add(globalColorsDetails);
            }
            genericResponse.setDetails(globalColorsDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalColors_get_all", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getAllGlobalImages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getGlobalImages() {
        GenericResponse<GlobalImageDetails> genericResponse = new GenericResponse<>();
        try {
            List<GlobalImages> globalImagesList = globalImagesService.getAllGlobalImages();
            List<GlobalImageDetails> globalImagesDetailsList = new ArrayList<>();
            GlobalImageDetails globalImageDetails = null;
            for (GlobalImages globalImagesObject : globalImagesList) {
                globalImageDetails = new GlobalImageDetails();
                globalImageDetails.setCreatedDate(globalImagesObject.getCreateDate());
                globalImageDetails.setGlobalImageId(globalImagesObject.getGlobalImagesId());
                globalImageDetails.setImageName(globalImagesObject.getImageName());
                globalImagesDetailsList.add(globalImageDetails);
            }
            genericResponse.setDetails(globalImagesDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_get_all", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "getAllFonts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllFonts() {
        GenericResponse<GlobalFontsDetails> genericResponse = new GenericResponse<>();
        try {
            List<GlobalFonts> globalFontsList = globalFontsService.getAllGlobalFonts();
            List<GlobalFontsDetails> globalFontsDetailsList = new ArrayList<>();
            for (GlobalFonts globalFontsObject : globalFontsList) {
                GlobalFontsDetails globalFontsDetails = new GlobalFontsDetails();
                globalFontsDetails.setGlobalFontsId(globalFontsObject.getGlobalFontsId());
                globalFontsDetails.setFontName(globalFontsObject.getFontName());
                globalFontsDetails.setFontFamilyName(globalFontsObject.getFontFamilyName());
                globalFontsDetails.setFileName(globalFontsObject.getFileName());
                globalFontsDetailsList.add(globalFontsDetails);
            }
            genericResponse.setDetails(globalFontsDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalFonts_get_all", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "saveFont", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveFont(@RequestBody GlobalFontsDetails globalFontsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            GlobalFonts globalFonts = new GlobalFonts();
            globalFonts.setFontName(globalFontsDetails.getFontName());
            globalFonts.setFontFamilyName(globalFontsDetails.getFontFamilyName());
            globalFonts.setFileName(globalFontsDetails.getFileName());
            String filePathWithFileName=AppConstants.BASE_ADMIN_FONT_UPLOAD_PATH+File.separator+globalFontsDetails.getFileName();
             String storableFileName = null;
             try {
                storableFileName = FileHandlerUtil.saveAdminGlobalFont(filePathWithFileName,
                        globalFontsDetails.getFontType(), globalFontsDetails.getFontData());
            
           
        } catch (Throwable throwable) {
            
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
             throw throwable;
        }
         
            try {
                globalFontsService.save(globalFonts);
            } catch (Throwable throwable) {
                if (storableFileName != null) {
                    FileHandlerUtil.deleteAdminGlobalFont(globalFontsDetails.getFileName());
                }
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalFonts_not_save", null, Locale.US)));
                throw throwable;
            }

            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalFonts_save", null, Locale.US)));
      
  } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
   
    }
    
    
   
    
    
    @RequestMapping(value = "saveColorTheme", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveColorTheme(@RequestBody GlobalColorsDetails globalColorsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            GlobalColors globalColors = new GlobalColors();
            globalColors.setColorName(globalColorsDetails.getColorName());
            globalColors.setColor1(globalColorsDetails.getColor1());
            globalColors.setColor2(globalColorsDetails.getColor2());
            globalColors.setColor3(globalColorsDetails.getColor3());
            globalColors.setColor4(globalColorsDetails.getColor4());
            globalColorsService.save(globalColors);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalColors_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "saveGlobalImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveGlobalImage(@RequestBody GlobalImageDetails globalImageDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            globalImageDetails.setCreatedDate(DateTimeUtil.getCurrentGMTDate());
            GlobalImages globalImages = new GlobalImages();
            globalImages.setCreateDate(globalImageDetails.getCreatedDate());
            String storableFileName = null;
            try {
                storableFileName = FileHandlerUtil.saveAdminGlobalImage(globalImageDetails.getImageName(),
                        globalImageDetails.getImageType(), globalImageDetails.getImageData());
            } catch (Throwable throwable) {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_not_save", null, Locale.US)));
                throw throwable;
            }

            globalImages.setImageName(storableFileName);
            try {
                globalImagesService.save(globalImages);
            } catch (Throwable throwable) {
                if (storableFileName != null) {
                    FileHandlerUtil.deleteAdminGlobalImage(storableFileName);
                }
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_not_save", null, Locale.US)));
                throw throwable;
            }

            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_save", null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateGlobalImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateGlobalImage(@RequestBody GlobalImageDetails globalImageDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            GlobalImages globalImages = globalImagesService.getGlobalImagesById(globalImageDetails.getGlobalImageId());
            String storableFileName = null;

            try {
                storableFileName = FileHandlerUtil.updateAdminGlobalImage(globalImages.getImageName(), globalImageDetails.getImageName(),
                        globalImageDetails.getImageType(), globalImageDetails.getImageData());
            } catch (Throwable throwable) {
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_not_update", null, Locale.US)));
                throw throwable;
            }
            if (storableFileName != null) {
                globalImages.setImageName(storableFileName);
                try {
                    globalImagesService.update(globalImages);
                } catch (Throwable throwable) {
                    transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_not_update", null, Locale.US)));
                    throw throwable;
                }
            }
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_update", null, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateFont", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateFont(@RequestBody GlobalFontsDetails globalFontsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            GlobalFonts globalFonts = new GlobalFonts();
            globalFonts.setGlobalFontsId(globalFontsDetails.getGlobalFontsId());
            globalFonts.setFontName(globalFontsDetails.getFontName());
            globalFonts.setFontFamilyName(globalFontsDetails.getFontFamilyName());
            globalFonts.setFileName(globalFontsDetails.getFileName());
            globalFontsService.update(globalFonts);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalFonts_update", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "updateColorTheme", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateColorTheme(@RequestBody GlobalColorsDetails globalColorsDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            GlobalColors globalColors = new GlobalColors();
            globalColors.setGlobalColorsId(globalColorsDetails.getGlobalColorsId());
            globalColors.setColorName(globalColorsDetails.getColorName());
            globalColors.setColor1(globalColorsDetails.getColor1());
            globalColors.setColor2(globalColorsDetails.getColor2());
            globalColors.setColor3(globalColorsDetails.getColor3());
            globalColors.setColor4(globalColorsDetails.getColor4());
            globalColorsService.update(globalColors);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalColors_update", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteFont", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteFont(@RequestParam("globalFontsId") Integer globalFontsId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            globalFontsService.delete(globalFontsId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalFonts_delete", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteGlobalImage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteGLobalImage(@RequestParam("globalImageId") Integer globalImageId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            globalImagesService.delete(globalImageId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_delete", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "checkGlobalImageUniqueness", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteGLobalImage(@RequestParam("globalImageName") String globalImageName) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            boolean unique = globalImagesService.checkForUniqueness(globalImageName);
            if(unique){
                transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_name_avialable", null, Locale.US)));
            }
            else{
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_name_not_avialable", null, Locale.US)));
            }
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteColorTheme", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteColorTheme(@RequestParam("globalColorsId") Integer globalColorsId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {

            globalColorsService.delete(globalColorsId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalColors_delete", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
}
