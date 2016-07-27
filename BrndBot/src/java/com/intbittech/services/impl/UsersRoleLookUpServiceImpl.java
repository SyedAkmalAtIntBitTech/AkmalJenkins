/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.controller.SignupController;
import com.intbittech.dao.UsersInviteDao;
import com.intbittech.dao.UsersRoleLookUpDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyInvite;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.services.UsersRoleLookUpService;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class UsersRoleLookUpServiceImpl implements UsersRoleLookUpService{

    private final static Logger logger = Logger.getLogger(SignupController.class);

    @Autowired
    private UsersRoleLookUpDao usersRoleLookUpDao;
    
    @Autowired
    private MessageSource messageSource;
    
    @Override
    public String save(UsersRoleLookup usersRoleLookup) throws ProcessFailed {
        String returnMessage = "false";
        try {
            usersRoleLookUpDao.save(usersRoleLookup);

            returnMessage = "true";
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }  
        return returnMessage;    
    }

    @Override
    public void delete(Integer id) throws ProcessFailed {
        UsersRoleLookup userRoleLookup = usersRoleLookUpDao.getUsersRoleLookupById(id);
        if(userRoleLookup == null){
            throw new ProcessFailed("No user role found with id " + id + ".");
        }
         usersRoleLookUpDao.delete(userRoleLookup); 
    }

    @Override
    public UsersRoleLookup getUsersRoleLookupById(Integer id) throws ProcessFailed {
        UsersRoleLookup userRoleLookup = usersRoleLookUpDao.getUsersRoleLookupById(id);
        if(userRoleLookup == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
    }

    
}
