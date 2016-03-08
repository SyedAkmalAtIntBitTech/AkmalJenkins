/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ExternalSourceKeywordDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ExternalSource;
import com.intbittech.model.ExternalSourceKeyword;
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
public class ExternalSourceKeywordDaoImpl implements ExternalSourceKeywordDao{
    
     private static Logger logger = Logger.getLogger(ExternalSourceKeywordDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

     /**
     * {@inheritDoc}
     */
    public ExternalSourceKeyword getByExternalSourceKeywordId(Integer externalSourceKeywordId) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ExternalSourceKeyword.class)
                    .add(Restrictions.eq("externalSourceKeywordId", externalSourceKeywordId));
            List<ExternalSource> externalSourceKeywordList = criteria.list();
            if (externalSourceKeywordList.isEmpty()) {
                return null;
            }
            return (ExternalSourceKeyword) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ExternalSourceKeyword> getALLExternalSourceKeywords() throws ProcessFailed {
     try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ExternalSourceKeyword.class);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public Integer save(ExternalSourceKeyword externalSourceKeyword) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(externalSourceKeyword));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(ExternalSourceKeyword externalSourceKeyword) throws ProcessFailed {
          try {
            sessionFactory.getCurrentSession().update(externalSourceKeyword);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }    }

    /**
     * {@inheritDoc}
     */
    public void delete(ExternalSourceKeyword externalSourceKeyword) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(externalSourceKeyword);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
    
}
