/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.MarketingCategoryUsersDao;
import com.intbit.marketing.model.TblMarketingCategoryUsersLookup;
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
public class MarketingCategoryUsersDaoImpl implements MarketingCategoryUsersDao{
    private static final Logger logger = Logger.getLogger(MarketingCategoryUsersDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;
    
   /**
	 * {@inheritDoc}
     */
    public TblMarketingCategoryUsersLookup getById(Integer id) throws Throwable {
        try {
            Criteria criteria=sessionFactory.getCurrentSession()
                    .createCriteria(TblMarketingCategoryUsersLookup.class)
                    .setFetchMode("tbl_marketing_category", FetchMode.JOIN)
                    .setFetchMode("tbl_user_login_details", FetchMode.JOIN)
                    .add(Restrictions.eq("id", id));
            return (TblMarketingCategoryUsersLookup)criteria.list().get(0);
            } catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record");
            }
        }
    /**
         * {@inheritDoc}
    */
    public List<TblMarketingCategoryUsersLookup> getAllMarketingCategoryUsers() throws Throwable {
      try {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(TblMarketingCategoryUsersLookup.class);
            return criteria.list();
         } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record(s).");
         }
    }

  /**
	* {@inheritDoc}
	 */
    public Integer save(TblMarketingCategoryUsersLookup marketingCategory) throws Throwable {
        try {
                return ((Integer) sessionFactory.getCurrentSession().save(marketingCategory));
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
            TblMarketingCategoryUsersLookup marketingCategory = getById(id);
            sessionFactory.getCurrentSession().delete(marketingCategory);
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record.");
        }
		
    }

}
