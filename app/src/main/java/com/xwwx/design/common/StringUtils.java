package com.xwwx.design.common;

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @功能
 * @作者 Administrator
 * @创建日期 2018/12/7 0007
 */

public class StringUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static SimpleDateFormat dateFormater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * 得到当前日期时间
     *
     * @return
     */
    public static String getCurrDateTime() {
        Date today = new Date();
        return dateFormater.format(today.getTime());
    }
}
