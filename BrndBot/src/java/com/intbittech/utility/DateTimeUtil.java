/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author AR/Haider Khan @ Intbit
 */
public class DateTimeUtil {

    static final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
    public static final Logger logger = Logger.getLogger(com.intbittech.utility.Utility.getClassName(DateTimeUtil.class));

    public static boolean timeEqualsCurrentTime(Date datetime, Integer companyProgramId) {
        if (companyProgramId != 0) {
            //Checks if the currect date is DST if yes then -1 hour
            datetime = isDSTCalculate(datetime);
        }
        //Make sure time zone is the same when comparison is done. Time doesnt have to be equal to the second. Just the minute is enough.
        boolean flag = false;
        String dateFormat = "z";
        SimpleDateFormat timeZoneFormat = new SimpleDateFormat(dateFormat);
        String timeZoneStr = timeZoneFormat.format(datetime);
        String formatStr = "hh:mm a";
        Date currentdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        format.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String currentDateString = format.format(currentdate);
        String receivedDateString = format.format(datetime);
        if (currentDateString.equals(receivedDateString)) {
            flag = true;
        }
        return flag;
    }

    public static Date isDSTCalculate(Date datetime) {
        Date returnDate = datetime;
        if (TimeZone.getDefault().inDaylightTime(new Date())) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(datetime);
            cal.add(Calendar.HOUR, -1);
            returnDate = cal.getTime();
        }
        return returnDate;
    }

    public static boolean dateEqualsCurrentDate(Date datetime) throws ParseException {
        //Make sure time zone is the same when comparison is done. Time doesnt have to be equal to the second. Just the minute is enough.
        boolean flag = false;
        String dateFormat = "z";
        SimpleDateFormat timeZoneFormat = new SimpleDateFormat(dateFormat);
        String timeZoneStr = timeZoneFormat.format(datetime);
        String formatStr = "yyyy-MM-dd hh:mm a";
        Date currentdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        format.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String currentDateString = format.format(currentdate);
        String receivedDateString = format.format(datetime);

        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format1.parse(currentDateString);
        Date date2 = format1.parse(receivedDateString);
        if (DateUtils.isSameDay(date1, date2)) {
            flag = true;
        }
        return flag;
    }

    public static long differenceCurrentTime(Date nextPostDate) throws Exception {
        //Make sure time zone is the same when difference is taken. Return milliseconds.

        String dateFormat = "z";
        SimpleDateFormat timeZoneFormat = new SimpleDateFormat(dateFormat);
        String timeZoneStr = timeZoneFormat.format(new Date());
        String formatStr = "yyyy-MM-dd hh:mm a";
        Date currentdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        format.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String StringNextPostDate = format.format(nextPostDate);
        String currentDateString = format.format(currentdate);

        Date nextPostDateTime = format.parse(StringNextPostDate);
        Date currentDateTime = format.parse(currentDateString);
        logger.log(Level.INFO, "Next PostDateTime:" + StringNextPostDate + "currentDateTime:" + currentDateString);
        Long differenceCurrentTime = (nextPostDateTime.getTime() - currentDateTime.getTime()) / 1000;

        return differenceCurrentTime;
    }

    public static long differenceCurrentTimeRecurring(Date nextPostDate) throws Exception {
        //Make sure time zone is the same when difference is taken. Return milliseconds.

        String dateFormat = "z";
        SimpleDateFormat timeZoneFormat = new SimpleDateFormat(dateFormat);
        String timeZoneStr = timeZoneFormat.format(new Date());
        String formatStr = "hh:mm a";
        Date currentdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        format.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String StringNextPostDate = format.format(nextPostDate);
        String currentDateString = format.format(currentdate);
        Date nextPostDateTime = format.parse(StringNextPostDate);
        Date currentDateTime = format.parse(currentDateString);

        Long differenceCurrentTime = (nextPostDateTime.getTime() - currentDateTime.getTime()) / 1000;

        return differenceCurrentTime;
    }

    public static Date getDatePlusMins(int minsToAdd) {
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        return new Date(t + (minsToAdd * ONE_MINUTE_IN_MILLIS));
    }

    public static Date getCurrentGMTDate() {
        Date date = new Date();
        return date;
    }

    public static Date daysAgo(Integer days) {
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, days);
        Date returnDate = cal.getTime();
        return returnDate;
    }
}
