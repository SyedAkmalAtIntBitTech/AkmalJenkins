/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.PushedScheduledActionCompaniesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledActionCompanies;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link PushedScheduledActionCompaniesDaoImpl} </code> is
 * implementation of {@link PushedScheduledActionCompaniesDaoImpl} and perform
 * the database related operation for managing
 * {@link PushedScheduledActionCompanies}
 *
 * @author Ajit
 */
@Repository
public class PushedScheduledActionCompaniesDaoImpl implements PushedScheduledActionCompaniesDao {

    private static Logger logger = Logger.getLogger(PushedScheduledActionCompaniesDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public PushedScheduledActionCompanies getByPushedScheduledActionCompaniesId(Integer pushedScheduledActionCompaniesId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledActionCompanies.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId", FetchMode.JOIN)
                    .add(Restrictions.eq("pushedScheduledActionCompaniesId", pushedScheduledActionCompaniesId));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return (PushedScheduledActionCompanies) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed {
          try {
            return ((Integer) sessionFactory.getCurrentSession().save(pushedScheduledActionCompanies));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(pushedScheduledActionCompanies);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(PushedScheduledActionCompanies pushedScheduledActionCompanies) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(pushedScheduledActionCompanies);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

     /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByScheduledEntityListId(Integer ScheduledEntityListId) throws ProcessFailed {
       try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledActionCompanies.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId.fkScheduledEntityListId", FetchMode.JOIN)
                    .createAlias("fkPushedScheduledActionEntityListId.fkScheduledEntityListId", "pscId")
                    .add(Restrictions.eq("pscId.scheduledEntityListId", ScheduledEntityListId));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

     /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByFranchiseId(Integer franchiseId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledActionCompanies.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId.fkFranchiseId", FetchMode.JOIN)
                    .createAlias("fkPushedScheduledActionEntityListId.fkFranchiseId", "psfId")
                    .add(Restrictions.eq("psfId.franchiseId", franchiseId));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

     /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByCompanyId(Integer companyId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledActionCompanies.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

}
