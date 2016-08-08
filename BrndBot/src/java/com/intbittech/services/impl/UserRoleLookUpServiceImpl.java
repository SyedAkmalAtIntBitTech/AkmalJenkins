/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.controller.SignupController;
import com.intbittech.dao.UserRoleLookUpDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategory;
import com.intbittech.model.UserRole;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.model.Users;
import com.intbittech.services.UserRoleLookUpService;
import java.util.List;
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

    private final static Logger logger = Logger.getLogger(UserRoleLookUpServiceImpl.class);

    @Autowired
    private UserRoleLookUpDao usersRoleLookUpDao;
    
    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoleExist(UsersRoleLookup usersRoleLookup) throws ProcessFailed {
        return usersRoleLookUpDao.isRoleExist(usersRoleLookup);
    }
    
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

    @Override
    public UsersRoleLookup getUsersRoleLookupByUserId(Users user) throws ProcessFailed {
        UsersRoleLookup userRoleLookup = usersRoleLookUpDao.getUsersRoleLookupByUserId(user);
        if(userRoleLookup == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
    }
    
    @Override
    public UserRole getUsersRoleByUserId(Users user) throws ProcessFailed {
        UserRole userRole = usersRoleLookUpDao.getUsersRoleByUserId(user);
        if(userRole == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRole;
    }

   /**
     * {@inheritDoc}
     */
    public List<UsersRoleLookup> getAllUserRolesByUserId(Users user) throws ProcessFailed {
        List<UsersRoleLookup> userRoleLookup = usersRoleLookUpDao.getAllUserRolesByUserId(user);
        if (userRoleLookup.isEmpty()) {
            throw new ProcessFailed("No Sub Categories found.");
        }
        return userRoleLookup;
    }    

    @Override
    public void update(UsersRoleLookup usersRoleLookup) throws ProcessFailed {
            usersRoleLookUpDao.update(usersRoleLookup);
    }

    @Override
    public UsersRoleLookup getUsersRoleLookupByUserAndRoleId(Users user, UserRole role) throws ProcessFailed {
        UsersRoleLookup userRoleLookup = usersRoleLookUpDao.getUsersRoleLookupByUserAndRoleId(user, role);
        if(userRoleLookup == null)
        {
            return null;
//             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
        
    }
    
}
