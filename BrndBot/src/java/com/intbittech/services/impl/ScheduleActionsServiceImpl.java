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
import com.intbittech.enums.ActivityStatus;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.marketing.service.CompanyMarketingProgramService;
import com.intbittech.marketing.service.ScheduledEntityListService;
import com.intbittech.model.CompanyMarketingProgram;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.modelmappers.ActivityLogDetails;
import com.intbittech.services.ActivityLogService;
import com.intbittech.services.ScheduleActionsService;
import com.intbittech.utility.StringUtility;
import com.intbittech.utility.Utility;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleActionsServiceImpl implements ScheduleActionsService {

    private static Logger logger = Logger.getLogger(ScheduleActionsServiceImpl.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ActivityLogService activityLogService;
    @Autowired
    private ScheduledEntityListService scheduledEntityListService;
    @Autowired
    private CompanyMarketingProgramService companyMarketingProgramService;

    @Override
    public String getActions(Map<String, Object> requestBodyMap, Integer companyId) throws Exception {
        JSONArray json_array = new JSONArray();

        String type = (String) requestBodyMap.get("type");
        String program_id = (String) requestBodyMap.get("programid");

        if (type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
                //  json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(companyId, Integer.parseInt(program_id));
                json_array = ScheduleSocialPostDAO.getScheduledActionsfacebookWithDate(companyId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
//                json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(companyId, Integer.parseInt(program_id));
                json_array = ScheduleSocialPostDAO.getScheduledActionstwitterWithDate(companyId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
//                json_array = ScheduleDAO.getScheduledActions(companyId, Integer.parseInt(program_id));
                json_array = ScheduleDAO.getScheduledActionsEmailWithDate(companyId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase("social")) {
            JSONArray json_social = new JSONArray();
//            json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(companyId, Integer.parseInt(program_id));
            json_array = ScheduleSocialPostDAO.getScheduledActionsfacebookWithDate(companyId, Integer.parseInt(program_id));
            for (int i = 0; i < json_array.size(); i++) {
                json_social.add(json_array.get(i));
            }
//            json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(companyId, Integer.parseInt(program_id));
            json_array = ScheduleSocialPostDAO.getScheduledActionstwitterWithDate(companyId, Integer.parseInt(program_id));
            for (int i = 0; i < json_array.size(); i++) {
                json_social.add(json_array.get(i));
            }
            json_array = json_social;
        }
        return AppConstants.GSON.toJson(json_array);
    }

    @Override
    public Map<String, Integer> scheduleEmail(Map<String, Object> requestBodyMap, Integer companyId, Integer createdBy) {

        try {

//            Double schedule = (Double) requestBodyMap.get("schedule_time");
            //As of now schedule description is not yet mandatory.
            String scheduleDesc = requestBodyMap.containsKey("schedule_desc")
                    ? String.valueOf(requestBodyMap.get("schedule_desc")) : null;
            String marketing_program_id = (String) requestBodyMap.get("program_id");
            String userAssignedToString = requestBodyMap.get("userAssignedTo").toString();
            if (StringUtility.isEmpty(userAssignedToString)) {
                userAssignedToString = "0.0";
            }
            Double TempUserAssignToId = new Double(userAssignedToString.trim());
            Integer userAssignToId = TempUserAssignToId.intValue();

            //Added by Syed Ilyas 27 Nov 2015 - email body from iframe
            String html_text = "";
            String path = "";
            String templateStatus = "";
            if (requestBodyMap.get("iframeName").toString().trim() != null) {
                String iframeName = requestBodyMap.get("iframeName").toString().trim();
                path = AppConstants.BASE_HTML_TEMPLATE_UPLOAD_PATH + File.separator + iframeName + ".html";
                File file = new File(path);
                html_text = FileUtils.readFileToString(file, "UTF-8");
            }
            boolean autoApproved = (boolean)requestBodyMap.get("autoApproved");
            if (autoApproved){
                templateStatus = TemplateStatus.approved.toString();
            }else {
                templateStatus = TemplateStatus.template_saved.toString();
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
                    //                    requestBodyMap.get("to_email_addresses").toString().split(","),
                    requestBodyMap.get("schedule_title").toString(),
                    scheduleDesc,
                    new Timestamp(Double.valueOf(requestBodyMap.get("schedule_time").toString()).longValue()),
                    templateStatus,
                    requestBodyMap.get("html_body").toString(), createdBy, userAssignToId
            );
            Integer scheduleEntityId = idMap.get("schedule_entity_id");
                ActivityLogDetails activityLogDetails = new ActivityLogDetails();
                activityLogDetails.setActivityId(ActivityStatus.ACTIVITY_CREATED_ACTION_ID.getId());
                activityLogDetails.setScheduledEntityId(scheduleEntityId);
                activityLogDetails.setCreatedBy(createdBy);
                activityLogDetails.setCompanyId(companyId);
                activityLogDetails.setActionTitle(requestBodyMap.get("schedule_title").toString());
                
                activityLogService.saveActivityLog(activityLogDetails);
                ActivityLogDetails activityLogDetailsObject = new ActivityLogDetails();
                activityLogDetailsObject.setActivityId(ActivityStatus.ACTIVITY_ADDED_TEMPLATE_ID.getId());
                activityLogDetailsObject.setScheduledEntityId(scheduleEntityId);
                activityLogDetailsObject.setCreatedBy(createdBy);
                activityLogDetailsObject.setCompanyId(companyId);
                activityLogDetailsObject.setActionTitle(requestBodyMap.get("schedule_title").toString());
                activityLogService.saveActivityLog(activityLogDetailsObject);
                
                ScheduledEntityList scheduledEntityList = scheduledEntityListService.getById(scheduleEntityId);
                ActivityLogDetails activityLog = new ActivityLogDetails();
                activityLog.setActivityId(ActivityStatus.ACTIVITY_ASSIGNED_TO_ID.getId());
                activityLog.setScheduledEntityId(scheduleEntityId);
                if (scheduledEntityList.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId() == 0){
                    activityLog.setActionDate(new Timestamp(Double.valueOf(requestBodyMap.get("schedule_time").toString()).longValue()));
                }else {
                    CompanyMarketingProgram userMarketingProgram = companyMarketingProgramService.getById(scheduledEntityList.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId());
                    Date eventDate = userMarketingProgram.getDateEvent();
                    Integer scheduleDays = scheduledEntityList.getDays();
                    Date scheduleDate = scheduledEntityList.getScheduleTime();
                    eventDate.setHours(scheduleDate.getHours());
                    eventDate.setMinutes(scheduleDate.getMinutes());
                    eventDate.setSeconds(scheduleDate.getSeconds());
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(eventDate);
                    cal.add(Calendar.DAY_OF_MONTH, -scheduleDays);
                    activityLog.setActionDate(new Timestamp(cal.getTimeInMillis()));
                }
                activityLog.setActionStatus(TemplateStatus.valueOf(scheduledEntityList.getStatus()).getDisplayName());
                activityLog.setActionTitle(scheduledEntityList.getScheduleTitle());
                activityLog.setActionType(scheduledEntityList.getEntityType());
                activityLog.setProgramName(scheduledEntityList.getFkCompanyMarketingProgramId().getCompanyMarketingProgramName());
                activityLog.setCreatedBy(createdBy);
                activityLog.setCompanyId(companyId);
                activityLog.setAssignedTo(userAssignToId);
                activityLogService.saveActivityLog(activityLog);
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
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
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
                    scheduleDesc,
                    TemplateStatus.template_saved.toString(),
                    requestBodyMap.get("html_body").toString()
            );
            ActivityLogDetails activityLogDetails = new ActivityLogDetails();
            activityLogDetails.setActivityId(ActivityStatus.ACTIVITY_ADDED_TEMPLATE_ID.getId());
            activityLogDetails.setScheduledEntityId(Integer.parseInt(schedule_id));
            activityLogDetails.setCreatedBy(userCompanyIds.getUserId());
            activityLogDetails.setActionTitle(requestBodyMap.get("schedule_title").toString());
            activityLogDetails.setCompanyId(companyId);
            activityLogService.saveActivityLog(activityLogDetails);
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
                UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
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
                ActivityLogDetails activityLogDetails = new ActivityLogDetails();
                activityLogDetails.setActivityId(ActivityStatus.ACTIVITY_ADDED_TEMPLATE_ID.getId());
                activityLogDetails.setScheduledEntityId(Integer.parseInt(schedule_id));
                activityLogDetails.setCreatedBy(userCompanyIds.getUserId());
                activityLogDetails.setActionTitle(requestBodyMap.get("schedule_title").toString());
                activityLogService.saveActivityLog(activityLogDetails);
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
    public List<Map<String, Integer>> scheduleSocialPost(Map<String, Object> requestBodyMap, Integer companyId, Integer createdBy) {
        List<Map<String, Integer>> daoResponseList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try {

                Double schedule = (Double) requestBodyMap.get("schedule_time");
                String metadataString = requestBodyMap.get("metadata").toString();
                String marketing_program_id = (String) requestBodyMap.get("program_id");
                //As of now schedule description is not yet mandatory.
                String scheduleDesc = requestBodyMap.containsKey("schedule_desc")
                        ? String.valueOf(requestBodyMap.get("schedule_desc")) : null;
                String marketingType = "0";

                String imageType = requestBodyMap.get("image_type").toString();
                Double TempUserAssignToId = new Double(requestBodyMap.get("userAssignedTo").toString().trim());
                Integer userAssignToId = TempUserAssignToId.intValue();
                Map<String, Integer> daoResponse = ScheduleSocialPostDAO.addToScheduleSocialPost(
                        companyId,
                        requestBodyMap.get("image_name").toString(),
                        Integer.parseInt(marketing_program_id),
                        AppConstants.GSON.fromJson(metadataString, Map.class),
                        requestBodyMap.get("type").toString(),
                        requestBodyMap.get("schedule_title").toString(),
                        scheduleDesc,
                         new Timestamp(Double.valueOf(requestBodyMap.get("schedule_time").toString()).longValue()),
                        TemplateStatus.template_saved.toString(),
                        imageType, createdBy, userAssignToId,
                        conn);
                daoResponseList.add(daoResponse);
                conn.commit();
                Integer scheduleEntityId = daoResponse.get("schedule_entity_id");
                ActivityLogDetails activityLogDetails = new ActivityLogDetails();
                activityLogDetails.setActivityId(ActivityStatus.ACTIVITY_CREATED_ACTION_ID.getId());
                activityLogDetails.setScheduledEntityId(scheduleEntityId);
                activityLogDetails.setCreatedBy(createdBy);
                activityLogDetails.setCompanyId(companyId);
                activityLogDetails.setActionTitle(requestBodyMap.get("schedule_title").toString());
                activityLogService.saveActivityLog(activityLogDetails);
                ActivityLogDetails activityLogDetailsObject = new ActivityLogDetails();
                activityLogDetailsObject.setActivityId(ActivityStatus.ACTIVITY_ADDED_TEMPLATE_ID.getId());
                activityLogDetailsObject.setScheduledEntityId(scheduleEntityId);
                activityLogDetailsObject.setCreatedBy(createdBy);
                activityLogDetailsObject.setCompanyId(companyId);
                activityLogDetailsObject.setActionTitle(requestBodyMap.get("schedule_title").toString());
                activityLogService.saveActivityLog(activityLogDetailsObject);
                ScheduledEntityList scheduledEntityList = scheduledEntityListService.getById(scheduleEntityId);
                ActivityLogDetails activityLog = new ActivityLogDetails();
                activityLog.setActivityId(ActivityStatus.ACTIVITY_ASSIGNED_TO_ID.getId());
                activityLog.setScheduledEntityId(scheduleEntityId);
                activityLog.setProgramName(scheduledEntityList.getFkCompanyMarketingProgramId().getCompanyMarketingProgramName());

                if (scheduledEntityList.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId() == 0){
                    activityLog.setActionDate(new Timestamp(Double.valueOf(requestBodyMap.get("schedule_time").toString()).longValue()));
                }else {
                    CompanyMarketingProgram userMarketingProgram = companyMarketingProgramService.getById(scheduledEntityList.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId());
                    Date eventDate = userMarketingProgram.getDateEvent();
                    Integer scheduleDays = scheduledEntityList.getDays();
                    Date scheduleDate = scheduledEntityList.getScheduleTime();
                    eventDate.setHours(scheduleDate.getHours());
                    eventDate.setMinutes(scheduleDate.getMinutes());
                    eventDate.setSeconds(scheduleDate.getSeconds());
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(eventDate);
                    cal.add(Calendar.DAY_OF_MONTH, -scheduleDays);
                    activityLog.setActionDate(new Timestamp(cal.getTimeInMillis()));
                }
                
//                activityLog.setActionDate(new Timestamp(scheduledEntityList.getScheduleTime().getTime()));
                activityLog.setActionStatus(TemplateStatus.valueOf(scheduledEntityList.getStatus()).getDisplayName());
                activityLog.setActionTitle(scheduledEntityList.getScheduleTitle());
                activityLog.setActionType(scheduledEntityList.getEntityType());
                activityLog.setCreatedBy(createdBy);
                activityLog.setAssignedTo(userAssignToId);
                activityLog.setCompanyId(companyId);
                activityLogService.saveActivityLog(activityLog);
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
