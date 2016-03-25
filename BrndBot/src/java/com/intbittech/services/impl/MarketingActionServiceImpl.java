/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.MarketingActionDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingAction;
import com.intbittech.services.MarketingActionService;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class MarketingActionServiceImpl implements MarketingActionService {
    
    @Autowired
    private MarketingActionDao marketingActionDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingAction> getAllMarketingActions() throws ProcessFailed {
        List<MarketingAction> marketingActionList = marketingActionDao.getAllMarketingActions();
        if(marketingActionList == null)
            throw new ProcessFailed(messageSource.getMessage("marketingAction_list_not_found",new String[]{}, Locale.US));
        return marketingActionList;
    }

    /**
     * {@inheritDoc}
     */
    public MarketingAction getByMarketingActionById(Integer marketinActionId) throws ProcessFailed {
        MarketingAction marketingAction = marketingActionDao.getByMarketingActionById(marketinActionId);
        if(marketingAction == null)
            throw new ProcessFailed(messageSource.getMessage("marketingAction_not_found",new String[]{}, Locale.US));
        return marketingAction;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingAction marketingAction) throws ProcessFailed {
        return marketingActionDao.save(marketingAction);
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingAction marketingAction) throws ProcessFailed {
        marketingActionDao.update(marketingAction);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer marketingActionId) throws ProcessFailed {
        MarketingAction marketingAction = marketingActionDao.getByMarketingActionById(marketingActionId);
        if(marketingAction == null)
            throw new ProcessFailed(messageSource.getMessage("marketingAction_not_found_delete",new String[]{}, Locale.US));
        marketingActionDao.delete(marketingAction);
    }
    
}
