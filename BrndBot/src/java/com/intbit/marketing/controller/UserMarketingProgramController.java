/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;



import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingAction;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.MarketingActionService;
import com.intbit.marketing.service.MarketingProgramService;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledSocialpostListService;
import com.intbit.marketing.service.UserMarketingProgramService;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
     
      @RequestMapping(value="/allmarketingProgram", method = RequestMethod.GET)
      public @ResponseBody String getAllUserMarketingProgram() {
                Integer uMarketingId = 1;
                String entityType = "1";
                Integer marketingProgramId = 1;
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
                    TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getByUserMarketingProgramIdAndMarketingProgramId(uMarketingId, marketingProgramId);
                    System.out.println(userMarketingProgram);
                    JSONObject userMarketinProgramObject= new JSONObject();   
                        userMarketinProgramObject.put("programName", userMarketingProgram.getName());
                        userMarketinProgramObject.put("noOfActions", "15");
                        userMarketinProgramObject.put("linktodestination", userMarketingProgram.getUrl());
                        userMarketinProgramObject.put("dateofevent", userMarketingProgram.getDateEvent());
                        userMarketinProgramObject.put("description", userMarketingProgram.getTblMarketingProgram().getHtmlData());
                   
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

    @RequestMapping(value="/setMarketingProgram", method = RequestMethod.POST)
      public @ResponseBody String setUserMarketingProgram(HttpServletRequest request, 
                HttpServletResponse response) throws IOException, Throwable{
          try {  
          TblUserMarketingProgram addUserMarketingProgram = new TblUserMarketingProgram();
          TblUserLoginDetails userLoginDetails = new TblUserLoginDetails();
          TblMarketingProgram marketingProgram = new TblMarketingProgram();
          sql_methods.session = request.getSession();
                Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
          userLoginDetails.setId(user_id);
          
          
          Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
          addUserMarketingProgram.setName(requestBodyMap.get("program_name").toString());
          String target = requestBodyMap.get("program_date_time").toString();
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          Date eventDate =  df.parse(target);  
          Integer marketingProgramId = intValue((double)requestBodyMap.get("marketing_category_id"));
          Integer marketingCategoryId = intValue((double)requestBodyMap.get("marketing_program_id"));
          marketingProgram.setId(marketingProgramId);
          addUserMarketingProgram.setId(0);
          addUserMarketingProgram.setDateEvent(eventDate);
          addUserMarketingProgram.setCreateDate(new Date());
          addUserMarketingProgram.setTblUserLoginDetails(userLoginDetails);
          addUserMarketingProgram.setStatus("Open");
          addUserMarketingProgram.setUrl(requestBodyMap.get("program_url").toString());
          addUserMarketingProgram.setTblMarketingProgram(marketingProgram);
           userMarketingProgramService.save(addUserMarketingProgram);
          
           
           
             TblScheduledEntityList scheduledEntityList = new  TblScheduledEntityList();
             
              
          return "success";
          }
          catch(Exception ex)
          {
              logger.log(Level.SEVERE, null, ex.getMessage());
          }
          return "false";
      }
  
       

}
