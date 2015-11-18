/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.ScheduledEntityType;
import com.intbit.marketing.dao.ScheduledEmailListDao;
import com.intbit.marketing.model.TblScheduledEmailList;
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
public class ScheduledEmailListDaoImpl  implements ScheduledEmailListDao{
    
     private static final Logger logger = Logger.getLogger(ScheduledEmailListDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

     /**
	 * {@inheritDoc}
     */
    public TblScheduledEmailList getById(Integer id) throws Throwable {
        try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblScheduledEmailList.class)
                        .setFetchMode("tblUserLoginDetails", FetchMode.JOIN)
                        .add(Restrictions.eq("id", id));
                return (TblScheduledEmailList)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

    /**
	 * {@inheritDoc}
     */
    public List<TblScheduledEmailList> getAllScheduledEmailList() throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEmailList.class);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

    /**
	 * {@inheritDoc}
     */
    public Integer save(TblScheduledEmailList scheduledEmailList) throws Throwable {
       try {			
		 return ((Integer) sessionFactory.getCurrentSession().save(scheduledEmailList));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

    /**
	 * {@inheritDoc}
     */
    public void update(TblScheduledEmailList scheduledEmailList) throws Throwable {
         try {			
		  sessionFactory.getCurrentSession().update(scheduledEmailList);
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
                TblScheduledEmailList scheduledEmailList = getById(id);
                sessionFactory.getCurrentSession().delete(scheduledEmailList);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }

    @Override
    public List<TblScheduledEmailList> getAllScheduledEmailListForUserMarketingProgram(Integer UserMarketingId, Boolean isRecuring) throws Throwable {
      try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEmailList.class)
                    .setFetchMode("tblUserLoginDetails", FetchMode.JOIN)
                    .setFetchMode("tblScheduledEntityList", FetchMode.JOIN)
                     .setFetchMode("tblScheduledEntityList.tblUserMarketingProgram", FetchMode.JOIN)
                      .createAlias("tblScheduledEntityList.tblUserMarketingProgram", "umId")
                     .add(Restrictions.eq("umId.id", UserMarketingId))
                     .createAlias("tblScheduledEntityList", "sl")
                     .add(Restrictions.eq("sl.isRecuring", isRecuring))
                     .add(Restrictions.eq("sl.entityType", ScheduledEntityType.Email.toString()));		
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}  
    }
    
}