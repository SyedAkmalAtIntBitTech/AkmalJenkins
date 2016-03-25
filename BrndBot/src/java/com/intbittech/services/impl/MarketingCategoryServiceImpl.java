/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.MarketingCategoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingCategory;
import com.intbittech.services.MarketingCategoryService;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class MarketingCategoryServiceImpl implements MarketingCategoryService {
    
    @Autowired
    private MarketingCategoryDao marketingCategoryDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public List<MarketingCategory> getAllMarketingCategories() throws ProcessFailed {
        List<MarketingCategory> marketingCategoryList = marketingCategoryDao.getAllMarketingCategories();
        if(marketingCategoryList == null)
            throw new ProcessFailed(messageSource.getMessage("marketingCategry_list_not_found",new String[]{}, Locale.US));
        return marketingCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    public MarketingCategory getByMarketingCategoryId(Integer marketingCategoryId) throws ProcessFailed {
        MarketingCategory marketingCategory = marketingCategoryDao.getByMarketingCategoryId(marketingCategoryId);
        if(marketingCategory == null)
            throw new ProcessFailed(messageSource.getMessage("marketingCategry_not_found",new String[]{}, Locale.US));
        return marketingCategory;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(MarketingCategory marketingCategory) throws ProcessFailed {
        return marketingCategoryDao.save(marketingCategory);
    }

    /**
     * {@inheritDoc}
     */
    public void update(MarketingCategory marketingCategory) throws ProcessFailed {
        marketingCategoryDao.update(marketingCategory);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer marketingCategoryId) throws ProcessFailed {
        MarketingCategory marketingCategory = marketingCategoryDao.getByMarketingCategoryId(marketingCategoryId);
        if(marketingCategory == null)
            throw new ProcessFailed(messageSource.getMessage("marketingCategry_not_found_delete",new String[]{}, Locale.US));
        marketingCategoryDao.delete(marketingCategory);
    }
    
}
