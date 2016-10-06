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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public List<ContactEmailListLookup> getContactsByEmailListNameAndCompanyId(String emailListName, Integer companyId) throws ProcessFailed {
        String contactsForEmailList = "";
        EmailList emailList = emailListService.getEmailListByCompanyIdAndEmailListName(companyId, emailListName);
        List<ContactEmailListLookup> contactsList = new ArrayList<>();
        if (emailList != null) {
            contactsList = getContactsByEmailListId(emailList.getEmailListId());
        }

       return contactsList;
    }

    /**
     * {@inheritDoc}
     */
    public List<ContactEmailListLookup> getContactsByEmailListNameAndCompanyIdForToday(String emailListName, Integer companyId, Integer days) throws ProcessFailed, ParseException {
        EmailList emailList = emailListService.getEmailListByCompanyIdAndEmailListName(companyId, emailListName);
        List<ContactEmailListLookup> contactsList = new ArrayList<>();
        List<ContactEmailListLookup> returnContactsList = new ArrayList<>();
        if (emailList != null) {
            contactsList = getContactsByEmailListId(emailList.getEmailListId());
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (contactsList != null) {
            for (ContactEmailListLookup contact : contactsList) {
                String createDate = contact.getAddedDate().toString();
                Date date = formatter.parse(createDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, days);
                String postDate = formatter.format(cal.getTime());
                Date todayDate = Calendar.getInstance().getTime();
                String currentDateString = formatter.format(todayDate);
                if (postDate.equalsIgnoreCase(currentDateString)) {
                    returnContactsList.add(contact);
                }
            }
        }

       return returnContactsList;
    }

    /**
     * {@inheritDoc}
     */
    public ContactEmailListLookup getByEmailListIdAndContactId(Integer emailListId, Integer contactId) throws ProcessFailed {
        ContactEmailListLookup contact = contactEmailListLookupDao.getByEmailListIdAndContactId(emailListId, contactId);
        return contact;
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
