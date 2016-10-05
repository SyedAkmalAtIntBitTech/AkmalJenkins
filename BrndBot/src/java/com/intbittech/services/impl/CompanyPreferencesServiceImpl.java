/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intbittech.utility.IConstants;
import com.intbittech.AppConstants;
import com.intbittech.dao.CompanyPreferencesDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.modelmappers.CompanyColorsDetails;
import com.intbittech.modelmappers.CompanyPreferencesJson;
import com.intbittech.modelmappers.CriticalInfoTips;
import com.intbittech.modelmappers.EmailSettings;
import com.intbittech.modelmappers.FacebookDataDetails;
import com.intbittech.modelmappers.FooterDetails;
import com.intbittech.modelmappers.OrientationTips;
import com.intbittech.modelmappers.Tooltips;
import com.intbittech.modelmappers.TutorialTip;
import com.intbittech.modelmappers.TwitterDataDetails;
import com.intbittech.modelmappers.UserProfile;
import com.intbittech.modelmappers.UserProfileColorDetails;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.utility.EmailValidator;
import com.intbittech.utility.StringUtility;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

    @Autowired
    private CompanyPreferencesJson companyPreferencesJson;

    @Override
    public void updatePreferences(CompanyPreferences companyPreferences) {
        companyPreferencesDao.saveOrUpdate(companyPreferences);
    }

    @Override
    public CompanyPreferences getByCompany(Company company) {
        return companyPreferencesDao.getByCompany(company);
    }

    @Override
    public void updateEmailSettings(EmailSettings emailSettings, Company company) throws ProcessFailed {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            companyPreferencesJson.setEmailSettings(emailSettings);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while updating record");
        }
    }

    @Override
    public EmailSettings getEmailSettings(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            String companyPreferencesString = companyPreferences.getCompanyPreferences();
            ObjectMapper mapper = new ObjectMapper();
            EmailSettings emailSettings = mapper.convertValue(companyPreferencesString, EmailSettings.class);
            return emailSettings;
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<String> getColors(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonString = companyPreferences.getCompanyPreferences();
            companyPreferencesJson = mapper.readValue(companyPreferencesJsonString, CompanyPreferencesJson.class);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
        return companyPreferencesJson.getColors();
    }

    @Override
    public void setColors(CompanyColorsDetails companyColorsDetailsList, Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            List<String> colorList = new ArrayList<>();
            colorList.add(companyColorsDetailsList.getColor1());
            colorList.add(companyColorsDetailsList.getColor2());
            colorList.add(companyColorsDetailsList.getColor3());
            colorList.add(companyColorsDetailsList.getColor4());
            companyPreferencesJson.setColors(colorList);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public void setStudioId(CompanyPreferences companyPreferences) {
        try {
            CompanyPreferences companyPreferencesObject = companyPreferencesDao.getByCompany(companyPreferences.getFkCompanyId());
            if (companyPreferencesObject == null) {
                companyPreferencesObject = new CompanyPreferences();
                Company company = new Company();
                company.setCompanyId(companyPreferences.getFkCompanyId().getCompanyId());
                companyPreferencesObject.setFkCompanyId(company);

            }
            companyPreferencesObject.setCompanyLocation(companyPreferences.getCompanyLocation());
            updatePreferences(companyPreferencesObject);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error saving studio id");
        }
    }

    @Override
    public CompanyPreferences getByCompanyId(Integer companyId) {
        Company company = new Company();
        company.setCompanyId(companyId);
        return getByCompany(company);
    }

    @Override
    public void setFooterDetails(FooterDetails footerDetails, Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            UserProfile userProfile = new UserProfile();
            userProfile.setFacebookUrl(footerDetails.getFacebookUrl());
            userProfile.setTwitterUrl(footerDetails.getTwitterUrl());
            userProfile.setInstagramUrl(footerDetails.getInstagramUrl());
            userProfile.setWebsiteUrl(footerDetails.getWebsiteUrl());
            companyPreferencesJson.setUserProfile(userProfile);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public List<CompanyPreferences> getAllForLocationId(String locationId) {
        return companyPreferencesDao.getByLocationId(locationId);
    }

    @Override
    public List<CompanyPreferences> getAll() {
        return companyPreferencesDao.getAll();
    }

    @Override
    public void saveUnsubscribeEmails(Integer companyId, List<String> emailList) {
        try {
            EmailValidator emailValidator = new EmailValidator();
            for (int i = 0; i < emailList.size(); i++) {
                if (!emailValidator.validate(emailList.get(i))) {
                    emailList.remove(i);
                }
            }
            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            CompanyPreferences companyPreferences = getByCompanyId(companyId);
            JSONParser parser = new JSONParser();
            if (!StringUtility.isEmpty(companyPreferences.getUnsubscribeEmails())) {
                jsonObject = (JSONObject) parser.parse(companyPreferences.getUnsubscribeEmails());
                array = (JSONArray) jsonObject.get(IConstants.kUnsubscribeEmails);
            }

            array.addAll(emailList);

            jsonObject.put(IConstants.kUnsubscribeEmails, array);
            String jsonString = AppConstants.GSON.toJson(jsonObject);
            companyPreferences.setUnsubscribeEmails(jsonString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CompanyPreferencesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Map<String, String> getUnsubscribedEmailsMap(CompanyPreferences companyPreferences) {
        Map<String, String> filteredMap = new HashMap<>();

        try {
            if (companyPreferences.getUnsubscribeEmails() != null) {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(companyPreferences.getUnsubscribeEmails());
                JSONArray array = (JSONArray) jsonObject.get(IConstants.kUnsubscribeEmails);
                if (array != null && array.size() > 0) {
                    for (Object arrayObject : array) {
                        String email = (String) arrayObject;
                        filteredMap.put(email, "true");
                    }
                }
            }
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CompanyPreferencesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filteredMap;
    }

    @Override
    public void setUserProfileColor(UserProfileColorDetails userProfileColorDetails, Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            companyPreferencesJson.setUserProfileColor(userProfileColorDetails.getUserProfileColor());
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public String getUserProfileColor(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            String companyPreferencesJsonString = companyPreferences.getCompanyPreferences();
            ObjectMapper mapper = new ObjectMapper();
            companyPreferencesJson = mapper.readValue(companyPreferencesJsonString, CompanyPreferencesJson.class);
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return companyPreferencesJson.getUserProfileColor();
    }

    @Override
    public void setTwitterDetails(TwitterDataDetails twitter, Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            companyPreferencesJson.setTwitter(twitter);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public TwitterDataDetails getTwitterDetails(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            String companyPreferencesJsonString = companyPreferences.getCompanyPreferences();
            ObjectMapper mapper = new ObjectMapper();
            companyPreferencesJson = mapper.readValue(companyPreferencesJsonString, CompanyPreferencesJson.class);
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return companyPreferencesJson.getTwitter();
    }

    @Override
    public void deleteTwitterDetails(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            TwitterDataDetails twitterDataDetails = null;
            companyPreferencesJson.setTwitter(twitterDataDetails);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error");
        }
    }

    @Override
    public void setFacebookDetails(FacebookDataDetails facebook, Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            companyPreferencesJson.setFacebook(facebook);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public FacebookDataDetails getFacebookDetails(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            String companyPreferencesJsonString = companyPreferences.getCompanyPreferences();
            ObjectMapper mapper = new ObjectMapper();
            companyPreferencesJson = mapper.readValue(companyPreferencesJsonString, CompanyPreferencesJson.class);
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return companyPreferencesJson.getFacebook();
    }

    @Override
    public void deleteFacebookDetails(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            FacebookDataDetails facebook = null;
            companyPreferencesJson.setFacebook(facebook);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error");
        }
    }

    @Override
    public void setTooltips(Tooltips tooltips, Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            if (companyPreferences == null) {
                companyPreferences = new CompanyPreferences();
                companyPreferences.setFkCompanyId(company);
            }
            companyPreferencesJson.setTooltips(tooltips);
            ObjectMapper mapper = new ObjectMapper();
            String companyPreferencesJsonToString = mapper.writeValueAsString(companyPreferencesJson);
            companyPreferences.setCompanyPreferences(companyPreferencesJsonToString);
            companyPreferencesDao.saveOrUpdate(companyPreferences);
        } catch (Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while retrieving record");
        }
    }

    @Override
    public Tooltips getTooltips(Company company) {
        try {
            CompanyPreferences companyPreferences = companyPreferencesDao.getByCompany(company);
            String companyPreferencesJsonString = companyPreferences.getCompanyPreferences();
            ObjectMapper mapper = new ObjectMapper();
            companyPreferencesJson = mapper.readValue(companyPreferencesJsonString, CompanyPreferencesJson.class);
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return companyPreferencesJson.getTooltips();
    }

}
