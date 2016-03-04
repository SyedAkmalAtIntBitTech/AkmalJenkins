/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailModel;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link EmailModelDaoImpl} </code> is implementation of
 * {@link EmailModelDao} and perform the database related operation for managing
 * {@link EmailModel}
 *
 * @author Ajit
 */
@Repository
public class EmailModelDaoImpl implements EmailModelDao {

    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public EmailModel getByEmailModelId(Integer emailModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailModel.class)
                    .add(Restrictions.eq("emailModelId", emailModelId));
            List<EmailModel> emailModel = criteria.list();
            if (emailModel.isEmpty()) {
                return null;
            }
            return (EmailModel) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailModel emailModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailModel emailModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(emailModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailModel emailModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(emailModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

}
