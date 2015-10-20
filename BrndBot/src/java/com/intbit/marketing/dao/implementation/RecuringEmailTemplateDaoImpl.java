/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.RecuringEmailTemplateDao;
import com.intbit.marketing.model.TblRecuringEmailTemplate;
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
public class RecuringEmailTemplateDaoImpl implements RecuringEmailTemplateDao{
    
    private static final Logger logger = Logger.getLogger(RecuringEmailTemplateDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

   /**
	 * {@inheritDoc}
     */
    public TblRecuringEmailTemplate getById(Integer id) throws Throwable {
         try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblRecuringEmailTemplate.class)
                        .add(Restrictions.eq("id", id));
                return (TblRecuringEmailTemplate)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }

    /**
	 * {@inheritDoc}
     */
    public List<TblRecuringEmailTemplate> getAllRecuringEmailTemplate() throws Throwable {
       try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblRecuringEmailTemplate.class);
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}
    }

   /**
	 * {@inheritDoc}
     */
    public Integer save(TblRecuringEmailTemplate recuringEmailTemplate) throws Throwable {
        try {			
		 return ((Integer) sessionFactory.getCurrentSession().save(recuringEmailTemplate));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while saving record.");
		}
    }

   /**
	 * {@inheritDoc}
     */
    public void update(TblRecuringEmailTemplate recuringEmailTemplate) throws Throwable {
        try {			
		  sessionFactory.getCurrentSession().update(recuringEmailTemplate);
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
                TblRecuringEmailTemplate recuringEmailTemplate = getById(id);
                sessionFactory.getCurrentSession().delete(recuringEmailTemplate);
		} catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record.");
		}
    }
    
}
