/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.modelmappers.CompanyColorsDetails;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author ilyas
 */
public interface CompanyPreferencesService {

    public void updatePreferences(CompanyPreferences companyPreferences);

    public CompanyPreferences getByCompany(Company company);

    public void updateEmailSettings(JSONObject json_object, Company company);

    public JSONObject getEmailSettings(Company company);

    public List<String> getColors(Company company);

    public void setColors(CompanyColorsDetails companyColorsDetails, Company company);
    
    public void setStudioId(CompanyPreferences companyPreferences);

    public CompanyPreferences getByCompanyId(Integer companyId);   

    public List<CompanyPreferences> getAllForLocationId(String locationId);
}
