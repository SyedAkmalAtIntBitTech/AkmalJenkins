/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import social.controller.ScheduleFacebookPost;
import social.controller.ScheduleTwitterPost;
import util.DateTimeUtil;

/**
 *
 * @author AR
 */
class SocialPostScheduler {

    public static final Logger logger = Logger.getLogger(util.Utility.getClassName(SocialPostScheduler.class));

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    
    private ScheduleTwitterPost twitterPostCallable;
    private ScheduleFacebookPost facebookPostCallable;
    
    public void startTwitterScheduler() {
        
        try {
            if (twitterPostCallable != null) {
                twitterPostCallable.terminateThread();
            }
            twitterPostCallable = new ScheduleTwitterPost();
            Date nextTwitterPostDate = twitterPostCallable.call();
            long nextExecution = DateTimeUtil.differenceCurrentTime(nextTwitterPostDate);
            scheduler.schedule(twitterPostCallable, nextExecution, TimeUnit.SECONDS);
            ApplicationContextListener.refreshTwitterScheduler();
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stopScheduler() {
        if (scheduler != null) {
            twitterPostCallable.terminateThread();
            facebookPostCallable.terminateThread();
            scheduler.shutdownNow();
        }
    }

    void startFacebookScheduler() {
        try {
            if (facebookPostCallable != null) {
                facebookPostCallable.terminateThread();
            }
            facebookPostCallable = new ScheduleFacebookPost();
            Date nextFBPostDate = facebookPostCallable.call();
            //the difference between the current time and next time needs to go in here.
            long nextExecution = DateTimeUtil.differenceCurrentTime(nextFBPostDate);
            scheduler.schedule(facebookPostCallable, nextExecution, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void startEmailScheduler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
