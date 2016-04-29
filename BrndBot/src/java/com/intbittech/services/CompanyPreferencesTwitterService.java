/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import java.sql.SQLException;
import org.json.simple.JSONObject;

/**
 *
 * @author AR
 */
public interface CompanyPreferencesTwitterService {

    public void updatePreference(Integer companyId, String default_page_access_token, String fb_user_profile_name, String default_page_name) throws SQLException;

    public JSONObject getCompanyPreferenceForAccessToken(Integer companyId) throws SQLException;

    public void deletePreferences(Integer companyId) throws SQLException;

}
