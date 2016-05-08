/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.intbittech.social.ScheduleAnEmail;
import com.intbittech.social.ScheduleAnRecurringEmail;
import com.intbittech.social.ScheduleFacebookPost;
import com.intbittech.social.ScheduleTwitterPost;

/**
 *
 * @author AR
 */
public class SocialPostScheduler {

    public static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(SocialPostScheduler.class));
    public static final int DefaultPollingInterval = 60;//5 mins
    public static final int InitialDelayPollingInterval = 10;//5 mins
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    private ScheduleTwitterPost twitterPostCallable;
    private ScheduleFacebookPost facebookPostCallable;
    private ScheduleAnEmail scheduleEmailCallable;
    private ScheduleAnRecurringEmail scheduleRecurringEmailCallable;

    public void startTwitterScheduler() {

        try {
            if (twitterPostCallable != null) {
                twitterPostCallable.terminateThread();
            }
            twitterPostCallable = new ScheduleTwitterPost();
            logger.log(Level.INFO, "Started Twitter Scheduler");

            scheduler.scheduleAtFixedRate(twitterPostCallable, InitialDelayPollingInterval, DefaultPollingInterval, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stopScheduler() {
        try {
            if (scheduler != null) {
                twitterPostCallable.terminateThread();
                facebookPostCallable.terminateThread();
                scheduleEmailCallable.terminateThread();
                scheduleRecurringEmailCallable.terminateThread();
                scheduler.shutdownNow();
            }
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void startFacebookScheduler() {
        try {
            if (facebookPostCallable != null) {
                facebookPostCallable.terminateThread();
            }

            logger.log(Level.INFO, "Started FB Scheduler");

            facebookPostCallable = new ScheduleFacebookPost();

            scheduler.scheduleAtFixedRate(facebookPostCallable, InitialDelayPollingInterval, DefaultPollingInterval, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void startEmailScheduler() {
        try {
            if (scheduleEmailCallable != null) {
                scheduleEmailCallable.terminateThread();
            }
            logger.log(Level.INFO, "Started Email Scheduler");

            scheduleEmailCallable = new ScheduleAnEmail();
            scheduler.scheduleAtFixedRate(scheduleEmailCallable, InitialDelayPollingInterval, DefaultPollingInterval, TimeUnit.SECONDS);

        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void startRecurringEmailScheduler() {
        try {
            if (scheduleRecurringEmailCallable != null) {
                scheduleRecurringEmailCallable.terminateThread();
            }
            logger.log(Level.INFO, "Started Recurring Email Scheduler");

            scheduleRecurringEmailCallable = new ScheduleAnRecurringEmail();
            scheduler.scheduleAtFixedRate(scheduleRecurringEmailCallable, InitialDelayPollingInterval, DefaultPollingInterval, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
