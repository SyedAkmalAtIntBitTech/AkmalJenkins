/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ContactEmailListLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ContactEmailListLookup;
import com.intbittech.model.EmailList;
import com.intbittech.model.UnsubscribedEmails;
import com.intbittech.services.ContactEmailListLookupService;
import com.intbittech.services.EmailListService;
import com.intbittech.services.UnsubscribedEmailsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
        
/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ContactEmailListLookupServiceImpl implements ContactEmailListLookupService {

    private static Logger logger = Logger.getLogger(ContactEmailListLookupServiceImpl.class);

    @Autowired
    private ContactEmailListLookupDao contactEmailListLookupDao;

    @Autowired
    private UnsubscribedEmailsService unsubscribedEmailsService;
    
    @Autowired
    private EmailListService emailListService;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public ContactEmailListLookup getByContactEmailListLookupId(Integer contactEmailListLookupId) throws ProcessFailed {
        ContactEmailListLookup contactEmailListLookup = contactEmailListLookupDao.getByContactEmailListLookupId(contactEmailListLookupId);
        if (contactEmailListLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("contact_email_list_tag_not_found", new String[]{}, Locale.US));
        }
        return contactEmailListLookup;
    }

    /**
     * {@inheritDoc}
     */
    public List<ContactEmailListLookup> getContactsByEmailListId(Integer emailListId) throws ProcessFailed {
        List<ContactEmailListLookup> contactsList = contactEmailListLookupDao.getContactsByEmailListId(emailListId);
        return contactsList;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getContactsByEmailListNameAndCompanyId(String emailListName,Integer companyId) throws ProcessFailed {
        String contactsForEmailList = "";
        EmailList emailList = emailListService.getEmailListByCompanyIdAndEmailListName(companyId, emailListName);
        List<ContactEmailListLookup> contactsList = new ArrayList<>();
        if(emailList != null) {
            contactsList = getContactsByEmailListId(emailList.getEmailListId());
            for(int i = 0; i<contactsList.size();i++) {
                if(contactsList.get(i).getUnsubscribed()) {
                    contactsList.remove(i);
                }
            }
        }
        if(contactsList != null) {
            contactsForEmailList = StringUtils.join(contactsList, ',');
        }
            
        return contactsForEmailList;
    }

    /**
     * {@inheritDoc}
     */
    public ContactEmailListLookup getByEmailListIdAndContactId(Integer emailListId, Integer contactId) throws ProcessFailed {
        ContactEmailListLookup contacts = contactEmailListLookupDao.getByEmailListIdAndContactId(emailListId, contactId);
        return contacts;
    }

    /**
     * {@inheritDoc}
     */
    public void updateUnsubscribedUserEmailLists(Integer companyId) throws ProcessFailed {
        List<ContactEmailListLookup> contactsList = contactEmailListLookupDao.getGeneralContactsByCompanyId(companyId);
        if (contactsList != null) {
            for (ContactEmailListLookup contact : contactsList) {
                UnsubscribedEmails unsubscribedEmails = unsubscribedEmailsService.getByUnsubscribedEmailsAddress(contact.getFkContactId().getEmailAddress());
                if (unsubscribedEmails != null) {
                    ContactEmailListLookup contactEmailListLookup = getByContactEmailListLookupId(contact.getContactLookupId());
                    contactEmailListLookup.setUnsubscribed(Boolean.TRUE);
                    update(contactEmailListLookup);
                }

            }
        }

    }

    /**
     * {@inheritDoc}
     */
    public void saveOrUpdate(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed {
        contactEmailListLookupDao.saveOrUpdate(contactEmailListLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void update(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed {
        contactEmailListLookupDao.update(contactEmailListLookup);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer contactEmailListLookupId) throws ProcessFailed {
        ContactEmailListLookup contactEmailListLookup = contactEmailListLookupDao.getByContactEmailListLookupId(contactEmailListLookupId);
        if (contactEmailListLookup == null) {
            throw new ProcessFailed(messageSource.getMessage("contact_email_list_tag_not_found", new String[]{}, Locale.US));
        }
        contactEmailListLookupDao.delete(contactEmailListLookup);
    }

}
