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
import com.intbittech.model.ExternalSourceKeywordLookup;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationEmailBlockLookup;
import com.intbittech.modelmappers.EmailBlockDetails;
import com.intbittech.services.EmailBlockService;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailBlockServiceImpl implements EmailBlockService {
    
    private static Logger logger = Logger.getLogger(EmailBlockServiceImpl.class);
    @Autowired
    private EmailBlockDao emailBlockDao;
    
    @Autowired
    private OrganizationEmailBlockLookupDao organizationEmailBlockLookupDao;
    
    @Autowired
    private EmailBlockExternalSourceDao emailBlockExternalSourceDao;

    /**
     * {@inheritDoc}
     */
    public EmailBlock getByEmailBlockId(Integer emailBlockId) throws ProcessFailed {
        EmailBlock emailBlock = emailBlockDao.getByEmailBlockId(emailBlockId);
        if(emailBlock == null)
            throw new ProcessFailed("No email block found.");
        return emailBlock;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailBlock emailBlock) throws ProcessFailed {
        return emailBlockDao.save(emailBlock);
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailBlock emailBlock) throws ProcessFailed {
        emailBlockDao.update(emailBlock);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailBlockId) throws ProcessFailed {
        EmailBlock emailBlock = emailBlockDao.getByEmailBlockId(emailBlockId);
        if(emailBlock == null)
            throw new ProcessFailed("No email block found.");
        emailBlockDao.delete(emailBlock);
    }
    
    /**
     * {@inheritDoc}
     */
    public Integer saveEmailBlock(EmailBlockDetails emailBlockDetails) throws ProcessFailed {
        try {
        /*  saving the Email Block  */
        EmailBlock emailBlock = new EmailBlock();
        emailBlock.setEmailBlockName(emailBlockDetails.getEmailBlockName());
        Integer emailBlockId = emailBlockDao.save(emailBlock);
        
        /*saving EmailBlockExternalSource using emailBlockId and externalSourceKeywordLookupId*/
        
        EmailBlockExternalSource emailBlockExternalSource = new EmailBlockExternalSource();
        
        EmailBlock emailBlockForId = new EmailBlock();
        emailBlockForId.setEmailBlockId(emailBlockId);
        emailBlockExternalSource.setFkEmailBlockId(emailBlockForId);
        
        ExternalSourceKeywordLookup externalSourceKeywordLookup = new ExternalSourceKeywordLookup();
        
        externalSourceKeywordLookup.setExternalSourceKeywordLookupId(emailBlockDetails.getExternalSourceKeywordLookupId());
        
        emailBlockExternalSource.setFkExternalSourceKeywordLookupId(externalSourceKeywordLookup);
        
        emailBlockExternalSourceDao.save(emailBlockExternalSource);
        
        /*saving OrganizationEmailBlockLookup using OrganizationId */
        
        OrganizationEmailBlockLookup organizationEmailBlockLookup = new OrganizationEmailBlockLookup();
        
        Organization organization = new Organization();
        organization.setOrganizationId(emailBlockDetails.getOrganizationId());
        
        
        
        organizationEmailBlockLookup.setFkEmailBlockId(emailBlockForId);
        organizationEmailBlockLookup.setFkOrganizationId(organization);
        
        return organizationEmailBlockLookupDao.save(organizationEmailBlockLookup);
        
        
        } catch(Throwable throwable) {
            logger.error(throwable);
            throw new ProcessFailed("Database error while saving record.");
        }
        
        
    }
    
    public List<OrganizationEmailBlockLookup> getAllOrganizationEmailBlock(Integer organizationId) throws ProcessFailed {
        List<OrganizationEmailBlockLookup> organizationEmailBlockList = organizationEmailBlockLookupDao.getAllOrganizationEmailBlock(organizationId);
        if(organizationEmailBlockList == null)
            throw new ProcessFailed("No email blocks exist.");
        return organizationEmailBlockList;
    }
    
}
