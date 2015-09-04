/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.intbit.EmailAndSocialStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Timestamp scheduleTime) throws SQLException {
        String sql = "INSERT INTO tbl_scheduled_socialpost_list "
                + " (user_id, image_name, token_data, metadata, type) VALUES"
                + " (?, ?, ?, ?, ?) RETURNING id";
        Map<String, Integer> methodResponse = new HashMap<>();
        int scheduleSocialPostId = -1;
        try (Connection conn = connectionManager.getConnection()) {
            conn.setAutoCommit(false);
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
                    try(ResultSet rs = ps.getResultSet()){
                        if (rs.next()) {
                            scheduleSocialPostId = rs.getInt(1);
                            methodResponse.put("schedule_socialpost_id", scheduleSocialPostId);
                            logger.log(Level.INFO, "Id of Scheduled Social Post: " + scheduleSocialPostId);
                        }
                    }

                } 
                int scheduleId = ScheduleDAO.addToScheduleEntityList(scheduleSocialPostId, 
                        scheduleTitle, 
                        scheduleTime, 
                        type, 
                        EmailAndSocialStatus.scheduled.toString(), 
                        userId, 
                        conn);
                methodResponse.put("schedule_entity_id", scheduleId);
                conn.commit();
            }catch (SQLException ex) {
                conn.rollback();
                logger.log(Level.SEVERE, null, ex);
            }
        }

        return methodResponse;
    }
}
