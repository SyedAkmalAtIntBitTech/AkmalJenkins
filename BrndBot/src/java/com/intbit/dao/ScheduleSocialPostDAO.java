/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.controller.IConstants;
import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import static com.intbit.dao.ScheduleDAO.updateScheduleEntityList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;

/**
 *
 * @author Mohamed
 */
public class ScheduleSocialPostDAO {

    private static final Logger logger
            = Logger.getLogger(ScheduleSocialPostDAO.class.getName());

    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();

    public static Map<String, Integer> addToScheduleSocialPost(int userId,
            String imageName,
            Integer marketing_program_id,
            Map<String, Object> tokenDataMap,
            Map<String, Object> metadataMap,
            String type,
            String scheduleTitle,
            String scheduleDesc,
            Timestamp scheduleTime,
            String templateStatus,
            Connection conn) throws SQLException {
        String sql = "INSERT INTO tbl_scheduled_socialpost_list "
                + " (user_id, image_name, token_data, metadata, type) VALUES"
                + " (?, ?, ?, ?, ?) RETURNING id";
        Map<String, Integer> methodResponse = new HashMap<>();
        int scheduleSocialPostId = -1;
        try {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setString(2, imageName);

                PGobject tokenData = new PGobject();
                tokenData.setType("json");
                tokenData.setValue(AppConstants.GSON.toJson(tokenDataMap));
                ps.setObject(3, tokenData);

                PGobject metadata = new PGobject();
                metadata.setType("json");
                metadata.setValue(AppConstants.GSON.toJson(metadataMap));
                ps.setObject(4, metadata);

                ps.setString(5, type);
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    if (rs.next()) {
                        scheduleSocialPostId = rs.getInt(1);
                        methodResponse.put("schedule_socialpost_id", scheduleSocialPostId);
                        logger.log(Level.INFO, "Id of Scheduled Social Post: " + scheduleSocialPostId);
                    }
                }

            }
            int scheduleId = ScheduleDAO.addToScheduleEntityList(scheduleSocialPostId,
                    scheduleTitle,
                    marketing_program_id,
                    scheduleDesc,
                    scheduleTime,
                    type,
                    "0",
                    templateStatus,
                    userId,
                    conn);
            methodResponse.put("schedule_entity_id", scheduleId);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw ex;
        }

        return methodResponse;
    }

    public static Map<String, Integer> updateActionsToScheduleSocialPost(int userId,
            int scheduleid,
            String imageName,
            Map<String, Object> tokenDataMap,
            Map<String, Object> metadataMap,
            String type,
            String templateStatus,
            Connection conn) throws SQLException {
        String sql = "INSERT INTO tbl_scheduled_socialpost_list "
                + " (user_id, image_name, token_data, metadata, type) VALUES"
                + " (?, ?, ?, ?, ?) RETURNING id";
        Map<String, Integer> methodResponse = new HashMap<>();
        int scheduleSocialPostId = -1;
        try {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setString(2, imageName);

                PGobject tokenData = new PGobject();
                tokenData.setType("json");
                tokenData.setValue(AppConstants.GSON.toJson(tokenDataMap));
                ps.setObject(3, tokenData);

                PGobject metadata = new PGobject();
                metadata.setType("json");
                metadata.setValue(AppConstants.GSON.toJson(metadataMap));
                ps.setObject(4, metadata);

                ps.setString(5, type);
                ps.execute();
                try (ResultSet rs = ps.getResultSet()) {
                    if (rs.next()) {
                        scheduleSocialPostId = rs.getInt(1);
                        methodResponse.put("schedule_socialpost_id", scheduleSocialPostId);
                        logger.log(Level.INFO, "Id of Scheduled Social Post: " + scheduleSocialPostId);
                    }
                }

            }
            int scheduleId = ScheduleDAO.updateScheduleEntityList(scheduleSocialPostId, 
                        scheduleid,
                        templateStatus,
                        conn);
            methodResponse.put("schedule_entity_id", scheduleId);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw ex;
        }

        return methodResponse;
    }
    
    public static Map<String, Object> getScheduleSocialPostDetails(int userId, int scheduleId) throws SQLException{
        Map<String, Object> socialPostDetails = new HashMap<>();
        String[] socialEntities = {"'"+ ScheduledEntityType.Facebook+"'", "'"+ ScheduledEntityType.Twitter+"'"};
        String SQL = "SELECT spList.* "
                + " FROM tbl_scheduled_socialpost_list spList, "
                + "  tbl_scheduled_entity_list seList "
                + " WHERE seList.id = ? AND "
                + " seList.entity_id = spList.id AND"
                + " seList.user_id = ? AND"
                + " seList.entity_type IN ("+ StringUtils.join(socialEntities, ",")+")";
        logger.info(SQL);
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL)){
            ps.setInt(1, scheduleId);
            ps.setInt(2, userId);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int entityId = rs.getInt("id");
                    String imageName = rs.getString("image_name");
                    String tokenData = rs.getString("token_data");
                    String metadata = rs.getString("metadata");
                    String type = rs.getString("type");
                    Map<String, Object> tokenDataMap = 
                            AppConstants.GSON.fromJson(tokenData, Map.class);
                    Map<String, Object> metadataMap = 
                            AppConstants.GSON.fromJson(metadata, Map.class);
                    
                    socialPostDetails.put("id", entityId);
                    socialPostDetails.put("image_name", imageName);
                    socialPostDetails.put("token_data", tokenDataMap);
                    socialPostDetails.put("metadata", metadataMap);
                    socialPostDetails.put("type", type);
                }
            }
        }
        return socialPostDetails;
    }
    
    public static JSONArray getScheduledActionsfacebook(int userid, Integer user_marketing_program_id)throws SQLException{
        JSONObject json_action_facebook = new JSONObject();
        JSONArray json_array_facebook = new JSONArray();
        String query = "Select * from tbl_scheduled_entity_list"
                + " where entity_id=?"
                + " and entity_type =?"
                + " and status = ?"
                + " and user_marketing_program_id =?"
                + " and user_id = ?";
        
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, 0);
            ps.setString(2, ScheduledEntityType.Facebook.toString());
            ps.setString(3, TemplateStatus.no_template.toString());
            ps.setInt(4, user_marketing_program_id);
            ps.setInt(5, userid);
            try(ResultSet result_set = ps.executeQuery()){
                while (result_set.next()){
                    
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
                    json_array_facebook.add(json_object);
                }
            }
        }
        return json_array_facebook;
    }

    public static JSONArray getScheduledActionstwitter(int userid, Integer user_marketing_program_id)throws SQLException{
        JSONObject json_action_facebook = new JSONObject();
        JSONArray json_array_twitter = new JSONArray();
        String query = "Select * from tbl_scheduled_entity_list"
                + " where entity_id=?"
                + " and entity_type =?"
                + " and status = ?"
                + " and user_marketing_program_id =?"
                + " and user_id = ?";
        
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, 0);
            ps.setString(2, ScheduledEntityType.Twitter.toString());
            ps.setString(3, TemplateStatus.no_template.toString());
            ps.setInt(4, user_marketing_program_id);
            ps.setInt(5, userid);
            
            try(ResultSet result_set = ps.executeQuery()){
                while (result_set.next()){
                    
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
                    
                    json_array_twitter.add(json_object);
                }
            }
        }
        return json_array_twitter;
    }
    
    public static void updateScheduleSocialPostDetails(Integer userid, 
            Integer Scheduleid,
            Integer entity_id,
            String schedule_title,
            Timestamp schedule_time,
            String schedule_desc,
            Map<String, Object> metadataMap
            )throws SQLException, ParseException{
        JSONObject json_metadata = new JSONObject();
        PGobject pg_object = new PGobject();
        String query_string = "Update tbl_scheduled_entity_list"
                + " SET schedule_title = ?, schedule_time = ?,"
                + " schedule_desc = ?" 
                + " Where id = ?";
        
        try(Connection conn = connectionManager.getConnection()){
            try(PreparedStatement prepared_statement = conn.prepareStatement(query_string)){
                prepared_statement.setString(1, schedule_title);
                prepared_statement.setTimestamp(2, schedule_time);
                prepared_statement.setString(3, schedule_desc);
                prepared_statement.setInt(4, Scheduleid);

                prepared_statement.executeUpdate();
            }
            json_metadata = getjsonmetadata(entity_id, conn);
            
        query_string = "Update tbl_scheduled_socialpost_list"
                + " SET metadata = ?"
                + " Where id = ?";
            
            pg_object.setType("json");
            pg_object.setValue(AppConstants.GSON.toJson(metadataMap));
            try(PreparedStatement prepared_statement = conn.prepareStatement(query_string)){
                prepared_statement.setObject(1, pg_object, Types.OTHER);
                prepared_statement.setInt(2, entity_id);
                prepared_statement.executeUpdate();
            }
        }
    }
    public static JSONObject getjsonmetadata(Integer entityid, 
            Connection connection)throws SQLException, ParseException{
        JSONObject json_metadata = new JSONObject();
        PGobject pgobject = new PGobject();
        JSONParser parser = new JSONParser();        
        String query_string = "Select metadata from tbl_scheduled_socialpost_list"
                + "where id=?";
        
        try(PreparedStatement prepared_statement = connection.prepareStatement(query_string)){
            try(ResultSet result_set = prepared_statement.executeQuery()){
                if (result_set.next()){
                    
                    pgobject = (PGobject) result_set.getObject("metadata");

                    pgobject.setType("json");
                    String obj = pgobject.getValue();
                    json_metadata = (org.json.simple.JSONObject) parser.parse(obj);
                }
            }
        }catch (Exception ex){
            logger.log(Level.SEVERE, util.Utility.logMessage(ex, "Exception while reading the json metadata:", null), ex);
        }
        return json_metadata;
    } 
}
