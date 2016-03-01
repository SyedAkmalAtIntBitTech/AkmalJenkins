/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Organization;
import com.intbittech.model.SubCategory;
import java.util.List;

/**
 * <code> {@link SubCategoryDao} </code> Interface to get SubCategory details
 * from SubCategory table
 *
 * @author ilyas
 */
public interface SubCategoryDao {

    /**
     * This method saves {@link SubCategory} into the database.
     *
     * @param subCategory the SubCategory
     * @return the Integer
     * @throws ProcessFailed the process failed
     */
    public Integer Save(SubCategory subCategory) throws ProcessFailed;

    /**
     * This method update {@link SubCategory} updates existing data from the
     * database.
     *
     * @param subCategory the SubCategory
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategory subCategory) throws ProcessFailed;

    /**
     * This method delete particular {@link Organization} based on the
     * organization from the database.
     *
     * @param subCategory the SubCategory
     * @throws ProcessFailed the process failed
     */
    public void delete(SubCategory subCategory) throws ProcessFailed;

    /**
     * This method retrieves particular {@link SubCategory} from the database.
     *
     * @param subCategoryId the subCategoryId
     * @return {@link SubCategory}
     * @throws ProcessFailed the process failed
     */
    public SubCategory getSubCategoryById(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method retrieves the list of {@link SubCategory} from Database.
     *
     * @param categoryId the categoryId
     * @return {@link SubCategory}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategory> getAllSubCategoriesByCategoryId(Integer categoryId) throws ProcessFailed;

}
