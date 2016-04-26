/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.CategoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link CategoryDaoImpl} </code> is implementation of
 * {@link CategoryDao} and perform the database related operation for managing
 * {@link Category}
 *
 * @author Ajit
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    private static Logger logger = Logger.getLogger(CategoryDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public Category getByCategoryId(Integer categoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Category.class)
                    .setFetchMode("fkChannelId", FetchMode.JOIN)
                    .add(Restrictions.eq("categoryId", categoryId));
            List<Category> category = criteria.list();
            if (category.isEmpty()) {
                return null;
            }
            return (Category) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Category category) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(category));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(Category category) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(category);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Category category) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(category);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

}
