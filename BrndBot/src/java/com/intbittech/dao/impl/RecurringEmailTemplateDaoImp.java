/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.RecurringEmailTemplateDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.RecurringEmailTemplate;
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
 * <code> {@link RecurringEmailTemplateDaoImp} </code> is implementation of
 * {@link RecurringEmailTemplateDao} and perform the database related operation for
 * managing {@link RecurringEmailTemplate}
 *
 * @author Mohammed-Tameem
 */
@Repository
public class RecurringEmailTemplateDaoImp implements RecurringEmailTemplateDao {

    private static Logger logger = Logger.getLogger(SubCategoryEmailModelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */

    public Integer save(RecurringEmailTemplate template) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(template));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }
    /**
     * {@inheritDoc}
     */
    public void delete(RecurringEmailTemplate recurringEmailTemplate) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(recurringEmailTemplate);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }
    /**
     * {@inheritDoc}
     */
    public void update(RecurringEmailTemplate template) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(template);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }
    /**
     * {@inheritDoc}
     */
    public RecurringEmailTemplate getRecurringEmailTemplateById(Integer recurringEmailTemplateId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(RecurringEmailTemplate.class)
                    .setFetchMode("recurring_email_template_id", FetchMode.JOIN)
                    .add(Restrictions.eq("recurring_email_template_id", recurringEmailTemplateId));
            List<RecurringEmailTemplate> recurringEmailTemplate = criteria.list();
            if (recurringEmailTemplate.isEmpty()) {
                return null;
            }
            return (RecurringEmailTemplate) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }

    }
    
    
    /**
     * {@inheritDoc}
     */
       public List<RecurringEmailTemplate> getAllNonRecurringEmail(Integer[] recurringEmailTemplateIds) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(RecurringEmailTemplate.class);
            for(int i =0 ; i<recurringEmailTemplateIds.length;i++)
            criteria.add(Restrictions.ne("recurringEmailTemplateId", recurringEmailTemplateIds[i]));
            List<RecurringEmailTemplate> recurringEmailList = criteria.list();
            if (recurringEmailList.isEmpty()) {
                return null;
            }
            return recurringEmailList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }

    
}
