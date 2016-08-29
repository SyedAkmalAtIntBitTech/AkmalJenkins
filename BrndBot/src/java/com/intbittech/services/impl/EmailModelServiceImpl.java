/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.divtohtml.ProcessHTML;
import com.intbittech.AppConstants;
import com.intbittech.controller.ModelController;
import com.intbittech.dao.EmailBlockModelDao;
import com.intbittech.dao.EmailModelDao;
import com.intbittech.dao.RecurringEmailTemplateDao;
import com.intbittech.dao.SubCategoryEmailModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.externalcontent.ExternalContentProcessor;
import com.intbittech.model.Company;
import com.intbittech.model.EmailBlockModel;
import com.intbittech.model.EmailModel;
import com.intbittech.model.ExternalSourceKeywordLookup;
import com.intbittech.model.RecurringEmailTemplate;
import com.intbittech.model.SubCategoryEmailModel;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.EmailModelService;
import com.intbittech.services.ExternalSourceKeywordLookupService;
import com.intbittech.utility.Utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ilyas
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailModelServiceImpl implements EmailModelService {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelController.class);

    @Autowired
    private EmailModelDao emailModelDao;

    @Autowired
    private SubCategoryEmailModelDao subCategoryEmailModelDao;

    @Autowired
    private CompanyPreferencesService companyPreferencesService;

    @Autowired
    private EmailBlockModelDao emailBlockModelDao;
    
    @Autowired
    private RecurringEmailTemplateDao recurringEmailTemplateDao;

    @Autowired
    private ExternalSourceKeywordLookupService externalSourceKeywordLookupService;
    private ExternalContentProcessor externalContentProcessor;

    /**
     * {@inheritDoc}
     */
    public EmailModel getByEmailModelId(Integer emailModelId) throws ProcessFailed {
        EmailModel emailModel = emailModelDao.getByEmailModelId(emailModelId);
        if (emailModel == null) {
            throw new ProcessFailed("No email template found.");
        }
        return emailModel;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailModel emailModel) throws ProcessFailed {
        return emailModelDao.save(emailModel);
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailModel emailModel) throws ProcessFailed {
        emailModelDao.update(emailModel);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailModelId) throws ProcessFailed {
        EmailModel emailModel = emailModelDao.getByEmailModelId(emailModelId);
        if (emailModel == null) {
            throw new ProcessFailed("No email template found.");
        }
        emailModelDao.delete(emailModel);

    }

    /**
     * {@inheritDoc}
     */
    public List<EmailModel> getAllEmailModel() throws ProcessFailed {
        List<EmailModel> emailModel = emailModelDao.getAllEmailModel();
        if (emailModel == null) {
            throw new ProcessFailed("No email template found.");
        }
        return emailModel;
    }

    /**
     * {@inheritDoc}
     */
    public List<EmailModel> getAllNonAddedEmailModels(Integer subCategoryId) throws ProcessFailed {
        List<SubCategoryEmailModel> subCategoryEmailModelList = subCategoryEmailModelDao.getAllSubCategoryEmailModel(subCategoryId);
        ArrayList<Integer> emailModelIds = new ArrayList<>();

        emailModelIds.add(0);
        if (subCategoryEmailModelList != null) {
            for (SubCategoryEmailModel subCategoryEmailModelObject : subCategoryEmailModelList) {
                emailModelIds.add(subCategoryEmailModelObject.getFkEmailModelId().getEmailModelId());
            }
        }
        List<EmailModel> emailModelList = emailModelDao.getByEmailModelsByIds(emailModelIds);
        if (emailModelList == null) {
            throw new ProcessFailed("No email templates exist.");
        }
        return emailModelList;
    }

    @Override
    public String getLayoutEmail(Boolean isBlock, Integer emailModelId, String hostURL, Integer companyId, ExternalSourceKeywordLookup externalSourceKeywordLookup, Integer externalDataId, Map<String, Object> data, Boolean isRecurring) throws ProcessFailed {
        String responseHTML = "";
        try {
            String dataHTML = "";
            if (!isBlock) {
                if(!isRecurring) {
                EmailModel emailModel = getByEmailModelId(emailModelId);
                dataHTML = emailModel.getHtmlData();
                } else {
                    RecurringEmailTemplate recurringEmailTemplate = recurringEmailTemplateDao.getRecurringEmailTemplateById(emailModelId);
                    dataHTML = recurringEmailTemplate.getHtmlData();
                }
            } else {
                EmailBlockModel emailBlockModel = emailBlockModelDao.getByEmailBlockModelId(emailModelId);
                dataHTML = emailBlockModel.getHtmlData();
            }
            String logo_url = hostURL + "downloadImage?imageType=COMPANY_LOGO&companyId=" + companyId + "&imageName=" + AppConstants.COMPANY_LOGO_FILENAME;
            HashMap<String, String> colorHashmap = new HashMap();
            Company company = new Company(companyId);
            Map<String, String> dataMap = new HashMap<>();
            List<String> colorArray = companyPreferencesService.getColors(company);
            int i = 0;
            for (String color : colorArray) {
                colorHashmap.put("color" + (i++), Utility.rgbToHex(color));
            }

            if (externalSourceKeywordLookup != null) {
                externalContentProcessor = new ExternalContentProcessor(companyId);
                String query = externalSourceKeywordLookup.getFkExternalSourceKeywordId().getExternalSourceKeywordName();
                Set<String> keys = data.keySet();
                if (keys.contains(String.valueOf(externalDataId))) {
                    Object obj = data.get(String.valueOf(externalDataId));
                    dataMap = externalContentProcessor.getDetailData(query, obj);                            
                }
                logger.info(data.keySet());
            }

            String html = "";
            JSONObject htmljson = new JSONObject();
            ProcessHTML mindbodyHtmlData = new ProcessHTML(dataHTML, colorHashmap, dataMap, logo_url);
            html = mindbodyHtmlData.processHTML();

            htmljson.put("htmldata", html);
            responseHTML = htmljson.toString();
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return responseHTML;
    }

}
