/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.SubCategoryPrintModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.SubCategoryPrintModel;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link SubCategoryPrintModelDaoImpl} </code> is implementation of
 * {@link SubCategoryPrintModelDao} and perform the database related operation
 * for managing {@link SubCategoryPrintModel}
 *
 * @author ilyas
 */
@Repository
public class SubCategoryPrintModelDaoImpl implements SubCategoryPrintModelDao {

    private static Logger logger = Logger.getLogger(SubCategoryPrintModelDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public SubCategoryPrintModel getSubCategoryPrintModelById(Integer subCategoryPrintModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryPrintModel.class)
                    .setFetchMode("fkPrintModelId", FetchMode.JOIN)
                    .setFetchMode("fkSubCategoryId", FetchMode.JOIN)
                    .add(Restrictions.eq("subCategoryPrintModelId", subCategoryPrintModelId));
            List<SubCategoryPrintModel> subCategoryPrintModelList = criteria.list();
            if (subCategoryPrintModelList.isEmpty()) {
                return null;
            }
            return (SubCategoryPrintModel) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<SubCategoryPrintModel> getAllSubCategoryPrintModel(Integer subCategoryId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(SubCategoryPrintModel.class)
                    .setFetchMode("fkPrintModelId", FetchMode.JOIN)
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
    public Integer save(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(subCategoryPrintModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(subCategoryPrintModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(SubCategoryPrintModel subCategoryPrintModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(subCategoryPrintModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

}
