/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UserCompanyLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UserCompanyLookup;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public class UserCompanyLookupDaoImpl implements UserCompanyLookupDao {
    private Logger logger = Logger.getLogger(UserCompanyLookupDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean isCompanyExist(UserCompanyLookup userCompanyLookup) throws ProcessFailed {
        boolean isUserUnique = true;
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UserCompanyLookup.class)
                .add(Restrictions.eq("companyId", userCompanyLookup.getCompanyid()))
                .add(Restrictions.eq("userId", userCompanyLookup.getUserid()));
        if (criteria.list().isEmpty()) {
            isUserUnique = true;
        } else {
            isUserUnique = false;
        }
        return isUserUnique;
    }

    @Override
    public Integer save(UserCompanyLookup userCompanyLookup) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(userCompanyLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public void update(UserCompanyLookup userCompanyLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(userCompanyLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public void delete(UserCompanyLookup userCompanyLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(userCompanyLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public UserCompanyLookup getUsersCompanyLookupById(Integer Id) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UserCompanyLookup.class)
                .add(Restrictions.eq("id", Id));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UserCompanyLookup) criteria.list().get(0);
    }

    @Override
    public UserCompanyLookup getUsersCompanyLookupByUserId(Users userId) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UserCompanyLookup.class)
                .add(Restrictions.eq("userId", userId));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UserCompanyLookup) criteria.list().get(0);
    }

    @Override
    public UserCompanyLookup getUsersCompanyLookupByUserAndCompanyId(Users user, Company company) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UserCompanyLookup.class)
                .add(Restrictions.eq("userId", user))
                .add(Restrictions.eq("companyId", company));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UserCompanyLookup) criteria.list().get(0);
    }

    @Override
    public Company getUsersCompanyByUserId(Users user) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UserCompanyLookup.class)
                .add(Restrictions.eq("userId", user));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (Company) criteria.list().get(0);
    }

    @Override
    public List<UserCompanyLookup> getAllUserCompaniesByUserId(Users user) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(UserCompanyLookup.class)
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
    
}
