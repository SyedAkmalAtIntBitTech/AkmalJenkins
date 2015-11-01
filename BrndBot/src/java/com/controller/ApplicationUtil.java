/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author AR
 */
class ApplicationUtil {
    
    static ApplicationContextListener applicationContextListener;
    
    public static void setApplicationContextListener(ApplicationContextListener listener){
        applicationContextListener = listener;
    }
    
    public static ApplicationContextListener getApplicationContextListener(){
        return applicationContextListener;
    }
    
}
