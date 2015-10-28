/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.MarketingProgramUsersDao;
import com.intbit.marketing.model.TblMarketingProgramUsersLookup;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarketingProgramUsersDaoImpl implements MarketingProgramUsersDao {
 private static final Logger logger = Logger.getLogger(MarketingCategoryUsersDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public TblMarketingProgramUsersLookup getById(Integer id) throws Throwable {
    try {
            Criteria criteria=sessionFactory.getCurrentSession()
                    .createCriteria(TblMarketingProgramUsersLookup.class)
                    .setFetchMode("tbl_marketing_program", FetchMode.JOIN)
                    .setFetchMode("tbl_user_login_details", FetchMode.JOIN)
                    .add(Restrictions.eq("id", id));
            return (TblMarketingProgramUsersLookup)criteria.list().get(0);
            } catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while retrieving record");
            }
    }

    @Override
    public List<TblMarketingProgramUsersLookup> getAllMarketingProgramUsers() throws Throwable {
    try {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(TblMarketingProgramUsersLookup.class);
            return criteria.list();
         } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record(s).");
         }
    }

    @Override
    public Integer save(TblMarketingProgramUsersLookup marketingProgramUser) throws Throwable {
    try {
                return ((Integer) sessionFactory.getCurrentSession().save(marketingProgramUser));
            } catch (Throwable throwable) {
                    logger.log(Level.SEVERE, null, throwable);
                    throw new Throwable("Database error while saving record.");
            }
    }

    @Override
    public void delete(Integer id) throws Throwable {
    try {
            TblMarketingProgramUsersLookup marketingProgram = getById(id);
            sessionFactory.getCurrentSession().delete(marketingProgram);
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record.");
        }
    }
    
}
