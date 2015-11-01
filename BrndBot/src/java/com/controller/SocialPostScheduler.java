/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import social.controller.ScheduleTwitterPost;

/**
 *
 * @author AR
 */
class SocialPostScheduler {

    public static final Logger logger = Logger.getLogger(util.Utility.getClassName(SocialPostScheduler.class));

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    
    private ScheduleTwitterPost twitterPostCallable;
    
    public void startTwitterScheduler() {
        
        try {
            twitterPostCallable = new ScheduleTwitterPost();
            Date nextTwitterPostDate = twitterPostCallable.call();
            scheduler.schedule(twitterPostCallable, 0, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stopScheduler() {
        if (scheduler != null) {
//            twitterPostCallable.terminateThread();
            scheduler.shutdownNow();
        }
    }
    
}
