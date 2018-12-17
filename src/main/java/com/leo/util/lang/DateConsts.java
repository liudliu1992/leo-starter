package com.leo.util.lang;

import java.text.SimpleDateFormat;

/**
 * @author 刘绍林
 * @create 2017-10-15 23:32
 **/
public class DateConsts {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");

    /**
     * 一秒的毫秒数
     */
    public static final long MS_ONE_SECOND =1000L;
    /**
     * 一分钟的毫秒数
     */
    public static final long MS_ONE_MINUTE = 60000L;
    /**
     * 一小时的毫秒数
     */
    public static final long MS_ONE_HOUR = 3600000L;
    /**
     * 一天的毫秒数
     */
    public static final long MS_ONE_DAY = 86400000L;
    /**
     * 一周的毫秒数
     */
    public static final long MS_ONE_WEEK = 604800000L;

    /**
     * 一分钟的秒数
     */
    public static final long SECOND_ONE_MINUTE=60L;
    /**
     * 一小时的分钟数
     */
    public static final long MINUTE_ONE_HOUR=60L;
    /**
     * 一天的小时数
     */
    public static final long HOUR_ONE_DAY =24L;
    /**
     * 一月的天数
     */
    public static final long DAY_ONE_MOUTH =30L;
    /**
     * 一年的天数
     */
    public static final long DAY_ONE_YEAR =30L;


    public static final String JUST_NOW ="刚刚";
    public static final String ONE_SECOND_AGO = "秒前";
    public static final String ONE_MINUTE_AGO = "分钟前";
    public static final String ONE_HOUR_AGO = "小时前";
    public static final String ONE_DAY_AGO = "天前";
    public static final String ONE_MONTH_AGO = "月前";
    public static final String ONE_YEAR_AGO = "年前";
    public static final String YESTERDAY = "昨天";


    public static final String DATE_SHORT = "yyyy-MM-dd";
    public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD = "yyyyMMdd";
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
}
