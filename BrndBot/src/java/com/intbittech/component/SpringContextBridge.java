/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.component;

import com.intbittech.services.CompanyPreferencesService;
import com.intbittech.services.EmailListService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
* Register this SpringContextBridge as a Spring Component.
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) 
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * A static method to lookup the SpringContextBridgedServices Bean in 
     * the applicationContext. It is basically an instance of itself, which 
     * was registered by the @Component annotation.
     *
     * @return the SpringContextBridgedServices, which exposes all the 
     * Spring services that are bridged from the Spring context.
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
}