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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author AR
 */
public class MindbodyEmailListScheduler {

    public static final Logger logger = Logger.getLogger(util.Utility.getClassName(ApplicationContextListener.class));

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    
    void startScheduler() {
        
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("America/New_York");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNext5 ;
        zonedNext5 = zonedNow.withHour(4).withMinute(0).withSecond(0);
        if(zonedNow.compareTo(zonedNext5) > 0)
            zonedNext5 = zonedNext5.plusDays(1);

        Duration duration = Duration.between(zonedNow, zonedNext5);
        long initalDelay = duration.getSeconds();
        long periodicTime = 24*60*60;
        initalDelay = 0;
        periodicTime =  24*60*60;
        Runnable mindbodyEmailListProcessor = new MindbodyEmailListProcessor();
        scheduler.scheduleAtFixedRate(mindbodyEmailListProcessor, initalDelay, periodicTime, TimeUnit.SECONDS);
        //initial delay is to make sure the program runs at 4 in the morning always.
        //24*60*60 splits fixed the interval time to 24 hours
    }

    void stopScheduler() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
    
}
