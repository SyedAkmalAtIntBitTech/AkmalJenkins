/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.model.Users;
import com.intbittech.modelmappers.Tooltips;
import com.intbittech.modelmappers.UserPreferencesJson;
import java.util.List;

/**
 *
 * @author sandeep
 */
public interface UserPreferencesService {

    public String getUserProfileColor(Integer userId);

    public void setTooltips(Tooltips tooltips, Integer userId);

    public Tooltips getTooltips(Integer userId);

    public List<Users> getAll();
    
    public void setUserPreferencesJson(UserPreferencesJson userPreferencesJson,Users users);
}
