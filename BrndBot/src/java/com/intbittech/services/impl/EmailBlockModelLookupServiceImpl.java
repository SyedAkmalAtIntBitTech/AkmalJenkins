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
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailBlockModelLookupServiceImpl implements EmailBlockModelLookupService {
    
    private static Logger logger = Logger.getLogger(EmailBlockModelLookupServiceImpl.class);
    @Autowired
    private EmailBlockModelLookupDao emailBlockModelLookupDao;

    /**
     * {@inheritDoc}
     */
    public EmailBlockModelLookup getByEmailBlockModelLookupId(Integer emailBlockModelLookupId) throws ProcessFailed {
        EmailBlockModelLookup emailBlockModelLookup = emailBlockModelLookupDao.getEmailBlockModelLookupById(emailBlockModelLookupId);
        if(emailBlockModelLookup == null)
            throw new ProcessFailed("No email block templete found.");
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
            throw new ProcessFailed("No email block templete found to delete.");
        emailBlockModelLookupDao.delete(emailBlockModelLookup);
    }
    
    public List<EmailBlockModelLookup> getAllEmailBlockModel(Integer emailBlockId) throws ProcessFailed {
        List<EmailBlockModelLookup> emailBlockModelLookupList = emailBlockModelLookupDao.getAllEmailBlockModel(emailBlockId);
        if(emailBlockModelLookupList == null)
            throw new ProcessFailed("No email block templete found.");
        return emailBlockModelLookupList;
    }
    
}
