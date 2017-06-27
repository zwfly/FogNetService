package com.yrsd.fognet;

import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by admin on 2017/6/27.
 */
public class TestMain4 {

    public static void main(String[] args) {

        MongoDB_WSLink.start();

        WSDeviceBean bean = new WSDeviceBean();
        bean.setDeviceId("32ffd8054b48303706692252");
        bean.setLastOnLineDate(new Date());
        bean.setLastOffLineDate(new Date());
        bean.setDeviceAddr("山东临沂");

        MongoDB_WSLink.insert(bean);

    }
}
