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
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AR
 */
public class SchedulerUtilityMethods {

    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final Logger logger = Logger.getLogger(ScheduledEntityListDaoImpl.class.getName());

    public static String getLatestApprovedPost(String status, String entityType, String programStatus) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select  entitytable.schedule_time::time,entitytable.entity_id as en_id  from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
        sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
        sbSql.append(" and lower(programtable.status)");
        sbSql.append(" like'").append(programStatus).append("'");
        sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
        sbSql.append(" and entitytable.entity_type like'").append(entityType).append("'");
//        sbSql.append(" and (date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' or (date(entitytable.schedule_time AT TIME ZONE 'US/Eastern'))= current_date AT TIME ZONE 'US/Eastern')");
        sbSql.append(" and date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' ");
        sbSql.append(" order by entitytable.schedule_time::time");
        sbSql.append(" limit 1");
        sbSql.append(";");
        String entityId = "";

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                if (resultSet.next()) {
                    int e = resultSet.getInt("en_id");
                    entityId = Integer.toString(e);
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
            sbSql.append("select  entitytable.schedule_time::time, entitytable.entity_id as en_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
            sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
            sbSql.append(" and lower(programtable.status)");
            sbSql.append("like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append(" and entitytable.is_recuring =  '").append(isRecuring).append("'");
            //Need to review this
            sbSql.append(" and date_trunc('minute', entitytable.schedule_time)::time AT TIME ZONE 'US/Eastern' >= date_trunc('minute', localtimestamp)::time AT TIME ZONE 'US/Eastern' ");
//            sbSql.append(" and entitytable.schedule_time::time AT TIME ZONE 'US/Eastern' >= current_time AT TIME ZONE 'US/Eastern' ");
            sbSql.append(" order by entitytable.schedule_time::time");
            sbSql.append(" limit 1");
            sbSql.append(";");

        } else {            
            sbSql.append("select  entitytable.schedule_time::time, entitytable.entity_id as en_id from tbl_scheduled_entity_list as entitytable, tbl_user_marketing_program as programtable");
            sbSql.append(" where programtable.id = entitytable.user_marketing_program_id ");
            sbSql.append(" and lower(programtable.status)");
            sbSql.append(" like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append(" and entitytable.is_recuring = '").append(isRecuring).append("'");
            sbSql.append(" and date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern'");
            sbSql.append(" order by entitytable.schedule_time::time");
            sbSql.append(" limit 1");
            sbSql.append(";");
        }
        String entityId = "";

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                if (resultSet.next()) {
                    entityId = String.valueOf(resultSet.getInt("en_id"));
                }
            }

            return entityId;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return entityId;
        }

    }

    public static TblScheduledEntityList getEntityById(int entityId) {
        String sql = "select entitytable.*,concat(date(programtable.date_event) - entitytable.days, ' ', entitytable.schedule_time::time WITH TIME ZONE) as cal_schedule_time,concat(date(programtable.date_event), ' ', entitytable.schedule_time::time WITH TIME ZONE) as cal_rec_schedule_time from tbl_scheduled_entity_list as entitytable,tbl_user_marketing_program as programtable "
                + "where programtable.id = entitytable.user_marketing_program_id and entity_id=" + entityId;
        TblScheduledEntityList result = null;

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                if (resultSet.next()) {
                    result = new TblScheduledEntityList(resultSet.getInt("id"));
                    result.setEntityId(resultSet.getInt("entity_id"));
                    TblUserMarketingProgram uPgm = new TblUserMarketingProgram(resultSet.getInt("user_marketing_program_id"));
                    result.setTblUserMarketingProgram(uPgm);
                    result.setIsRecuring(resultSet.getBoolean("is_recuring"));
                    if (result.getIsRecuring()) {
                        result.setScheduleTime(resultSet.getTimestamp("cal_rec_schedule_time"));
                    } else {
                        if (uPgm.getId() == 0) {//This is for general marketing program. Time is populated correctly.
                            result.setScheduleTime(resultSet.getTimestamp("schedule_time"));
                        } else {
                            result.setScheduleTime(resultSet.getTimestamp("cal_schedule_time"));
                        }
                    }
                    result.setScheduleTitle(resultSet.getString("schedule_title"));

                    result.setEntityType(resultSet.getString("entity_type"));
                    result.setStatus(resultSet.getString("status"));
                    result.setUserId(resultSet.getInt("user_id"));
                    result.setScheduleDesc(resultSet.getString("schedule_desc"));

                    result.setDays(resultSet.getInt("days"));
                    result.setTillDate(resultSet.getDate("till_date"));
                    result.setRecuringEmailId(resultSet.getInt("recuring_email_id"));
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return null;
        }
        return result;
    }

    public static TblScheduledSocialpostList getSocialPostEntityById(int entityId) {
        String sql = "select * from tbl_scheduled_socialpost_list where id=" + entityId;
        TblScheduledSocialpostList result = null;

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                if (resultSet.next()) {
                    result = new TblScheduledSocialpostList(resultSet.getInt("id"));
                    result.setImageName(resultSet.getString("image_name"));
                    result.setMetadata(resultSet.getString("metadata"));
                    result.setType(resultSet.getString("type"));
                    result.setTblScheduledEntityList(new TblScheduledEntityList(resultSet.getInt("entity_list_id")));
                    result.setUserId(resultSet.getInt("user_id"));
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return result;
    }

    public static TblScheduledEmailList getEmailEntityById(int entityId) {
        String sql = "select * from tbl_scheduled_email_list where id=" + entityId;
        TblScheduledEmailList result = null;

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                if (resultSet.next()) {
                    result = new TblScheduledEmailList(resultSet.getInt("id"));
                    result.setSubject(resultSet.getString("subject"));
                    result.setBody(resultSet.getString("body"));
                    result.setFromAddress(resultSet.getString("from_address"));
                    result.setEmailListName(resultSet.getString("email_list_name"));
                    result.setFromName(resultSet.getString("from_name"));
                    result.setToEmailAddresses(resultSet.getString("to_email_addresses"));
                    result.setReplyToEmailAddress(resultSet.getString("reply_to_email_address"));
                    result.setTblScheduledEntityList(new TblScheduledEntityList(resultSet.getInt("entity_list_id")));
                    TblUserLoginDetails userDetail = new TblUserLoginDetails();
                    userDetail.setId(resultSet.getInt("user_id"));
                    result.setTblUserLoginDetails(userDetail);
                }

            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return result;
    }

    public static void updateScheduledEntityListEntity(TblScheduledEntityList scheduledFacebookPost) {
        String sql = "update tbl_scheduled_entity_list set status='" + scheduledFacebookPost.getStatus() + "' where entity_id=" + scheduledFacebookPost.getEntityId() + ";";
        logger.log(Level.INFO, sql);
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }

    }

}
