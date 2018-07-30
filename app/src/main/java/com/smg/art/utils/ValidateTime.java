package com.smg.art.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lenovo on 2018/7/30.
 */

public class ValidateTime {
    public static boolean TimeCompare(String nowTime,String endTime){
        SimpleDateFormat CurrentTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date now=CurrentTime.parse(nowTime);
            Date end=CurrentTime.parse(endTime);
            if(now.getTime() < end.getTime()) {
                // 当前时间小
                return  false;
            }else if(now.getTime() > end.getTime()){
                // 当前时间大
                return  true;
            }else  if(now.getTime() == end.getTime()){
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24)+(day*24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return hour + "," + min + "," + sec + ",";
    }


    }
