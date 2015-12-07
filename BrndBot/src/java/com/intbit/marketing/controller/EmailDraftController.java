/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.marketing.model.EmailDraftModel;
import com.intbit.marketing.model.TblEmailDraft;
import com.intbit.marketing.service.EmailDraftService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import social.controller.ScheduleFacebookPost;

/**
 *
 * @author development
 */
@Controller
public class EmailDraftController {
    private static final Logger logger = Logger.getLogger(EmailDraftController.class.getName());
    SqlMethods sqlmethods = new SqlMethods();
    @Autowired
    private EmailDraftService emaildraftservice;
    
    @RequestMapping(value="/saveEmailDrafts", method= RequestMethod.POST)
    public @ResponseBody String saveEmailDrafts(HttpServletRequest request, 
            @RequestParam("bodyString") String bodyString,
            HttpServletResponse response)throws IOException, Throwable{
            try{
                
        sqlmethods.session = request.getSession(true);
            Integer user_id = (Integer) sqlmethods.session.getAttribute("UID");

            String emailSubject=(String)sqlmethods.session.getAttribute("email_subject");
            String subCategoryName=(String)sqlmethods.session.getAttribute("sub_category_name");
            String subCategoryId=(String)sqlmethods.session.getAttribute("sub_category_id");
            String categoryId=(String)sqlmethods.session.getAttribute("category_id");
            
            TblEmailDraft email_draft = new TblEmailDraft();
            email_draft.setId(0);
            Date current_date = new Date();
            
            email_draft.setDraftDate(current_date);

            EmailDraftModel emaildraftmodel = new EmailDraftModel();
            
            emaildraftmodel.setCategory_id(Integer.parseInt(categoryId));
            emaildraftmodel.setSub_category_id(Integer.parseInt(subCategoryId));
            emaildraftmodel.setEmail_subject(emailSubject);
            emaildraftmodel.setHtml_body_string(bodyString);
            
            String str_model = (String)AppConstants.GSON.toJson(emaildraftmodel);
            JSONParser json_parser = new JSONParser();
            JSONObject json_object = (JSONObject)json_parser.parse(str_model);
            email_draft.setUserId(user_id);
            email_draft.setDraftJson(json_object.toString());
            emaildraftservice.save(email_draft);
            return "true";
            }catch (Exception ex){
                Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        return "false";
    }
    @RequestMapping(value="/displayAllEmailDrafts", method = RequestMethod.GET)
    public @ResponseBody String displayAllEmailDrafts(HttpServletRequest request,
            HttpServletResponse response)throws ServletException, IOException, Throwable{
            JSONObject json_object_email_draft = new JSONObject();
        try{
            
            sqlmethods.session = request.getSession(true);
            Integer user_id = (Integer) sqlmethods.session.getAttribute("UID");
        
            List<TblEmailDraft> emaildraftlist = emaildraftservice.getAllEmailDrafts();
            
            JSONArray json_array_email_draft = new JSONArray();
            
            for (TblEmailDraft emaildraft: emaildraftlist){
                JSONObject json_email_draft = new JSONObject();
                
                json_email_draft.put("id", emaildraft.getId());
                json_email_draft.put("draftdate", emaildraft.getDraftDate().getTime());
                json_email_draft.put("editdate", emaildraft.getDraftDate().getTime());
                String json_string_data = (String)emaildraft.getDraftJson();
                JSONParser json_parser = new JSONParser();
                JSONObject json_draft_data = (JSONObject)json_parser.parse(json_string_data);
                json_email_draft.put("emailsubject", json_draft_data.get("email_subject"));
                json_email_draft.put("jsonemaildraftdata", json_draft_data.get("html_body_string"));
                
                json_array_email_draft.add(json_email_draft);
            }
            json_object_email_draft.put("emaildrafts", json_array_email_draft);
            System.out.println(json_object_email_draft);
            return json_object_email_draft.toString();
        }catch (Exception e){
                Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, e);
        }
        return json_object_email_draft.toJSONString();
    }
}
