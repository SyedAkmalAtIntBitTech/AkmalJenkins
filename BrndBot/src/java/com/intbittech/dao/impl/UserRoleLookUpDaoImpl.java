/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UserRoleLookUpDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleCompanyLookup;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
@Repository
public class UserRoleLookUpDaoImpl implements UserRoleLookUpDao{

    private Logger logger = Logger.getLogger(UsersDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    
/**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoleExist(UsersRoleCompanyLookup userRoleLookup) {
        boolean isUserUnique = true;
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UsersRoleCompanyLookup.class)
                .add(Restrictions.eq("roleId", userRoleLookup.getRoleId()))
                .add(Restrictions.eq("companyId", userRoleLookup.getCompanyId()))
                .add(Restrictions.eq("userId", userRoleLookup.getUserId()));
        if (criteria.list().isEmpty()) {
            isUserUnique = true;
        } else {
            isUserUnique = false;
        }
        return isUserUnique;
    }    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(UsersRoleCompanyLookup userRoleLookup) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(userRoleLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(UsersRoleCompanyLookup userRoleLookup) {
        try {
            sessionFactory.getCurrentSession().update(userRoleLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }
    
    @Override
    public void delete(UsersRoleCompanyLookup userRoleLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(userRoleLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByLookUpId(Integer Id) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UsersRoleCompanyLookup.class)
                .add(Restrictions.eq("id", Id));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UsersRoleCompanyLookup) criteria.list().get(0);
    }

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByUser(Users user) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UsersRoleCompanyLookup.class)
                .add(Restrictions.eq("userId", user));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UsersRoleCompanyLookup) criteria.list().get(0);
    }
    
    @Override
    public UserRole getUsersRoleByUser(Users user) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UsersRoleCompanyLookup.class)
                .add(Restrictions.eq("userId", user));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UserRole) criteria.list().get(0);
    }

    /**
     * {@inheritDoc}
     */
    public List<UsersRoleCompanyLookup> getAllUserRolesByUser(Users user) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(UsersRoleCompanyLookup.class)
                    .add(Restrictions.eq("userId", user));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
        }

    }        

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndRole(Users user, UserRole role) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UsersRoleCompanyLookup.class)
                .add(Restrictions.eq("userId", user))
                .add(Restrictions.eq("roleId", role));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UsersRoleCompanyLookup) criteria.list().get(0);
    }

    @Override
    public UsersRoleCompanyLookup getUsersRoleLookupByUserAndCompany(Users user, Company company) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UsersRoleCompanyLookup.class)
                .add(Restrictions.eq("userId", user))
                .add(Restrictions.eq("companyId", company));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UsersRoleCompanyLookup) criteria.list().get(0);
    }
}
