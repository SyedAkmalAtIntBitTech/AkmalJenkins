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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value="/setEmailDrafts", method= RequestMethod.POST)
    public @ResponseBody String setEmailDrafts(HttpServletRequest request, 
            @RequestParam("bodyString") String bodyString,
            HttpServletResponse response)throws IOException, Throwable{
            try{
                
        sqlmethods.session = request.getSession(true);
            Integer user_id = (Integer) sqlmethods.session.getAttribute("UID");

            String emailSubject=(String)sqlmethods.session.getAttribute("email_subject");
            String subCategoryName=(String)sqlmethods.session.getAttribute("sub_category_name");
            String subCategoryId=(String)sqlmethods.session.getAttribute("sub_category_id");
            String categoryId=(String)sqlmethods.session.getAttribute("category_id");
            String emailAddresses=(String)sqlmethods.session.getAttribute("email_addresses");
            String emailList=(String)sqlmethods.session.getAttribute("email_list");
            
            TblEmailDraft email_draft = new TblEmailDraft();
            email_draft.setId(0);
            Date current_date = new Date();
            
            email_draft.setDraftDate(current_date);

            EmailDraftModel emaildraftmodel = new EmailDraftModel();
            
            emaildraftmodel.setCategory_id(Integer.parseInt(categoryId));
            emaildraftmodel.setSub_category_id(Integer.parseInt(subCategoryId));
            emaildraftmodel.setEmail_addresses(emailAddresses);
            emaildraftmodel.setEmail_list_name(emailList);
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
}
