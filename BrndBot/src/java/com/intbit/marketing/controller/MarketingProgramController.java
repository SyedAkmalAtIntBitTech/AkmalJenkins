/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.MarketingProgramService;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author development
 */
@Controller
public class MarketingProgramController {
    Logger logger = Logger.getLogger(MarketingProgramController.class.getName());
    @Autowired
    private MarketingProgramService marketingprogramservice;
//    @RequestMapping(value="/getMarketingPrograms", method = RequestMethod.GET)
//      public @ResponseBody String getAllMarketingPrograms() {
//
//        try {
//
//            List<TblMarketingProgram> MarketingPrograms = marketingprogramservice.getAllTblMarketingProgram();
//
//            for (TblMarketingProgram marketing_program : MarketingPrograms) {
//
//                JSONObject json_marketing_programming = new JSONObject();
//                json_marketing_programming.put("scheduleDesc", scheduledEntityList.getScheduleDesc());
//                json_marketing_programming.put("entityType", scheduledEntityList.getEntityType());
//                json_marketing_programming.put("status", scheduledEntityList.getStatus());
//                json_marketing_programming.put("date", scheduledEntityList.getScheduleTime());
//
//
//                scheduledEntityJsonArray.put(json_marketing_programming);
//
//            }
//             JSONObject jsonObject= new JSONObject();
//             jsonObject.put("scheduledEntityData",scheduledEntityJsonArray);   
//             jsonObject.put("name", userMarketingProgram.getName());
//             jsonObject.put("dateEvent", userMarketingProgram.getDateEvent());
//             return jsonObject.toString();
//
//        }          
//
//        catch (Throwable throwable) {
//                logger.log(Level.SEVERE, null, throwable);
//
//        }
//        return null;
//
//    }
}
