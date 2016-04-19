/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;


import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblMarketingCategory;
import com.intbit.marketing.model.TblMarketingCategoryUsersLookup;
import com.intbit.marketing.model.TblOrganization;
import com.intbit.marketing.model.TblUserLoginDetails;
import com.intbit.marketing.service.MarketingCategoryService;
import com.intbit.marketing.service.MarketingCategoryUsersService;
import com.intbit.util.ServletUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author intbit-6
 */

//delete whole controller
@Controller
public class MarketingCategoryController {
    private static final Logger logger = Logger.getLogger(MarketingCategoryController.class.getName());
    SqlMethods sql_methods = new SqlMethods();
    @Autowired
    private MarketingCategoryService marketingCategoryService;
    @Autowired
    private MarketingCategoryUsersService marketingCategoryUsersService;
    
   
   
   //user - Replaced with getCompanyMarketingCategories.do
   @RequestMapping(value="/displaymarketingCategory", method = RequestMethod.GET)
   public @ResponseBody String getMarketingCategoriesForUser(HttpServletRequest request, 
                HttpServletResponse response)throws ServletException, IOException, Throwable {
//       List<TblMarketingCategory> marketingCategorysList = null;
       sql_methods.session = request.getSession(true);
                Integer user_id = (Integer) sql_methods.session.getAttribute("UID");
                
       String json = "test";
       JSONArray json_array = new JSONArray();
       JSONObject json_obj = new JSONObject();
       JSONObject jsonObject= new JSONObject();
       try {

                List<TblMarketingCategoryUsersLookup> marketingCategorysList = marketingCategoryService.getAllMarketingCategoryForUser(user_id);
                     
                org.json.JSONArray marketingCategoryJsonArray = new org.json.JSONArray();
                Integer i = 1;
                for (TblMarketingCategoryUsersLookup marketingCategoryobject : marketingCategorysList) {
                   JSONObject json_object = new JSONObject();
                   json_object.put("id", i);
                   json_object.put("category_id", marketingCategoryobject.getTblMarketingCategory().getId());
                   json_object.put("name", marketingCategoryobject.getTblMarketingCategory().getName());
                   json_object.put("order", marketingCategoryobject.getTblMarketingCategory().getCategoryOrder().toString());
                   json_object.put("image", ServletUtil.bytesTo64(marketingCategoryobject.getTblMarketingCategory().getImage()));
                   json_object.put("organization_id", marketingCategoryobject.getTblMarketingCategory().getTblOrganization().getId());
                   marketingCategoryJsonArray.put(json_object);
                   i++;
                }
                   
                    jsonObject.put("marketingData",marketingCategoryJsonArray);  
                    System.out.println(marketingCategoryJsonArray);
                    return jsonObject.toString();
                                    
       }catch (Exception e){
           logger.log(Level.SEVERE, "Exception while getting the categories", e);
       }
       return jsonObject.toJSONString();
   }
   
   //admin delete
   @RequestMapping(value="/deleteMarketingCategory", method = RequestMethod.POST)
   public @ResponseBody String deleteMarketingCategories(HttpServletRequest request, 
                HttpServletResponse response)throws ServletException, IOException, Throwable {
       String status = "false";
       try {

           Map<String, Object> requestBodyMap =
                    AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
           Double category_id = (Double)requestBodyMap.get("category_id");
           
           marketingCategoryService.delete(category_id.intValue());
           status = "true";
       }catch (Exception e){
           logger.log(Level.SEVERE, "Exception while deleting the categories", e);
       }
       return status;
   }   
   
   

}
