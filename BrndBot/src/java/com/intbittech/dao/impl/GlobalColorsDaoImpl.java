/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.GlobalColorsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalColors;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

@Repository
public class GlobalColorsDaoImpl implements GlobalColorsDao {
    
    private static Logger logger = Logger.getLogger(CategoryDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */
    public GlobalColors getGlobalColorsById(Integer globalColorsId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(GlobalColors.class)
                    .add(Restrictions.eq("globalColorsId", globalColorsId));
            List<GlobalColors> globalColorsList = criteria.list();
            if (globalColorsList.isEmpty()) {
                return null;
            }
            return (GlobalColors) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<GlobalColors> getAllGlobalColors() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(GlobalColors.class);
            List<GlobalColors> globalColorsList = criteria.list();
            if (globalColorsList.isEmpty()) {
                return null;
            }
            return globalColorsList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(GlobalColors globalColors) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(globalColors));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(GlobalColors globalColors) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(globalColors);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(GlobalColors globalColors) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(globalColors);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}