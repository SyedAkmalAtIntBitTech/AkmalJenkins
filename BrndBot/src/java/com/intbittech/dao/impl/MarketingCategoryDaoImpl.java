/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.MarketingCategoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailModel;
import com.intbittech.model.MarketingCategory;
import com.intbittech.model.OrganizationMarketingCategoryLookup;
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
 * <code> {@link MarketingCategoryDaoImpl} </code> is implementation of
 * {@link MarketingCategoryDao} and perform the database related operation for managing
 * {@link MarketingCategory}
 *
 * @author ilyas
 */
@Repository
public class MarketingCategoryDaoImpl implements MarketingCategoryDao {
    
    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingCategory> getAllMarketingCategories() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingCategory.class);
            List<MarketingCategory> marketingCategoryList = criteria.list();
            if (marketingCategoryList.isEmpty()) {
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
    public MarketingCategory getByMarketingCategoryId(Integer marketingCategoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingCategory.class)
                    .add(Restrictions.eq("marketingCategoryId", marketingCategoryId));
            List<MarketingCategory> marketingCategoryList = criteria.list();
            if (marketingCategoryList.isEmpty()) {
                return null;
            }
            return (MarketingCategory) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingCategory marketingCategory) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(marketingCategory));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingCategory marketingCategory) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(marketingCategory);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(MarketingCategory marketingCategory) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(marketingCategory);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<OrganizationMarketingCategoryLookup> getByOrganizationId(Integer organizationId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationMarketingCategoryLookup.class)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .setFetchMode("fkMarketingCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkOrganizationId.organizationId", organizationId));
            List<OrganizationMarketingCategoryLookup> organizationMarketingCategoryList = criteria.list();
            if (organizationMarketingCategoryList.isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<OrganizationMarketingCategoryLookup> getByOrganizationIds(Integer[] organizationIds) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationMarketingCategoryLookup.class)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .setFetchMode("fkMarketingCategoryId", FetchMode.JOIN);
            for(int i =0 ; i< organizationIds.length ; i++)
                criteria.add(Restrictions.eq("fkOrganizationId.organizationId", organizationIds[i]));
            List<OrganizationMarketingCategoryLookup> organizationMarketingCategoryList = criteria.list();
            if (organizationMarketingCategoryList.isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer saveMarketingCategoryOrganization(OrganizationMarketingCategoryLookup organizationMarketingCategoryLookup) throws ProcessFailed {
        try {
            return (Integer) sessionFactory.getCurrentSession().save(organizationMarketingCategoryLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteMarketingCategoryOrganization(OrganizationMarketingCategoryLookup organizationMarketingCategoryLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(organizationMarketingCategoryLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}
