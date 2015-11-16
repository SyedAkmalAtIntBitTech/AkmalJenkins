/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.dao.implementation;

import com.intbit.marketing.dao.ScheduledEntityListDao;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.OrderBy;
import org.hibernate.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intbit-6
 */
@Repository
public class ScheduledEntityListDaoImpl implements ScheduledEntityListDao {

    private static final Logger logger = Logger.getLogger(ScheduledEntityListDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public TblScheduledEntityList getById(Integer id) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEntityList.class)
                    .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                    .add(Restrictions.eq("id", id));
            return (TblScheduledEntityList) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record");
        }
    }

    public Integer getCurrentRecords(Integer program_id)throws Throwable{
        try {
                Criteria criteria = sessionFactory.getCurrentSession()
                        .createCriteria(TblScheduledEntityList.class)
                        .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                        .add(Restrictions.eq("tblUserMarketingProgram.id", program_id))
                        .add(Restrictions.eq("status", "no_template"));
                List<TblScheduledEntityList> entity_list = criteria.list();
                criteria = sessionFactory.getCurrentSession()
                        .createCriteria(TblScheduledEntityList.class)
                        .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                        .add(Restrictions.eq("tblUserMarketingProgram.id", program_id))
                        .add(Restrictions.eq("status", "template_saved"));
                List<TblScheduledEntityList> entity_list1 = criteria.list();
                
        return (Integer)entity_list.size() + (Integer)entity_list1.size();
        }catch (Throwable throwable){
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
            Criteria criteria = sessionFactory.getCurrentSession()
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
//    public TblScheduledEntityList getLatestApprovedFacebookPost(String status, String entityType,String programStatus) throws Throwable {
//        try {
//                   
////               Criteria criteria=sessionFactory.getCurrentSession()
////                       .createCriteria(TblScheduledEntityList.class)                   
////                        .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
////                        .createAlias("tblUserMarketingProgram", "tump")
////                       .add(Restrictions.eq("status", status))
////                        .add(Restrictions.eq("tump.status", programStatus))
////                       .add(Restrictions.eq("entityType",entityType));
//////                       .setProjection(Projections.min("tump.dateEvent"));
////                       
////               return (TblScheduledEntityList) criteria.list().get(0);
////              Query query = (Query) sessionFactory.getCurrentSession()
////                    .createSQLQuery("select entitytable.entity_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable where programtable.id = entitytable.user_marketing_program_id and lower(programtable.status) like 'open' and lower(entitytable.status) like 'approved' and entitytable.days > 0 and entitytable.entity_type like 'facebook' order by entitytable.schedule_time");
////                    
////                   return query.list().get(0);
//            StringBuilder sbSql1 = new StringBuilder();
//            sbSql1.append("select entitytable.entity_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
//            sbSql1.append(" where programtable.id = entitytable.user_marketing_program_id ");
//            sbSql1.append("and lower(programtable.status)");
//            sbSql1.append("like'").append(programStatus).append("'");
//            sbSql1.append(" and lower(entitytable.status) like ' ").append(status).append("'");
//            sbSql1.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
//             sbSql1.append(" ;");
////            Query query =  sessionFactory.getCurrentSession().createSQLQuery(sbSql1.toString());
////            return (TblScheduledEntityList) query;                
//
//               } catch (Throwable throwable) {
//                       logger.log(Level.SEVERE, null, throwable);
//                       throw new Throwable("Database error while retrieving record");
//           } 
//    }
//
//    @Override
//    public TblScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecuring) throws Throwable {
//         try{
//               Criteria criteria=sessionFactory.getCurrentSession()
//                    .createCriteria(TblScheduledEntityList.class)                   
//                     .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
//                     .createAlias("tblUserMarketingProgram", "tump")
//                    .add(Restrictions.eq("status", status))
//                    .add(Restrictions.eq("tump.status", programStatus))
//                    .add(Restrictions.eq("entityType",entityType))
//                    .add(Restrictions.eq("entityType",isRecuring));
//               return (TblScheduledEntityList) criteria.list().get(0);
//               }catch (Throwable throwable) {
//                    logger.log(Level.SEVERE, null, throwable);
//                    throw new Throwable("Database error while retrieving record");
//           } 
//    }
    @Override
    public String getLatestApprovedPost(String status, String entityType, String programStatus) throws Throwable {

        try {

            StringBuilder sbSql = new StringBuilder();
            sbSql.append("select  entitytable.schedule_time\\:\\:time, entitytable.entity_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
            sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
            sbSql.append("and lower(programtable.status)");
            sbSql.append("like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append("and date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' ");
            sbSql.append("order by entitytable.schedule_time\\:\\:time");
            sbSql.append(" limit 1");
            sbSql.append(";");
            Query query = sessionFactory.getCurrentSession().createSQLQuery(sbSql.toString());

            List<Object> result = (List<Object>) query.list();
            String entityId = "";
            Iterator itr = result.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                entityId = String.valueOf(obj[1]);

            }
            return entityId;

        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record");
        }
    }

    @Override
    public TblScheduledEntityList getLatestApprovedFacebookPost(String status, String entityType, String programStatus) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TblScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecuring) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TblScheduledEntityList getScheduledEntityListByEntityId(Integer entityId) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEntityList.class)
                    .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                    .add(Restrictions.eq("entityId", entityId));
            return (TblScheduledEntityList) criteria.list().get(0);
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record");
        }
    }

    @Override
    public List<TblScheduledEntityList> getScheduledEntityListIdForEmailType(Integer userMarketingProgramId, Boolean isRecuring) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEntityList.class)
                    .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                     .add(Restrictions.eq("tblUserMarketingProgram.id", userMarketingProgramId))
                     .add(Restrictions.eq("isRecuring", isRecuring))
                     .add(Restrictions.eq("entityType", "email"));		
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}  
    } 

    @Override
    public List<TblScheduledEntityList> getScheduledEntityListIdForSocialPostType(Integer userMarketingProgramId) throws Throwable {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(TblScheduledEntityList.class)
                    .setFetchMode("tblUserMarketingProgram", FetchMode.JOIN)
                     .add(Restrictions.eq("tblUserMarketingProgram.id", userMarketingProgramId));
                    Criterion rest1= Restrictions.and(Restrictions.eq("entityType", "twitter"));
                    Criterion rest2= Restrictions.and(Restrictions.eq("entityType", "facebook"));
                    criteria.add(Restrictions.or(rest1, rest2));
                   return criteria.list();
		} catch (Throwable throwable) {
                   logger.log(Level.SEVERE, null, throwable);
                   throw new Throwable("Database error while retrieving record(s).");
		}  
    }
    
    /**
     * {@inheritDoc}
     */
    public String getLatestApprovedEmail(String status, String entityType, String programStatus, Boolean isRecuring) throws Throwable {
        try {

            StringBuilder sbSql = new StringBuilder();
            sbSql.append("select  entitytable.schedule_time\\:\\:time, entitytable.entity_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
            sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
            sbSql.append("and lower(programtable.status)");
            sbSql.append("like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append(" and lower(entitytable. is_recuring) like '").append(isRecuring).append("'");
            //Need to review this
            if (isRecuring) {
                sbSql.append("date(programtable.date_event AT TIME ZONE 'US/Eastern') = current_date AT TIME ZONE 'US/Eastern' ");
            } else {
                sbSql.append("date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' ");
            }
            sbSql.append("order by entitytable.schedule_time\\:\\:time");
            sbSql.append(" limit 1");
            sbSql.append(";");
            Query query = sessionFactory.getCurrentSession().createSQLQuery(sbSql.toString());

            List<Object> result = (List<Object>) query.list();
            String entityId = "";
            Iterator itr = result.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                entityId = String.valueOf(obj[1]);

            }
            return entityId;

        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new Throwable("Database error while retrieving record");
        }
    }

}
    
 

    

