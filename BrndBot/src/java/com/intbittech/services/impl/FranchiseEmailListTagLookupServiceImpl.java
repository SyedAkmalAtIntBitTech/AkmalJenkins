/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailListTagLookupDao;
import com.intbittech.dao.FranchiseEmailListTagLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.EmailListTagLookup;
import com.intbittech.model.FranchiseEmailListTagLookup;
import com.intbittech.modelmappers.TagAndEmailListDetails;
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
public class FranchiseEmailListTagLookupServiceImpl implements FranchiseEmailListTagLookupService {

    private static Logger logger = Logger.getLogger(FranchiseEmailListTagLookupServiceImpl.class);
    @Autowired
    private FranchiseEmailListTagLookupDao franchiseEmailListTagLookupDao;
    @Autowired
    private EmailListTagLookupDao emailListTagLookupDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public Integer save(FranchiseEmailListTagLookup franchiseEmailListTagLookup) throws ProcessFailed {
        return franchiseEmailListTagLookupDao.save(franchiseEmailListTagLookup);
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
        if (franchiseEmailListTagLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found", new String[]{}, Locale.US));
        }
        franchiseEmailListTagLookupDao.delete(franchiseEmailListTagLookup);
    }

    /**
     * {@inheritDoc}
     */
    public List<FranchiseEmailListTagLookup> getAllFranchiseEmailListTagLookup(Integer franchiseId) throws ProcessFailed {
        List<FranchiseEmailListTagLookup> franchiseEmailListTagLookupList = franchiseEmailListTagLookupDao.getAllFranchiseEmailListTagLookup(franchiseId);
        if (franchiseEmailListTagLookupList == null) {
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found", new String[]{}, Locale.US));
        }
        return franchiseEmailListTagLookupList;

    }

    /**
     * {@inheritDoc}
     */
    public List<TagAndEmailListDetails> getAllEmailListsAndTagsForFranchise(Integer franchiseId, Integer companyId) throws ProcessFailed {
        List<TagAndEmailListDetails> tagAndEmailListDetailsList = new ArrayList<>();
        List<FranchiseEmailListTagLookup> franchiseEmailListTagLookupList = franchiseEmailListTagLookupDao.getAllFranchiseEmailListTagLookup(franchiseId);
        List<EmailListTagLookup> emailListTagLookupList = emailListTagLookupDao.getEmailListTagLookupByCompanyId(companyId);
        if (franchiseEmailListTagLookupList != null) {
            for (FranchiseEmailListTagLookup emailTag : franchiseEmailListTagLookupList) {
                if(emailListTagLookupList != null) {
                    TagAndEmailListDetails tagAndEmailListDetails = new TagAndEmailListDetails();
                    tagAndEmailListDetails.setTagId(emailTag.getFkEmailListTagId().getTagId());
                    tagAndEmailListDetails.setTagName(emailTag.getFkEmailListTagId().getTagName());
                    for (EmailListTagLookup emailListTag : emailListTagLookupList) {
                        if(emailTag.getFkEmailListTagId().getTagId() == emailListTag.getFkEmailListTagId().getTagId()) {
                            tagAndEmailListDetails.setEmailListTagId(emailListTag.getEmailListTagId());
                            tagAndEmailListDetails.setEmailListId(emailListTag.getFkEmailListId().getEmailListId());
                            tagAndEmailListDetails.setEmailListName(emailListTag.getFkEmailListId().getEmailListName());
                            break;
                        }
                            
                    }
                    tagAndEmailListDetailsList.add(tagAndEmailListDetails);
                }
            }
        }
        return tagAndEmailListDetailsList;

    }

    /**
     * {@inheritDoc}
     */
    public FranchiseEmailListTagLookup getFranchiseEmailListTagLookupByFranchiseEmailListTagLookupId(Integer franchiseEmailListTagLookupId) throws ProcessFailed {
        FranchiseEmailListTagLookup franchiseEmailListTagLookup = franchiseEmailListTagLookupDao.getFranchiseEmailListTagLookupByFranchiseEmailListTagLookupId(franchiseEmailListTagLookupId);
        if (franchiseEmailListTagLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found", new String[]{}, Locale.US));
        }
        return franchiseEmailListTagLookup;

    }

    /**
     * {@inheritDoc}
     */
    public List<FranchiseEmailListTagLookup> getAllEmailListTagForFranchise(Integer franchiseId) throws ProcessFailed {
        List<FranchiseEmailListTagLookup> franchiseEmailListTagLookupList = franchiseEmailListTagLookupDao.getAllFranchiseEmailListTagLookup(franchiseId);
        if (franchiseEmailListTagLookupList == null) {
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found", new String[]{}, Locale.US));
        }
        List<EmailListTag> emailListTagList = new ArrayList<>();
        for (FranchiseEmailListTagLookup franchiseEmailListTagLookup : franchiseEmailListTagLookupList) {
            EmailListTag emailListTag = new EmailListTag();
            emailListTag.setTagId(franchiseEmailListTagLookup.getFkEmailListTagId().getTagId());
            emailListTag.setTagName(franchiseEmailListTagLookup.getFkEmailListTagId().getTagName());
            emailListTag.setTagDescription(franchiseEmailListTagLookup.getFkEmailListTagId().getTagDescription());
            emailListTagList.add(emailListTag);
        }
        return franchiseEmailListTagLookupList;
    }

}
