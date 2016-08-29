/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailListTypeDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Contacts;
import com.intbittech.model.EmailListType;
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
 * <code> {@link EmailListTypeDaoImpl} </code> is implementation of
 * {@link EmailListTypeDao} and perform the database related operation for
 * managing {@link EmailListType}
 *
 * @author Ajit
 */
@Repository
public class EmailListTypeDaoImpl implements EmailListTypeDao {

    private static Logger logger = Logger.getLogger(EmailListTypeDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailListType getByEmailListTypeId(Integer emailListTypeId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailListType.class)
                    .add(Restrictions.eq("typeId", emailListTypeId));
            List<EmailListType> emailListType = criteria.list();
            if (emailListType.isEmpty()) {
                return null;
            }
            return (EmailListType) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailListType emailListType) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailListType));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailListType emailListType) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(emailListType);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailListType emailListType) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(emailListType);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

}
