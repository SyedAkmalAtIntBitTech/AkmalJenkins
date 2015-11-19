/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.intbit.ConnectionManager;
import com.intbit.marketing.dao.implementation.ScheduledEntityListDaoImpl;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ResultSetMapper;

/**
 *
 * @author AR
 */
public class SchedulerUtilityMethods {

    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final Logger logger = Logger.getLogger(ScheduledEntityListDaoImpl.class.getName());

    public static String getLatestApprovedPost(String status, String entityType, String programStatus) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select  entitytable.schedule_time::time, entitytable.entity_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
        sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
        sbSql.append("and lower(programtable.status)");
        sbSql.append("like'").append(programStatus).append("'");
        sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
        sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
        sbSql.append("and date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' ");
        sbSql.append("order by entitytable.schedule_time::time");
        sbSql.append(" limit 1");
        sbSql.append(";");
        String entityId = "";

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                if (resultSet.next()) {
                    entityId = resultSet.getString(1);
                }
            }

            return entityId;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return entityId;
        }
    }

    public static String getLatestEmailApprovedPost(String status, String entityType, String programStatus, Boolean isRecuring) {

        StringBuilder sbSql = new StringBuilder();

        if (isRecuring) {
            sbSql.append("select  entitytable.schedule_time::time as scheduledTime, entitytable.entity_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
            sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
            sbSql.append("and lower(programtable.status)");
            sbSql.append("like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append(" and lower(entitytable. is_recuring) like '").append(isRecuring).append("'");
            //Need to review this
            sbSql.append("time(scheduledTime AT TIME ZONE 'US/Eastern') > current_time AT TIME ZONE 'US/Eastern' ");
            sbSql.append("order by scheduledTime");
            sbSql.append(" limit 1");
            sbSql.append(";");

        } else {
            sbSql.append("select  entitytable.schedule_time::time, entitytable.entity_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
            sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
            sbSql.append("and lower(programtable.status)");
            sbSql.append("like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append(" and lower(entitytable. is_recuring) like '").append(isRecuring).append("'");
            sbSql.append("date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' ");
            sbSql.append("order by entitytable.schedule_time::time");
            sbSql.append(" limit 1");
            sbSql.append(";");
        }
        String entityId = "";

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                if (resultSet.next()) {
                    entityId = resultSet.getString(1);
                }
            }

            return entityId;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return entityId;
        }

    }

    public static TblScheduledEntityList getEntityById(int entityId) {
        String sql = "select * from tbl_scheduled_entity_list where entity_id=" + entityId;
        ResultSetMapper<TblScheduledEntityList> resultSetMapper = new ResultSetMapper<>();

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                List<TblScheduledEntityList> pojoList = resultSetMapper.mapRersultSetToObject(resultSet, TblScheduledEntityList.class);
                if (pojoList != null) {
                    return pojoList.get(0);
                }
            }

            return null;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return null;
        }
    }

    public static TblScheduledSocialpostList getSocialPostEntityById(int entityId) {
        String sql = "select * from tbl_scheduled_socialpost_list where id=" + entityId;
        ResultSetMapper<TblScheduledSocialpostList> resultSetMapper = new ResultSetMapper<>();

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                List<TblScheduledSocialpostList> pojoList = resultSetMapper.mapRersultSetToObject(resultSet, TblScheduledSocialpostList.class);
                if (pojoList != null) {
                    return pojoList.get(0);
                }
            }

            return null;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return null;
        }
    }

    public static TblScheduledEmailList getEmailEntityById(int entityId) {
        String sql = "select * from tbl_scheduled_email_list where id=" + entityId;
        ResultSetMapper<TblScheduledEmailList> resultSetMapper = new ResultSetMapper<>();

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                List<TblScheduledEmailList> pojoList = resultSetMapper.mapRersultSetToObject(resultSet, TblScheduledEmailList.class);
                if (pojoList != null) {
                    return pojoList.get(0);
                }
            }

            return null;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return null;
        }
    }

    public static void updateScheduledEntityListEntity(TblScheduledEntityList scheduledFacebookPost) {
        String sql = "update tbl_scheduled_entity_list set status=" + scheduledFacebookPost.getStatus() + " where entity_id=" + scheduledFacebookPost.getEntityId() + ";";
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }

    }

}
