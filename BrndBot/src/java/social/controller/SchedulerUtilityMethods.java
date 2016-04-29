/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.intbit.ConnectionManager;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyMarketingProgram;
import com.intbittech.model.RecurringEmailTemplate;
import com.intbittech.model.ScheduledEmailList;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.ScheduledSocialpostList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AR
 */
public class SchedulerUtilityMethods {

    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final Logger logger = Logger.getLogger(SchedulerUtilityMethods.class.getName());

    public static ArrayList<String> getLatestApprovedPost(String status, String entityType, String programStatus) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select entitytable.schedule_time::time,entitytable.entity_id as en_id  from scheduled_entity_list as entitytable, company_marketing_program as programtable");
        sbSql.append(" where programtable.company_marketing_program_id = entitytable.fk_company_marketing_program_id ");
        sbSql.append(" and lower(programtable.status)");
        sbSql.append(" like '").append(programStatus).append("'");
        sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
        sbSql.append(" and entitytable.entity_type like'").append(entityType).append("'");
        sbSql.append(" and (date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' or (date(entitytable.schedule_time AT TIME ZONE 'US/Eastern'))= current_date AT TIME ZONE 'US/Eastern')");
        sbSql.append(" and date_trunc('minute', entitytable.schedule_time)::time AT TIME ZONE 'US/Eastern' = date_trunc('minute', localtimestamp)::time AT TIME ZONE 'US/Eastern'");
        sbSql.append(" order by entitytable.schedule_time::time");
        //sbSql.append(" limit 1");
        sbSql.append(";");
//        String entityId = "";
        ArrayList<String> entityId = new ArrayList<String>();

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                while (resultSet.next()) {
                    int e = resultSet.getInt("en_id");
                    entityId.add(Integer.toString(e));
                }
            }

            return entityId;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return entityId;
        }
    }

    public static ArrayList<String> getLatestEmailApprovedPost(String status, String entityType, String programStatus, Boolean isRecurring) {

        StringBuilder sbSql = new StringBuilder();

        if (isRecurring) {
            sbSql.append("select  entitytable.schedule_time::time, entitytable.entity_id as en_id from scheduled_entity_list as entitytable, company_marketing_program as programtable");
            sbSql.append(" where programtable.company_marketing_program_id = entitytable.fk_company_marketing_program_id ");
            sbSql.append(" and lower(programtable.status)");
            sbSql.append("like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.days > 0 and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append(" and entitytable.is_recurring =  '").append(isRecurring).append("'");
            //Need to review this
            sbSql.append(" and date_trunc('minute', entitytable.schedule_time)::time AT TIME ZONE 'US/Eastern' >= date_trunc('minute', localtimestamp)::time AT TIME ZONE 'US/Eastern'");
//            sbSql.append(" and entitytable.schedule_time::time AT TIME ZONE 'US/Eastern' >= current_time AT TIME ZONE 'US/Eastern' ");
            sbSql.append(" order by entitytable.schedule_time::time");
//            sbSql.append(" limit 1");
            sbSql.append(";");

        } else {
            sbSql.append("select  entitytable.schedule_time::time, entitytable.entity_id as en_id from scheduled_entity_list as entitytable, company_marketing_program as programtable");
            sbSql.append(" where programtable.company_marketing_program_id = entitytable.fk_company_marketing_program_id ");
            sbSql.append(" and lower(programtable.status)");
            sbSql.append(" like'").append(programStatus).append("'");
            sbSql.append(" and lower(entitytable.status) like '").append(status).append("'");
            sbSql.append(" and entitytable.entity_type like'").append(entityType).append("'");
            sbSql.append(" and entitytable.is_recurring = '").append(isRecurring).append("'");
            sbSql.append(" and (date(programtable.date_event AT TIME ZONE 'US/Eastern') - entitytable.days  = current_date AT TIME ZONE 'US/Eastern' or (date(entitytable.schedule_time AT TIME ZONE 'US/Eastern'))= current_date AT TIME ZONE 'US/Eastern')");
            sbSql.append(" and date_trunc('minute', entitytable.schedule_time)::time AT TIME ZONE 'US/Eastern' = date_trunc('minute', localtimestamp)::time AT TIME ZONE 'US/Eastern'");
            sbSql.append(" order by entitytable.schedule_time::time");
//            sbSql.append(" limit 1");
            sbSql.append(";");
        }
        ArrayList<String> entityId = new ArrayList<String>();

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sbSql.toString())) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                while (resultSet.next()) {
                    entityId.add(String.valueOf(resultSet.getInt("en_id")));
                }
            }

            return entityId;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return entityId;
        }

    }

    public static ScheduledEntityList getEntityById(int entityId, String entityType) {
        String sql = "select entitytable.*,concat(date(programtable.date_event) - entitytable.days, ' ', entitytable.schedule_time::time WITH TIME ZONE) as cal_schedule_time,concat(date(programtable.date_event), ' ', entitytable.schedule_time::time WITH TIME ZONE) as cal_rec_schedule_time from scheduled_entity_list as entitytable,company_marketing_program as programtable "
                + " where programtable.company_marketing_program_id = entitytable.fk_company_marketing_program_id  and entity_id=" + entityId + " and entity_type='"+entityType+"'";
        ScheduledEntityList result = null;

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {

                if (resultSet.next()) {
                    result = new ScheduledEntityList(resultSet.getInt("scheduled_entity_list_id"));
                    result.setEntityId(resultSet.getInt("entity_id"));
                    CompanyMarketingProgram cPgm = new CompanyMarketingProgram(resultSet.getInt("fk_company_marketing_program_id"));
                    result.setFkCompanyMarketingProgramId(cPgm);
                    result.setIsRecurring(resultSet.getBoolean("is_recurring"));
                    if (result.getIsRecurring()) {
                        result.setScheduleTime(resultSet.getTimestamp("cal_rec_schedule_time"));
                    } else {
                        if (cPgm.getCompanyMarketingProgramId() == 0) {//This is for general marketing program. Time is populated correctly.
                            result.setScheduleTime(resultSet.getTimestamp("schedule_time"));
                        } else {
                            result.setScheduleTime(resultSet.getTimestamp("cal_schedule_time"));
                        }
                    }
                    result.setScheduleTitle(resultSet.getString("schedule_title"));

                    result.setEntityType(resultSet.getString("entity_type"));
                    result.setStatus(resultSet.getString("status"));
                    Company company = new Company(resultSet.getInt("fk_company_id"));
                    result.setFkCompanyId(company);
                    result.setScheduleDesc(resultSet.getString("schedule_desc"));

                    result.setDays(resultSet.getInt("days"));
                    result.setTillDate(resultSet.getDate("till_date"));
                    RecurringEmailTemplate recurringEmailTemplate = new RecurringEmailTemplate(resultSet.getInt("fk_recurring_email_id"));
                    result.setFkRecurringEmailId(recurringEmailTemplate);
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            return null;
        }
        return result;
    }

    public static ScheduledSocialpostList getSocialPostEntityById(int entityId) {
        String sql = "select * from scheduled_socialpost_list where id=" + entityId;
        ScheduledSocialpostList result = null;

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                if (resultSet.next()) {
                    result = new ScheduledSocialpostList(resultSet.getInt("scheduled_socialpost_list_id"));
                    result.setImageName(resultSet.getString("image_name"));
                    result.setMetaData(resultSet.getString("meta_data"));
                    result.setType(resultSet.getString("type"));
                    result.setFkScheduledEntityListId(new ScheduledEntityList(resultSet.getInt("fk_scheduled_entity_list_id")));
                    result.setFkCompanyId(new Company(resultSet.getInt("fk_company_id")));
                    result.setImageType(resultSet.getString("image_type"));
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return result;
    }

    public static ScheduledEmailList getEmailEntityById(int entityId) {
        String sql = "select * from scheduled_email_list where id=" + entityId;
        ScheduledEmailList result = null;

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            try (ResultSet resultSet = ps.getResultSet()) {
                if (resultSet.next()) {
                    result = new ScheduledEmailList(resultSet.getInt("scheduled_email_list_id"));
                    result.setSubject(resultSet.getString("subject"));
                    result.setBody(resultSet.getString("body"));
                    result.setFromAddress(resultSet.getString("from_address"));
                    result.setEmailListName(resultSet.getString("email_list_name"));
                    result.setFromName(resultSet.getString("from_name"));
                    result.setToEmailAddresses(resultSet.getString("to_email_addresses"));
                    result.setReplyToEmailAddress(resultSet.getString("reply_to_email_address"));
                    result.setFkScheduledEntityListId(new ScheduledEntityList(resultSet.getInt("fk_scheduled_entity_list_id")));
                    result.setFkCompanyId(new Company(resultSet.getInt("fk_company_id")));
                 
                }

            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return result;
    }

    public static void updateScheduledEntityListEntity(ScheduledEntityList scheduledFacebookPost) {
        String sql = "update scheduled_entity_list set status='" + scheduledFacebookPost.getStatus() + "' where entity_id=" + scheduledFacebookPost.getEntityId() + ";";
        logger.log(Level.INFO, sql);
        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }

    }

}
