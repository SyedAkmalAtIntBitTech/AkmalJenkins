/**
 * Created by AR on May 25, 2015
 * Copyright (c) 2015 IntBit Technologies. All rights reserved.
 *
 */

package com.intbittech.divtohtml;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Provides utility methods to manipulate string objects.
 * 
 * @author AR
 */
public final class StringUtil {

	/**
	 * TAG for log.
	 */
	@SuppressWarnings("unused")
	private static String TAG = "StringUtil";

	/**
	 * Prevents construction of this utility class.
	 */
	private StringUtil() {
		throw new UnsupportedOperationException("Cannot construct an utility class");
	}

	/**
	 * Checks if the given string is empty.
	 * 
	 * @param value
	 *            reference to the source string
	 * @return true, if the strings is either null or empty
	 */
	public static boolean isEmpty(final String value) {
		return (value == null || (value.trim().length() == 0));
	}

	

	/**
	 * Compares if the given strings are equal, ignoring case differences.
	 * 
	 * @param lhs
	 *            reference to the source string
	 * @param rhs
	 *            reference to the target string
	 * @return true, if the both strings are same either by reference or
	 *         intrinsic content.
	 */
	public static boolean isEqualIgnoreCase(final String lhs, final String rhs) {
		return (lhs == rhs || (lhs != null && rhs != null && lhs.equalsIgnoreCase(rhs)));
	}

	/**
	 * Holds reference to the US currency formatter.
	 */
	@SuppressWarnings("unused")
	private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);

	/**
	 * Holds reference to the US Decimal formatter.
	 */
	private static final DecimalFormat NUMBER_FORMATTER = new DecimalFormat("###,###,###");

	/**
	 * Formats the given amount into human readable string. The method ignores
	 * OS level regional settings, because all amounts are always in U.S
	 * dollars.
	 * 
	 * @param amount
	 *            specifies the U.S dollar amount to be formatted.
	 * 
	 * @return string containing the formatted U.S dollar amount.
	 */
	public static String formatUSD(final double amount) {
		return formatUSD(new Double(amount));
	}

	
	
	/**
	 * Format the given amount into human readable string. This is the textual
	 * formatting for a percent amount.
	 * 
	 * @param amount
	 *            specifies the U.S dollar amount to be formatted.
	 * 
	 * @return string containing the formatted percent amount.
	 */
	public static String formatPct(final Number amount) {
		// Append '%'.
		if (amount == null) {
			return "--";
		} else {
			return amount.doubleValue() + "%";
		}
	}

	/**
	 * Format the given amount into human readable string. This is the textual
	 * formatting for a percent amount.
	 * 
	 * @param value
	 *            specifies the U.S dollar amount to be formatted.
	 * 
	 * @return string containing the percent amount.
	 */
	public static String formatPercent(final Number value) {
		if (value == null) {
			return "--";
		} else {
			return value.doubleValue() + "%";
		}
	}

	/**
	 * Converts simple Number object to String for display purposes.
	 * 
	 * @param object
	 *            Contains the value that needs to be converted.
	 * @return Returns the String value.
	 */
	public static CharSequence convertToString(final Number object) {
		if (object == null) {
			return "--";
		}
		return object.toString();
	}

	/**
	 * Format numbers for display as comma separated groups.
	 * 
	 * @param object
	 *            Contains the value that needs to be converted.
	 * @return Returns comma separated formatted values.
	 */
	public static String formatIncludeCommas(final Number object) {
		if (object == null) {
			return "";
		}
		return NUMBER_FORMATTER.format(object);
	}

	/**
	 * Affixes % for display purposes.
	 * 
	 * @param amount
	 *            Contains the value that needs to be converted.
	 * @return Returns the String value.
	 */
	public static String affixPercent(final Number amount) {
		if (amount == null) {
			return "--";
		} else if (amount.doubleValue() < 0) {
			String number = Double.toString(amount.doubleValue());
			return (number.substring(1)) + "%";
		}
		return amount + "%";

	}

	/**
	 * Remove any spaces from a string.
	 * 
	 * @param s
	 *            string to format
	 * @return stringWithoutSpaces
	 */
	public static String removeSpaces(final String s) {
		StringTokenizer st = new StringTokenizer(s.trim(), " ", false);
		String t = "";
		while (st.hasMoreElements()) {
			t += st.nextElement();
		}
		return t;
	}

	/**
	 * Logic to clean up html markup data.
	 * 
	 * @param htmlData
	 *            .
	 * @return cleanData.
	 */
	public static String cleanUpHtmlDataForWebView(final String htmlData) {
		String cleanData = htmlData;
		StringBuffer sb = new StringBuffer();

		// Remove all Anchor Tags
		Pattern p = Pattern.compile("<a [^>]*href *= *\"([^\"]*)\"[^>]*>");
		Matcher m = p.matcher(cleanData);
		boolean result = m.find();
		boolean deletedIllegalChars = false;

		while (result) {
			deletedIllegalChars = true;
			m.appendReplacement(sb, "");
			result = m.find();
		}

		// Add the last segment of input to the new String.
		m.appendTail(sb);
		cleanData = sb.toString();

		if (deletedIllegalChars) {
			cleanData = cleanData.replaceAll("</a>", "");
			/** LOG DISABLED Log.d(TAG, "Anchor Tags deleted"); **/
		}

		// Android WebView cannot handle % ascii symbol.
		// Replace % with html equivalent markup.
		cleanData = cleanData.replaceAll("%", "&#37;");
		/**
		 * LOG DISABLED Log.d(TAG,
		 * "Replaced all % chars with html equivalent markup numbers");
		 **/

		return cleanData;
	}
	
	/**
	 * Logic to clean-up RCD module Content-Data.
	 * 
	 */
	public static String cleanRCDContentForWebView(final String htmlData) {
		String cleanData = htmlData;
		cleanData = cleanData.replaceAll("<Content> *< *!\\[CDATA\\[ *", "");
		cleanData = cleanData.replaceAll(" *\\]\\] *> *</Content>", "");
		cleanData = cleanData.trim();

		// Android WebView cannot handle % ascii symbol.
		// Replace % with html equivalent markup.
		cleanData = cleanData.replaceAll("%", "&#37;");
		/**
		 * LOG DISABLED Log.d(TAG,
		 * "Replaced all % chars with html equivalent markup numbers");
		 **/

		return cleanData;
	}
	
	/**
	 * Method to remove all HTML tags
	 * @param htmlData
	 * @return
	 */
	public static String replaceAllHtmlTags(final String htmlData) {
		if (!isEmpty(htmlData)) { 
			return htmlData.replaceAll("<[a-z|/| ]*/?>", "");
		} else {
			 return htmlData;
		}
	}

	public static String join(List<String> strList, String delim) {
		if (strList == null || strList.size() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strList.size() - 1; i++) {
			builder.append(strList.get(i));
			builder.append(delim);
		}
		builder.append(strList.get(strList.size() - 1));
		return builder.toString();
	}

	public static Integer parseVersion(final String versionString) {
		// Our version format is as follows:
		// MajorVersion.MinorVersion.BuildVersion
		// JJ.NN.BB
		final String PERIOD_REGEX = "\\.";
		final int SIG_DIGITS = 100;

		String[] versionSections = versionString.split(PERIOD_REGEX);

		// strip trailing alpha characters, if any, from the build number
		// example 0.0.30A -> 0.0.30
		if (versionSections.length == 3) {
			final String BUILDINFO_REGEX = "([0-9]*)(.*)"; // capture groups
			final Pattern pattern = Pattern.compile(BUILDINFO_REGEX);
			final Matcher matcher = pattern.matcher(versionSections[2]);
			if (matcher.find() && matcher.groupCount() >= 2) {
				versionSections[2] = matcher.group(1); // first group
			}
		}

		// /** LOG DISABLED Log.d(TAG, "parseVersion(" + versionString +
		// ") versionSections[" + versionSections.length + "]"); **/

		int iTmp = 0;

		for (int i = 0, len = versionSections.length; i < len; i++) {
			// /** LOG DISABLED Log.d(TAG, "parseVersion[" + i + "](" +
			// versionSections[i] + ")"); **/

			try {
				Integer tmpI = Integer.parseInt(versionSections[i]);
				// Increment the section (because each section can have two
				// significant digits
				iTmp *= SIG_DIGITS;
				// Add the current value to it
				iTmp += tmpI.intValue();
			} catch (NumberFormatException e) {
				/**
				 * LOG DISABLED Log.d(TAG,
				 * "Numeric Format Exception, when parsing (" +
				 * versionSections[i] + ")", e);
				 **/
			}
		}

		// /** LOG DISABLED Log.d(TAG, "parseVersion(" + versionString +
		// ") became: " + iTmp); **/

		return iTmp;
	}

	/**
	 * Formats a date into a standard timestamp format: yy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param timeInMillis
	 *            a time in Millis
	 * @return a String timestamp
	 */
	public static String formatDataTimeStamp(final long timeInMillis) {
		final String timeFormat = "yy-MM-dd HH:mm:ss.SSS";
		return formatDateTimeStamp(timeInMillis, timeFormat);
	}

	/**
	 * Formats a date into a given timestamp format.
	 * 
	 * @param timeInMillis
	 *            a time in Millis
	 * @param dateFormatString
	 *            a time format String
	 * @return a String timestamp
	 */
	public static String formatDateTimeStamp(final long timeInMillis, final String dateFormatString) {
		String tmpString = "";

		// Convert the timeInMillis to Date
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillis);
		java.util.Date date = cal.getTime();

		// Format it
		SimpleDateFormat format = new SimpleDateFormat(dateFormatString);
		tmpString = format.format(date);

		return tmpString;
	}
	
	/**
	 * Supplies Current Date in the given dateFormat.
	 * 
	 * @param dateFormatString
	 * 				pattern for the date format.
	 * 
	 * @return date
	 * 				string representation of the date.
	 */
	public static String formatCurrentDateTimeStamp(final String dateFormatString) {
		String tmpString = "";
		
		SimpleDateFormat format = new SimpleDateFormat(dateFormatString);
		tmpString = format.format(new Date());
		
		return tmpString;
	}
	
	/**
	 * Flips through the Calendar from the current date
	 * over the given number of days to fetch the required date
	 * in milli seconds.
	 * @param numDays
	 * 				Number of days to flip through in the calendar.
	 * @return timeInMillis
	 * 				Date stamp in milli seconds after flipping through
	 * 				the calendar.
	 */
	public static long getCalendarDateFromToday(final int numDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, numDays);
		return cal.getTimeInMillis();
	}

	/**
	 * Normalize date/time string to include "ET" timezone, which is the default
	 * timezone for all service responses. Some inputs may only contain a date,
	 * so the original string is returned. A timestamp is determined based on 
	 * existence of either "AM" or "PM".
	 *
	 * @param a preformatted date/time string
	 *
	 * @return the input date/time string with "ET" appended
	 */
	public static String normalizeDateTimestamp(String datetime) {
		if(datetime != null && datetime.length() > 0 &&
		   (datetime.endsWith("AM") || datetime.endsWith("PM"))) {
			datetime = datetime+" ET";
		}
		return datetime;
	}
	
	
	/**
	 * Method which returns a truncated version of inputString.
	 * If truncateLength is > String length, then the String is returned unmodified
	 * @param inputString The String to be truncated
	 * @param truncateLength The Length of the resulting Truncated String
	 * @return
	 */
	public static String truncateToLength(String inputString, int truncateLength)
	{
		try {
			
			if(truncateLength > inputString.length() - 1) return inputString;
			else return inputString.substring(0, truncateLength);
			
		} catch (Exception e) {
			
			if(inputString == null) return " ";
			else return inputString;
		}
	}
	
	/**
	 * Strip any HTML or XML tags that begin and end with '<' and '>'.  
	 * Omit those chars and anything between but keep the contents between two tags.... 
	 */
	public static String stripHTMLTags(String source) {
		String output  = null;
		if(source != null)
		{
			try {
				boolean inTag = false;
				StringBuffer buf = new StringBuffer();
				char [] chars = new char[source.length()];
				source.getChars(0, source.length(), chars, 0);
				for(int i = 0; i < chars.length; i++) {
					if(chars[i] == '<') {
						inTag = true;
						continue;
					} else if (chars[i] == '>') {
						inTag = false;
						continue;
					} else if (inTag) {
						continue;
					} else {
						buf.append(chars[i]);
					}
				}
				output = buf.toString();
			} catch (Exception e) {
				// Do nothing...
			} 		
		}
		return output;
	}

	public static String lineSeparator() {
		return System.getProperty("line.separator");
	}
	
}
