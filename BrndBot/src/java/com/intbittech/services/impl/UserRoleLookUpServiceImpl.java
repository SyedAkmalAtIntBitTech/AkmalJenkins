/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.controller.SignupController;
import com.intbittech.dao.UserRoleLookUpDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UserRoleLookup;
import com.intbittech.services.UserRoleLookUpService;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UserRoleLookUpServiceImpl implements UserRoleLookUpService{

    private final static Logger logger = Logger.getLogger(SignupController.class);

    @Autowired
    private UserRoleLookUpDao usersRoleLookUpDao;
    
    @Autowired
    private MessageSource messageSource;
    
    @Override
    public String save(UserRoleLookup usersRoleLookup) throws ProcessFailed {
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
        UserRoleLookup userRoleLookup = usersRoleLookUpDao.getUsersRoleLookupById(id);
        if(userRoleLookup == null){
            throw new ProcessFailed("No user role found with id " + id + ".");
        }
         usersRoleLookUpDao.delete(userRoleLookup); 
    }

    @Override
    public UserRoleLookup getUsersRoleLookupById(Integer id) throws ProcessFailed {
        UserRoleLookup userRoleLookup = usersRoleLookUpDao.getUsersRoleLookupById(id);
        if(userRoleLookup == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
    }

    
}
