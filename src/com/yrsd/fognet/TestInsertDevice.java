package com.yrsd.fognet;

import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by admin on 2017/6/27.
 */
public class TestInsertDevice {

    public static void main(String[] args) {

        MongoDB_WSLink.start();


        for (int i = 0; i < 9; i++) {

            WSDeviceBean bean = new WSDeviceBean();
            bean.setDeviceId("32ffd8054b4830370669225" + i);
            bean.setLastOnLineDate(new Date());
            bean.setLastOffLineDate(new Date());
            bean.setDeviceAddr("山东临沂");
            bean.setDeviceName("我的" + i);
            bean.setDeviceCreateDate(new Date());
            bean.setDeviceType("2");

            bean.setPhoneNumber("156987456221");

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
