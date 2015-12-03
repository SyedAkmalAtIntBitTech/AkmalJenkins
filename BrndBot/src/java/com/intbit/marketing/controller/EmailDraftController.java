/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbit.marketing.controller;

import com.controller.SqlMethods;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblEmailDraft;
import com.intbit.marketing.service.EmailDraftService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
            @RequestParam("sessionValue") String sessionValue,
            @RequestParam("sessionKey") String sessionKey,
            @RequestParam("sessionIframeKey") String sessionIframeKey,
            @RequestParam("sessionIframevalue") String sessionIframevalue,
            HttpServletResponse response)throws IOException, Throwable{
        
        sqlmethods.session = request.getSession(true);
            Integer user_id = (Integer) sqlmethods.session.getAttribute("UID");

            sqlmethods.session = request.getSession(true);

            sqlmethods.session.setAttribute(sessionKey, sessionValue);
            sqlmethods.session.setAttribute(sessionIframeKey, sessionIframevalue); 
            String emailSubject=(String)sqlmethods.session.getAttribute("email_subject");
            String subCategoryName=(String)sqlmethods.session.getAttribute("sub_category_name");
            String subCategoryId=(String)sqlmethods.session.getAttribute("sub_category_id");
            String categoryId=(String)sqlmethods.session.getAttribute("category_id");
            String emailAddresses=(String)sqlmethods.session.getAttribute("email_addresses");
            String emailList=(String)sqlmethods.session.getAttribute("email_list");
            
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);

            TblEmailDraft email_draft = new TblEmailDraft();

            email_draft.setId(user_id);
            email_draft.setCategory(categoryId);
            email_draft.setEmailAddresses(emailAddresses);
            
//            email_draft.setEmailId(emailList);
            email_draft.setEmailSubject(emailSubject);
            email_draft.setSessionIframeKey(sessionIframeKey);
            email_draft.setSessionIframeValue(sessionIframevalue);
            email_draft.setSessionKey(sessionKey);
            email_draft.setSessionValue(sessionValue);
            email_draft.setSubCategory(subCategoryId);
            email_draft.setSubCategoryName(subCategoryName);
            
            emaildraftservice.save(email_draft);
        return "false";
    }
}
