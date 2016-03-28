/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;
import com.intbittech.dao.OrganizationRecurringEmailLookUpDao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationRecuringEmailLookup;
import com.intbittech.model.RecuringEmailTemplate;

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
import com.intbittech.model.OrganizationRecuringEmailLookup;

/**
 * <code> {@link OrganizationRecuringEmailLookupDaoImpl} </code> is implementation of
 * {@link OrganizationRecuringEmailLookupDao} and perform the database related operation for
 * managing {@link OrganizationRecuringEmailLookupDaoImpl}
 *
 * @author Mohammed-Tameem
 */
public class OrganizationRecuringEmailLookupDaoImpl implements OrganizationRecurringEmailLookUpDao

{
    private static Logger logger = Logger.getLogger(SubCategoryEmailModelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;
    
    
    /**
     * {@inheritDoc}
     */

     public List<OrganizationRecuringEmailLookup> getAllRecuringByOrganizationId(Integer organizationId) throws ProcessFailed 
       {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationRecuringEmailLookup.class)
                    .setFetchMode("fkRecuringEmailTemplateId", FetchMode.JOIN)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkOrganizationId.organizationId", organizationId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }
    }
     
    /**
     * {@inheritDoc}
     */
     
     public OrganizationRecuringEmailLookup getOrganizationRecuringEmailById(Integer organizationRecuringEmailLookupId) throws ProcessFailed 
     {
                 try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationRecuringEmailLookup.class)
                    .add(Restrictions.eq("fkOrganizationId.organizationId", organizationRecuringEmailLookupId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (OrganizationRecuringEmailLookup) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message", new String[]{}, Locale.US));
        }     
     }
     
    /**
     * {@inheritDoc}
     */
     
       public Integer saveRecuringEmailOrganization(OrganizationRecuringEmailLookup organizationRecuringEmailLookup) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(organizationRecuringEmailLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }
       
    /**
     * {@inheritDoc}
     */

    public void deleteRecuringEmailOrganization(OrganizationRecuringEmailLookup organizationRecuringEmailLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(organizationRecuringEmailLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        }
    }
    
}
