/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.intbittech.marketing.dao.CompanyMarketingProgramDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyMarketingProgram;
import java.util.Locale;

/**
 *
 * @author- @jit
 */
@Repository
public class CompanyMarketingProgramDaoImpl implements CompanyMarketingProgramDao {

    private static final Logger logger = Logger.getLogger(CompanyMarketingProgramDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public CompanyMarketingProgram getById(Integer id) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyMarketingProgram.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("companyMarketingProgramId", id));
            List<CompanyMarketingProgram> companyMarketingProgramList = criteria.list();
            if (companyMarketingProgramList.isEmpty()) {
                return null;
            }
            return (CompanyMarketingProgram) criteria.list().get(0);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgram() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyMarketingProgram.class);
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }
    }

    /**
     *
     * @param status
     * @param companyId
     * @return
     * @throws ProcessFailed
     */
    public List<CompanyMarketingProgram> getAllCompanyMarketingOpenPrograms(String status, Integer companyId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyMarketingProgram.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("status", status))
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .addOrder(Order.asc("dateEvent"));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(companyMarketingProgram));
        } catch (Throwable throwable) {
            if (throwable instanceof org.hibernate.exception.ConstraintViolationException) {
                throw new ProcessFailed("The entered marketing program name already exists for the chosen date. Please enter a different name.");
            }
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(CompanyMarketingProgram companyMarketingProgram) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(companyMarketingProgram);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer id) throws ProcessFailed {
        try {
            CompanyMarketingProgram companyMarketingProgram = getById(id);
            sessionFactory.getCurrentSession().delete(companyMarketingProgram);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public CompanyMarketingProgram getByCompanyMarketingProgramIdAndMarketingProgramId(Integer id, Integer marketingProgramId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyMarketingProgram.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("companyMarketingProgramId", id))
                    .add(Restrictions.eq("fkMarketingProgramId.marketingProgramId", marketingProgramId));
            List<CompanyMarketingProgram> companyMarketingProgramList = criteria.list();
            if (companyMarketingProgramList.isEmpty()) {
                return null;
            }
            return (CompanyMarketingProgram) criteria.list().get(0);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgramByType(Integer companyId, String programType) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyMarketingProgram.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("status", programType))
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId))
                    .addOrder(Order.asc("dateEvent"));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }
    }

    @Override
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgramByCompanyId(Integer companyId) throws ProcessFailed {

        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyMarketingProgram.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("status", "Open"))
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId));
            if (criteria.list().isEmpty()) {
                return null;
            }
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }

    }

    @Override
    public List<CompanyMarketingProgram> getAllCompanyMarketingProgramBySessionCompanyId(Integer companyId) throws ProcessFailed {

        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(CompanyMarketingProgram.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyId.companyId", companyId));
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }

    }

}
