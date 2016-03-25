/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.MarketingProgramDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingProgram;
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
 * <code> {@link MarketingProgramDaoImpl} </code> is implementation of
 * {@link MarketingProgramDao} and perform the database related operation for managing
 * {@link MarketingProgram}
 *
 * @author ilyas
 */
@Repository
public class MarketingProgramDaoImpl implements MarketingProgramDao {
    
    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingProgram> getAllMarketingPrograms() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingProgram.class);
            List<MarketingProgram> marketingProgramList = criteria.list();
            if (marketingProgramList.isEmpty()) {
                return null;
            }
            return  criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_list_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public MarketingProgram getByMarketingProgramId(Integer marketingProgramId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingProgram.class)
                    .add(Restrictions.eq("marketingProgramId", marketingProgramId));
            List<MarketingProgram> marketingProgramList = criteria.list();
            if (marketingProgramList.isEmpty()) {
                return null;
            }
            return (MarketingProgram) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingProgram marketingProgram) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(marketingProgram));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingProgram marketingProgram) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(marketingProgram);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(MarketingProgram marketingProgram) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(marketingProgram);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}
