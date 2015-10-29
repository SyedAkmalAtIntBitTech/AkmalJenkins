/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.MarketingActionDao;
import com.intbit.marketing.model.TblMarketingAction;
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
public class MarketingActionDaoImpl implements MarketingActionDao{
    
    private static final Logger logger = Logger.getLogger(MarketingActionDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    /**
	 * {@inheritDoc}
     */
    public TblMarketingAction getById(Integer id) throws Throwable {
       try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblMarketingAction.class)
                        .setFetchMode("tblMarketingCategory", FetchMode.JOIN)
                         .setFetchMode("tblMarketingProgram", FetchMode.JOIN)
                        .add(Restrictions.eq("id", id));
                return (TblMarketingAction)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

     /**
	 * {@inheritDoc}
     */
    public List<TblMarketingAction> getAllMarketingAction() throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblMarketingAction.class)
                     .setFetchMode("tblMarketingCategory", FetchMode.JOIN)
                      .setFetchMode("tblMarketingProgram", FetchMode.JOIN);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(TblMarketingAction marketingAction) throws Throwable {
        try {			
		 return ((Integer) sessionFactory.getCurrentSession().save(marketingAction));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

     /**
	 * {@inheritDoc}
     */
    public void update(TblMarketingAction marketingAction) throws Throwable {
       try {
            sessionFactory.getCurrentSession().update(marketingAction);
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
                TblMarketingAction marketingAction = getById(id);
                sessionFactory.getCurrentSession().delete(marketingAction);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }
    
    
}
