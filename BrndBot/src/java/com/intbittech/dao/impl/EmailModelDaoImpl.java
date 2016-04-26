/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.EmailModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailModel;
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
 * <code> {@link EmailModelDaoImpl} </code> is implementation of
 * {@link EmailModelDao} and perform the database related operation for managing
 * {@link EmailModel}
 *
 * @author ilyas
 */
@Repository
public class EmailModelDaoImpl implements EmailModelDao {

    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailModel getByEmailModelId(Integer emailModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailModel.class)
                    .add(Restrictions.eq("emailModelId", emailModelId));
            List<EmailModel> emailModelList = criteria.list();
            if (emailModelList.isEmpty()) {
                return null;
            }
            return (EmailModel) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    public List<EmailModel> getAllEmailModel() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailModel.class);
            List<EmailModel> emailModelList = criteria.list();
            if (emailModelList.isEmpty()) {
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
    public Integer save(EmailModel emailModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
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
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
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
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailModel> getByEmailModelsByIds(ArrayList<Integer> emailModelIds) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailModel.class);
            for(int i =0 ; i<emailModelIds.size();i++)
            criteria.add(Restrictions.ne("emailModelId", emailModelIds.get(i)));
            List<EmailModel> emailModelList = criteria.list();
            if (emailModelList.isEmpty()) {
                return null;
            }
            return emailModelList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }

}
