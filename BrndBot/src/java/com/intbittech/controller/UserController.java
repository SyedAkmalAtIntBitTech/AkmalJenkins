/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.enums.AdminStatus;
import com.intbittech.model.Company;
import com.intbittech.model.FranchiseCompanyLookup;
import com.intbittech.model.UserCompanyDetails;
import com.intbittech.model.UserProfile;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.CompanyService;
import com.intbittech.services.ForgotPasswordService;
import com.intbittech.services.FranchiseService;
import com.intbittech.services.UserRoleCompanyLookUpService;
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
    private UserRoleCompanyLookUpService userRoleCompanyLookUpService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private FranchiseService franchiseService;
    
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

            List<UsersRoleCompanyLookup> listUsersRoleCompanyLookup = userRoleCompanyLookUpService.getAllUserRolesByUser(user);

            for (int i = 0; i< listUsersRoleCompanyLookup.size(); i++){

                UsersRoleCompanyLookup usersRoleCompanyLookup = listUsersRoleCompanyLookup.get(i);
                
                UserCompanyDetails userCompanyDetails = new UserCompanyDetails();
                 FranchiseCompanyLookup franchiseCompanyLookup = franchiseService.getFranchiseByCompanyId(usersRoleCompanyLookup.getCompanyId().getCompanyId());
                
                 if (franchiseCompanyLookup != null){
                    userCompanyDetails.setFranchiseId(franchiseCompanyLookup.getFkFranchiseId().getFranchiseId());
                    userCompanyDetails.setFranchiseName(franchiseCompanyLookup.getFkFranchiseId().getFranchiseName());
                    userCompanyDetails.setIsHeadquarter(franchiseCompanyLookup.getIsHeadQuarter());
                 }                 
                userCompanyDetails.setUserId(userId);
                userCompanyDetails.setCompanyId(usersRoleCompanyLookup.getCompanyId().getCompanyId());
                userCompanyDetails.setCompanyName(usersRoleCompanyLookup.getCompanyId().getCompanyName());
                userCompanyDetails.setRoleId(usersRoleCompanyLookup.getRoleId().getUserRoleId());
                userCompanyDetails.setRoleName(AdminStatus.valueOf(usersRoleCompanyLookup.getRoleId().getRoleName()).getDisplayName());
                userCompanyDetails.setAccountStatus(usersRoleCompanyLookup.getAccountStatus());
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

                UsersRoleCompanyLookup userRoleLookUp = userRoleCompanyLookUpService.getUsersRoleLookupByUserAndCompany(user, company);
               
                UserCompanyDetails userCompanyDetails = new UserCompanyDetails();

                userCompanyDetails.setCompanyId(userRoleLookUp.getCompanyId().getCompanyId());
                userCompanyDetails.setCompanyName(userRoleLookUp.getCompanyId().getCompanyName());
                userCompanyDetails.setRoleId(userRoleLookUp.getRoleId().getUserRoleId());
                userCompanyDetails.setRoleName(AdminStatus.valueOf(userRoleLookUp.getRoleId().getRoleName()).getDisplayName());
                userCompanyDetails.setAccountStatus(userRoleLookUp.getAccountStatus());
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
            String accountStatus = userRoleCompanyLookUpService.getStatus(userCompanyDetails);

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
