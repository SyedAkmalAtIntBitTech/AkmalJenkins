/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailSentHistoryDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailSentHistory;
import com.intbittech.services.EmailSentHistoryService;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ilyas
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailSentHistoryServiceImpl implements EmailSentHistoryService {
    
    private static Logger logger = Logger.getLogger(EmailListTagServiceImpl.class);
    
    @Autowired
    private EmailSentHistoryDao emailSentHistoryDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmailSentHistory> getAllEmailSentHistoryByCompanyId(Integer companyId) throws ProcessFailed {
        List<EmailSentHistory> emailSentHistoryList = emailSentHistoryDao.getAllEmailSentHistoryByCompanyId(companyId);
        if(emailSentHistoryList == null){
            throw new ProcessFailed(messageSource.getMessage("emailSentHistory_not_found",new String[]{}, Locale.US));
        }
        return emailSentHistoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmailSentHistory getByEmailSentHistoryId(Integer emailSentHistoryId) throws ProcessFailed {
        EmailSentHistory emailSentHistory = emailSentHistoryDao.getByEmailSentHistoryId(emailSentHistoryId);
        if(emailSentHistory == null){
            throw new ProcessFailed(messageSource.getMessage("emailSentHistory_not_found",new String[]{}, Locale.US));
        }
        return emailSentHistory;
    }
    
}
