/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.ScheduledEntityType;
import static com.intbit.dao.ScheduleDAO.updateScheduleEntityList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
                    scheduleDesc,
                    scheduleTime,
                    type,
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
        String[] socialEntities = {"'"+ ScheduledEntityType.facebook+"'", "'"+ ScheduledEntityType.twitter+"'"};
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
    
    public static JSONArray getScheduledActionsfacebook(int userid)throws SQLException{
        JSONObject json_action_facebook = new JSONObject();
        JSONArray json_array_facebook = new JSONArray();
        String query = "Select * from tbl_scheduled_entity_list"
                + " where entity_id=?"
                + " and entity_type =?"
                + " and user_id = ?";
        
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, 0);
            ps.setString(2, "facebook");
            ps.setInt(3, userid);
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

    public static JSONArray getScheduledActionstwitter(int userid)throws SQLException{
        JSONObject json_action_facebook = new JSONObject();
        JSONArray json_array_twitter = new JSONArray();
        String query = "Select * from tbl_scheduled_entity_list"
                + " where entity_id=?"
                + " and entity_type =?"
                + " and user_id = ?";
        
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, 0);
            ps.setString(2, "twitter");
            ps.setInt(3, userid);
            
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
    
    public void updateScheduleSocialPostDetails(Integer userid, 
            String Scheduleid
            ){
        
    }
}
