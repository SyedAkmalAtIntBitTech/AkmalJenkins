/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailBlockDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;
import com.intbittech.model.EmailModel;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link EmailBlockDaoImpl} </code> is implementation of
 * {@link EmailBlockDao} and perform the database related operation for managing
 * {@link EmailBlock}
 *
 * @author ilyas
 */
@Repository
public class EmailBlockDaoImpl implements EmailBlockDao {

    private static Logger logger = Logger.getLogger(EmailBlockDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public EmailBlock getByEmailBlockId(Integer emailBlockId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlock.class)
                    .add(Restrictions.eq("emailBlockId", emailBlockId));
            List<EmailModel> emailModelList = criteria.list();
            if (emailModelList.isEmpty()) {
                return null;
            }
            return (EmailBlock) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlock emailBlock) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailBlock));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlock emailBlock) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(emailBlock);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailBlock emailBlock) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(emailBlock);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }
    
}
