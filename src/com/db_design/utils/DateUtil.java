package com.db_design.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 将日期转化为字符串
	 * 
	 * @param d
	 * @return
	 */
	public static String DateCovertToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String returVa = sdf.format(d);
		return returVa;

	}

	/**
	 * 将字符串转化为日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date StringCovertToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;

	}
	
	
	/**
	 * 将日期转化为字符串
	 * 
	 * @param d
	 * @return
	 */
	public static String DateCovertToString(Date d,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String returVa = sdf.format(d);
		return returVa;

	}

	/**
	 * 将字符串转化为日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date StringCovertToDate(String date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
}
