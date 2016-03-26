/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.MarketingCategoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingCategory;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationMarketingCategoryLookup;
import com.intbittech.modelmappers.MarketingCategoryDetails;
import com.intbittech.services.MarketingCategoryService;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class MarketingCategoryServiceImpl implements MarketingCategoryService {
    
    private static Logger logger = Logger.getLogger(MarketingCategoryServiceImpl.class);
    
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
            throw new ProcessFailed(messageSource.getMessage("marketingCategory_list_not_found",new String[]{}, Locale.US));
        return marketingCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    public MarketingCategory getByMarketingCategoryId(Integer marketingCategoryId) throws ProcessFailed {
        MarketingCategory marketingCategory = marketingCategoryDao.getByMarketingCategoryId(marketingCategoryId);
        if(marketingCategory == null)
            throw new ProcessFailed(messageSource.getMessage("marketingCategory_not_found",new String[]{}, Locale.US));
        return marketingCategory;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<OrganizationMarketingCategoryLookup> getByMarketingCategoriesByOrganizationId(Integer organizationId) throws ProcessFailed {
        List<OrganizationMarketingCategoryLookup> organizationMarketingCategoryList = marketingCategoryDao.getByMarketingCategoriesByOrganizationId(organizationId);
        if(organizationMarketingCategoryList == null)
            throw new ProcessFailed(messageSource.getMessage("marketingCategory_list_not_found",new String[]{}, Locale.US));
        return organizationMarketingCategoryList;
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
            throw new ProcessFailed(messageSource.getMessage("marketingCategory_not_found_delete",new String[]{}, Locale.US));
        marketingCategoryDao.delete(marketingCategory);
    }
    
    /**
     * {@inheritDoc}
     */
    public void saveMarketingCategory(MarketingCategoryDetails marketingCategoryDetails) throws ProcessFailed {
        try {
        MarketingCategory marketingCategory = new MarketingCategory();
        marketingCategory.setMarketingCategoryName(marketingCategoryDetails.getMarketingCategoryName());
        Integer marketingCategoryId = marketingCategoryDao.save(marketingCategory);
        
        
        OrganizationMarketingCategoryLookup organizationMarketingCategoryLookup = new OrganizationMarketingCategoryLookup();
        Organization organization = new Organization();
        organization.setOrganizationId(marketingCategoryDetails.getMarketingCategoryId());
        MarketingCategory marketingCategoryObject = new MarketingCategory();
        marketingCategoryObject.setMarketingCategoryId(marketingCategoryId);
        
        organizationMarketingCategoryLookup.setFkMarketingCategoryId(marketingCategoryObject);
        organizationMarketingCategoryLookup.setFkOrganizationId(organization);
        
        marketingCategoryDao.saveMarketingCategoryOrganization(organizationMarketingCategoryLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("marketingCategory_save_error",new String[]{}, Locale.US));
        }
        
        
    }
}
