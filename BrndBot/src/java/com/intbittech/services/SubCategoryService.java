/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategory;
import java.util.List;

/**
 *
 * @author ilyas
 */
public interface SubCategoryService {

    public Integer Save(SubCategory subCategory) throws ProcessFailed;

    public void update(SubCategory subCategory) throws ProcessFailed;

    public void delete(Integer subCategoryId) throws ProcessFailed;

    public SubCategory getSubCategoryById(Integer subCategoryId) throws ProcessFailed;

    public List<SubCategory> getAllSubCategoriesByCategoryId(Integer categoryId) throws ProcessFailed;
}
