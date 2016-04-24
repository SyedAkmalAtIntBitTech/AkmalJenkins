/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.ApplicationContextListener;
import com.controller.GenerateHashPassword;
import com.intbit.AppConstants;
import com.intbittech.model.ForgotPassword;
import com.intbittech.model.UserProfile;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
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
import util.Utility;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    private Logger logger = Logger.getLogger(SignupController.class);

    @Autowired
    ForgotPasswordService forgotPasswordService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/{jspFileName}", method = RequestMethod.GET)
    public String signUpJspPages(ModelMap model, @PathVariable(value = "jspFileName") String jspFileName) {
        return "signup/" + jspFileName;
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> forgotSendEMail(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            ServletContext servletContext = ApplicationContextListener.getApplicationServletContext();
            String context_real_path = servletContext.getRealPath("");

            String contextPath = Utility.getServerName(context_real_path);

            String email_id = (String) requestBodyMap.get("emailid");

            forgotPasswordService.sendMail(email_id, contextPath);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_pleasecheckmail", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> resetPassword(HttpServletRequest request,
            HttpServletResponse response) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            String type = (String) requestBodyMap.get("type");
            String password = (String) requestBodyMap.get("password");
            String hashURL = (String) requestBodyMap.get("hashURL");
            Integer userId;
            GenerateHashPassword generate_hash_password = new GenerateHashPassword();
            String hashPassword = generate_hash_password.hashPass(password);
            if (type.equalsIgnoreCase("update")) {
                UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
                userId = userProfile.getUser().getUserId();
                forgotPasswordService.updatePassword(userId, hashPassword);
            } else if (type.equalsIgnoreCase("change")) {
                ForgotPassword forgotPassword = forgotPasswordService.getByRandomHash(hashURL);
                forgotPasswordService.updatePassword(forgotPassword.getFkUserId().getUserId(), hashPassword);
            }

            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_passwordchangesuccessfully", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
}
