/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.MarketingActionDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingAction;
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
 * <code> {@link MarketingActionDaoImpl} </code> is implementation of
 * {@link MarketingActionDao} and perform the database related operation for managing
 * {@link MarketingAction}
 *
 * @author ilyas
 */
@Repository
public class MarketingActionDaoImpl implements MarketingActionDao {
    
    private static Logger logger = Logger.getLogger(EmailModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingAction> getAllMarketingActions() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingAction.class);
            List<MarketingAction> marketingActionList = criteria.list();
            if (marketingActionList.isEmpty()) {
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
    public MarketingAction getByMarketingProgramId(Integer marketingActionId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MarketingAction.class)
                    .add(Restrictions.eq("marketingActionId", marketingActionId));
            List<MarketingAction> marketingActionList = criteria.list();
            if (marketingActionList.isEmpty()) {
                return null;
            }
            return (MarketingAction) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingAction marketingAction) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(marketingAction));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_saving_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingAction marketingAction) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(marketingAction);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message",new String[]{}, Locale.US));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(MarketingAction marketingAction) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(marketingAction);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_deleting_message",new String[]{}, Locale.US));
        }
    }
    
}
