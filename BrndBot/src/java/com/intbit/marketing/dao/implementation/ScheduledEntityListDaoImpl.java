/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.ScheduledEntityListDao;
import com.intbit.marketing.model.TblScheduledEntityList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.OrderBy;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intbit-6
 */
@Repository
public class ScheduledEntityListDaoImpl implements ScheduledEntityListDao{
    
      private static final Logger logger = Logger.getLogger(ScheduledEntityListDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

   
    /**
	 * {@inheritDoc}
     */
    public TblScheduledEntityList getById(Integer id) throws Throwable {
        try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblScheduledEntityList.class)
                        .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                        .add(Restrictions.eq("id", id));
                return (TblScheduledEntityList)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

    
    /**
	 * {@inheritDoc}
     */
    public List<TblScheduledEntityList> getAllScheduledEmailList() throws Throwable {
         try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEntityList.class);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

    
    /**
	 * {@inheritDoc}
     */
    public Integer save(TblScheduledEntityList scheduledEntityList) throws Throwable {
       try {			
		 return ((Integer) sessionFactory.getCurrentSession().save(scheduledEntityList));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

   
    /**
	 * {@inheritDoc}
     */
    public void update(TblScheduledEntityList scheduledEntityList) throws Throwable {
        try {			
		  sessionFactory.getCurrentSession().update(scheduledEntityList);
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
                TblScheduledEntityList scheduledEntityList = getById(id);
                sessionFactory.getCurrentSession().delete(scheduledEntityList);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }

    /**
	 * {@inheritDoc}
     */
    public List<TblScheduledEntityList> getScheduledEntityLisId(Integer userMarketingProgramId) throws Throwable {
       try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblScheduledEntityList.class)                   
                         .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                        .add(Restrictions.eq("tblUserMarketingProgram.id", userMarketingProgramId));
                return criteria.list();
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

     /**
	 * {@inheritDoc}
     */
    public TblScheduledEntityList getLatestApprovedFacebookPost(String status, String entityType,String programStatus) throws Throwable {
        try {
                   
               Criteria criteria=sessionFactory.getCurrentSession()
                       .createCriteria(TblScheduledEntityList.class)                   
                        .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                        .createAlias("tblUserMarketingProgram", "tump")
                       .add(Restrictions.eq("status", status))
                        .add(Restrictions.eq("tump.status", programStatus))
                       .add(Restrictions.eq("entityType",entityType));
//                       .setProjection(Projections.min("tump.dateEvent"));
                       
               
               return (TblScheduledEntityList) criteria.list().get(0);
               } catch (Throwable throwable) {
                       logger.log(Level.SEVERE, null, throwable);
                       throw new Throwable("Database error while retrieving record");
           } 
    }

    @Override
    public TblScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecuring) throws Throwable {
         try{
               Criteria criteria=sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEntityList.class)                   
                     .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                     .createAlias("tblUserMarketingProgram", "tump")
                    .add(Restrictions.eq("status", status))
                    .add(Restrictions.eq("tump.status", programStatus))
                    .add(Restrictions.eq("entityType",entityType))
                    .add(Restrictions.eq("entityType",isRecuring));
               return (TblScheduledEntityList) criteria.list().get(0);
               }catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record");
           } 
    }



    
}
