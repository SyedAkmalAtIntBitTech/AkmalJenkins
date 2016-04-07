/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.GlobalImagesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.GlobalColors;
import com.intbittech.model.GlobalImages;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Repository
public class GlobalImagesDaoImpl implements GlobalImagesDao {

    private static Logger logger = Logger.getLogger(GlobalImagesDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public GlobalImages getGlobalImagesById(Integer globalImagesId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(GlobalImages.class)
                    .add(Restrictions.eq("globalImagesId", globalImagesId));
            List<GlobalImages> globalImagesList = criteria.list();
            if (globalImagesList.isEmpty()) {
                return null;
            }
            return (GlobalImages) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GlobalImages> getAllGlobalImages() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(GlobalImages.class);
            List<GlobalImages> globalImagesList = criteria.list();
            if (globalImagesList.isEmpty()) {
                return null;
            }
            return globalImagesList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer save(GlobalImages globalImages) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(globalImages));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GlobalImages globalImages) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(globalImages);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", null, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(GlobalImages globalImages) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(globalImages);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message", null, Locale.US));
        }
    }

}
