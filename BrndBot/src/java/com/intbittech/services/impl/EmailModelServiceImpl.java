/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailModelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailModel;
import com.intbittech.services.EmailModelService;
import java.util.List;
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

    @Autowired
    private EmailModelDao emailModelDao;

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
        if (emailModel == null)
            throw new ProcessFailed("No email template found.");
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
}
