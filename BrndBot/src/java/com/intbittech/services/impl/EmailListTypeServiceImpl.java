/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.EmailListTypeDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.EmailListType;
import com.intbittech.services.EmailListTypeService;
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
public class EmailListTypeServiceImpl implements  EmailListTypeService{
    
    private static Logger logger = Logger.getLogger(EmailListTypeServiceImpl.class);
   
    @Autowired
    private EmailListTypeDao emailListTypeDao;
    
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public EmailListType getByEmailListTypeId(Integer emailListTypeId) throws ProcessFailed {
        EmailListType emailListType = emailListTypeDao.getByEmailListTypeId(emailListTypeId);
        if(emailListType == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_type_not_found",new String[]{}, Locale.US));
        }
            return emailListType;
    }

   /**
     * {@inheritDoc}
     */
    public Integer save(EmailListType emailListType) throws ProcessFailed {
        return  emailListTypeDao.save(emailListType);
    }

   /**
     * {@inheritDoc}
     */
    public void update(EmailListType emailListType) throws ProcessFailed {
        emailListTypeDao.update(emailListType);
    }

   /**
     * {@inheritDoc}
     */
    public void delete(Integer emailListTypeId) throws ProcessFailed {
       EmailListType emailListType = emailListTypeDao.getByEmailListTypeId(emailListTypeId);
        if(emailListType == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_type_not_found",new String[]{}, Locale.US));
        }
            emailListTypeDao.delete(emailListType);
    }

   
   /**
     * {@inheritDoc}
     */
    public List<EmailListType> getAllEmailListType() throws ProcessFailed {
        List <EmailListType> emailListTypeList = emailListTypeDao.getAllEmailListType();
        if(emailListTypeList == null){
            throw new ProcessFailed(messageSource.getMessage("email_list_type_not_found",new String[]{}, Locale.US));
        }
            return emailListTypeList;
    }
}
