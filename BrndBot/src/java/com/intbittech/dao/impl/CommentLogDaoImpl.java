/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.CommentLogDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CommentLog;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link CommentLogDaoImpl} </code> is implementation of
 * {@link CommentLogDao} and perform the database related operation for
 * managing {@link CommentLog}
 *
 * @author Ajit
 */
@Repository
public class CommentLogDaoImpl implements CommentLogDao{
    
    private static Logger logger = Logger.getLogger(CommentLogDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public Integer save(CommentLog commentLog) throws ProcessFailed {
          try {
            return ((Integer) sessionFactory.getCurrentSession().save(commentLog));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

   /**
     * {@inheritDoc}
     */
    public List<CommentLog> getAllCommentLog() throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CommentLog.class)
                    .setFetchMode("fkScheduledEntityid", FetchMode.JOIN)
                    .setFetchMode("commentedBy", FetchMode.JOIN);
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
    public List<CommentLog> getAllCommentLogByScheduledEntityListId(Integer scheduledEntityListId) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CommentLog.class)
                    .setFetchMode("fkScheduledEntityid", FetchMode.JOIN)
                    .setFetchMode("commentedBy", FetchMode.JOIN)
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
    public CommentLog getCommentLogByCommentLogId(Integer commentLogId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CommentLog.class)
                    .setFetchMode("fkScheduledEntityid", FetchMode.JOIN)
                    .setFetchMode("commentedBy", FetchMode.JOIN)
                    .add(Restrictions.eq("commentLogId", commentLogId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (CommentLog)criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(CommentLog commentLog) throws ProcessFailed {
        try {
             sessionFactory.getCurrentSession().delete(commentLog);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }
    
}
