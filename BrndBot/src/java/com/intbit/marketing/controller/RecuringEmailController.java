/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.controller.IConstants;
import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblRecuringEmailTemplate;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.RecuringEmailTemplateService;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledEntityListService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author development
 */
@Controller
public class RecuringEmailController {
    Logger logger = Logger.getLogger(RecuringEmailController.class.getName());
    @Autowired
    private RecuringEmailTemplateService recuring_email_template_service;
    @Autowired
    private ScheduledEmailListService schedule_email_list_service;
    @Autowired
    private ScheduledEntityListService schedule_entity_list_service;
    String return_response = "false";
    /*
        this method is used to get all of the recuring email templates from the database
        table tbl_recuring_email_template
    */
    @RequestMapping (value = "/getAllRecuringEmailTemplates", method = RequestMethod.GET)
    public @ResponseBody String getAllRecuringEmailTemplates(){
         JSONArray json_array_recuring_email_template = new JSONArray();
         try{

             List<TblRecuringEmailTemplate> recuring_email_templates = recuring_email_template_service.getAllRecuringEmailTemplate();
             Integer i = 1;
            for (TblRecuringEmailTemplate marketing_template : recuring_email_templates) {

                JSONObject json_marketing_programming = new JSONObject();
                json_marketing_programming.put("id", i);
                json_marketing_programming.put("template_id", marketing_template.getId());
                json_marketing_programming.put("template_name", marketing_template.getName());
                json_marketing_programming.put("html_data", marketing_template.getTemplate());

                json_array_recuring_email_template.put(json_marketing_programming);
                i++;
            }
         }catch (Throwable throwable){
             logger.log(Level.SEVERE,"Exception while reading the recuring email", throwable);
         }
         return json_array_recuring_email_template.toString();
    }
    /*
        this method is used to get the recuring email template from the database
        table tbl_recuring_email_template with the query parameter
        @template_id
    */
    
    @RequestMapping (value = "/getRecuringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String getRecuringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        JSONObject json_marketing_programming = new JSONObject();
         try{
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            Double template_id = (Double)requestBodyMap.get("template_id");
            TblRecuringEmailTemplate recuring_email_templates = recuring_email_template_service.getById(template_id.intValue());
            
            json_marketing_programming.put("template_id", recuring_email_templates.getId());
            json_marketing_programming.put("template_name", recuring_email_templates.getName());
            json_marketing_programming.put("html_data", recuring_email_templates.getTemplate());

         }catch (Throwable throwable){
             logger.log(Level.SEVERE,"Exception while reading the recuring email", throwable);
         }
         return json_marketing_programming.toString();
    }
    /*
        saves a new recuring email template into the database table tbl_recuring_email_template
    */
    @RequestMapping (value = "/setRecuringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String setRecuringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            String template_name = requestBodyMap.get("template_name").toString();
            String template_data = requestBodyMap.get("html_data").toString();
            
            TblRecuringEmailTemplate recuring_email_template = new TblRecuringEmailTemplate();
            
            recuring_email_template.setId(0);
            recuring_email_template.setName(template_name);
            recuring_email_template.setTemplate(template_data);
            
            recuring_email_template_service.save(recuring_email_template);
            return_response = "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE, null, throwable);
        }
        return return_response;
    }
    
    @RequestMapping (value = "/deleteRecuringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String deleteRecuringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            Double template_id = (Double)requestBodyMap.get("template_id");

            recuring_email_template_service.delete(template_id.intValue());
            return_response = "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while deleting the recuring email template:", throwable);
        }
        
        return return_response;
    }

    @RequestMapping (value = "/updateRecuringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String updateRecuringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            String template_id = (String)requestBodyMap.get("template_id");
            String template_name = requestBodyMap.get("template_name").toString();
            String template_html_data = requestBodyMap.get("html_data").toString();
            
            TblRecuringEmailTemplate recuring_email_template = new TblRecuringEmailTemplate();
            
            recuring_email_template.setId(Integer.parseInt(template_id));
            recuring_email_template.setName(template_name);
            recuring_email_template.setTemplate(template_html_data);
            
            recuring_email_template_service.update(recuring_email_template);
            return_response = "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while deleting the recuring email template:", throwable);
        }
        return return_response;
    }
    
    @RequestMapping (value = "/setEmailTemplateToRecuringAction", method = RequestMethod.POST)
    public @ResponseBody String setEmailTemplateToRecuringAction(HttpServletRequest request,
            HttpServletResponse response){
        try{
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            SqlMethods sql_methods = new SqlMethods();
            
            sql_methods.session = request.getSession(true);
            Integer user_id = (Integer) sql_methods.session.getAttribute("UID");

            org.json.simple.JSONObject json_user_preferences = sql_methods.getJSONUserPreferences(user_id);
            
            org.json.simple.JSONObject json_object_email_settings = (org.json.simple.JSONObject)json_user_preferences.get(IConstants.kEmailSettings);

            Double entity_id = (Double)requestBodyMap.get("entity_id");
            String days = (String)requestBodyMap.get("days");
            Double template_id = (Double)requestBodyMap.get("template_id");
            String emaillist = requestBodyMap.get("emaillist").toString();
            String subject = requestBodyMap.get("subject").toString();
            String from_name = requestBodyMap.get("from_name").toString();
            String reply_to_address = requestBodyMap.get("reply_to_address").toString();
            String html_data = requestBodyMap.get("html_data").toString();
            
            TblScheduledEmailList scheduled_email_list = new TblScheduledEmailList();
            scheduled_email_list.setId(0);
            TblUserLoginDetails user_login = new TblUserLoginDetails();
            user_login.setId(user_id);
            scheduled_email_list.setTblUserLoginDetails(user_login);
            scheduled_email_list.setSubject(subject);
            scheduled_email_list.setBody(html_data);
            String from_address = (String)json_object_email_settings.get(IConstants.kEmailFromAddress);
            scheduled_email_list.setFromAddress(from_address);
            scheduled_email_list.setEmailListName(emaillist);
            scheduled_email_list.setFromName(from_name);
            scheduled_email_list.setReplyToEmailAddress(reply_to_address);
            
            Integer email_list_id = schedule_email_list_service.save(scheduled_email_list);
            
            TblScheduledEntityList scheduled_entity_list = schedule_entity_list_service.getById(entity_id.intValue());
            
            scheduled_entity_list.setEntityId(email_list_id);
            scheduled_entity_list.setStatus(TemplateStatus.template_saved.toString());
            scheduled_entity_list.setDays(Integer.parseInt(days));
            scheduled_entity_list.setRecuringEmailId(template_id.intValue());
            schedule_entity_list_service.update(scheduled_entity_list);
            return "true";
            
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while saving the email action in the table:", throwable);
        }
        return "false";
    }
    
    @RequestMapping (value = "/addRecuringAction", method = RequestMethod.POST)
    public @ResponseBody String addRecuringAction(HttpServletRequest request,
            HttpServletResponse response)throws IOException, ParseException{
        try {
        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        SqlMethods sql_methods = new SqlMethods();

        sql_methods.session = request.getSession(true);
        Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
            
        String days = (String)requestBodyMap.get("days");
        String emaillist = (String)requestBodyMap.get("emaillist");
        ArrayList email_addresses = (ArrayList)requestBodyMap.get("to_email_addresses");

//        JSONParser parser = new JSONParser();
//        
//        org.json.simple.JSONObject json_object_email_addresses = (org.json.simple.JSONObject)parser.parse(emails);
        String subject = (String)requestBodyMap.get("subject");
        String from_name = (String)requestBodyMap.get("from_name");
        String reply_to_address = (String)requestBodyMap.get("reply_to_address");
        String recuring_email_title = (String)requestBodyMap.get("recuring_email_title");
        String recuring_email_description = (String)requestBodyMap.get("recuring_email_description");
        Double till_date_epoch = (Double)requestBodyMap.get("till_date_epoch");
        
        Date till_date = new Date(till_date_epoch.longValue());
        
        Double schedule_time_epoch = (Double)requestBodyMap.get("schedule_time_epoch");
        
        Date schedule_time = new Date(schedule_time_epoch.longValue());
        Double program_id = (Double)requestBodyMap.get("program_id");
        
        TblScheduledEmailList schedule_email_list = new TblScheduledEmailList();
        
        schedule_email_list.setId(0);
        TblUserLoginDetails user_login = new TblUserLoginDetails();
        user_login.setId(user_id);
        
        schedule_email_list.setTblUserLoginDetails(user_login);
        schedule_email_list.setEmailListName(emaillist);
        schedule_email_list.setFromAddress(getFromAddress(user_id));
        schedule_email_list.setFromName(from_name);
        schedule_email_list.setReplyToEmailAddress(reply_to_address);
        schedule_email_list.setSubject(subject);
        schedule_email_list.setToEmailAddresses(email_addresses.toString());
        schedule_email_list.setTblScheduledEntityList(null);
        
        Integer email_list_id = schedule_email_list_service.save(schedule_email_list);

        TblScheduledEntityList schedule_entity_list = new TblScheduledEntityList();
        
        schedule_entity_list.setId(0);
        schedule_entity_list.setEntityId(email_list_id);
        schedule_entity_list.setEntityType(ScheduledEntityType.email.toString());
        schedule_entity_list.setIsRecuring(Boolean.TRUE);
        schedule_entity_list.setRecuringEmailId(null);
        schedule_entity_list.setScheduleDesc(recuring_email_description);
        schedule_entity_list.setScheduleTime(schedule_time);
        schedule_entity_list.setScheduleTitle(recuring_email_title);
        schedule_entity_list.setStatus(TemplateStatus.no_template.toString());
        TblUserMarketingProgram user_marketing_program = new TblUserMarketingProgram();
        user_marketing_program.setId(program_id.intValue());
        
        schedule_entity_list.setTblUserMarketingProgram(user_marketing_program);
        schedule_entity_list.setDays(Integer.parseInt(days));
        schedule_entity_list.setTillDate(till_date);
        schedule_entity_list.setUserId(user_id);
        
        schedule_entity_list_service.save(schedule_entity_list);
        
        return "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while saving the email action in the table:", throwable);
        }
        return "true";
    }
    
    public String getFromAddress(Integer user_id){
        SqlMethods sql_methods = new SqlMethods();

        org.json.simple.JSONObject json_user_preferences = sql_methods.getJSONUserPreferences(user_id);
            
        org.json.simple.JSONObject json_object_email_settings = (org.json.simple.JSONObject)json_user_preferences.get(IConstants.kEmailSettings);

        String from_address = (String)json_object_email_settings.get(IConstants.kEmailFromAddress);
        return from_address;
    }
}
