package com.cornor.gank.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/8
 */

public class DateUtil {
    public static String fromDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("MM-dd");
        return dateFormat.format(date);
    }
}
