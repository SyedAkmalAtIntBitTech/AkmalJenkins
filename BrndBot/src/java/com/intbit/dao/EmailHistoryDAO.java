/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.dao;

import com.intbit.ConnectionManager;
import email.mandrill.MessageResponse;
import email.mandrill.MessageResponses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed
 */
public class EmailHistoryDAO {
    private static final Logger logger = Logger.getLogger(EmailHistoryDAO.class.getName());
    
    public static void insertMandrillEmailId(MessageResponses mandrillResponse,
            int emailHistoryId){
        String insertSql = "INSERT INTO tbl_email_mandrill_history "
                + " (email_history_id, to_email, mandrill_email_id, status, reject_reason) VALUES "
                + " (?, ?, ?, ?, ?) ";
        ConnectionManager connectionManager = ConnectionManager.getInstance();
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
}
