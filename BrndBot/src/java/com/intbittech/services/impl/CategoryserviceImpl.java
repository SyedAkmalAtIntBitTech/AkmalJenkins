/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CategoryDao;
import com.intbittech.dao.OrganizationCategoryLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.model.Channel;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCategoryLookup;
import com.intbittech.modelmappers.CategoryDetails;
import com.intbittech.services.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class CategoryserviceImpl implements CategoryService {

    private static Logger logger = Logger.getLogger(CategoryserviceImpl.class);
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private OrganizationCategoryLookupDao organizationCategoryLookupDao;

    /**
     * {@inheritDoc}
     */
    public Category getByCategoryId(Integer categoryId) throws ProcessFailed {
        Category category = categoryDao.getByCategoryId(categoryId);
        if (category == null) {
            throw new ProcessFailed("No Category with id" + categoryId + ".");
        }
        return category;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Category category) throws ProcessFailed {
        return categoryDao.save(category);
    }

    /**
     * {@inheritDoc}
     */
    public void update(Category category) throws ProcessFailed {
        categoryDao.update(category);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer categoryId) throws ProcessFailed {
        Category category = categoryDao.getByCategoryId(categoryId);
        if (category == null) {
            throw new ProcessFailed("No Category with id" + categoryId + ".");
        }
        categoryDao.delete(category);
    }

    /**
     * {@inheritDoc}
     */
    public void saveCategory(CategoryDetails categoryDetails) throws ProcessFailed {
        try {

            /*  saving the category  */
            Category category = new Category();
            category.setCategoryName(categoryDetails.getCategoryName());
            Channel channel = new Channel();
            channel.setChannelId(categoryDetails.getChannelId());
            category.setFkChannelId(channel);
            Integer categoryId = categoryDao.save(category);

            /*saving organizationCategoryLookup using categoryId and OrganizationId */
            OrganizationCategoryLookup organizationCategoryLookup = new OrganizationCategoryLookup();
            Category categoryObject = new Category();
            categoryObject.setCategoryId(categoryId);
            organizationCategoryLookup.setFkCategoryId(categoryObject);
            Organization organization = new Organization();
            organization.setOrganizationId(categoryDetails.getOrgnizationId());
            organizationCategoryLookup.setFkOrganizationId(organization);
            organizationCategoryLookupDao.save(organizationCategoryLookup);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving category");
        }
    }
}
