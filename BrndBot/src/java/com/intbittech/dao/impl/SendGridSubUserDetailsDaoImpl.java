/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.SendGridSubUserDetailsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SendGridSubUserDetails;
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
 * <code> {@link SendGridSubUserDetailsDaoImpl} </code> is implementation of
 * {@link SendGridSubUserDetailsDao} and perform the database related operation
 * for managing {@link SendGridSubUserDetails}
 *
 * @author Ajit
 */
@Repository
public class SendGridSubUserDetailsDaoImpl implements SendGridSubUserDetailsDao {

    private static Logger logger = Logger.getLogger(SendGridSubUserDetailsDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getBySendGridSubUserDetailsId(Integer sendGridSubUserDetailsId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SendGridSubUserDetails.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkUserId", FetchMode.JOIN)
                    .add(Restrictions.eq("sendGridSubUserDetailsId", sendGridSubUserDetailsId));
            List<SendGridSubUserDetails> sendGridSubUserDetails = criteria.list();
            if (sendGridSubUserDetails.isEmpty()) {
                return null;
            }
            return (SendGridSubUserDetails) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(sendGridSubUserDetails));
        } catch (Throwable throwable) {
            if (throwable instanceof org.hibernate.exception.ConstraintViolationException){
                throw new ProcessFailed(messageSource.getMessage("send_grid_sub_user_details_already_exists", null, Locale.US));
            }
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(sendGridSubUserDetails);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(SendGridSubUserDetails sendGridSubUserDetails) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(sendGridSubUserDetails);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsBySendGridSubUserIdAndCompanyId(String sendGridSubUserId, Integer companyId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SendGridSubUserDetails.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkUserId", FetchMode.JOIN)
                    .add(Restrictions.eq("sendGridUserId", sendGridSubUserId))
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId));
            List<SendGridSubUserDetails> sendGridSubUserDetails = criteria.list();
            if (sendGridSubUserDetails.isEmpty()) {
                return null;
            }
            return (SendGridSubUserDetails) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsByCompanyId(Integer companyId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SendGridSubUserDetails.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkUserId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId));
            List<SendGridSubUserDetails> sendGridSubUserDetails = criteria.list();
            if (sendGridSubUserDetails.isEmpty()) {
                return null;
            }
            return (SendGridSubUserDetails) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public SendGridSubUserDetails getSendGridSubUserDetailsBySendGridSubUserId(String sendGridSubUserId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SendGridSubUserDetails.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkUserId", FetchMode.JOIN)
                    .add(Restrictions.eq("sendGridUserId", sendGridSubUserId));
            List<SendGridSubUserDetails> sendGridSubUserDetails = criteria.list();
            if (sendGridSubUserDetails.isEmpty()) {
                return null;
            }
            return (SendGridSubUserDetails) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", null, Locale.US));
        }
    }

}
