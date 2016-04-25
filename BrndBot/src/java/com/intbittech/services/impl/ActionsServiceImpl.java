/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbit.AppConstants;
import com.intbit.ScheduledEntityType;
import com.intbit.dao.ScheduleDAO;
import com.intbit.dao.ScheduleSocialPostDAO;
import com.intbittech.services.ActionsService;
import java.util.Map;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActionsServiceImpl implements ActionsService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getActions(Map<String, Object> requestBodyMap, Integer userId) throws Exception {
        JSONArray json_array = new JSONArray();

        String type = (String) requestBodyMap.get("type");
        String program_id = (String) requestBodyMap.get("programid");

        if (type.equalsIgnoreCase(ScheduledEntityType.Facebook.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
                json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(userId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase(ScheduledEntityType.Twitter.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
                json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(userId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase(ScheduledEntityType.Email.toString())) {
            if ((program_id != null) && !(program_id.equals("undefined"))) {
                json_array = ScheduleDAO.getScheduledActions(userId, Integer.parseInt(program_id));
            }
        } else if (type.equalsIgnoreCase("social")) {
            JSONArray json_social = new JSONArray();
            json_array = ScheduleSocialPostDAO.getScheduledActionsfacebook(userId, Integer.parseInt(program_id));
            for (int i = 0; i < json_array.size(); i++) {
                json_social.add(json_array.get(i));
            }
            json_array = ScheduleSocialPostDAO.getScheduledActionstwitter(userId, Integer.parseInt(program_id));
            for (int i = 0; i < json_array.size(); i++) {
                json_social.add(json_array.get(i));
            }
            json_array = json_social;
        }
        return AppConstants.GSON.toJson(json_array);
    }

}
