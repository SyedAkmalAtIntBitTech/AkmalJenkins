/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.CategoryDao;
import com.intbittech.dao.CompanyPreferencesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
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
public class CompanyPreferencesDaoImpl implements CompanyPreferencesDao {

    private static Logger logger = Logger.getLogger(CompanyPreferencesDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public Integer save(CompanyPreferences companyPreferences) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(companyPreferences));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(CompanyPreferences companyPreferences) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(CompanyPreferences companyPreferences) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

    @Override
    public void saveOrUpdate(CompanyPreferences companyPreferences) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    @Override
    public CompanyPreferences getByCompany(Company company) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyPreferences.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", company.getCompanyId()));
            List<Category> category = criteria.list();
            if (category.isEmpty()) {
                return null;
            }
            return (CompanyPreferences) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<CompanyPreferences> getByLocationId(String locationId) {
          try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyPreferences.class)
                    .add(Restrictions.eq("companyLocation", locationId));
            List<CompanyPreferences> list = criteria.list();
            if (list.isEmpty()) {
                return null;
            }
            return list;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<CompanyPreferences> getAll() {
          try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyPreferences.class);
            List<CompanyPreferences> list = criteria.list();
            if (list.isEmpty()) {
                return null;
            }
            return list;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }
}
