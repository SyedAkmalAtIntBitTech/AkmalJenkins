/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ExternalSourceKeywordLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSourceKeywordLookup;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 *
 * @author ajit
 */
public class ExternalSourceKeywordLookupDaoImpl implements ExternalSourceKeywordLookupDao {

    private static Logger logger = Logger.getLogger(ExternalSourceKeywordLookupDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public ExternalSourceKeywordLookup getByExternalSourceKeywordLookupId(Integer externalSourceKeywordLookupId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ExternalSourceKeywordLookup.class)
                    .setFetchMode("fkExternalSourceId", FetchMode.JOIN)
                    .setFetchMode("fkExternalSourceKeywordId", FetchMode.JOIN)
                    .add(Restrictions.eq("externalSourceKeywordLookupId", externalSourceKeywordLookupId));
            List<ExternalSourceKeywordLookup> externalSourceKeywordLookupList = criteria.list();
            if (externalSourceKeywordLookupList.isEmpty()) {
                return null;
            }
            return (ExternalSourceKeywordLookup) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ExternalSourceKeywordLookup> getALLExternalSourceKeywordLookups() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ExternalSourceKeywordLookup.class)
                    .setFetchMode("fkExternalSourceId", FetchMode.JOIN)
                    .setFetchMode("fkExternalSourceKeywordId", FetchMode.JOIN);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(externalSourceKeywordLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(externalSourceKeywordLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(ExternalSourceKeywordLookup externalSourceKeywordLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(externalSourceKeywordLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

}
