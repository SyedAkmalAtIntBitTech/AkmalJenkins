/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;



import com.controller.IConstants;
import com.intbit.marketing.model.TblScheduledEmailList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingAction;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblRecuringEmailTemplate;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.MarketingActionService;
import com.intbit.marketing.service.MarketingProgramService;
import com.intbit.marketing.service.RecuringEmailTemplateService;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledEntityListService;
import com.intbit.marketing.service.ScheduledSocialpostListService;
import com.intbit.marketing.service.UserMarketingProgramService;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
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
     @Autowired
     private ScheduledEntityListService scheduledEntityListService;
     @Autowired
     private RecuringEmailTemplateService recuringEmailTemplateService;
             
     
      @RequestMapping(value="/allmarketingProgram", method = RequestMethod.GET)
      public @ResponseBody String getAllUserMarketingProgram() {
        Integer uMarketingId = 1;
        String entityType = "email";
        Integer marketingProgramId = 1;
        Boolean actionStatus;
        
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat TimeFormatter = new SimpleDateFormat("hh:mm");
        try { 
        
           List<TblScheduledEmailList> scheduledEmailList = scheduledEmailListService.getAllScheduledEmailListForUserMarketingProgram(uMarketingId, Boolean.TRUE, entityType);
                JSONArray scheduledEmailJsonArray= new JSONArray();
           for (TblScheduledEmailList scheduledEmailListObject : scheduledEmailList) {

               JSONObject jSONObject = new JSONObject();
                jSONObject.put("emailAutomationName", scheduledEmailListObject.getEmailListName());
                jSONObject.put("dateTime", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTime());
                jSONObject.put("programTemplateName", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTitle());
                jSONObject.put("status", scheduledEmailListObject.getTblScheduledEntityList().getStatus());

                scheduledEmailJsonArray.put(jSONObject);

          }
             List<TblScheduledEmailList> scheduledEmailListForRecuring = scheduledEmailListService.getAllScheduledEmailListForUserMarketingProgram(uMarketingId, Boolean.FALSE, entityType);
                JSONArray scheduledEmailAndSocailPostJsonForRecuringArray= new JSONArray();
            for (TblScheduledEmailList scheduledEmailListObject : scheduledEmailListForRecuring) {
                    TblScheduledEntityList scheduledEntityListObject = scheduledEntityListService.getById(scheduledEmailListObject.getTblScheduledEntityList().getId());
                    TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getById(scheduledEntityListObject.getTblUserMarketingProgram().getId());
                     Date eventDate = userMarketingProgram.getDateEvent();
                     String dateString = formatter.format(eventDate);
                     Date todayDate = Calendar.getInstance().getTime();
                     String eventDateString = formatter.format(todayDate);
                     if(dateString.equalsIgnoreCase(eventDateString)){
                         actionStatus = true;
                     }
                     else {
                         actionStatus =false;
                     }
                     Integer days = scheduledEmailListObject.getTblScheduledEntityList().getDays();
                      Calendar cal = Calendar.getInstance();
                       cal.setTime(eventDate);
                       cal.add(Calendar.DAY_OF_MONTH, -days);
                       String postDate =formatter.format(cal.getTime());
                       String postTime =TimeFormatter.format(cal.getTime());
                     TblRecuringEmailTemplate   recuringEmailTemplate = recuringEmailTemplateService.getById(scheduledEmailListObject.getTblScheduledEntityList().getRecuringEmailId());
                JSONObject jSONObject = new JSONObject();
//                jSONObject.put("emailAutomationName", scheduledEmailListObject.getEmailListName());
                jSONObject.put("scheduledEntityListId", scheduledEmailListObject.getTblScheduledEntityList().getId());
                jSONObject.put("eventDate", dateString);
                jSONObject.put("programTemplateName", scheduledEmailListObject.getTblScheduledEntityList().getScheduleTitle());
                jSONObject.put("status", scheduledEmailListObject.getTblScheduledEntityList().getStatus());
                jSONObject.put("actionStatus",actionStatus);
                jSONObject.put("postDate",postDate);
                jSONObject.put("postTime","Scheduled for "+postTime);
                jSONObject.put("actionType",scheduledEmailListObject.getTblScheduledEntityList().getEntityType());
                jSONObject.put("emailRecuringTemplateName",recuringEmailTemplate.getName());
                scheduledEmailAndSocailPostJsonForRecuringArray.put(jSONObject);
               System.out.println(scheduledEmailAndSocailPostJsonForRecuringArray);

               
          }
             List<TblScheduledSocialpostList> scheduledSocialpostList = scheduledSocialpostListService.getAllScheduledSocialpostListForUserMarketingProgram(uMarketingId, Boolean.FALSE, entityType);

           for (TblScheduledSocialpostList scheduledSocialpostListObject : scheduledSocialpostList) {
                Date eventDate = scheduledSocialpostListObject.getTblScheduledEntityList().getTblUserMarketingProgram().getDateEvent();
                     String dateString = formatter.format(eventDate);
                     Date todayDate = Calendar.getInstance().getTime();
                     String eventDateString = formatter.format(todayDate);
                     if(dateString.equalsIgnoreCase(eventDateString)){
                         actionStatus = true;
                     }
                     else {
                         actionStatus =false;
                     }
                     Integer days = scheduledSocialpostListObject.getTblScheduledEntityList().getDays();
                      Calendar cal = Calendar.getInstance();
                       cal.setTime(eventDate);
                       cal.add(Calendar.DAY_OF_MONTH, -days);
                       String postDate =formatter.format(cal.getTime());
                       String postTime =TimeFormatter.format(cal.getTime());
               TblRecuringEmailTemplate   recuringEmailTemplate = recuringEmailTemplateService.getById(scheduledSocialpostListObject.getTblScheduledEntityList().getRecuringEmailId());
               JSONObject jSONObject = new JSONObject();
               jSONObject.put("scheduledEntityListId", scheduledSocialpostListObject.getTblScheduledEntityList().getId());
               jSONObject.put("eventDate", dateString);
               jSONObject.put("programTemplateName", scheduledSocialpostListObject.getTblScheduledEntityList().getScheduleTitle());
               jSONObject.put("status", scheduledSocialpostListObject.getTblScheduledEntityList().getStatus());
               jSONObject.put("actionStatus",actionStatus);
               jSONObject.put("postDate",postDate);
               jSONObject.put("postTime","Scheduled for "+postTime);
               jSONObject.put("emailRecuringTemplateName",recuringEmailTemplate.getName());
               jSONObject.put("actionType",scheduledSocialpostListObject.getTblScheduledEntityList().getEntityType());
                scheduledEmailAndSocailPostJsonForRecuringArray.put(jSONObject);

          }
            TblUserMarketingProgram userMarketingProgram = userMarketingProgramService.getByUserMarketingProgramIdAndMarketingProgramId(uMarketingId, marketingProgramId);
            System.out.println(userMarketingProgram);
            JSONObject userMarketinProgramObject= new JSONObject();  
                Date dateEvent = userMarketingProgram.getDateEvent();
                String dateOfEvent = formatter.format(dateEvent);
                userMarketinProgramObject.put("programName", userMarketingProgram.getName());
                userMarketinProgramObject.put("noOfActions", "15");
                userMarketinProgramObject.put("linktodestination", userMarketingProgram.getUrl());
                userMarketinProgramObject.put("dateOfEvent", dateOfEvent);
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
 public Integer getCurrentUser(HttpServletRequest request)
      {
         sql_methods.session = request.getSession(true);
                Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
                return user_id;
      }
@RequestMapping(value="/setMarketingProgram", method = RequestMethod.POST)
public @ResponseBody String setUserMarketingProgram(HttpServletRequest request, 
        HttpServletResponse response) throws IOException, Throwable{
    try {  
    TblUserMarketingProgram addUserMarketingProgram = new TblUserMarketingProgram();
    TblUserLoginDetails userLoginDetails = new TblUserLoginDetails();
    TblMarketingProgram marketingProgram = new TblMarketingProgram();
    sql_methods.session = request.getSession(true);
          Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
    userLoginDetails.setId(user_id);

    Map<String, Object> requestBodyMap =
              AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
    addUserMarketingProgram.setName(requestBodyMap.get("program_name").toString());
    String target = requestBodyMap.get("program_date_time").toString();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date eventDate =  df.parse(target);  
    Integer marketingCategoryId = intValue((double)requestBodyMap.get("marketing_category_id"));
    Integer marketingProgramId = intValue((double)requestBodyMap.get("marketing_program_id"));
    marketingProgram.setId(marketingProgramId);
    addUserMarketingProgram.setId(0);
    addUserMarketingProgram.setDateEvent(eventDate);
    addUserMarketingProgram.setCreateDate(new Date());
    addUserMarketingProgram.setTblUserLoginDetails(userLoginDetails);
    addUserMarketingProgram.setStatus("Open");
    addUserMarketingProgram.setUrl(requestBodyMap.get("program_url").toString());
    addUserMarketingProgram.setTblMarketingProgram(marketingProgram);
    Integer userMarketingProgramId= userMarketingProgramService.save(addUserMarketingProgram);

    TblMarketingAction marketingAction = marketingActionService.getMarketingActionByMCategoryIdAndMProgramId(marketingCategoryId, marketingProgramId);
    String jsonString =marketingAction.getJsonTemplate();     
    JSONObject json = (JSONObject)new JSONParser().parse(jsonString);
    org.json.simple.JSONArray jSONArray = (org.json.simple.JSONArray)json.get(IConstants.kMarketingActionsKey);
        for(Integer i = 0; i< jSONArray.size(); i++){
            JSONObject jsonObject = (JSONObject)jSONArray.get(i);
            String tillDateString = jsonObject.get("tilldate").toString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
            Date tillDate = formatter.parse(tillDateString);
            String timeString = jsonObject.get("time").toString();
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
            Date time = formatterTime.parse(timeString);

            TblScheduledEntityList  scheduledEntityList = new TblScheduledEntityList();
            scheduledEntityList.setEntityType(jsonObject.get("type").toString());
            scheduledEntityList.setIsRecuring(Boolean.parseBoolean(jsonObject.get("is_recuring").toString()));
            scheduledEntityList.setScheduleDesc(jsonObject.get("description").toString());
            scheduledEntityList.setScheduleTime(time);
            scheduledEntityList.setTillDate(tillDate);
            scheduledEntityList.setDays(Integer.parseInt(jsonObject.get("days").toString()));
            scheduledEntityList.setStatus("No template");
            scheduledEntityList.setScheduleTitle(jsonObject.get("title").toString());
            scheduledEntityList.setUserId(user_id);
            TblUserMarketingProgram userMarketingProgram = new TblUserMarketingProgram();
            userMarketingProgram.setId(userMarketingProgramId);
            scheduledEntityList.setTblUserMarketingProgram(userMarketingProgram);
            scheduledEntityListService.save(scheduledEntityList);
        }
        return "success";
        }
        catch(Exception ex)
        {
            logger.log(Level.SEVERE, null, ex);
        }
        return "false";          
      }
      @RequestMapping(value="/listAllMarketingProgram", method = RequestMethod.GET)
      public @ResponseBody String listAllUserMarketingProgram(HttpServletRequest request, 
                HttpServletResponse response,@RequestParam("programType") String programType) throws Throwable {
          System.out.println("in listAllmarketingProgram");
          Integer user_id = getCurrentUser(request);
          List<TblUserMarketingProgram> userMarketingProgramList = userMarketingProgramService.getAllUserMarketingProgramByType(user_id,programType);
          JSONObject jsonObject= new JSONObject();
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
              if (programType.equalsIgnoreCase("Open")){
                 Integer noofrecords = scheduledEntityListService.getCurrentRecords(userMarketingProgramObject.getId());
                 json_obj.put("noofpostleft", noofrecords);
              }
              json_array.put(json_obj);
              
          }
          jsonObject.put("programs", json_array);
          return jsonObject.toString();
      }
       
}
