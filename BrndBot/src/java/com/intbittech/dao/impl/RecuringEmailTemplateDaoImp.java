/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.RecurringEmailTemplateDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationRecuringEmailLookup;
import com.intbittech.model.RecuringEmailTemplate;
import com.intbittech.model.SubCategory;
import com.intbittech.model.SubCategoryEmailModel;
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
 * <code> {@link RecuringEmailTemplateDaoImp} </code> is implementation of
 * {@link RecurringEmailTemplateDao} and perform the database related operation for
 * managing {@link RecuringEmailTemplate}
 *
 * @author Mohammed-Tameem
 */
@Repository
public class RecuringEmailTemplateDaoImp implements RecurringEmailTemplateDao {

    private static Logger logger = Logger.getLogger(SubCategoryEmailModelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */

    public Integer save(RecuringEmailTemplate template) throws ProcessFailed {
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
    public void delete(RecuringEmailTemplate template) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(template);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }
    /**
     * {@inheritDoc}
     */
    public void update(RecuringEmailTemplate template) throws ProcessFailed {
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
    public RecuringEmailTemplate getRecuringEmailTemplateById(Integer recuringEmailTemplateId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(RecuringEmailTemplate.class)
                    .setFetchMode("recuring_email_template_id", FetchMode.JOIN)
                    .add(Restrictions.eq("recuring_email_template_id", recuringEmailTemplateId));
            List<RecuringEmailTemplate> recuringEmailTemplate = criteria.list();
            if (recuringEmailTemplate.isEmpty()) {
                return null;
            }
            return (RecuringEmailTemplate) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }

    }
    
    
    /**
     * {@inheritDoc}
     */
       public List<RecuringEmailTemplate> getAllNonRecurringEmail(Integer[] nonRecuringEmailIds) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(RecuringEmailTemplate.class);
            for(int i =0 ; i<nonRecuringEmailIds.length;i++)
            criteria.add(Restrictions.ne("recuringEmailTemplateId", nonRecuringEmailIds[i]));
            List<RecuringEmailTemplate> recuringEmailList = criteria.list();
            if (recuringEmailList.isEmpty()) {
                return null;
            }
            return recuringEmailList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }

    
}
