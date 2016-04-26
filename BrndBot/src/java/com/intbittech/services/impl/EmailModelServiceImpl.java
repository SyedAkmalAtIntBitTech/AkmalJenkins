/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.divtohtml.ProcessHTML;
import com.intbit.util.GetUserColors;
import com.intbittech.controller.ModelController;
import com.intbittech.dao.EmailModelDao;
import com.intbittech.dao.SubCategoryEmailModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.CompanyPreferences;
import com.intbittech.model.EmailModel;
import com.intbittech.model.SubCategoryEmailModel;
import com.intbittech.services.EmailModelService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
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

    //TODO Ilyas needs to check path.
    @Override
    public String getLayoutEmail(Integer emailModelId, String imageFileName, String hostURL, Integer companyId, Map<String, String> requestBodyMap) {
        String responseHTML = "";
        try {
            EmailModel emailModel = getByEmailModelId(emailModelId);
            String logo_url = hostURL + "DownloadImage?image_type=USER_LOGO&company_id=" + companyId + "&image_name=" + imageFileName;
            HashMap<String, String> colorHashmap = new HashMap();
            JSONArray userColors = GetUserColors.getColorUserPreferences(companyId);
            String html = "";
            JSONObject htmljson = new JSONObject();
            for (int i = 0; i < userColors.size(); i++) {
                String userColor = (String) userColors.get(i);
                colorHashmap.put("color" + (i + 1), userColor);
            }
            ProcessHTML mindbodyHtmlData = new ProcessHTML(emailModel.getHtmlData(), colorHashmap, requestBodyMap, logo_url);
            html = mindbodyHtmlData.processHTML();

            htmljson.put("htmldata", html);
            responseHTML = htmljson.toString();
        } catch (Throwable throwable) {
            logger.error(throwable);
        }
        return responseHTML;

    }

}
