/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.controller.ApplicationContextListener;
import com.controller.IConstants;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import com.intbit.marketing.model.TblMarketingAction;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblRecuringEmailTemplate;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.MarketingActionService;
import com.intbit.marketing.service.RecuringEmailTemplateService;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledEntityListService;
import com.intbit.marketing.service.ScheduledSocialpostListService;
import com.intbit.marketing.service.UserMarketingProgramService;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static oracle.jrockit.jfr.events.Bits.intValue;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateTimeUtil;

/**
 *
 * @author intbit-6
 */
@Controller
public class UserMarketingProgramController {

    private static final Logger logger = Logger.getLogger(UserMarketingProgramController.class.getName());
    SqlMethods sql_methods = new SqlMethods();
    @Autowired
    private UserMarketingProgramService userMarketingProgramService;
    @Autowired
    private ScheduledEmailListService scheduledEmailListService;
    @Autowired
    private ScheduledSocialpostListService scheduledSocialpostListService;
    @Autowired
    private MarketingActionService marketingActionService;
    @Autowired
    private ScheduledEntityListService scheduledEntityListService;
    @Autowired
    private RecuringEmailTemplateService recuringEmailTemplateService;

    @Deprecated
    @RequestMapping(value = "/allmarketingProgram", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUserMarketingProgram(HttpServletRequest request,
            HttpServletResponse response) {
        Integer userProgram_id = 1;
        Integer marketingProgramId = 1;
        Boolean actionStatus;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat TimeFormatter = new SimpleDateFormat("hh:mm");
        try {

            List<TblScheduledEmailList> scheduledEmailList = scheduledEmailListService.getAllScheduledEmailListForUserMarketingProgram(userProgram_id, Boolean.TRUE);
            JSONArray scheduledEmailJsonArray = new JSONArray();
            for (TblScheduledEmailList scheduledEmailListObject : scheduledEmailList) {
                TblRecuringEmailTemplate recuringEmailTemplate = recuringEmailTemplateService.getById(scheduledEmailListObject.getTblScheduledEntityList().getRecuringEmailId());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("scheduledEntityListId", scheduledEmailListObject.getTblScheduledEntityList().getId());
                jSONObject.put("emailAutomationName", scheduledEmailListObject.getEmailListName());
                jSONObject.put("dateTime", "Started on " + scheduledEmailListObject.getTblScheduledEntityList().getTblUserMarketingProgram().getCreateDate());
                jSONObject.put("programTemplateName", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTitle());
                jSONObject.put("status", scheduledEmailListObject.getTblScheduledEntityList().getStatus());
                jSONObject.put("emailRecuringTemplateName", recuringEmailTemplate.getName());
                scheduledEmailJsonArray.put(jSONObject);

            }
            List<TblScheduledEmailList> scheduledEmailListForRecuring = scheduledEmailListService.getAllScheduledEmailListForUserMarketingProgram(userProgram_id, Boolean.FALSE);
            JSONArray scheduledEmailAndSocailPostJsonForRecuringArray = new JSONArray();
            for (TblScheduledEmailList scheduledEmailListObject : scheduledEmailListForRecuring) {
                TblScheduledEntityList scheduledEntityListObject = scheduledEntityListService.getById(scheduledEmailListObject.getTblScheduledEntityList().getId());
                TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getById(scheduledEntityListObject.getTblUserMarketingProgram().getId());
                Date eventDate = userMarketingProgram.getDateEvent();
                String dateString = formatter.format(eventDate);
                Date todayDate = Calendar.getInstance().getTime();
                String eventDateString = formatter.format(todayDate);
                if (dateString.equalsIgnoreCase(eventDateString)) {
                    actionStatus = true;
                } else {
                    actionStatus = false;
                }
                Integer days = scheduledEmailListObject.getTblScheduledEntityList().getDays();
                Calendar cal = Calendar.getInstance();
                cal.setTime(eventDate);
                cal.add(Calendar.DAY_OF_MONTH, -days);
                String postDate = formatter.format(cal.getTime());
                String postTime = TimeFormatter.format(cal.getTime());
                JSONObject jSONObject = new JSONObject();
//                jSONObject.put("emailAutomationName", scheduledEmailListObject.getEmailListName());
                jSONObject.put("scheduledEntityListId", scheduledEmailListObject.getTblScheduledEntityList().getId());
                jSONObject.put("eventDate", dateString);
                jSONObject.put("programTemplateName", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTitle());
                jSONObject.put("status", scheduledEmailListObject.getTblScheduledEntityList().getStatus());
                jSONObject.put("actionStatus", actionStatus);
                jSONObject.put("postDate", postDate);
                jSONObject.put("postTime", "Scheduled for " + postTime);
                jSONObject.put("actionType", scheduledEmailListObject.getTblScheduledEntityList().getEntityType());
                scheduledEmailAndSocailPostJsonForRecuringArray.put(jSONObject);
                System.out.println(scheduledEmailAndSocailPostJsonForRecuringArray);

            }
//            
            TblUserMarketingProgram marketingProgram = userMarketingProgramService.getById(userProgram_id);
            TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getByUserMarketingProgramIdAndMarketingProgramId(userProgram_id, marketingProgram.getTblMarketingProgram().getId());
            JSONObject userMarketinProgramObject = new JSONObject();
            Date dateEvent = userMarketingProgram.getDateEvent();
            String dateOfEvent = formatter.format(dateEvent);
            userMarketinProgramObject.put("programName", userMarketingProgram.getName());
            userMarketinProgramObject.put("noOfActions", "15");
            userMarketinProgramObject.put("linktodestination", userMarketingProgram.getUrl());
            userMarketinProgramObject.put("dateOfEvent", dateOfEvent);
            userMarketinProgramObject.put("description", userMarketingProgram.getTblMarketingProgram().getHtmlData());

            JSONObject jSONObject = new JSONObject();
            jSONObject.put("programdetails", userMarketinProgramObject);
            jSONObject.put("emailautomation", scheduledEmailJsonArray);
            jSONObject.put("programactions", scheduledEmailAndSocailPostJsonForRecuringArray);
            System.out.println(jSONObject);

            return jSONObject.toString();
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);

        }
        return null;

    }

    public Integer getCurrentUser(HttpServletRequest request) {
        sql_methods.session = request.getSession(true);
        Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
        return user_id;
    }

    @RequestMapping(value = "/setMarketingProgram", method = RequestMethod.POST)
    public @ResponseBody
    String setUserMarketingProgram(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {
            SimpleDateFormat formatter = null;
            Date tillDate = null;
            TblUserMarketingProgram addUserMarketingProgram = new TblUserMarketingProgram();
            TblUserLoginDetails userLoginDetails = new TblUserLoginDetails();
            TblMarketingProgram marketingProgram = new TblMarketingProgram();
            sql_methods.session = request.getSession(true);
            Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
            userLoginDetails.setId(user_id);

            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            addUserMarketingProgram.setName(requestBodyMap.get("program_name").toString());
            String target = requestBodyMap.get("program_date_time").toString();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date eventDate = df.parse(target);
            Double marketingCategoryId = (Double) requestBodyMap.get("marketing_category_id");
            Double marketingProgramId = (Double) requestBodyMap.get("marketing_program_id");
            marketingProgram.setId(marketingProgramId.intValue());
            addUserMarketingProgram.setId(0);
            addUserMarketingProgram.setDateEvent(eventDate);
            addUserMarketingProgram.setCreateDate(new Date());
            addUserMarketingProgram.setTblUserLoginDetails(userLoginDetails);
            addUserMarketingProgram.setStatus("Open");
            addUserMarketingProgram.setUrl(requestBodyMap.get("program_url").toString());
            addUserMarketingProgram.setLinkName(requestBodyMap.get("program_url_name").toString());
            addUserMarketingProgram.setTblMarketingProgram(marketingProgram);
            Integer userMarketingProgramId = userMarketingProgramService.save(addUserMarketingProgram);
            String link = userMarketingProgramId.toString();

            TblMarketingAction marketingAction = marketingActionService.getMarketingActionByMCategoryIdAndMProgramId(marketingCategoryId.intValue(), marketingProgramId.intValue());
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

                TblScheduledEntityList scheduledEntityList = new TblScheduledEntityList();
                scheduledEntityList.setEntityType(jsonObject.get("type").toString());
                scheduledEntityList.setIsRecuring(Boolean.parseBoolean(jsonObject.get("is_recuring").toString()));
                scheduledEntityList.setScheduleDesc(jsonObject.get("description").toString());
                scheduledEntityList.setEntityId(0);
                scheduledEntityList.setScheduleTime(time);
                scheduledEntityList.setTillDate(tillDate);
                scheduledEntityList.setDays(Integer.parseInt(jsonObject.get("days").toString()));
                scheduledEntityList.setStatus(TemplateStatus.no_template.toString());
                scheduledEntityList.setScheduleTitle(jsonObject.get("title").toString());
                scheduledEntityList.setUserId(user_id);
                TblUserMarketingProgram userMarketingProgram = new TblUserMarketingProgram();
                userMarketingProgram.setId(userMarketingProgramId);
                scheduledEntityList.setTblUserMarketingProgram(userMarketingProgram);
                scheduledEntityListService.save(scheduledEntityList);
            }
            return link;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception while saving the user marketing program", ex);
        }
        return "false";
    }

    @RequestMapping(value = "/listAllMarketingProgram", method = RequestMethod.GET)
    public @ResponseBody
    String listAllUserMarketingProgram(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("programType") String programType) throws Throwable {
        System.out.println("in listAllmarketingProgram");
        Integer user_id = getCurrentUser(request);
        List<TblUserMarketingProgram> userMarketingProgramList = userMarketingProgramService.getAllUserMarketingProgramByType(user_id, programType);
        JSONObject jsonObject = new JSONObject();
        JSONArray json_array = new JSONArray();

        for (TblUserMarketingProgram userMarketingProgramObject : userMarketingProgramList) {
            JSONObject json_obj = new JSONObject();
            json_obj.put("id", userMarketingProgramObject.getId());
            json_obj.put("program_name", userMarketingProgramObject.getName());
            Date create_date = userMarketingProgramObject.getCreateDate();
            Date end_date = userMarketingProgramObject.getDateEvent();
            Long create_date_in = create_date.getTime();
            Long end_date_in = end_date.getTime();
            json_obj.put("start_date", create_date_in);
            json_obj.put("end_date", end_date_in);
            if (programType.equalsIgnoreCase("Open")) {
                Integer noofrecords = scheduledEntityListService.getCurrentRecords(userMarketingProgramObject.getId());
                json_obj.put("noofpostleft", noofrecords);
            }
            json_array.put(json_obj);

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
        SimpleDateFormat TimeFormatter = new SimpleDateFormat("hh:mm");
        try {

            List<TblScheduledEntityList> scheduledEntityList = scheduledEntityListService.getScheduledEntityListIdForEmailType(userProgram_id, Boolean.TRUE);
            JSONArray scheduledEmailJsonArray = new JSONArray();
            for (TblScheduledEntityList scheduledEntityListObject : scheduledEntityList) {
                JSONObject jSONObject = new JSONObject();

                Date eventDate = scheduledEntityListObject.getTblUserMarketingProgram().getDateEvent();
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
                Date postDate = new Date(cal.getTimeInMillis());
                if (DateTimeUtil.dateEqualsCurrentDate(postDate)){
                    postDateStatus = true;
                } else {
                    postDateStatus = false;
                }
                if (scheduledEntityListObject.getRecuringEmailId() != null) {
                    TblRecuringEmailTemplate recuringEmailTemplate = recuringEmailTemplateService.getById(scheduledEntityListObject.getRecuringEmailId());
                    jSONObject.put("emailRecuringTemplateName", recuringEmailTemplate.getName());
                } else {
                    jSONObject.put("emailRecuringTemplateName", "no recuring template");
                }

                jSONObject.put("scheduledEntityListId", scheduledEntityListObject.getId());
                jSONObject.put("dateTime", scheduledEntityListObject.getTblUserMarketingProgram().getCreateDate().getTime());
                jSONObject.put("programTemplateName", scheduledEntityListObject.getScheduleTitle());
                jSONObject.put("days", scheduledEntityListObject.getDays());
                int d=scheduledEntityListObject.getDays();
                jSONObject.put("description", scheduledEntityListObject.getScheduleDesc());
                jSONObject.put("postDateStatus", postDateStatus);
                jSONObject.put("status", TemplateStatus.valueOf(scheduledEntityListObject.getStatus()).getDisplayName());
                scheduledEmailJsonArray.put(jSONObject);
            }
            List<TblScheduledEntityList> scheduledEmailListForRecuring = scheduledEntityListService.getScheduledEntityListIdForEmailType(userProgram_id, Boolean.FALSE);
            JSONArray scheduledEmailAndSocailPostJsonForRecuringArray = new JSONArray();
            for (TblScheduledEntityList scheduledEntityListObject : scheduledEmailListForRecuring) {
                TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getById(scheduledEntityListObject.getTblUserMarketingProgram().getId());
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
                Date postDate = new Date(cal.getTimeInMillis());
                if (DateTimeUtil.dateEqualsCurrentDate(postDate)){
                    postDateStatus = true;
                } else {
                    postDateStatus = false;
                }
                String postTime = TimeFormatter.format(cal.getTimeInMillis());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("scheduledEntityListId", scheduledEntityListObject.getId());
                jSONObject.put("eventDate", eventDate.getTime());
                jSONObject.put("programTemplateName", scheduledEntityListObject.getScheduleTitle());
                jSONObject.put("status", TemplateStatus.valueOf(scheduledEntityListObject.getStatus()).getDisplayName());
                jSONObject.put("description", scheduledEntityListObject.getScheduleDesc());
                jSONObject.put("actionStatus", actionStatus);
                jSONObject.put("days", scheduledEntityListObject.getDays());
                int d1=scheduledEntityListObject.getDays();
                jSONObject.put("postDateStatus", postDateStatus);
                jSONObject.put("postDate", cal.getTimeInMillis());
                jSONObject.put("postTime", scheduledEntityListObject.getScheduleTime().getTime());
                jSONObject.put("actionType", scheduledEntityListObject.getEntityType());
                scheduledEmailAndSocailPostJsonForRecuringArray.put(jSONObject);
                System.out.println(scheduledEmailAndSocailPostJsonForRecuringArray);

            }
            List<TblScheduledEntityList> scheduledEntityListForSocialpost = scheduledEntityListService.getScheduledEntityListIdForSocialPostType(userProgram_id);

            for (TblScheduledEntityList scheduledSocialpostListObject : scheduledEntityListForSocialpost) {
                Date eventDate = scheduledSocialpostListObject.getTblUserMarketingProgram().getDateEvent();
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
                Date postDate = new Date(cal.getTimeInMillis());
                if (DateTimeUtil.dateEqualsCurrentDate(postDate)){
                    postDateStatus = true;
                } else {
                    postDateStatus = false;
                }
                String postTime = TimeFormatter.format(cal.getTimeInMillis());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("scheduledEntityListId", scheduledSocialpostListObject.getId());
                jSONObject.put("eventDate", dateString);
                jSONObject.put("programTemplateName", scheduledSocialpostListObject.getScheduleTitle());
                jSONObject.put("status", TemplateStatus.valueOf(scheduledSocialpostListObject.getStatus()).getDisplayName());
                jSONObject.put("actionStatus", actionStatus);
                jSONObject.put("days", scheduledSocialpostListObject.getDays());
                int d2=scheduledSocialpostListObject.getDays();
                jSONObject.put("description", scheduledSocialpostListObject.getScheduleDesc());
                jSONObject.put("postDateStatus", postDateStatus);
                jSONObject.put("postDate", cal.getTimeInMillis());
                jSONObject.put("postTime", scheduledSocialpostListObject.getScheduleTime().getTime());
                jSONObject.put("actionType", scheduledSocialpostListObject.getEntityType());
                scheduledEmailAndSocailPostJsonForRecuringArray.put(jSONObject);

            }

            Integer list1_size = scheduledEntityList.size();
            Integer list2_size = scheduledEmailListForRecuring.size();
            Integer list3_size = scheduledEntityListForSocialpost.size();

            TblUserMarketingProgram marketingProgram = userMarketingProgramService.getById(userProgram_id);
            TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getByUserMarketingProgramIdAndMarketingProgramId(userProgram_id, marketingProgram.getTblMarketingProgram().getId());
            JSONObject userMarketinProgramObject = new JSONObject();
            Date dateEvent = userMarketingProgram.getDateEvent();
            String dateOfEvent = formatter.format(dateEvent);
            userMarketinProgramObject.put("programName", userMarketingProgram.getName());
            userMarketinProgramObject.put("noOfActions", list1_size + list2_size + list3_size);
            userMarketinProgramObject.put("linktodestination", userMarketingProgram.getUrl());
            userMarketinProgramObject.put("link_name", userMarketingProgram.getLinkName());
            userMarketinProgramObject.put("program_status", userMarketingProgram.getStatus());
            userMarketinProgramObject.put("dateOfEvent", dateEvent.getTime());
            userMarketinProgramObject.put("description", userMarketingProgram.getTblMarketingProgram().getHtmlData());

            JSONObject jSONObject = new JSONObject();
            
            jSONObject.put("programdetails", userMarketinProgramObject);
            jSONObject.put("emailautomation", scheduledEmailJsonArray);
            jSONObject.put("programactions", scheduledEmailAndSocailPostJsonForRecuringArray);
            System.out.println(jSONObject);

            return jSONObject.toString();
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);

        }
        return null;

    }

    @RequestMapping(value = "/getAllUserMarketingPrograms", method = RequestMethod.GET)
    public @ResponseBody String getAllUserMarketingPrograms(HttpServletRequest request,
            HttpServletResponse response) {
        JSONArray json_array_marketing_program = new JSONArray();
        try {

            List<TblUserMarketingProgram> UserMarketingPrograms = userMarketingProgramService.getAllUserMarketingProgram();
            Integer i = 1;
            for (TblUserMarketingProgram marketing_program : UserMarketingPrograms) {

                org.json.JSONObject json_marketing_programming = new org.json.JSONObject();
                json_marketing_programming.put("id", i);
                json_marketing_programming.put("program_id", marketing_program.getId());
                json_marketing_programming.put("name", marketing_program.getName());

                json_array_marketing_program.put(json_marketing_programming);
                i++;
            }
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
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

            Double program_id = (Double) requestBodyMap.get("program_id");
            Double date_of_event = (Double) requestBodyMap.get("date_of_event");
            String link_url = (String) requestBodyMap.get("link_url");
            String link_name = (String) requestBodyMap.get("link_name");

            TblUserMarketingProgram user_marketing_program = userMarketingProgramService.getById(program_id.intValue());

            Date event_date = new Date(date_of_event.longValue());

            user_marketing_program.setDateEvent(event_date);
            user_marketing_program.setUrl(link_url);
            user_marketing_program.setLinkName(link_name);

            userMarketingProgramService.update(user_marketing_program);
            return "true";
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
        }
        return "false";
    }

    @RequestMapping(value = "/getEntityDetails", method = RequestMethod.POST)
    public @ResponseBody
    String getEntityDetails(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {

        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

        Double entity_id = (Double) requestBodyMap.get("entity_id");
        TblScheduledEntityList schedule_entity = scheduledEntityListService.getById(entity_id.intValue());

        return "false";
    }

    @RequestMapping(value = "/approveStatusRecuring", method = RequestMethod.POST)
    public @ResponseBody
    String approveStatusRecuring(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {

            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            Double entity_id = (Double) requestBodyMap.get("entity_id");
            String template_status = (String) requestBodyMap.get("template_status");
            
            TblScheduledEntityList scheduled_entity_list = scheduledEntityListService.getById(entity_id.intValue());

            if (template_status.equalsIgnoreCase("approved")) {
                scheduled_entity_list.setStatus(TemplateStatus.approved.toString());
            } else if (template_status.equalsIgnoreCase("template_saved")) {
                scheduled_entity_list.setStatus(TemplateStatus.template_saved.toString());
            }
            scheduledEntityListService.update(scheduled_entity_list);
            ApplicationContextListener.refreshEmailRecuringScheduler();
            return "true";
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
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

            Double entity_id = (Double) requestBodyMap.get("entity_id");
            String template_status = (String) requestBodyMap.get("template_status");
            String entity_type = (String)requestBodyMap.get("entity_type");
            
            TblScheduledEntityList scheduled_entity_list = scheduledEntityListService.getById(entity_id.intValue());

            if (template_status.equalsIgnoreCase("approved")) {
                scheduled_entity_list.setStatus(TemplateStatus.approved.toString());
            } else if (template_status.equalsIgnoreCase("template_saved")) {
                scheduled_entity_list.setStatus(TemplateStatus.template_saved.toString());
            }
            scheduledEntityListService.update(scheduled_entity_list);
            
            if (entity_type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString())){
                ApplicationContextListener.refreshFacebookScheduler();
            }else if(entity_type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString())){
                ApplicationContextListener.refreshTwitterScheduler();
            }else if(entity_type.equalsIgnoreCase(ScheduledEntityType.Email.toString())){
                ApplicationContextListener.refreshEmailScheduler();
            }

            return "true";
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
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
            TblUserMarketingProgram user_marketing_program = userMarketingProgramService.getById(program_id.intValue());

            user_marketing_program.setStatus("Closed");

            userMarketingProgramService.update(user_marketing_program);
            return "true";
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
        }
        return "false";
    }

    @RequestMapping(value = "/forFacebook", method = RequestMethod.GET)
    public @ResponseBody
    String Facebook(HttpServletRequest request,
            HttpServletResponse response) throws Throwable {
//          TblScheduledEntityList scheduledEntityList = scheduledEntityListService.getLatestApprovedFacebookPost("template_saved", "facebook");
//            System.out.println(scheduledEntityList);
//            System.out.println(scheduledEntityList.getScheduleTime());
//            System.out.println(scheduledEntityList.getScheduleTitle());
//          TblScheduledEntityList  scheduledEntityList = scheduledEntityListService.getLatestApprovedSocialPost("approved", "facebook", "open");
//            System.out.println(scheduledEntityList);
//            System.out.println(scheduledEntityList.getScheduleTime());
//            System.out.println(scheduledEntityList.getScheduleTitle());

        String latestApprovedPost = scheduledEntityListService.getLatestApprovedPost("approved",ScheduledEntityType.Facebook.toString(), "open");
        System.out.println(latestApprovedPost);

        JSONObject json_obj = new JSONObject();
        return null;

    }

}
