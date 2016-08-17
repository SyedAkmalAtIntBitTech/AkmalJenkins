/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.ActivityLogDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ActivityLog;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link ActivityLogDaoImpl} </code> is implementation of
 * {@link ActivityLogDao} and perform the database related operation for
 * managing {@link ActivityLog}
 *
 * @author Ajit
 */
@Repository
public class ActivityLogDaoImpl implements ActivityLogDao {

    private static Logger logger = Logger.getLogger(ActivityLogDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public Integer save(ActivityLog activityLog) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(activityLog));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ActivityLog> getAllActivityLog() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ActivityLog.class);
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
    public List<ActivityLog> getAllActivityLogByScheduledEntityListId(Integer scheduledEntityListId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ActivityLog.class)
                    .setFetchMode("fkScheduledEntityid", FetchMode.JOIN)
                    .setFetchMode("fkActivityId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkScheduledEntityid.scheduledEntityListId", scheduledEntityListId));
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
    public ActivityLog getActivityLogByActivityLogId(Integer activityLogId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ActivityLog.class)
                    .setFetchMode("fkScheduledEntityid", FetchMode.JOIN)
                    .setFetchMode("fkActivityId", FetchMode.JOIN)
                    .add(Restrictions.eq("activityLogId", activityLogId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (ActivityLog) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

}
