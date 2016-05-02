/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ForgotPasswordDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ForgotPassword;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ilyas
 */
@Repository
public class ForgotPasswordDaoImpl implements ForgotPasswordDao {

    private Logger logger = Logger.getLogger(ForgotPasswordDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Integer save(ForgotPassword forgotPassword) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(forgotPassword);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public void update(ForgotPassword forgotPassword) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(forgotPassword);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public void delete(ForgotPassword forgotPassword) {
        try {
            sessionFactory.getCurrentSession().delete(forgotPassword);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public ForgotPassword getByRandomHash(String hashURL) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ForgotPassword.class)
                    .add(Restrictions.eq("randomLink", hashURL));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (ForgotPassword) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

}
