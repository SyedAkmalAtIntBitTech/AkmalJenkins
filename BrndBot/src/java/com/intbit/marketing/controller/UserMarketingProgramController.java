/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;


import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledSocialpostListService;
import com.intbit.marketing.service.UserMarketingProgramService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author intbit-6
 */
@Controller
public class UserMarketingProgramController {
     private static final Logger logger = Logger.getLogger(UserMarketingProgramController.class.getName());
     @Autowired
     private UserMarketingProgramService userMarketingProgramService;
     @Autowired
     private ScheduledEmailListService scheduledEmailListService;
     @Autowired
     private ScheduledSocialpostListService scheduledSocialpostListService;
      @RequestMapping(value="/allmarketingProgram", method = RequestMethod.GET)
      public @ResponseBody String getAllUserMarketingProgram() {
                Integer uMarketingId = 1;
                Integer entityType = 1;
		try {  
                   
                    
                   List<TblScheduledEmailList> scheduledEmailList = scheduledEmailListService.getAllScheduledEmailListForUserMarketingProgram(uMarketingId, Boolean.FALSE, entityType);
                        JSONArray scheduledEmailJsonArray= new JSONArray();
                   for (TblScheduledEmailList scheduledEmailListObject : scheduledEmailList) {
                   
                       JSONObject jSONObject = new JSONObject();
                        jSONObject.put("emailAutomationName", scheduledEmailListObject.getEmailListName());
                        jSONObject.put("dateTime", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTime());
                        jSONObject.put("programTemplateName", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTitle());
                        jSONObject.put("status", scheduledEmailListObject.getTblScheduledEntityList().getStatus());
                        
                        scheduledEmailJsonArray.put(jSONObject);
                       
                  }
                     List<TblScheduledEmailList> scheduledEmailListForRecuring = scheduledEmailListService.getAllScheduledEmailListForUserMarketingProgram(uMarketingId, Boolean.TRUE, entityType);
                        JSONArray scheduledEmailAndSocailPostJsonForRecuringArray= new JSONArray();
                   for (TblScheduledEmailList scheduledEmailListObject : scheduledEmailListForRecuring) {
                   
                       JSONObject jSONObject = new JSONObject();
                        jSONObject.put("emailAutomationName", scheduledEmailListObject.getEmailListName());
                        jSONObject.put("dateTime", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTime());
                        jSONObject.put("programTemplateName", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTitle());
                        jSONObject.put("status", scheduledEmailListObject.getTblScheduledEntityList().getStatus());
                       
                        scheduledEmailAndSocailPostJsonForRecuringArray.put(jSONObject);
                       System.out.println(scheduledEmailAndSocailPostJsonForRecuringArray);
                  }
                     List<TblScheduledSocialpostList> scheduledSocialpostList = scheduledSocialpostListService.getAllScheduledSocialpostListForUserMarketingProgram(uMarketingId, Boolean.TRUE, entityType);
                        
                   for (TblScheduledSocialpostList scheduledSocialpostListObject : scheduledSocialpostList) {
                   
                       JSONObject jSONObject = new JSONObject();
                       jSONObject.put("dateTime", scheduledSocialpostListObject.getTblScheduledEntityList().getScheduleTime());
                       jSONObject.put("programTemplateName", scheduledSocialpostListObject.getTblScheduledEntityList().getScheduleTitle());
                       jSONObject.put("status", scheduledSocialpostListObject.getTblScheduledEntityList().getStatus());
                       
                        scheduledEmailAndSocailPostJsonForRecuringArray.put(jSONObject);
                      
                  }
                    TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getById(uMarketingId);
                    System.out.println(userMarketingProgram);
                    JSONObject userMarketinProgramObject= new JSONObject();   
                        userMarketinProgramObject.put("programName", userMarketingProgram.getName());
                        userMarketinProgramObject.put("noOfActions", "15");
                        userMarketinProgramObject.put("linktodestination", "http://localhost:8080/BrndBot/allmarketingProgram.do");
                        userMarketinProgramObject.put("dateofevent", userMarketingProgram.getDateEvent());
                        userMarketinProgramObject.put("description", "this is description");
                   
                    JSONObject jSONObject = new JSONObject();
                        jSONObject.put("programdetails",userMarketinProgramObject );
                        jSONObject.put("emailautomation",scheduledEmailJsonArray );
                        jSONObject.put("programactions",scheduledEmailAndSocailPostJsonForRecuringArray );
                        System.out.println(jSONObject);
                        
                       return jSONObject.toString();
		}          
		
		catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			
		}
		return null;
    
            }
      
    
}
