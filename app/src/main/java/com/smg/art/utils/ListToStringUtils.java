package com.smg.art.utils;

import java.util.List;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public class ListToStringUtils {

    public static String listToString(List<String> list){

        if(list==null){
            return null;
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;

        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(";");
            }
            result.append(string);
        }
        return result.toString();
    }
}
