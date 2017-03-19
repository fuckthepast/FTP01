package com.gzmh.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

	private static SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 返回格式："yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String get_dateFormat(Date date) {
		return _dateFormat.format(date);
	}
	
	/**
	 * 获取时间格式
	 * @param source
	 * @return
	 */
	public static Date get_Date(String source) {
		try {
			return _dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回格式："yyyyMMdd"
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date) {
		return dateFormat.format(date);
	}
	
	/**
	 * 返回格式："yyyyMMddHH"
	 * 
	 * @param date
	 * @return
	 */
	public static String getHourFormat(Date date) {
		SimpleDateFormat hourFormat = new SimpleDateFormat("yyyyMMddHH");
		return hourFormat.format(date);
	}
	
	/**
	 * 返回格式："HH"
	 * 
	 * @param date
	 * @return
	 */
	public static String getCurrentHour(Date date) {
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		return hourFormat.format(date);
	}
	
	/**
	 * 返回星期：如：星期一
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekFormat(Date date) {
		SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
		return weekFormat.format(date);
	}
	
	/**
	 * 返回当前年份
	 * @return
	 */
	public static int getYear() {
		Calendar cal=Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 返回当前月份
	 * @return
	 */
	public static int getMonth() {
		Calendar cal=Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 返回当前天的号数
	 * @return
	 */
	public static int getDay() {
		Calendar cal=Calendar.getInstance();
		return cal.get(Calendar.DATE);
	}
	
	/**
	 * 返回当前天的星期数
	 * @return
	 */
	public static int getWeekDays(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

}
