/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ActivityDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Activity;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link ActivityDaoImpl} </code> is implementation of
 * {@link ActivityDao} and perform the database related operation for managing
 * {@link Activity}
 *
 * @author Ajit
 */
@Repository
public class ActivityDaoImpl implements ActivityDao{

     private static Logger logger = Logger.getLogger(ActivityDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public Integer save(Activity activity) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(activity));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

   /**
     * {@inheritDoc}
     */
    public List<Activity> getAllActivity() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Activity.class);
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
    public void update(Activity activity) throws ProcessFailed {
      try {
             sessionFactory.getCurrentSession().update(activity);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Activity activity) throws ProcessFailed {
        try {
             sessionFactory.getCurrentSession().delete(activity);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

   /**
     * {@inheritDoc}
     */
    public Activity getActivityByactivityId(Integer activityId) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Activity.class)
                    .add(Restrictions.eq("activityId", activityId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (Activity)criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }
    
}
