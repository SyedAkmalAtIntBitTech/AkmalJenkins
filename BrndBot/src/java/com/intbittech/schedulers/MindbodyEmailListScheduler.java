/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.schedulers;

import com.controller.ApplicationContextListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AR
 */
public class MindbodyEmailListScheduler {

    public static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(ApplicationContextListener.class));

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    private MindbodyEmailListProcessor mindbodyEmailListRunnable;

    void startScheduler() {
        try {
            LocalDateTime localNow = LocalDateTime.now();
            ZoneId currentZone = ZoneId.of("America/New_York");
            ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
            ZonedDateTime zonedNext5;
            zonedNext5 = zonedNow.withHour(4).withMinute(0).withSecond(0);
            if (zonedNow.compareTo(zonedNext5) > 0) {
                zonedNext5 = zonedNext5.plusDays(1);
            }

            Duration duration = Duration.between(zonedNow, zonedNext5);
            long initalDelay = duration.getSeconds();
            long periodicTime = 24 * 60 * 60;
//            initalDelay = 0;
            mindbodyEmailListRunnable = new MindbodyEmailListProcessor();
            mindbodyEmailListRunnable.startThread();
            scheduler.scheduleAtFixedRate(mindbodyEmailListRunnable, initalDelay, periodicTime, TimeUnit.SECONDS);
            //initial delay is to make sure the program runs at 4 in the morning always.
            //24*60*60 splits fixed the interval time to 24 hours
        } catch (Exception ex) {
            Logger.getLogger(MindbodyEmailListScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stopScheduler() {
        try {
            if (scheduler != null) {
                mindbodyEmailListRunnable.terminateThread();
                scheduler.shutdownNow();
            }
        } catch (Exception ex) {
            Logger.getLogger(MindbodyEmailListScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
