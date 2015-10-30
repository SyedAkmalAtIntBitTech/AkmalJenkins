/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblRecuringEmailTemplate;
import com.intbit.marketing.service.RecuringEmailTemplateService;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
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
    String return_response = "false";
    
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
    
}
