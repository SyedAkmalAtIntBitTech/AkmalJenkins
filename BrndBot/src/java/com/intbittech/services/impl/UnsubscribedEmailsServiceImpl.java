/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.UnsubscribedEmailCompanyDao;
import com.intbittech.dao.UnsubscribedEmailsDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Company;
import com.intbittech.model.UnsubscribedCompanyLookup;
import com.intbittech.model.UnsubscribedEmails;
import com.intbittech.services.UnsubscribedEmailsService;
import com.intbittech.utility.EmailValidator;
import java.util.Date;
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
public class UnsubscribedEmailsServiceImpl implements UnsubscribedEmailsService {

    private static Logger logger = Logger.getLogger(UnsubscribedEmailsServiceImpl.class);

    @Autowired
    private UnsubscribedEmailsDao unsubscribedEmailsDao;

    @Autowired
    private UnsubscribedEmailCompanyDao unsubscribedEmailCompanyDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public UnsubscribedEmails getByUnsubscribedEmailsId(Integer unsubscribedEmailsId) throws ProcessFailed {
        UnsubscribedEmails unsubscribedEmails = unsubscribedEmailsDao.getByUnsubscribedEmailsId(unsubscribedEmailsId);
        if (unsubscribedEmails == null) {
            throw new ProcessFailed(messageSource.getMessage("unsubscribed_emails_not_found", new String[]{}, Locale.US));
        }
        return unsubscribedEmails;
    }

    /**
     * {@inheritDoc}
     */
    public UnsubscribedEmails getByUnsubscribedEmailsAddress(String emailAddress) throws ProcessFailed {
        UnsubscribedEmails unsubscribedEmails = unsubscribedEmailsDao.getByUnsubscribedEmailsAddress(emailAddress);
        return unsubscribedEmails;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Integer companyId, List<String> emailList) throws ProcessFailed {
        EmailValidator emailValidator = new EmailValidator();
        for (int i = 0; i < emailList.size(); i++) {
            if (!emailValidator.validate(emailList.get(i))) {
                emailList.remove(i);
            }
        }
        for (int i = 0; i < emailList.size(); i++) {
            String emailAddress = emailList.get(i);
            Integer unsubscribedEmailId = 0;
            UnsubscribedEmails unsubscribedEmails = getByUnsubscribedEmailsAddress(emailAddress);
            if (unsubscribedEmails == null) {
                unsubscribedEmails = new UnsubscribedEmails();
                unsubscribedEmails.setEmailAddress(emailAddress);
                unsubscribedEmails.setCreatedDate(new Date());
                unsubscribedEmailId = unsubscribedEmailsDao.save(unsubscribedEmails);
            } else {
                unsubscribedEmailId = unsubscribedEmails.getUnsubscribedEmailId();
            }
            
            if(!unsubscribedEmailCompanyDao.isEmailUnsubscribed(companyId, emailAddress)) {
                UnsubscribedCompanyLookup unsubscribedCompanyLookup = new UnsubscribedCompanyLookup();
                unsubscribedEmails = new UnsubscribedEmails();
                unsubscribedEmails.setUnsubscribedEmailId(unsubscribedEmailId);
                unsubscribedCompanyLookup.setFkUnsubscribedEmailId(unsubscribedEmails);
                Company company = new Company();
                company.setCompanyId(companyId);
                unsubscribedCompanyLookup.setFkCompanyId(company);
                unsubscribedEmailCompanyDao.save(unsubscribedCompanyLookup);
            }

        }
        return 0;
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
        if (unsubscribedEmails == null) {
            throw new ProcessFailed(messageSource.getMessage("unsubscribed_emails_not_found", new String[]{}, Locale.US));
        }
        unsubscribedEmailsDao.delete(unsubscribedEmails);
    }

}
