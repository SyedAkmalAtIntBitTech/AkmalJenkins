/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.controller.IConstants;
import com.intbit.ConnectionManager;
import email.mandrill.MessageResponse;
import email.mandrill.MessageResponses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.postgresql.util.PGobject;

/**
 *
 * @author Mohamed
 */
public class EmailHistoryDAO {
    private static final Logger logger = Logger.getLogger(EmailHistoryDAO.class.getName());
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();

    public static void insertMandrillEmailId(MessageResponses mandrillResponse,
            int emailHistoryId){
        String insertSql = "INSERT INTO tbl_email_mandrill_history "
                + " (email_history_id, to_email, mandrill_email_id, status, reject_reason) VALUES "
                + " (?, ?, ?, ?, ?) ";
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertSql)){
            for ( MessageResponse msgResp : mandrillResponse.getMessageResponseList()){
                ps.setInt(1, emailHistoryId);
                ps.setString(2, msgResp.getEmail());
                ps.setString(3, msgResp.getId());
                ps.setString(4, msgResp.getStatus());
                ps.setString(5, msgResp.getReject_reason() == null?"":msgResp.getReject_reason());
                ps.addBatch();
            }
            
            ps.executeBatch();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, null, ex.getNextException());
        }
    }
    
    /**
     * Fetches the email tags created for this user.
     * 
     * @param userId
     * @return 
     */
    public static Set<String> getTagsForUser(int userId){
        Set<String> tagSet = new HashSet<>();
        String sql = "SELECT DISTINCT email_tag "
                + " FROM tbl_emailsenthistory "
                + " WHERE user_id = ? AND"
                + " email_tag IS NOT NULL";
        try(Connection conn = connectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, userId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    tagSet.add(rs.getString("email_tag"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmailHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tagSet;
    }
    
    public static int addToEmailHistory(
            Integer userid, 
            String contenthtml,
            String emailaddress, 
            String emaillistname,
            String toemailAddresses,
            String emailSubject,
            String tag
    ) throws SQLException {
        int lastUpdateId = -1;
        PGobject pg_object = new PGobject();
        JSONObject json_email_addresses = new JSONObject();
        JSONArray json_array_email_address = new JSONArray();
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String query_string = "INSERT INTO tbl_emailsenthistory "
                        + " (user_id, timesent, contenthtml, emailaddress, emaillistname, email_tag, subject, toemailaddresses) VALUES "
                        + " (?,CURRENT_TIMESTAMP,?,?,?,?,?,?) RETURNING id";
                try (PreparedStatement prepared_statement = connection.prepareStatement(query_string)) {
                    prepared_statement.setInt(1, userid);
                    prepared_statement.setString(2, contenthtml);
                    prepared_statement.setString(3, emailaddress);
                    prepared_statement.setString(4, emaillistname);
                    prepared_statement.setString(5, tag);
                    prepared_statement.setString(6, emailSubject);
                    String[] emailids = toemailAddresses.split(",");
                    for (int i = 0; i< emailids.length; i++){
                        json_array_email_address.add(emailids[i].trim());
                    }
                    json_email_addresses.put(IConstants.kEmailAddressesKey, json_array_email_address);
                    pg_object.setType("json");
                    pg_object.setValue(json_email_addresses.toJSONString());
                    prepared_statement.setObject(7, pg_object);
                    prepared_statement.execute();
                    try (ResultSet result_set = prepared_statement.getResultSet()) {

                        if (result_set.next()) {
                            lastUpdateId = result_set.getInt(1);
                            logger.log(Level.INFO, "Id of new email history: " + lastUpdateId);
                        }
                    }
                }
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            }
        }
        return lastUpdateId;
    }

}
