package com.project.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	public static final String DATE_TIME_GMT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String DATE_TIME_HYPHEN_REVERSE = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_HYPHEN = "dd-MM-yyyy HH:mm:ss";
	public static final String DATE_TIME_SLASH = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_HYPHEN_REVERSE = "yyyy-MM-dd";
	public static final String DATE_HYPHEN = "dd-MM-yyyy";
	public static final String DATE_SLASH = "dd/MM/yyyy";

	public static String dateToString(Date date, String format) {
		try {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}
	
	public static Date stringToDate(String date, String format) {
		try {
			DateFormat df = new SimpleDateFormat(format);
			return df.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date addTimeToDate(Date date, int timeType, int time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(timeType, time);
		return calendar.getTime();
	}

	public static LocalDate convertDatetoLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	public static Date convertLocalDatetoDate(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}
}
