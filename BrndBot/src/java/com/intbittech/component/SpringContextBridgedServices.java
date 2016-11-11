/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.component;

import com.intbittech.marketing.service.ScheduledEntityListService;
import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.ContactEmailListLookupService;
import com.intbittech.services.ContactsService;
import com.intbittech.services.EmailListService;
import com.intbittech.services.EmailListTagLookupService;
import com.intbittech.services.EmailListTagService;
import com.intbittech.services.PushedScheduledActionCompaniesService;
import com.intbittech.services.PushedScheduledEntityListService;
import com.intbittech.services.SendEmailService;
import com.intbittech.services.UnsubscribedEmailsService;
import com.intbittech.services.UserPreferencesService;
import com.intbittech.social.PostToFacebook;
import com.intbittech.social.PostToTwitter;

/**
 * This interface represents a list of Spring Beans (services) which need to be referenced from a non Spring class.
 * @author ar
 */
public interface SpringContextBridgedServices {
    public CompanyPreferencesService getCompanyPreferencesService();
    public EmailListService getEmailListService();
    public ContactsService getContactsService();
    public ContactEmailListLookupService getContactEmailListLookupService();
    public UnsubscribedEmailsService getUnsubscribedEmailsService();
    public SendEmailService getSendEmailService();
    public UserPreferencesService getUserPreferencesService();
    public PostToTwitter getPostToTwitter();
    public PostToFacebook getPostToFacebook();
    public PushedScheduledEntityListService getPushedScheduledEntityListService();
    public PushedScheduledActionCompaniesService getPushedScheduledActionCompaniesService();
    public EmailListTagLookupService getEmailListTagLookupService();
    public EmailListTagService getEmailListTagService();
    public ScheduledEntityListService getScheduledEntityListService();
}
