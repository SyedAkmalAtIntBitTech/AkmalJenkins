/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.FranchiseEmailListTagLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.FranchiseEmailListTagLookup;
import com.intbittech.services.FranchiseEmailListTagLookupService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
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
public class FranchiseEmailListTagLookupServiceImpl implements FranchiseEmailListTagLookupService{
    
    private static Logger logger = Logger.getLogger(FranchiseEmailListTagLookupServiceImpl.class);
    @Autowired
    private FranchiseEmailListTagLookupDao franchiseEmailListTagLookupDao;
    @Autowired
    private  MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public Integer save(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed {
            return  franchiseEmailListTagLookupDao.save(franchiseEmailListTagLookup);
    }

   /**
     * {@inheritDoc}
     */
    public void update(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed {
        franchiseEmailListTagLookupDao.update(franchiseEmailListTagLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer franchiseEmailListTagLookupId) throws ProcessFailed {
          FranchiseEmailListTagLookup franchiseEmailListTagLookup = franchiseEmailListTagLookupDao.getFranchiseEmailListTagLookupByFranchiseEmailListTagLookupId(franchiseEmailListTagLookupId);
        if(franchiseEmailListTagLookup == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
            franchiseEmailListTagLookupDao.delete(franchiseEmailListTagLookup);
    }

    /**
     * {@inheritDoc}
     */
    public List<FranchiseEmailListTagLookup> getAllFranchiseEmailListTagLookup(Integer franchiseId) throws ProcessFailed {
           List<FranchiseEmailListTagLookup> franchiseEmailListTagLookupList = franchiseEmailListTagLookupDao.getAllFranchiseEmailListTagLookup(franchiseId);
        if(franchiseEmailListTagLookupList == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
            return franchiseEmailListTagLookupList;

    }

   /**
     * {@inheritDoc}
     */
    public FranchiseEmailListTagLookup getFranchiseEmailListTagLookupByFranchiseEmailListTagLookupId(Integer franchiseEmailListTagLookupId) throws ProcessFailed {
        FranchiseEmailListTagLookup franchiseEmailListTagLookup = franchiseEmailListTagLookupDao.getFranchiseEmailListTagLookupByFranchiseEmailListTagLookupId(franchiseEmailListTagLookupId);
        if(franchiseEmailListTagLookup == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
            return franchiseEmailListTagLookup;

    }

    /**
     * {@inheritDoc}
     */
    public List<EmailListTag> getAllEmailListTagForFranchise(Integer franchiseId) throws ProcessFailed {
         List<FranchiseEmailListTagLookup> franchiseEmailListTagLookupList = franchiseEmailListTagLookupDao.getAllFranchiseEmailListTagLookup(franchiseId);
        if(franchiseEmailListTagLookupList == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
        List<EmailListTag> emailListTagList = new ArrayList<>();
        for (FranchiseEmailListTagLookup franchiseEmailListTagLookup : franchiseEmailListTagLookupList) {
            EmailListTag  emailListTag = new EmailListTag();
            emailListTag.setTagId(franchiseEmailListTagLookup.getFkEmailListTagId().getTagId());
            emailListTag.setTagName(franchiseEmailListTagLookup.getFkEmailListTagId().getTagName());
            emailListTag.setTagDescription(franchiseEmailListTagLookup.getFkEmailListTagId().getTagDescription());
           emailListTagList.add(emailListTag);
        }
      return emailListTagList ;   
    }
    
}
