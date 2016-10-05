/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UserRoleCompanyLookUpDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyDetails;
import com.intbittech.model.UserRole;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.model.Users;
import com.intbittech.services.UserRoleCompanyLookUpService;
import com.intbittech.services.UsersService;
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
public class UserRoleCompanyLookUpServiceImpl implements UserRoleCompanyLookUpService{

    private final static Logger logger = Logger.getLogger(UserRoleCompanyLookUpServiceImpl.class);

    @Autowired
    private UserRoleCompanyLookUpDao usersRoleCompanyLookUpDao;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private UsersService usersService;

    @Autowired
    private CompanyDao companyDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoleExist(UsersRoleCompanyLookup usersRoleLookup) throws ProcessFailed {
        return usersRoleCompanyLookUpDao.isRoleExist(usersRoleLookup);
    }
    
    @Override
    public String save(UsersRoleCompanyLookup usersRoleLookup) throws ProcessFailed {
        String returnMessage = "false";
        try {
            usersRoleCompanyLookUpDao.save(usersRoleLookup);

            returnMessage = "true";
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }  
        return returnMessage;    
    }

    @Override
    public void delete(Integer id) throws ProcessFailed {
        UsersRoleCompanyLookup userRoleLookup = usersRoleCompanyLookUpDao.getUsersRoleLookupByLookUpId(id);
        if(userRoleLookup == null){
            throw new ProcessFailed("No user role found with id " + id + ".");
        }
         usersRoleCompanyLookUpDao.delete(userRoleLookup); 
    }

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByLookUpId(Integer id) throws ProcessFailed {
        UsersRoleCompanyLookup userRoleLookup = usersRoleCompanyLookUpDao.getUsersRoleLookupByLookUpId(id);
        if(userRoleLookup == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
    }

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByUser(Users user) throws ProcessFailed {
        UsersRoleCompanyLookup userRoleLookup = usersRoleCompanyLookUpDao.getUsersRoleLookupByUser(user);
        if(userRoleLookup == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
    }
    
    @Override
    public UserRole getUsersRoleByUser(Users user) throws ProcessFailed {
        UserRole userRole = usersRoleCompanyLookUpDao.getUsersRoleByUser(user);
        if(userRole == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRole;
    }

   /**
     * {@inheritDoc}
     */
    public List<UsersRoleCompanyLookup> getAllUserRolesByUser(Users user) throws ProcessFailed {
        List<UsersRoleCompanyLookup> userRoleLookup = usersRoleCompanyLookUpDao.getAllUserRolesByUser(user);
        if (userRoleLookup.isEmpty()) {
            throw new ProcessFailed("No Sub Categories found.");
        }
        return userRoleLookup;
    }    

    @Override
    public void update(UsersRoleCompanyLookup usersRoleLookup) throws ProcessFailed {
            usersRoleCompanyLookUpDao.update(usersRoleLookup);
    }

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndRole(Users user, UserRole role) throws ProcessFailed {
        UsersRoleCompanyLookup userRoleLookup = usersRoleCompanyLookUpDao.getUsersRoleLookupByUserAndRole(user, role);
        if(userRoleLookup == null)
        {
            return null;
//             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
        
    }

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndCompany(Users user, Company company) throws ProcessFailed {
        UsersRoleCompanyLookup userRoleLookup = usersRoleCompanyLookUpDao.getUsersRoleLookupByUserAndCompany(user, company);
        if(userRoleLookup == null)
        {
            return null;
//             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userRoleLookup;
    }

    @Override
    public String getStatus(UserCompanyDetails userCompanyDetails) throws ProcessFailed {
        String statusReturn = "";
        try{
            Users user = usersService.getUserByEmailId(userCompanyDetails.getUserEmailId());
            Company company = companyDao.getCompanyById(userCompanyDetails.getCompanyId());

            UsersRoleCompanyLookup userRoleCompanyLookup = getUsersRoleLookupByUserAndCompany(user,company);
            
            statusReturn = userRoleCompanyLookup.getAccountStatus();
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("something_wrong",new String[]{}, Locale.US));
        }
        return statusReturn;

    }

   /**
     * {@inheritDoc}
     */
    public List<UsersRoleCompanyLookup> getAllUsersRoleCompanyLookupByCompanyId(Integer companyId) throws ProcessFailed {
         List<UsersRoleCompanyLookup> usersRoleCompanyLookupList = usersRoleCompanyLookUpDao.getAllUsersRoleCompanyLookupByCompanyId(companyId);
        if(usersRoleCompanyLookupList == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return usersRoleCompanyLookupList;
    }
    
}
