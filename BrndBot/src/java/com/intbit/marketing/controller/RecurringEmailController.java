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
import com.intbit.marketing.model.TblRecurringEmailTemplate;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.RecurringEmailTemplateService;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledEntityListService;
import com.intbit.util.ServletUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
public class RecurringEmailController {
    static final Logger logger = Logger.getLogger(RecurringEmailController.class.getName());
    @Autowired
    private RecurringEmailTemplateService recurring_email_template_service;
    @Autowired
    private ScheduledEmailListService schedule_email_list_service;
    @Autowired
    private ScheduledEntityListService schedule_entity_list_service;
    String return_response = "false";
    /*
        this method is used to get all of the recurring email templates from the database
        table tbl_recurring_email_template
    */
    @RequestMapping (value = "/getAllRecurringEmailTemplates", method = RequestMethod.GET)
    public @ResponseBody String getAllRecurringEmailTemplates(){
         JSONArray json_array_recurring_email_template = new JSONArray();
         try{

             List<TblRecurringEmailTemplate> recurring_email_templates = recurring_email_template_service.getAllRecurringEmailTemplate();
             Integer i = 1;
            for (TblRecurringEmailTemplate marketing_template : recurring_email_templates) {

                JSONObject json_marketing_programming = new JSONObject();
                json_marketing_programming.put("id", i);
                json_marketing_programming.put("template_id", marketing_template.getId());
                json_marketing_programming.put("template_name", marketing_template.getName());
                json_marketing_programming.put("html_data", marketing_template.getTemplate());

                json_array_recurring_email_template.put(json_marketing_programming);
                i++;
            }
         }catch (Throwable throwable){
             logger.log(Level.SEVERE,"Exception while reading the recurring email", throwable);
         }
         return json_array_recurring_email_template.toString();
    }
    /*
        this method is used to get the recurring email template from the database
        table tbl_recurring_email_template with the query parameter
        @template_id
    */
    
    @RequestMapping (value = "/getRecurringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String getRecurringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        JSONObject json_marketing_programming = new JSONObject();
         try{
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            Double template_id = (Double)requestBodyMap.get("template_id");
            TblRecurringEmailTemplate recurring_email_templates = recurring_email_template_service.getById(template_id.intValue());
            
            json_marketing_programming.put("template_id", recurring_email_templates.getId());
            json_marketing_programming.put("template_name", recurring_email_templates.getName());
            json_marketing_programming.put("html_data", recurring_email_templates.getTemplate());

         }catch (Throwable throwable){
             logger.log(Level.SEVERE,"Exception while reading the recurring email", throwable);
         }
         return json_marketing_programming.toString();
    }
    /*
        saves a new recurring email template into the database table tbl_recurring_email_template
    */
    @RequestMapping (value = "/setRecurringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String setRecurringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            String template_name = requestBodyMap.get("template_name").toString();
            String template_data = requestBodyMap.get("html_data").toString();
            
            TblRecurringEmailTemplate recurring_email_template = new TblRecurringEmailTemplate();
            
            recurring_email_template.setId(0);
            recurring_email_template.setName(template_name);
            recurring_email_template.setTemplate(template_data);
            
            recurring_email_template_service.save(recurring_email_template);
            return_response = "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE, null, throwable);
        }
        return return_response;
    }
    
    @RequestMapping (value = "/deleteRecurringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String deleteRecurringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            Double template_id = (Double)requestBodyMap.get("template_id");

            recurring_email_template_service.delete(template_id.intValue());
            return_response = "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while deleting the recurring email template:", throwable);
        }
        
        return return_response;
    }

    @RequestMapping (value = "/updateRecurringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String updateRecurringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            String template_id = (String)requestBodyMap.get("template_id");
            String template_name = requestBodyMap.get("template_name").toString();
            String template_html_data = requestBodyMap.get("html_data").toString();
            
            TblRecurringEmailTemplate recurring_email_template = new TblRecurringEmailTemplate();
            
            recurring_email_template.setId(Integer.parseInt(template_id));
            recurring_email_template.setName(template_name);
            recurring_email_template.setTemplate(template_html_data);
            
            recurring_email_template_service.update(recurring_email_template);
            return_response = "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while deleting the recurring email template:", throwable);
        }
        return return_response;
    }
    
    @RequestMapping (value = "/setEmailTemplateToRecurringAction", method = RequestMethod.POST)
    public @ResponseBody String setEmailTemplateToRecurringAction(HttpServletRequest request,
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
            scheduled_entity_list.setRecurringEmailId(template_id.intValue());
            schedule_entity_list_service.update(scheduled_entity_list);
            return "true";
            
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while saving the email action in the table:", throwable);
        }
        return "false";
    }
    
    @RequestMapping (value = "/addRecurringAction", method = RequestMethod.POST)
    public @ResponseBody String addRecurringAction(HttpServletRequest request,
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

        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray(email_addresses);
        org.json.simple.JSONObject json_object = new org.json.simple.JSONObject();
        json_object.put(IConstants.kEmailAddressesKey, array);
                
        String subject = (String)requestBodyMap.get("subject");
        String from_name = (String)requestBodyMap.get("from_name");
        String reply_to_address = (String)requestBodyMap.get("reply_to_address");
        String recurring_email_title = (String)requestBodyMap.get("recurring_email_title");
        String recurring_email_description = (String)requestBodyMap.get("recurring_email_description");
        Double till_date_epoch = (Double)requestBodyMap.get("till_date_epoch");
        
        Date till_date = new Date(till_date_epoch.longValue());
        
        String schedule_time = (String)requestBodyMap.get("schedule_time_epoch");
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm:a");
        Date time = formatterTime.parse(schedule_time);

        String program_id = (String)requestBodyMap.get("program_id");
        
        TblScheduledEmailList schedule_email_list = new TblScheduledEmailList();
        
        schedule_email_list.setId(0);
        TblUserLoginDetails user_login = new TblUserLoginDetails();
        user_login.setId(user_id);
        
        schedule_email_list.setTblUserLoginDetails(user_login);
        schedule_email_list.setEmailListName(emaillist);
        org.json.simple.JSONObject jsonFromAddress = (org.json.simple.JSONObject)getFromAddress(user_id);
        
        if (jsonFromAddress != null){
            schedule_email_list.setFromAddress(jsonFromAddress.get(IConstants.kEmailFromAddress).toString());
        }

        schedule_email_list.setFromName(from_name);
        schedule_email_list.setReplyToEmailAddress(reply_to_address);
        schedule_email_list.setSubject(subject);
        schedule_email_list.setToEmailAddresses(json_object.toString());
        schedule_email_list.setTblScheduledEntityList(null);
        
        Integer email_list_id = schedule_email_list_service.save(schedule_email_list);

        TblScheduledEntityList schedule_entity_list = new TblScheduledEntityList();
        
        schedule_entity_list.setId(0);
        schedule_entity_list.setEntityId(email_list_id);
        schedule_entity_list.setEntityType(ScheduledEntityType.Email.toString());
        schedule_entity_list.setIsRecurring(Boolean.TRUE);
        schedule_entity_list.setRecurringEmailId(null);
        schedule_entity_list.setScheduleDesc(recurring_email_description);
        schedule_entity_list.setScheduleTime(time);
        schedule_entity_list.setScheduleTitle(recurring_email_title);
        schedule_entity_list.setStatus(TemplateStatus.no_template.toString());
        TblUserMarketingProgram user_marketing_program = new TblUserMarketingProgram();
        user_marketing_program.setId(Integer.parseInt(program_id));
        
        schedule_entity_list.setTblUserMarketingProgram(user_marketing_program);
        schedule_entity_list.setDays(Integer.parseInt(days));
        schedule_entity_list.setTillDate(till_date);
        schedule_entity_list.setUserId(user_id);
        
        schedule_entity_list_service.save(schedule_entity_list);
        
        return "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while saving the email action in the table:", throwable);
        }
        return "false";
    }
    
    @RequestMapping (value = "/addupdateRecurringAction", method = RequestMethod.POST)
    public @ResponseBody String addupdateRecurringAction(HttpServletRequest request,
            HttpServletResponse response)throws IOException, ParseException{
        try{
            
        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        SqlMethods sql_methods = new SqlMethods();

        sql_methods.session = request.getSession(true);
        Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
            
        String entity_id = (String)requestBodyMap.get("entity_id");
        String days = (String)requestBodyMap.get("days");
        String emaillist = (String)requestBodyMap.get("emaillist");
        ArrayList email_addresses = (ArrayList)requestBodyMap.get("to_email_addresses");

        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray(email_addresses);
        org.json.simple.JSONObject json_object = new org.json.simple.JSONObject();
        json_object.put(IConstants.kEmailAddressesKey, array);
                
        String subject = (String)requestBodyMap.get("subject");
        String from_name = (String)requestBodyMap.get("from_name");
        String reply_to_address = (String)requestBodyMap.get("reply_to_address");
        String recurring_email_title = (String)requestBodyMap.get("recurring_email_title");
        String recurring_email_description = (String)requestBodyMap.get("recurring_email_description");
        Double till_date_epoch = (Double)requestBodyMap.get("till_date_epoch");
        
        Date till_date = new Date(till_date_epoch.longValue());
        
        String schedule_time = (String)requestBodyMap.get("schedule_time_epoch");
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm:a");
        Date time = formatterTime.parse(schedule_time);

        String program_id = (String)requestBodyMap.get("program_id");
        
        TblScheduledEmailList schedule_email_list = new TblScheduledEmailList();
        
        schedule_email_list.setId(0);
        TblUserLoginDetails user_login = new TblUserLoginDetails();
        user_login.setId(user_id);
        
        schedule_email_list.setTblUserLoginDetails(user_login);
        schedule_email_list.setEmailListName(emaillist);
        org.json.simple.JSONObject jsonFromAddress = (org.json.simple.JSONObject)getFromAddress(user_id);
        
        if (jsonFromAddress != null){
            schedule_email_list.setFromAddress(jsonFromAddress.get(IConstants.kEmailFromAddress).toString());
        }
        
        schedule_email_list.setFromName(from_name);
        schedule_email_list.setReplyToEmailAddress(reply_to_address);
        schedule_email_list.setSubject(subject);
        schedule_email_list.setToEmailAddresses(json_object.toString());
        schedule_email_list.setTblScheduledEntityList(null);
        
        Integer email_list_id = schedule_email_list_service.save(schedule_email_list);
        
        TblScheduledEntityList schedule_entity_list = schedule_entity_list_service.getById(Integer.parseInt(entity_id));
        
        schedule_entity_list.setId(Integer.parseInt(entity_id));
        schedule_entity_list.setEntityId(email_list_id);
        schedule_entity_list.setEntityType(ScheduledEntityType.Email.toString());
        schedule_entity_list.setIsRecurring(Boolean.TRUE);
        schedule_entity_list.setScheduleDesc(recurring_email_description);
        schedule_entity_list.setScheduleTime(time);
        schedule_entity_list.setScheduleTitle(recurring_email_title);
        schedule_entity_list.setStatus(TemplateStatus.no_template.toString());
        schedule_entity_list.setRecurringEmailId(null);
        TblUserMarketingProgram user_marketing_program = new TblUserMarketingProgram();
        user_marketing_program.setId(Integer.parseInt(program_id));
        
        schedule_entity_list.setTblUserMarketingProgram(user_marketing_program);
        schedule_entity_list.setDays(Integer.parseInt(days));
        schedule_entity_list.setTillDate(till_date);
        schedule_entity_list.setUserId(user_id);
        
        schedule_entity_list_service.update(schedule_entity_list);
        return "true";
        
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while saving the email action in the table:", throwable);
        }

        return "false";
    }
    
    @RequestMapping (value = "/updateRecurringAction", method = RequestMethod.POST)
    public @ResponseBody String updateRecurringAction(HttpServletRequest request,
            HttpServletResponse response)throws IOException, ParseException{
        try {
        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        SqlMethods sql_methods = new SqlMethods();

        sql_methods.session = request.getSession(true);
        Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
            
        String entity_id = (String)requestBodyMap.get("entity_id");
        String days = (String)requestBodyMap.get("days");
        String emaillist = (String)requestBodyMap.get("emaillist");
        ArrayList email_addresses = (ArrayList)requestBodyMap.get("to_email_addresses");
        
        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray(email_addresses);
        org.json.simple.JSONObject json_object = new org.json.simple.JSONObject();
        json_object.put(IConstants.kEmailAddressesKey, array);
                
        String subject = (String)requestBodyMap.get("subject");
        String from_name = (String)requestBodyMap.get("from_name");
        Double template_id = (Double)requestBodyMap.get("template_id");
        String html_data = (String)requestBodyMap.get("html_data");
        
        //Add by Syed Ilyas 27 Nov 2015 - adds email header
        String htmlHeader = "";
        htmlHeader = ServletUtil.getEmailHeader();
        html_data = htmlHeader + html_data + "</body></html>";
        
        String reply_to_address = (String)requestBodyMap.get("reply_to_address");
        String recurring_email_title = (String)requestBodyMap.get("recurring_email_title");
        String recurring_email_description = (String)requestBodyMap.get("recurring_email_description");
        Double till_date_epoch = (Double)requestBodyMap.get("till_date_epoch");
        
        Date till_date = new Date(till_date_epoch.longValue());
        
        String schedule_time = (String)requestBodyMap.get("schedule_time_epoch");
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm:a");
        Date time = formatterTime.parse(schedule_time);

        String program_id = (String)requestBodyMap.get("program_id");
        
        TblScheduledEntityList schedule_entity_list = schedule_entity_list_service.getById(Integer.parseInt(entity_id));
        
        Integer email_list_id = (Integer)schedule_entity_list.getEntityId();

        schedule_entity_list.setId(Integer.parseInt(entity_id));
        schedule_entity_list.setEntityType(ScheduledEntityType.Email.toString());
        schedule_entity_list.setIsRecurring(Boolean.TRUE);
        schedule_entity_list.setScheduleDesc(recurring_email_description);
        schedule_entity_list.setScheduleTime(time);
        schedule_entity_list.setScheduleTitle(recurring_email_title);
        if (template_id.intValue() == 0){
            schedule_entity_list.setStatus(TemplateStatus.no_template.toString());
            schedule_entity_list.setRecurringEmailId(null);
        }else {
            schedule_entity_list.setStatus(TemplateStatus.template_saved.toString());
            schedule_entity_list.setRecurringEmailId(template_id.intValue());
        }
        TblUserMarketingProgram user_marketing_program = new TblUserMarketingProgram();
        user_marketing_program.setId(Integer.parseInt(program_id));
        
        schedule_entity_list.setTblUserMarketingProgram(user_marketing_program);
        schedule_entity_list.setDays(Integer.parseInt(days));
        schedule_entity_list.setTillDate(till_date);
        schedule_entity_list.setUserId(user_id);
        
        TblScheduledEmailList schedule_email_list = schedule_email_list_service.getById(email_list_id);
        
        TblUserLoginDetails user_login = new TblUserLoginDetails();
        user_login.setId(user_id);
        
        schedule_email_list.setTblUserLoginDetails(user_login);
        schedule_email_list.setEmailListName(emaillist);
        schedule_email_list.setBody(html_data);
        org.json.simple.JSONObject jsonFromAddress = (org.json.simple.JSONObject)getFromAddress(user_id);
        
        if (jsonFromAddress != null){
            schedule_email_list.setFromAddress(jsonFromAddress.get(IConstants.kEmailFromAddress).toString());
        }
        schedule_email_list.setFromName(from_name);
        schedule_email_list.setReplyToEmailAddress(reply_to_address);
        schedule_email_list.setSubject(subject);
        schedule_email_list.setToEmailAddresses(json_object.toString());
        schedule_email_list.setTblScheduledEntityList(null);
        
        schedule_email_list_service.update(schedule_email_list);
        
        schedule_entity_list_service.update(schedule_entity_list);
        
        return "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while saving the email action in the table:", throwable);
        }
        return "true";
    }
    
    public org.json.simple.JSONObject getFromAddress(Integer user_id){
        try{

            SqlMethods sql_methods = new SqlMethods();

            org.json.simple.JSONObject json_user_preferences = sql_methods.getJSONUserPreferences(user_id);

            org.json.simple.JSONObject json_object_email_settings = (org.json.simple.JSONObject)json_user_preferences.get(IConstants.kEmailSettings);

            String from_address = (String)json_object_email_settings.get(IConstants.kEmailFromAddress);

            return json_object_email_settings;
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while getting the from address:", throwable);
        }
        return null;
    }
    
    @RequestMapping (value = "/getUserPreferences", method = RequestMethod.GET)
    public @ResponseBody String getUserPreferences(HttpServletRequest request,
            HttpServletResponse response)throws IOException{

        SqlMethods sql_methods = new SqlMethods();
        
        sql_methods.session = request.getSession(true);
        Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
        
        org.json.simple.JSONObject from_address = (org.json.simple.JSONObject)getFromAddress(user_id);

        return from_address.toString();
        
    }
    
    @RequestMapping (value = "/getRecurringEntity", method = RequestMethod.POST)
    public @ResponseBody String getRecurringEntity(HttpServletRequest request,
            HttpServletResponse response)throws IOException{
        try{
            
        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        
        String entity_id = (String)requestBodyMap.get("entity_id");
        
        if (Integer.parseInt(entity_id) != 0){
        TblScheduledEntityList schedule_entity_list = schedule_entity_list_service.getById(Integer.parseInt(entity_id));

            JSONObject json_entity_list = new JSONObject();

            json_entity_list.put("recurring_email_days", schedule_entity_list.getDays());
            json_entity_list.put("recurring_email_entity_id", schedule_entity_list.getEntityId());
            json_entity_list.put("recurring_email_entity_type", schedule_entity_list.getEntityType());
            json_entity_list.put("recurring_email_is_recurring", schedule_entity_list.getIsRecurring());
            json_entity_list.put("recurring_email_template_id", schedule_entity_list.getRecurringEmailId());
            json_entity_list.put("recurring_email_description", schedule_entity_list.getScheduleDesc());
            json_entity_list.put("recurring_email_time", schedule_entity_list.getScheduleTime().getTime());
            json_entity_list.put("recurring_email_title", schedule_entity_list.getScheduleTitle());
            json_entity_list.put("recurring_email_status", schedule_entity_list.getStatus());
            json_entity_list.put("recurring_email_user_marketing_program_id", schedule_entity_list.getTblUserMarketingProgram().getId());
            json_entity_list.put("recurring_email_till_date", schedule_entity_list.getTillDate().getTime());
            
        if (schedule_entity_list.getEntityId().intValue() != 0){
            TblScheduledEmailList schedule_email_list = schedule_email_list_service.getById(schedule_entity_list.getEntityId().intValue());

            json_entity_list.put("recurring_email_body", schedule_email_list.getBody());
            json_entity_list.put("recurring_email_email_list_name", schedule_email_list.getEmailListName());
            json_entity_list.put("recurring_email_from_address", schedule_email_list.getFromAddress());
            json_entity_list.put("recurring_email_reply_to_email_address", schedule_email_list.getReplyToEmailAddress());
            json_entity_list.put("recurring_email_subject", schedule_email_list.getSubject());
            json_entity_list.put("recurring_email_to_email_addresses", schedule_email_list.getToEmailAddresses());
            json_entity_list.put("recurring_email_from_name", schedule_email_list.getFromName());
            
        }
        return json_entity_list.toString();
        }
        
        
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while getting the recurring email action from the table:", throwable);
        }
        
        return "false";
    }
}
