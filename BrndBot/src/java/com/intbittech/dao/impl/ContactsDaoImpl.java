/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ContactsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Contact;
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
 * {@link Contact}
 *
 * @author Ajit
 */
@Repository
public class ContactsDaoImpl implements ContactsDao {
    
    private static Logger logger = Logger.getLogger(ContactsDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;
     /**
     * {@inheritDoc}
     */
    public Contact getByContactsId(Integer contactsId) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Contact.class)
                    .add(Restrictions.eq("contactId", contactsId));
            List<Contact> contacts = criteria.list();
            if (contacts.isEmpty()) {
                return null;
            }
            return (Contact) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Contact getContactByEmailAddress(String emailAddress) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Contact.class)
                    .add(Restrictions.eq("emailAddress", emailAddress));
            List<Contact> contacts = criteria.list();
            if (contacts.isEmpty()) {
                return null;
            }
            return (Contact) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public Integer save(Contact contacts) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(contacts));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public void update(Contact contacts) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(contacts);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public void delete(Contact contacts) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(contacts);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}
