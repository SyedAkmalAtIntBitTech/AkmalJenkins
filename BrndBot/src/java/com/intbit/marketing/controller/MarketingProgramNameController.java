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
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.MarketingActionService;
import com.intbit.marketing.service.MarketingProgramService;
import com.intbit.marketing.service.ScheduledEmailListService;
import com.intbit.marketing.service.ScheduledSocialpostListService;
import com.intbit.marketing.service.UserMarketingProgramService;
import com.intbit.util.ServletUtil;
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
import javax.servlet.ServletException;
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
 * @author intbit
 */
@Controller
public class MarketingProgramNameController {
     private static final Logger logger = Logger.getLogger(UserMarketingProgramController.class.getName());
     SqlMethods sql_methods = new SqlMethods();
     @Autowired
     private UserMarketingProgramService userMarketingProgramService;
     
     @RequestMapping(value="/getMarketingProgramName", method = RequestMethod.GET)
   public @ResponseBody String getMarketingProgramName()throws ServletException, IOException, Throwable {
//       List<TblMarketingCategory> marketingCategorysList = null;
   // String json = "test";
    JSONArray json_array = new JSONArray();
    JSONObject json_obj = new JSONObject();
    JSONObject jsonObject= new JSONObject();
    try {

        List<TblUserMarketingProgram> userMarketingProgramList = userMarketingProgramService.getAllUserMarketingProgram();

        JSONArray marketingCategoryJsonArray = new JSONArray();
        Integer i = 1;
        for (TblUserMarketingProgram userMarketingProgramObject : userMarketingProgramList) {
           org.json.JSONObject json_object = new org.json.JSONObject();
           json_object.put("id", i);
           json_object.put("user_program_id", userMarketingProgramObject.getId());
           json_object.put("name", userMarketingProgramObject.getName());
           marketingCategoryJsonArray.put(json_object);
           i++;
        }
        jsonObject.put("userProgramData",marketingCategoryJsonArray);  
        System.out.println(marketingCategoryJsonArray);
        return jsonObject.toString();
                                    
       }catch (Exception e){
           logger.log(Level.SEVERE, "Exception while getting the categories", e);
       }
       return jsonObject.toJSONString();
   }
   
    
}
