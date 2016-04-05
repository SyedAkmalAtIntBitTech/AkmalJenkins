/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.PrintModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.PrintModel;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link PrintModelDaoImpl} </code> is implementation of
 * {@link PrintModelDao} and perform the database related operation for managing
 * {@link PrintModel}
 *
 * @author ilyas
 */
@Repository
public class PrintModelDaoImpl implements PrintModelDao {

    private static Logger logger = Logger.getLogger(PrintModelDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    
    public List<PrintModel> getByAllPrintModel() throws ProcessFailed{
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PrintModel.class);
            List<PrintModel> printModelList = criteria.list();
            if (printModelList.isEmpty()) {
                return null;
            }
            return criteria.list();

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records.");
        }
    }
    public PrintModel getByPrintModelId(Integer printModelId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PrintModel.class)
                    .add(Restrictions.eq("printModelId", printModelId));
            List<PrintModel> printModelList = criteria.list();
            if (printModelList.isEmpty()) {
                return null;
            }
            return (PrintModel) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(PrintModel printModel) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(printModel));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(PrintModel printModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(printModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(PrintModel printModel) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(printModel);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<PrintModel> getByPrintModelsByIds(ArrayList<Integer> printModelIds) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PrintModel.class);
            for(int i =0 ; i<printModelIds.size();i++)
            criteria.add(Restrictions.ne("printModelId", printModelIds.get(i)));
            List<PrintModel> printModelList = criteria.list();
            if (printModelList.isEmpty()) {
                return null;
            }
            return printModelList;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving records");
        }
    }
    
}
