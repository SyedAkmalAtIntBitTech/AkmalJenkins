/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.GetColorFromImage;
//import com.intbit.AppConstants;
import com.intbittech.AppConstants;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.CompanyColorsDetails;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.modelmappers.CompanyLogoDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.FileHandlerUtil;
import com.intbittech.utility.StringUtility;
import com.mindbodyonline.clients.api._0_5.GetActivationCodeResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import mindbody.controller.MindBodyClass;
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
import org.springframework.security.crypto.password.PasswordEncoder;

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
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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
            Users user = new Users();
            user.setUserName(usersDetails.getUserName());
            user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
            UserRole userRole = new UserRole();
            userRole.setUserRoleId(2);
            user.setFkUserRoleId(userRole);
            user.setCreatedDate(new Date());

            Integer returnMessage = usersService.save(user);
            transactionResponse.setMessage(returnMessage.toString());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_save", new String[]{}, Locale.US)));
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
            Integer companyId = companyService.saveCompany(companyDetails);
            transactionResponse.setMessage(companyId.toString());
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("company_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/onboarding/getColorsForLogo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getColorsForLogo(@RequestParam("companyId") Integer companyId) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {

            GetColorFromImage getcolorsfromimages = new GetColorFromImage();
            //Change to new AppConstants
            String uploadPath = AppConstants.BASE_IMAGE_COMPANY;
            //TODO Get companyId from session after spring security
            uploadPath = uploadPath + File.separator + companyId + File.separator + "logo";
            //TODO set correct file name 
            String FileName = "companylogo.png";
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

    @RequestMapping(value = "/onboarding/getActivationLink", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getActivationLink(@RequestParam("studioId") String studioIdRequest) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            MindBodyClass mind_body_class = null;

            String studioId = studioIdRequest;
            int[] siteIds = new int[]{Integer.parseInt(studioId)};
            mind_body_class = new MindBodyClass(siteIds);

            GetActivationCodeResult result = mind_body_class.getActivationCode();

            ArrayList<String> activationLink = new ArrayList<String>();
            activationLink.add(result.getActivationLink());
            genericResponse.setDetails(activationLink);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("activation_link", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/onboarding/saveCompanyColors", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCompanyColors(@RequestBody CompanyColorsDetails companyColorsDetails, @RequestParam("companyId") Integer companyId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            List<CompanyColorsDetails> companyColorsDetailsList = new ArrayList<>();
            companyColorsDetailsList.add(companyColorsDetails);
            String jsonString = StringUtility.objectListToJsonString(companyColorsDetailsList);
            CompanyPreferences companyPreferences = new CompanyPreferences();
            companyPreferences.setCompanyPreferences(jsonString);
            Company company = new Company();
            company.setCompanyId(companyId);
            companyPreferences.setFkCompanyId(company);

            companyService.saveCompanyPreferences(companyPreferences);
            transactionResponse.setMessage("true");
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_success", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/onboarding/saveCompanyLogo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveCompanyLogo(@RequestBody CompanyLogoDetails companyLogoDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            String storableFileName = null;
            String filePath = AppConstants.BASE_IMAGE_COMPANY + File.separator + companyLogoDetails.getCompanyId().toString() + File.separator + "logo";
            storableFileName = FileHandlerUtil.saveCompanyLogo(filePath,"companylogo","png",companyLogoDetails.getImageData());
            transactionResponse.setMessage(storableFileName);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyLogo_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyLogo_not_save", null, Locale.US)));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
