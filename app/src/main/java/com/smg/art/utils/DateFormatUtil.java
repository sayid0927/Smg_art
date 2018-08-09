package com.smg.art.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式处理
 * Created by lurs on 2017/4/27.
 */

public class DateFormatUtil {

    /**
     * 把毫秒值转化为yyyy-MM-dd
     *
     * @param currentTimeMillis
     * @return
     */
    public static String forString(long currentTimeMillis) {
        return forString(currentTimeMillis, null);
    }

    /**
     * 把毫秒值转化为format格式的日期
     *
     * @param currentTimeMillis
     * @return
     */
    public static String forString(long currentTimeMillis, String format) {
        Date d = new Date(currentTimeMillis);
        if ((format == null) || format.equals("")) {
            format = "yyyy-MM-dd";
        }
        DateFormat df = new SimpleDateFormat(format);
        String str = df.format(d);
        return str;
    }

    /**
     * 取得当前时间戳（精确到秒）
     */
    public static long getTime() {
        long time = System.currentTimeMillis();
        return time;
    }

    /**
     * 毫秒转为时/分/秒
     */
    public static String toHMS(long currentTimeMillis) {
        long ls = currentTimeMillis / 1000;
        String s = (ls % 60) + "秒";
        int im = (int) (ls / 60);
        String m = "";
        if (im > 0) {
            m = (im % 60) + "分钟";
        }
        String h = "";
        int ih = im / 60;
        if (ih > 0) {
            h = ih + "小时";
        }
        return h + m + s;
    }

    /**
     * 把时间转化在long
     *
     * @param date
     * @param format
     * @return
     */
    public static long stringToLong(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Long getLongBirthday(String birthady) {
        if ((birthady != null) && (!birthady.equals(""))) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(birthady);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        } else {
            return null;
        }
    }


    public static String getFormatDate(Long date, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 时间戳转YYYY-DD-MM HH:mm:ss
     *
     * @param timestamp
     * @return
     */
    public static Date toDate(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(timestamp);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param timestamp
     * @return
     */
    public static Date toHourDate(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
        String d = format.format(timestamp);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String toHour(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

    public static String getTimestampString(Date date) {
        String formatStr = null;
        formatStr = "M-d HH:mm";
        return (new SimpleDateFormat(formatStr)).format(date);
    }

    public static String getShortDate(long timestamp) {
        String data = null;
        data = getTimestampString(toDate(timestamp));
        return data;
    }

    public static String getStartTimestampString(Date date) {
        String formatStr = null;
        formatStr = "M月d日 HH:mm";
        return (new SimpleDateFormat(formatStr)).format(date);
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static String getEvFormatDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date d1 = new Date(time);
        return format.format(d1);
    }

    /**
     * 将时间戳转成日期字符串
     *
     * @param timeStamp 时间戳的值,类型为：Long
     * @param pattern   转成字符串的格式
     * @return
     */
    public static String getDateStringByTimeSTamp(Long timeStamp, String pattern) {
        String result = null;
        Date date = new Date(timeStamp * 1000);
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        result = sd.format(date);
        return result;
    }
}
