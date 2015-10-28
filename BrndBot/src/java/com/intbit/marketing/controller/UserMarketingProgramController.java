/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;


import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.ScheduledEntityListService;
import com.intbit.marketing.service.UserMarketingProgramService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author intbit-6
 */
@Controller
public class UserMarketingProgramController {
     private static final Logger logger = Logger.getLogger(MarketingCategoryController.class.getName());
     SqlMethods sql_methods = new SqlMethods();
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
    @RequestMapping(value="/setMarketingProgram", method = RequestMethod.POST)
      public @ResponseBody String setUserMarketingProgram(HttpServletRequest request, 
                HttpServletResponse response) throws IOException, Throwable{
          TblUserMarketingProgram addUserMarketingProgram = new TblUserMarketingProgram();
          TblUserLoginDetails userLoginDetails = new TblUserLoginDetails();
          sql_methods.session = request.getSession();
                Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
          userLoginDetails.setId(user_id);
          Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
          addUserMarketingProgram.setName(requestBodyMap.get("program_name").toString());
          double itemDouble = (double)requestBodyMap.get("program_date_time");
long itemLong = (long) (itemDouble * 1000);
          Date eventDate = new Date(itemLong);
          
          addUserMarketingProgram.setDateEvent(eventDate);
          addUserMarketingProgram.setCreateDate(new Date());
          addUserMarketingProgram.setTblUserLoginDetails(userLoginDetails);
          userMarketingProgramService.save(addUserMarketingProgram);
          return "success";
      }
}
