/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.SubCategoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategory;
import com.intbittech.services.SubCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Override
    public Integer Save(SubCategory subCategory) throws ProcessFailed {
        return subCategoryDao.Save(subCategory);
    }

    @Override
    public void update(SubCategory subCategory) throws ProcessFailed {
        subCategoryDao.update(subCategory);
    }

    @Override
    public void delete(Integer subCategoryId) throws ProcessFailed {
        SubCategory subCategory = subCategoryDao.getSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new ProcessFailed("No Sub Category with id " + subCategoryId + ".");
        }
        subCategoryDao.delete(subCategory);
    }

    @Override
    public SubCategory getSubCategoryById(Integer subCategoryId) throws ProcessFailed {
        SubCategory subCategory = subCategoryDao.getSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new ProcessFailed("No Sub Category found.");
        }
        return subCategory;
    }

    @Override
    public List<SubCategory> getAllSubCategoriesByCategoryId(Integer categoryId) throws ProcessFailed {
        List<SubCategory> subCategories = subCategoryDao.getAllSubCategoriesByCategoryId(categoryId);
        if (subCategories.isEmpty()) {
            throw new ProcessFailed("No Sub Categories found.");
        }
        return subCategories;
    }

}
