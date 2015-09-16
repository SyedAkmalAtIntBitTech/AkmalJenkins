/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.controller.IConstants;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.postgresql.util.PGobject;

/**
 *
 * @author Mohamed
 */
public class ScheduleDAO {
    
    private static final Logger logger = Logger.getLogger(ScheduleDAO.class.getName());
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();

    public static Map<String, Integer> addToScheduledEmailList(int userId,
            String subject,
            String body,
            String fromAddress,
            String emailListName,
            String fromName,
            String[] toAddress,
            String scheduledTitle,
            String scheduleDesc,
            Timestamp scheduledTime,
            String templateStatus
    ) throws SQLException{

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
                        + " (user_id, subject, body, from_address, email_list_name, from_name, to_email_addresses) VALUES "
                        + " (?, ?, ?, ?, ?, ?, ?) RETURNING id";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ps.setString(2, subject);
                    ps.setString(3, body);
                    ps.setString(4, fromAddress);
                    ps.setString(5, emailListName);
                    ps.setString(6, fromName);
                    for (int i = 0; i< toAddress.length; i++){
                        json_array_email_address.add(toAddress[i].trim());
                    }
                    json_email_addresses.put(IConstants.kEmailAddressesKey, json_array_email_address);
                    pg_object.setType("json");
                    pg_object.setValue(json_email_addresses.toJSONString());
                    ps.setObject(7, pg_object);
                    
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
                        scheduleDesc, 
                        scheduledTime, 
                        ScheduledEntityType.email.toString(), 
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
    
    /**
     * Add the array of to email addresses against the schedule email id.
     * 
     * Connection has to be passed to this API and the caller of the API 
     * should take care of committing the db operation.
     * 
     * @param scheduledEmailId
     * @param toEmailAddress
     * @param connection 
     */
    public static void addToScheduleEmailRecipient(int scheduledEmailId, 
            String[] toEmailAddress, 
            Connection connection) throws SQLException{
        if ( toEmailAddress == null){
            throw new IllegalArgumentException("Email Ids array is null");
        }
        
        final String sql = "INSERT INTO tbl_scheduled_email_recipients"
                + "(scheduled_email_id, to_email_address) "
                + " VALUES (?,?)";
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            for(String emailId : toEmailAddress){
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
    
    public static int addToScheduleEntityList(Integer entityId, 
            String scheduleTitle,
            String scheduleDesc,
            Timestamp scheduleTime,
            String entityType,
            String status, 
            int userId,
            Connection connection) throws SQLException{
        String sql = "INSERT INTO tbl_scheduled_entity_list"
                + " (entity_id, schedule_title, schedule_desc, schedule_time, entity_type, status, user_id) VALUES"
                + " (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        
        int scheduleId = -1;
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            if ( entityId == null){
                ps.setNull(1, Types.INTEGER);
            }else{
                ps.setInt(1, entityId);
            }
            
            ps.setString(2, scheduleTitle);
            ps.setString(3, scheduleDesc);
            ps.setTimestamp(4, scheduleTime);
            ps.setString(5, entityType);
            ps.setString(6, status);
            ps.setInt(7, userId);
            ps.execute();
            try(ResultSet rs = ps.getResultSet()){
                if (rs.next()) {
                    scheduleId = rs.getInt(1);
                    logger.log(Level.INFO, "Id of scheduled entity: " + scheduleId);
                }
            }
        }
        return scheduleId;
    }
    
    public static Map<String, List<Map<String, Object>>> getScheduledEntities(int userId, 
            LocalDate fromDate, LocalDate toDate) throws SQLException{
        
        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();
        
        String sql = "SELECT slist.*, tc.color, date(schedule_time) schedule_date "
                + " FROM tbl_scheduled_entity_list slist, "
                + " tbl_scheduled_entity_type_color tc "
                + " WHERE user_id = ? "
                + " AND date(schedule_time) <= ? "
                + " AND date(schedule_time) >= ? "
                + " AND slist.entity_type = tc.entity_type"
                + " ORDER BY schedule_time ";
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(toDate));
            ps.setDate(3, Date.valueOf(fromDate));
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Timestamp scheduleTimestamp = rs.getTimestamp("schedule_time");
                    long scheduleTime = scheduleTimestamp.getTime();
                    Map<String, Object> scheduleDetailMap = new HashMap<>();
                    long scheduleDate = rs.getDate("schedule_date").getTime();
                    String scheduleDateStr = new Date(scheduleDate).toString();
                    scheduleDetailMap.put("schedule_id", rs.getInt("id"));
                    scheduleDetailMap.put("entity_id", rs.getInt("entity_id"));
                    scheduleDetailMap.put("schedule_title", rs.getString("schedule_title"));
                    scheduleDetailMap.put("schedule_description", rs.getString("schedule_desc"));
                    scheduleDetailMap.put("schedule_time", scheduleTime);
                    scheduleDetailMap.put("entity_type", rs.getString("entity_type"));
                    scheduleDetailMap.put("status", rs.getString("status"));
                    scheduleDetailMap.put("user_id", rs.getInt("user_id"));
                    scheduleDetailMap.put("color", rs.getString("color"));
                    scheduleDetailMap.put("template_status", TemplateStatus.valueOf(rs.getString("status")).getDisplayName());
                    
                    if ( !result.containsKey(scheduleDateStr)){
                        result.put(scheduleDateStr, new ArrayList<>());
                    }
                    
                    result.get(scheduleDateStr).add(scheduleDetailMap);
                }
            }
        }
        
        return result;
    }
    
    public static Map<String, Object> getScheduleEmailDetails(int userId, int scheduleId) throws SQLException{
        String sql = "SELECT elist.* FROM "
                + " tbl_scheduled_email_list elist, "
                + " tbl_scheduled_entity_list enlist "
                + " WHERE elist.id = enlist.entity_id "
                + " AND enlist.user_id = ? "
                + " AND enlist.id = ?"
                + " AND enlist.entity_type = ? ";
        
        Map<String, Object> scheduleEmailDetails = new HashMap<>();
        int scheduleEmailId = -1;
        try(Connection connection = connectionManager.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, scheduleId);
                ps.setString(3, ScheduledEntityType.email.toString());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        scheduleEmailId = rs.getInt("id");

                        scheduleEmailDetails.put("schedule_email_id", rs.getInt("id"));
                        scheduleEmailDetails.put("user_id", rs.getInt("user_id"));
                        scheduleEmailDetails.put("subject", rs.getString("subject"));
                        scheduleEmailDetails.put("body", rs.getString("body"));
                        scheduleEmailDetails.put("from_address", rs.getString("from_address"));
                        scheduleEmailDetails.put("email_list_name", rs.getString("email_list_name"));
                        scheduleEmailDetails.put("from_name", rs.getString("from_name"));
                    }
                }
            }
            if ( scheduleEmailId != -1){
                List<String> toEmailAddress = new ArrayList<>();
                String sql2 = "SELECT to_email_address FROM tbl_scheduled_email_recipients "
                        + " WHERE scheduled_email_id = ?";
                try(PreparedStatement ps = connection.prepareStatement(sql2)){
                    ps.setInt(1, scheduleEmailId);
                    try(ResultSet rs = ps.executeQuery()){
                        while(rs.next()){
                            toEmailAddress.add(rs.getString("to_email_address"));
                        }
                    }
                }
                scheduleEmailDetails.put("to_email_addresses", toEmailAddress);
                
            }
        }
        
        //now get the to email addresses
        return scheduleEmailDetails;
    }
}
