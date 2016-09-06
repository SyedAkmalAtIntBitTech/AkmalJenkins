/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ContactEmailListLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ContactEmailListLookup;
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
 * <code> {@link ContactEmailListLookupDaoImpl} </code> is implementation of
 * {@link ContactEmailListLookupDao} and perform the database related operation for managing
 * {@link ContactEmailListLookup}
 *
 * @author Ajit
 */
@Repository
public class ContactEmailListLookupDaoImpl implements ContactEmailListLookupDao{
    
    private static Logger logger = Logger.getLogger(ContactEmailListLookupDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

     /**
     * {@inheritDoc}
     */
    public ContactEmailListLookup getByContactEmailListLookupId(Integer contactEmailListLookupId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ContactEmailListLookup.class)
                    .setFetchMode("fkContactId", FetchMode.JOIN)
                    .setFetchMode("fkEmailListId", FetchMode.JOIN)
                    .add(Restrictions.eq("contactLookupId", contactEmailListLookupId));
            List<ContactEmailListLookup> contactEmailListLookup = criteria.list();
            if (contactEmailListLookup.isEmpty()) {
                return null;
            }
            return (ContactEmailListLookup) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<ContactEmailListLookup> getContactsByEmailListId(Integer emailListId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ContactEmailListLookup.class)
                    .setFetchMode("fkContactId", FetchMode.JOIN)
                    .setFetchMode("fkEmailListId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkEmailListId.emailListId", emailListId));
            List<ContactEmailListLookup> contactEmailListLookup = criteria.list();
            if (contactEmailListLookup.isEmpty()) {
                return null;
            }
            return contactEmailListLookup;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public Integer save(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(contactEmailListLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public void update(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(contactEmailListLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public void delete(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(contactEmailListLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}
