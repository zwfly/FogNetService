package com.yrsd.fognet;

import com.google.gson.Gson;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by admin on 2017/6/27.
 */
public class TestMain3 {

    public static void main(String[] args) {


        WSDeviceBean bean = new WSDeviceBean();

        try {
            BeanUtils.setProperty(bean,"deviceId","123123");
            System.out.println(bean.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
