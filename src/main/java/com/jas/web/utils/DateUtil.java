package com.jas.web.utils;

import com.jas.web.constants.TimeConstant;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);


    public static final int YEAR_LENGTH = 4; //年份长度
    public static final int YEARMO_LENGTH = 6; //年月长度
    public static final String MAX_BATCH_DATE = "2100/01/01";
    public static final String MIN_BATCH_DATE = "1900/01/01";

    // 系统标准日期格式
    public static final String STANDARD_FORMAT = "yyyy-MM-dd";

    public static final String[] FORMATS = new String[]{
            "yyyy-MM-dd", "yyyyMM", "yyyyMMdd", "yyyy.M.d", "yyyy.MM.dd", "yyyy-M-d",
            "yyyy/MM/dd", "yyyy/M/d", "*yyyy/M/d", "*yyyy/MM/dd", "yyyy年M月d日", "yyyy年MM月dd日"
    };

    /**
     * 批量允许的日期格式
     * 格式参考 “20150506”，“2015.5.6”， “2015-5-6”， “2015/5/6”， “2015-05-06”， “2015/05/06”
     */
    public static final String[] BATCH_TIME_FORMAT = new String[]{
            "yyyyMMdd", "yyyy.M.d", "yyyy-M-d", "yyyy/M/d", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    public static final String[] TIME_FORMATS = new String[]{"HH:mm:ss", "HH:mm"};

    private DateUtil() {
    }

    public static String[] getFormats() {
        Set<String> list = new HashSet<String>();
        for (String date : FORMATS) {
            for (String time : TIME_FORMATS) {
                list.add(date + " " + time);
            }
            list.add(date);
        }
        return list.toArray(new String[]{});
    }


    public static String[] getBatchOvertimeFormats() {
        Set<String> list = new HashSet<String>();
        for (String date : BATCH_TIME_FORMAT) {
            for (String time : TIME_FORMATS) {
                list.add(date + " " + time);
            }
        }
        return list.toArray(new String[]{});
    }

    /**
     * 使用多个format匹配，其中一个成功即返回
     *
     * @param str
     * @return
     */
    public static Date getDateValueFromString(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        } else {
            for (int i = 0; i < getFormats().length; i++) {
                String f = getFormats()[i];
                SimpleDateFormat sf = new SimpleDateFormat(f);
                sf.setLenient(false);
                try {
                    return sf.parse(str);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
            }
            throw new RuntimeException("日期格式（" + str + "）无法解析");
        }

    }

    /**
     * 使用多个format匹配，其中一个成功即返回
     *
     * @param str
     * @return
     */
    public static Date getDateValueFromString(String str, String[] formats) {
        if (StringUtil.isBlank(str)) {
            return null;
        } else {
            for (int i = 0; i < formats.length; i++) {
                String f = formats[i];
                SimpleDateFormat sf = new SimpleDateFormat(f);
                sf.setLenient(false);
                try {
                    return sf.parse(str);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
            }
            return null;
        }
    }

    /**
     * 获得指定日期的零点零分零秒
     *
     * @param date
     * @return
     */
    public static Date getDateStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 获得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + TimeConstant.DAYS_PER_WEEK - 1); // Sunday
        return c.getTime();
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 返回yyyy-MM-dd格式的日期字符串
     *
     * @param d
     * @return
     */
    public static String getStringDateShort(Date d) {
        if (d == null)
            return null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(d);
        return dateString;
    }

    /**
     * 根据yyyy-MM-dd的字符串返回Date对象
     *
     * @param s
     * @return
     */
    public static Date getDateShortFromString(String s) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(s);
        } catch (Exception e) {
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            return formatter.parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据format的字符串返回Date对象
     *
     * @param s
     * @param format
     * @return
     */
    public static Date getDateByFormatFromString(String s, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回format制定格式的日期字符串
     *
     * @param d
     * @param format
     * @return
     */
    public static String getStringDateByFormat(Date d, String format) {
        if (d == null) return "";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(d);
        return dateString;
    }

    /**
     * 获得n天后的Timestamp
     *
     * @param ts
     * @param n
     * @return
     */
    public static Timestamp getNDaysAfterTimestamp(Timestamp ts, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, n);
        return (new Timestamp(cal.getTime().getTime()));
    }

    public static long getNowTimestamp() {
        return System.currentTimeMillis() / TimeConstant.MILLIS_PER_SECOND;
    }

    /**
     * 拿到WEEK数
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR) + 1;
    }

    /**
     * 拿到WEEK数
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(String date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(getDateShortFromString(date));
        return c.get(Calendar.WEEK_OF_YEAR) + 1;
    }

    /**
     * 将毫秒数，按照format的格式，转化成日期的字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String getStringFromLongByFormat(long time, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    public static String getStringFromSecondByFormat(long time, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatter.format(calendar.getTime());
    }

    /**
     * 将秒数，按照format的格式，转换成日期的字符串
     *
     * @param seconds
     * @param format
     * @return
     */
    public static String formatSeconds(long seconds, String format) {
        return getStringFromLongByFormat(seconds * TimeConstant.MILLIS_PER_SECOND, format);
    }

    /**
     * 将时间字符串转化成毫秒
     *
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static long getMillisFromStringByFormat(String date, String format) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(format).parse(date));
            return c.getTimeInMillis();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 当前日期上或者下时间间隔的的yearMo   interval 负数表示前几月 正数相反
     *
     * @param interval
     * @return
     */
    public static String getYearmoByInterval(int interval) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, interval);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + "" + (month < 10 ? "0" + month : month);
    }

    /**
     * 获取给定yearmo的间隔interval(月)的yearmo
     *
     * @param yearmo
     * @param interval
     * @return
     */
    public static String getYearmoByInterval(String yearmo, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        cal.add(Calendar.MONTH, interval);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + "" + (month < 10 ? "0" + month : month);
    }

    /**
     * 获取给定yearmo的Date
     *
     * @return
     */
    public static Date getDateByYearmoday(Integer yearmoday) {
        int day = yearmoday % 100;
        int mon = (yearmoday / 100) % 100;
        int year = yearmoday / 10000;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        return cal.getTime();
    }

    /**
     * 获取给定yearmo的Date
     *
     * @return
     */
    public static Date getDateByYearmo(Integer yearmo) {
        int mon = yearmo % 100;
        int year = yearmo / 100;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }


    /**
     * 获取固定日期上或者下时间间隔的的yearMo   interval 负数表示前几月 正数相反
     *
     * @param interval
     * @return
     */
    public static String getTimeYearMoByInterval(Date date, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, interval);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + "" + (month < 10 ? "0" + month : month);
    }

    /**
     * 获得日期中的day
     *
     * @return
     */
    public static Integer getDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得星期几
     * 周一为一星期开始
     *
     * @return
     */
    public static Integer getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.getDateBefore(date, 1));
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得日期中的day，给定日期
     *
     * @param date
     * @return
     */
    public static Integer getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 截取yyyy-MM-dd 的yearmo
     *
     * @param arg
     */

    public static String getYearmoByStr(String arg) {
        String[] date = arg.split("-");
        return date[0] + date[1];
    }

    /**
     * 获取当前yyyy-MM-dd HH:mm:ss的时间
     *
     * @return
     */
    public static String getNowTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }

    /**
     * 获取指定格式的当前时间
     *
     * @return
     */
    public static String getNowTimeByFormat(String formatString) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(formatString);
        String time = format.format(date);
        return time;
    }

    //获取当前年月日对应的日期
    public static Date getDateWithoutHMS(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond =
                hour * TimeConstant.MILLIS_PER_HOUR +
                        minute * TimeConstant.MILLIS_PER_MINUTE +
                        second * TimeConstant.MILLIS_PER_SECOND +
                        cal.get(Calendar.MILLISECOND);
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

        return cal.getTime();
    }

    /**
     * 获取当前yyyy-MM-dd 的时间戳 以毫秒为单位
     *
     * @return
     */
    public static long getNowDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return transferStringtoDate(time, "yyyy-MM-dd").getTime();
    }


    //String转Date
    public static Date transferStringtoDate(String strDate, String pattern) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat(pattern);
        fmt.setLenient(false);
        try {
            Date date = fmt.parse(strDate);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    //Date转String
    public static String transferDatetoString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat(pattern);
        return fmt.format(date);
    }


    /**
     * 当前月
     *
     * @return
     */
    public static Integer getMonth() {
        Calendar cal = Calendar.getInstance();
        Integer month = cal.get(Calendar.MONTH) + 1;
        return month;
    }


    /**
     * 当前
     *
     * @return
     */
    public static Integer getYmdHms(long timeMillis, int calendarType) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeMillis);
        switch (calendarType) {
            case Calendar.YEAR:
                return cal.get(calendarType);
            case Calendar.MONTH:
                return cal.get(calendarType) + 1;
            case Calendar.DAY_OF_MONTH:
                return cal.get(calendarType);
            case Calendar.HOUR:
                return cal.get(calendarType);
            case Calendar.MINUTE:
                return cal.get(calendarType);
            case Calendar.SECOND:
                return cal.get(calendarType);
            default:
                return cal.get(calendarType);
        }
    }

    /**
     * 当前年
     *
     * @return
     */
    public static Integer getYear() {
        Calendar cal = Calendar.getInstance();
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获得年份
     *
     * @return
     */
    public static String getYear(String date, String format) {
        Date date1 = DateUtil.getDateByFormatFromString(date, format);
        String year = getStringDateByFormat(date1, "yyyy");
        return year;
    }

    /**
     * 获取日其中的年份
     *
     * @return
     */
    public static Integer getYearFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Date getMonthFirstDay() {
        String date = getYear() + "-" + getMonth() + "-1";
        Date day = getDateShortFromString(date);
        return day;
    }

    public static Date getYearFirstDay() {
        String date = getYear() + "-1-1";
        Date day = getDateShortFromString(date);
        return day;
    }

    public static Date convertTimestampSecondsToDate(int timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(((long) timestamp) * TimeConstant.MILLIS_PER_SECOND);
        return calendar.getTime();
    }

    /**
     * 将Date，转化为秒数
     */
    public static int convertDateToTimestampSeconds(Date date) {
        long timeInMillis = date.getTime();
        return (int) (timeInMillis / TimeConstant.MILLIS_PER_SECOND);
    }

    /**
     * 获取当前时间距离1970.1.1的秒数，对应mysql的unix_timestamp()
     */
    public static int getUnixTimestamp() {
        return (int) (System.currentTimeMillis() / TimeConstant.MILLIS_PER_SECOND);
    }

    /**
     * 将秒数，按照format转化为string
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String convertTimestampSecondsToString(int timestamp, String format) {
        Date date = convertTimestampSecondsToDate(timestamp);
        return getStringDateByFormat(date, format);
    }

    /**
     * 获得指定日期的指定时间
     *
     * @param date
     * @return
     */
    public static Date getDateOnHour(Date date, int hourOfDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期下一日的指定时间
     *
     * @param date
     * @return
     */
    public static Date getDateNextDayOnHour(Date date, int hourOfDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, +1);
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期前后的日期
     *
     * @param date
     * @return
     */
    public static Date getBeforeOrAfterDate(Date date, int beforeOrAfterDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, beforeOrAfterDays);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取sql类型Date
     *
     * @param date
     * @return
     */
    public static java.sql.Date getSqlDateByDate(Date date) {
        String stringDateShort = DateUtil.getStringDateShort(date);
        Date dateShortFromString = DateUtil.getDateShortFromString(stringDateShort);
        return new java.sql.Date(dateShortFromString.getTime());
    }

    /**
     * 获得某年第一天
     *
     * @return
     */
    public static Date getFirstDateOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 获取该日期的月份第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获得某年第一天
     *
     * @return
     */
    public static Date getLastDateOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year + 1);
        return getDateBefore(cal.getTime(), 1);
    }

    /**
     * 将date对象转化成诸如20160401的字符串，再转化成整数
     *
     * @param date
     * @return
     */
    public static int getDateInt(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String dateStr = formatter.format(date);
            return Integer.parseInt(dateStr);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 返回两个日期中较大的一个，如果相等，则任意返回一个
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Date findMax(Date d1, Date d2) {
        if (compare(d1, d2) <= 0)
            return d2;
        else
            return d1;
    }

    /**
     * 返回两个日期中较小的一个，如果相等，则任意返回一个
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Date findMin(Date d1, Date d2) {
        if (compare(d1, d2) <= 0)
            return d1;
        else
            return d2;
    }

    /**
     * 比较两个日期的大小
     */
    public static int compare(Date d1, Date d2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String str1 = format.format(d1);
        String str2 = format.format(d2);
        return str1.compareTo(str2);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            smdate = sdf.parse(sdf.format(smdate));

            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long betweenDays = (time2 - time1) / TimeConstant.MILLIS_PER_DAY;
            return Integer.parseInt(String.valueOf(betweenDays));
        } catch (ParseException e) {
            LOGGER.error("daysBetween:时间计算错误,smdate:" + smdate + ",bdate:" + bdate, e);
            return -1;
        }
    }

    /**
     * 计算两个日期机制相差的分钟数
     *
     * @param smdate
     * @param bdate
     * @return
     */
    public static int minutesBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenMinute = (time2 - time1) / TimeConstant.MILLIS_PER_MINUTE;
        return Integer.parseInt(String.valueOf(betweenMinute));
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取当前yearmo
     *
     * @return
     */
    public static int getCurrentYearmo() {
        //当前yearmo
        int yearmo = Integer.parseInt((DateUtil.getDateInt(DateUtil.getCurrentTime()) + "").substring(0, 6));
        return yearmo;
    }

    /**
     * 获取指定年月的上个月
     *
     * @param yearmo 201708 201801
     * @return 201707 201712
     */
    public static int getLastYearmo(int yearmo) {
        int month = yearmo % 100;
        if (1 == month) {
            // 1月 年减一 月加11
            return yearmo - 89;
        } else {
            return yearmo - 1;
        }
    }

    /**
     * 获取指定的月的下一个月字符串，yearmo的格式是201604，返回的格式也是201605
     *
     * @param yearmo
     * @return
     */
    public static String getNextMonth(String yearmo) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)) - 1);
        calendar.add(Calendar.MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(calendar.getTime());
    }

    /**
     * 获取指定的月的下一个月字符串，yearmo的格式是201604，返回的格式也是201605
     *
     * @param yearmo
     * @return
     */
    public static Date getNextMonth(Date yearmo) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(yearmo);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当前月的上一个月字符串，返回的格式也是201603
     *
     * @return
     */
    public static String getPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(calendar.getTime());
    }

    /**
     * 将秒数，转化为SQLDate
     *
     * @param timestamp
     * @return
     */
    public static java.sql.Date convertTimestampSecondsToSqlDate(int timestamp) {
        Date date = convertTimestampSecondsToDate(timestamp);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    /**
     * 将诸如20160401的日期字符串，转化成整数
     *
     * @param date
     * @return
     */
    public static int getDateInt(String date) {
        try {
            return Integer.parseInt(date);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 将时间精确到分钟
     *
     * @param date
     * @return
     */
    public static Date correctToMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定时间
     *
     * @param date      日期
     * @param hour      时
     * @param minute    分
     * @param addMinute 增加多少分钟
     * @return
     */
    public static Date getTime(Date date, int hour, int minute, int addMinute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MINUTE, addMinute);
        return cal.getTime();
    }


    /**
     * 获得指定时间的指定分钟前后时间
     *
     * @param date
     * @param addMinute
     * @return
     */
    public static Date getTimeAddMinute(Date date, int addMinute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, addMinute);
        return cal.getTime();
    }

    /**
     * 根据时间戳获取某天的小时数——24小时制
     *
     * @param detailDate 日期
     * @param date       "20160501"格式的日期
     * @return
     */
    public static int getHoursOfDate(Date detailDate, String date) {
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.setTime(detailDate);
        int hour = cal1.get(Calendar.HOUR_OF_DAY); //当天小时
        int date1 = cal1.get(Calendar.DATE);

        Calendar cal2 = Calendar.getInstance();
        cal2.clear();
        cal2.setTime(DateUtil.getDateByFormatFromString(date, "yyyyMMdd"));
        int date2 = cal2.get(Calendar.DATE);
        if (date1 - date2 == 1 && hour == 0) {
            hour = 24;
        }
        return hour;
    }

    /**
     * 根据时间戳获取某天的小时数——24小时制
     *
     * @param time 时间戳
     * @param date "20160501"格式的日期
     * @return
     */
    public static int getHoursOfDate(int time, String date) {
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.setTime(DateUtil.convertTimestampSecondsToDate(time));
        int hour = cal1.get(Calendar.HOUR_OF_DAY); //当天小时
        int date1 = cal1.get(Calendar.DATE);

        Calendar cal2 = Calendar.getInstance();
        cal2.clear();
        cal2.setTime(DateUtil.getDateByFormatFromString(date, "yyyyMMdd"));
        int date2 = cal2.get(Calendar.DATE);
        if (date1 - date2 == 1 && hour == 0) {
            hour = TimeConstant.HOURS_PER_DAY;
        }
        return hour;
    }

    /**
     * 获取准确sqldate,下一天0点算前一天24点
     *
     * @return
     */
    public static java.sql.Date getSqlDateWith0Hours(Date date) {
        java.sql.Date sqlDateByDate = getSqlDateByDate(date);
        if (DateUtil.getDateOnHour(sqlDateByDate, 0).compareTo(date) == 0) {
            //零点
            return getSqlDateByDate(getBeforeOrAfterDate(sqlDateByDate, -1));
        }
        return sqlDateByDate;

    }

    public static Date getFirstDayOfYearmo(String yearmo) {
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        cal1.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)) - 1);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        return getSqlDateByDate(cal1.getTime());
    }

    public static Date getlastDayOfYearmo(String yearmo) {
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        cal1.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)));
        cal1.set(Calendar.DAY_OF_MONTH, 1);

        return getSqlDateByDate(getBeforeOrAfterDate(cal1.getTime(), -1));
    }


    public static Date getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));
            return time;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getCurrentTimeMin() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            return sdf.format(new Date());
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        //得到月末
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取指定日其所在月份的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        return cal.getTime();
    }

    /**
     * 获取指定年，月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return cal.getTime();
    }

    /**
     * 获取指定年,月份的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);

        return cal.getTime();
    }

    /**
     * 计算相差分钟数
     *
     * @param dateStartStr
     * @param dateEndStr
     * @return
     */
    public static int getIntervalMin(String dateStartStr, String dateEndStr) {
        Date startDate = getDateByFormatFromString(dateStartStr, "HH:mm");
        Date endDate = getDateByFormatFromString(dateEndStr, "HH:mm");
        return (int) ((endDate.getTime() - startDate.getTime()) / TimeConstant.MILLIS_PER_MINUTE);
    }

    /**
     * 计算相差天数
     */
    public static int getDiscrepantDays(Date dateStart, Date dateEnd) {
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / TimeConstant.MILLIS_PER_DAY);
    }

    public static Date getLastSunDayOfLastYearmo(String yearmo) {
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        cal1.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)) - 1);
        cal1.add(Calendar.DAY_OF_MONTH, -1);
        cal1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getSqlDateByDate(cal1.getTime());

    }

    public static Date getLastYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    public static int getNextDateKey(int dateKey) {
        Date date = getDateByFormatFromString(dateKey + "", "yyyyMMdd");
        date = getDateAfter(date, 1);
        return getDateInt(date);
    }

    public static int getDayOfYear(Date date) {
        Calendar cal1 = Calendar.getInstance();
//        cal1.clear();
        cal1.setTime(date);
        return cal1.get(Calendar.DAY_OF_YEAR);
    }


    public static String getFistDayOfYearmo(String yearmo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        String formatDate = getStringDateByFormat(cal.getTime(), "yyyy-MM-dd");
        String[] date = formatDate.split("-");
        return date[1] + "." + date[2];
    }


    public static String getLastDayOfYearmo(String yearmo) {
        Calendar cal = Calendar.getInstance();
        //清除缓存
        cal.clear();
        cal.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String formatDate = getStringDateByFormat(cal.getTime(), "yyyy-MM-dd");
        String[] date = formatDate.split("-");
        return date[1] + "." + date[2];
    }


    /**
     * 获取参数月份的月初时间戳(秒)
     *
     * @param yearmo
     * @return
     */
    public static long getFistDayTimestampOfYearmo(Integer yearmo) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearmo / 100);
        cal.set(Calendar.MONTH, yearmo % 100 - 1);
        int minimum = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    public static Date getLastSundayOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
            calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getSqlDateByDate(calendar.getTime());
    }

    public static int getLastDayNumOfYearmo(String yearmo) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, Integer.parseInt(yearmo.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(yearmo.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String formatDate = getStringDateByFormat(cal.getTime(), "yyyy-MM-dd");
        String[] date = formatDate.split("-");
        return Integer.parseInt(date[2]);
    }

    public static String getStringDateByFormat(int year, int month, int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return DateUtil.getStringDateByFormat(cal.getTime(), format);
    }

    public static int getYearFromYearmo(String yearmo) {
        return Integer.parseInt(yearmo.substring(0, 4));
    }

    public static int getMonthFromYearmo(String yearmo) {
        return Integer.parseInt(yearmo.substring(4, 6));
    }

    public static java.sql.Date getSqlDateFromStringShort(String dateStr) {
        if (dateStr == null)
            return null;

        Date date = DateUtil.getDateShortFromString(dateStr);
        if (date == null)
            return null;

        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取给定日期的前几个月或后几个月的日期
     *
     * @param date
     * @param interval
     * @return
     */
    public static Date getDateByIntervalMonth(Date date, int interval) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.MONTH, interval);
        return now.getTime();
    }

    /**
     * 获取考勤日期，凌晨4点之前算前一天
     *
     * @return
     */
    public static Date getAttendanceDateByTimeStamp(int timeStamp) {
        Date clockDate = null;
        Date clockTimeDate = DateUtil.convertTimestampSecondsToDate(timeStamp);

        Date startTime = DateUtil.getDateOnHour(clockTimeDate, 4);
        Date endTime = DateUtil.getDateNextDayOnHour(clockTimeDate, 4);
        if (clockTimeDate.before(startTime)) {
            clockDate = new Date(getBeforeOrAfterDate(clockTimeDate, -1).getTime());//等于4点也算第二天
        } else if (clockTimeDate.compareTo(endTime) >= 0) {
            clockDate = new Date(DateUtil.getBeforeOrAfterDate(clockTimeDate, +1).getTime());
        } else {
            clockDate = new Date(clockTimeDate.getTime());
        }
        return clockDate;
        //return getAttendanceDate(clockTimeDate);
    }

    /**
     * 获取考勤日期，凌晨4点之前算前一天
     *
     * @return
     */
    public static Date getAttendanceDate(Date clockTimeDate) {
        Date clockDate = null;
        Date startTime = DateUtil.getDateOnHour(clockTimeDate, 4);
        Date endTime = DateUtil.getDateNextDayOnHour(clockTimeDate, 4);
        if (clockTimeDate.before(startTime)) {
            clockDate = new Date(getBeforeOrAfterDate(clockTimeDate, -1).getTime());
        } else if (clockTimeDate.before(endTime)) {
            clockDate = new Date(clockTimeDate.getTime());
        } else {
            clockDate = new Date(DateUtil.getBeforeOrAfterDate(clockTimeDate, +1).getTime());
        }
        return clockDate;
    }

    /**
     * 获取当天 零点的时间戳
     *
     * @return
     */
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / TimeConstant.MILLIS_PER_SECOND;
    }

    public static Date getDatefromDateAndTimeStr(Date date, String time) {
        String dateStr = getStringDateByFormat(date, "yyyyMMdd");
        dateStr += " " + time;
        return getDateByFormatFromString(dateStr, "yyyyMMdd HH:mm");
    }

    /**
     * 获取两个时间之间的日期［年-月-日］
     * 包含起始时间
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getMiddleDate(String startDate, String endDate) throws ParseException {
        List<String> listDate = new ArrayList<String>();
        if (StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)) {
            return listDate;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();

        startDay.setTime(dateFormat.parse(startDate));
        endDay.setTime(dateFormat.parse(endDate));
        // 给出的日期开始日比终了日大则不执行打印
        if (startDay.compareTo(endDay) >= 0) {
            return listDate;
        }
        listDate.add(dateFormat.format(dateFormat.parse(startDate)));
        // 现在打印中的日期
        Calendar currentPrintDay = startDay;
        while (true) {
            // 日期加一
            currentPrintDay.add(Calendar.DATE, 1);
            // 日期加一后判断是否达到终了日，达到则终止打印
            if (currentPrintDay.compareTo(endDay) == 0) {
                break;
            }
            // 打印日期
            listDate.add(dateFormat.format(currentPrintDay.getTime()));
        }
        listDate.add(dateFormat.format(dateFormat.parse(endDate)));
        return listDate;
    }

    /**
     * 获取两个日期之间的时间
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getMiddleDate(Date startDate, Date endDate) {
        List<String> middleDateList = new ArrayList<String>();
        Date date = startDate;
        while (date.compareTo(endDate) <= 0) {
            middleDateList.add(String.valueOf(getDateInt(date)));
            date = DateUtil.getDateAfter(date, 1);
        }
        return middleDateList;
    }

    public static String getYearmoFromDate(Date date) {
        return getStringDateByFormat(date, "yyyyMM");
    }

    public static String getYearMoDayFromDate(Date date) {
        return getStringDateByFormat(date, "yyyyMMdd");
    }

    /**
     * @param nowHM "13:00"
     * @param t     秒
     * @return 返回  13：00 t秒后的时间
     */
    public static Date getAfterHM(String nowHM, int t) {
        Date nowd = getDateByFormatFromString("20170101 " + nowHM, "yyyyMMdd HH:mm");
        return new Date(nowd.getTime() + t * 1000L);
    }
    //CHECKSTYLE:ON


    /**
     * @param date
     * @param t    分钟数
     * @return
     */
    public static Date getAfterMinDate(Date date, int t) {
        return new Date(date.getTime() + t * TimeConstant.MILLIS_PER_MINUTE);
    }

    /**
     * 取小时数的小数部分,换算成分钟
     *
     * @param hour
     * @return
     */
    public static int doubleHourToMinute(double hour) {
        DecimalFormat df = new DecimalFormat("######0");
        if (hour > 0) {
            hour = hour - (int) hour;
            return Integer.parseInt(df.format((hour * 60)));
        } else {
            return 0;
        }

    }

    /**
     * 根据出生年月计算员工的年龄
     *
     * @param bornDate
     * @return
     */
    public static Integer getAgeByBornDate(Date bornDate) {
        int age;
        Calendar c = Calendar.getInstance();
        int now = c.get(c.YEAR);
        int bornYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(bornDate));
        age = now - bornYear;
        return age;
    }

    /**
     * 将天数转换成秒
     *
     * @param numberOfDays
     * @return
     */
    public static Long getSecondsByNumberOfDays(Integer numberOfDays) {
        return numberOfDays * 24 * 60 * 60L;
    }

    public static Date getDateAfterOrBeforeYear(Date d, int year) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.YEAR, year);
        return now.getTime();
    }

    public static Date getBatchTimeFromStr(String str) {
        Date parse = null;

        if (StringUtil.isBlank(str)) {
            return parse;
        }

        for (int i = 0; i < BATCH_TIME_FORMAT.length; i++) {
            String f = BATCH_TIME_FORMAT[i];
            SimpleDateFormat sf = new SimpleDateFormat(f);
            // 设置严格匹配
            sf.setLenient(false);
            try {
                parse = sf.parse(str);
                return parse;
            } catch (ParseException e) {
            }

        }
        throw new RuntimeException("操作失败，日期填写错误");
    }

    public static Date getBatchTimeFromRegexStr(String str) {
        Date parse = null;

        if (StringUtil.isBlank(str)) {
            return parse;
        }
        //年份1000-9999，格式yyyy-MM-dd或yyyy-M-d，连字符可以没有或是“-”、“/”、“.”之一。
        String timeRegex = "^(?:[1-9]{1}[0-9]{3}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$";
        if (str.matches(timeRegex)) {
            for (int i = 0; i < BATCH_TIME_FORMAT.length; i++) {
                String f = BATCH_TIME_FORMAT[i];
                SimpleDateFormat sf = new SimpleDateFormat(f);
                // 设置严格匹配
                sf.setLenient(false);
                try {
                    parse = sf.parse(str);
                    return parse;
                } catch (ParseException e) {
                }

            }
        }
        throw new RuntimeException("日期格式（" + str + "）无法解析");
    }

    public static List<java.sql.Date> getSqlDateList(Date start, Date end) {
        List<java.sql.Date> res = new ArrayList<java.sql.Date>();
        Date front = new Date(start.getTime());
        while (!front.after(end)) {
            java.sql.Date sqlDate = new java.sql.Date(front.getTime());
            front = getDateAfter(front, 1);
            res.add(sqlDate);
        }
        return res;
    }

    public static List<Integer> getDateIntList(Date start, Date end) {
        List<Integer> res = new ArrayList<Integer>();
        Date front = new Date(start.getTime());
        while (!front.after(end)) {
            res.add(getDateInt(front));
            front = getDateAfter(front, 1);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
