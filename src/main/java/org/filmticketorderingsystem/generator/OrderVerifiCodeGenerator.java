package org.filmticketorderingsystem.generator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ���� on 2016/5/12.
 * 订单验证码生成器,=年月日+秒数(5位,一天够用)+3位流水号
 */
public class OrderVerifiCodeGenerator {
    private static OrderVerifiCodeGenerator generator = new OrderVerifiCodeGenerator();
    //自认为同一时刻不会超过1000张订单数
    private static final int MAX_SERIAL_NUMBER = 1000;
    private static final int MAX_SEC = 99999;
    //大于1000的话,可以利用5位数的最大值99999-一天的最大秒数86400的空间
    //记录当前在  最大值99999-一天的最大秒数86400的空间 借到的开始值
    private int now_borrow = MAX_SEC_OF_DAY;

    //一天总秒数
    private static final int MAX_SEC_OF_DAY = 24 * 60 * 60;//86400
    //记录每个时刻的流水号
    private Map<Integer, Integer> sec2SerialNumber = new HashMap<Integer, Integer>();
    private SimpleDateFormat dateFormator = new SimpleDateFormat("yyyyMMdd");
    private DecimalFormat secFormator = new DecimalFormat("00000");
    private DecimalFormat serialNumFormator = new DecimalFormat("000");

    private OrderVerifiCodeGenerator() {
        for(int i = 0; i < MAX_SEC; i++){
            sec2SerialNumber.put(i, 0);
        }
    }

    public static OrderVerifiCodeGenerator getGenerator(){
        if(generator != null){
            synchronized (generator){
                if(generator != null){
                    return generator;
                }
                else{
                    generator = new OrderVerifiCodeGenerator();
                }
            }
        }

        return null;
    }

    public String generate(Date now){
        String nowStr = dateFormator.format(now);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);//转换现在的时间

        int sec = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        //把当前时刻转换成一天中的秒数形式
        int secOfDay = getSecondOfDay(hour, minute, sec);

        String secOfDayStr = secFormator.format(secOfDay);

        int serialNumber = -1;

        synchronized (sec2SerialNumber){
            if(sec2SerialNumber.get(secOfDay) >= MAX_SERIAL_NUMBER){
                //大于1000的话,可以利用5位数的最大值99999-一天的最大秒数86400的空间
                //取一天中秒数的最大值来开始尝试借空间
                int borrow = this.now_borrow;

                if(sec2SerialNumber.get(borrow) >= MAX_SERIAL_NUMBER){
                    borrow ++;
                }

                //记录该借的空间位置值,以便于不用老是重新开始
                this.now_borrow = borrow;

                serialNumber = sec2SerialNumber.get(borrow);
                sec2SerialNumber.put(borrow, serialNumber + 1);
            }
            else{
                serialNumber = sec2SerialNumber.get(secOfDay);
                sec2SerialNumber.put(secOfDay, serialNumber + 1);
            }
        }

        if(serialNumber != -1){
            String serialNumberStr = serialNumFormator.format(serialNumber);

            return nowStr + secOfDayStr + serialNumberStr;
        }

        return null;
    }

    public boolean clear(){
        boolean flag = false;

        this.now_borrow = MAX_SEC_OF_DAY;

        synchronized (sec2SerialNumber){
            for(int i = 0; i < MAX_SEC; i++){
                sec2SerialNumber.put(i, 0);
            }

            flag = true;
        }

        return flag;
    }

    private Integer getSecondOfDay(int hour, int minute, int second){
        return hour*60*60 + minute * 60 + second;
    }
}
