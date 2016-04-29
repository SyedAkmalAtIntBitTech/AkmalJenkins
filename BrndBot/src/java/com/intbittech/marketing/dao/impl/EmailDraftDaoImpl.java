/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.dao.impl;

import com.intbittech.marketing.dao.EmailDraftDao;
import com.intbittech.model.EmailDraft;
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
 * @author development
 */
@Repository
public class EmailDraftDaoImpl implements EmailDraftDao {
    private static final Logger logger = Logger.getLogger(EmailDraftDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    /**
	 * {@inheritDoc}
     */
    public EmailDraft getById(Integer id) throws Throwable {
       try {
            Criteria criteria=sessionFactory.getCurrentSession()
                    .createCriteria(EmailDraft.class)
                    .add(Restrictions.eq("id", id));
            return (EmailDraft)criteria.list().get(0);
            } catch (Throwable throwable) {
                logger.log(Level.SEVERE, null, throwable);
                throw new Throwable("Database error while retrieving record");
            }
    }

     /**
	 * {@inheritDoc}
     */
    public List<EmailDraft> getAllEmailDrafts(Integer user_id) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(EmailDraft.class)
                    .add(Restrictions.eq("fkCompanyId.companyId", user_id));
                   return criteria.list();
            } catch (Throwable throwable) {
               logger.log(Level.SEVERE, null, throwable);
               throw new Throwable("Database error while retrieving record(s).");
            }
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(EmailDraft emailDraft) throws Throwable {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(emailDraft));
           } catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while saving record.");
           }
    }

     /**
	 * {@inheritDoc}
     */
    public void update(EmailDraft emailDraft) throws Throwable {
       try {
            sessionFactory.getCurrentSession().update(emailDraft);
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
            EmailDraft emailDraft = getById(id);
            sessionFactory.getCurrentSession().delete(emailDraft);
            } catch (Throwable throwable) {
                logger.log(Level.SEVERE, null, throwable);
                throw new Throwable("Database error while retrieving record.");
            }
    }

}
