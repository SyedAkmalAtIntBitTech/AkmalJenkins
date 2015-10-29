/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.controller.SqlMethods;
import com.google.gson.Gson;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingAction;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.model.TblMarketingProgram;
import com.intbit.marketing.model.TblMarketingProgramUsersLookup;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.service.MarketingActionService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            Integer i = 0;

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

        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, null, throwable);
        }
        return "true";
    }    
}
