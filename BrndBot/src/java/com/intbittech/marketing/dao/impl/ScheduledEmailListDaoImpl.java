/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.dao.impl;

import com.intbit.ScheduledEntityType;
import com.intbittech.marketing.dao.ScheduledEmailListDao;
import com.intbittech.model.ScheduledEmailList;
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
    public ScheduledEmailList getById(Integer scheduledEmailListId) throws Throwable {
        try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(ScheduledEmailList.class)
                        .setFetchMode("fkCompanyId", FetchMode.JOIN)
                          .setFetchMode("fkScheduledEntityListId", FetchMode.JOIN)
                        .add(Restrictions.eq("scheduledEmailListId", scheduledEmailListId));
                List<ScheduledEmailList> scheduledEmailList = criteria.list();
                if(scheduledEmailList.isEmpty()){
                    return  null;
                }
                return (ScheduledEmailList)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

    /**
	 * {@inheritDoc}
     */
    public List<ScheduledEmailList> getAllScheduledEmailList() throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEmailList.class);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

    /**
	 * {@inheritDoc}
     */
    public Integer save(ScheduledEmailList scheduledEmailList) throws Throwable {
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
    public void update(ScheduledEmailList scheduledEmailList) throws Throwable {
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
                ScheduledEmailList scheduledEmailList = getById(id);
                sessionFactory.getCurrentSession().delete(scheduledEmailList);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }

    @Override
    public List<ScheduledEmailList> getAllScheduledEmailListForCompanyMarketingProgram(Integer comapanyMarketingProgramId, Boolean isRecurring) throws Throwable {
      try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEmailList.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                     .setFetchMode("fkScheduledEntityListId", FetchMode.JOIN)
                     .setFetchMode("fkScheduledEntityListId.fkCompanyMarketingProgramId", FetchMode.JOIN)
                      .createAlias("fkScheduledEntityListId.fkCompanyMarketingProgramId", "cmId")
                     .add(Restrictions.eq("cmId.id", comapanyMarketingProgramId))
                     .createAlias("fkScheduledEntityListId", "sl")
                     .add(Restrictions.eq("sl.isRecurring", isRecurring))
                     .add(Restrictions.eq("sl.entityType", ScheduledEntityType.Email.toString()));		
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}  
    }

  
    
}