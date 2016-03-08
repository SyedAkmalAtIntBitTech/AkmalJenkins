/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailBlockModelLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModelLookup;
import com.intbittech.model.OrganizationEmailBlockLookup;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link EmailBlockModelLookupDaoImpl} </code> is implementation of
 * {@link EmailBlockModelLookupDao} and perform the database related operation for managing
 * {@link EmailBlockModelLookup}
 *
 * @author ilyas
 */
@Repository
public class EmailBlockModelLookupDaoImpl implements EmailBlockModelLookupDao {
    
    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public EmailBlockModelLookup getEmailBlockModelLookupById(Integer emailBlockModelLookupId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockModelLookup.class)
                    .setFetchMode("fkEmailBlockId", FetchMode.JOIN)
                    .setFetchMode("fkEmailBlockModelId", FetchMode.JOIN)
                    .add(Restrictions.eq("emailBlockModelLookupId", emailBlockModelLookupId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (EmailBlockModelLookup) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<EmailBlockModelLookup> getAllEmailBlockModel(Integer emailBlockId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockModelLookup.class)
                    .setFetchMode("fkEmailBlockId", FetchMode.JOIN)
                    .setFetchMode("fkEmailBlockModelId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkEmailBlockId.emailBlockId", emailBlockId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailBlockModelLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(emailBlockModelLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(emailBlockModelLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }
    
}
