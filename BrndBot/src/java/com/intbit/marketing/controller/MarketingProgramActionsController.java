/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.controller.ApplicationContextListener;
import com.controller.IConstants;
import com.controller.SqlMethods;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingAction;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.service.MarketingActionService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
public class MarketingProgramActionsController {
    private static final Logger logger = Logger.getLogger(MarketingCategoryController.class.getName());
    SqlMethods sql_methods = new SqlMethods();

    @Autowired
    MarketingActionService marketing_action_service;
    @RequestMapping(value = "/setMarketingProgramActions", method = RequestMethod.POST)
    public @ResponseBody String setMarketingProgramActions(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, Throwable {
        String returnResponse = "true";
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String category_id = requestBodyMap.get("category").toString();
            String programs_id = requestBodyMap.get("programs").toString();
            Map marketing_actions = (Map)requestBodyMap.get("marketing_actions");

            Gson gson = new Gson();
            String data = gson.toJson(marketing_actions);

            JSONParser json_parser = new JSONParser();

            org.json.simple.JSONObject actions = (org.json.simple.JSONObject) json_parser.parse(data);
            
            TblMarketingAction marketing_action_saved = marketing_action_service.getMarketingActionByMCategoryIdAndMProgramId(
                    Integer.parseInt(category_id), Integer.parseInt(programs_id));
            
            if (marketing_action_saved == null){

                TblMarketingAction marketing_action = new TblMarketingAction();
                marketing_action.setId(0);
                TblMarketingCategory marketing_category = new TblMarketingCategory();
                marketing_category.setId(Integer.parseInt(category_id));
                marketing_action.setTblMarketingCategory(marketing_category);

                TblMarketingProgram marketing_program = new TblMarketingProgram();
                marketing_program.setId(Integer.parseInt(programs_id));

                marketing_action.setTblMarketingProgram(marketing_program);
                marketing_action.setJsonTemplate(actions.toString());
                Integer program_action = marketing_action_service.save(marketing_action);
            
            }else {    
                String saved_actions = marketing_action_saved.getJsonTemplate();

                org.json.simple.JSONObject saved_marketing_actions_json = (org.json.simple.JSONObject) json_parser.parse(saved_actions);
                JSONArray saved_marketing_actions_json_array = (JSONArray)saved_marketing_actions_json.get(IConstants.kMarketingActionsKey);
                JSONArray json_array_from_UI = (JSONArray)actions.get(IConstants.kMarketingActionsKey);
                
                saved_marketing_actions_json_array.addAll(json_array_from_UI);
                
                actions.put(IConstants.kMarketingActionsKey, saved_marketing_actions_json_array);
                TblMarketingAction marketing_action = new TblMarketingAction();
                marketing_action.setId(marketing_action_saved.getId());
                
                TblMarketingCategory marketing_category = new TblMarketingCategory();
                marketing_category.setId(Integer.parseInt(category_id));
                marketing_action.setTblMarketingCategory(marketing_category);

                TblMarketingProgram marketing_program = new TblMarketingProgram();
                marketing_program.setId(Integer.parseInt(programs_id));

                marketing_action.setTblMarketingProgram(marketing_program);
                marketing_action.setJsonTemplate(actions.toString());
                marketing_action_service.update(marketing_action);
            }

        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
        }
        return "true";
    }
    
    @RequestMapping(value = "/getAllMarketingProgramActions", method = RequestMethod.GET)
    public @ResponseBody String getAllMarketingProgramActions() 
            throws ServletException, IOException, Throwable {

        JSONArray json_array_marketing_actions = new JSONArray();
        try {
            
            List<TblMarketingAction> MarketingActions = marketing_action_service.getAllMarketingAction();
            Integer i = 1;
            for(TblMarketingAction marketing_action: MarketingActions){
                JSONObject json_object = new JSONObject();
                
                json_object.put("id", i);
                json_object.put("action_id", marketing_action.getId());

                JSONParser json_parser = new JSONParser();

                org.json.simple.JSONObject actions = (org.json.simple.JSONObject) json_parser.parse(marketing_action.getJsonTemplate());
                json_object.put("json_data", actions.get(IConstants.kMarketingActionsKey));
                json_object.put("program_name", marketing_action.getTblMarketingProgram().getName());
                json_object.put("category_name", marketing_action.getTblMarketingCategory().getName());
                json_array_marketing_actions.add(json_object);
                i++;
            }

        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
        }
        return json_array_marketing_actions.toString();
    }
    
    @RequestMapping(value = "/deleteMarketingProgramAction", method = RequestMethod.POST)
    public @ResponseBody String deleteMarketingProgramAction(HttpServletRequest request,
            HttpServletResponse response)throws ServletException, IOException, Throwable{
        String return_response = "false";
        try{
        Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
        Double program_action_id = (Double)requestBodyMap.get("action_id");

        marketing_action_service.delete(program_action_id.intValue());
        }catch (Throwable throwable){
            logger.log(Level.SEVERE, null, throwable);
        }
        return return_response;
        
    }
    
    @RequestMapping(value = "/approveTwitterAction", method = RequestMethod.POST)
    public @ResponseBody String approveTwitterAction(HttpServletRequest request,
            HttpServletResponse response)throws ServletException, IOException, Throwable{
        String return_response = "false";
        //Calling this restart to refresh the scheduler. 
        //Make sure this needs to be called for approve/unapprove/removed saved template or delete action.
        ApplicationContextListener.refreshTwitterScheduler();
        return return_response;
        
    }
    
}
