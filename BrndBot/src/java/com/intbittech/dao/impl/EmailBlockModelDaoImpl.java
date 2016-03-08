/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailBlockModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModel;
import com.intbittech.model.OrganizationEmailBlockLookup;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link EmailBlockModelDaoImpl} </code> is implementation of
 * {@link EmailBlockModelDao} and perform the database related operation for managing
 * {@link EmailBlockModel}
 *
 * @author ilyas
 */
@Repository
public class EmailBlockModelDaoImpl implements EmailBlockModelDao {
    
    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public EmailBlockModel getByEmailBlockModelId(Integer emailBlockModelId) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationEmailBlockLookup.class)
                    .add(Restrictions.eq("emailBlockModelId", emailBlockModelId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (EmailBlockModel) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlockModel emailBlockModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailBlockModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlockModel emailBlockModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(emailBlockModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailBlockModel emailBlockModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(emailBlockModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }
    
}
