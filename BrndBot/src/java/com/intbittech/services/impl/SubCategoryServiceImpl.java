/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ExternalSourceKeywordLookupDao;
import com.intbittech.dao.SubCategoryDao;
import com.intbittech.dao.SubCategoryExternalSourceDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.model.ExternalSource;
import com.intbittech.model.ExternalSourceKeyword;
import com.intbittech.model.ExternalSourceKeywordLookup;
import com.intbittech.model.SubCategory;
import com.intbittech.model.SubCategoryExternalSource;
import com.intbittech.modelmappers.SubCategoryDetails;
import com.intbittech.services.SubCategoryService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class SubCategoryServiceImpl implements SubCategoryService {

    private static Logger logger = Logger.getLogger(SubCategoryServiceImpl.class);
    @Autowired
    private SubCategoryDao subCategoryDao;
    @Autowired
    private ExternalSourceKeywordLookupDao externalSourceKeywordLookupDao;
    
    @Autowired
    private SubCategoryExternalSourceDao subCategoryExternalSourceDao;

    /**
     * {@inheritDoc}
     */
    public Integer save(SubCategory subCategory) throws ProcessFailed {
        return subCategoryDao.Save(subCategory);
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategory subCategory) throws ProcessFailed {
        subCategoryDao.update(subCategory);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer subCategoryId) throws ProcessFailed {
        SubCategory subCategory = subCategoryDao.getSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new ProcessFailed("No Sub Category with id " + subCategoryId + ".");
        }
        subCategoryDao.delete(subCategory);
    }

    /**
     * {@inheritDoc}
     */
    public SubCategory getSubCategoryById(Integer subCategoryId) throws ProcessFailed {
        SubCategory subCategory = subCategoryDao.getSubCategoryById(subCategoryId);
        if (subCategory == null) {
            throw new ProcessFailed("No Sub Category found.");
        }
        return subCategory;
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategory> getAllSubCategoriesByCategoryId(Integer categoryId) throws ProcessFailed {
        List<SubCategory> subCategories = subCategoryDao.getAllSubCategoriesByCategoryId(categoryId);
        if (subCategories.isEmpty()) {
            throw new ProcessFailed("No Sub Categories found.");
        }
        return subCategories;
    }

    /**
     * {@inheritDoc}
     */
    public void saveSubCategory(SubCategoryDetails subCategoryDetails) throws ProcessFailed {
        try {

            /*  saving the sub category  */
            SubCategory subCategory = new SubCategory();
            subCategory.setSubCategoryName(subCategoryDetails.getSubCategoryName());
            Category category = new Category();
            category.setCategoryId(subCategoryDetails.getCategoryId());
            subCategory.setFkCategoryId(category);
            Integer subCategoryId = subCategoryDao.Save(subCategory);
            
            /*saving externalSourceKeywordLookup using externalSourceId and externalSourceKeywordId */
            ExternalSourceKeywordLookup externalSourceKeywordLookup = new ExternalSourceKeywordLookup();
            ExternalSource externalSource = new ExternalSource();
            externalSource.setExternalSourceId(subCategoryDetails.getExternalSourceId());
            externalSourceKeywordLookup.setFkExternalSourceId(externalSource);
            ExternalSourceKeyword externalSourceKeyword = new ExternalSourceKeyword();
            externalSourceKeyword.setExternalSourceKeywordId(subCategoryDetails.getExternalSourceKeywordId());
            externalSourceKeywordLookup.setFkExternalSourceKeywordId(externalSourceKeyword);
            Integer externalSourceKeywordLookupId = externalSourceKeywordLookupDao.save(externalSourceKeywordLookup);

            /*saving organizationCategoryLookup using categoryId and OrganizationId */
            SubCategoryExternalSource subCategoryExternalSource = new SubCategoryExternalSource();
            ExternalSourceKeywordLookup externalSourceKeywordLookupObject = new ExternalSourceKeywordLookup();
            externalSourceKeywordLookupObject.setExternalSourceKeywordLookupId(externalSourceKeywordLookupId);
            subCategoryExternalSource.setFkExternalSourceKeywordLookupId(externalSourceKeywordLookupObject);
            SubCategory subCategoryObject = new SubCategory();
            subCategoryObject.setSubCategoryId(subCategoryId);
            subCategoryExternalSource.setFkSubCategoryId(subCategoryObject);
            subCategoryExternalSourceDao.save(subCategoryExternalSource);
            
            
            
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving sub category");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryExternalSource> getAllSubCategoriesByCategoryID(Integer categoryId) throws ProcessFailed {
        try {
            List<SubCategoryExternalSource> subCategoryExternalSourceList = subCategoryExternalSourceDao.getAllSubCategoriesByCategoryID(categoryId);
        if (subCategoryExternalSourceList.isEmpty()) {
            throw new ProcessFailed("No Sub Categories found.");
        }
        return subCategoryExternalSourceList;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving sub categories");
        }
    }

}
