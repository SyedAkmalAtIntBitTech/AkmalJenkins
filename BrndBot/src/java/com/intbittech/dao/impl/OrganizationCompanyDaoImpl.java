/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.OrganizationCompanyDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationCompanyLookup;
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

@Repository
public class OrganizationCompanyDaoImpl implements OrganizationCompanyDao {
    
    private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanies() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationCompanyLookup.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public OrganizationCompanyLookup getOrganizationCompanyById(Integer companyId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationCompanyLookup.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .createAlias("fkOrganizationId.fkOrganizationTypeId", "organizationType")
                    .add(Restrictions.eq("organizationType.organizationTypeId", 2)); //Organization is 2
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (OrganizationCompanyLookup)criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<OrganizationCompanyLookup> getAllOrganizationCompanyById(Integer companyId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationCompanyLookup.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .createAlias("fkOrganizationId.fkOrganizationTypeId", "organizationType")
                    .add(Restrictions.eq("organizationType.organizationTypeId", 1)); //Group is 1
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Integer save(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(organizationCompanyLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void delete(OrganizationCompanyLookup organizationCompanyLookup) throws ProcessFailed {
        try {
            
            sessionFactory.getCurrentSession().delete(organizationCompanyLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }
    
    public OrganizationCompanyLookup getById(Integer organizationCompanyLookupId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationCompanyLookup.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .add(Restrictions.eq("organizationCompanyLookupId", organizationCompanyLookupId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (OrganizationCompanyLookup)criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
}
