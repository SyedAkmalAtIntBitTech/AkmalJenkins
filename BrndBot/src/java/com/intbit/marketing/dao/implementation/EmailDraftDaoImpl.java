/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.EmailDraftDao;
import com.intbit.marketing.model.TblEmailDraft;
import com.intbit.marketing.model.TblEmailDraft;
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
    public TblEmailDraft getById(Integer id) throws Throwable {
       try {
            Criteria criteria=sessionFactory.getCurrentSession()
                    .createCriteria(TblEmailDraft.class)
                    .add(Restrictions.eq("id", id));
            return (TblEmailDraft)criteria.list().get(0);
            } catch (Throwable throwable) {
                logger.log(Level.SEVERE, null, throwable);
                throw new Throwable("Database error while retrieving record");
            }
    }

     /**
	 * {@inheritDoc}
     */
    public List<TblEmailDraft> getAllEmailDrafts(Integer user_id) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblEmailDraft.class)
                    .add(Restrictions.eq("userId", user_id));
                   return criteria.list();
            } catch (Throwable throwable) {
               logger.log(Level.SEVERE, null, throwable);
               throw new Throwable("Database error while retrieving record(s).");
            }
    }

     /**
	 * {@inheritDoc}
     */
    public Integer save(TblEmailDraft emailDraft) throws Throwable {
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
    public void update(TblEmailDraft emailDraft) throws Throwable {
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
            TblEmailDraft emailDraft = getById(id);
            sessionFactory.getCurrentSession().delete(emailDraft);
            } catch (Throwable throwable) {
                logger.log(Level.SEVERE, null, throwable);
                throw new Throwable("Database error while retrieving record.");
            }
    }

}
