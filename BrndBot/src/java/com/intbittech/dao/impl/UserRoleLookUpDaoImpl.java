/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UserRoleLookUpDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Invite;
import com.intbittech.model.Users;
import com.intbittech.model.UserRoleLookup;
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
    public boolean isRoleExist(UserRoleLookup userRoleLookup) {
        boolean isUserUnique = true;
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Users.class)
                .add(Restrictions.eq("role_id", userRoleLookup.getRoleId()));
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
    public Integer save(UserRoleLookup userRoleLookup) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(userRoleLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public void delete(UserRoleLookup userRoleLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(userRoleLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public UserRoleLookup getUsersRoleLookupById(Integer Id) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UserRoleLookup.class)
                .add(Restrictions.eq("id", Id));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UserRoleLookup) criteria.list().get(0);
    }
    
}
