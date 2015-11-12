/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.UserPreferencesDao;
import com.intbit.marketing.model.TblMarketingAction;
import com.intbit.marketing.model.TblUserPreferences;
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
 * @author Ajit
 */
@Repository
public class UserPreferencesDaoImpl implements UserPreferencesDao{
      private static final Logger logger = Logger.getLogger(ScheduledEntityListDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TblUserPreferences getById(Integer userId) throws Throwable {
             try {
                Criteria criteria=sessionFactory.getCurrentSession()
                        .createCriteria(TblUserPreferences.class)
                        .add(Restrictions.eq("id", userId));
                return (TblUserPreferences)criteria.list().get(0);
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			throw new Throwable("Database error while retrieving record");
            }
    }
    
}
