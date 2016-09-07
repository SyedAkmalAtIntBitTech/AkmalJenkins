/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.dao.impl;

import com.intbittech.marketing.dao.ScheduledEntityListDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ScheduledEntityList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;

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
public class ScheduledEntityListDaoImpl implements ScheduledEntityListDao {

    private static final Logger logger = Logger.getLogger(ScheduledEntityListDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    public ScheduledEntityList getById(Integer id) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyMarketingProgramId", FetchMode.JOIN)
                    .setFetchMode("fkRecurringEmailId", FetchMode.JOIN)
                    .add(Restrictions.eq("scheduledEntityListId", id));
            return (ScheduledEntityList) criteria.list().get(0);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    public ScheduledEntityList getEntityById(Integer id) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class)
                    .add(Restrictions.eq("scheduledEntityListId", id));
            return (ScheduledEntityList) criteria.list().get(0);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    public Integer getCurrentRecords(Integer program_id) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyMarketingProgramId", FetchMode.JOIN)
                    .setFetchMode("fkRecurringEmailId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyMarketingProgramId.companyMarketingProgramId", program_id));
            List<ScheduledEntityList> entity_list = criteria.list();
            return (Integer) entity_list.size();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ScheduledEntityList> getAllScheduledEmailList() throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class);
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(ScheduledEntityList scheduledEntityList) throws ProcessFailed {
        try {
            return ((Integer) sessionFactory.getCurrentSession().save(scheduledEntityList));
        } catch (Throwable throwable) {
            if (throwable instanceof org.hibernate.exception.ConstraintViolationException) {
                throw new ProcessFailed("Scheduled entity list already exits");
            }
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(ScheduledEntityList scheduledEntityList) throws ProcessFailed {
        try {
            sessionFactory.getCurrentSession().update(scheduledEntityList);
        } catch (ProcessFailed throwable) {

            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while updating record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer id) throws ProcessFailed {
        try {
            ScheduledEntityList scheduledEntityList = getById(id);
            sessionFactory.getCurrentSession().delete(scheduledEntityList);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ScheduledEntityList> getScheduledEntityLisId(Integer companyMarketingProgramId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyMarketingProgramId", FetchMode.JOIN)
                    .setFetchMode("fkRecurringEmailId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyMarketingProgramId.companyMarketingProgramId", companyMarketingProgramId));
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public String getLatestApprovedPost(String status, String entityType, String programStatus) throws ProcessFailed {

        try {

            StringBuilder sbSql = new StringBuilder();
            sbSql.append("select  entitytable.schedule_time\\:\\:time, entitytable.entity_id from scheduled_entity_list as entitytable, company_marketing_program as programtable");
            sbSql.append(" where programtable.company_marketing_program_id = entitytable.fk_company_marketing_program_id ");
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

        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public ScheduledEntityList getLatestApprovedFacebookPost(String status, String entityType, String programStatus) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ScheduledEntityList getLatestApprovedSendEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ScheduledEntityList getScheduledEntityListByEntityId(Integer entityId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyMarketingProgramId", FetchMode.JOIN)
                    .setFetchMode("fkRecurringEmailId", FetchMode.JOIN)
                    .add(Restrictions.eq("entityId", entityId));
            return (ScheduledEntityList) criteria.list().get(0);
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<ScheduledEntityList> getScheduledEntityListIdForEmailType(Integer companyMarketingProgramId, Boolean isRecurring) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyMarketingProgramId", FetchMode.JOIN)
                    .setFetchMode("fkRecurringEmailId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyMarketingProgramId.companyMarketingProgramId", companyMarketingProgramId))
                    .add(Restrictions.eq("isRecurring", isRecurring))
                    .add(Restrictions.eq("entityType", "Email"));
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }
    }

    @Override
    public List<ScheduledEntityList> getScheduledEntityListIdForSocialPostType(Integer companyMarketingProgramId) throws ProcessFailed {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(ScheduledEntityList.class)
                    .setFetchMode("fkCompanyId", FetchMode.JOIN)
                    .setFetchMode("fkCompanyMarketingProgramId", FetchMode.JOIN)
                    .setFetchMode("fkRecurringEmailId", FetchMode.JOIN)
                    .add(Restrictions.eq("fkCompanyMarketingProgramId.companyMarketingProgramId", companyMarketingProgramId));
            Criterion rest1 = Restrictions.and(Restrictions.eq("entityType", "Twitter"));
            Criterion rest2 = Restrictions.and(Restrictions.eq("entityType", "Facebook"));
            criteria.add(Restrictions.or(rest1, rest2));
            return criteria.list();
        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record(s).");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getLatestApprovedEmail(String status, String entityType, String programStatus, Boolean isRecurring) throws ProcessFailed {
        try {
            StringBuilder sbSql = new StringBuilder();

            if (isRecurring) {
                sbSql.append("select  entitytable.schedule_time\\:\\:time, entitytable.entity_id from scheduled_entity_list as entitytable, company_marketing_program as programtable");
                sbSql.append(" where programtable.company_marketing_program_id = entitytable.fk_company_marketing_program_id ");
                sbSql.append("and lower(programtable.status)");
                sbSql.append("like'").append(programStatus).append("'");
                sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
                sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
                sbSql.append(" and lower(entitytable. is_recuring) like '").append(isRecurring).append("'");
                sbSql.append("time(entitytable.schedule_time\\:\\:time AT TIME ZONE 'US/Eastern') > current_time AT TIME ZONE 'US/Eastern' ");
                sbSql.append("order by entitytable.schedule_time\\:\\:time");
                sbSql.append(" limit 1");
                sbSql.append(";");

            } else {
                sbSql.append("select  entitytable.schedule_time\\:\\:time, entitytable.entity_id from scheduled_entity_list as entitytable, company_marketing_program as programtable");
                sbSql.append(" where programtable.company_marketing_program_id = entitytable.fk_company_marketing_program_id ");
                sbSql.append("and lower(programtable.status)");
                sbSql.append("like'").append(programStatus).append("'");
                sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
                sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
                sbSql.append(" and lower(entitytable. is_recuring) like '").append(isRecurring).append("'");
                sbSql.append("date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' ");
                sbSql.append("order by entitytable.schedule_time\\:\\:time");
                sbSql.append(" limit 1");
                sbSql.append(";");
            }
            Query query = sessionFactory.getCurrentSession().createSQLQuery(sbSql.toString());

            List<Object> result = (List<Object>) query.list();
            String entityId = "";
            Iterator itr = result.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                entityId = String.valueOf(obj[1]);

            }
            return entityId;

        } catch (ProcessFailed throwable) {
            logger.log(Level.SEVERE, null, throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

}
