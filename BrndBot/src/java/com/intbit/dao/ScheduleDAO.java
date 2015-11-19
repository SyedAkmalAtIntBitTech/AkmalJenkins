/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.controller.IConstants;
import com.divtohtml.StringUtil;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import static com.intbit.dao.ScheduleSocialPostDAO.getjsonmetadata;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.bytecode.analysis.Type;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;
import util.DateTimeUtil;

/**
 *
 * @author Mohamed
 */
public class ScheduleDAO {

    private static final Logger logger = Logger.getLogger(ScheduleDAO.class.getName());
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();

    public static Map<String, Integer> addToScheduledEmailList(int userId,
            String subject,
            Integer marketing_program_id,
            String body,
            String fromAddress,
            String emailListName,
            String fromName,
            String replytoEmailAddress,
            String[] toAddress,
            String scheduledTitle,
            String scheduleDesc,
            Timestamp scheduledTime,
            String templateStatus
    ) throws SQLException {

        int emailScheduleId = -1;
        int scheduleEntityId = -1;
        Map<String, Integer> returnMap = new HashMap<>();
        PGobject pg_object = new PGobject();
        JSONObject json_email_addresses = new JSONObject();
        JSONArray json_array_email_address = new JSONArray();

        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String sql = "INSERT INTO tbl_scheduled_email_list "
                        + " (user_id, subject, body, from_address, email_list_name, from_name, to_email_addresses, reply_to_email_address) VALUES "
                        + " (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ps.setString(2, subject);
                    ps.setString(3, body);
                    ps.setString(4, fromAddress);
                    ps.setString(5, emailListName);
                    ps.setString(6, fromName);
                    for (int i = 0; i < toAddress.length; i++) {
                        json_array_email_address.add(toAddress[i].trim());
                    }
                    json_email_addresses.put(IConstants.kEmailAddressesKey, json_array_email_address);
                    pg_object.setType("json");
                    pg_object.setValue(json_email_addresses.toJSONString());
                    ps.setObject(7, pg_object);
                    ps.setString(8, replytoEmailAddress);
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
                        userId,
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
            int userId,
            int scheduleid,
            String subject,
            String body,
            String fromAddress,
            String emailListName,
            String fromName,
            String replytoEmailAddress,
            String[] toAddress,
            String scheduleDesc,
            String templateStatus
    ) throws SQLException {

        int emailScheduleId = -1;
        int scheduleEntityId = -1;
        Map<String, Integer> returnMap = new HashMap<>();
        PGobject pg_object = new PGobject();
        JSONObject json_email_addresses = new JSONObject();
        JSONArray json_array_email_address = new JSONArray();

        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String sql = "INSERT INTO tbl_scheduled_email_list "
                        + " (user_id, subject, body, from_address, email_list_name, from_name, to_email_addresses, reply_to_email_address) VALUES "
                        + " (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ps.setString(2, subject);
                    ps.setString(3, body);
                    ps.setString(4, fromAddress);
                    ps.setString(5, emailListName);
                    ps.setString(6, fromName);
                    for (int i = 0; i < toAddress.length; i++) {
                        json_array_email_address.add(toAddress[i].trim());
                    }
                    json_email_addresses.put(IConstants.kEmailAddressesKey, json_array_email_address);
                    pg_object.setType("json");
                    pg_object.setValue(json_email_addresses.toJSONString());
                    ps.setObject(7, pg_object);
                    ps.setString(8, replytoEmailAddress);
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

    /**
     * Add the array of to email addresses against the schedule email id.
     *
     * Connection has to be passed to this API and the caller of the API should
     * take care of committing the db operation.
     *
     * @param scheduledEmailId
     * @param toEmailAddress
     * @param connection
     */
    public static void addToScheduleEmailRecipient(int scheduledEmailId,
            String[] toEmailAddress,
            Connection connection) throws SQLException {
        if (toEmailAddress == null) {
            throw new IllegalArgumentException("Email Ids array is null");
        }

        final String sql = "INSERT INTO tbl_scheduled_email_recipients"
                + "(scheduled_email_id, to_email_address) "
                + " VALUES (?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (String emailId : toEmailAddress) {
                ps.setInt(1, scheduledEmailId);
                ps.setString(2, emailId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(EmailHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public static int updateScheduleEntityList(int entityID, int schedule_id,
            String templateStatus, Connection connection) throws SQLException {
        String query = "Update tbl_scheduled_entity_list"
                + " SET entity_id = ?, status = ?"
                + " Where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, entityID);
            ps.setString(2, templateStatus);
            ps.setInt(3, schedule_id);

            ps.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e,
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
            int userId,
            Connection connection) throws SQLException {
        String sql = "INSERT INTO tbl_scheduled_entity_list"
                + " (entity_id, schedule_title,schedule_time,entity_type,status,user_id, schedule_desc,is_recuring,user_marketing_program_id,days,till_date,recuring_email_id ) VALUES"
                + " (?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id";

        int scheduleId = -1;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (entityId == null) {
                ps.setInt(1, 0);
            } else {
                ps.setInt(1, entityId);
            }
            ps.setString(2, scheduleTitle);
            ps.setTimestamp(3, scheduleTime);
            ps.setString(4, entityType);
            ps.setString(5, status);
            ps.setInt(6, userId);
            ps.setString(7, scheduleDesc);
            ps.setBoolean(8, false);
            ps.setInt(9, marketing_program_id);
            ps.setInt(10, Integer.parseInt(days));
            ps.setTimestamp(11, null);
            ps.setNull(12, java.sql.Types.INTEGER);

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

    public static int updateScheduledEntity(Integer scheduleId,
            String scheduleTitle,
            String scheduleDesc,
            Timestamp scheduleTime,
            String entityType,
            int userId,
            int days,
            Connection connection) throws SQLException {
        String query_string = "Update tbl_scheduled_entity_list"
                + " SET schedule_title = ?, schedule_time = ?,"
                + " entity_type = ?, schedule_desc = ?, days= ?"
                + " Where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query_string)) {

            ps.setString(1, scheduleTitle);
            ps.setTimestamp(2, scheduleTime);
            ps.setString(3, entityType);
            ps.setString(4, scheduleDesc);
            ps.setInt(5, days);
            ps.setInt(6, scheduleId);
            ps.execute();

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e,
                    "Exception while updating the schedule:", null), e);
        }
        return scheduleId;
    }

    public static JSONArray getScheduledActions(int userid, Integer user_marketing_program_id) throws SQLException {
        JSONObject json_action_facebook = new JSONObject();
        JSONArray json_array_email = new JSONArray();
        String query = "Select * from tbl_scheduled_entity_list"
                + " where entity_id=?"
                + " and entity_type =?"
                + " and user_marketing_program_id =?"
                + " and user_id = ?";

        try (Connection conn = connectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, 0);
            ps.setString(2, ScheduledEntityType.Email.toString());
            ps.setInt(3, user_marketing_program_id);
            ps.setInt(4, userid);
            try (ResultSet result_set = ps.executeQuery()) {
                while (result_set.next()) {

                    JSONObject json_object = new JSONObject();
                    Integer id = result_set.getInt("id");
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

    public static JSONObject getScheduledEntities(int userId,
            LocalDate fromDate, LocalDate toDate) throws SQLException {

        Map<String, JSONArray> result = new LinkedHashMap<>();
//        String sql = "SELECT DISTINCT ON (id) slist.*, date(programtable.date_event) - slist.days as cal_schedule_time, date(schedule_time) schedule_date "
//                + " FROM tbl_scheduled_entity_list slist, "
//                + " tbl_scheduled_entity_type_color tc, tbl_user_marketing_program programtable"
//                + " WHERE slist.user_id = ? "
//                + " AND (date(schedule_time) <= ? "
//                + " AND date(schedule_time) >= ?) "
//                + " OR ((slist.is_recuring = 'false' AND date(programtable.date_event) - slist.days <= ? "
//                + " AND date(programtable.date_event) - slist.days >= ?) "
//                + " OR (slist.is_recuring = 'true' AND date(programtable.date_event) <= ? "
//                + " AND date(programtable.date_event) >= ?)) "
//                + " AND slist.entity_type = tc.entity_type"
//                + " AND slist.user_marketing_program_id = programtable.id"
//                + " ORDER BY slist.id, schedule_time ";
        String sql = "SELECT DISTINCT ON (id) slist.*, concat(date(programtable.date_event) - slist.days, ' ', slist.schedule_time::time WITH TIME ZONE) as cal_schedule_time, date(schedule_time) schedule_date "
                + " FROM tbl_scheduled_entity_list slist, "
                + " tbl_scheduled_entity_type_color tc, tbl_user_marketing_program programtable"
                + " WHERE slist.user_id = ? "
                + " AND (date(schedule_time) <= ? "
                + " AND date(schedule_time) >= ?) "
                + " OR ((slist.is_recuring = 'false' AND date(programtable.date_event) - slist.days <= ? "
                + " AND date(programtable.date_event) - slist.days >= ?) "
                + " OR (slist.is_recuring = 'true' AND date(programtable.date_event) <= ? "
                + " AND date(programtable.date_event) >= ?)) "
                + " AND slist.user_marketing_program_id = programtable.id"
                + " ORDER BY slist.id, schedule_time ";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(toDate));
            ps.setDate(3, Date.valueOf(fromDate));
            ps.setDate(4, Date.valueOf(toDate));
            ps.setDate(5, Date.valueOf(fromDate));
            ps.setDate(6, Date.valueOf(toDate));
            ps.setDate(7, Date.valueOf(fromDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Timestamp scheduleTimestamp = rs.getTimestamp("schedule_time");
                    long scheduleTime = scheduleTimestamp.getTime();
                    JSONObject scheduleDetailJSONObject = new JSONObject();
                    long scheduleDate;
                    if (rs.getInt("user_marketing_program_id") == 0) {
                        scheduleDate = rs.getDate("schedule_date").getTime();
                    } else {
                        Timestamp scheduleTimestamp1 = rs.getTimestamp("cal_schedule_time");
                        if (rs.getBoolean("is_recuring")) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(scheduleTimestamp1);
                            cal.add(Calendar.DAY_OF_WEEK, rs.getInt("days"));
                            scheduleDate = cal.getTime().getTime();
                        } else {
                            scheduleDate = scheduleTimestamp1.getTime();
                        }
                    }
                    //String scheduleDateStr = new Date(scheduleDate).toString();
                    scheduleDetailJSONObject.put("days", rs.getInt("days"));
                    scheduleDetailJSONObject.put("schedule_id", rs.getInt("id"));
                    scheduleDetailJSONObject.put("entity_id", rs.getInt("entity_id"));
                    int marketingId = rs.getInt("user_marketing_program_id");
                    String marketingName = getMarketingProgramName(marketingId, connection);
                    scheduleDetailJSONObject.put("marketingName", marketingName);
                    scheduleDetailJSONObject.put("user_marketing_program_id", rs.getInt("user_marketing_program_id"));
                    scheduleDetailJSONObject.put("schedule_title", rs.getString("schedule_title"));
                    scheduleDetailJSONObject.put("schedule_description", rs.getString("schedule_desc"));
                    if (rs.getInt("user_marketing_program_id") == 0) {
                        scheduleDetailJSONObject.put("schedule_time", scheduleTime);
                        if (DateTimeUtil.dateEqualsCurrentDate(new Date(scheduleTime))) {
                            scheduleDetailJSONObject.put("is_today_active", "true");
                        } else {
                            scheduleDetailJSONObject.put("is_today_active", "false");
                        }
                    } else {
                        Timestamp scheduleTimestamp1 = rs.getTimestamp("cal_schedule_time");
                        long scheduleTime1;
                        if (rs.getBoolean("is_recuring")) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(scheduleTimestamp1);
                            cal.add(Calendar.DAY_OF_WEEK, rs.getInt("days"));
                            scheduleTime1 = cal.getTime().getTime();
                        } else {
                            scheduleTime1 = scheduleTimestamp1.getTime();
                        }
                        scheduleDetailJSONObject.put("schedule_time", scheduleTime1);
                        if (DateTimeUtil.dateEqualsCurrentDate(new Date(scheduleTime1))) {
                            scheduleDetailJSONObject.put("is_today_active", "true");
                        } else {
                            scheduleDetailJSONObject.put("is_today_active", "false");
                        }
                    }
                    scheduleDetailJSONObject.put("is_recuring", rs.getBoolean("is_recuring"));
                    scheduleDetailJSONObject.put("entity_type", rs.getString("entity_type"));
                    scheduleDetailJSONObject.put("status", rs.getString("status"));
                    scheduleDetailJSONObject.put("user_id", rs.getInt("user_id"));
                    //scheduleDetailJSONObject.put("color", rs.getString("color"));
                    scheduleDetailJSONObject.put("template_status",
                            TemplateStatus.valueOf(rs.getString("status")).getDisplayName());

                    if (!result.containsKey(String.valueOf(scheduleDate))) {
                        result.put(String.valueOf(scheduleDate), new JSONArray());
                    }

                    result.get(String.valueOf(scheduleDate)).add(scheduleDetailJSONObject);
                }
                logger.log(Level.INFO, "result:" + result.toString());
            } catch (Exception e) {
                logger.log(Level.SEVERE, util.Utility.logMessage(e,
                        "Exception while deleting the schedule:", null), e);
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
                    while (resultIterator.hasNext()) {
                        Map.Entry pair = (Map.Entry) resultIterator.next();
                        JSONArray jsonArray = (JSONArray) pair.getValue();
                        String jsonParsedString = new Date(Long.parseLong(pair.getKey().toString())).toString();
                        if (jsonParsedString.equals(parsedDateString)) {
                            if (jsonArray.size() > 0) {
                                jsonArrayDateJSONEntities.add(jsonArray.get(0));
                                resultIterator.remove(); // avoids a ConcurrentModificationException                            
                            }
                        }

                    }
//                JSONArray jsonArrayDateEntities = result.get(dateString);
                    entityObject.put("dataArray", jsonArrayDateJSONEntities);
                    entityDataArray.add(entityObject);
                }

            }
            logger.log(Level.INFO, entityDataArray.toString());

        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e,
                    "Exception while reading the dates :", null), e);
        }
        JSONObject json_data = new JSONObject();
        json_data.put("noactionsmessage", "No Actions Setup");
        json_data.put("entitydata", entityDataArray);
        return json_data;

    }

    public static String getMarketingProgramName(int marketingId, Connection connection) {
        String marketingProgram = "";
        String sql1 = "SELECT name FROM tbl_user_marketing_program WHERE id = ? ";
        try (PreparedStatement ps1 = connection.prepareStatement(sql1)) {
            ps1.setInt(1, marketingId);
            try (ResultSet rs1 = ps1.executeQuery()) {
                if (rs1.next()) {
                    marketingProgram = rs1.getString("name");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, util.Utility.logMessage(e,
                    "Exception while getting the program names :", null), e);
        }
        return marketingProgram;
    }

    public static Map<String, Object> getScheduleEmailDetails(int userId, int scheduleId) throws SQLException, ParseException {
        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject emailJSONObject = new org.json.simple.JSONObject();
        JSONArray emailids_json_array = new JSONArray();
        String email_ids = "";
        String sql = "SELECT elist.* FROM "
                + " tbl_scheduled_email_list elist, "
                + " tbl_scheduled_entity_list enlist "
                + " WHERE elist.id = enlist.entity_id "
                + " AND enlist.user_id = ? "
                + " AND enlist.id = ?"
                + " AND enlist.entity_type = ? ";

        Map<String, Object> scheduleEmailDetails = new HashMap<>();
        int scheduleEmailId = -1;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, scheduleId);
                ps.setString(3, ScheduledEntityType.Email.toString());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        scheduleEmailId = rs.getInt("id");
                        pgobject = (PGobject) rs.getObject("to_email_addresses");
                        pgobject.setType("json");
                        String obj = pgobject.getValue();
                        emailJSONObject = (org.json.simple.JSONObject) parser.parse(obj);
                        emailids_json_array = (JSONArray) emailJSONObject.get(IConstants.kEmailAddressesKey);

                        for (int i = 0; i < emailids_json_array.size(); i++) {
                            email_ids = emailids_json_array.get(i) + "," + email_ids;
                        }

                        scheduleEmailDetails.put("schedule_email_id", rs.getInt("id"));
                        scheduleEmailDetails.put("user_id", rs.getInt("user_id"));
                        scheduleEmailDetails.put("subject", rs.getString("subject"));
                        scheduleEmailDetails.put("body", rs.getString("body"));
                        scheduleEmailDetails.put("from_address", rs.getString("from_address"));
                        scheduleEmailDetails.put("reply_to_email_address", rs.getString("reply_to_email_address"));
                        scheduleEmailDetails.put("email_list_name", rs.getString("email_list_name"));
                        scheduleEmailDetails.put("from_name", rs.getString("from_name"));
                        scheduleEmailDetails.put("to_email_addresses", email_ids);

                    }
                }
            }
        }

        //now get the to email addresses
        return scheduleEmailDetails;
    }

    public static void deleteSchedules(int user_id, String schedule_id) throws SQLException, ParseException {

        String schedule_ids[] = schedule_id.split(",");

        for (int i = 0; i < schedule_ids.length; i++) {
            String query_String = "Select * from tbl_scheduled_entity_list"
                    + " Where id=?"
                    + " and user_id=?";

            try (Connection connection = connectionManager.getConnection()) {
                try (PreparedStatement prepared_statement = connection.prepareStatement(query_String)) {
                    prepared_statement.setInt(1, Integer.parseInt(schedule_ids[i]));
                    prepared_statement.setInt(2, user_id);
                    try (ResultSet result_set = prepared_statement.executeQuery()) {
                        if (result_set.next()) {
                            Integer entity_id = result_set.getInt("entity_id");
                            String entity_type = result_set.getString("entity_type");
                            if (entity_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
                                String query_string1 = "Delete from tbl_scheduled_email_list"
                                        + " where id = ?";
                                try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                    prepared_statement1.setInt(1, entity_id);
                                    prepared_statement1.execute();
                                } catch (Exception e) {
                                    logger.log(Level.SEVERE, util.Utility.logMessage(e,
                                            "Exception while deleting the schedule:", null), e);
                                }
                            } else if ((entity_type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString()))
                                    || (entity_type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString()))) {
                                String query_string1 = "Delete from tbl_scheduled_socialpost_list"
                                        + " where id = ?";
                                try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                    prepared_statement1.setInt(1, entity_id);
                                    prepared_statement1.execute();
                                } catch (Exception e) {
                                    logger.log(Level.SEVERE, util.Utility.logMessage(e,
                                            "Exception while deleting the schedule:", null), e);
                                }
                            }

                        }
                    }

                } catch (Exception e) {
                    logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
                }
                String query_string2 = "Delete from tbl_scheduled_entity_list"
                        + " where id = ?";
                try (PreparedStatement prepared_statement2 = connection.prepareStatement(query_string2)) {
                    prepared_statement2.setInt(1, Integer.parseInt(schedule_ids[i]));
                    prepared_statement2.execute();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
                }
            }

        }
    }

    public static void removeSavedTemplate(int user_id, Integer schedule_id) throws SQLException, ParseException {

        String query_String = "Select * from tbl_scheduled_entity_list"
                + " Where id=?"
                + " and user_id=?";

        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_String)) {
                prepared_statement.setInt(1, schedule_id);
                prepared_statement.setInt(2, user_id);
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    if (result_set.next()) {
                        Integer entity_id = result_set.getInt("entity_id");
                        String entity_type = result_set.getString("entity_type");
                        if (entity_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
                            String query_string1 = "Delete from tbl_scheduled_email_list"
                                    + " where id = ?";
                            try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                prepared_statement1.setInt(1, entity_id);
                                prepared_statement1.execute();
                            } catch (Exception e) {
                                logger.log(Level.SEVERE, util.Utility.logMessage(e,
                                        "Exception while deleting the schedule:", null), e);
                            }
                        } else if ((entity_type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString()))
                                || (entity_type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString()))) {
                            String query_string1 = "Delete from tbl_scheduled_socialpost_list"
                                    + " where id = ?";
                            try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                                prepared_statement1.setInt(1, entity_id);
                                prepared_statement1.execute();
                            } catch (Exception e) {
                                logger.log(Level.SEVERE, util.Utility.logMessage(e,
                                        "Exception while deleting the schedule:", null), e);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
            }
            String query_string2 = "UPDATE tbl_scheduled_entity_list"
                    + " SET entity_id = ?, status = ? where id = ?";
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_string2)) {
                prepared_statement.setInt(1, 0);
                prepared_statement.setString(2, TemplateStatus.no_template.toString());
                prepared_statement.setInt(3, schedule_id);
                prepared_statement.executeUpdate();
            } catch (Exception e) {
                logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating the schedule:", null), e);
            }
        }
    }

    public static void deleteSchedule(int user_id, Integer schedule_id) throws SQLException, ParseException {

        String query_String = "Select * from tbl_scheduled_entity_list"
                + " Where id=?"
                + " and user_id=?";

        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_String)) {
                prepared_statement.setInt(1, schedule_id);
                prepared_statement.setInt(2, user_id);
//                 prepared_statement.setString(3, ScheduledEntityType.email.toString());
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    if (result_set.next()) {
                        Integer entity_id = result_set.getInt("entity_id");
                        String query_string1 = "Delete from tbl_scheduled_email_list"
                                + " where id = ?";
                        try (PreparedStatement prepared_statement1 = connection.prepareStatement(query_string1)) {
                            prepared_statement1.setInt(1, entity_id);
                            prepared_statement1.executeUpdate();
                        }
                    }
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
            }
            String query_string2 = "Delete from tbl_scheduled_entity_list"
                    + " where id = ?";
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_string2)) {
                prepared_statement.setInt(1, schedule_id);
                prepared_statement.executeUpdate();
            } catch (Exception e) {
                logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while deleting the schedule:", null), e);
            }
        }

    }

    public static void updateNoteDetails(Integer userid,
            Integer scheduleID,
            String scheduleTitle,
            String scheduleDesc,
            String status,
            Timestamp scheduleTime) throws SQLException {

        String query_string = "Update tbl_scheduled_entity_list"
                + " SET schedule_title = ?, schedule_time = ?,"
                + " status = ?, schedule_desc = ?"
                + " Where id = ?";
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = connection.prepareStatement(query_string)) {
                prepared_statement.setString(1, scheduleTitle);
                prepared_statement.setTimestamp(2, scheduleTime);
                prepared_statement.setString(3, status);
                prepared_statement.setString(4, scheduleDesc);
                prepared_statement.setInt(5, scheduleID);

                prepared_statement.executeUpdate();
            } catch (Exception e) {
                logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while "
                        + "updating the note schedule:", null), e);
            }
        }
    }

    public static void updateScheduleEmailDetails(Integer userid,
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
        String query_string = "Update tbl_scheduled_entity_list"
                + " SET schedule_title = ?, schedule_time = ?"
                + " Where id = ?";

        try (Connection conn = connectionManager.getConnection()) {
            try (PreparedStatement prepared_statement = conn.prepareStatement(query_string)) {
                prepared_statement.setString(1, scheduledTitle);
                prepared_statement.setTimestamp(2, scheduledTime);
                prepared_statement.setInt(3, ScheduleID);

                prepared_statement.executeUpdate();
            }

            json_email_addresses = getjsontoemailaddresses(entityID, conn);

            query_string = "Update tbl_scheduled_email_list"
                    + " SET subject = ?, from_address = ?, email_list_name = ?,"
                    + " to_email_addresses = ?, reply_to_email_address = ?"
                    + " Where id = ?";

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
            logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating the email schedule:", null), e);
        }
    }

    public static JSONObject getjsontoemailaddresses(Integer entityid,
            Connection connection) throws SQLException, ParseException {
        JSONObject json_email_addresses = new JSONObject();
        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();
        String query_string = "Select to_email_addresses from tbl_scheduled_email_list"
                + " where id=?";

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
            logger.log(Level.SEVERE, util.Utility.logMessage(ex, "Exception while reading the json email addresses:", null), ex);
        }
        return json_email_addresses;
    }

}
