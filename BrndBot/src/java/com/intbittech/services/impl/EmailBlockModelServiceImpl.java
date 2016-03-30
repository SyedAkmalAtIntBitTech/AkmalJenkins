/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailBlockModelDao;
import com.intbittech.dao.EmailBlockModelLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModel;
import com.intbittech.model.EmailBlockModelLookup;
import com.intbittech.services.EmailBlockModelService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailBlockModelServiceImpl implements EmailBlockModelService {

    private static Logger logger = Logger.getLogger(EmailBlockModelServiceImpl.class);
    @Autowired
    private EmailBlockModelDao emailBlockModelDao;
    
    @Autowired
    private EmailBlockModelLookupDao emailBlockModelLookupDao; 
    
    @Autowired
    private MessageSource messageSource;
    /**
     * {@inheritDoc}
     */
    public EmailBlockModel getByEmailBlockModelId(Integer emailBlockModelId) throws ProcessFailed {
        EmailBlockModel emailBlockModel = emailBlockModelDao.getByEmailBlockModelId(emailBlockModelId);
        if(emailBlockModel == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlockModel_not_found",new String[]{}, Locale.US));
        
        return emailBlockModel;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlockModel emailBlockModel) throws ProcessFailed {
        return emailBlockModelDao.save(emailBlockModel);
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlockModel emailBlockModel) throws ProcessFailed {
        emailBlockModelDao.update(emailBlockModel);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailBlockModelId) throws ProcessFailed {
        EmailBlockModel emailBlockModel = emailBlockModelDao.getByEmailBlockModelId(emailBlockModelId);
        if(emailBlockModel == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlockModel_not_found_delete",new String[]{}, Locale.US));
        emailBlockModelDao.delete(emailBlockModel);
    }

    /**
     * {@inheritDoc}
     */
    public List<EmailBlockModel> getAllEmailBlockModel() throws ProcessFailed {
        List<EmailBlockModel> emailBlockModel = emailBlockModelDao.getAllEmailBlockModel();
        if(emailBlockModel == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlockModel_not_found",new String[]{}, Locale.US));
        
        return emailBlockModel;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailBlockModel> getAllNonAddedEmailBlockModels(Integer emailBlockId) throws ProcessFailed {
        List<EmailBlockModelLookup> emailBlockModelLookupList = emailBlockModelLookupDao.getAllEmailBlockModel(emailBlockId);
        ArrayList<Integer> emailBlockModelIds = new ArrayList<>();
        emailBlockModelIds.add(0);
        if(emailBlockModelLookupList!=null)
        {
            for(EmailBlockModelLookup emailBlockModelLookupObject : emailBlockModelLookupList)
            {
                emailBlockModelIds.add(emailBlockModelLookupObject.getFkEmailBlockModelId().getEmailBlockModelId());
            }
        }
        List<EmailBlockModel> emailBlockModelList = emailBlockModelDao.getByEmailBlockModelsByIds(emailBlockModelIds);
        if(emailBlockModelList == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlockModel_list_not_found",new String[]{}, Locale.US));
        return emailBlockModelList;
    }
    
}
