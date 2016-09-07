/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.UserRoleDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Invite;
import com.intbittech.model.UserRole;
import com.intbittech.services.UserRoleService;
import java.util.Locale;
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
public class UserRoleServiceImpl implements UserRoleService{
    
    @Autowired
    private UserRoleDao userRoleDao;
    
    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean isRoleExist(UserRole userRole) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String save(UserRole userRole) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserRole getUserRoleById(Integer Id) throws ProcessFailed {
        UserRole userRole = userRoleDao.getUserRoleById(Id);
            if(userRole == null)
            {
                 throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
            }
        return userRole;
    }
    
}
