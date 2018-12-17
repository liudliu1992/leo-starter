package com.leo.util.date;

import com.leo.util.lang.DateConsts;
import com.leo.util.lang.WeekEnum;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author 刘绍林
 * @description
 * @time 2017/4/15
 */
public class DateUtils {

    /**
     * 将默认格式的时间字符串转换为Date
     * @param time 默认格式时间字符串
     * @return DateTT
     */
    public static Date formatToDate(String time){
        return formatToDate(time, DateConsts.DATE_FORMAT);
    }

    /**
     * 将format格式的时间字符串转换为Date
     * @param time format格式的时间字符串
     * @param format 时间字符串格式
     * @return Date
     */
    public static Date formatToDate(String time,String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 获取date前lastDay的时间
     * @param date  date
     * @param lastDay 截至时间
     * @return Date
     */
    public static Date getLastDay(Date date, int lastDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -lastDay);
        return cal.getTime();
    }

    public static int subDay(Date minuend, Date subtrahend) {
        long diff = minuend.getTime() - subtrahend.getTime();
        int diffDay = (int) (diff / 1000 / 60 / 60 / 24);
        return diffDay;
    }

    public static int dayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    public static int year(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Date getLastDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        return cal.getTime();
    }

    public static String format(Date date) {
        return new SimpleDateFormat(DateConsts.DEFAULT_PATTERN).format(date);
    }

    public static String formatShort(Date date) {
        return new SimpleDateFormat(DateConsts.DATE_SHORT).format(date);
    }

    public static String formatToyyyyMMdd(Date date) {
        return new SimpleDateFormat(DateConsts.YYYY_MM_DD).format(date);
    }

    public static List<Date> getDayBeginAndEnd() {
        List<Date> list = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date start = cal.getTime();
        list.add(start);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        list.add(cal.getTime());
        return list;
    }

    /**
     * 获取今天的开始时间
     * @return Date 获取今天的开始时间
     */
    public static Date todayBeginTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        return cal.getTime();

    }

    /**
     * 昨天开始时间
     *
     * @return Date 昨天开始时间
     */
    public static Date yesterdaybeginTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -5);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        return cal.getTime();

    }

    /**
     * 昨天结束时间
     *
     * @return Date 昨天结束时间
     */
    public static Date yesterdayEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();

    }
    /**
     * @return yyyy
     */
    public static String getNowYear() {
        //获取现在的年份
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    public static Date converTime(String closeTime) {
        if (StringUtils.isNotBlank(closeTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                return sdf.parse(closeTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getWeekDayWithTime(Date playingTime) {
        if (playingTime != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(playingTime);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            return WeekEnum.getNameByNumber(day);
        }
        return "";
    }
    /**
     * 获取d小时之前的时间
     *
     * @param date 现在时间
     * @param d 小时数
     * @return Date
     */
    public static Date getLastHoursTime(Date date, int d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -d);
        return cal.getTime();
    }

    /**
     * 计算两个时间的分钟差值
     *
     * @param maxDate 大的时间
     * @param minDate 小的时间
     * @return millMinute 分钟差
     */
    public static Long getMinuteOfTwoDate(Date maxDate, Date minDate) {
        long millMinute = maxDate.getTime() - minDate.getTime();
        return millMinute / 1000 / 60;
    }

    /**
     * 计算两个时间的秒差值
     *
     * @param maxDate 大的时间
     * @param minDate 小的时间
     * @return millSeconds 相差的秒
     */
    public static Long getSecondOfTwoDate(Date maxDate, Date minDate) {
        long millSeconds = maxDate.getTime() - minDate.getTime();
        return millSeconds / 1000;
    }
}