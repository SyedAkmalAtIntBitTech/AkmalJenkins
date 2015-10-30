/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbit.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.util.PGobject;

/**
 *
 * @author AR
 */
class TwitterPostProcessor implements Runnable {

    private static final Logger logger = Logger.getLogger(util.Utility.getClassName(TwitterPostProcessor.class));
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private volatile boolean running = true;

    //This is added just in case the application server itself shutdowns. That time the contextDestroyed is not called.
    void startThread() {
        running = true;
    }

    public void terminateThread() {
        running = false;
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        while (running) {
            try {
                logger.log(Level.INFO, "Scheduler at " + new Date());
                logger.log(Level.INFO, "Started the automation");
                startProcessing();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Interupted the thread of Email list");
            }
        }
    }

    private void startProcessing() {

        HashMap<Integer, String> rowIdPreferencesHashMap = new HashMap<>();

        String sql = "SELECT id, user_preferences"
                + " FROM tbl_user_preferences "
                + " WHERE user_preferences IS NOT NULL";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String user_preferences = rs.getString("user_preferences");
                    int rowId = rs.getInt("id");
                    rowIdPreferencesHashMap.put(rowId, user_preferences);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Mindbody email list processor", ex);
        }

        processEachRow(rowIdPreferencesHashMap);
    }

    private void processEachRow(HashMap<Integer, String> rowIdPreferencesHashMap) {

        Iterator it = rowIdPreferencesHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> pair = (Map.Entry<Integer, String>) it.next();
            try {
                long startTime = System.currentTimeMillis();
                logger.log(Level.INFO, "Started working on email lists for :" + pair.getKey());

                JSONObject userPreferencesJSONObject = (JSONObject) new JSONParser().parse(pair.getValue());
                JSONArray emailListsArray = (JSONArray) userPreferencesJSONObject.get("emailLists");

                if (emailListsArray != null && emailListsArray.size() > 0) {
                    for (Object emailListsArrayItem : emailListsArray) {
                        JSONObject emailListJSONObject = (JSONObject) emailListsArrayItem;
                        Object emailAddresses = emailListJSONObject.get("emailAddresses");
                        JSONArray emailAddressJSONArray = new JSONArray();
                        if (emailAddresses instanceof String) {
                            String emailAddressWithCommaArray[] = emailAddresses.toString().split(",");
                            for (String emailAddress : emailAddressWithCommaArray) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("emailaddress", emailAddress);
//                                jsonObject.put("addeddate", emailAddresses)
                                emailAddressJSONArray.add(new JSONParser().parse(emailAddress));
                            }
                        } else {
                            emailAddressJSONArray = (JSONArray) new JSONParser().parse(emailAddresses.toString());
                        }

                        if (emailAddressJSONArray != null && emailAddressJSONArray.size() > 0) {
                            for (Object emailAddressItem : emailAddressJSONArray) {
                                JSONObject emailAddressJSONItem = (JSONObject) emailAddressItem;
                                
                            }
                        }
                    }

                    updateUserPreferencesTable(pair.getKey(), userPreferencesJSONObject);
                }
//                logger.log(Level.INFO, "Ended working on getting email lists for :" + siteids[0] + " Processing time:" + ((System.currentTimeMillis() - startTime) / 1000.0) + " Email Indexes Present:" + clientIndexesHashmap.keySet().size());

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Mindbody email list processor", e);
            }

        }
    }

    private void updateUserPreferencesTable(Integer idOfRow, JSONObject userIdWithJSONObject) {

        String query_string = "";

        PGobject pg_object = new PGobject();
        query_string = "Update tbl_user_preferences SET tbl_user_preferences=? where id=?";
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

        query_string = "Update tbl_user_preferences SET tbl_user_preferences=? where id=?";
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
