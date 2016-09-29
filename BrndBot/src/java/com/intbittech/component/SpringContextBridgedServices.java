/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.component;

import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.ContactEmailListLookupService;
import com.intbittech.services.ContactsService;
import com.intbittech.services.EmailListService;
import com.intbittech.services.UnsubscribedEmailsService;

/**
 * This interface represents a list of Spring Beans (services) which need to be referenced from a non Spring class.
 * @author ar
 */
public interface SpringContextBridgedServices {
    CompanyPreferencesService getCompanyPreferencesService();
    EmailListService getEmailListService();
    ContactsService getContactsService();
    ContactEmailListLookupService getContactEmailListLookupService();
    UnsubscribedEmailsService getUnsubscribedEmailsService();
}
