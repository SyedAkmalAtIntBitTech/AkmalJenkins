/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.PushedScheduledActionCompaniesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledActionCompanies;
import java.sql.Timestamp;
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
    @Autowired
     private MessageSource messageSource;

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
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
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
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
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
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
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
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
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
                    .createAlias("fkPushedScheduledActionEntityListId.fkScheduledEntityListId", "scheduledEntityList")
                    .add(Restrictions.eq("scheduledEntityList.scheduledEntityListId", ScheduledEntityListId));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
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
                    .createAlias("fkPushedScheduledActionEntityListId.fkFranchiseId", "franchise")
                    .add(Restrictions.eq("franchise.franchiseId", franchiseId));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
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
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
        }
    }

     /**
     * {@inheritDoc}
     */
    public List<PushedScheduledActionCompanies> getAllPushedScheduledActionCompaniesByCompanyIdAndDateDifference(Integer companyId, Timestamp fromDate, Timestamp toDate) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledActionCompanies.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .add(Restrictions.gt("updatedAt", fromDate))
                    .add(Restrictions.lt("updatedAt", toDate));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
        }
    }
    
    @Override
    public List<PushedScheduledActionCompanies> getPushedScheduledActionCompaniesByScheduledEntityListIdAndStatus(Integer ScheduledEntityListId, String Status) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledActionCompanies.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId", FetchMode.JOIN)
                    .setFetchMode("fkPushedScheduledActionEntityListId.fkScheduledEntityListId", FetchMode.JOIN)
                    .createAlias("fkPushedScheduledActionEntityListId.fkScheduledEntityListId", "scheduledEntityList")
                    .add(Restrictions.eq("status", Status))
                    .add(Restrictions.eq("scheduledEntityList.scheduledEntityListId", ScheduledEntityListId));
          List<PushedScheduledActionCompanies> pushedScheduledActionCompanies = criteria.list();

            if (pushedScheduledActionCompanies.isEmpty()) {
              
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
        }
    }

}
