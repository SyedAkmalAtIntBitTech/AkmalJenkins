/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailBlockExternalSourceDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockExternalSource;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link EmailBlockExternalSourceDaoImpl} </code> is implementation of
 * {@link EmailBlockExternalSourceDao} and perform the database related
 * operation for managing {@link EmailBlockExternalSource}
 *
 * @author ilyas
 */
@Repository
public class EmailBlockExternalSourceDaoImpl implements EmailBlockExternalSourceDao {

    private static Logger logger = Logger.getLogger(EmailBlockExternalSourceDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public EmailBlockExternalSource getEmailBlockExternalSourceById(Integer emailBlockExternalSourceId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockExternalSource.class)
                    .setFetchMode("fkEmailBlockId", FetchMode.JOIN)
                    .setFetchMode("fkExternalSourceKeywordLookupId", FetchMode.JOIN)
                    .add(Restrictions.eq("emailBlockExternalSourceId", emailBlockExternalSourceId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (EmailBlockExternalSource) criteria.list();
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<EmailBlockExternalSource> getAllEmailBlockExternalSource(Integer emailBlockId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockExternalSource.class)
                    .setFetchMode("fkEmailBlockId", FetchMode.JOIN)
                    .setFetchMode("fkExternalSourceKeywordLookupId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkEmailBlockId.emailBlockId", emailBlockId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(emailBlockExternalSource);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(emailBlockExternalSource);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(emailBlockExternalSource);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

    @Override
    public EmailBlockExternalSource getByEmailBlockId(Integer emailBlockId) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockExternalSource.class)
                    .setFetchMode("fkEmailBlockId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkEmailBlockId.emailBlockId", emailBlockId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (EmailBlockExternalSource) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records.");
        }
    }

}
