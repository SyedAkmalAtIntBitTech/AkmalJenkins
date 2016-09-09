/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.marketing.controller;

import com.intbittech.utility.IConstants;
import com.intbittech.AppConstants;
import com.intbittech.enums.TemplateStatus;
import com.intbittech.marketing.service.CompanyMarketingProgramService;
import com.intbittech.marketing.service.ScheduledEntityListService;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyMarketingProgram;
import com.intbittech.model.MarketingAction;
import com.intbittech.model.MarketingProgram;
import com.intbittech.model.RecurringEmailTemplate;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.UserProfile;
import com.intbittech.services.MarketingActionService;
import com.intbittech.services.RecurringEmailTemplateService;
import com.intbittech.utility.UserSessionUtil;
import com.intbittech.utility.Utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ajit
 */
@Controller
public class CompanyMarketingProgramController {

    private Logger logger = Logger.getLogger(CompanyMarketingProgramController.class);

    @Autowired
    private CompanyMarketingProgramService companyMarketingProgramService;
    @Autowired
    private ScheduledEntityListService scheduledEntityListService;
    @Autowired
    private MarketingActionService marketingActionService;
    @Autowired
    private RecurringEmailTemplateService recurringEmailTemplateService;

    @RequestMapping(value = "/setMarketingProgram", method = RequestMethod.POST)
    public @ResponseBody
    String setUserMarketingProgram(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {
            SimpleDateFormat formatter = null;
            Date tillDate = null;
            CompanyMarketingProgram addCompanyMarketingProgram = new CompanyMarketingProgram();
            MarketingProgram marketingProgram = new MarketingProgram();
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            Company company = new Company();
            company.setCompanyId(userCompanyIds.getCompanyId());
            
            addCompanyMarketingProgram.setCompanyMarketingProgramName(requestBodyMap.get("program_name").toString());
            String target = requestBodyMap.get("program_date_time").toString();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date eventDate = df.parse(target);
            String marketingCategoryId1 = (String) requestBodyMap.get("marketing_category_id");
            Double marketingCategoryId = Double.parseDouble(marketingCategoryId1);
            String marketingProgramId1 = (String) requestBodyMap.get("marketing_program_id");
            Double marketingProgramId = Double.parseDouble(marketingProgramId1);
            marketingProgram.setMarketingProgramId(marketingProgramId.intValue());
            addCompanyMarketingProgram.setCompanyMarketingProgramId(0);
            addCompanyMarketingProgram.setDateEvent(eventDate);
            addCompanyMarketingProgram.setCreatedDate(new Date());
            addCompanyMarketingProgram.setFkCompanyId(company);
            addCompanyMarketingProgram.setStatus("Open");
            addCompanyMarketingProgram.setUrl(requestBodyMap.get("program_url").toString());
            addCompanyMarketingProgram.setLinkName(requestBodyMap.get("program_url_name").toString());
            addCompanyMarketingProgram.setFkMarketingProgramId(marketingProgram);
            Integer companyMarketingProgramId = companyMarketingProgramService.save(addCompanyMarketingProgram);
            String link = companyMarketingProgramId.toString();

            MarketingAction marketingAction = marketingActionService.getByMarketingActionByProgramId(marketingProgramId.intValue());

            if (marketingAction != null) {
                String jsonString = marketingAction.getJsonTemplate();
                JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
                org.json.simple.JSONArray jSONArray = (org.json.simple.JSONArray) json.get(IConstants.kMarketingActionsKey);
                for (Integer i = 0; i < jSONArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jSONArray.get(i);
                    String tillDateString = jsonObject.get("tilldate").toString();

                    if (!(tillDateString.equals(""))) {
                        formatter = new SimpleDateFormat("yyyy-MM-DD");
                        tillDate = formatter.parse(tillDateString);
                    }

                    String timeString = jsonObject.get("time").toString();
                    SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
                    Date time = formatterTime.parse(timeString);

                    ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
                    scheduledEntityList.setEntityType(jsonObject.get("type").toString());
                    scheduledEntityList.setIsRecurring(Boolean.parseBoolean(jsonObject.get("is_recurring").toString()));
                    scheduledEntityList.setScheduleDesc(jsonObject.get("description").toString());
                    scheduledEntityList.setEntityId(0);
                    scheduledEntityList.setScheduleTime(time);
                    scheduledEntityList.setTillDate(tillDate);
                    scheduledEntityList.setDays(Integer.parseInt(jsonObject.get("days").toString()));
                    scheduledEntityList.setStatus(TemplateStatus.no_template.toString());
                    scheduledEntityList.setScheduleTitle(jsonObject.get("title").toString());
                    scheduledEntityList.setFkCompanyId(company);
                    CompanyMarketingProgram companyMarketingProgram = new CompanyMarketingProgram();
                    companyMarketingProgram.setCompanyMarketingProgramId(companyMarketingProgramId);
                    scheduledEntityList.setFkCompanyMarketingProgramId(companyMarketingProgram);
                    scheduledEntityListService.save(scheduledEntityList);
                }
            }
            return link;
        } catch (Exception ex) {
            logger.error(ex);
        }
        return "false";
    }

    @RequestMapping(value = "/listAllMarketingProgram", method = RequestMethod.GET)
    public @ResponseBody
    String listAllUserMarketingProgram(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("programType") String programType, @RequestParam("companyId") Integer companyId) throws Throwable {
        List<CompanyMarketingProgram> companyMarketingProgramList = companyMarketingProgramService.getAllCompanyMarketingProgramByType(companyId, programType);
        JSONObject jsonObject = new JSONObject();
        JSONArray json_array = new JSONArray();
        if(companyMarketingProgramList !=null)
        {
        for (CompanyMarketingProgram companyMarketingProgramObject : companyMarketingProgramList) {
            JSONObject json_obj = new JSONObject();
            json_obj.put("id", companyMarketingProgramObject.getCompanyMarketingProgramId());
            json_obj.put("program_name", companyMarketingProgramObject.getCompanyMarketingProgramName());
            Date create_date = companyMarketingProgramObject.getCreatedDate();
            Date end_date = companyMarketingProgramObject.getDateEvent();
            Long create_date_in = create_date.getTime();
            Long end_date_in = end_date.getTime();
            json_obj.put("start_date", create_date_in);
            json_obj.put("end_date", end_date_in);
            if ((programType.equalsIgnoreCase("Open")) || (programType.equalsIgnoreCase("Closed"))) {
                Integer noofrecords = scheduledEntityListService.getCurrentRecords(companyMarketingProgramObject.getCompanyMarketingProgramId());
                json_obj.put("noofpostleft", noofrecords);
            }
            json_array.put(json_obj);

        }
        }
        jsonObject.put("programs", json_array);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/alluserMarketingProgramForDisplay", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUserMarketingProgramDetails(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("program_id") Integer userProgram_id) {

        Boolean actionStatus;
        Boolean postDateStatus;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {

            List<ScheduledEntityList> scheduledEntityList = scheduledEntityListService.getScheduledEntityListIdForEmailType(userProgram_id, Boolean.TRUE);
            JSONArray scheduledEmailJsonArray = new JSONArray();
            for (ScheduledEntityList scheduledEntityListObject : scheduledEntityList) {
                JSONObject jSONObject = new JSONObject();

                Date eventDate = scheduledEntityListObject.getFkCompanyMarketingProgramId().getDateEvent();
                String dateString = formatter.format(eventDate);
                Date todayDate = Calendar.getInstance().getTime();
                String eventDateString = formatter.format(todayDate);
                if (dateString.equalsIgnoreCase(eventDateString)) {
                    actionStatus = true;
                } else {
                    actionStatus = false;
                }
                Integer days = scheduledEntityListObject.getDays();
                Calendar cal = Calendar.getInstance();
                cal.setTime(eventDate);
                cal.add(Calendar.DAY_OF_MONTH, -days);
         
                postDateStatus = true;
                if (scheduledEntityListObject.getFkRecurringEmailId() != null) {
                    RecurringEmailTemplate recurringEmailTemplate = recurringEmailTemplateService.getRecurringEmailTemplateById(scheduledEntityListObject.getFkRecurringEmailId().getRecurringEmailTemplateId());
                    jSONObject.put("emailRecurringTemplateName", recurringEmailTemplate.getRecurringEmailTemplateId());
                } else {
                    jSONObject.put("emailRecurringTemplateName", "no recurring template");
                }

                jSONObject.put("scheduledEntityListId", scheduledEntityListObject.getScheduledEntityListId());
                jSONObject.put("dateTime", scheduledEntityListObject.getScheduleTime().getTime());
                jSONObject.put("programTemplateName", scheduledEntityListObject.getScheduleTitle());
                jSONObject.put("days", scheduledEntityListObject.getDays());
                jSONObject.put("tillDate", "");
                int d = scheduledEntityListObject.getDays();
                jSONObject.put("description", scheduledEntityListObject.getScheduleDesc());
                jSONObject.put("postDateStatus", postDateStatus);
                jSONObject.put("status", TemplateStatus.valueOf(scheduledEntityListObject.getStatus()).getDisplayName());
                jSONObject.put("assignedToId", scheduledEntityListObject.getAssignedTo().getUserId());
                jSONObject.put("assignedFirstName", scheduledEntityListObject.getAssignedTo().getFirstName());
                jSONObject.put("assignedLastName", scheduledEntityListObject.getAssignedTo().getLastName());
                scheduledEmailJsonArray.put(jSONObject);
            }
            List<ScheduledEntityList> scheduledEmailListForRecurring = scheduledEntityListService.getScheduledEntityListIdForEmailType(userProgram_id, Boolean.FALSE);
            JSONArray scheduledEmailAndSocailPostJsonForRecurringArray = new JSONArray();
            for (ScheduledEntityList scheduledEntityListObject : scheduledEmailListForRecurring) {
                CompanyMarketingProgram userMarketingProgram = companyMarketingProgramService.getById(scheduledEntityListObject.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId());
                Date eventDate = userMarketingProgram.getDateEvent();
                String dateString = formatter.format(eventDate);
                Date todayDate = Calendar.getInstance().getTime();
                String eventDateString = formatter.format(todayDate);
                if (dateString.equalsIgnoreCase(eventDateString)) {
                    actionStatus = true;
                } else {
                    actionStatus = false;
                }
                Integer days = scheduledEntityListObject.getDays();
                Calendar cal = Calendar.getInstance();
                cal.setTime(eventDate);
                cal.add(Calendar.DAY_OF_MONTH, -days);
        
                postDateStatus = true;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("scheduledEntityListId", scheduledEntityListObject.getScheduledEntityListId());
                jSONObject.put("eventDate", eventDate.getTime());
                jSONObject.put("programTemplateName", scheduledEntityListObject.getScheduleTitle());
                jSONObject.put("status", TemplateStatus.valueOf(scheduledEntityListObject.getStatus()).getDisplayName());
                jSONObject.put("description", scheduledEntityListObject.getScheduleDesc());
                jSONObject.put("actionStatus", actionStatus);
                jSONObject.put("days", scheduledEntityListObject.getDays());
                int d1 = scheduledEntityListObject.getDays();
                jSONObject.put("postDateStatus", postDateStatus);
                jSONObject.put("postDate", cal.getTimeInMillis());
                jSONObject.put("postTime", scheduledEntityListObject.getScheduleTime().getTime());
                jSONObject.put("actionType", scheduledEntityListObject.getEntityType());
                scheduledEmailAndSocailPostJsonForRecurringArray.put(jSONObject);
                System.out.println(scheduledEmailAndSocailPostJsonForRecurringArray);

            }
            List<ScheduledEntityList> scheduledEntityListForSocialpost = scheduledEntityListService.getScheduledEntityListIdForSocialPostType(userProgram_id);

            for (ScheduledEntityList scheduledSocialpostListObject : scheduledEntityListForSocialpost) {
                Date eventDate = scheduledSocialpostListObject.getFkCompanyMarketingProgramId().getDateEvent();
                String dateString = formatter.format(eventDate);
                Date todayDate = Calendar.getInstance().getTime();
                String eventDateString = formatter.format(todayDate);
                if (dateString.equalsIgnoreCase(eventDateString)) {
                    actionStatus = true;
                } else {
                    actionStatus = false;
                }
                Integer days = scheduledSocialpostListObject.getDays();
                Calendar cal = Calendar.getInstance();
                cal.setTime(eventDate);
                cal.add(Calendar.DAY_OF_MONTH, -days);
    
                postDateStatus = true;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("scheduledEntityListId", scheduledSocialpostListObject.getScheduledEntityListId());
                jSONObject.put("eventDate", dateString);
                jSONObject.put("programTemplateName", scheduledSocialpostListObject.getScheduleTitle());
                jSONObject.put("status", TemplateStatus.valueOf(scheduledSocialpostListObject.getStatus()).getDisplayName());
                jSONObject.put("actionStatus", actionStatus);
                jSONObject.put("days", scheduledSocialpostListObject.getDays());
                jSONObject.put("description", scheduledSocialpostListObject.getScheduleDesc());
                jSONObject.put("postDateStatus", postDateStatus);
                jSONObject.put("postDate", cal.getTimeInMillis());
                jSONObject.put("postTime", scheduledSocialpostListObject.getScheduleTime().getTime());
                jSONObject.put("actionType", scheduledSocialpostListObject.getEntityType());
                scheduledEmailAndSocailPostJsonForRecurringArray.put(jSONObject);

            }

            Integer list1_size = scheduledEntityList.size();
            Integer list2_size = scheduledEmailListForRecurring.size();
            Integer list3_size = scheduledEntityListForSocialpost.size();

            CompanyMarketingProgram marketingProgram = companyMarketingProgramService.getById(userProgram_id);
            CompanyMarketingProgram companyMarketingProgram = companyMarketingProgramService.getByCompanyMarketingProgramIdAndMarketingProgramId(userProgram_id, marketingProgram.getFkMarketingProgramId().getMarketingProgramId());
            JSONObject companyMarketinProgramObject = new JSONObject();
            Date dateEvent = companyMarketingProgram.getDateEvent();
            String dateOfEvent = formatter.format(dateEvent);
            companyMarketinProgramObject.put("programName", companyMarketingProgram.getCompanyMarketingProgramName());
            companyMarketinProgramObject.put("noOfActions", list1_size + list2_size + list3_size);
            companyMarketinProgramObject.put("linktodestination", companyMarketingProgram.getUrl());
            companyMarketinProgramObject.put("link_name", companyMarketingProgram.getLinkName());
            companyMarketinProgramObject.put("program_status", companyMarketingProgram.getStatus());
            companyMarketinProgramObject.put("dateOfEvent", dateEvent.getTime());
            companyMarketinProgramObject.put("description", companyMarketingProgram.getFkMarketingProgramId().getHtmlData());

            JSONObject jSONObject = new JSONObject();

            jSONObject.put("programdetails", companyMarketinProgramObject);
            jSONObject.put("emailautomation", scheduledEmailJsonArray);
            jSONObject.put("programactions", scheduledEmailAndSocailPostJsonForRecurringArray);
            System.out.println(jSONObject);

            return jSONObject.toString();
        } catch (Throwable throwable) {
            logger.error(throwable);

        }
        return null;

    }

    @RequestMapping(value = "/getAllUserMarketingPrograms", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUserMarketingPrograms(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("companyId") Integer companyId) {
        JSONArray json_array_marketing_program = new JSONArray();
        try {
            List<CompanyMarketingProgram> companyMarketingPrograms = companyMarketingProgramService.getAllCompanyMarketingOpenPrograms("Open", companyId);
            Integer i = 1;
            for (CompanyMarketingProgram marketing_program : companyMarketingPrograms) {

                org.json.JSONObject json_marketing_programming = new org.json.JSONObject();
                json_marketing_programming.put("id", i);
                json_marketing_programming.put("program_id", marketing_program.getCompanyMarketingProgramId());
                json_marketing_programming.put("name", marketing_program.getCompanyMarketingProgramName());

                json_array_marketing_program.put(json_marketing_programming);
                i++;
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return json_array_marketing_program.toString();
    }

    @RequestMapping(value = "/getAllUserMarketingProgramsByUserId", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUserMarketingProgramsByUserId(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("companyId") Integer companyId) {
        JSONArray json_array_marketing_program = new JSONArray();
        try {

            List<CompanyMarketingProgram> companyMarketingPrograms = companyMarketingProgramService.getAllCompanyMarketingProgramByCompanyId(companyId);
            Integer i = 1;
            for (CompanyMarketingProgram marketing_program : companyMarketingPrograms) {

                org.json.JSONObject json_marketing_programming = new org.json.JSONObject();
                json_marketing_programming.put("id", i);
                json_marketing_programming.put("program_id", marketing_program.getCompanyMarketingProgramId());
                json_marketing_programming.put("prigram_name", marketing_program.getCompanyMarketingProgramName());
                json_marketing_programming.put("url", marketing_program.getUrl());
                json_marketing_programming.put("link_name", marketing_program.getLinkName());

                json_array_marketing_program.put(json_marketing_programming);
                i++;
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return json_array_marketing_program.toString();
    }

    @RequestMapping(value = "/getAllUserMarketingProgramsBySessionUserId", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUserMarketingProgramBySessionUserId(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("companyId") Integer companyId) {
        JSONArray json_array_marketing_program = new JSONArray();
        try {

            List<CompanyMarketingProgram> companyMarketingPrograms = companyMarketingProgramService.getAllCompanyMarketingProgramByCompanyId(companyId);
            Integer i = 1;
            for (CompanyMarketingProgram marketing_program : companyMarketingPrograms) {

                org.json.JSONObject json_marketing_programming = new org.json.JSONObject();
                json_marketing_programming.put("href", marketing_program.getUrl());
                json_marketing_programming.put("text", marketing_program.getLinkName());

                json_array_marketing_program.put(json_marketing_programming);
                i++;
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return json_array_marketing_program.toString();
    }

    @RequestMapping(value = "/updateUserProgram", method = RequestMethod.POST)
    public @ResponseBody
    String updateUserProgram(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            String program_id1 = (String) requestBodyMap.get("program_id");
            Double program_id = Double.parseDouble(program_id1);
            Double date_of_event = (Double) requestBodyMap.get("date_of_event");
            String link_url = (String) requestBodyMap.get("link_url");
            String link_name = (String) requestBodyMap.get("link_name");
            String name = (String) requestBodyMap.get("program_name");

            CompanyMarketingProgram companyMarketingProgram = companyMarketingProgramService.getById(program_id.intValue());

            Date event_date = new Date(date_of_event.longValue());

            companyMarketingProgram.setDateEvent(event_date);
            companyMarketingProgram.setUrl(link_url);
            companyMarketingProgram.setLinkName(link_name);
            companyMarketingProgram.setCompanyMarketingProgramName(name);

            companyMarketingProgramService.update(companyMarketingProgram);
            return "true";
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return "false";
    }


    @RequestMapping(value = "/approveStatusRecurring", method = RequestMethod.POST)
    public @ResponseBody
    String approveStatusRecurring(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {

            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            Double entity_id = (Double) requestBodyMap.get("entity_id");
            String template_status = (String) requestBodyMap.get("template_status");

            ScheduledEntityList scheduled_entity_list = scheduledEntityListService.getEntityById(entity_id.intValue());

            if (template_status.equalsIgnoreCase("approved")) {
                scheduled_entity_list.setStatus(TemplateStatus.approved.toString());
            } else if (template_status.equalsIgnoreCase("template_saved")) {
                scheduled_entity_list.setStatus(TemplateStatus.template_saved.toString());
            }
            scheduledEntityListService.update(scheduled_entity_list);
            return "true";
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return "false";
    }

     @RequestMapping(value = "/approveStatus", method = RequestMethod.POST)
    public @ResponseBody
    String approveStatus(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {

            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            Integer entity_id = Integer.parseInt((String)requestBodyMap.get("entity_id"));
            String template_status = (String) requestBodyMap.get("template_status");

            ScheduledEntityList scheduled_entity_list = scheduledEntityListService.getEntityById(entity_id);

            if (template_status.equalsIgnoreCase("approved")) {
                scheduled_entity_list.setStatus(TemplateStatus.approved.toString());
            } else if (template_status.equalsIgnoreCase("template_saved")) {
                scheduled_entity_list.setStatus(TemplateStatus.template_saved.toString());
            } else if (template_status.equalsIgnoreCase("complete")){
                scheduled_entity_list.setStatus(TemplateStatus.complete.toString());
            } else if (template_status.equalsIgnoreCase("no_template")){
                scheduled_entity_list.setStatus(TemplateStatus.no_template.toString());
            }
            scheduledEntityListService.update(scheduled_entity_list);

            return "true";
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return "false";
    }

    @RequestMapping(value = "/endMarketingProgram", method = RequestMethod.POST)
    public @ResponseBody
    String endMarketingProgram(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {

            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            Double program_id = (Double) requestBodyMap.get("program_id");
            CompanyMarketingProgram user_marketing_program = companyMarketingProgramService.getById(program_id.intValue());

            user_marketing_program.setStatus("Closed");

            companyMarketingProgramService.update(user_marketing_program);
            return "true";
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return "false";
    }

}
