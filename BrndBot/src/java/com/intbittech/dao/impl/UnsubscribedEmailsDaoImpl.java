/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UnsubscribedEmailsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Contacts;
import com.intbittech.model.UnsubscribedEmails;
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
 * <code> {@link ContactsDaoImpl} </code> is implementation of
 * {@link ContactsDao} and perform the database related operation for managing
 * {@link Contacts}
 *
 * @author Ajit
 */
@Repository
public class UnsubscribedEmailsDaoImpl implements UnsubscribedEmailsDao{
    
    private static Logger logger = Logger.getLogger(UnsubscribedEmailsDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

     /**
     * {@inheritDoc}
     */
    public UnsubscribedEmails getByUnsubscribedEmailsId(Integer unsubscribedEmailsId) throws ProcessFailed {
          try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Contacts.class)
                    .add(Restrictions.eq("unsubscribedEmailId", unsubscribedEmailsId));
            List<UnsubscribedEmails> contacts = criteria.list();
            if (contacts.isEmpty()) {
                return null;
            }
            return (UnsubscribedEmails) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(unsubscribedEmails));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(unsubscribedEmails);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public void delete(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed {
          try {
            sessionFactory.getCurrentSession().delete(unsubscribedEmails);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
}
