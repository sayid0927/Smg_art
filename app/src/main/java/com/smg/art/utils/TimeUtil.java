package com.smg.art.utils;

import android.util.Log;

/**
 * Created by ss on 2017/11/24.
 */

public class TimeUtil {
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
        Log.e("222222", second + "");
        StringBuffer sb = new StringBuffer();
        if (day > 0 && day < 10) {
            sb.append("0" + day + "天");
        } else if (day >= 10) {
            sb.append("" + day + "天");
        }
        if (hour > 0 && hour < 10) {
            sb.append("0" + hour + "小时");
        } else if (hour >= 10) {
            sb.append("" + hour + "小时");
        }
        if (minute >= 0 && minute < 10) {
            sb.append("0" + minute + "分");
        } else if (minute >= 10) {
            sb.append("" + minute + "分");
        }
        if (second >= 0 && second < 10) {
            Log.e("1111111", second + "");
            sb.append("0" + second + "秒");
        } else if (second >= 10) {
            sb.append("" + second + "秒");
        }
        return sb.toString();
    }
}
