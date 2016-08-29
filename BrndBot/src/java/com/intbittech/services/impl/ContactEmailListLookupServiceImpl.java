/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ContactEmailListLookupDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.ContactEmailListLookup;
import com.intbittech.services.ContactEmailListLookupService;
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
public class ContactEmailListLookupServiceImpl implements ContactEmailListLookupService {

    private static Logger logger = Logger.getLogger(ContactEmailListLookupServiceImpl.class);

    @Autowired
    private ContactEmailListLookupDao contactEmailListLookupDao;

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
    public Integer save(ContactEmailListLookup contactEmailListLookup) throws ProcessFailed {
        return contactEmailListLookupDao.save(contactEmailListLookup);
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
