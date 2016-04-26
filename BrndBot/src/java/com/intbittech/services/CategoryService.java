/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.modelmappers.CategoryDetails;

/**
 * <code>{@link CategoryService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface CategoryService {

    /**
     * This method pass id as input and get the {@link Category} from DAO layer.
     *
     * @param categoryId the categoryId
     * @return {@link Category}
     * @throws ProcessFailed the process failed
     */
    public Category getByCategoryId(Integer categoryId) throws ProcessFailed;

    /**
     * This method save {@link Category} into the database.
     *
     * @param category the Category
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer save(Category category) throws ProcessFailed;

    /**
     * This method update {@link Category} updates existing data from the
     * database.
     *
     * @param category the category
     * @throws ProcessFailed the process failed
     */
    public void update(Category category) throws ProcessFailed;

    /**
     * This method delete particular {@link Category} based on the organization
     * from the database.
     *
     * @param categoryId the categoryId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer categoryId) throws ProcessFailed;
    
    /**
     * This method save {@link Category} into the database.
     *
     * @param categoryDetails the categoryDetails
     * @throws ProcessFailed the process failed
     */
    public void saveCategory(CategoryDetails categoryDetails) throws ProcessFailed;

}
