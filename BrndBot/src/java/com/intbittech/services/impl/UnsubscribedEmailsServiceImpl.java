/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.UnsubscribedEmailsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UnsubscribedEmails;
import com.intbittech.services.UnsubscribedEmailsService;
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
public class UnsubscribedEmailsServiceImpl implements UnsubscribedEmailsService{
    
      private static Logger logger = Logger.getLogger(UnsubscribedEmailsServiceImpl.class);
    
    @Autowired
    private UnsubscribedEmailsDao unsubscribedEmailsDao;
    
    @Autowired
    private MessageSource messageSource;

   /**
     * {@inheritDoc}
     */
    public UnsubscribedEmails getByUnsubscribedEmailsId(Integer unsubscribedEmailsId) throws ProcessFailed {
        UnsubscribedEmails unsubscribedEmails = unsubscribedEmailsDao.getByUnsubscribedEmailsId(unsubscribedEmailsId);
        if(unsubscribedEmails == null){
            throw new ProcessFailed(messageSource.getMessage("unsubscribed_emails_not_found",new String[]{}, Locale.US));
        }
            return unsubscribedEmails;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed {
        return unsubscribedEmailsDao.save(unsubscribedEmails);
    }

    /**
     * {@inheritDoc}
     */
    public void update(UnsubscribedEmails unsubscribedEmails) throws ProcessFailed {
        unsubscribedEmailsDao.update(unsubscribedEmails);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer unsubscribedEmailsId) throws ProcessFailed {
         UnsubscribedEmails unsubscribedEmails = unsubscribedEmailsDao.getByUnsubscribedEmailsId(unsubscribedEmailsId);
        if(unsubscribedEmails == null){
            throw new ProcessFailed(messageSource.getMessage("unsubscribed_emails_not_found",new String[]{}, Locale.US));
        }
            unsubscribedEmailsDao.delete(unsubscribedEmails);
    }
    
}
