/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.intbit.ConnectionManager;
import com.intbit.EmailAndSocialStatus;
import com.intbit.ScheduledEntityType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Timestamp scheduledTime
    ) throws SQLException{

        int emailScheduleId = -1;
        int scheduleEntityId = -1;
        Map<String, Integer> returnMap = new HashMap<>();
        
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String sql = "INSERT INTO tbl_scheduled_email_list "
                        + " (user_id, subject, body, from_address, email_list_name, from_name) VALUES "
                        + " (?, ?, ?, ?, ?, ?) RETURNING id";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, userId);
                    ps.setString(2, subject);
                    ps.setString(3, body);
                    ps.setString(4, fromAddress);
                    ps.setString(5, emailListName);
                    ps.setString(6, fromName);
                    ps.execute();
                    try (ResultSet resultSet = ps.getResultSet()) {

                        if (resultSet.next()) {
                            emailScheduleId = resultSet.getInt(1);
                            logger.log(Level.INFO, "Id of scheduled email: " + emailScheduleId);
                        }
                    }
                }
                addToScheduleEmailRecipient(emailScheduleId, toAddress, connection);
                scheduleEntityId = addToScheduleEntityList(emailScheduleId, 
                        scheduledTitle, 
                        scheduledTime, 
                        ScheduledEntityType.email.toString(), 
                        EmailAndSocialStatus.scheduled.toString(),
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
    
    public static int addToScheduleEntityList(int entityId, 
            String scheduleTitle,
            Timestamp scheduleTime,
            String entityType,
            String status, 
            int userId,
            Connection connection) throws SQLException{
        String sql = "INSERT INTO tbl_scheduled_entity_list"
                + " (entity_id, schedule_title, schedule_time, entity_type, status, user_id) VALUES"
                + " (?, ?, ?, ?, ?, ?) RETURNING id";
        
        int scheduleId = -1;
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, entityId);
            ps.setString(2, scheduleTitle);
            ps.setTimestamp(3, scheduleTime);
            ps.setString(4, entityType);
            ps.setString(5, status);
            ps.setInt(6, userId);
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
    
    public static List<Map<String, Object>> getScheduledEntities(int userId, 
            LocalDate scheduleDate) throws SQLException{
        
        List<Map<String, Object>> result = new ArrayList<>();
        
        String sql = "SELECT slist.*, tc.color "
                + " FROM tbl_scheduled_entity_list slist, "
                + " tbl_scheduled_entity_type_color tc "
                + " WHERE user_id = ? "
                + " AND date(schedule_time) = ? "
                + " AND slist.entity_type = tc.entity_type";
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(scheduleDate));
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Map<String, Object> scheduleDetailMap = new HashMap<>();
                    scheduleDetailMap.put("schedule_id", rs.getInt("id"));
                    scheduleDetailMap.put("entity_id", rs.getInt("entity_id"));
                    scheduleDetailMap.put("schedule_title", rs.getString("schedule_title"));
                    scheduleDetailMap.put("schedule_time", rs.getTimestamp("schedule_time"));
                    scheduleDetailMap.put("entity_type", rs.getString("entity_type"));
                    scheduleDetailMap.put("status", rs.getString("status"));
                    scheduleDetailMap.put("user_id", rs.getInt("user_id"));
                    scheduleDetailMap.put("color", rs.getString("color"));
                    result.add(scheduleDetailMap);
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
