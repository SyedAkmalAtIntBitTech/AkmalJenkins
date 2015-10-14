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
import java.util.ArrayList;
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
            HashMap<String, List<Client>> clientIndexesHashmap = new HashMap<String, List<Client>>();
            try {
                long startTime = System.currentTimeMillis();
                logger.log(Level.INFO, "Started working on getting email lists for :" + siteids[0]);
                //String key is the Email List Name. And the value is the clients associated with it.
                try {
                    clientIndexesHashmap = mind_body_class.getAllClientIndexes();
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Mindbody email list processor", ex);
                }
                logger.log(Level.INFO, "Ended working on getting email lists for :" + siteids[0] + " Processing time:" + ((System.currentTimeMillis() - startTime) / 1000.0) + " Email Indexes Present:" + clientIndexesHashmap.keySet().size());
                //convert clientIndexesHashmap to JSONObject and update table
                //Email list name : [clientEmailAddresses].

                for (String email_list_name : clientIndexesHashmap.keySet()) {
                    org.json.JSONObject json_email_object = new org.json.JSONObject();
                    List email_client = (List<Client>) clientIndexesHashmap.get(email_list_name);
                    org.json.JSONArray json_array_emailclient = new org.json.JSONArray();

                    for (int i = 0; i < email_client.size(); i++) {
                        Client email_object1 = (Client) email_client.get(i);
                        String email_id = email_object1.getEmail();
                        json_array_emailclient.put(email_id);
                    }
                    json_email_object.put(IConstants.kEmailListNameKey, "Mindbody - " + email_list_name);
                    json_email_object.put(IConstants.kEmailAddressesKey, json_array_emailclient);
                    json_email_array.add(json_email_object);

                }
                if (json_email_array.size() >= 0 ){
                    JSONObject json_clientIndexes = new JSONObject();
                    json_clientIndexes.put(IConstants.kEmailAddressUserPreferenceKey, json_email_array);

                    updateUserPreferencesTable(pair.getKey(), json_clientIndexes);
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Mindbody email list processor", e);
            }

        }
    }

    private void updateUserPreferencesTable(Integer idOfRow, JSONObject userIdWithJSONObject) {

        String query_string = "";

        PGobject pg_object = new PGobject();
        query_string = "Update tbl_user_preferences SET mindbody_email_list=? where id=?";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query_string)) {
            pg_object.setType("json");
            pg_object.setValue(null);
            ps.setObject(1, pg_object, Types.OTHER);
            ps.setInt(2, idOfRow);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Mindbody email list processor", ex);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Mindbody email list processor", e);
        }

        query_string = "Update tbl_user_preferences SET mindbody_email_list=? where id=?";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query_string)) {
            pg_object.setType("json");
            pg_object.setValue(userIdWithJSONObject.toString());
            ps.setObject(1, pg_object, Types.OTHER);
            ps.setInt(2, idOfRow);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Mindbody email list processor", ex);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Mindbody email list processor", e);
        }

    }
}
