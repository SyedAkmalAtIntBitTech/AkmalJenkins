/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.MarketingCategory;
import java.util.List;

/**
 * <code> {@link MarketingCategoryDao} </code> Interface to get MarketingCategory details from
 * marketing_category table
 *
 * @author ilyas
 */
public interface MarketingCategoryDao {
    
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
     * @param marketingCategory the marketingCategory
     * @throws ProcessFailed the process failed
     */
    public void delete(MarketingCategory marketingCategory) throws ProcessFailed;
    
}
