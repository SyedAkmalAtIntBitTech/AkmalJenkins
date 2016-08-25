/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.AppConstants;
import com.intbit.ConnectionManager;
import com.intbittech.enums.ScheduledEntityType;
import com.intbittech.enums.TemplateStatus;
import com.intbittech.dao.impl.ScheduleDAO;
import com.intbittech.dao.impl.ScheduleSocialPostDAO;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.services.ScheduleActionsService;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleActionsServiceImpl implements ScheduleActionsService {

    private static Logger logger = Logger.getLogger(ScheduleActionsServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getActions(Map<String, Object> requestBodyMap, Integer companyId) throws Exception {
        JSONArray json_array = new JSONArray();

        String type = (String) requestBodyMap.get("type");
        String program_id = (String) requestBodyMap.get("programid");

        if (type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
                json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(companyId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
                json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(companyId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
                json_array = ScheduleDAO.getScheduledActions(companyId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase("social")) {
            JSONArray json_social = new JSONArray();
            json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(companyId, Integer.parseInt(program_id));
            for (int i = 0; i < json_array.size(); i++) {
                json_social.add(json_array.get(i));
            }
            json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(companyId, Integer.parseInt(program_id));
            for (int i = 0; i < json_array.size(); i++) {
                json_social.add(json_array.get(i));
            }
            json_array = json_social;
        }
        return AppConstants.GSON.toJson(json_array);
    }

    @Override
    public Map<String, Integer> scheduleEmail(Map<String, Object> requestBodyMap, Integer companyId) {

        try {

            Double schedule = (Double) requestBodyMap.get("schedule_time");
            //As of now schedule description is not yet mandatory.
            String scheduleDesc = requestBodyMap.containsKey("schedule_desc")
                    ? String.valueOf(requestBodyMap.get("schedule_desc")) : null;
            String marketing_program_id = (String) requestBodyMap.get("program_id");

            //Added by Syed Ilyas 27 Nov 2015 - email body from iframe
            String html_text = "";
            String path = "";
            if (requestBodyMap.get("iframeName").toString().trim() != null) {
                String iframeName = requestBodyMap.get("iframeName").toString().trim();
                path = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + iframeName + ".html";
                File file = new File(path);
                html_text = FileUtils.readFileToString(file, "UTF-8");
            }

            Map<String, Integer> idMap = ScheduleDAO.addToScheduledEmailList(
                    companyId,
                    requestBodyMap.get("email_subject").toString(),
                    requestBodyMap.get("email_preheader").toString(),
                    Integer.parseInt(marketing_program_id),
                    html_text,
                    requestBodyMap.get("from_email_address").toString(),
                    requestBodyMap.get("email_list").toString(),
                    requestBodyMap.get("from_name").toString(),
                    requestBodyMap.get("reply_to_email_address").toString(),
                    requestBodyMap.get("to_email_addresses").toString().split(","),
                    requestBodyMap.get("schedule_title").toString(),
                    scheduleDesc,
                    new Timestamp(schedule.longValue()),
                    TemplateStatus.template_saved.toString(),
                    requestBodyMap.get("html_body").toString()
            );

            if (!path.equals("")) {
                File IframeDelete = new File(path);
                IframeDelete.delete();
            }
            return idMap;
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return null;
    }

    @Override
    public Map<String, Integer> scheduleEmailActions(Map<String, Object> requestBodyMap, Integer companyId) {

        try {
            String schedule_id = (String) requestBodyMap.get("schedule_id");
            String scheduleDesc = requestBodyMap.containsKey("schedule_desc")
                    ? String.valueOf(requestBodyMap.get("schedule_desc")) : null;

            //Added by Syed Ilyas 27 Nov 2015 - email body from iframe
            String html_text = "";
            String path = "";
            if (requestBodyMap.get("iframeName").toString().trim() != null) {
                String iframeName = requestBodyMap.get("iframeName").toString().trim();
                path = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + iframeName + ".html";
                File file = new File(path);
                html_text = FileUtils.readFileToString(file, "UTF-8");
            }

            Map<String, Integer> idMap = ScheduleDAO.updatetoScheduledEmailList(
                    companyId,
                    Integer.parseInt(schedule_id),
                    requestBodyMap.get("email_subject").toString(),
                    requestBodyMap.get("email_preheader").toString(),
                    html_text,
                    requestBodyMap.get("from_email_address").toString(),
                    requestBodyMap.get("email_list").toString(),
                    requestBodyMap.get("from_name").toString(),
                    requestBodyMap.get("reply_to_email_address").toString(),
                    requestBodyMap.get("to_email_addresses").toString().split(","),
                    scheduleDesc,
                    TemplateStatus.template_saved.toString(),
                    requestBodyMap.get("html_body").toString()
            );

            if (!path.equals("")) {
                File IframeDelete = new File(path);
                IframeDelete.delete();
            }
            return idMap;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Error while inserting record");
        }
    }

    @Override
    public List<Map<String, Integer>> scheduleSocialPostActions(Map<String, Object> requestBodyMap, Integer companyId) {
        List<Map<String, Integer>> daoResponseList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try {
                
                    String metadataString = requestBodyMap.get("metadata").toString();
                    String schedule_id = (String) requestBodyMap.get("schedule_id");
                    String image_type = (String) requestBodyMap.get("image_type");
                    Map<String, Integer> daoResponse = ScheduleSocialPostDAO.updateActionsToScheduleSocialPost(
                            companyId,
                            Integer.parseInt(schedule_id),
                            requestBodyMap.get("image_name").toString(),
                            AppConstants.GSON.fromJson(metadataString, Map.class),
                            requestBodyMap.get("type").toString(),
                            TemplateStatus.template_saved.toString(),
                            image_type,
                            conn);
                    daoResponseList.add(daoResponse);
                    
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Unable to insert records");
        }
        return daoResponseList;
    }

    @Override
    public List<Map<String, Integer>> scheduleSocialPost(Map<String, Object> requestBodyMap, Integer companyId) {
        List<Map<String, Integer>> daoResponseList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try {
//                for (Map<String, Object> requestBodyMap : requestBodyList) {
                    Double schedule = (Double) requestBodyMap.get("schedule_time");

                    Timestamp scheduleTimeStamp = new Timestamp(schedule.longValue());
                    String metadataString = requestBodyMap.get("metadata").toString();
                    String marketing_program_id = (String) requestBodyMap.get("program_id");
                    //As of now schedule description is not yet mandatory.
                    String scheduleDesc = requestBodyMap.containsKey("schedule_desc")
                            ? String.valueOf(requestBodyMap.get("schedule_desc")) : null;
                    String marketingType = "0";
                    String imageType = requestBodyMap.get("image_type").toString();
                    Map<String, Integer> daoResponse = ScheduleSocialPostDAO.addToScheduleSocialPost(
                            companyId,
                            requestBodyMap.get("image_name").toString(),
                            Integer.parseInt(marketing_program_id),
                            AppConstants.GSON.fromJson(metadataString, Map.class),
                            requestBodyMap.get("type").toString(),
                            requestBodyMap.get("schedule_title").toString(),
                            scheduleDesc,
                            scheduleTimeStamp,
                            TemplateStatus.template_saved.toString(),
                            imageType,
                            conn);
                    daoResponseList.add(daoResponse);
//                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Unable to insert records");
        }
        return daoResponseList;
    }
}
