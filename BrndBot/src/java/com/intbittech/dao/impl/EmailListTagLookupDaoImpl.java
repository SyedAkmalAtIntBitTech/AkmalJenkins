/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailListTagLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTagLookup;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link EmailListTagLookupDaoImpl} </code> is implementation of
 * {@link EmailListTagLookupDao} and perform the database related operation for managing
 * {@link EmailListTagLookup}
 *
 * @author Ajit
 */
@Repository
public class EmailListTagLookupDaoImpl implements EmailListTagLookupDao{
    
      
    private static Logger logger = Logger.getLogger(EmailListTagLookupDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

     /**
     * {@inheritDoc}
     */
    public EmailListTagLookup getByEmailListTagLookupId(Integer emailListTagLookupId) throws ProcessFailed {
       try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailListTagLookup.class)
                    .setFetchMode("fkEmailListId", FetchMode.JOIN)
                    .setFetchMode("fkEmailListTagId", FetchMode.JOIN)
                    .add(Restrictions.eq("emailListTagId", emailListTagLookupId));
            List<EmailListTagLookup> contactEmailListLookup = criteria.list();
            if (contactEmailListLookup.isEmpty()) {
                return null;
            }
            return (EmailListTagLookup) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public Integer save(EmailListTagLookup emailListTagLookup) throws ProcessFailed {
          try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailListTagLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public void update(EmailListTagLookup emailListTagLookup) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(emailListTagLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public void delete(EmailListTagLookup emailListTagLookup) throws ProcessFailed {
          try {
            sessionFactory.getCurrentSession().delete(emailListTagLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}
