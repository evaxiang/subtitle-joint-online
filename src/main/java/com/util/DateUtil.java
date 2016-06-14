package com.util;


import hirondelle.date4j.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: UncleYee
 * Date: 13-3-23
 * Time: 上午9:54
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    public static final String FORMAT_STR_END_MONTH = "yyyy-MM";
    public static final String FORMAT_STR_END_DAY = "yyyy-MM-dd";
    public static final String FORMAT_STR_END_HOUR = "yyyy-MM-dd HH";
    public static final String FORMAT_STR_END_MIN = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_STR_END_SEC = "yyyy-MM-dd HH:mm:ss";

    public static Date getStartOfHour(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        return c.getTime();
    }

    public static Date getEndOfHour(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static Date getStartOfYear(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        return c.getTime();
    }

    public static Date getEndOfYear(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, Calendar.DECEMBER);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }


    public static Date getStartOfDay(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        return c.getTime();
    }

    public static Date getEndOfDay(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static Date sub(Date day,int dayCount) {
        return plus(day, -1 * dayCount);
    }

    public static Date plus(Date day,int dayCount) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + dayCount);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        return c.getTime();
    }


    public static Date getStartOfWeek(Date day, Integer firstDayOfWeek) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(firstDayOfWeek);
        c.setTime(day);
        c.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        return c.getTime();
    }

    public static Date getEndOfWeek(Date day, Integer firstDayOfWeek) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(firstDayOfWeek);
        c.setTime(day);
        c.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.add(Calendar.DAY_OF_WEEK, 6);
        return c.getTime();
    }

    public static Date getEndOfWeekByDay(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.add(Calendar.DAY_OF_YEAR, 6);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }


    public static Date getStartOfMonth(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        return c.getTime();
    }

    public static Date getEndOfMonth(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);


        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }


    public static String getDaysStrBetweenDates(Date start, Date end) {
        TimeZone tz = TimeZone.getTimeZone("Etc/GMT-8");
        StringBuffer sb = new StringBuffer();
        Date day = start;
        DateTime dtend = DateTime.forInstant(end.getTime(), tz);
        String endtemp = dtend.getMonth() + ("-") + dtend.getDay();
        while (true) {
            DateTime dt = DateTime.forInstant(day.getTime(), tz);
            String temp = dt.getMonth() + ("-") + dt.getDay();
            sb.append(temp).append(",");
            if (endtemp.equals(temp)) {
                break;
            }
            day = new Date(dt.plusDays(1).getMilliseconds(tz));
        }
        if (sb.length() != 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static Integer getDayCount(Date a, Date b) {
        long temp = 1000L * 60 * 60 * 24;
        Long ret = Math.abs((a.getTime() - b.getTime()) / temp) + 1;
        return ret.intValue();
    }

    public static Integer getYearCount(Date a, Date b) {
        if(a == null || b == null){
            return -1;
        }
        long temp = 1000L * 60 * 60 * 24*365;
        Long ret = Math.abs((a.getTime() - b.getTime()) / temp);
        return ret.intValue();
    }

    public static void main(String[] args) {
        Date d1 = new Date();
        d1.setMonth(1);
        Date d2 = new Date();
        System.out.println(DateUtil.getDaysStrBetweenDates(d1, d2));

        DateTime dateAndTime = new DateTime("2010-01-19 23:59:59");
        DateTime dateAndTime_2 = new DateTime("2010-01-19 23:59:59.123456789");
        DateTime dateOnly = new DateTime("2010-01-19");
        DateTime timeOnly = new DateTime("23:59:59");
//        DateTime dateOnly = DateTime.forDateOnly(2010,01,19);
//        DateTime timeOnly = DateTime.forTimeOnly(23,59,59,0);
        DateTime dt = new DateTime("2010-01-15 13:59:15");
        boolean leap = dt.isLeapYear(); //false
        dt.getNumDaysInMonth(); //31
        dt.getStartOfMonth(); //2010-01-01, 00:00:00.000000000
        dt.getEndOfDay(); //2010-01-15, 23:59:59.999999999
        System.out.println(dateOnly.format("YYYY-MM-DD")); //formats as '2010-01-15'
        dt.plusDays(30); //30 days after Jan 15
//        dt.numDaysFrom(someDate); //returns an int
//        dueDate.lt(someDate); //less-than
//        dueDate.lteq(someDate); //less-than-or-equal-to
////Although DateTime carries no TimeZone information internally, there are methods that take a TimeZone as a parameter :
//        DateTime now = DateTime.now(someTimeZone);
//        DateTime today = DateTime.today(someTimeZone);
//        DateTime fromMilliseconds = DateTime.forInstant(31313121L, someTimeZone);
//        birthday.isInFuture(someTimeZone);
//        dt.changeTimeZone(fromOneTimeZone, toAnotherTimeZone);
    }


    public static Date getDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            String formatStr = "";
            if (dateStr.trim().length() == FORMAT_STR_END_MONTH.length()) {
                formatStr = FORMAT_STR_END_MONTH;
            }
            if (dateStr.trim().length() == FORMAT_STR_END_DAY.length()) {
                formatStr = FORMAT_STR_END_DAY;
            }

            if (dateStr.trim().length() == FORMAT_STR_END_HOUR.length()) {
                formatStr = FORMAT_STR_END_HOUR;
            }

            if (dateStr.trim().length() == FORMAT_STR_END_MIN.length()) {
                formatStr = FORMAT_STR_END_MIN;
            }

            if (dateStr.trim().length() == FORMAT_STR_END_SEC.length()) {
                formatStr = FORMAT_STR_END_SEC;
            }
            DateFormat fmt = new SimpleDateFormat(formatStr);
            date = fmt.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

}
