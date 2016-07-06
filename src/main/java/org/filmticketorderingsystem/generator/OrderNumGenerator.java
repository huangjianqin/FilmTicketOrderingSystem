package org.filmticketorderingsystem.generator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ���� on 2016/5/12.
 * 订单号生成器,=年月日+流水号
 */
public class OrderNumGenerator {
    private static OrderNumGenerator generator = new OrderNumGenerator();
    //自认为日订单数不会超过这个数
    private static final Long MAX_SERIAL_NUMBER = 100000000L;
    //利用一些额外的框架自动重置
    private Long availableSerialNumber = 0L;//流水号
    private DecimalFormat serialNumFormator = new DecimalFormat("00000000");
    private SimpleDateFormat dateFormator = new SimpleDateFormat("yyyyMMdd");

    private OrderNumGenerator() {
    }

    public static OrderNumGenerator getGenerator(){
        if(generator != null){
            synchronized (generator){
                if(generator != null){
                    return generator;
                }
                else{
                    generator = new OrderNumGenerator();
                }
            }
        }

        return null;
    }

    public String generate(Date now){
        String nowStr = dateFormator.format(now);
        String serialNumberStr = null;
        Long serialNumber = -1L;

        synchronized (availableSerialNumber){
            serialNumber = availableSerialNumber;
            availableSerialNumber++;

            if(serialNumber >= MAX_SERIAL_NUMBER){
                //超过100000000张订单自动出错,因为不可能存储更多订单了
                serialNumber = -1L;
            }
        }

        if(serialNumber != -1){
            serialNumberStr = serialNumFormator.format(serialNumber);
            return nowStr + serialNumberStr;
        }

        return null;
    }

    public String dateFormat(Date now){
        return dateFormator.format(now);
    }

    public boolean clear(){
        boolean flag = false;

        synchronized (availableSerialNumber){
            availableSerialNumber = 0L;
            flag = true;
        }

        return flag;
    }

}
