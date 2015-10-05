/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbit.AppConstants;
import com.intbit.ConnectionManager;
import com.mindbodyonline.clients.api._0_5Client.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import mindbody.controller.MindBodyClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;

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
        startProcessing();
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

        processEachRow(rowIdLocationHashMap);
    }

    private void processEachRow(HashMap<Integer, String> rowIdLocationHashMap) {
        JSONParser parser = new JSONParser();
        JSONArray json_email_array = new JSONArray();
        Iterator it = rowIdLocationHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> pair = (Entry<Integer, String>) it.next();
            int[] siteids = new int[]{Integer.parseInt(pair.getValue())};
            MindBodyClass mind_body_class = new MindBodyClass(siteids);
            try {
                long startTime = System.currentTimeMillis();
                logger.log(Level.INFO, "Started working on getting email lists for :" + siteids[0]);
                //String key is the Email List Name. And the value is the clients associated with it.
                HashMap<String, List<Client>> clientIndexesHashmap = mind_body_class.getAllClientIndexes();
                logger.log(Level.INFO, "Ended working on getting email lists for :" + siteids[0] + " Processing time:" + ((System.currentTimeMillis() - startTime) / 1000.0) + " Hashmap:"+clientIndexesHashmap.toString());
                //convert clientIndexesHashmap to JSONObject and update table
                //Email list name : [clientEmailAddresses] . 
             
                System.out.println(clientIndexesHashmap);
                
                Iterator iter = clientIndexesHashmap.entrySet().iterator();
                
                for (Entry<String, List<Client>> entry : clientIndexesHashmap.entrySet()) {
                    JSONObject json_email_object = new JSONObject();
                        String email_list_name = entry.getKey();
                        List email_client = entry.getValue();
                        org.json.JSONArray json_array_emailclient = new org.json.JSONArray(email_client);
                        json_email_object.put(IConstants.kEmailListNameKey, email_list_name);
                        json_email_object.put(IConstants.kEmailAddressesKey, json_array_emailclient);
                        
                        json_email_array.add(json_email_object);
                        
                }
//                JSONObject json_clientIndexes = (JSONObject)parser.parse(AppConstants.GSON.toJson(clientIndexesHashmap));
                JSONObject json_clientIndexes = new JSONObject();
                json_clientIndexes.put(IConstants.kEmailListNameKey, json_email_array);
                updateUserPreferencesTable(pair.getKey(), json_clientIndexes);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

        }
    }

    private void updateUserPreferencesTable(Integer idOfRow, JSONObject userIdWithJSONObject) {

        Iterator it = userIdWithJSONObject.entrySet().iterator();
        String query_string = "";

        while (it.hasNext()) {
            Map.Entry<Integer, JSONObject> pair = (Entry<Integer, JSONObject>) it.next();
            PGobject pg_object = new PGobject();
            query_string = "Update tbl_user_preferences SET mindbody_email_list=? where id=?";
            try (Connection connection = connectionManager.getConnection();
                    PreparedStatement ps = connection.prepareStatement(query_string)) {
                pg_object.setType("json");
                pg_object.setValue(pair.getValue().toJSONString());
                ps.setObject(1, pg_object, Types.OTHER);
                ps.setInt(2, idOfRow);
                ps.executeUpdate();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Mindbody email list processor", ex);
            }
        }
    }
}
