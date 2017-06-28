package com.yrsd.fognet;

import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;

import java.util.Date;
import java.util.Random;

/**
 * Created by admin on 2017/6/28.
 */
public class TestInsertRecord {


    public static void main(String[] args) {

        MongoDB_WSLink.start();


        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            WSRecordBean bean = new WSRecordBean();
            bean.setDeviceId("32ffd8054b48303706692251");
            bean.setDeviceType("2");
            bean.setTemperature((double) (Math.round(random.nextDouble() * 100)));
            bean.setHumidity((double) (Math.round(random.nextDouble() * 100)));
            bean.setWindSpeed((double) (Math.round(random.nextDouble() * 100)));
            bean.setWindDirection("东");
            bean.setPM2d5(Math.round(random.nextFloat() * 1000));
            bean.setPM10(Math.round(random.nextFloat() * 1000));

            bean.setRecordCreateDate(new Date());

            MongoDB_WSLink.insert(bean);

            System.out.println("count " + i);

            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
