/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.AppConstants;
import com.intbittech.dao.CompanyPreferencesDao;
import com.intbittech.utility.FileUploadUtil;
import com.intbittech.utility.ServletUtil;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyImages;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.GlobalImages;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.UserProfile;
import com.intbittech.modelmappers.OnBoarding;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyImagesService;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.CompanyService;
import com.intbittech.services.GlobalImagesService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/images")
public class ImagesController {

    private final static Logger logger = Logger.getLogger(ImagesController.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private GlobalImagesService globalImagesService;
    @Autowired
    private CompanyImagesService companyImagesService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyPreferencesService companyPreferencesService;
    @Autowired
    private CompanyPreferencesDao companyPreferencesDao;

    @RequestMapping(value = "/get/{imageId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ContainerResponse> getImageById(HttpServletRequest request, HttpServletResponse response, @RequestParam("companyId") Integer companyId) {
        GenericResponse<CompanyImages> genericResponse = new GenericResponse<>();
        try {
            List<CompanyImages> companyImages = companyImagesService.getAllImagesForCompany(companyId);
            genericResponse.setDetails(companyImages);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ContainerResponse> saveImage(HttpServletRequest request, HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Integer companyId = Integer.parseInt((String) request.getParameter("companyId"));

            String pathSuffix = "";
            String fileName = "";
            String link = "";
            String imageURL = ServletUtil.getServerName(request.getServletContext());
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority authority : userProfile.getAuthorities()) {
                roles.add(authority.getAuthority());
            }
            if (roles.contains("ROLE_ADMIN")) {
                pathSuffix = globalImagesService.getPath();
                fileName = FileUploadUtil.uploadFile(pathSuffix, request);
                link = globalImagesService.getLink(fileName, imageURL);
                GlobalImages globalImages = new GlobalImages();
                globalImages.setImageName(fileName);
                globalImagesService.save(globalImages);
            } else {
                Company company = new Company();
                company.setCompanyId(companyId);
                pathSuffix = companyImagesService.getPath(companyId);
                fileName = FileUploadUtil.uploadFile(pathSuffix, request);
                link = companyImagesService.getLink(fileName, companyId, imageURL);
                CompanyImages companyImages = new CompanyImages();
                companyImages.setImageName(fileName);
                companyImages.setFkCompanyId(company);
                companyImages.setCreatedDate(new Date());
                companyImagesService.save(companyImages);
            }
            transactionResponse.setId(link);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
    public ResponseEntity<ContainerResponse> uploadLogo(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("companyId") Integer companyId) {
        TransactionResponse transactionResponse = new TransactionResponse();

        try {

            String pathSuffix = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH;
            String fileName = "";
            String link = "";
            String imageURL = ServletUtil.getServerName(request.getServletContext());

            pathSuffix = pathSuffix + File.separator + companyId + File.separator + AppConstants.LOGO_FOLDERNAME;

            fileName = FileUploadUtil.uploadLogo(pathSuffix, request);
            Company company = companyService.getCompanyById(companyId);
            OnBoarding onBoarding = companyPreferencesService.getOnBoarding(company);
            onBoarding.setLogo("true");
            onBoarding.setTotal("false");
            companyPreferencesService.setOnBoarding(onBoarding, company);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("globalImages_save", new String[]{}, Locale.US)));
        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

}
