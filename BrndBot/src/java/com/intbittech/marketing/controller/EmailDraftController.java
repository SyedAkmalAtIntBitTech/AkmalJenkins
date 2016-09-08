/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.marketing.controller;

import com.controller.SqlMethods;
import com.intbittech.AppConstants;
import com.intbittech.marketing.model.EmailDraftModel;
import com.intbittech.marketing.service.EmailDraftService;
import com.intbittech.model.Company;
import com.intbittech.model.EmailDraft;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.UserProfile;
import com.intbittech.utility.UserSessionUtil;
import com.intbittech.utility.Utility;
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

    @RequestMapping(value = "/saveEmailDrafts", method = RequestMethod.POST)
    public @ResponseBody
    String saveEmailDrafts(HttpServletRequest request,
                    HttpServletResponse response) throws IOException, Throwable {
        try {

            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            UserCompanyIds userCompanyIds = Utility.getUserCompanyIdsFromRequestBodyMap(requestBodyMap);
            
            String emailSubject = (String) requestBodyMap.get("emailSubject");
            String emailPreHeader = (String) requestBodyMap.get("emailPreHeader");
            String bodyString = (String) requestBodyMap.get("bodyString");
            Integer categoryId = Integer.parseInt((String)requestBodyMap.get("categoryId"));
            Integer subCategoryId = Integer.parseInt((String) requestBodyMap.get("subCategoryId"));
            String lookupIdStr=(String)requestBodyMap.get("lookupId");
            Integer blockAddedCount = Integer.parseInt((String)requestBodyMap.get("blockAddedCount"));
            Integer lookupId=0;
            if((!lookupIdStr.equals("null")) && (!lookupIdStr.equals("")))
            {
                lookupId = Integer.parseInt(lookupIdStr);
            }    
            String mindbodyIdStr=(String) requestBodyMap.get("mindbodyData");
            Integer mindbodyId=0;
            if(!mindbodyIdStr.equals("null"))
            {
                mindbodyId = Integer.parseInt(mindbodyIdStr);
            }  
            EmailDraft email_draft = new EmailDraft();
            Date current_date = new Date();

            email_draft.setDraftDate(current_date);
            email_draft.setEditDate(current_date);

            EmailDraftModel emaildraftmodel = new EmailDraftModel();

            emaildraftmodel.setEmailsubject(emailSubject);
            emaildraftmodel.setPreheader(emailPreHeader);
            emaildraftmodel.setLookupid(lookupId);
            emaildraftmodel.setMindbodyid(mindbodyId);
            emaildraftmodel.setCategoryid(categoryId);
            emaildraftmodel.setSubcategoryid(subCategoryId);
            emaildraftmodel.setHtmlbodystring(bodyString);
            emaildraftmodel.setBlockAddedCount(blockAddedCount);

            String str_model = (String) AppConstants.GSON.toJson(emaildraftmodel);
            JSONParser json_parser = new JSONParser();
            JSONObject json_object = (JSONObject) json_parser.parse(str_model);
            Company company = new Company();
            company.setCompanyId(userCompanyIds.getCompanyId());
            email_draft.setFkCompanyId(company);
            email_draft.setDraftJson(json_object.toString());
            Integer draftID = emaildraftservice.save(email_draft);
            return draftID.toString();
        } catch (Throwable ex) {
            Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "0";
    }

    @RequestMapping(value = "/updateEmailDraft", method = RequestMethod.POST)
    public @ResponseBody
    String updateEmailDraft(HttpServletRequest request,
                    HttpServletResponse response) throws IOException, Throwable  {
        JSONObject json_object_email_draft = new JSONObject();
        try {

            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            
            String emailSubject = (String) requestBodyMap.get("emailSubject");
            String emailPreHeader = (String) requestBodyMap.get("emailPreHeader");
            Integer categoryId = Integer.parseInt((String)requestBodyMap.get("categoryId"));
            Integer subCategoryId = Integer.parseInt((String) requestBodyMap.get("subCategoryId"));
            Integer lookupId = Integer.parseInt((String)requestBodyMap.get("lookupId"));
            Integer mindbodyId = Integer.parseInt((String) requestBodyMap.get("mindbodyData"));
            Integer draftId = Integer.parseInt((String) requestBodyMap.get("draftId"));
            String bodyString =(String) requestBodyMap.get("bodyString");
            Integer blockAddedCount = Integer.parseInt((String)requestBodyMap.get("blockAddedCount"));
            EmailDraft emaildraft = emaildraftservice.getById(draftId);

            EmailDraftModel emaildraftmodel = new EmailDraftModel();

            emaildraftmodel.setEmailsubject(emailSubject);
            emaildraftmodel.setPreheader(emailPreHeader);
            emaildraftmodel.setHtmlbodystring(bodyString);
            emaildraftmodel.setCategoryid(categoryId);
            emaildraftmodel.setSubcategoryid(subCategoryId);
            emaildraftmodel.setLookupid(lookupId);
            emaildraftmodel.setMindbodyid(mindbodyId);
            emaildraftmodel.setBlockAddedCount(blockAddedCount);

            Date edit_date = new Date();

            emaildraft.setEditDate(edit_date);
            String str_model = (String) AppConstants.GSON.toJson(emaildraftmodel);
            JSONParser json_parser = new JSONParser();
            JSONObject json_object = (JSONObject) json_parser.parse(str_model);

            emaildraft.setDraftJson(json_object.toString());

            emaildraftservice.update(emaildraft);
            return "true";
        } catch (Exception e) {
            Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "false";
    }

    @RequestMapping(value = "/displayAllEmailDrafts", method = RequestMethod.GET)
    public @ResponseBody
    String displayAllEmailDrafts(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("companyId") Integer companyId) throws ServletException, IOException, Throwable {
        JSONObject json_object_email_draft = new JSONObject();
        try {

            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            List<EmailDraft> emaildraftlist = emaildraftservice.getAllEmailDrafts(companyId);

            JSONArray json_array_email_draft = new JSONArray();
            
            if (emaildraftlist.size() != 0){
                for (EmailDraft emaildraft : emaildraftlist) {
                    JSONObject json_email_draft = new JSONObject();

                    json_email_draft.put("id", emaildraft.getEmailDraftId());
                    json_email_draft.put("draftdate", emaildraft.getDraftDate().getTime());
                    json_email_draft.put("editdate", emaildraft.getEditDate().getTime());
                    String json_string_data = (String) emaildraft.getDraftJson();
                    JSONParser json_parser = new JSONParser();
                    JSONObject json_draft_data = (JSONObject) json_parser.parse(json_string_data);
                    json_email_draft.put("emailsubject", json_draft_data.get("emailsubject"));
                    json_email_draft.put("jsonemaildraftdata", json_draft_data.get("htmlbodystring"));
                    json_email_draft.put("subcategoryid", json_draft_data.get("subcategoryid"));
                    json_email_draft.put("categoryid", json_draft_data.get("categoryid"));
                    json_email_draft.put("subcategoryname", json_draft_data.get("subcategoryname")); 
                    json_email_draft.put("lookupid", json_draft_data.get("lookupid")); 
                    json_email_draft.put("mindbodyid", json_draft_data.get("mindbodyid")); 
                    json_array_email_draft.add(json_email_draft);
                }
                json_object_email_draft.put("emaildrafts", json_array_email_draft);
            }else {
                json_object_email_draft.put("nodrafts", "yes");
            }
            System.out.println(json_object_email_draft);
            return json_object_email_draft.toString();
        } catch (Exception e) {
            Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, e);
        }
        return json_object_email_draft.toJSONString();
    }

//    @RequestMapping(value = "/saveEmailDraftSessionValues", method = RequestMethod.POST)
//    public @ResponseBody
//    String saveEmailDraftSessionValues(HttpServletRequest request,
//            HttpServletResponse response) throws IOException {
//        try {
//            Map<String, Object> requestBodyMap
//                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
//
//            sqlmethods.session.setAttribute("email_subject", requestBodyMap.get("email_subject"));
//            sqlmethods.session.setAttribute("category_id", requestBodyMap.get("category_id"));
//            sqlmethods.session.setAttribute("sub_category_id", requestBodyMap.get("sub_category_id"));
//            sqlmethods.session.setAttribute("sub_category_name", requestBodyMap.get("sub_category_name"));
//
//            return "true";
//        } catch (Exception e) {
//            Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return "false";
//    }

    @RequestMapping(value = "/getEmailDraft", method = RequestMethod.GET)
    public @ResponseBody
    String getEmailDraft(@RequestParam("draftid") Integer draftid, HttpServletRequest request,
            HttpServletResponse response) throws Throwable {
        JSONObject json_object = new JSONObject();

        try {

            EmailDraft emaildraft = emaildraftservice.getById(draftid);

            json_object.put("id", emaildraft.getEmailDraftId());
            json_object.put("draftdate", emaildraft.getDraftDate().getTime());
            json_object.put("editdate", emaildraft.getEditDate().getTime());

            String json_string_data = (String) emaildraft.getDraftJson();
            JSONParser json_parser = new JSONParser();
            JSONObject json_draft_data = (JSONObject) json_parser.parse(json_string_data);
            json_object.put("emailsubject", json_draft_data.get("emailsubject"));
            json_object.put("htmlbody", json_draft_data.get("htmlbodystring"));
            json_object.put("blockAddedCount", json_draft_data.get("blockAddedCount"));

            return json_object.toString();
        } catch (Exception e) {
            Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, e);
        }
        return json_object.toString();
    }
    
    //Added by Syed Ilyas - 16 dec 2015 - delete email draft
    @RequestMapping(value = "/deleteEmailDrafts", method = RequestMethod.POST)
    public @ResponseBody
    String deleteEmailDrafts(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            String draftIds = (String) requestBodyMap.get("draft_ids");
           String draftIdsArray[] = draftIds.split(",");
           for (int i = 0; i < draftIdsArray.length; i++) {
               emaildraftservice.delete(Integer.parseInt(draftIdsArray[i]));
           }
            return "true";
        } catch (Exception e) {
            Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "false";
    }
    
    @RequestMapping(value = "/deleteEmailDraft", method = RequestMethod.POST)
    public @ResponseBody
    String deleteEmailDraft(@RequestParam("draftid") Integer draftId) throws IOException, Throwable {
        try {
            emaildraftservice.delete(draftId);
            return "true";
        } catch (Exception e) {
            Logger.getLogger(EmailDraftController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "false";
    }
}
