/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intbittech.dao.UsersDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.Tooltips;
import com.intbittech.modelmappers.UserPreferencesJson;
import com.intbittech.services.UserPreferencesService;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sandeep
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserPreferencesServiceImpl implements UserPreferencesService {

    private final static Logger LOOGER = Logger.getLogger(CompanyPreferencesServiceImpl.class);
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private UserPreferencesJson userPreferencesJson;

    @Override
    public String getUserProfileColor(Integer userId) {
        try {
            Users users = usersDao.getUserById(userId);
            String userPreferencesJsonString = users.getUserPreferences();
            ObjectMapper mapper = new ObjectMapper();
            userPreferencesJson = mapper.readValue(userPreferencesJsonString, UserPreferencesJson.class);
        } catch (Throwable throwable) {
            LOOGER.error(throwable);
        }
        return userPreferencesJson.getProfileColor();
    }

    @Override
    public void setTooltips(Tooltips tooltips, Integer userId) {
        try {
            Users users = usersDao.getUserById(userId);
            if (users != null) {
                userPreferencesJson.setTooltips(tooltips);
                ObjectMapper mapper = new ObjectMapper();
                String userPreferencesJsonToString = mapper.writeValueAsString(userPreferencesJson);
                users.setUserPreferences(userPreferencesJsonToString);
                usersDao.save(users);
            }

        } catch (Throwable throwable) {
            LOOGER.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }
    }

    @Override
    public Tooltips getTooltips(Integer userId) {
        try {
            Users users = usersDao.getUserById(userId);
            String userPreferencesJsonString = users.getUserPreferences();
            ObjectMapper mapper = new ObjectMapper();
            userPreferencesJson = mapper.readValue(userPreferencesJsonString, UserPreferencesJson.class);
        } catch (Throwable throwable) {
            LOOGER.error(throwable);
        }
        return userPreferencesJson.getTooltips();
    }

    @Override
    public List<Users> getAll() {
        return usersDao.getAll();
    }

    @Override
    public void setUserPreferencesJson(UserPreferencesJson userPreferencesJson,Users users) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String userPreferencesJsonToString = mapper.writeValueAsString(userPreferencesJson);
            users.setUserPreferences(userPreferencesJsonToString);
            usersDao.update(users);

        } catch (Throwable throwable) {
            LOOGER.error(throwable);
            throw new ProcessFailed(messageSource.getMessage("error_updating_message", new String[]{}, Locale.US));
        }

    }

}
