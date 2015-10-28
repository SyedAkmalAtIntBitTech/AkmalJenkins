/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;


import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.ScheduledEntityListService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author intbit-6
 */
@Controller
public class UserMarketingProgramController {
     private static final Logger logger = Logger.getLogger(MarketingCategoryController.class.getName());
     @Autowired
     private UserMarketingProgramService userMarketingProgramService;
     @Autowired
     private ScheduledEntityListService scheduledEntityListService;
      @RequestMapping(value="/allmarketingProgram", method = RequestMethod.GET)
      public @ResponseBody String getAllUserMarketingProgram(@RequestParam("userMarketingId") Integer uMarketingId) {

		try {
                   
                    TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getById(uMarketingId);
                    List<TblScheduledEntityList> scheduledEntityLists = scheduledEntityListService.getAllUserScheduledEmailList(uMarketingId, Boolean.TRUE, "email");
                         JSONArray scheduledEntityJsonArray= new JSONArray();
                    for (TblScheduledEntityList scheduledEntityList : scheduledEntityLists) {
                     
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("scheduleDesc", scheduledEntityList.getScheduleDesc());
                        jSONObject.put("entityType", scheduledEntityList.getEntityType());
                        jSONObject.put("status", scheduledEntityList.getStatus());
                        jSONObject.put("date", scheduledEntityList.getScheduleTime());
                       
         
                        scheduledEntityJsonArray.put(jSONObject);
                        
                    }
                     JSONObject jsonObject= new JSONObject();
                     jsonObject.put("scheduledEntityData",scheduledEntityJsonArray);   
                     jsonObject.put("name", userMarketingProgram.getName());
                     jsonObject.put("dateEvent", userMarketingProgram.getDateEvent());
                     return jsonObject.toString();
             
		}          
		
		catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			
		}
		return null;
    
            }
    
}
