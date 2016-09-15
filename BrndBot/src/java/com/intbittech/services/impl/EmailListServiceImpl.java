/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailListDao;
import com.intbittech.enums.EmailListTagConstants;
import com.intbittech.enums.EmailListTypeConstants;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.EmailList;
import com.intbittech.model.EmailListTag;
import com.intbittech.model.EmailListTagLookup;
import com.intbittech.modelmappers.AddEmailListDetails;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.EmailListService;
import com.intbittech.services.EmailListTagLookupService;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class EmailListServiceImpl implements EmailListService {
    
    enum EmailListType {
        
        Regular, Mindbody
    };
    
    private final static Logger logger = Logger.getLogger(EmailListServiceImpl.class);
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private CompanyPreferencesService companyPreferencesService;
    
    @Autowired
    private EmailListDao emailListDao;
    
    @Autowired
    EmailListTagLookupService emailListTagLookupService;

    /**
     * {@inheritDoc}
     */
    public EmailList getByEmailListId(Integer emailListId) throws ProcessFailed {
        EmailList emailList = emailListDao.getByEmailListId(emailListId);
        if (emailList == null) {
            throw new ProcessFailed(messageSource.getMessage("email_list_not_found", new String[]{}, Locale.US));
        }
        return emailList;
    }
    
    /**
     * {@inheritDoc}
     */
    public Boolean checkUniqueness(Integer companyId, String emailListName) throws ProcessFailed {
        return emailListDao.checkUniqueness(companyId, emailListName);
    }
    
    /**
     * {@inheritDoc}
     */
    public EmailList getEmailListByCompanyIdAndEmailListName(Integer companyId, String emailListName) throws ProcessFailed {
        EmailList emailList = emailListDao.getEmailListByCompanyIdAndEmailListName(companyId, emailListName);
        return emailList;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(AddEmailListDetails addEmailListDetails) throws ProcessFailed {
        EmailList emailList = new EmailList();
        if(addEmailListDetails.getCreatedDate() == null)
            emailList.setCreatedDate(new Date());
        else
            emailList.setCreatedDate(addEmailListDetails.getCreatedDate());
        emailList.setEmailListName(addEmailListDetails.getEmailListName());
        emailList.setDescription(addEmailListDetails.getEmailListDescription());
        emailList.setDefaultFromAddress(addEmailListDetails.getDefaultFromAddress());

        com.intbittech.model.EmailListType emailListType = new com.intbittech.model.EmailListType();
        emailListType.setTypeId(EmailListTypeConstants.valueOf(addEmailListDetails.getEmailListType()).getEmailListType());
        emailList.setFkTypeId(emailListType);

        Company company = new Company();
        company.setCompanyId(addEmailListDetails.getCompanyId());
        emailList.setFkCompanyId(company);
        Integer emailListId = emailListDao.save(emailList);
        emailList = getByEmailListId(emailListId);
        if(addEmailListDetails.getEmailListTags()!=null) {
            for(String tag : addEmailListDetails.getEmailListTags()) {
                EmailListTagLookup emailListTagLookup = new EmailListTagLookup();
                emailListTagLookup.setFkEmailListId(emailList);
                EmailListTag emailListTag = new EmailListTag();
                emailListTag.setTagId(EmailListTagConstants.valueOf(tag).getEmailListTag());
                emailListTagLookup.setFkEmailListTagId(emailListTag);
                emailListTagLookupService.save(emailListTagLookup);
            }
        }
        return emailListId;
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailList emailList) throws ProcessFailed {
        emailListDao.update(emailList);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailListId) throws ProcessFailed {
        EmailList emailList = emailListDao.getByEmailListId(emailListId);
        if (emailList == null) {
            throw new ProcessFailed(messageSource.getMessage("email_list_not_found", new String[]{}, Locale.US));
        }
        emailListDao.delete(emailList);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailList> getEmailListByCompanyId(Integer companyId) throws ProcessFailed {
        List<EmailList> emailLists = emailListDao.getEmailListByCompanyId(companyId);
        if (emailLists == null) {
            throw new ProcessFailed(messageSource.getMessage("email_lists_not_found", new String[]{}, Locale.US));
        }
        return emailLists;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailList> getEmailListByCompanyIdAndType(Integer companyId, Integer typeId) throws ProcessFailed {
        List<EmailList> emailLists = emailListDao.getEmailListByCompanyIdAndType(companyId, typeId);
        if (emailLists == null) {
            throw new ProcessFailed(messageSource.getMessage("email_lists_not_found", new String[]{}, Locale.US));
        }
        return emailLists;
    }
}
