package com.leo.util.date;

import com.leo.util.lang.DateConsts;
import com.leo.util.lang.NumberConsts;

import java.util.Date;

/**
 * 计算到现在时间
 *
 * @author 刘绍林
 * @create 20NumberConsts.ONE7-08-NumberConsts.ONE9 NumberConsts.ONENumberConsts.ONE:4NumberConsts.ONE
 **/
public class RelativeDateFormat {

    public static String formatDayAgo(Date date) {
        long delta = System.currentTimeMillis() - date.getTime();
        String result;
        if (delta < DateConsts.MS_ONE_MINUTE) {
            result = DateConsts.JUST_NOW;
        }else if (delta < DateConsts.MS_ONE_HOUR) {
            result = toMinutes(delta) + DateConsts.ONE_MINUTE_AGO;
        }else if (delta < DateConsts.MS_ONE_DAY) {
            result = toHours(delta) + DateConsts.ONE_HOUR_AGO;
        }else {
            result = toDays(delta) +DateConsts.ONE_DAY_AGO;
        }
        return result;
    }

    public static String format(Date date) {
        long delta = System.currentTimeMillis() - date.getTime();
        if (delta < DateConsts.MS_ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= NumberConsts.ZERO ? NumberConsts.ONE : seconds) + DateConsts.ONE_SECOND_AGO;
        }
        if (delta < DateConsts.MINUTE_ONE_HOUR * DateConsts.MS_ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= NumberConsts.ZERO ? NumberConsts.ONE : minutes) + DateConsts.ONE_MINUTE_AGO;
        }
        if (delta < DateConsts.HOUR_ONE_DAY * DateConsts.MS_ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= NumberConsts.ZERO ? NumberConsts.ONE : hours) + DateConsts.ONE_HOUR_AGO;
        }
        if (delta < DateConsts.HOUR_ONE_DAY * NumberConsts.TWO * DateConsts.MS_ONE_HOUR) {
            return DateConsts.YESTERDAY;
        }
        if (delta < DateConsts.DAY_ONE_MOUTH * DateConsts.MS_ONE_DAY) {
            long days = toDays(delta);
            return (days <= NumberConsts.ZERO ? NumberConsts.ONE : days) + DateConsts.ONE_DAY_AGO;
        }
        if (delta <  DateConsts.HOUR_ONE_DAY * NumberConsts.TWO * DateConsts.MS_ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= NumberConsts.ZERO ? NumberConsts.ONE : months) + DateConsts.ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= NumberConsts.ZERO ? NumberConsts.ONE : years) + DateConsts.ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / DateConsts.MS_ONE_SECOND;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / DateConsts.MINUTE_ONE_HOUR;
    }

    private static long toHours(long date) {
        return toMinutes(date) / DateConsts.SECOND_ONE_MINUTE;
    }

    private static long toDays(long date) {
        return toHours(date) / DateConsts.HOUR_ONE_DAY;
    }

    private static long toMonths(long date) {
        return toDays(date) / DateConsts.DAY_ONE_MOUTH;
    }

    private static long toYears(long date) {
        return toMonths(date) / DateConsts.DAY_ONE_YEAR;
    }
}
