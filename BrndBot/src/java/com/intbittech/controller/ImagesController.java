/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbit.FileUploadUtil;
import com.intbit.util.ServletUtil;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyImages;
import com.intbittech.model.GlobalImages;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyImagesService;
import com.intbittech.services.GlobalImagesService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/get/{imageId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ContainerResponse> getImageById(HttpServletRequest request, HttpServletResponse response) {
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ContainerResponse> saveImage(HttpServletRequest request, HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();

        try {
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
                Company company = userProfile.getUser().getFkCompanyId();
                pathSuffix = companyImagesService.getPath(company.getCompanyId());
                fileName = FileUploadUtil.uploadFile(pathSuffix, request);
                link = companyImagesService.getLink(fileName, company, imageURL);
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

}
