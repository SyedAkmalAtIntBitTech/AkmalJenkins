/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.GlobalFontsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalColors;
import com.intbittech.model.GlobalFonts;
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
public class GlobalFontsDaoImpl implements GlobalFontsDao {
    
    private static Logger logger = Logger.getLogger(CategoryDaoImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public GlobalFonts getGlobalFontsById(Integer globalFontsId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(GlobalFonts.class)
                    .add(Restrictions.eq("globalFontsId", globalFontsId));
            List<GlobalColors> globalColorsList = criteria.list();
            if (globalColorsList.isEmpty()) {
                return null;
            }
            return (GlobalFonts) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<GlobalFonts> getAllGlobalColors() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(GlobalFonts.class);
            List<GlobalFonts> globalFontsList = criteria.list();
            if (globalFontsList.isEmpty()) {
                return null;
            }
            return globalFontsList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(GlobalFonts globalFonts) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(globalFonts));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(GlobalFonts globalFonts) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(globalFonts);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(GlobalFonts globalFonts) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(globalFonts);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}
