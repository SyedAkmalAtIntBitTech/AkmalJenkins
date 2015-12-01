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
import social.controller.ScheduleAnEmail;
import social.controller.ScheduleAnRecuringEmail;
import social.controller.ScheduleFacebookPost;
import social.controller.ScheduleTwitterPost;
import util.DateTimeUtil;

/**
 *
 * @author AR
 */
public class SocialPostScheduler {

    public static final Logger logger = Logger.getLogger(util.Utility.getClassName(SocialPostScheduler.class));
    public static final int DefaultPollingInterval = 1;//5 mins
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    private ScheduleTwitterPost twitterPostCallable;
    private ScheduleFacebookPost facebookPostCallable;
    private ScheduleAnEmail scheduleEmailCallable;
    private ScheduleAnRecuringEmail scheduleRecurringEmailCallable;

    public void startTwitterScheduler() {

        try {
            if (twitterPostCallable != null) {
                twitterPostCallable.terminateThread();
            }
            twitterPostCallable = new ScheduleTwitterPost();
            Date nextTwitterPostDate = twitterPostCallable.call();
            long nextExecution = DateTimeUtil.differenceCurrentTime(nextTwitterPostDate);
            if (nextExecution < 0) {
                nextExecution = 20;

            }

            scheduler.schedule(twitterPostCallable, nextExecution, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stopScheduler() {
        if (scheduler != null) {
            twitterPostCallable.terminateThread();
            facebookPostCallable.terminateThread();
            scheduleEmailCallable.terminateThread();
            scheduleRecurringEmailCallable.terminateThread();
            scheduler.shutdownNow();
        }
    }

    void startFacebookScheduler() {
        try {
            if (facebookPostCallable != null) {
                facebookPostCallable.terminateThread();
            }

            logger.log(Level.INFO, "Started FB Scheduler");

            facebookPostCallable = new ScheduleFacebookPost();

           

            scheduler.scheduleAtFixedRate(facebookPostCallable, 10 ,60, TimeUnit.SECONDS);
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
            Date nextFBPostDate = scheduleEmailCallable.call();
            //the difference between the current time and next time needs to go in here.
            long nextExecution = DateTimeUtil.differenceCurrentTime(nextFBPostDate);
            logger.log(Level.INFO, "Next Email Execution after " + nextExecution + " seconds");
            if (nextExecution < 0) {
                nextExecution = 20;

            }

            scheduler.schedule(scheduleEmailCallable, nextExecution, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void startRecurringEmailScheduler() {
        try {
            if (scheduleRecurringEmailCallable != null) {
                scheduleRecurringEmailCallable.terminateThread();
            }
            scheduleRecurringEmailCallable = new ScheduleAnRecuringEmail();
            Date nextFBPostDate = scheduleRecurringEmailCallable.call();
            //the difference between the current time and next time needs to go in here.
            long nextExecution = DateTimeUtil.differenceCurrentTimeRecuring(nextFBPostDate);
            if (nextExecution < 0) {
                nextExecution = 20;

            }

            scheduler.schedule(scheduleRecurringEmailCallable, nextExecution, TimeUnit.SECONDS);
        } catch (Exception ex) {
            Logger.getLogger(SocialPostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
