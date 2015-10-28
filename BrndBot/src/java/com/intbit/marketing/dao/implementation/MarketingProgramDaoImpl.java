/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.MarketingProgramDao;
import com.intbit.marketing.model.TblMarketingProgram;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intbit-6
 */
@Repository
public class MarketingProgramDaoImpl implements MarketingProgramDao{
    
    private static final Logger logger = Logger.getLogger(MarketingProgramDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    /**
	 * {@inheritDoc}
     */
    public TblMarketingProgram getById(Integer id) throws Throwable {
        try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblMarketingProgram.class)
                        .setFetchMode("tblMarketingCategory", FetchMode.JOIN)
                         .setFetchMode("tblUserLoginDetails", FetchMode.JOIN)
                        .add(Restrictions.eq("id", id));
                return (TblMarketingProgram)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

    /**
	 * {@inheritDoc}
     */
    public List<TblMarketingProgram> getAllTblMarketingProgram() throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblMarketingProgram.class);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

   /**
	 * {@inheritDoc}
     */
    public Integer save(TblMarketingProgram marketingProgram) throws Throwable {
        try {			
		 return ((Integer) sessionFactory.getCurrentSession().save(marketingProgram));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

   /**
	 * {@inheritDoc}
     */
    public void update(TblMarketingProgram marketingProgram) throws Throwable {
         try {			
		  sessionFactory.getCurrentSession().update(marketingProgram);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

   /**
	 * {@inheritDoc}
     */
    public void delete(Integer id) throws Throwable {
       try {
                TblMarketingProgram marketingProgram = getById(id);
                sessionFactory.getCurrentSession().delete(marketingProgram);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }

    @Override
    public List<TblMarketingProgram> getAllTblMarketingProgramForCategory(Integer marketingCategoryId, Integer userId) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblMarketingProgram.class)
                     .setFetchMode("tblMarketingCategory", FetchMode.JOIN)
                         .setFetchMode("tblUserLoginDetails", FetchMode.JOIN)
                    .add(Restrictions.eq("tblMarketingCategory.id", marketingCategoryId))
                    .add(Restrictions.eq("tblUserLoginDetails.id", userId));
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }
    
    
}
