/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.marketing.controller;

import com.controller.IConstants;
import com.intbit.AppConstants;
import com.intbit.TemplateStatus;
import com.intbittech.marketing.service.CompanyMarketingProgramService;
import com.intbittech.marketing.service.ScheduledEntityListService;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyMarketingProgram;
import com.intbittech.model.MarketingAction;
import com.intbittech.model.MarketingProgram;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.UserProfile;
import com.intbittech.services.MarketingActionService;
import com.intbittech.utility.UserSessionUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ajit
 */
@Controller
public class CompanyMarketingProgramController {
    
      private Logger logger = Logger.getLogger(CompanyMarketingProgramController.class);
      
      @Autowired
      private CompanyMarketingProgramService companyMarketingProgramService;
      @Autowired
      private ScheduledEntityListService scheduledEntityListService;
      @Autowired
      private MarketingActionService marketingActionService;
      
        @RequestMapping(value = "/setMarketingProgram", method = RequestMethod.POST)
    public @ResponseBody
    String setUserMarketingProgram(HttpServletRequest request,
            HttpServletResponse response) throws IOException, Throwable {
        try {
            UserProfile userProfile = (UserProfile) UserSessionUtil.getLogedInUser();
            SimpleDateFormat formatter = null;
            Date tillDate = null;
            CompanyMarketingProgram addCompanyMarketingProgram = new CompanyMarketingProgram();
            MarketingProgram marketingProgram = new MarketingProgram();
            Integer companyId = userProfile.getUser().getFkCompanyId().getCompanyId();
            Company company = new Company();
            company.setCompanyId(companyId);
            Map<String, Object> requestBodyMap
                    = AppConstants.GSON.fromJson(new BufferedReader(request.getReader()), Map.class);
            addCompanyMarketingProgram.setCompanyMarketingProgramName(requestBodyMap.get("program_name").toString());
            String target = requestBodyMap.get("program_date_time").toString();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date eventDate = df.parse(target);
            String marketingCategoryId1 = (String) requestBodyMap.get("marketing_category_id");
            Double marketingCategoryId = Double.parseDouble(marketingCategoryId1);
            String marketingProgramId1 = (String) requestBodyMap.get("marketing_program_id");
            Double marketingProgramId =Double.parseDouble(marketingProgramId1);
          //  marketingProgram.setMarketingProgramId(marketingProgramId.intValue());
            addCompanyMarketingProgram.setCompanyMarketingProgramId(0);
            addCompanyMarketingProgram.setDateEvent(eventDate);
            addCompanyMarketingProgram.setCreatedDate(new Date());
            addCompanyMarketingProgram.setFkCompanyId(company);
            addCompanyMarketingProgram.setStatus("Open");
            addCompanyMarketingProgram.setUrl(requestBodyMap.get("program_url").toString());
            addCompanyMarketingProgram.setLinkName(requestBodyMap.get("program_url_name").toString());
            addCompanyMarketingProgram.setFkMarketingProgramId(marketingProgramId.intValue());
            Integer companyMarketingProgramId = companyMarketingProgramService.save(addCompanyMarketingProgram);
            String link = companyMarketingProgramId.toString();

           MarketingAction marketingAction = marketingActionService.getByMarketingActionByProgramId(marketingProgramId.intValue());
           
           if (marketingAction != null){
                String jsonString = marketingAction.getJsonTemplate();
                JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
                org.json.simple.JSONArray jSONArray = (org.json.simple.JSONArray) json.get(IConstants.kMarketingActionsKey);
                for (Integer i = 0; i < jSONArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jSONArray.get(i);
                    String tillDateString = jsonObject.get("tilldate").toString();

                    if (!(tillDateString.equals(""))) {
                        formatter = new SimpleDateFormat("yyyy-MM-DD");
                        tillDate = formatter.parse(tillDateString);
                    }

                    String timeString = jsonObject.get("time").toString();
                    SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm");
                    Date time = formatterTime.parse(timeString);

                    ScheduledEntityList scheduledEntityList = new ScheduledEntityList();
                    scheduledEntityList.setEntityType(jsonObject.get("type").toString());
                    scheduledEntityList.setIsRecurring(Boolean.parseBoolean(jsonObject.get("is_recurring").toString()));
                    scheduledEntityList.setScheduleDesc(jsonObject.get("description").toString());
                    scheduledEntityList.setEntityId(0);
                    scheduledEntityList.setScheduleTime(time);
                    scheduledEntityList.setTillDate(tillDate);
                    scheduledEntityList.setDays(Integer.parseInt(jsonObject.get("days").toString()));
                    scheduledEntityList.setStatus(TemplateStatus.no_template.toString());
                    scheduledEntityList.setScheduleTitle(jsonObject.get("title").toString());
                    scheduledEntityList.setFkCompanyId(company);
                    CompanyMarketingProgram companyMarketingProgram = new CompanyMarketingProgram();
                    companyMarketingProgram.setCompanyMarketingProgramId(companyMarketingProgramId);
                    scheduledEntityList.setFkCompanyMarketingProgramId(companyMarketingProgram);
                    scheduledEntityListService.save(scheduledEntityList);
                }
            }
                return link;
        } catch (Exception ex) {
           logger.error(ex);
        }
        return "false";
    }
    
}
