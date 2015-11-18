/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.ScheduledEntityType;
import com.intbit.marketing.dao.ScheduledSocialpostListDao;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intbit-6
 */
@Repository
public class ScheduledSocialpostListDaoImpl implements ScheduledSocialpostListDao {

    private static final Logger logger = Logger.getLogger(ScheduledSocialpostListDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public TblScheduledSocialpostList getByEntityId(Integer entityId) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledSocialpostList.class)
                    .setFetchMode("tblScheduledEntityList", FetchMode.JOIN)
                    .add(Restrictions.eq("id", entityId));
            return (TblScheduledSocialpostList) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record");
        }
    }

    @Override
    public List<TblScheduledSocialpostList> getAllScheduledSocialpostListForUserMarketingProgram(Integer UserMarketingId, Boolean isRecuring, String entityType) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledSocialpostList.class)
                    .setFetchMode("tblScheduledEntityList", FetchMode.JOIN)
                    .setFetchMode("tblScheduledEntityList.tblUserMarketingProgram", FetchMode.JOIN)
                    .createAlias("tblScheduledEntityList.tblUserMarketingProgram", "umId")
                    .add(Restrictions.eq("umId.id", UserMarketingId))
                    .createAlias("tblScheduledEntityList", "sl");
            Criterion rest1 = Restrictions.and(Restrictions.eq("sl.entityType",ScheduledEntityType.Twitter.toString()));
            Criterion rest2 = Restrictions.and(Restrictions.eq("sl.entityType",ScheduledEntityType.Facebook.toString()));
            criteria.add(Restrictions.or(rest1, rest2));
            return criteria.list();
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record(s).");
        }
    }

}
