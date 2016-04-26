/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.GenerateHashPassword;
import com.intbit.AppConstants;
import com.intbittech.model.CompanyImages;
import com.intbittech.model.ForgotPassword;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyImagesService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/companyImages")
public class ImagesController {

    private final static Logger logger = Logger.getLogger(ImagesController.class);

    @Autowired
    CompanyImagesService companyImagesService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getImages() {
        GenericResponse<CompanyImages> genericResponse = new GenericResponse<>();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            List<CompanyImages> companyImages = companyImagesService.getAllImagesForCompany(companyId);
            genericResponse.setDetails(companyImages);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/delete/{companyImageId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveImage(@PathVariable (value = "companyImageId") Integer companyImageId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            companyImagesService.delete(companyImageId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_passwordchangesuccessfully", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
        
}
