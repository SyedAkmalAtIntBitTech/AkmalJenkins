/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.CategoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class CategoryserviceImpl implements CategoryService{
    
    @Autowired
    private CategoryDao categoryDao;

    /**
     * {@inheritDoc}
     */
    public Category getByCategoryId(Integer categoryId) throws ProcessFailed {
        Category category = categoryDao.getByCategoryId(categoryId);
        if(category == null){
            throw new ProcessFailed("No Category with id"+categoryId+".");
        }
        return category;
    }

     /**
     * {@inheritDoc}
     */
    public Integer save(Category category) throws ProcessFailed {
       return  categoryDao.save(category);
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
        if(category == null){
            throw new ProcessFailed("No Category with id"+categoryId+".");
        }
         categoryDao.delete(category);
    }
    
}
