package com.jas.web.constants;

/**
 * 日期、时间类的常量
 *
 * @author fanglijun on 2017/2/6.
 */
public final class TimeConstant {

    private TimeConstant() { }

    public static final long MILLIS_PER_SECOND = 1000L;
    public static final long MILLIS_PER_MINUTE = 60 * 1000L;
    public static final long MILLIS_PER_HOUR = 3600 * 1000L;
    public static final long MILLIS_PER_DAY = 24 * 3600 * 1000L;
    public static final int SECONDS_PER_DAY = 24 * 3600;

    public static final long SECONDS_PER_HOUR = 3600L;
    public static final int SECONDS_PER_MINUTE = 60;

    public static final int DAYS_PER_WEEK = 7;
    public static final int HOURS_PER_DAY = 24;
    public static final int DAYS_PER_YEAR = 365;

    public static final int MONTHS_PER_YEAR = 12;

    // 年的位数，yearmo的位数
    public static final int DIGITS_OF_YEAR = 4;
    public static final int DIGITS_OF_YEARMO = 6;
}
