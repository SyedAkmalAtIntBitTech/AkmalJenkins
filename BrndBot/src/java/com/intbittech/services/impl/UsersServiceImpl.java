/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;
import com.intbittech.services.UsersService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersServiceImpl implements UsersService {
    
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private MessageSource messageSource;
    
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
    public Integer save(Users user) throws ProcessFailed {
        return usersDao.save(user);
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
    
    
    
}
