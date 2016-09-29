/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailListTagDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTag;
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
 * <code> {@link EmailListTagDaoImpl} </code> is implementation of
 * {@link EmailListTagDao} and perform the database related operation for managing
 * {@link EmailListTag}
 *
 * @author Ajit
 */
@Repository
public class EmailListTagDaoImpl implements EmailListTagDao  {
    
    private static Logger logger = Logger.getLogger(EmailListTagDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailListTag getByEmailListTagId(Integer emailListTagId) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailListTag.class)
                    .add(Restrictions.eq("tagId", emailListTagId));
            List<EmailListTag> emailListTag = criteria.list();
            if (emailListTag.isEmpty()) {
                return null;
            }
            return (EmailListTag) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public EmailListTag getByTagName(String tagName) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailListTag.class)
                    .add(Restrictions.eq("tagName", tagName));
            List<EmailListTag> emailListTag = criteria.list();
            if (emailListTag.isEmpty()) {
                return null;
            }
            return (EmailListTag) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailListTag emailListTag) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailListTag));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailListTag emailListTag) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(emailListTag);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

   /**
     * {@inheritDoc}
     */
    public void delete(EmailListTag emailListTag) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(emailListTag);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<EmailListTag> getAllEmailListTag() throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailListTag.class);
            List<EmailListTag> emailListTag = criteria.list();
            if (emailListTag.isEmpty()) {
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
}
