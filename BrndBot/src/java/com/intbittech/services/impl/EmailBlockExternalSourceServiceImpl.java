/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailBlockDao;
import com.intbittech.dao.EmailBlockExternalSourceDao;
import com.intbittech.dao.OrganizationEmailBlockLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailBlock;
import com.intbittech.model.EmailBlockExternalSource;
import com.intbittech.services.EmailBlockExternalSourceService;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailBlockExternalSourceServiceImpl implements EmailBlockExternalSourceService {
    
    private static Logger logger = Logger.getLogger(EmailBlockExternalSourceServiceImpl.class);
    @Autowired
    private EmailBlockDao emailBlockDao;
    
    @Autowired
    private OrganizationEmailBlockLookupDao organizationEmailBlockLookupDao;
    
    @Autowired
    private EmailBlockExternalSourceDao emailBlockExternalSourceDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailBlockExternalSource getByEmailBlockId(Integer emailBlockId) {
        EmailBlockExternalSource emailBlockExternalSource = emailBlockExternalSourceDao.getByEmailBlockId(emailBlockId);
        if(emailBlockExternalSource == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlock_not_found",new String[]{}, Locale.US));
        return emailBlockExternalSource;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed {
        return emailBlockExternalSourceDao.save(emailBlockExternalSource);
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlockExternalSource emailBlockExternalSource) throws ProcessFailed {
        emailBlockExternalSourceDao.update(emailBlockExternalSource);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailBlockId) throws ProcessFailed {
        EmailBlock emailBlockExternalSource = emailBlockDao.getByEmailBlockId(emailBlockId);
        if(emailBlockExternalSource == null)
            throw new ProcessFailed(messageSource.getMessage("emailBlock_not_found",new String[]{}, Locale.US));
        emailBlockDao.delete(emailBlockExternalSource);
    }
    
}
