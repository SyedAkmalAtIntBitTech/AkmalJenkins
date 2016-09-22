/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.FranchiseCompanyLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.Franchise;
import com.intbittech.model.FranchiseCompanyLookup;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 * <code> {@link FranchiseCompanyLookupDaoImpl} </code> is implementation of
 * {@link FranchiseCompanyLookupDao} and perform the database related operation for managing
 * {@link FranchiseCompanyLookup}
 *
 * @author Ajit
 */
@Repository
public class FranchiseCompanyLookupDaoImpl implements FranchiseCompanyLookupDao {

    private static Logger logger = Logger.getLogger(FranchiseCompanyLookupDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private MessageSource messageSource;

    @Override
    public List<Company> getCompanyForFranchiseId(Franchise franchise) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(FranchiseCompanyLookup.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkFranchiseId.franchiseId", franchise.getFranchiseId()));
            List<FranchiseCompanyLookup> franchises = criteria.list();
            if (franchises.isEmpty()) {
                return null;
            }
            
            List<Company> companys = FranchiseCompanyLookup.getCompanys(franchises);
            return companys;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }                       
    }

    @Override
    public List<Franchise> getFranchiseForCompanyId(Company company) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(FranchiseCompanyLookup.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", company.getCompanyId()));
            List<FranchiseCompanyLookup> franchiseCompanyLookups = criteria.list();
            if (franchiseCompanyLookups.isEmpty()) {
                return null;
            }
            
            List<Franchise> franchises = FranchiseCompanyLookup.getFranchises(franchiseCompanyLookups);
            return franchises;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public Integer save(FranchiseCompanyLookup franchiseCompanyLookup) throws ProcessFailed {
         try {
            return ((Integer) sessionFactory.getCurrentSession().save(franchiseCompanyLookup));
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    @Override
    public void update(FranchiseCompanyLookup franchiseCompanyLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(franchiseCompanyLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    @Override
    public void delete(FranchiseCompanyLookup franchiseCompanyLookup) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().delete(franchiseCompanyLookup);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while deleting record.");
        }
    }

    @Override
    public FranchiseCompanyLookup getFranchiseLookup(Integer companyId, Integer franchiseId) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(FranchiseCompanyLookup.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .add(Restrictions.eq("fkFranchiseId.franchiseId", franchiseId));
            List<FranchiseCompanyLookup> franchiseCompanyLookups = criteria.list();
            if (franchiseCompanyLookups.isEmpty()) {
                return null;
            }
            FranchiseCompanyLookup franchiseCompanyLookup = franchiseCompanyLookups.get(0);
            return franchiseCompanyLookup;

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public FranchiseCompanyLookup getFranchiseHeadquarter(Integer franchiseId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(FranchiseCompanyLookup.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .add(Restrictions.eq("isHeadquarter", true))
                    .add(Restrictions.eq("fkFranchiseId.franchiseId", franchiseId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (FranchiseCompanyLookup) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }

    }

    /**
     * {@inheritDoc}
     */
    public FranchiseCompanyLookup getFranchiseByCompanyId(Integer companyId) throws ProcessFailed {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(FranchiseCompanyLookup.class)
                    .setFetchMode("fkFranchiseId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return (FranchiseCompanyLookup) criteria.list().get(0);

        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_retrieving_message",new String[]{}, Locale.US));
        }

    }

}
