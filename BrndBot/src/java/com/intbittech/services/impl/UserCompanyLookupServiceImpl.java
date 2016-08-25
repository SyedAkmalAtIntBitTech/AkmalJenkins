/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CompanyDao;
import com.intbittech.dao.UserCompanyLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyDetails;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.Users;
import com.intbittech.services.UserCompanyLookupService;
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
public class UserCompanyLookupServiceImpl implements UserCompanyLookupService {
    private final static Logger logger = Logger.getLogger(UserCompanyLookupServiceImpl.class);

    @Autowired
    private UserCompanyLookupDao usersCompanyLookUpDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CompanyDao companyDao;
    
    @Override
    public boolean isCompanyExist(UserCompanyLookup usersCompanyLookup) throws ProcessFailed {
        return usersCompanyLookUpDao.isCompanyExist(usersCompanyLookup);
    }

    @Override
    public String save(UserCompanyLookup usersCompanyLookup) throws ProcessFailed {
        String returnMessage = "false";
        try {
            usersCompanyLookUpDao.save(usersCompanyLookup);

            returnMessage = "true";
        } catch(Throwable throwable) {
            returnMessage = "false";
            throw new ProcessFailed(messageSource.getMessage("something_wrong", new String[]{}, Locale.US));
        }  
        return returnMessage;    
    }

    @Override
    public void update(UserCompanyLookup usersCompanyLookup) throws ProcessFailed {
        usersCompanyLookUpDao.update(usersCompanyLookup);
    }

    @Override
    public void delete(Integer id) throws ProcessFailed {
        UserCompanyLookup userCompanyLookup = usersCompanyLookUpDao.getUsersCompanyLookupById(id);
        if(userCompanyLookup == null){
            throw new ProcessFailed("No user role found with id " + id + ".");
        }
         usersCompanyLookUpDao.delete(userCompanyLookup); 
    }

    @Override
    public UserCompanyLookup getUserCompanyLookupById(Integer Id) throws ProcessFailed {
        UserCompanyLookup userCompanyLookup = usersCompanyLookUpDao.getUsersCompanyLookupById(Id);
        if(userCompanyLookup == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userCompanyLookup;
    }

    @Override
    public UserCompanyLookup getUserCompanyLookupByUser(Users user) throws ProcessFailed {
        UserCompanyLookup userCompanyLookup = usersCompanyLookUpDao.getUsersCompanyLookupByUser(user);
        if(userCompanyLookup == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userCompanyLookup;
    }

    @Override
    public UserCompanyLookup getUserCompanyLookupByUserAndCompany(Users user, Company company) throws ProcessFailed {
        UserCompanyLookup userCompanyLookup = usersCompanyLookUpDao.getUsersCompanyLookupByUserAndCompany(user, company);
        if(userCompanyLookup == null)
        {
            return null;
//             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return userCompanyLookup;
    }

    @Override
    public UserCompanyLookup getUsersCompanyByUser(Users user) throws ProcessFailed {
        UserCompanyLookup company = usersCompanyLookUpDao.getUsersCompanyByUser(user);
        if(company == null)
        {
             throw new ProcessFailed(messageSource.getMessage("user_not_found",new String[]{}, Locale.US));
        }
        return company;
    }

    @Override
    public List<UserCompanyLookup> getAllUserCompaniesByUser(Users user) throws ProcessFailed {
        List<UserCompanyLookup> userCompanyLookup = usersCompanyLookUpDao.getAllUserCompaniesByUser(user);
        if (userCompanyLookup.isEmpty()) {
            throw new ProcessFailed("No Sub Categories found.");
        }
        return userCompanyLookup;
    }
    
    @Override
    public String getStatus(UserCompanyDetails userCompanyDetails)throws ProcessFailed{
        
        String statusReturn = "";
        try{
            Users user = usersService.getUserByEmailId(userCompanyDetails.getUserEmailId());
            Company company = companyDao.getCompanyById(userCompanyDetails.getCompanyId());

            UserCompanyLookup userCompanyLookup = getUserCompanyLookupByUserAndCompany(user,company);
            
//            statusReturn = userCompanyLookup.getAccountStatus();
        }catch (Throwable throwable){
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("something_wrong",new String[]{}, Locale.US));
        }
        return statusReturn;
    }
}
