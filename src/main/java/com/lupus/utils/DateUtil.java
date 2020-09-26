package com.lupus.utils;

public class DateUtil {
	public static final String DAYS_WORD = " dni ";
	public static final String HOURS_WORD = " godzin ";
	public static final String MINUTES_WORD = " minut ";
	public static final String SECONDS_WORD = " sekund ";

	public static String getTimeLeftFromSeconds(long seconds){
		String result = "";
		double r = (double)seconds / 86400d;
		if(r > 0){
			r =  Math.floor(r);
			result += r + DAYS_WORD;
		}
		seconds -= r*86400;
		r = (double)seconds / 3600d;
		if(r > 0){
			r = Math.floor(r);
			result += r +HOURS_WORD;
		}
		seconds -= r*3600;
		r = (double)seconds / 60d;
		if(r > 0){
			r = Math.floor(r);
			result += r + MINUTES_WORD;
		}
		seconds -= r*60;
		result += seconds + SECONDS_WORD;
		return result;
	}
}
