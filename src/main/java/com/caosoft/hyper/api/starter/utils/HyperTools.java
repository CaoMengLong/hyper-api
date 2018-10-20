package com.caosoft.hyper.api.starter.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * **************************************************************
 * Copyright (c) 1996-2018 CaoSoft.com
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name HyperTools
 * @describe 常用方法集合
 * @create 2018-02-09 10:04
 * **************************************************************
 */
public class HyperTools {

    /*
     * @author 曹梦龙 <138888611@qq.com>
     * @date  2018/2/9  10:06
     * @methodName getISO8601Timestamp
     * @describe 传入Data类型日期，返回字符串类型时间（ISO8601标准时间）
     * @param [date]  
     * @return java.lang.String  
     */
    public static String getISO8601Timestamp(Date date){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    /**
     * @author 曹梦龙<138888611@qq.com>
     * @date 2018/4/27 17:31
     * @methodName getNowTimeStamp
     * @describe 获取当前时间戳
     * @param []
     * @return java.lang.Integer
     */
    public static Integer getNowTimeStamp(){
        return  (int)(System.currentTimeMillis()/1000);
    }

    /**
     * 产生随机的六位数
     * @return 六位数
     */
    public static String getSix(){
        Random rad=new Random();

        String result  = rad.nextInt(1000000) +"";

        if(result.length()!=6){
            return getSix();
        }
        return result;
    }

}
