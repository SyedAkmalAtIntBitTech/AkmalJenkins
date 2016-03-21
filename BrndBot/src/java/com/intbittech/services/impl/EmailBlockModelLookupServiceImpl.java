/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailBlockModelLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlockModelLookup;
import com.intbittech.services.EmailBlockModelLookupService;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailBlockModelLookupServiceImpl implements EmailBlockModelLookupService {
    
    private static Logger logger = Logger.getLogger(EmailBlockModelLookupServiceImpl.class);
    @Autowired
    private EmailBlockModelLookupDao emailBlockModelLookupDao;

    @Autowired
    private MessageSource messageSource;
    
    /**
     * {@inheritDoc}
     */
    public EmailBlockModelLookup getByEmailBlockModelLookupId(Integer emailBlockModelLookupId) throws ProcessFailed {
        EmailBlockModelLookup emailBlockModelLookup = emailBlockModelLookupDao.getEmailBlockModelLookupById(emailBlockModelLookupId);
        if(emailBlockModelLookup == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlockModel_not_found",new String[]{}, Locale.US));
        return emailBlockModelLookup;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed {
        return emailBlockModelLookupDao.save(emailBlockModelLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlockModelLookup emailBlockModelLookup) throws ProcessFailed {
        emailBlockModelLookupDao.update(emailBlockModelLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailBlockModelLookupId) throws ProcessFailed {
        EmailBlockModelLookup emailBlockModelLookup = emailBlockModelLookupDao.getEmailBlockModelLookupById(emailBlockModelLookupId);
        if(emailBlockModelLookup == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlockModel_not_found_delete",new String[]{}, Locale.US));
        emailBlockModelLookupDao.delete(emailBlockModelLookup);
    }
    
    public List<EmailBlockModelLookup> getAllEmailBlockModel(Integer emailBlockId) throws ProcessFailed {
        List<EmailBlockModelLookup> emailBlockModelLookupList = emailBlockModelLookupDao.getAllEmailBlockModel(emailBlockId);
        if(emailBlockModelLookupList == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlockModel_not_found",new String[]{}, Locale.US));
        return emailBlockModelLookupList;
    }
    
}
