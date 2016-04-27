/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.UserDetails;
import com.intbittech.services.UsersService;
import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class UsersServiceImpl implements UsersService {
    
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private CompanyDao companyDao;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
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
        try {
        //Save temporary company
        Company company = new Company();
        company.setCompanyName("none");
        Integer companyId = companyDao.save(company);
        
        Users user = new Users();
        user.setUserName(usersDetails.getUserName());
        user.setUserPassword(passwordEncoder.encode(usersDetails.getUserPassword()));
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(2);
        user.setFkUserRoleId(userRole);
        user.setCreatedDate(new Date());
        Company companyObject = new Company();
        companyObject.setCompanyId(companyId);
        user.setFkCompanyId(companyObject);
        usersDao.save(user);

        returnMessage = "true";
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
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
    
    
    
}
