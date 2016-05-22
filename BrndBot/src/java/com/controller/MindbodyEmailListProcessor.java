/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.intbit.ConnectionManager;
import com.intbittech.component.SpringContextBridge;
import com.intbittech.mindbody.MindBodyClass;
import com.intbittech.model.EmailInfo;
import com.intbittech.services.EmailListService;
import com.mindbodyonline.clients.api._0_5Client.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

/**
 *
 * @author AR
 */
public class MindbodyEmailListProcessor implements Runnable {

    private static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(MindbodyEmailListProcessor.class));
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private volatile boolean running = true;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
        if (running) {
            try {
                logger.log(Level.INFO, "Scheduler at " + new Date());
                logger.log(Level.INFO, "Started the automation");
                startProcessing();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Interupted the thread of Mindbody email list");
            }
        }
    }

    public void startProcessing() throws ParseException {

        HashMap<Integer, Integer> rowIdLocationHashMap = new HashMap<>();

        String sql = "SELECT company_location, email_list,fk_company_id"
                + " FROM company_preferences "
                + " WHERE company_location IS NOT NULL";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int location = rs.getInt("company_location");
                    int compnayId = rs.getInt("fk_company_id");
                    rowIdLocationHashMap.put(compnayId, location);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Mindbody email list processor", ex);
        }

        processEachRow(rowIdLocationHashMap);
    }

    public void processEachRow(HashMap<Integer, Integer> rowIdLocationHashMap) throws ParseException {
        JSONArray json_email_array = new JSONArray();
        Iterator it = rowIdLocationHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = (Entry<Integer, Integer>) it.next();
            int[] siteids = new int[]{pair.getValue()};
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

                        String first_name = email_object1.getFirstName();
                        String last_name = email_object1.getLastName();
                        EmailInfo email_info = new EmailInfo(email_id, first_name, last_name, dateFormat.format(new Date()));
                        json_array_emailclient.put(email_info.getEmailInfoJSONObject());
                    }
                    
                    json_email_object.put(IConstants.kEmailListNameKey, "Mindbody - " + email_list_name);
                    json_email_object.put(IConstants.kEmailAddressesKey, json_array_emailclient);
                    json_email_array.add(json_email_object);

                }

                if (json_email_array.size() >= 0) {
                    updateUserPreferencesTable(pair.getKey(), json_email_array);
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Mindbody email list processor", e);
            }
        }
    }

    private void updateUserPreferencesTable(Integer companyId, JSONArray mindbodyEmails) throws Exception {
        EmailListService emailListService = SpringContextBridge.services().getEmailListService();
        Map<String, Object> map = new HashMap<>();
        map.put("update", "updateAllCompanyMindbodyListForStudioId");
        map.put(IConstants.kEmailListMindbodyKey, mindbodyEmails);
        emailListService.setEmailList(map, companyId);;
    }
}
