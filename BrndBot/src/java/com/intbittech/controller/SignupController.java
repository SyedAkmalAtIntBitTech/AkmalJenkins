/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.controller.ApplicationContextListener;
import com.intbittech.AppConstants;
import com.intbittech.enums.AdminStatus;
import com.intbittech.model.Company;
import com.intbittech.model.ForgotPassword;
import com.intbittech.model.SendGridSubUserDetails;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.EmailServiceProviderService;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.services.SendGridSubUserDetailsService;
import com.intbittech.services.UserRoleCompanyLookUpService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.Utility;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    private final static Logger logger = Logger.getLogger(SignupController.class);

    @Autowired
    ForgotPasswordService forgotPasswordService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UsersService usersService;
    @Autowired
    SendGridSubUserDetailsService sendGridSubUserDetailsService;
    @Autowired
    UserRoleCompanyLookUpService userRoleCompanyLookUpService;
    @Autowired
    EmailServiceProviderService emailServiceProviderService;

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

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> updateUser(@RequestBody UserDetails usersDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            Users user = usersService.getUserById(usersDetails.getUserId());
            user.setFirstName(usersDetails.getFirstName());
            user.setLastName(usersDetails.getLastName());
            usersService.update(user);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("user_updated", new String[]{}, Locale.US)));
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
            String oldPassword = (String) requestBodyMap.get("currentPassword");
            String hashURL = (String) requestBodyMap.get("hashURL");
            String hashPassword = passwordEncoder.encode(password);
            if (type.equalsIgnoreCase("update")) {
                UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
                
                SendGridSubUserDetails sendGridSubUserDetails = null;
                try {
                    sendGridSubUserDetails = sendGridSubUserDetailsService.getByCompanyId(userCompanyIds.getCompanyId());
                } catch (Throwable throwable) {
                    Users user = new Users();
                    user.setUserId(userCompanyIds.getUserId());
                    Company company = new Company();
                    company.setCompanyId(userCompanyIds.getCompanyId());
                    UsersRoleCompanyLookup usersRoleCompanyLookup = userRoleCompanyLookUpService.getUsersRoleLookupByUserAndCompany(user, company);
                    if (usersRoleCompanyLookup.getRoleId().getRoleName().equals(AdminStatus.ROLE_ACCOUNT_OWNER.toString())) {
                        //create subuser

                        Users userObject = usersService.getUserById(userCompanyIds.getUserId());
                        UserDetails userDetails = new UserDetails();
                        userDetails.setUserName(userObject.getUserName());
                        userDetails.setUserPassword(password);

                        usersService.saveSubUser(userDetails, userCompanyIds.getUserId(), userCompanyIds.getCompanyId());
                        
                    }
                    forgotPasswordService.updatePassword(userCompanyIds.getUserId(), hashPassword);
                }
                if(sendGridSubUserDetails != null) {
                    if(sendGridSubUserDetails.getFkUserId().getUserId() == userCompanyIds.getUserId()) {
                        //Reset subUser password in sendGrid
                        //Commented will be used later
//                        emailServiceProviderService.changePassword(sendGridSubUserDetails.getSendGridUserId(), password, oldPassword);
                        
                    }
                    forgotPasswordService.updatePassword(userCompanyIds.getUserId(), hashPassword);
                    
                }
                
                
                
            } else if (type.equalsIgnoreCase("change")) {
                ForgotPassword forgotPassword = forgotPasswordService.getByRandomHash(hashURL);
                forgotPasswordService.updatePassword(forgotPassword.getFkUserId().getUserId(), hashPassword);
                forgotPasswordService.delete(forgotPassword);
            }

            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("signup_passwordchangesuccessfully", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
}
