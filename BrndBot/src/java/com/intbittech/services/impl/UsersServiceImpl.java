/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.UsersDao;
import org.apache.log4j.Logger;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;
import com.intbittech.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersServiceImpl implements UsersService{
       private static Logger logger = Logger.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersDao usersDao;

    @Override
    public Users findByUserName(String userName) throws ProcessFailed {
          Users users = usersDao.findByUserName(userName);
        if (users == null) {
            throw new ProcessFailed("No user found.");
        }
        return users;
    }
        
}
