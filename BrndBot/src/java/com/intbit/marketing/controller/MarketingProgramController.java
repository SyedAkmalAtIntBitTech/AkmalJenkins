/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblMarketingProgramUsersLookup;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.model.TblUserMarketingProgram;
import com.intbit.marketing.service.MarketingProgramService;
import com.intbit.marketing.service.MarketingProgramUsersService;

import java.io.BufferedReader;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
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
    @Autowired
    private MarketingProgramUsersService marketingprogramusers;
    @RequestMapping(value="/getMarketingPrograms", method = RequestMethod.GET)
      public @ResponseBody String getMarketingPrograms() {
        JSONArray json_array_marketing_program = new JSONArray();
        try {

            List<TblMarketingProgram> MarketingPrograms = marketingprogramservice.getAllTblMarketingProgram();
            
            for (TblMarketingProgram marketing_program : MarketingPrograms) {

                JSONObject json_marketing_programming = new JSONObject();
                json_marketing_programming.put("id", marketing_program.getId());
                json_marketing_programming.put("name", marketing_program.getName());
                json_marketing_programming.put("order", marketing_program.getProgramOrder());
                json_marketing_programming.put("htmldata", marketing_program.getHtmlData());
                json_marketing_programming.put("category_id", marketing_program.getTblMarketingCategory().getId());

                json_array_marketing_program.put(json_marketing_programming);
            }
        }          

        catch (Throwable throwable) {
                logger.log(Level.SEVERE, null, throwable);

        }
        return json_array_marketing_program.toString();
    }
    @RequestMapping(value="/setMarketingPrograms", method = RequestMethod.POST)
    public @ResponseBody String setMarketingPrograms(HttpServletRequest request, 
                 HttpServletResponse response)throws ServletException, IOException, Throwable {
        JSONArray json_array_marketing_program = new JSONArray();
        try {
            Map<String, Object> requestBodyMap =
                     AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String user_ids = requestBodyMap.get("user").toString();
            String program_name = requestBodyMap.get("program_name").toString();
            String program_order = requestBodyMap.get("program_order").toString();
            String html_data = requestBodyMap.get("html_data").toString();
            String category = requestBodyMap.get("category").toString();
            
            JSONParser json_parser = new JSONParser();

            org.json.simple.JSONArray users = (org.json.simple.JSONArray)json_parser.parse(user_ids.toString());
            Integer i = 0;
                
                TblMarketingProgram marketing_program = new TblMarketingProgram();
                marketing_program.setId(0);
                marketing_program.setName(program_name);
                marketing_program.setProgramOrder(Integer.parseInt(program_order));
                marketing_program.setHtmlData(html_data);
                TblMarketingCategory marketing_category = new TblMarketingCategory();
                marketing_category.setId(Integer.parseInt(category));
                marketing_program.setTblMarketingCategory(marketing_category);
                Integer program_id = marketingprogramservice.save(marketing_program);
                
                for (i = 0; i< users.size(); i++){

                    TblMarketingProgramUsersLookup marketing_program_users = new TblMarketingProgramUsersLookup();
                    marketing_program_users.setId(0);
                    marketing_program.setId(program_id);
                    marketing_program_users.setTblMarketingProgram(marketing_program);
                    TblUserLoginDetails user_login = new TblUserLoginDetails();
                    user_login.setId(Integer.parseInt(users.get(i).toString()));
                    marketing_program_users.setTblUserLoginDetails(user_login);
                    marketingprogramusers.save(marketing_program_users);
                }
        }          

        catch (Throwable throwable) {
                logger.log(Level.SEVERE, null, throwable);

        }
        return json_array_marketing_program.toString();
    }
    @RequestMapping(value="/deleteMarketingPrograms", method = RequestMethod.POST)
    public @ResponseBody String deleteMarketingPrograms(HttpServletRequest request, 
                 HttpServletResponse response)throws ServletException, IOException, Throwable {

        try {

            Map<String, Object> requestBodyMap =
                     AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            Double program_id = (Double)requestBodyMap.get("program_id");

            marketingprogramservice.delete(program_id.intValue());

        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception while deleting the categories", e);
        }
        return "true";
    }
      
}
