/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.controller.IConstants;
import com.intbittech.dao.CompanyPreferencesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.modelmappers.CompanyColorsDetails;
import com.intbittech.services.CompanyPreferencesService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyPreferencesServiceImpl implements CompanyPreferencesService {

    private final static Logger logger = Logger.getLogger(CompanyPreferencesServiceImpl.class);

    @Autowired
    private CompanyPreferencesDao companyPreferencesDao;
    @Autowired
    private MessageSource messageSource;

    @Override
    public void updatePreferences(CompanyPreferences companyPreferences) {
        companyPreferencesDao.saveOrUpdate(companyPreferences);
    }

    @Override
    public CompanyPreferences getByCompany(Company company) {
        return companyPreferencesDao.getByCompany(company);
    }

    @Override
    public void updateEmailSettings(JSONObject json_object, Company company) throws ProcessFailed {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if(companyPreferences == null) {
                companyPreferences.setFkCompanyId(company);
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(companyPreferences.getCompanyPreferences());
            jsonObject.put(IConstants.kEmailSettings, json_object);
            companyPreferences.setCompanyPreferences(jsonObject.toJSONString());
            updatePreferences(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record");
        }
    }

    @Override
    public JSONObject getEmailSettings(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(companyPreferences.getCompanyPreferences());
            return (JSONObject) jsonObject.get(IConstants.kEmailSettings);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<String> getColors(Company company) {
        List<String> colorsArray = new ArrayList<>();
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(companyPreferences.getCompanyPreferences());
            JSONArray array = (JSONArray) jsonObject.get(IConstants.kColors);
            for (int i = 0; i < array.size(); i++) {
                colorsArray.add((String) array.get(i));
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
        return colorsArray;
    }

    @Override
    public void setColors(CompanyColorsDetails companyColorsDetailsList, Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if(companyPreferences == null) {
                companyPreferences.setFkCompanyId(company);
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(companyPreferences.getCompanyPreferences());
            jsonObject.put(IConstants.kColors, companyColorsDetailsList);
            companyPreferences.setCompanyPreferences(jsonObject.toJSONString());
            updatePreferences(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

}