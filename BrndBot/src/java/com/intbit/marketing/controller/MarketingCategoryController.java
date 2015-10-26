/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.service.MarketingCategoryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import org.json.JSONArray;


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
public class MarketingCategoryController {
    private static final Logger logger = Logger.getLogger(MarketingCategoryController.class.getName());
    @Autowired
    private MarketingCategoryService marketingCategoryService;
  @RequestMapping(value="/allmarketingCategory", method = RequestMethod.GET)
	
    public @ResponseBody String getAllMarketingCategory() {

		try {
                  
			List<TblMarketingCategory> marketingCategorysList = marketingCategoryService.getAllMarketingCategory();
			
                      
                        JSONArray marketingCategoryJsonArray = new JSONArray();
                       for (TblMarketingCategory marketingCategoryobject : marketingCategorysList) {
                         JSONArray jsonArray=new JSONArray();
                        
                          jsonArray.put(marketingCategoryobject.getName());
                          jsonArray.put(marketingCategoryobject.getCategoryOrder().toString());
                        marketingCategoryJsonArray.put(jsonArray);
                    }
                    JSONObject jsonObject= new JSONObject();
                    
                     jsonObject.put("marketingData",marketingCategoryJsonArray);   
                     System.out.println(marketingCategoryJsonArray);
                     return jsonObject.toString();
             
		}          
		
		catch (Throwable throwable) {
			logger.log(Level.SEVERE, null, throwable);
			
		}
		return null;
    
}
}
