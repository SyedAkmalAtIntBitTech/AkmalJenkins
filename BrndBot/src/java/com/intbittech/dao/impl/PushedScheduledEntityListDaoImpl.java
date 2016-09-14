/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.PushedScheduledEntityListDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PushedScheduledEntityList;
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
 * <code> {@link PushedScheduledEntityListDaoImpl} </code> is implementation of
 * {@link PushedScheduledEntityListDao} and perform the database related operation for managing
 * {@link PushedScheduledEntityList}
 *
 * @author Ajit
 */
@Repository
public class PushedScheduledEntityListDaoImpl implements PushedScheduledEntityListDao{
    
    private static Logger logger = Logger.getLogger(PushedScheduledEntityListDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
     private MessageSource messageSource;
            

    
    /**
     * {@inheritDoc}
     */
    public PushedScheduledEntityList getByPushedScheduledEntityListId(Integer pushedScheduledEntityListId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledEntityList.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .setFetchMode("fkScheduledEntityListId", FetchMode.JOIN)
                    .add(Restrictions.eq("pushedScheduledEntityListId", pushedScheduledEntityListId));
            List<PushedScheduledEntityList> pushedScheduledEntityList = criteria.list();
            if (pushedScheduledEntityList.isEmpty()) {
                return null;
            }
            return (PushedScheduledEntityList) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
        }

    }

    /**
     * {@inheritDoc}
     */
    public Integer save(PushedScheduledEntityList pushedScheduledEntityList) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(pushedScheduledEntityList));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(PushedScheduledEntityList pushedScheduledEntityList) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().update(pushedScheduledEntityList);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

   /**
     * {@inheritDoc}
     */
    public void delete(PushedScheduledEntityList pushedScheduledEntityList) throws ProcessFailed {
         try {
            sessionFactory.getCurrentSession().delete(pushedScheduledEntityList);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }

   /**
     * {@inheritDoc}
     */
    public List<PushedScheduledEntityList> getAllPushedScheduledEntityListIdByFranchiseId(Integer franchiseId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledEntityList.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .setFetchMode("fkScheduledEntityListId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkFranchiseId.franchiseId", franchiseId));
            List<PushedScheduledEntityList> pushedScheduledEntityList = criteria.list();
            if (pushedScheduledEntityList.isEmpty()) {
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
    public List<PushedScheduledEntityList> getAllPushedScheduledEntityListIdByScheduledEntityListId(Integer scheduledEntityListId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PushedScheduledEntityList.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .setFetchMode("fkScheduledEntityListId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkScheduledEntityListId.scheduledEntityListId", scheduledEntityListId));
            List<PushedScheduledEntityList> pushedScheduledEntityList = criteria.list();
            if (pushedScheduledEntityList.isEmpty()) {
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retreving_message",new String[]{}, Locale.US));
        }
    }
    
}
