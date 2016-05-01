/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.controller;

import com.controller.IConstants;
import com.controller.SqlMethods;
import com.intbittech.AppConstants;
import com.intbit.ScheduledEntityType;
import com.intbit.TemplateStatus;
import com.intbittech.marketing.service.ScheduledEmailListService;
import com.intbit.util.ServletUtil;
import com.intbittech.marketing.service.ScheduledEntityListService;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyMarketingProgram;
import com.intbittech.model.RecurringEmailTemplate;
import com.intbittech.model.ScheduledEmailList;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.UserProfile;
import com.intbittech.services.RecurringEmailTemplateService;
import com.intbittech.utility.UserSessionUtil;
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
public class MarketingRecurringEmailController {
    static final Logger logger = Logger.getLogger(MarketingRecurringEmailController.class.getName());
    @Autowired
    private RecurringEmailTemplateService recurringEmailTemplateService;
    @Autowired
    private ScheduledEmailListService schedule_email_list_service;
    @Autowired
    private ScheduledEntityListService scheduledEntityListService;
    String return_response = "false";
    /*
        this method is used to get all of the recurring email templates from the database
        table recurring_email_template
    */
    @RequestMapping (value = "/getAllRecurringEmailTemplates", method = RequestMethod.GET)
    public @ResponseBody String getAllRecurringEmailTemplates(){
         JSONArray json_array_recurring_email_template = new JSONArray();
         try{

             List<RecurringEmailTemplate> recurring_email_templates = recurringEmailTemplateService.getAllRecurringEmails();
             Integer i = 1;
            for (RecurringEmailTemplate marketing_template : recurring_email_templates) {

                JSONObject json_marketing_programming = new JSONObject();
                json_marketing_programming.put("id", i);
                json_marketing_programming.put("template_id", marketing_template.getRecurringEmailTemplateId());
                json_marketing_programming.put("template_name", marketing_template.getTemplateName());
                json_marketing_programming.put("html_data", marketing_template.getHtmlData());

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
        table recurring_email_template with the query parameter
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
            RecurringEmailTemplate recurring_email_templates = recurringEmailTemplateService.getRecurringEmailTemplateById(template_id.intValue());
            
            json_marketing_programming.put("template_id", recurring_email_templates.getRecurringEmailTemplateId());
            json_marketing_programming.put("template_name", recurring_email_templates.getTemplateName());
            json_marketing_programming.put("html_data", recurring_email_templates.getHtmlData());

         }catch (Throwable throwable){
             logger.log(Level.SEVERE,"Exception while reading the recurring email", throwable);
         }
         return json_marketing_programming.toString();
    }
    /*
        saves a new recurring email template into the database table recurring_email_template
    */
    @RequestMapping (value = "/setRecurringEmailTemplate", method = RequestMethod.POST)
    public @ResponseBody String setRecurringEmailTemplate(HttpServletRequest request,
            HttpServletResponse response){
        
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            String template_name = requestBodyMap.get("template_name").toString();
            String template_data = requestBodyMap.get("html_data").toString();
            
            RecurringEmailTemplate recurring_email_template = new RecurringEmailTemplate();
            
            recurring_email_template.setRecurringEmailTemplateId(0);
            recurring_email_template.setTemplateName(template_name);
            recurring_email_template.setHtmlData(template_data);
            
            recurringEmailTemplateService.save(recurring_email_template);
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

            recurringEmailTemplateService.delete(template_id.intValue());
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
            
            RecurringEmailTemplate recurring_email_template = new RecurringEmailTemplate();
            
            recurring_email_template.setRecurringEmailTemplateId(Integer.parseInt(template_id));
            recurring_email_template.setTemplateName(template_name);
            recurring_email_template.setHtmlData(template_html_data);
            
            recurringEmailTemplateService.update(recurring_email_template);
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
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            SqlMethods sql_methods = new SqlMethods();
            
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();

            org.json.simple.JSONObject json_company_preferences = sql_methods.getJSONCompanyPreferences(companyId);
            
            org.json.simple.JSONObject json_object_email_settings = (org.json.simple.JSONObject)json_company_preferences.get(IConstants.kEmailSettings);

            Double entity_id = (Double)requestBodyMap.get("entity_id");
            String days = (String)requestBodyMap.get("days");
            Double template_id = (Double)requestBodyMap.get("template_id");
            String emaillist = requestBodyMap.get("emaillist").toString();
            String subject = requestBodyMap.get("subject").toString();
            String from_name = requestBodyMap.get("from_name").toString();
            String reply_to_address = requestBodyMap.get("reply_to_address").toString();
            String html_data = requestBodyMap.get("html_data").toString();
            
            ScheduledEmailList scheduled_email_list = new ScheduledEmailList();
            scheduled_email_list.setScheduledEmailListId(0);
            Company company = new Company();
            company.setCompanyId(userProfile.getUser().getFkCompanyId().getCompanyId());
            scheduled_email_list.setFkCompanyId(company);
            scheduled_email_list.setSubject(subject);
            scheduled_email_list.setBody(html_data);
            String from_address = (String)json_object_email_settings.get(IConstants.kEmailFromAddress);
            scheduled_email_list.setFromAddress(from_address);
            scheduled_email_list.setEmailListName(emaillist);
            scheduled_email_list.setFromName(from_name);
            scheduled_email_list.setReplyToEmailAddress(reply_to_address);
            
            Integer email_list_id = schedule_email_list_service.save(scheduled_email_list);
            
            ScheduledEntityList scheduled_entity_list = scheduledEntityListService.getById(entity_id.intValue());
            
            scheduled_entity_list.setEntityId(email_list_id);
            scheduled_entity_list.setStatus(TemplateStatus.template_saved.toString());
            scheduled_entity_list.setDays(Integer.parseInt(days));
            RecurringEmailTemplate recurringEmailTemplate =  new RecurringEmailTemplate();
            recurringEmailTemplate.setRecurringEmailTemplateId(template_id.intValue());
            scheduled_entity_list.setFkRecurringEmailId(recurringEmailTemplate);
            scheduledEntityListService.update(scheduled_entity_list);
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
         UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        SqlMethods sql_methods = new SqlMethods();

        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            
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
        
        ScheduledEmailList schedule_email_list = new ScheduledEmailList();
        
        schedule_email_list.setScheduledEmailListId(0);
        Company company = new Company();
        company.setCompanyId(userProfile.getUser().getFkCompanyId().getCompanyId());
        schedule_email_list.setFkCompanyId(company);
        schedule_email_list.setEmailListName(emaillist);
        org.json.simple.JSONObject jsonFromAddress = (org.json.simple.JSONObject)getFromAddress(companyId);
        
        if (jsonFromAddress != null){
            schedule_email_list.setFromAddress(jsonFromAddress.get(IConstants.kEmailFromAddress).toString());
        }

        schedule_email_list.setFromName(from_name);
        schedule_email_list.setReplyToEmailAddress(reply_to_address);
        schedule_email_list.setSubject(subject);
        schedule_email_list.setToEmailAddresses(json_object.toString());
        schedule_email_list.setFkScheduledEntityListId(null);
        
        Integer email_list_id = schedule_email_list_service.save(schedule_email_list);

        ScheduledEntityList schedule_entity_list = new ScheduledEntityList();
        
//        schedule_entity_list.setScheduledEntityListId(0);
        schedule_entity_list.setEntityId(email_list_id);
        schedule_entity_list.setEntityType(ScheduledEntityType.Email.toString());
        schedule_entity_list.setIsRecurring(Boolean.TRUE);
//        schedule_entity_list.setFkRecurringEmailId(null);
        schedule_entity_list.setScheduleDesc(recurring_email_description);
        schedule_entity_list.setScheduleTime(time);
        schedule_entity_list.setScheduleTitle(recurring_email_title);
        schedule_entity_list.setStatus(TemplateStatus.no_template.toString());
        CompanyMarketingProgram companyMarketingProgram = new CompanyMarketingProgram();
        companyMarketingProgram.setCompanyMarketingProgramId(Integer.parseInt(program_id));
        schedule_entity_list.setFkCompanyMarketingProgramId(companyMarketingProgram);
        schedule_entity_list.setDays(Integer.parseInt(days));
        schedule_entity_list.setTillDate(till_date);
        schedule_entity_list.setFkCompanyId(company);
        
       scheduledEntityListService.save(schedule_entity_list);
        
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
         UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser(); 
        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        SqlMethods sql_methods = new SqlMethods();

        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            
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
        
        ScheduledEmailList schedule_email_list = new ScheduledEmailList();
        
        schedule_email_list.setScheduledEmailListId(0);
    
        schedule_email_list.setFkCompanyId(userProfile.getUser().getFkCompanyId());
        schedule_email_list.setEmailListName(emaillist);
        org.json.simple.JSONObject jsonFromAddress = (org.json.simple.JSONObject)getFromAddress(companyId);
        
        if (jsonFromAddress != null){
            schedule_email_list.setFromAddress(jsonFromAddress.get(IConstants.kEmailFromAddress).toString());
        }
        
        schedule_email_list.setFromName(from_name);
        schedule_email_list.setReplyToEmailAddress(reply_to_address);
        schedule_email_list.setSubject(subject);
        schedule_email_list.setToEmailAddresses(json_object.toString());
        schedule_email_list.setFkScheduledEntityListId(null);
        
        Integer email_list_id = schedule_email_list_service.save(schedule_email_list);
        
        ScheduledEntityList schedule_entity_list = scheduledEntityListService.getById(Integer.parseInt(entity_id));
        
        schedule_entity_list.setScheduledEntityListId(Integer.parseInt(entity_id));
        schedule_entity_list.setEntityId(email_list_id);
        schedule_entity_list.setEntityType(ScheduledEntityType.Email.toString());
        schedule_entity_list.setIsRecurring(Boolean.TRUE);
        schedule_entity_list.setScheduleDesc(recurring_email_description);
        schedule_entity_list.setScheduleTime(time);
        schedule_entity_list.setScheduleTitle(recurring_email_title);
        schedule_entity_list.setStatus(TemplateStatus.no_template.toString());
        schedule_entity_list.setFkRecurringEmailId(null);
        CompanyMarketingProgram companyMarketingProgram = new CompanyMarketingProgram();
        companyMarketingProgram.setCompanyMarketingProgramId(Integer.parseInt(program_id));
        
        schedule_entity_list.setFkCompanyMarketingProgramId(companyMarketingProgram);
        schedule_entity_list.setDays(Integer.parseInt(days));
        schedule_entity_list.setTillDate(till_date);
        schedule_entity_list.setFkCompanyId(userProfile.getUser().getFkCompanyId());
        
        scheduledEntityListService.update(schedule_entity_list);
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
             UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser(); 
        Map<String, Object> requestBodyMap
                = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        SqlMethods sql_methods = new SqlMethods();

        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            
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
        
       ScheduledEntityList schedule_entity_list = scheduledEntityListService.getById(Integer.parseInt(entity_id));
        Integer email_list_id = (Integer)schedule_entity_list.getEntityId();

        schedule_entity_list.setScheduledEntityListId(Integer.parseInt(entity_id));
        schedule_entity_list.setEntityType(ScheduledEntityType.Email.toString());
        schedule_entity_list.setIsRecurring(Boolean.TRUE);
        schedule_entity_list.setScheduleDesc(recurring_email_description);
        schedule_entity_list.setScheduleTime(time);
        schedule_entity_list.setScheduleTitle(recurring_email_title);
        if (template_id.intValue() == 0){
            schedule_entity_list.setStatus(TemplateStatus.no_template.toString());
            schedule_entity_list.setFkRecurringEmailId(null);
        }else {
            schedule_entity_list.setStatus(TemplateStatus.template_saved.toString());
            RecurringEmailTemplate recurringEmailTemplate = new RecurringEmailTemplate();
            recurringEmailTemplate.setRecurringEmailTemplateId(template_id.intValue());
             schedule_entity_list.setFkRecurringEmailId(recurringEmailTemplate);
        }
        CompanyMarketingProgram companyMarketingProgram = new CompanyMarketingProgram();
        companyMarketingProgram.setCompanyMarketingProgramId(Integer.parseInt(program_id));
        
        schedule_entity_list.setFkCompanyMarketingProgramId(companyMarketingProgram);
        schedule_entity_list.setDays(Integer.parseInt(days));
        schedule_entity_list.setTillDate(till_date);
        schedule_entity_list.setFkCompanyId(userProfile.getUser().getFkCompanyId());
        
        ScheduledEmailList schedule_email_list = schedule_email_list_service.getById(email_list_id);
        schedule_email_list.setFkCompanyId(userProfile.getUser().getFkCompanyId());
      
        schedule_email_list.setEmailListName(emaillist);
        schedule_email_list.setBody(html_data);
        org.json.simple.JSONObject jsonFromAddress = (org.json.simple.JSONObject)getFromAddress(companyId);
        
        if (jsonFromAddress != null){
            schedule_email_list.setFromAddress(jsonFromAddress.get(IConstants.kEmailFromAddress).toString());
        }
        schedule_email_list.setFromName(from_name);
        schedule_email_list.setReplyToEmailAddress(reply_to_address);
        schedule_email_list.setSubject(subject);
        schedule_email_list.setToEmailAddresses(json_object.toString());
        schedule_email_list.setFkScheduledEntityListId(null);
        
        schedule_email_list_service.update(schedule_email_list);
        
        scheduledEntityListService.update(schedule_entity_list);
        
        return "true";
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while saving the email action in the table:", throwable);
        }
        return "true";
    }

    public org.json.simple.JSONObject getFromAddress(Integer companyId){
        try{

            SqlMethods sql_methods = new SqlMethods();

            org.json.simple.JSONObject json_company_preferences = sql_methods.getJSONCompanyPreferences(companyId);

            org.json.simple.JSONObject json_object_email_settings = (org.json.simple.JSONObject)json_company_preferences.get(IConstants.kEmailSettings);

            String from_address = (String)json_object_email_settings.get(IConstants.kEmailFromAddress);

            return json_object_email_settings;
        }catch (Throwable throwable){
            logger.log(Level.SEVERE,"Exception while getting the from address:", throwable);
        }
        return null;
    }
    
    //TODO Ilyas check this again
    @RequestMapping (value = "/getUserPreferences", method = RequestMethod.GET)
    public @ResponseBody String getUserPreferences(HttpServletRequest request,
            HttpServletResponse response)throws IOException{

        SqlMethods sql_methods = new SqlMethods();
        UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
        Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
        
        org.json.simple.JSONObject from_address = (org.json.simple.JSONObject)getFromAddress(companyId);

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
        ScheduledEntityList schedule_entity_list = scheduledEntityListService.getById(Integer.parseInt(entity_id));

            JSONObject json_entity_list = new JSONObject();

            json_entity_list.put("recurring_email_days", schedule_entity_list.getDays());
            json_entity_list.put("recurring_email_entity_id", schedule_entity_list.getEntityId());
            json_entity_list.put("recurring_email_entity_type", schedule_entity_list.getEntityType());
            json_entity_list.put("recurring_email_is_recurring", schedule_entity_list.getIsRecurring());
            json_entity_list.put("recurring_email_template_id", schedule_entity_list.getFkRecurringEmailId().getRecurringEmailTemplateId());
            json_entity_list.put("recurring_email_description", schedule_entity_list.getScheduleDesc());
            json_entity_list.put("recurring_email_time", schedule_entity_list.getScheduleTime().getTime());
            json_entity_list.put("recurring_email_title", schedule_entity_list.getScheduleTitle());
            json_entity_list.put("recurring_email_status", schedule_entity_list.getStatus());
            json_entity_list.put("recurring_email_user_marketing_program_id", schedule_entity_list.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId());
            json_entity_list.put("recurring_email_till_date", schedule_entity_list.getTillDate().getTime());
            
        if (schedule_entity_list.getEntityId().intValue() != 0){
            ScheduledEmailList schedule_email_list = schedule_email_list_service.getById(schedule_entity_list.getEntityId().intValue());
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
