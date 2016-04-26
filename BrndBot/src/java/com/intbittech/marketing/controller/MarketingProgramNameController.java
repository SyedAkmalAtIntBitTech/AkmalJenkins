/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.controller;

import com.controller.SqlMethods;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intbittech.marketing.service.CompanyMarketingProgramService;
import com.intbittech.model.CompanyMarketingProgram;

/**
 *
 * @author ajit
 */
@Controller
public class MarketingProgramNameController {

    private static final Logger logger = Logger.getLogger(MarketingProgramNameController.class.getName());
    SqlMethods sql_methods = new SqlMethods();
    @Autowired
    private CompanyMarketingProgramService companyMarketingProgramService;

    @RequestMapping(value = "/getMarketingProgramName", method = RequestMethod.GET)
    public @ResponseBody
    String getMarketingProgramName() throws ServletException, IOException, Throwable {

        JSONObject jsonObject = new JSONObject();
        try {

            List<CompanyMarketingProgram> companyMarketingProgramList = companyMarketingProgramService.getAllCompanyMarketingProgram();

            JSONArray marketingCategoryJsonArray = new JSONArray();
            Integer i = 1;
            for (CompanyMarketingProgram companyMarketingProgramObject : companyMarketingProgramList) {
                org.json.JSONObject json_object = new org.json.JSONObject();
                json_object.put("id", i);
                json_object.put("user_program_id", companyMarketingProgramObject.getCompanyMarketingProgramId());
                json_object.put("name", companyMarketingProgramObject.getCompanyMarketingProgramName());
                json_object.put("event_date", companyMarketingProgramObject.getDateEvent());
                marketingCategoryJsonArray.put(json_object);
                i++;
            }
            jsonObject.put("userProgramData", marketingCategoryJsonArray);
            System.out.println(marketingCategoryJsonArray);
            return jsonObject.toString();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception while getting the categories", e);
        }
        return jsonObject.toJSONString();
    }

}
