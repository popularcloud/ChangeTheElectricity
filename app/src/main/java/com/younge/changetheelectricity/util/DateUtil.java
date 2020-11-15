package com.younge.changetheelectricity.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @return
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }   
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
    /** 
     * 日期格式字符串转换成时间戳 
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);  
        return t;  
    }

    public static String getMoney(String money) {
        if (money.contains(".")) {
            int index = money.indexOf(".");
            if (index == money.length() - 1) {
                money = money + "00";
            } else if (index == money.length() - 2) {
                money = money + "0";
            }else{
                money = money.substring(0,index+3);
            }
        } else {
            money = money + ".00";
        }
        return money;
    }


    public static String getJuLiUnit(String juli) {
        if (juli.contains(".")) {
            int index = juli.indexOf(".");
            juli = juli.substring(0,index);

            Long juliLong = Long.valueOf(juli);
            if(juliLong > 1000){
                return juliLong/1000 + "km";
            }else {
                return juliLong + "m";
            }
        } else {
            Long juliLong = Long.valueOf(juli);
            if(juliLong > 1000){
                return juliLong/1000 + "km";
            }else {
                return juliLong + "m";
            }
        }
    }


    public static String getPresentTimeAddSome(int minute){
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, minute);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(nowTime.getTime());

    }
}