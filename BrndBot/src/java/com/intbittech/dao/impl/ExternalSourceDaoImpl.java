/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ExternalSourceDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSource;
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
 * <code> {@link ExternalSourceDaoImpl} </code> is implementation of
 * {@link ExternalSourceDao} and perform the database related operation for
 * managing {@link ExternalSource}
 *
 * @author Ajit
 */
@Repository
public class ExternalSourceDaoImpl implements ExternalSourceDao {

    private static Logger logger = Logger.getLogger(ExternalSourceDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public ExternalSource getByExternalSourceId(Integer externalSourceId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ExternalSource.class)
                    .add(Restrictions.eq("externalSourceId", externalSourceId));
            List<ExternalSource> externalSourceList = criteria.list();
            if (externalSourceList.isEmpty()) {
                return null;
            }
            return (ExternalSource) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ExternalSource> getALLExternalSources() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ExternalSource.class);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ExternalSource externalSource) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(externalSource));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(ExternalSource externalSource) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(externalSource);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(ExternalSource externalSource) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(externalSource);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }

}
