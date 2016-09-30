/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import static com.intbittech.enums.ScheduledEntityType.Twitter;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.modelmappers.CompanyColorsDetails;
import com.intbittech.modelmappers.EmailSettings;
import com.intbittech.modelmappers.Facebook;
import com.intbittech.modelmappers.FooterDetails;
import com.intbittech.modelmappers.Twitter;
import com.intbittech.modelmappers.UserProfileColorDetails;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author ilyas
 */
public interface CompanyPreferencesService {

    public void updatePreferences(CompanyPreferences companyPreferences);

    public CompanyPreferences getByCompany(Company company);

    public void updateEmailSettings(EmailSettings emailSettings, Company company);

    public EmailSettings getEmailSettings(Company company);

    public List<String> getColors(Company company);

    public void setColors(CompanyColorsDetails companyColorsDetails, Company company);

    public void setUserProfileColor(UserProfileColorDetails userProfileColorDetails, Company company);

    public String getUserProfileColor(Company company);

    public void setStudioId(CompanyPreferences companyPreferences);

    public CompanyPreferences getByCompanyId(Integer companyId);

    public void setFooterDetails(FooterDetails footerDetails, Company company);

    public List<CompanyPreferences> getAllForLocationId(String locationId);

    public List<CompanyPreferences> getAll();

    public void saveUnsubscribeEmails(Integer companyId, List<String> emailList);

    public Map<String, String> getUnsubscribedEmailsMap(CompanyPreferences companyPreferences);
    
    public void setTwitterDetails(Twitter twitter,Company company);
    
    public Twitter getTwitterDetails(Integer companyId);
    
    public void deleteTwitterDetails(Integer companyId);
        
    public void setFacebookDetails(Facebook facebook,Company company);
 
    public Twitter getFacebookDetails(Integer companyId);
    
    public void deleteFacebookDetails(Integer companyId);
    
    
}
