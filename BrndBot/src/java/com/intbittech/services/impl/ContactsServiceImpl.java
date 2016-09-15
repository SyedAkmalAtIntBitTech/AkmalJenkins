/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ContactsDao;
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
    
     /**
     * {@inheritDoc}
     */
    public Contacts getByContactsId(Integer contactsId) throws ProcessFailed {
        Contacts contacts = contactsDao.getByContactsId(contactsId);
        if(contacts == null){
           throw new ProcessFailed(messageSource.getMessage("contacts_not_found",new String[]{}, Locale.US));
        }
           return contacts;
    }
    
    /**
     * {@inheritDoc}
     */
    public Contacts getContactByEmailAddress(String emailAddress) throws ProcessFailed {
        Contacts contacts = contactsDao.getContactByEmailAddress(emailAddress);
        return contacts;
    }
    
    /**
     * {@inheritDoc}
     */
    public Integer addContact(ContactDetails contactDetails) {
        Contacts contacts = getContactByEmailAddress(contactDetails.getEmailAddress());
        Integer contactId = 0;
        if(contacts == null) {
            contacts = new Contacts();
            contacts.setEmailAddress(contactDetails.getEmailAddress());
            contacts.setFirstName(contactDetails.getFirstName());
            contacts.setLastName(contactDetails.getLastName());
            if(contactDetails.getAddedDate() == null)
                contacts.setCreatedDate(new Date());
            else
                contacts.setCreatedDate(contactDetails.getAddedDate());
            contactId = save(contacts);
        } else {
            contactId = contacts.getContactId();
        }
        contacts = new Contacts();
        contacts.setContactId(contactId);
        //TODO ilyas Save or update
        ContactEmailListLookup contactEmailListLookup = contactEmailListLookupService.getByEmailListIdAndContactId(contactDetails.getEmailListId(), contactId);
        if(contactEmailListLookup == null){
            contactEmailListLookup = new ContactEmailListLookup();
            contactEmailListLookup.setFkContactId(contacts);
            EmailList emailList = new EmailList();
            emailList.setEmailListId(contactDetails.getEmailListId());
            contactEmailListLookup.setFkEmailListId(emailList);
        }
        //checks if contact is unsubscribed, base on that sets below value
        Boolean isUnsubscribed = Boolean.FALSE;
        UnsubscribedEmails unsubscribedEmails = unsubscribedEmailsService.getByUnsubscribedEmailsAddress(contactDetails.getEmailAddress());
        if(unsubscribedEmails != null) {
            isUnsubscribed = Boolean.TRUE;
            contactEmailListLookup.setUnsubscribed(isUnsubscribed);
            contactEmailListLookup.setAddedDate(new Date());

            contactEmailListLookupService.saveOrUpdate(contactEmailListLookup);
        }
        
            
        return contactId;
    }

    /**
     * {@inheritDoc}
     */
    public Integer save(Contacts contacts) throws ProcessFailed {
        return  contactsDao.save(contacts);
    }

     /**
     * {@inheritDoc}
     */
    public void update(Contacts contacts) throws ProcessFailed {
        contactsDao.update(contacts);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Integer contactsId) throws ProcessFailed {
       Contacts contacts = contactsDao.getByContactsId(contactsId);
        if(contacts == null){
            throw new ProcessFailed(messageSource.getMessage("contacts_not_found",new String[]{}, Locale.US));
        }
            contactsDao.delete(contacts);
    }
    
}
