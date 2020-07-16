package com.lwc.test.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /** 默认的时间格式化器，格式为yyyy-MM-dd HH:mm:ss */
    public static final ThreadLocal<SimpleDateFormat> DEFAULT_DATETIME_FORMATER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    public static Date parse(String date) {
    	return parse(date, DATE_PATTERN);
    }
    
    public static Date parse(String date, String pattern) {
        if(StringUtils.isNotBlank(date)){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
				return df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        return null;
    }

    /**
     * String转Date
     * 时间格式yyyyMMdd
     * @param str 指定日期
     * @return
     */
    public static Date stringByDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(str);  // Thu Jan 18 00:00:00 CST 2007
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * String转Date
     * 时间格式yyyyMMdd
     * @param str 指定日期
     * @return
     */
    public static Date stringByDate1(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(str);  // Thu Jan 18 00:00:00 CST 2007
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化时间 date转string
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        try {
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            return sformat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 根据传入的格式，将日期对象格式化为日期字符串
     *
     * @param date
     *            待被格式化日期
     * @param format
     *            自定义日期格式器
     * @return 格式后的日期字符串
     */
    public static String formatDate(Date date, String format) {
        String s = "";

        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            s = sdf.format(date);
        }

        return s;
    }

    /**
     * 用默认的日期时间格式，格式化一个Date对象
     *
     * @param date
     *            待被格式化日期
     * @return “yyyy-MM-dd HH:mm:ss”格式的日期时间字符串
     */
    public static String formatTime(Date date) {
        return date == null ? "" : DateUtils.DEFAULT_DATETIME_FORMATER.get().format(date);
    }

    /**
     * 根据传入的格式，将日期对象格式化为时间字符串
     *
     * @param date
     *            待被格式化日期
     * @param format
     *            自定义日期格式器
     * @return 格式后的日期时间字符串
     */
    public static String formatTime(Date date, String format) {
        String s = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            s = sdf.format(date);
        }

        return s;
    }
}
