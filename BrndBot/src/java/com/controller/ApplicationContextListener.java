/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author AR
 */
public class ApplicationContextListener implements ServletContextListener {

    public static final Logger logger = Logger.getLogger(util.Utility.getClassName(ApplicationContextListener.class));

    private MindbodyEmailListScheduler mindbodyEmailListScheduler;
    private TwitterPostScheduler emailListScheduler;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.log(Level.INFO, "Application Deployed");
//        mindbodyEmailListScheduler = new MindbodyEmailListScheduler();
//        mindbodyEmailListScheduler.startScheduler();
        
//        emailListScheduler = new TwitterPostScheduler();
//        emailListScheduler.startScheduler();
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.log(Level.INFO, "Application Un Deployed");
//        mindbodyEmailListScheduler.stopScheduler();
//        emailListScheduler.stopScheduler();        
    }
}
