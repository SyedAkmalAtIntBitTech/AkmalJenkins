/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ContactsDao;
import com.intbittech.dao.UnsubscribedEmailCompanyDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ContactEmailListLookup;
import com.intbittech.model.Contacts;
import com.intbittech.model.EmailList;
import com.intbittech.model.UnsubscribedEmails;
import com.intbittech.modelmappers.ContactDetails;
import com.intbittech.services.ContactEmailListLookupService;
import com.intbittech.services.ContactsService;
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
public class ContactsServiceImpl implements ContactsService{
    
    private static Logger logger = Logger.getLogger(ContactsServiceImpl.class);
    
    @Autowired
    private ContactsDao contactsDao;
    
    @Autowired
    private UnsubscribedEmailsService unsubscribedEmailsService;
    
    @Autowired
    private ContactEmailListLookupService contactEmailListLookupService;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private UnsubscribedEmailCompanyDao unsubscribedEmailCompanyDao;
    
     /**
     * {@inheritDoc}
     */
    public Contacts getByContactsId(Integer contactId) throws ProcessFailed {
        Contacts contact = contactsDao.getByContactsId(contactId);
        if(contact == null){
           throw new ProcessFailed(messageSource.getMessage("contacts_not_found",new String[]{}, Locale.US));
        }
           return contact;
    }
    
    /**
     * {@inheritDoc}
     */
    public Contacts getContactByEmailAddress(String emailAddress) throws ProcessFailed {
        Contacts contact = contactsDao.getContactByEmailAddress(emailAddress);
        return contact;
    }
    
    /**
     * {@inheritDoc}
     */
    public Integer addContact(ContactDetails contactDetails) {
        Contacts contact = getContactByEmailAddress(contactDetails.getEmailAddress());
        Integer contactId = 0;
        if(contact == null) {
            contact = new Contacts();
            contact.setEmailAddress(contactDetails.getEmailAddress());
            contact.setFirstName(contactDetails.getFirstName());
            contact.setLastName(contactDetails.getLastName());
            if(contactDetails.getAddedDate() == null)
                contact.setCreatedDate(new Date());
            else
                contact.setCreatedDate(contactDetails.getAddedDate());
            contactId = save(contact);
        } else {
            contactId = contact.getContactId();
        }
        contact = new Contacts();
        contact.setContactId(contactId);
        //TODO ilyas Save or update
        ContactEmailListLookup contactEmailListLookup = contactEmailListLookupService.getByEmailListIdAndContactId(contactDetails.getEmailListId(), contactId);
        if(contactEmailListLookup == null){
            contactEmailListLookup = new ContactEmailListLookup();
            contactEmailListLookup.setFkContactId(contact);
            EmailList emailList = new EmailList();
            emailList.setEmailListId(contactDetails.getEmailListId());
            contactEmailListLookup.setFkEmailListId(emailList);
        }
        //checks if contact is unsubscribed, base on that sets below value
        Boolean isUnsubscribed = Boolean.FALSE;
        UnsubscribedEmails unsubscribedEmails = unsubscribedEmailsService.getByUnsubscribedEmailsAddress(contactDetails.getEmailAddress());
        Boolean isEmailUnsubscribed = unsubscribedEmailCompanyDao.isEmailUnsubscribed(contactDetails.getCompanyId(), unsubscribedEmails.getUnsubscribedEmailId());
        contactEmailListLookup.setUnsubscribed(isEmailUnsubscribed);
        contactEmailListLookup.setAddedDate(new Date());

        contactEmailListLookupService.saveOrUpdate(contactEmailListLookup);
        
        
            
        return contactId;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Contacts contact) throws ProcessFailed {
        return  contactsDao.save(contact);
    }

     /**
     * {@inheritDoc}
     */
    public void update(Contacts contact) throws ProcessFailed {
        contactsDao.update(contact);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer contactId) throws ProcessFailed {
       Contacts contact = contactsDao.getByContactsId(contactId);
        if(contact == null){
            throw new ProcessFailed(messageSource.getMessage("contacts_not_found",new String[]{}, Locale.US));
        }
            contactsDao.delete(contact);
    }
    
}
