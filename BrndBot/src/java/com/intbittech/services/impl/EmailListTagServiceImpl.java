/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailListTagDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.Franchise;
import com.intbittech.model.FranchiseEmailListTagLookup;
import com.intbittech.modelmappers.EmailListTagDetails;
import com.intbittech.services.EmailListTagService;
import com.intbittech.services.FranchiseEmailListTagLookupService;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class EmailListTagServiceImpl implements EmailListTagService{
    
    private static Logger logger = Logger.getLogger(EmailListTagServiceImpl.class);
    
    @Autowired
    private EmailListTagDao emailListTagDao;
    
    @Autowired
    private FranchiseEmailListTagLookupService franchiseEmailListTagLookupService;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailListTag getByEmailListTagId(Integer emailListTagId) throws ProcessFailed {
        EmailListTag emailListTag = emailListTagDao.getByEmailListTagId(emailListTagId);
        if(emailListTag == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
            return emailListTag;
    }
    
    /**
     * {@inheritDoc}
     */
    public EmailListTag getByTagName(String tagName) throws ProcessFailed {
        EmailListTag emailListTag = emailListTagDao.getByTagName(tagName);
        return emailListTag;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(EmailListTag emailListTag) throws ProcessFailed {
        return emailListTagDao.save(emailListTag);
    }
    
    /**
     * {@inheritDoc}
     */
    public void saveEmailListTag(EmailListTagDetails emailListTagDetails) throws ProcessFailed {
        EmailListTag emailListTag = emailListTagDao.getByTagName(emailListTagDetails.getEmailListTagName());
        if(emailListTag == null) {
            emailListTag = new EmailListTag();
            emailListTag.setTagName(emailListTagDetails.getEmailListTagName());
            emailListTag.setTagDescription(emailListTagDetails.getEmailListTagDescription());
            Integer emailListTagId = save(emailListTag);
        }
        FranchiseEmailListTagLookup franchiseEmailListTagLookup = new FranchiseEmailListTagLookup();
        franchiseEmailListTagLookup.setFkEmailListTagId(emailListTag);
        
        Franchise franchise = new Franchise();
        franchise.setFranchiseId(emailListTagDetails.getFranchiseId());
        franchiseEmailListTagLookup.setFkFranchiseId(franchise);
        
        franchiseEmailListTagLookupService.save(franchiseEmailListTagLookup);
        
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailListTag emailListTag) throws ProcessFailed {
        emailListTagDao.update(emailListTag);
    }

     /**
     * {@inheritDoc}
     */
    public void delete(Integer emailListTagId) throws ProcessFailed {
          EmailListTag emailListTag = emailListTagDao.getByEmailListTagId(emailListTagId);
        if(emailListTag == null){
           throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
           emailListTagDao.delete(emailListTag);
    }

    /**
     * {@inheritDoc}
     */
    
    public List<EmailListTag> getAllEmailListTag() throws ProcessFailed {
         List<EmailListTag> emailListTagList = emailListTagDao.getAllEmailListTag();
        if(emailListTagList == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
            return emailListTagList;
    }
    
}
