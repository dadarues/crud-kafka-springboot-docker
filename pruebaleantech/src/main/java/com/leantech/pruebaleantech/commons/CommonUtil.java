package com.leantech.pruebaleantech.commons;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class CommonUtil {

	/**
	 * Build a string with the concatenation of the params
	 * 
	 * @param args
	 * @return String
	 */
	public static String generateString(String... args) {
		StringBuffer bf = new StringBuffer();
		for (String arg : args) {
			bf.append(arg);
		}
		return bf.toString();
	}

	/**
	 * Get the number of days between two dates
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return long
	 */
	public static long getDaysOfDifferenceBetweenTwoDates(Date firstDate, Date secondDate) {
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;

		return diff;
	}
	
}
