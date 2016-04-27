/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbit.AppConstants;
import com.intbit.util.ServletUtil;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.services.EmailModelService;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    ForgotPasswordService forgotPasswordService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private EmailModelService emailModelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String userWelcomePage(ModelMap model) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile.getUsername());
        return "user/dashboard";
    }

    @RequestMapping(value = "/{jspFileName}", method = RequestMethod.GET)
    public String UserJspPages(ModelMap model, @PathVariable(value = "jspFileName") String jspFileName) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile);
        return "user/" + jspFileName;
    }

    @RequestMapping(value = "getLayoutEmailModelById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getLayoutEmailModelById(@RequestParam("emailModelId") Integer emailModelId,
            HttpServletRequest request, HttpServletResponse response) {
        GenericResponse<String> genericResponse = new GenericResponse<>();
        try {
            Map<String, String> requestBodyMap = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String hostURL = ServletUtil.getServerName(request.getServletContext());
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            String html = emailModelService.getLayoutEmail(emailModelId, hostURL, companyId, requestBodyMap);
            genericResponse.addDetail(html);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email template retrieved successfully."));
        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);

    }
}
