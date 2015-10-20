/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.UserMarketingProgramDao;
import com.intbit.marketing.model.TblUserMarketingProgram;
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
public class UserMarketingProgramDaoImpl implements UserMarketingProgramDao{
    
      private static final Logger logger = Logger.getLogger(UserMarketingProgramDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

   /**
	 * {@inheritDoc}
     */
    public TblUserMarketingProgram getById(Integer id) throws Throwable {
        try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblUserMarketingProgram.class)
                        .setFetchMode("tblUserLoginDetails", FetchMode.JOIN)
                        .add(Restrictions.eq("id", id));
                return (TblUserMarketingProgram)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

    /**
	 * {@inheritDoc}
     */
    public List<TblUserMarketingProgram> getAllUserMarketingProgram() throws Throwable {
       try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblUserMarketingProgram.class);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

    /**
	 * {@inheritDoc}
     */
    public Integer save(TblUserMarketingProgram userMarketingProgram) throws Throwable {
       try {			
		 return ((Integer) sessionFactory.getCurrentSession().save(userMarketingProgram));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

    /**
	 * {@inheritDoc}
     */
    public void update(TblUserMarketingProgram userMarketingProgram) throws Throwable {
        try {			
		  sessionFactory.getCurrentSession().update(userMarketingProgram);
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
                TblUserMarketingProgram userMarketingProgram = getById(id);
                sessionFactory.getCurrentSession().delete(userMarketingProgram);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }
    
}
