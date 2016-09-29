/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.FranchiseEmailListTagLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.FranchiseEmailListTagLookup;
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
 * <code> {@link FranchiseEmailListTagLookupDaoImpl} </code> is implementation
 * of {@link FranchiseEmailListTagLookupDao} and perform the database related
 * operation for managing {@link FranchiseEmailListTagLookup}
 *
 * @author Ajit
 */
@Repository
public class FranchiseEmailListTagLookupDaoImpl implements FranchiseEmailListTagLookupDao {
    
    private static Logger logger = Logger.getLogger(FranchiseEmailListTagLookupDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public Integer save(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed {
          try {
            return ((Integer) sessionFactory.getCurrentSession().save(franchiseEmailListTagLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed {
         try {
             sessionFactory.getCurrentSession().update(franchiseEmailListTagLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        } 
    }

   /**
     * {@inheritDoc}
     */
    public void delete(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed {
         try {
             sessionFactory.getCurrentSession().delete(franchiseEmailListTagLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", new String[]{}, Locale.US));
        } 
    }

    /**
     * {@inheritDoc}
     */
    public List<FranchiseEmailListTagLookup> getAllFranchiseEmailListTagLookup(Integer franchiseId) throws ProcessFailed {
    try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(FranchiseEmailListTagLookup.class)
                    .setFetchMode("fkEmailListTagId", FetchMode.JOIN)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkFranchiseId.franchiseId", franchiseId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public FranchiseEmailListTagLookup getFranchiseEmailListTagLookupByFranchiseEmailListTagLookupId(Integer franchiseEmailListTagLookupId) throws ProcessFailed {
     try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(FranchiseEmailListTagLookup.class)
                    .setFetchMode("fkEmailListTagId", FetchMode.JOIN)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .add(Restrictions.eq("franchiseEmailListTagLookupId", franchiseEmailListTagLookupId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (FranchiseEmailListTagLookup)criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    
   

}
