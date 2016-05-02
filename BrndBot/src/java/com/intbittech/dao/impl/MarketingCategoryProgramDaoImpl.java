/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.MarketingCategoryProgramDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingAction;
import com.intbittech.model.MarketingCategoryProgram;
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
 * <code> {@link MarketingCategoryProgramDao} </code> is implementation of
 * {@link MarketingCategoryProgram} and perform the database related operation for managing
 * {@link MarketingCategoryProgram}
 *
 * @author ilyas
 */
@Repository
public class MarketingCategoryProgramDaoImpl implements MarketingCategoryProgramDao {
    
    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingCategoryProgram> getAllMarketingCategoryPrograms() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingCategoryProgram.class);
            List<MarketingAction> marketingCategoryProgramList = criteria.list();
            if (marketingCategoryProgramList.isEmpty()) {
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
    public MarketingCategoryProgram getByMarketingCategoryProgramId(Integer marketingCategoryProgramId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingCategoryProgram.class)
                    .add(Restrictions.eq("marketingCategoryProgramId", marketingCategoryProgramId));
            List<MarketingCategoryProgram> marketingCategoryProgramList = criteria.list();
            if (marketingCategoryProgramList.isEmpty()) {
                return null;
            }
            return (MarketingCategoryProgram) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingCategoryProgram marketingCategoryProgram) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(marketingCategoryProgram));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingCategoryProgram marketingCategoryProgram) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(marketingCategoryProgram);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(MarketingCategoryProgram marketingCategoryProgram) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(marketingCategoryProgram);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MarketingCategoryProgram> getMarketingProgramsByCategoryId(Integer marketingCategoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingCategoryProgram.class)
                    .setFetchMode("fkMarketingCategory", FetchMode.JOIN)
                    .setFetchMode("fkMarketingProgram", FetchMode.JOIN)
                    .add(Restrictions.eq("fkMarketingCategory.marketingCategoryId", marketingCategoryId));
            List<MarketingCategoryProgram> marketingCategoryProgramList = criteria.list();
            if (marketingCategoryProgramList.isEmpty()) {
                return null;
            }
            return marketingCategoryProgramList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }
    
}
