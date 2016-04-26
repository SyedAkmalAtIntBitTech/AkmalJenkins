/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.RecurringEmailTemplateDao;
import com.intbit.marketing.model.TblRecurringEmailTemplate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intbit-6
 */
@Repository
public class RecurringEmailTemplateDaoImpl implements RecurringEmailTemplateDao{
    
    private static final Logger logger = Logger.getLogger(RecurringEmailTemplateDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

   /**
	 * {@inheritDoc}
     */
    public TblRecurringEmailTemplate getById(Integer id) throws Throwable {
         try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblRecurringEmailTemplate.class)
                        .add(Restrictions.eq("id", id));
                return (TblRecurringEmailTemplate)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

    /**
	 * {@inheritDoc}
     */
    public List<TblRecurringEmailTemplate> getAllRecurringEmailTemplate() throws Throwable {
       try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblRecurringEmailTemplate.class);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

   /**
	 * {@inheritDoc}
     */
    public Integer save(TblRecurringEmailTemplate recurringEmailTemplate) throws Throwable {
        try {			
		 return ((Integer) sessionFactory.getCurrentSession().save(recurringEmailTemplate));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

   /**
	 * {@inheritDoc}
     */
    public void update(TblRecurringEmailTemplate recurringEmailTemplate) throws Throwable {
        try {			
		  sessionFactory.getCurrentSession().update(recurringEmailTemplate);
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
                TblRecurringEmailTemplate recurringEmailTemplate = getById(id);
                sessionFactory.getCurrentSession().delete(recurringEmailTemplate);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }
    
}
