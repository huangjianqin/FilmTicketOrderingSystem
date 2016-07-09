package org.filmticketorderingsystem.generator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 健勤 on 2016/7/7.
 */
public class DateGenerator {
    private static DateGenerator generator = new DateGenerator();
    private SimpleDateFormat YMDFormator = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat YMDHmSFormator = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat YMDHmFormator = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private DateGenerator() {
    }

    public static DateGenerator getGenerator(){
        if(generator == null){
            generator = new DateGenerator();
        }
        return generator;
    }

    public String getFormatedNowDate(){
        Date date = new Date();

        return YMDFormator.format(date);
    }

    public String formatYMD(Date date){
        return YMDFormator.format(date);
    }

    public String getFormatedNowFullDate(){
        Date date = new Date();

        return YMDHmSFormator.format(date);
    }

    public String getFormatedNowDateBefore10m(){
        Date date = new Date();

        //获取十分钟前的时间
        date.setTime(date.getTime() - (1000*10*60));

        return YMDHmFormator.format(date);
    }

    public String getFormatedNowFullDateBefore15m(){
        Date date = new Date();

        //获取十分钟前的时间
        date.setTime(date.getTime() - (1000*15*60));

        return YMDHmSFormator.format(date);
    }

}
