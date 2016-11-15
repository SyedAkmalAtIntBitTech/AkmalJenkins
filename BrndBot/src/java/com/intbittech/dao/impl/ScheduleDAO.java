/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.dao.impl;

import com.intbittech.utility.IConstants;
import com.intbittech.divtohtml.StringUtil;
import com.intbit.ConnectionManager;
import com.intbittech.component.SpringContextBridge;
import com.intbittech.enums.ScheduledEntityType;
import com.intbittech.enums.TemplateStatus;
import com.intbittech.marketing.service.ScheduledEntityListService;
import com.intbittech.model.PushedScheduledActionCompanies;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.services.PushedScheduledActionCompaniesService;
import com.intbittech.services.PushedScheduledEntityListService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;
import com.intbittech.utility.DateTimeUtil;
import com.intbittech.utility.Utility;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class ScheduleDAO {

    private static final Logger logger = Logger.getLogger(ScheduleDAO.class.getName());
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();

    public static Map<String, Integer> addToScheduledEmailList(int companyId,
            String subject,
            String preheader,
            Integer marketing_program_id,
            String body,
            String fromAddress,
            String emailListName,
            String fromName,
            String replytoEmailAddress,
            String scheduledTitle,
            String scheduleDesc,
            Timestamp scheduledTime,
            String templateStatus,
            String html_body,
            Integer createdBy,
            Integer assignedTo
    ) throws SQLException {

        int emailScheduleId = -1;
        int scheduleEntityId = -1;
        Map<String, Integer> returnMap = new HashMap<>();

        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String sql = "INSERT INTO scheduled_email_list "
                        + " (fk_company_id, subject, body, from_address, email_list_name, from_name,reply_to_email_address, preheader, html_body) VALUES "
                        + " (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING scheduled_email_list_id";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, companyId);
                    ps.setString(2, subject);
                    ps.setString(3, body);
                    ps.setString(4, fromAddress);
                    ps.setString(5, emailListName);
                    ps.setString(6, fromName);
                    ps.setString(7, replytoEmailAddress);
                    ps.setString(8, preheader);
                    ps.setString(9, html_body);
                    ps.execute();
                    try (ResultSet resultSet = ps.getResultSet()) {

                        if (resultSet.next()) {
                            emailScheduleId = resultSet.getInt(1);
                            logger.log(Level.INFO, "Id of scheduled email: " + emailScheduleId);
                        }
                    }
                }

                scheduleEntityId = addToScheduleEntityList(emailScheduleId,
                        scheduledTitle,
                        marketing_program_id,
                        scheduleDesc,
                        scheduledTime,
                        ScheduledEntityType.Email.toString(),
                        "0",
                        templateStatus,
                        companyId,
                        createdBy,
                        assignedTo,
                        connection);

                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        returnMap.put("schedule_email_id", emailScheduleId);
        returnMap.put("schedule_entity_id", scheduleEntityId);

        return returnMap;
    }

    public static Map<String, Integer> updatetoScheduledEmailList(
            int companyId,
            int scheduleid,
            String subject,
            String preheader,
            String body,
            String fromAddress,
            String emailListName,
            String fromName,
            String replyToEmailAddress,
            String scheduleDesc,
            String templateStatus,
            String html_body
    ) throws SQLException {

        int emailScheduleId = -1;
        int scheduleEntityId = -1;
        Map<String, Integer> returnMap = new HashMap<>();

        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String sql = "INSERT INTO scheduled_email_list "
                        + " (fk_company_id, subject, body, from_address, email_list_name, from_name, reply_to_email_address, preheader, html_body) VALUES "
                        + " (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING scheduled_email_list_id";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, companyId);
                    ps.setString(2, subject);
                    ps.setString(3, body);
                    ps.setString(4, fromAddress);
                    ps.setString(5, emailListName);
                    ps.setString(6, fromName);
                    ps.setString(7, replyToEmailAddress);
                    ps.setString(8, preheader);
                    ps.setString(9, html_body);
                    ps.execute();
                    try (ResultSet resultSet = ps.getResultSet()) {

                        if (resultSet.next()) {
                            emailScheduleId = resultSet.getInt(1);
                            logger.log(Level.INFO, "Id of scheduled email: " + emailScheduleId);
                        }
                    }
                }
                scheduleEntityId = updateScheduleEntityList(emailScheduleId,
                        scheduleid,
                        templateStatus,
                        connection);

                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        returnMap.put("schedule_email_id", emailScheduleId);
        returnMap.put("schedule_entity_id", scheduleEntityId);

        return returnMap;
    }

    public static int updateScheduleEntityList(int entityID, int schedule_id,
            String templateStatus, Connection connection) throws SQLException {
        String query = "Update scheduled_entity_list"
                + " SET entity_id = ?, status = ?"
                + " Where scheduled_entity_list_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, entityID);
            ps.setString(2, templateStatus);
            ps.setInt(3, schedule_id);

            ps.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                    "Exception while updating the schedule:", null), e);

        }
        return entityID;
    }

    public static int addToScheduleEntityList(Integer entityId,
            String scheduleTitle,
            Integer marketing_program_id,
            String scheduleDesc,
            Timestamp scheduleTime,
            String entityType,
            String days,
            String status,
            int companyId,
            Integer createdBy,
            Integer assignedTo,
            Connection connection) throws SQLException {
        String sql = "INSERT INTO scheduled_entity_list"
                + " (entity_id, schedule_title,schedule_time,entity_type,"
                + "status,fk_company_id, schedule_desc,is_recurring,"
                + "fk_company_marketing_program_id,days,till_date,fk_recurring_email_id,created_by,assigned_to,created_at,updated_at"
                + ") VALUES"
                + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP) RETURNING scheduled_entity_list_id";

        int scheduleId = -1;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (entityId == null) {
                ps.setInt(1, 0);
            } else {
                ps.setInt(1, entityId);
            }
            ps.setString(2, scheduleTitle);
            ps.setTimestamp(3, scheduleTime, Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault())));
            ps.setString(4, entityType);
            ps.setString(5, status);
            ps.setInt(6, companyId);
            ps.setString(7, scheduleDesc);
            ps.setBoolean(8, false);
            ps.setInt(9, marketing_program_id);
            ps.setInt(10, Integer.parseInt(days));
            ps.setTimestamp(11, null);
            ps.setNull(12, java.sql.Types.INTEGER);
            ps.setInt(13, createdBy);
            if (assignedTo == 0) {
                ps.setNull(14, java.sql.Types.INTEGER);
            } else {
                ps.setInt(14, assignedTo);
            }

            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    scheduleId = rs.getInt(1);
                    logger.log(Level.INFO, "Id of scheduled entity: " + scheduleId);
                }
            }
        }
        return scheduleId;
    }

    public static int updateDescriptionScheduledEntity(Integer scheduleId,
            String scheduleDesc,
            Connection connection) throws SQLException {
        String query_string = "Update scheduled_entity_list"
                + " SET schedule_desc = ?"
                + " Where scheduled_entity_list_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query_string)) {

            ps.setString(1, scheduleDesc);
            ps.setInt(2, scheduleId);
            ps.execute();

        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                    "Exception while updating the schedule:", null), e);
        }
        return scheduleId;
    }

    public static int updateScheduledEntity(Integer scheduleId,
            String scheduleTitle,
            String scheduleDesc,
            Timestamp scheduleTime,
            String entityType,
            int companyId,
            int days,
            Connection connection) throws SQLException {
        String query_string = "Update scheduled_entity_list"
                + " SET schedule_title = ?, schedule_desc = ?, schedule_time = ?,"
                + " entity_type = ?, days= ?"
                + " Where scheduled_entity_list_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query_string)) {

            ps.setString(1, scheduleTitle);
            ps.setString(2, scheduleDesc);
            ps.setTimestamp(3, scheduleTime);
            ps.setString(4, entityType);
            ps.setInt(5, days);
            ps.setInt(6, scheduleId);
            ps.execute();

        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                    "Exception while updating the schedule:", null), e);
        }
        return scheduleId;
    }

    public static JSONArray getScheduledActions(int companyId, Integer company_marketing_program_id) throws SQLException {
        JSONObject json_action_facebook = new JSONObject();
        JSONArray json_array_email = new JSONArray();
        String query = "Select * from scheduled_entity_list"
                + " where entity_id=?"
                + " and entity_type =?"
                + " and status = ?"
                + " and is_recurring = false"
                + " and fk_company_marketing_program_id =?"
                + " and fk_company_id = ?";

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, 0);
            ps.setString(2, ScheduledEntityType.Email.toString());
            ps.setString(3, TemplateStatus.no_template.toString());
            ps.setInt(4, company_marketing_program_id);
            ps.setInt(5, companyId);
            try (ResultSet result_set = ps.executeQuery()) {
                while (result_set.next()) {

                    JSONObject json_object = new JSONObject();
                    Integer id = result_set.getInt("scheduled_entity_list_id");
                    String schedule_title = result_set.getString("schedule_title");
                    String schedule_desc = result_set.getString("schedule_desc");
                    Timestamp scheduleTimestamp = result_set.getTimestamp("schedule_time");
                    long scheduleTime = scheduleTimestamp.getTime();

                    json_object.put("id", id);
                    json_object.put("schedule_title", schedule_title);
                    json_object.put("schedule_desc", schedule_desc);
                    json_object.put("schedule_time", scheduleTime);

                    json_array_email.add(json_object);
                }
            }
        }
        return json_array_email;
    }

    public static JSONObject getScheduledEntities(Integer companyId,
            LocalDate fromDate, LocalDate toDate) throws SQLException {

        Map<String, JSONArray> result = new LinkedHashMap<>();

        String sql = "SELECT DISTINCT ON (scheduled_entity_list_id) slist.*, concat(date(programtable.date_event) - slist.days, ' ', slist.schedule_time::time WITH TIME ZONE) as cal_schedule_time, concat(date(programtable.date_event), ' ', slist.schedule_time::time WITH TIME ZONE) as cal_schedule_time_recurring, date(schedule_time) schedule_date "
                + " FROM scheduled_entity_list slist, "
                + " company_marketing_program programtable"
                + " WHERE programtable.status = 'Open' AND slist.fk_company_id = ? "
                + " AND ((date(schedule_time) <= ? "
                + " AND date(schedule_time) >= ?) "
                + " OR ((slist.is_recurring = 'false' AND date(programtable.date_event) - slist.days <= ? "
                + " AND date(programtable.date_event) - slist.days >= ?) "
                //                + " OR (slist.is_recurring = 'true' AND date(programtable.date_event) <= ? "
                //                + " AND date(programtable.date_event) >= ?))) "
                + " )) "
                + " AND slist.fk_company_marketing_program_id = programtable.company_marketing_program_id"
                + " ORDER BY slist.scheduled_entity_list_id, schedule_time ";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, companyId);
            ps.setDate(2, Date.valueOf(toDate));
            ps.setDate(3, Date.valueOf(fromDate));
            ps.setDate(4, Date.valueOf(toDate));
            ps.setDate(5, Date.valueOf(fromDate));
//            ps.setDate(6, Date.valueOf(toDate));
//            ps.setDate(7, Date.valueOf(fromDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Timestamp scheduleTimestamp = rs.getTimestamp("schedule_time");
                    long scheduleTime = scheduleTimestamp.getTime();
                    JSONObject scheduleDetailJSONObject = new JSONObject();
                    long scheduleDate;
                    if (rs.getInt("fk_company_marketing_program_id") == 0) {
                        /* changed from getDate to getTimeStamp */
                        scheduleDate = rs.getTimestamp("schedule_time").getTime();
                    } else {
                        Timestamp scheduleTimestamp1 = rs.getTimestamp("cal_schedule_time");
                        if (rs.getBoolean("is_recurring")) {

                            scheduleDate = rs.getTimestamp("cal_schedule_time_recurring").getTime();
                        } else {
                            scheduleDate = scheduleTimestamp1.getTime();
                        }
                    }
                    //String scheduleDateStr = new Date(scheduleDate).toString();
                    scheduleDetailJSONObject.put("days", rs.getInt("days"));
                    scheduleDetailJSONObject.put("schedule_id", rs.getInt("scheduled_entity_list_id"));
                    scheduleDetailJSONObject.put("entity_id", rs.getInt("entity_id"));
                    int marketingId = rs.getInt("fk_company_marketing_program_id");
                    String marketingName = getMarketingProgramName(marketingId, connection);
                    scheduleDetailJSONObject.put("marketingName", marketingName);
                    scheduleDetailJSONObject.put("user_marketing_program_id", rs.getInt("fk_company_marketing_program_id"));
                    scheduleDetailJSONObject.put("schedule_title", rs.getString("schedule_title"));
                    scheduleDetailJSONObject.put("schedule_description", rs.getString("schedule_desc"));
                    if (rs.getInt("fk_company_marketing_program_id") == 0) {
                        scheduleDetailJSONObject.put("schedule_time", scheduleTime);
                        if (DateTimeUtil.dateEqualsCurrentDate(new Date(scheduleTime))) {
                            scheduleDetailJSONObject.put("is_today_active", "true");
                        } else {
                            scheduleDetailJSONObject.put("is_today_active", "false");
                        }
                    } else {
                        Timestamp scheduleTimestamp1 = rs.getTimestamp("cal_schedule_time");
                        long scheduleTime1;
                        if (rs.getBoolean("is_recurring")) {
                            scheduleTime1 = rs.getTimestamp("schedule_time").getTime();
                        } else {
                            scheduleTime1 = rs.getTimestamp("schedule_time").getTime();
                        }
                        scheduleDetailJSONObject.put("schedule_time", scheduleTime1);
                        //sends always true
                        scheduleDetailJSONObject.put("is_today_active", "true");
//                        if (DateTimeUtil.dateEqualsCurrentDate(new Date(scheduleTime1))) {
//                            scheduleDetailJSONObject.put("is_today_active", "true");
//                        } else {
//                            scheduleDetailJSONObject.put("is_today_active", "false");
//                        }
                    }
                    scheduleDetailJSONObject.put("is_recurring", rs.getBoolean("is_recurring"));
                    scheduleDetailJSONObject.put("entity_type", rs.getString("entity_type"));
                    scheduleDetailJSONObject.put("status", rs.getString("status"));
                    scheduleDetailJSONObject.put("user_id", rs.getInt("fk_company_id"));
                    //scheduleDetailJSONObject.put("color", rs.getString("color"));
                    scheduleDetailJSONObject.put("template_status",
                            TemplateStatus.valueOf(rs.getString("status")).getDisplayName());

                    if (!result.containsKey(String.valueOf(scheduleDate))) {
                        result.put(String.valueOf(scheduleDate), new JSONArray());
                    }

                    result.get(String.valueOf(scheduleDate)).add(scheduleDetailJSONObject);
                }
                logger.log(Level.INFO, "In com.intbit.dao.ScheduleDAO.getScheduledEntities");
            } catch (Exception e) {
                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                        "Exception while getting the schedule:", null), e);
            }
        }
        Set<String> dateSet = result.keySet();
        JSONArray entityDataArray = new JSONArray();
        logger.log(Level.INFO, "dateSet" + dateSet.toString());
        try {
            Instant instant = fromDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            java.util.Date from_date = (java.util.Date) Date.from(instant);
            Instant instant1 = toDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            java.util.Date to_date = (java.util.Date) Date.from(instant1);

            LocalDate start = from_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = to_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                java.util.Date date_new = (java.util.Date) Date.from(instant);
                String date_epoch = String.valueOf(date_new.getTime());
                if (!dateSet.contains(date_epoch)) {
                    result.put(date_epoch, new JSONArray());
                }
                logger.log(Level.INFO, date.toString());
            }

            Map<String, JSONObject> sortedMap = new TreeMap(result);
            dateSet = sortedMap.keySet();

            HashMap entityObject = null;
            //good way:
            Iterator<String> iterator = dateSet.iterator();
            while (iterator.hasNext()) {
                entityObject = new HashMap();
                String dateString = iterator.next();
                String parsedDateString = new Date(Long.parseLong(dateString)).toString();
                boolean foundObjectInArray = false;
                for (Object entityDataArrayObject : entityDataArray) {
                    HashMap entityDataArrayJSONObject = (HashMap) entityDataArrayObject;
                    String dateInEntityDateJSONObject = (String) entityDataArrayJSONObject.get("date");
                    if (!StringUtil.isEmpty(dateInEntityDateJSONObject) && dateInEntityDateJSONObject.equals(parsedDateString)) {
                        foundObjectInArray = true;
                        break;
                    }
                }
                if (!foundObjectInArray) {
                    entityObject.put("date", parsedDateString);
                    JSONArray jsonArrayDateJSONEntities = new JSONArray();
                    Iterator resultIterator = result.entrySet().iterator();
                    Integer i = 0;
                    while (resultIterator.hasNext()) {
                        Map.Entry pair = (Map.Entry) resultIterator.next();
                        JSONArray jsonArray = (JSONArray) pair.getValue();
                        String jsonParsedString = new Date(Long.parseLong(pair.getKey().toString())).toString();
                        if (jsonParsedString.equals(parsedDateString)) {
                            if (jsonArray.size() > 0) {
                                for (i = 0; i < jsonArray.size(); i++) {
                                    jsonArrayDateJSONEntities.add(jsonArray.get(i));
                                }
                                resultIterator.remove(); // avoids a ConcurrentModificationException                            
                            }
                        }

                    }
//                JSONArray jsonArrayDateEntities = result.get(dateString);
                    entityObject.put("dataArray", jsonArrayDateJSONEntities);
                    entityDataArray.add(entityObject);
                }

            }
//            logger.log(Level.INFO, entityDataArray.toString());

        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                    "Exception while reading the dates :", null), e);
        }
        JSONObject json_data = new JSONObject();
        json_data.put("noactionsmessage", "No Actions scheduled for this day");
        json_data.put("entitydata", entityDataArray);
        return json_data;

    }

    public static String getMarketingProgramName(int marketingId, Connection connection) {
        String marketingProgram = "";
        String sql1 = "SELECT company_marketing_program_name FROM company_marketing_program WHERE company_marketing_program_id = ? ";
        try (PreparedStatement ps1 = connection.prepareStatement(sql1)) {
            ps1.setInt(1, marketingId);
            try (ResultSet rs1 = ps1.executeQuery()) {
                if (rs1.next()) {
                    marketingProgram = rs1.getString("company_marketing_program_name");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                    "Exception while getting the program names :", null), e);
        }
        return marketingProgram;
    }

    public static Map<String, Object> getScheduleEmailDetails(int companyId, int scheduleId) throws SQLException, ParseException {
        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject emailJSONObject = new org.json.simple.JSONObject();
        JSONArray emailids_json_array = new JSONArray();
        String email_ids = "";
        String sql = "SELECT elist.* FROM "
                + " scheduled_email_list elist, "
                + " scheduled_entity_list enlist, "
                + " company_marketing_program program "
                + " WHERE elist.scheduled_email_list_id = enlist.entity_id "
                + " AND enlist.fk_company_id = ? "
                + " AND enlist.scheduled_entity_list_id = ?"
                + " AND enlist.entity_type = ? ";

        Map<String, Object> scheduleEmailDetails = new HashMap<>();
        int scheduleEmailId = -1;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, companyId);
                ps.setInt(2, scheduleId);
                ps.setString(3, ScheduledEntityType.Email.toString());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {

                        scheduleEmailDetails.put("schedule_email_id", rs.getInt("scheduled_email_list_id"));
                        scheduleEmailDetails.put("user_id", rs.getInt("fk_company_id"));
                        scheduleEmailDetails.put("subject", rs.getString("subject"));
                        scheduleEmailDetails.put("preheader", rs.getString("preheader"));
                        scheduleEmailDetails.put("body", rs.getString("body"));
                        scheduleEmailDetails.put("from_address", rs.getString("from_address"));
                        scheduleEmailDetails.put("reply_to_email_address", rs.getString("reply_to_email_address"));
                        scheduleEmailDetails.put("email_list_name", rs.getString("email_list_name"));
                        scheduleEmailDetails.put("from_name", rs.getString("from_name"));
                        scheduleEmailDetails.put("html_body", rs.getString("html_body"));
                    }
                }
            }
        }
        //now get the to email addresses
        return scheduleEmailDetails;
    }

    public static void deleteSchedules(int companyId, String schedule_id) throws SQLException, ParseException {

        String schedule_ids[] = schedule_id.split(",");

        for (int i = 0; i < schedule_ids.length; i++) {
            String query_String = "Select * from scheduled_entity_list"
                    + " Where scheduled_entity_list_id=?"
                    + " and fk_company_id=?";

            try (Connection connection = connectionManager.getConnection()) {
                try (PreparedStatement prepared_statement = connection.prepareStatement(query_String)) {
                    prepared_statement.setInt(1, Integer.parseInt(schedule_ids[i]));
                    prepared_statement.setInt(2, companyId);
                    try (ResultSet result_set = prepared_statement.executeQuery()) {
                        if (result_set.next()) {
                            Integer entity_id = result_set.getInt("entity_id");
                            String entity_type = result_set.getString("entity_type");
                            if (entity_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
                                String query_string1 = "Delete from scheduled_email_list"
                                        + " where scheduled_email_list_id = ?";
                                try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                    prepared_statement1.setInt(1, entity_id);
                                    prepared_statement1.execute();
                                } catch (Exception e) {
                                    logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                                            "Exception while deleting the schedule:", null), e);
                                }
                            } else if ((entity_type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString()))
                                    || (entity_type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString()))) {
                                String query_string1 = "Delete from scheduled_socialpost_list"
                                        + " where scheduled_socialpost_list_id = ?";
                                try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                    prepared_statement1.setInt(1, entity_id);
                                    prepared_statement1.execute();
                                } catch (Exception e) {
                                    logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                                            "Exception while deleting the schedule:", null), e);
                                }
                            }

                        }
                    }

                } catch (Exception e) {
                    logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
                }
                String query_string2 = "Delete from scheduled_entity_list"
                        + " where scheduled_entity_list_id = ?";
                try (PreparedStatement prepared_statement2 = connection.prepareStatement(query_string2)) {
                    prepared_statement2.setInt(1, Integer.parseInt(schedule_ids[i]));
                    prepared_statement2.execute();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
                }
            }

        }
    }

    public static void removeSavedTemplate(int companyId, Integer schedule_id) throws SQLException, ParseException {

        String query_String = "Select * from scheduled_entity_list"
                + " Where scheduled_entity_list_id=?"
                + " and fk_company_id=?";

        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_String)) {
                prepared_statement.setInt(1, schedule_id);
                prepared_statement.setInt(2, companyId);
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    if (result_set.next()) {
                        Integer entity_id = result_set.getInt("entity_id");
                        String entity_type = result_set.getString("entity_type");
                        if (entity_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
                            String query_string1 = "Delete from scheduled_email_list"
                                    + " where scheduled_email_list_id = ?";
                            try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                prepared_statement1.setInt(1, entity_id);
                                prepared_statement1.execute();
                            } catch (Exception e) {
                                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                                        "Exception while deleting the schedule:", null), e);
                            }
                        } else if ((entity_type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString()))
                                || (entity_type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString()))) {
                            String query_string1 = "Delete from scheduled_socialpost_list"
                                    + " where scheduled_socialpost_list_id = ?";
                            try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                prepared_statement1.setInt(1, entity_id);
                                prepared_statement1.execute();
                            } catch (Exception e) {
                                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                                        "Exception while deleting the schedule:", null), e);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
            }
            String query_string2 = "UPDATE scheduled_entity_list"
                    + " SET entity_id = ?, status = ?, fk_recurring_email_id = ? where scheduled_entity_list_id = ?";
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_string2)) {
                prepared_statement.setInt(1, 0);
                prepared_statement.setString(2, TemplateStatus.no_template.toString());
                prepared_statement.setNull(3, java.sql.Types.INTEGER);
                prepared_statement.setInt(4, schedule_id);
                prepared_statement.executeUpdate();
            } catch (Exception e) {
                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while updating the schedule:", null), e);
            }
        }
    }

    public static void deleteSchedule(int companyId, Integer schedule_id) throws SQLException, ParseException {

        String query_String = "Select * from scheduled_entity_list"
                + " Where scheduled_entity_list_id=?"
                + " and fk_company_id=?";

        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_String)) {
                prepared_statement.setInt(1, schedule_id);
                prepared_statement.setInt(2, companyId);
//                 prepared_statement.setString(3, ScheduledEntityType.email.toString());
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    if (result_set.next()) {
                        Integer entity_id = result_set.getInt("entity_id");
                        String query_string1 = "Delete from scheduled_email_list"
                                + " where scheduled_email_list_id = ?";
                        try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                            prepared_statement1.setInt(1, entity_id);
                            prepared_statement1.executeUpdate();
                        }
                    }
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
            }
            String query_string2 = "Delete from scheduled_entity_list"
                    + " where scheduled_entity_list_id = ?";
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_string2)) {
                prepared_statement.setInt(1, schedule_id);
                prepared_statement.executeUpdate();
            } catch (Exception e) {
                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
            }
        }

    }

    public static void updateNoteDetails(Integer companyId,
            Integer scheduleID,
            String scheduleTitle,
            String scheduleDesc,
            String status,
            Timestamp scheduleTime) throws SQLException {

        String query_string = "Update scheduled_entity_list"
                + " SET schedule_title = ?, schedule_time = ?,"
                + " status = ?, schedule_desc = ?"
                + " Where scheduled_entity_list_id = ?";
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_string)) {
                prepared_statement.setString(1, scheduleTitle);
                prepared_statement.setTimestamp(2, scheduleTime);
                prepared_statement.setString(3, status);
                prepared_statement.setString(4, scheduleDesc);
                prepared_statement.setInt(5, scheduleID);

                prepared_statement.executeUpdate();
            } catch (Exception e) {
                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while "
                        + "updating the note schedule:", null), e);
            }
        }
    }

    public static void updateScheduleEmailDetails(Integer companyId,
            Integer ScheduleID,
            Integer entityID,
            String subject,
            String fromAddress,
            String emailListName,
            String replytoEmailAddress,
            String[] toAddress,
            String scheduledTitle,
            Timestamp scheduledTime
    ) throws SQLException, ParseException {
        JSONObject json_email_addresses = new JSONObject();
        JSONArray json_array_email_address = new JSONArray();
        PGobject pg_object = new PGobject();
        String query_string = "Update scheduled_entity_list"
                + " SET schedule_title = ?, schedule_time = ?"
                + " Where scheduled_entity_list_id = ?";

        try (Connection conn = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = conn.prepareStatement(query_string)) {
                prepared_statement.setString(1, scheduledTitle);
                prepared_statement.setTimestamp(2, scheduledTime);
                prepared_statement.setInt(3, ScheduleID);

                prepared_statement.executeUpdate();
            }

            json_email_addresses = getjsontoemailaddresses(entityID, conn);

            query_string = "Update scheduled_email_list"
                    + " SET subject = ?, from_address = ?, email_list_name = ?,"
                    + " to_email_addresses = ?, reply_to_email_address = ?"
                    + " Where scheduled_email_list_id = ?";

            for (int i = 0; i < toAddress.length; i++) {
                json_array_email_address.add(toAddress[i].trim());
            }
            json_email_addresses.replace(IConstants.kEmailAddressesKey, json_array_email_address);
            pg_object.setType("json");
            pg_object.setValue(json_email_addresses.toJSONString());

            try (PreparedStatement prepared_statement = conn.prepareStatement(query_string)) {
                prepared_statement.setString(1, subject);
                prepared_statement.setString(2, fromAddress);
                prepared_statement.setString(3, emailListName);
                prepared_statement.setObject(4, pg_object, Types.OTHER);
                prepared_statement.setString(5, replytoEmailAddress);
                prepared_statement.setInt(6, entityID);
                prepared_statement.executeUpdate();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e, "Exception while updating the email schedule:", null), e);
        }
    }

    public static JSONObject getjsontoemailaddresses(Integer entityid,
            Connection connection) throws SQLException, ParseException {
        JSONObject json_email_addresses = new JSONObject();
        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();
        String query_string = "Select to_email_addresses from scheduled_email_list"
                + " where scheduled_email_list_id=?";

        try (PreparedStatement prepared_statement = connection.prepareStatement(query_string)) {
            prepared_statement.setInt(1, entityid);
            try (ResultSet result_set = prepared_statement.executeQuery()) {
                if (result_set.next()) {

                    pgobject = (PGobject) result_set.getObject("to_email_addresses");

                    pgobject.setType("json");
                    String obj = pgobject.getValue();
                    json_email_addresses = (org.json.simple.JSONObject) parser.parse(obj);
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(ex, "Exception while reading the json email addresses:", null), ex);
        }
        return json_email_addresses;
    }

    public static JSONArray getScheduledActionsEmailWithDate(int companyId, Integer company_marketing_program_id) throws SQLException {
        Timestamp actionTimestamp = null;
        Long actionDate = null;
        JSONArray json_array_email = new JSONArray();
        String query = "select sel.*,concat(date(cmp.date_event) - sel.days,' ', "
                + " sel.schedule_time::time WITH TIME ZONE) as cal_date,concat(date(sel.schedule_time),' ', sel.schedule_time::time WITH TIME ZONE)"
                + " as cal_rec_date from scheduled_entity_list as sel"
                + "  INNER JOIN company_marketing_program as cmp on (cmp.company_marketing_program_id = sel.fk_company_marketing_program_id)"
                + " where  sel.entity_id= ?"
                + " and sel.entity_type = ? "
                + " and sel.status = ?"
                + " and sel.fk_company_marketing_program_id = ?"
                + " and sel.fk_company_id = ?";

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, 0);
            ps.setString(2, ScheduledEntityType.Email.toString());
            ps.setString(3, TemplateStatus.no_template.toString());
            ps.setInt(4, company_marketing_program_id);
            ps.setInt(5, companyId);
            try (ResultSet result_set = ps.executeQuery()) {
                while (result_set.next()) {

                    JSONObject json_object = new JSONObject();
                    Integer id = result_set.getInt("scheduled_entity_list_id");
                    String schedule_title = result_set.getString("schedule_title");
                    String schedule_desc = result_set.getString("schedule_desc");
                    Timestamp scheduleTimestamp = result_set.getTimestamp("schedule_time");
                    long scheduleTime = scheduleTimestamp.getTime();
                    if (company_marketing_program_id == 0) {
                        actionTimestamp = result_set.getTimestamp("cal_rec_date");
                        actionDate = actionTimestamp.getTime();
                    } else if (company_marketing_program_id > 0) {
                        actionTimestamp = result_set.getTimestamp("cal_date");
                        actionDate = actionTimestamp.getTime();
                    }
                    json_object.put("id", id);
                    json_object.put("schedule_title", schedule_title);
                    json_object.put("schedule_desc", schedule_desc);
                    json_object.put("schedule_time", scheduleTime);
                    json_object.put("action_date", actionDate);

                    json_array_email.add(json_object);
                }
            }
        }
        return json_array_email;
    }

    public static JSONObject getScheduledEntitiesWithUser(Integer companyId,
            LocalDate fromDate, LocalDate toDate) throws SQLException {

        Map<String, JSONArray> result = new LinkedHashMap<>();
        PushedScheduledEntityListService pushedScheduleEntityListService = SpringContextBridge.services().getPushedScheduledEntityListService();
        PushedScheduledActionCompaniesService pushedScheduledActionCompaniesService = SpringContextBridge.services().getPushedScheduledActionCompaniesService();
        ScheduledEntityListService scheduledEntityListService = SpringContextBridge.services().getScheduledEntityListService();

        long scheduleTime;
        long scheduleDate;
        
        String sql = "SELECT DISTINCT ON (scheduled_entity_list_id) slist.*, "
                + "concat(date(programtable.date_event) - slist.days, ' ', slist.schedule_time::time WITH TIME ZONE) "
                + "as cal_schedule_time, concat(date(programtable.date_event), ' ', slist.schedule_time::time WITH TIME ZONE)"
                + " as cal_schedule_time_recurring, date(schedule_time) schedule_date ,suser.first_name,suser.last_name,suser.user_id "
                + "FROM scheduled_entity_list as slist FULL OUTER JOIN company_marketing_program as programtable"
                + " on (slist.fk_company_marketing_program_id = programtable.company_marketing_program_id)"
                + " FULL OUTER JOIN users as suser on (slist.assigned_to = suser.user_id)"
                + " WHERE programtable.status = 'Open' AND slist.fk_company_id = ?  "
                + "AND ((date(schedule_time) <= ?  AND date(schedule_time) >= ?) "
                + "OR ((slist.is_recurring = 'false' AND date(programtable.date_event) - slist.days<= ?  "
                + "AND date(programtable.date_event) - slist.days >= ?)  )) "
                + " ORDER BY slist.scheduled_entity_list_id, schedule_time";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, companyId);
            ps.setDate(2, Date.valueOf(toDate));
            ps.setDate(3, Date.valueOf(fromDate));
            ps.setDate(4, Date.valueOf(toDate));
            ps.setDate(5, Date.valueOf(fromDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    JSONObject scheduleDetailJSONObject = new JSONObject();
                    
                    Timestamp scheduleTimestamp = rs.getTimestamp("schedule_time");
                    scheduleTime = scheduleTimestamp.getTime();
                    if (rs.getInt("fk_company_marketing_program_id") == 0) {
                        scheduleDate = rs.getTimestamp("schedule_time").getTime();
                    } else {
                        Timestamp scheduleTimestamp1 = rs.getTimestamp("cal_schedule_time");
                        if (rs.getBoolean("is_recurring")) {

                            scheduleDate = rs.getTimestamp("cal_schedule_time_recurring").getTime();
                        } else {
                            scheduleDate = scheduleTimestamp1.getTime();
                        }
                    }
                    scheduleDetailJSONObject.put("days", rs.getInt("days"));
                    scheduleDetailJSONObject.put("schedule_id", rs.getInt("scheduled_entity_list_id"));
                    scheduleDetailJSONObject.put("entity_id", rs.getInt("entity_id"));
                    int marketingId = rs.getInt("fk_company_marketing_program_id");
                    String marketingName = getMarketingProgramName(marketingId, connection);
                    scheduleDetailJSONObject.put("marketingName", marketingName);
                    scheduleDetailJSONObject.put("isPushed", false);
                    scheduleDetailJSONObject.put("user_marketing_program_id", rs.getInt("fk_company_marketing_program_id"));
                    scheduleDetailJSONObject.put("schedule_title", rs.getString("schedule_title"));
                    scheduleDetailJSONObject.put("schedule_description", rs.getString("schedule_desc"));
                    if (rs.getInt("fk_company_marketing_program_id") == 0) {
                        scheduleDetailJSONObject.put("schedule_time", scheduleTime);
                        if (DateTimeUtil.dateEqualsCurrentDate(new Date(scheduleTime))) {
                            scheduleDetailJSONObject.put("is_today_active", "true");
                        } else {
                            scheduleDetailJSONObject.put("is_today_active", "false");
                        }
                    } else {
                        long scheduleTime1;
                        if (rs.getBoolean("is_recurring")) {
                            scheduleTime1 = rs.getTimestamp("schedule_time").getTime();
                        } else {
                            scheduleTime1 = rs.getTimestamp("schedule_time").getTime();
                        }
                        scheduleDetailJSONObject.put("schedule_time", scheduleTime1);
                        scheduleDetailJSONObject.put("is_today_active", "true");

                    }
                    scheduleDetailJSONObject.put("is_recurring", rs.getBoolean("is_recurring"));
                    scheduleDetailJSONObject.put("entity_type", rs.getString("entity_type"));
                    scheduleDetailJSONObject.put("status", rs.getString("status"));
                    scheduleDetailJSONObject.put("user_id", rs.getInt("fk_company_id"));
                    scheduleDetailJSONObject.put("assignedToId", rs.getInt("user_id"));
                    scheduleDetailJSONObject.put("assignedFirstName", rs.getString("first_name"));
                    scheduleDetailJSONObject.put("assignedLastName", rs.getString("last_name"));
                    if (rs.getString("first_name") != null && rs.getString("last_name") != null) {
                        scheduleDetailJSONObject.put("assignedToInitialChars", Utility.getFirstTwoCharactersOfName(rs.getString("first_name"), rs.getString("last_name")));
                    }
                    scheduleDetailJSONObject.put("template_status",
                            TemplateStatus.valueOf(rs.getString("status")).getDisplayName());

                    if (!result.containsKey(String.valueOf(scheduleDate))) {
                        result.put(String.valueOf(scheduleDate), new JSONArray());
                    }

                    result.get(String.valueOf(scheduleDate)).add(scheduleDetailJSONObject);
                }
                List<PushedScheduledActionCompanies> pushedScheduledActionCompaniesList = pushedScheduledActionCompaniesService.getAllPushedScheduledActionCompaniesByCompanyIdAndDateDifference(companyId, new Timestamp(Date.valueOf(fromDate).getTime()), new Timestamp(Date.valueOf(toDate).getTime()));

                for (int i = 0; i< pushedScheduledActionCompaniesList.size(); i++){

                    JSONObject scheduleDetailJSONObject = new JSONObject();
                    PushedScheduledActionCompanies pushedScheduledactionCompanies = pushedScheduledActionCompaniesList.get(i);
                    
                    ScheduledEntityList scheduleEntiyList = pushedScheduledactionCompanies.getFkPushedScheduledActionEntityListId().getFkScheduledEntityListId();
                    
                    scheduleTime = scheduleEntiyList.getScheduleTime().getTime();

                    scheduleDate = scheduleTime;
                    scheduleDetailJSONObject.put("days", "");
                    scheduleDetailJSONObject.put("schedule_id", scheduleEntiyList.getScheduledEntityListId());
                    scheduleDetailJSONObject.put("entity_id", scheduleEntiyList.getEntityId());
                    scheduleDetailJSONObject.put("marketingName", "");
                    scheduleDetailJSONObject.put("isPushed", true);
                    scheduleDetailJSONObject.put("user_marketing_program_id", "");
                    scheduleDetailJSONObject.put("schedule_title", scheduleEntiyList.getScheduleTitle());
                    scheduleDetailJSONObject.put("schedule_description", scheduleEntiyList.getScheduleDesc());
                    scheduleDetailJSONObject.put("schedule_time", scheduleTime);
                    if (DateTimeUtil.dateEqualsCurrentDate(new Date(scheduleTime))) {
                        scheduleDetailJSONObject.put("is_today_active", "true");
                    } else {
                        scheduleDetailJSONObject.put("is_today_active", "false");
                    }

                    scheduleDetailJSONObject.put("is_recurring", "");
                    scheduleDetailJSONObject.put("entity_type", scheduleEntiyList.getEntityType());
                    scheduleDetailJSONObject.put("status", scheduleEntiyList.getStatus());
                    scheduleDetailJSONObject.put("user_id", companyId);

                    if (scheduleEntiyList.getAssignedTo().getUserId() != null && scheduleEntiyList.getAssignedTo().getFirstName() != null && scheduleEntiyList.getAssignedTo().getLastName() != null) {
                        scheduleDetailJSONObject.put("assignedToInitialChars", Utility.getFirstTwoCharactersOfName(scheduleEntiyList.getAssignedTo().getFirstName(), scheduleEntiyList.getAssignedTo().getLastName()));
                        scheduleDetailJSONObject.put("assignedToId", scheduleEntiyList.getAssignedTo().getUserId());
                        scheduleDetailJSONObject.put("assignedFirstName", scheduleEntiyList.getAssignedTo().getFirstName());
                        scheduleDetailJSONObject.put("assignedLastName", scheduleEntiyList.getAssignedTo().getLastName());
                    }
                    scheduleDetailJSONObject.put("template_status",
                            TemplateStatus.valueOf(scheduleEntiyList.getStatus()).getDisplayName());
                    if (!result.containsKey(String.valueOf(scheduleDate))) {
                        result.put(String.valueOf(scheduleDate), new JSONArray());
                    }

                    result.get(String.valueOf(scheduleDate)).add(scheduleDetailJSONObject);
                    
                } 
                logger.log(Level.INFO, "In com.intbit.dao.ScheduleDAO.getScheduledEntities");
            } catch (Exception e) {
                logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                        "Exception while getting the schedule:", null), e);
            }
        }
        Set<String> dateSet = result.keySet();
        JSONArray entityDataArray = new JSONArray();
        logger.log(Level.INFO, "dateSet" + dateSet.toString());
        try {
            Instant instant = fromDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            java.util.Date from_date = (java.util.Date) Date.from(instant);
            Instant instant1 = toDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            java.util.Date to_date = (java.util.Date) Date.from(instant1);

            LocalDate start = from_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = to_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                java.util.Date date_new = (java.util.Date) Date.from(instant);
                String date_epoch = String.valueOf(date_new.getTime());
                if (!dateSet.contains(date_epoch)) {
                    result.put(date_epoch, new JSONArray());
                }
                logger.log(Level.INFO, date.toString());
            }

            Map<String, JSONObject> sortedMap = new TreeMap(result);
            dateSet = sortedMap.keySet();

            HashMap entityObject = null;
            Iterator<String> iterator = dateSet.iterator();
            while (iterator.hasNext()) {
                entityObject = new HashMap();
                String dateString = iterator.next();
                String parsedDateString = new Date(Long.parseLong(dateString)).toString();
                boolean foundObjectInArray = false;
                for (Object entityDataArrayObject : entityDataArray) {
                    HashMap entityDataArrayJSONObject = (HashMap) entityDataArrayObject;
                    String dateInEntityDateJSONObject = (String) entityDataArrayJSONObject.get("date");
                    if (!StringUtil.isEmpty(dateInEntityDateJSONObject) && dateInEntityDateJSONObject.equals(parsedDateString)) {
                        foundObjectInArray = true;
                        break;
                    }
                }
                if (!foundObjectInArray) {
                    entityObject.put("date", parsedDateString);
                    JSONArray jsonArrayDateJSONEntities = new JSONArray();
                    Iterator resultIterator = result.entrySet().iterator();
                    Integer i = 0;
                    while (resultIterator.hasNext()) {
                        Map.Entry pair = (Map.Entry) resultIterator.next();
                        JSONArray jsonArray = (JSONArray) pair.getValue();
                        String jsonParsedString = new Date(Long.parseLong(pair.getKey().toString())).toString();
                        if (jsonParsedString.equals(parsedDateString)) {
                            if (jsonArray.size() > 0) {
                                for (i = 0; i < jsonArray.size(); i++) {
                                    jsonArrayDateJSONEntities.add(jsonArray.get(i));
                                }
                                resultIterator.remove();
                            }
                        }

                    }
                    entityObject.put("dataArray", jsonArrayDateJSONEntities);
                    entityDataArray.add(entityObject);
                }

            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, com.intbittech.utility.Utility.logMessage(e,
                    "Exception while reading the dates :", null), e);
        }
        JSONObject json_data = new JSONObject();
        json_data.put("noactionsmessage", "No Actions scheduled for this day");
        json_data.put("entitydata", entityDataArray);
        return json_data;

    }

}
