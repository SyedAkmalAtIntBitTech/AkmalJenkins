/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.CategoryDao;
import com.intbittech.dao.FranchiseDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.model.Franchise;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
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
public class FranchiseDaoImpl implements FranchiseDao {

    private static Logger logger = Logger.getLogger(FranchiseDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Franchise getByFranchiseId(Integer franchiseId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Franchise.class)
                    .add(Restrictions.eq("franchiseId", franchiseId));
            List<Franchise> franchises = criteria.list();
            if (franchises.isEmpty()) {
                return null;
            }
            return (Franchise) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<Franchise> getAllFranchise() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Franchise.class);
            List<Franchise> franchises = criteria.list();
            if (franchises.isEmpty()) {
                return null;
            }
            return franchises;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public Integer save(Franchise franchise) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(franchise));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    @Override
    public void update(Franchise franchise) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(franchise);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    @Override
    public void delete(Franchise franchise) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(franchise);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

}
