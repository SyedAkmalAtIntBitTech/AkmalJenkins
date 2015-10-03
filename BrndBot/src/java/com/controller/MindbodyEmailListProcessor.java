/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbit.ConnectionManager;
import com.mindbody.source.MindBody;
import com.mindbodyonline.clients.api._0_5Client.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import mindbody.controller.MindBodyClass;
import org.json.simple.JSONObject;

/**
 *
 * @author AR
 */
public class MindbodyEmailListProcessor implements Runnable {

    private static final Logger logger = Logger.getLogger(util.Utility.getClassName(ApplicationContextListener.class));
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public void run() {
        logger.log(Level.INFO, "Scheduler at " + new Date());
        logger.log(Level.INFO, "Started the automation");
    }

    private void startProcessing() {

        HashMap<Integer, String> rowIdLocationHashMap = new HashMap<>();

        String sql = "SELECT id, location"
                + " FROM tbl_user_preferences "
                + " WHERE location IS NOT NULL";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String location = rs.getString("location");
                    int rowId = rs.getInt("id");
                    rowIdLocationHashMap.put(rowId, location);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Mindbody email list processor", ex);
        }

        processForEachRow(rowIdLocationHashMap);
    }

    private void processForEachRow(HashMap<Integer, String> rowIdLocationHashMap) {

        Iterator it = rowIdLocationHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> pair = (Entry<Integer, String>) it.next();
            int[] siteids = new int[]{Integer.parseInt(pair.getValue())};
            MindBodyClass mind_body_class = new MindBodyClass(siteids);
            HashMap<String, List<Client>> clientIndexesHashmap = mind_body_class.getAllClientIndexes();
            //convert clientIndexesHashmap to JSONObject and update table
            HashMap<Integer, JSONObject> userIdWithJSONObject = new HashMap<>();
            updateUserPreferencesTable(userIdWithJSONObject);
        }
    }

    private void updateUserPreferencesTable(HashMap<Integer, JSONObject> userIdWithJSONObject) {

        Iterator it = userIdWithJSONObject.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry<Integer, JSONObject> pair = (Entry<Integer, JSONObject>) it.next();

            String query_string = "UPDATE tbl_user_preferences"
                    + " SET mindbody_email_list='" + pair.getValue().toJSONString() + "'  WHERE id=" + pair.getKey() + "";
            try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query_string)) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Mindbody email list processor", ex);
            }
        }
    }
}
