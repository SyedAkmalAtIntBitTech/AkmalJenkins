/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author AR
 */
public class DateTimeUtil {

    public static boolean timeEqualsCurrentTime(Date datetime) {
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
		if (currentDateString.equals(receivedDateString)) {
		flag = true;
		}
		return flag;
		}
    public static long differenceCurrentTime(Date nextPostDate) {
        //Make sure time zone is the same when difference is taken. Return milliseconds.
        return 0;
    }
    }

   
    
    
    

