/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.GetColorFromImage;
import com.intbit.AppConstants;
import com.intbittech.model.Company;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.modelmappers.GlobalColorsDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.mindbodyonline.clients.api._0_5.GetActivationCodeResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import mindbody.controller.MindBodyClass;
import org.apache.commons.lang3.RandomStringUtils;
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
public class OnboardingController {
    
    private Logger logger = Logger.getLogger(OnboardingController.class);
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = "isUserUnique",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getUserUnique(@RequestBody UserDetails usersDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Users user = new Users();
            user.setUserName(usersDetails.getUserName());
            Boolean returnMessage = usersService.checkUniqueUser(user);
            transactionResponse.setMessage(returnMessage.toString());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_is_unique",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveUser(@RequestBody UserDetails usersDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Users user = new Users();
            user.setUserName(usersDetails.getUserName());
            user.setUserPassword(usersDetails.getUserPassword());
            user.setCreatedDate(new Date());
            
            Integer returnMessage = usersService.save(user);
            transactionResponse.setMessage(returnMessage.toString());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_save",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "saveCompany",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCompany(@RequestBody CompanyDetails companyDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
           companyService.saveCompany(companyDetails);
           transactionResponse.setMessage(messageSource.getMessage("company_save", new String[]{}, Locale.US));
           transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_save",new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getColorsForLogo",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColorsForLogo(@RequestParam("userId") Integer userIdRequest) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            
            GetColorFromImage getcolorsfromimages = new GetColorFromImage();
            //Change to new AppConstants
            String uploadPath = AppConstants.USER_LOGO;
            //TODO Get userId from session after spring security
            Integer userId = userIdRequest;
            uploadPath = uploadPath + File.separator + userId + File.separator + "logo";
            //TODO set correct file name 
            String FileName = "logo.png_1";
            String FilePath = uploadPath + File.separator + FileName;
            ArrayList<String> logoColorList = new ArrayList<String>();
            logoColorList = getcolorsfromimages.getColors(FilePath);
            genericResponse.setDetails(logoColorList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("logo_colors",new String[]{}, Locale.US)));
            
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getActivationLink",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getActivationLink(@RequestParam("studioid") String studioidRequest) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            MindBodyClass mind_body_class = null;
            
            String studioId = studioidRequest;
                    int[] siteIds = new int[]{Integer.parseInt(studioId)};
                    mind_body_class = new MindBodyClass(siteIds);

                    GetActivationCodeResult result = mind_body_class.getActivationCode();
            
            ArrayList<String> activationLink = new ArrayList<String>();
            activationLink.add(result.getActivationLink());
            genericResponse.setDetails(activationLink);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("activation_link",new String[]{}, Locale.US)));
            
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
}
