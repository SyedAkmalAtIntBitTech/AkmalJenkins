/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategory;
import com.intbittech.model.SubCategoryExternalSource;
import com.intbittech.modelmappers.SubCategoryDetails;
import java.util.List;

/**
 * <code>{@link SubCategoryService}</code> is service layer interface for communicating
 * between Controller and DAO classes
 *
 * @author ilyas
 */
public interface SubCategoryService {

    /**
     * This method passes subCategory as input and gets the {@link SubCategory} from DAO
     * layer.
     *
     * @param subCategory the subCategory
     * @return {@link subCategoryId}
     * @throws ProcessFailed the process failed
     */
    
    public Integer save(SubCategory subCategory) throws ProcessFailed;

    /**
     * This method updates the {@link SubCategory} from DAO
     * layer.
     *
     * @param subCategory the subCategory
     * @throws ProcessFailed the process failed
     */
    public void update(SubCategory subCategory) throws ProcessFailed;

    /**
     * This method deletes the {@link SubCategory} from DAO
     * layer.
     *
     * @param subCategoryId the subCategoryId
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method retrieves the {@link SubCategory} from DAO
     * layer.
     *
     * @param subCategoryId the subCategoryId
     * @return {@link subCategoryId}
     * @throws ProcessFailed the process failed
     */
    public SubCategory getSubCategoryById(Integer subCategoryId) throws ProcessFailed;

    /**
     * This method retrieves list of {@link SubCategory} from DAO
     * layer.
     *
     * @param categoryId the categoryId
     * @return {@link subCategoryId}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategory> getAllSubCategoriesByCategoryId(Integer categoryId) throws ProcessFailed;
    
    /**
     * This method passes subCategory as input and gets the {@link SubCategory} from DAO
     * layer.
     *
     * @param subCategoryDetails the subCategoryDetails
     * @throws ProcessFailed the process failed
     */
    
    public void saveSubCategory(SubCategoryDetails subCategoryDetails) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link SubCategoryExternalSource} from DAO
     * layer.
     *
     * @param categoryId the categoryId
     * @return {@link SubCategoryExternalSource}
     * @throws ProcessFailed the process failed
     */
    public List<SubCategoryExternalSource> getAllSubCategoriesByCategoryID(Integer categoryId) throws ProcessFailed;
    
}
