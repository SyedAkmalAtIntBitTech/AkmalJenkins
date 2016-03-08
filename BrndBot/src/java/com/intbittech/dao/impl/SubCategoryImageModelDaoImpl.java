/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.SubCategoryImageModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryImageModel;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link SubCategoryImageModelDaoImpl} </code> is implementation of
 * {@link SubCategoryImageModelDao} and perform the database related operation
 * for managing {@link SubCategoryImageModel}
 *
 * @author ilyas
 */
@Repository
public class SubCategoryImageModelDaoImpl implements SubCategoryImageModelDao {

    private static Logger logger = Logger.getLogger(SubCategoryImageModelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public SubCategoryImageModel getSubCategoryImageModelById(Integer subCategoryImageModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryImageModel.class)
                    .setFetchMode("fkImageModelId", FetchMode.JOIN)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("subCategoryImageModelId", subCategoryImageModelId));
            List<SubCategoryImageModel> subCategoryImageModelList = criteria.list();
            if (subCategoryImageModelList.isEmpty()) {
                return null;
            }
            return (SubCategoryImageModel) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryImageModel> getAllSubCategoryImageModel(Integer subCategoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryImageModel.class)
                    .setFetchMode("fkImageModelId", FetchMode.JOIN)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkSubCategoryId.subCategoryId", subCategoryId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(subCategoryImageModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(subCategoryImageModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(SubCategoryImageModel subCategoryImageModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(subCategoryImageModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

}
