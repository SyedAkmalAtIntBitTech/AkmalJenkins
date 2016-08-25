/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.enums.AdminStatus;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyDetails;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.UserProfile;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.services.UserCompanyLookupService;
import com.intbittech.services.UserRoleLookUpService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.UserSessionUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    private UserCompanyLookupService userCompanyLookupService;
    @Autowired
    private UserRoleLookUpService userRoleLookUpService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CompanyService companyService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String userWelcomePage(ModelMap model) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile.getUsername());
        return "user/loading";
    }
    
    @RequestMapping(value = "/getLoggedInUserId", method = RequestMethod.GET)
    public ResponseEntity<ContainerResponse> getLoggedInUserId(){
        GenericResponse<Integer> genericResponse = new GenericResponse<>();
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        genericResponse.addDetail(userProfile.getUser().getUserId());
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    };
    
    @RequestMapping(value = "/getAllUserCompanyDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllUserCompanyDetails(@RequestParam("userId") Integer userId) {
        GenericResponse<UserCompanyDetails> genericResponse = new GenericResponse<>();
        try{
            Users user = usersService.getUserById(userId);
            List<UserCompanyDetails> listUserCompanyDetails = new ArrayList<UserCompanyDetails>();

            List<UserCompanyLookup> listCompanyLookup = userCompanyLookupService.getAllUserCompaniesByUser(user);

            for (int i = 0; i< listCompanyLookup.size(); i++){

                UserCompanyLookup userCompanyLookup = listCompanyLookup.get(i);
                UsersRoleLookup userRoleLookUp = userRoleLookUpService.getUsersRoleLookupByUserAndCompany(user, userCompanyLookup.getCompanyid());
                
                UserCompanyDetails userCompanyDetails = new UserCompanyDetails();

                userCompanyDetails.setCompanyId(userCompanyLookup.getCompanyid().getCompanyId());
                userCompanyDetails.setCompanyName(userCompanyLookup.getCompanyid().getCompanyName());
                userCompanyDetails.setRoleId(userRoleLookUp.getRoleId().getUserRoleId());
                userCompanyDetails.setRoleName(AdminStatus.valueOf(userRoleLookUp.getRoleId().getRoleName()).getDisplayName());
//                userCompanyDetails.setAccountStatus(userCompanyLookup.getAccountStatus());
                userCompanyDetails.setUserEmailId(user.getUserName());
                userCompanyDetails.setUserFirstName(user.getFirstName());
                userCompanyDetails.setUserLastName(user.getLastName());
                
                listUserCompanyDetails.add(userCompanyDetails);
            }
            genericResponse.setDetails(listUserCompanyDetails);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
            
        }catch (Throwable throwable){
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
            
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getUserCompanyDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getUserCompanyDetails(@RequestParam("userId") Integer userId,@RequestParam("companyId") Integer companyId) {
        GenericResponse<UserCompanyDetails> genericResponse = new GenericResponse<>();
        try{
            Users user = usersService.getUserById(userId);
            Company company = companyService.getCompanyById(companyId);
            List<UserCompanyDetails> listUserCompanyDetails = new ArrayList<UserCompanyDetails>();

                UserCompanyLookup userCompanyLookup = userCompanyLookupService.getUserCompanyLookupByUserAndCompany(user, company);

                UsersRoleLookup userRoleLookUp = userRoleLookUpService.getUsersRoleLookupByUser(user);
                
                UserCompanyDetails userCompanyDetails = new UserCompanyDetails();

                userCompanyDetails.setCompanyId(userCompanyLookup.getCompanyid().getCompanyId());
                userCompanyDetails.setCompanyName(userCompanyLookup.getCompanyid().getCompanyName());
                userCompanyDetails.setRoleId(userRoleLookUp.getRoleId().getUserRoleId());
                userCompanyDetails.setRoleName(AdminStatus.valueOf(userRoleLookUp.getRoleId().getRoleName()).getDisplayName());
//                userCompanyDetails.setAccountStatus(userCompanyLookup.getAccountStatus());
                userCompanyDetails.setUserEmailId(user.getUserName());
                userCompanyDetails.setUserFirstName(user.getFirstName());
                userCompanyDetails.setUserLastName(user.getLastName());
                
                listUserCompanyDetails.add(userCompanyDetails);
            
            genericResponse.setDetails(listUserCompanyDetails);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Success"));
            
        }catch (Throwable throwable){
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
            
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/checkUserCompanyActivation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> checkUserCompanyActivation(@RequestBody UserCompanyDetails userCompanyDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try{
            String accountStatus = userCompanyLookupService.getStatus(userCompanyDetails);

            transactionResponse.setMessage(accountStatus);
            
        }catch (Throwable throwable){
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/{htmlFileName}", method = RequestMethod.GET)
    public String UserJspPages(ModelMap model, @PathVariable(value = "htmlFileName") String htmlFileName) {
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        model.addAttribute("user", userProfile);
//            todochange it with companyid
//        model.addAttribute("companyId", companyId);
        return "user/" + htmlFileName;
    }    
}
