/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.OrganizationEmailBlockLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;
import com.intbittech.model.OrganizationEmailBlockLookup;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link OrganizationEmailBlockLookupDaoImpl} </code> is implementation of
 * {@link OrganizationEmailBlockLookupDao} and perform the database related operation for managing
 * {@link OrganizationEmailBlockLookup}
 *
 * @author ilyas
 */
@Repository
public class OrganizationEmailBlockLookupDaoImpl implements OrganizationEmailBlockLookupDao {
    
    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public OrganizationEmailBlockLookup getByOrganizationEmailBlockId(Integer organizationEmailBlockId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationEmailBlockLookup.class)
                    .setFetchMode("fkEmailBlockId", FetchMode.JOIN)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .add(Restrictions.eq("organizationEmailBlockId", organizationEmailBlockId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (OrganizationEmailBlockLookup) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(OrganizationEmailBlockLookup organizationEmailBlockLookup) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(organizationEmailBlockLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<OrganizationEmailBlockLookup> getAllOrganizationEmailBlock(Integer organizationId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(OrganizationEmailBlockLookup.class)
                    .setFetchMode("fkOrganizationId", FetchMode.JOIN)
                    .setFetchMode("fkEmailBlockId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkOrganizationId.organizationId", organizationId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records");
        }
    }
    
}
