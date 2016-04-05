/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingCategory;
import com.intbittech.model.OrganizationMarketingCategoryLookup;
import com.intbittech.modelmappers.MarketingCategoryDetails;
import java.util.List;

/**
 * <code>{@link MarketingCategoryService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author ilyas
 */
public interface MarketingCategoryService {
    
    /**
     * This method pass id as input and get the {@link MarketingCategory} from database.
     *
     * @return {@link MarketingCategory}
     * @throws ProcessFailed the process failed
     */
    public List<MarketingCategory> getAllMarketingCategories() throws ProcessFailed;

    /**
     * This method pass id as input and get the {@link MarketingCategory} from DAO
     * layer.
     *
     * @param marketingCategoryId the marketingCategoryId
     * @return {@link MarketingCategory}
     * @throws ProcessFailed the process failed
     */
    public MarketingCategory getByMarketingCategoryId(Integer marketingCategoryId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link OrganizationMarketingCategoryLookup} from DAO
     * layer.
     *
     * @param organizationId the organizationId
     * @return list of {@link OrganizationMarketingCategoryLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationMarketingCategoryLookup> getByOrganizationId(Integer organizationId) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link OrganizationMarketingCategoryLookup} from DAO
     * layer.
     *
     * @param organizationIds the organizationIds
     * @return list of {@link OrganizationMarketingCategoryLookup}
     * @throws ProcessFailed the process failed
     */
    public List<OrganizationMarketingCategoryLookup> getByOrganizationIds(Integer[] organizationIds) throws ProcessFailed;
    
    /**
     * This method save {@link MarketingCategory} into the database.
     *
     * @param marketingCategory the marketingCategory
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(MarketingCategory marketingCategory) throws ProcessFailed;

    /**
     * This method update {@link MarketingCategory} updates existing data from the
     * database.
     *
     * @param marketingCategory the marketingCategory
     * @throws ProcessFailed the process failed
     */
    public void update(MarketingCategory marketingCategory) throws ProcessFailed;

    /**
     * This method delete particular {@link MarketingCategory} based on the
     * MarketingCategory from the database.
     *
     * @param marketingCategoryId the marketingCategoryId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer marketingCategoryId) throws ProcessFailed;
    
    /**
     * This method save {@link MarketingCategory} into the database.
     *
     * @param marketingCategoryDetails the marketingCategoryDetails
     * @throws ProcessFailed the process failed
     */
    public void saveMarketingCategory(MarketingCategoryDetails marketingCategoryDetails) throws ProcessFailed;
    
}
