/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.OrganizationCategoryLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.OrganizationCategoryLookup;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link OrganizationCategoryLookupDaoImpl} </code> is implementation of
 * {@link OrganizationCategoryLookupDao} and perform the database related
 * operation for managing {@link OrganizationCategoryLookup}
 *
 * @author Ajit
 */
@Repository
public class OrganizationCategoryLookupDaoImpl implements OrganizationCategoryLookupDao {

    private static Logger logger = Logger.getLogger(OrganizationCategoryLookupDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public List<OrganizationCategoryLookup> getAllOrganizationCategoryLookup(Integer organizationId, Integer channelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationCategoryLookup.class)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .setFetchMode("fkCategoryId", FetchMode.JOIN)
                    .createAlias("fkCategoryId.fkChannelId", "ccId")
                    .add(Restrictions.eq("fkOrganizationId.organizationId", organizationId))
                    .add(Restrictions.eq("ccId.channelId", channelId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<OrganizationCategoryLookup> getAllOrganizationCategoryLookupByIds(Integer[] organizationIds, Integer channelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationCategoryLookup.class)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .setFetchMode("fkCategoryId", FetchMode.JOIN)
                    .createAlias("fkCategoryId.fkChannelId", "ccId")
                    .add(Restrictions.eq("ccId.channelId", channelId));
            
            Criterion[] criterions = new Criterion[organizationIds.length];
            for(int i=0;i<organizationIds.length;i++)
              criterions[i] = (Restrictions.eq("fkOrganizationId.organizationId", organizationIds[i]));
              
            criteria.add(Restrictions.or(criterions));
            
            
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(organizationCategoryLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(OrganizationCategoryLookup organizationCategoryLookup) throws ProcessFailed {
        try {
             sessionFactory.getCurrentSession().update(organizationCategoryLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        } 
    }

}
