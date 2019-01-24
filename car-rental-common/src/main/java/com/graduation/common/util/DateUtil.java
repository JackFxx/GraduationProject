package com.graduation.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 时间处理工具
 * @Author fuxiaoxiang2
 * @Create 2019/1/24 11:56
 */
public class DateUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
    private static final String DATE_FORMATE_FIRST = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMATE_SECOND = "yyyy/MM/dd HH:mm:ss";

    private DateUtil() {

    }

    /**
     * @return java.lang.String
     * @Author fuxiaoxiang2
     * @Description date转特定格式时间
     * @Date 12:02 2019/1/24
     * @Param [date]
     **/
    public static String dateToSimpDateFormat_1(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_FIRST);
        return sdf.format(date);
    }

    /**
     * @return java.lang.String
     * @Author fuxiaoxiang2
     * @Description date转特定格式时间
     * @Date 12:02 2019/1/24
     * @Param [date]
     **/
    public static String dateToSimpDateFormat_2(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_SECOND);
        return sdf.format(date);
    }

    /**
     * @return long
     * @Author fuxiaoxiang2
     * @Description 特定时间格式转unix时间戳
     * @Date 12:03 2019/1/24
     * @Param [dateTime]
     **/
    public static long dateFormatToUnixTime(String dateTime) {
        if (StringUtils.isBlank(dateTime)) {
            LOG.warn("please input dateTime");
            return -1;
        }
        int len = dateTime.length();
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(dateTime.replaceAll("-", "").length() != len ? DATE_FORMATE_FIRST : DATE_FORMATE_SECOND);
            return sdf.parse(dateTime).getTime();
        } catch (Exception e) {
            LOG.error("parse time error", e);
            return -1;
        }
    }
}
