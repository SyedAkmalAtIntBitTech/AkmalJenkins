/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author AR
 */
public class ApplicationContextListener implements ServletContextListener {

    private static ServletContextEvent servletContextEvent;

    public static final Logger logger = Logger.getLogger(util.Utility.getClassName(ApplicationContextListener.class));
    static ApplicationContextListener applicationContextListener;

    public static ApplicationContextListener getApplicationContextListener() {
        return applicationContextListener;
    }

    public static ServletContext getApplicationServletContext() {
        return servletContextEvent.getServletContext();
    }

    public static void refreshAllSchedulers() {
        logger.log(Level.INFO, "Refresh All Schedulers");
        refreshTwitterScheduler();
        refreshEmailRecuringScheduler();
        refreshEmailScheduler();
        refreshFacebookScheduler();
    }
    
    public static void refreshTwitterScheduler() {
        logger.log(Level.INFO, "Refresh Twitter Scheduler");
//        getApplicationContextListener().getSocialPostScheduler().startTwitterScheduler();
    }

    public static void refreshFacebookScheduler() {
        logger.log(Level.INFO, "Refresh FB Scheduler");
//        getApplicationContextListener().getSocialPostScheduler().startFacebookScheduler();
    }

    public static void refreshEmailScheduler() {
        logger.log(Level.INFO, "Refresh EMAIL Scheduler");
//        getApplicationContextListener().getSocialPostScheduler().startEmailScheduler();
    }

    public static void refreshEmailRecuringScheduler() {
        logger.log(Level.INFO, "Refresh Recurring EMAIL Scheduler");
//        getApplicationContextListener().getSocialPostScheduler().startRecurringEmailScheduler();
    }

    private MindbodyEmailListScheduler mindbodyEmailListScheduler;
    private SocialPostScheduler socialPostScheduler;

    public SocialPostScheduler getSocialPostScheduler() {
        return socialPostScheduler;
    }

    public void setSocialPostScheduler(SocialPostScheduler socialPostScheduler) {
        this.socialPostScheduler = socialPostScheduler;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.log(Level.INFO, "Application Deployed");
        servletContextEvent = sce;
        applicationContextListener = this;
//        mindbodyEmailListScheduler = new MindbodyEmailListScheduler();
//        mindbodyEmailListScheduler.startScheduler();

        logger.log(Level.INFO, "Started Schedulers");

        socialPostScheduler = new SocialPostScheduler();
        socialPostScheduler.startTwitterScheduler();
        socialPostScheduler.startFacebookScheduler();
        socialPostScheduler.startEmailScheduler();
        socialPostScheduler.startRecurringEmailScheduler();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.log(Level.INFO, "Application Un Deployed");
        mindbodyEmailListScheduler.stopScheduler();
        socialPostScheduler.stopScheduler();
        applicationContextListener = null;
        servletContextEvent = null;
    }
}
