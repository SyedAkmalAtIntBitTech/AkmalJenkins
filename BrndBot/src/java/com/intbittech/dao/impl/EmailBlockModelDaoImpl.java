/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailBlockModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModel;
import java.util.ArrayList;
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
 * <code> {@link EmailBlockModelDaoImpl} </code> is implementation of
 * {@link EmailBlockModelDao} and perform the database related operation for
 * managing {@link EmailBlockModel}
 *
 * @author ilyas
 */
@Repository
public class EmailBlockModelDaoImpl implements EmailBlockModelDao {

    private static Logger logger = Logger.getLogger(EmailBlockModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailBlockModel getByEmailBlockModelId(Integer emailBlockModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockModel.class)
                    .add(Restrictions.eq("emailBlockModelId", emailBlockModelId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (EmailBlockModel) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlockModel emailBlockModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailBlockModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlockModel emailBlockModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(emailBlockModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(EmailBlockModel emailBlockModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(emailBlockModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<EmailBlockModel> getAllEmailBlockModel() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockModel.class);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailBlockModel> getByEmailBlockModelsByIds(ArrayList<Integer> emailBlockModelIds) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailBlockModel.class);
            for(int i =0 ; i<emailBlockModelIds.size();i++)
            criteria.add(Restrictions.ne("emailBlockModelId", emailBlockModelIds.get(i)));
            List<EmailBlockModel> emailBlockModelList = criteria.list();
            if (emailBlockModelList.isEmpty()) {
                return null;
            }
            return emailBlockModelList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }

}
