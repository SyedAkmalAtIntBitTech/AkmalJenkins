/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.SubCategoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategory;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link SubCategoryDaoImpl} </code> is implementation of
 * {@link SubCategoryDao} and perform the database related operation for
 * managing {@link SubCategory}
 *
 * @author ilyas
 */
@Repository
public class SubCategoryDaoImpl implements SubCategoryDao {

    private static Logger logger = Logger.getLogger(SubCategoryDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public Integer Save(SubCategory subCategory) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(subCategory));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategory subCategory) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(subCategory);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }

    }

    /**
     * {@inheritDoc}
     */
    public void delete(SubCategory subCategory) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(subCategory);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public SubCategory getSubCategoryById(Integer subCategoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategory.class)
                    .setFetchMode("fkCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("subCategoryId", subCategoryId));
            List<SubCategory> subCategory = criteria.list();
            if (subCategory.isEmpty()) {
                return null;
            }
            return (SubCategory) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategory> getAllSubCategoriesByCategoryId(Integer categoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategory.class)
                    .setFetchMode("fkCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCategoryId.categoryId", categoryId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records.");
        }

    }

}
