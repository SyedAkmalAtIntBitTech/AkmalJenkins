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
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Register this SpringContextBridge as a Spring Component.
 *
 * @author ar
 */
@Component
public class SpringContextBridge
        implements SpringContextBridgedServices, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Autowired
    private CompanyPreferencesService companyPreferencesService; //Autowire the CompanyPreferencesService
    @Autowired
    private EmailListService emailListService;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private ContactEmailListLookupService contactEmailListLookupService;
    @Autowired
    private UnsubscribedEmailsService unsubscribedEmailsService;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private UserPreferencesService userPreferencesService;
    @Autowired
    private PostToTwitter postToTwitter;
    @Autowired
    private PostToFacebook postToFacebook;
    @Autowired
    private PushedScheduledEntityListService pushedScheduledEntityListService;

    @Autowired
    private PushedScheduledActionCompaniesService pushedScheduledActionCompaniesService;
    @Autowired
    private EmailListTagLookupService emailListTagLookupService;
    @Autowired
    private EmailListTagService emailListTagService;
    @Autowired
    private ScheduledEntityListService scheduledEntityListService;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * A static method to lookup the SpringContextBridgedServices Bean in the
     * applicationContext. It is basically an instance of itself, which was
     * registered by the @Component annotation.
     *
     * @return the SpringContextBridgedServices, which exposes all the Spring
     * services that are bridged from the Spring context.
     */
    public static SpringContextBridgedServices services() {
        return applicationContext.getBean(SpringContextBridgedServices.class);
    }

    @Override
    public CompanyPreferencesService getCompanyPreferencesService() {
        return companyPreferencesService;
    }

    @Override
    public EmailListService getEmailListService() {
        return emailListService;
    }

    @Override
    public ContactsService getContactsService() {
        return contactsService;
    }

    @Override
    public ContactEmailListLookupService getContactEmailListLookupService() {
        return contactEmailListLookupService;
    }

    @Override
    public UnsubscribedEmailsService getUnsubscribedEmailsService() {
        return unsubscribedEmailsService;
    }

    @Override
    public SendEmailService getSendEmailService() {
        return sendEmailService;
    }

    @Override
    public UserPreferencesService getUserPreferencesService() {
        return userPreferencesService;
    }
    
    @Override
    public PostToTwitter getPostToTwitter() {
        return postToTwitter;
    }
    
    @Override
    public PostToFacebook getPostToFacebook() {
        return postToFacebook;
    }
    
    @Override
    public PushedScheduledEntityListService getPushedScheduledEntityListService() {
        return pushedScheduledEntityListService;
    }
    
    @Override
    public PushedScheduledActionCompaniesService getPushedScheduledActionCompaniesService() {
        return pushedScheduledActionCompaniesService;
    }
    @Override
    public EmailListTagLookupService getEmailListTagLookupService(){
        return emailListTagLookupService;
    }

    @Override
    public EmailListTagService getEmailListTagService() {
        return emailListTagService;
    }
    @Override
    public ScheduledEntityListService getScheduledEntityListService(){
        return scheduledEntityListService;
    } 
}
