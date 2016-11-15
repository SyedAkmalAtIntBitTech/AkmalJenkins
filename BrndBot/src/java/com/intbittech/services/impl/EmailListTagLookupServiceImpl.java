/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailListTagLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListTagLookup;
import com.intbittech.services.EmailListTagLookupService;
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
public class EmailListTagLookupServiceImpl implements EmailListTagLookupService{
   
    private static Logger logger = Logger.getLogger(EmailListTagLookupServiceImpl.class);
    
    @Autowired
    private EmailListTagLookupDao emailListTagLookupDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailListTagLookup getByEmailListTagLookupId(Integer emailListTagLookupId) throws ProcessFailed {
         EmailListTagLookup emailListTagLookup = emailListTagLookupDao.getByEmailListTagLookupId(emailListTagLookupId);
        if(emailListTagLookup == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US)); 
        }
           
        return emailListTagLookup;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<EmailListTagLookup> getByEmailListTagLookupByEmailListId(Integer emailListId) throws ProcessFailed {
        List<EmailListTagLookup> emailListTagLookupList = emailListTagLookupDao.getByEmailListTagLookupByEmailListId(emailListId);
//        if(emailListTagLookupList == null){
//            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US)); 
//        }
           
        return emailListTagLookupList;
    }

   /**
     * {@inheritDoc}
     */
    public Integer save(EmailListTagLookup emailListTagLookup) throws ProcessFailed {
        return emailListTagLookupDao.save(emailListTagLookup);
    }
    
    /**
     * {@inheritDoc}
     */
    public void saveOrUpdate(EmailListTagLookup emailListTagLookup) throws ProcessFailed {
        emailListTagLookupDao.saveOrUpdate(emailListTagLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void update(EmailListTagLookup emailListTagLookup) throws ProcessFailed {
        emailListTagLookupDao.update(emailListTagLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer emailListTagLookupId) throws ProcessFailed {
       EmailListTagLookup emailListTagLookup = emailListTagLookupDao.getByEmailListTagLookupId(emailListTagLookupId);
        if(emailListTagLookup == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US));
        }
        emailListTagLookupDao.delete(emailListTagLookup);

    }

    @Override
    public EmailListTagLookup getByEmailListTagLookupByEmailListIdAndCompanyId(Integer emailListId, Integer CompanyId) throws ProcessFailed {
       EmailListTagLookup emailListTagLookup = emailListTagLookupDao.getEmailListTagLookupByEmailListTagIdAndCompanyId(emailListId,CompanyId);
        if(emailListTagLookup == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_tag_not_found",new String[]{}, Locale.US)); 
        }
        
        return emailListTagLookup;        
    }

}
