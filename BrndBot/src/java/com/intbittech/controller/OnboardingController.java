/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.GetColorFromImage;
import com.intbittech.AppConstants;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.UserProfile;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.modelmappers.CompanyLogoDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.CompanyService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.FileHandlerUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.File;
import java.util.ArrayList;
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
public class OnboardingController {

    private Logger logger = Logger.getLogger(OnboardingController.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private CompanyPreferencesService companyPreferencesService;

    @Autowired
    private MessageSource messageSource;
    
    

    @RequestMapping(value = "/onboarding/isUserUnique", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getUserUnique(@RequestBody UserDetails usersDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Users user = new Users();
            user.setUserName(usersDetails.getUserName());
            Boolean returnMessage = usersService.checkUniqueUser(user);
            transactionResponse.setMessage(returnMessage.toString());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("check_user_is_unique", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/onboarding/saveUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveUser(@RequestBody UserDetails usersDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            String returnMessage = usersService.save(usersDetails);
            transactionResponse.setMessage(returnMessage);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/onboarding/saveStudioId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveStudioId(@RequestParam("studioId") String studioId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyID = userProfile.getUser().getFkCompanyId().getCompanyId();
        
        //save studioId
        Company company = new Company();
        company.setCompanyId(companyID);
        CompanyPreferences companyPreferences = new CompanyPreferences();
        companyPreferences.setCompanyLocation(studioId);
        companyPreferences.setFkCompanyId(company);
        companyPreferencesService.setStudioId(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/onboarding/saveCompany", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCompany(@RequestBody CompanyDetails companyDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            companyDetails.setCompanyId(userProfile.getUser().getFkCompanyId().getCompanyId());
            companyDetails.setUserId(userProfile.getUser().getUserId());
            String returnMessage = companyService.updateCompany(companyDetails);
            transactionResponse.setMessage(returnMessage);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("company_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/onboarding/getColorsForLogo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColorsForLogo() {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {

            GetColorFromImage getcolorsfromimages = new GetColorFromImage();
            //Change to new AppConstants
            String uploadPath = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH;
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            uploadPath = uploadPath + File.separator + companyId + File.separator + "logo";
            String FileName = AppConstants.COMPANY_LOGO_FILENAME;
            String FilePath = uploadPath + File.separator + FileName;
            ArrayList<String> logoColorList = new ArrayList<String>();
            logoColorList = getcolorsfromimages.getColors(FilePath);
            genericResponse.setDetails(logoColorList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("logo_colors", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/onboarding/saveCompanyLogo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCompanyLogo(@RequestBody CompanyLogoDetails companyLogoDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            String storableFileName = null;
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            String filePath = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId + File.separator + "logo";
            storableFileName = FileHandlerUtil.saveCompanyLogo(filePath, AppConstants.COMPANY_LOGO_FILENAME,companyLogoDetails.getImageData());
            transactionResponse.setMessage(storableFileName);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyLogo_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyLogo_not_save", null, Locale.US)));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
