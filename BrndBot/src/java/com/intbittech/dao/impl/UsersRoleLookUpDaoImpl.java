/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UsersRoleLookUpDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyInvite;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
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
public class UsersRoleLookUpDaoImpl implements UsersRoleLookUpDao{

    private Logger logger = Logger.getLogger(UsersDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    
    @Override
    public Integer save(UsersRoleLookup userRoleLookup) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(userRoleLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public void delete(UsersRoleLookup userRoleLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(userRoleLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public UsersRoleLookup getUsersRoleLookupById(Integer Id) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UsersRoleLookup.class)
                .add(Restrictions.eq("id", Id));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UsersRoleLookup) criteria.list().get(0);
    }
    
}
