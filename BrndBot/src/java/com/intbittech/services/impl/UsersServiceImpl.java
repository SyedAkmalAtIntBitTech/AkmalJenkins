/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.google.gson.Gson;
import com.intbittech.controller.SignupController;
import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyInvite;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.modelmappers.TaskDetails;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.services.UsersInviteService;
import com.intbittech.services.UsersRoleLookUpService;
import com.intbittech.services.UsersService;
import com.intbittech.utility.StringUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersServiceImpl implements UsersService {

    private final static Logger logger = Logger.getLogger(SignupController.class);
    
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    UsersInviteService usersInviteService;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersRoleLookUpService usersRoleLookUpService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Users findByUserName(String userName) throws ProcessFailed {
          Users users = usersDao.findByUserName(userName);
        if (users == null) {
            throw new ProcessFailed("No user found.");
        }
        return users;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean checkUniqueUser(Users user) throws ProcessFailed {
        return usersDao.checkUniqueUser(user);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String save(UserDetails usersDetails) throws ProcessFailed {
        String returnMessage = "false";
        Company company = null;Users user = null;
        try {
            //Save temporary company
            company = new Company();
            company.setCompanyName("none");
            Integer companyId = companyDao.save(company);

            user = new Users();
            user.setUserName(usersDetails.getUserName());
            user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
            user.setFirstName(usersDetails.getFirstName());
            user.setLastName(usersDetails.getLastName());

            user.setCreatedDate(new Date());
            Company companyObject = new Company();
            companyObject.setCompanyId(companyId);
            user.setFkCompanyId(companyObject);
            usersDao.save(user);

            returnMessage = "true";
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        } finally {
            company = null;user = null;
        } 
        return returnMessage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String saveUser(UserDetails usersDetails) throws ProcessFailed {
        String returnMessage = "your invite code is expired";
        CompanyInvite companyInvite = null;TaskDetails taskdetails = null;
        ArrayList roles = null;UsersRoleLookup usersRoleLookUp = null;
        
        try {
            companyInvite = usersInviteService.getCompanyInviteByInviteCode(usersDetails.getInvitationCode());
        /** this step checks the validity of the invitation code **/
            boolean validity = isInviteCodeValid(usersDetails.getInvitationCode());
            if (validity){

                Users user = new Users();
                user.setUserName(usersDetails.getUserName());
                user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
                user.setFirstName(usersDetails.getFirstName());
                user.setLastName(usersDetails.getLastName());
                user.setCreatedDate(new Date());
                Company companyObject = new Company();
                companyObject.setCompanyId(usersDetails.getCompanyId());
                user.setFkCompanyId(companyObject);
                Integer id = usersDao.save(user);

                companyInvite.setIsUsed(true);
                usersInviteService.update(companyInvite);
                
                taskdetails = (TaskDetails)StringUtility.stringToObject(companyInvite.getTask());
                
                roles = taskdetails.getRoles();
                for (int i = 0; i< roles.size(); i++){
                    
                    usersRoleLookUp = new UsersRoleLookup();

                    UserRole userRole = new UserRole();
                    userRole.setUserRoleId((Integer)roles.get(i));

                    usersRoleLookUp.setUserId(user);
                    usersRoleLookUp.setRoleId(userRole);
                    usersRoleLookUpService.save(usersRoleLookUp);
                    
                }
                
            }
        
            returnMessage = "saved successfully";
        } catch(Throwable throwable) {
            returnMessage = "problem saving the record";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }finally {
            companyInvite = null;taskdetails = null;roles = null;usersRoleLookUp = null;
        }  
        return returnMessage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Users user) throws ProcessFailed {
        usersDao.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Users getUserById(Integer userId) throws ProcessFailed {
        Users user = usersDao.getUserById(userId);
        if(user == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return user;
    }

    @Override
    public Users getUserByEmailId(String emailId) throws ProcessFailed {
        Users user = usersDao.getUserByEmailId(emailId);
        if(user == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return user;
    }
    
    @Override
    public boolean isInviteCodeValid(String inviteCode)throws ProcessFailed {
            boolean status = false;
        try{
            CompanyInvite companyInvite = usersInviteService.getCompanyInviteByInviteCode(inviteCode);

            Long createdDate = companyInvite.getCreatedDateTime().getTime();
            Long todayDate = new Date().getTime();

            Long Datedifference = createdDate - todayDate;
            
            boolean isUsed = companyInvite.getIsUsed();
            if ((Datedifference <= 172800000) && (!isUsed)){
                status = true;
            }else {
                status = false;
            }
            
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("problem checking the data",new String[]{}, Locale.US));
        }    
            return status;
    }
    
    
}
