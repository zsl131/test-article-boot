package com.zslin.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zsl on 2018/8/4.
 */
public class DateTools {

    public static String curDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    public static String curDate() {
        return curDate("yyyy-MM-dd");
    }

    public static String curTime() {
        return curDate("yyyy-MM-dd HH:mm:ss");
    }
}
