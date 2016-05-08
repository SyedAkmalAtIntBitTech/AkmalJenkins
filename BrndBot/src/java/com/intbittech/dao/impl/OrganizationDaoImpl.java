/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbit.OrganizationTypeConstants;
import com.intbittech.dao.OrganizationDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Organization;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link OrganizationDaoImpl} </code> is implementation of
 * {@link OrganizationDao} and perform the database related operation for
 * managing {@link Organization}
 *
 * @author Ajit
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private static Logger logger = Logger.getLogger(OrganizationDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public Organization getById(Integer id) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Organization.class)
                    .setFetchMode("fkOrganizationTypeId", FetchMode.JOIN)
                    .add(Restrictions.eq("organizationId", id));
            List<Organization> organization = criteria.list();
            if (organization.isEmpty()) {
                return null;
            }
            return (Organization) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Organization> getAllOrganizations() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Organization.class)
                    .setFetchMode("fkOrganizationTypeId", FetchMode.JOIN);
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
    public List<Organization> getAllOnlyOrganizations() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Organization.class)
                    .setFetchMode("fkOrganizationTypeId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkOrganizationTypeId.organizationTypeId", OrganizationTypeConstants.organization.getOrganizationType()));
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
    public Integer save(Organization organization) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(organization));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(Organization organization) throws ProcessFailed {
        try {
             sessionFactory.getCurrentSession().update(organization);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        } 
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Organization organization) throws ProcessFailed {
         try {
             sessionFactory.getCurrentSession().delete(organization);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        } 
    }

}
