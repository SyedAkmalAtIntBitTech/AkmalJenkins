/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.SubCategoryEmailModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryEmailModel;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link SubCategoryEmailModelDaoImpl} </code> is implementation of
 * {@link SubCategoryEmailModelDao} and perform the database related operation
 * for managing {@link SubCategoryEmailModel}
 *
 * @author Ajit
 */
@Repository
public class SubCategoryEmailModelDaoImpl implements SubCategoryEmailModelDao {

    private static Logger logger = Logger.getLogger(SubCategoryEmailModelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryEmailModel> getAllSubCategoryEmailModel(Integer subCategoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryEmailModel.class)
                    .setFetchMode("fkEmailModelId", FetchMode.JOIN)
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
    public Integer save(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(subCategoryEmailModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(subCategoryEmailModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(SubCategoryEmailModel subCategoryEmailModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(subCategoryEmailModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public SubCategoryEmailModel getSubCategoryEmailModelById(Integer subCategoryEmailModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryEmailModel.class)
                    .setFetchMode("fkEmailModelId", FetchMode.JOIN)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("subCategoryEmailModelId", subCategoryEmailModelId));
            List<SubCategoryEmailModel> subCategoryEmailModelList = criteria.list();
            if (subCategoryEmailModelList.isEmpty()) {
                return null;
            }
            return (SubCategoryEmailModel) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }

    }

    @Override
    public SubCategoryEmailModel getBySubCategoryEmailModelId(Integer emailModelId) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryEmailModel.class)
                    .setFetchMode("fkEmailModelId", FetchMode.JOIN)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("subCategoryEmailModelId", emailModelId));
            List<SubCategoryEmailModel> subCategoryEmailModelList = criteria.list();
            if (subCategoryEmailModelList.isEmpty()) {
                return null;
            }
            return (SubCategoryEmailModel) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }
    }

}
