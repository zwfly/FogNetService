package com.yrsd.fognet;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.user.entity.UserOwnDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import com.yrsd.fognet.utils.BeanMapConvertUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by admin on 2017/6/23.
 */
public class TestMain2 {

    public static void main(String[] args) {

        MongoDB_WSLink.start();

        WSDeviceBean wsDeviceBean = new WSDeviceBean();
        wsDeviceBean.setDeviceId("32ffd8054b48303706692251");
        MongoCursor<Document> mcDevice = MongoDB_WSLink.find(wsDeviceBean);
        if (mcDevice != null) {
            while (mcDevice.hasNext()) {
                WSDeviceBean bean = new WSDeviceBean();
                Document document = mcDevice.next();
                try {

//                    bean = MongoUtil.dbObject2Bean(document, bean);

                    Field[] fields = bean.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        String varName = field.getName();
                        System.out.println(varName);

                        Object object = document.get(varName);

                        if (object != null) {
                            BeanUtils.setProperty(bean, varName, object);
                            System.out.println(bean.toString());
                        }
                    }


//                    BeanUtils.copyProperties(bean, document);

                    System.out.println("1 " + new Gson().toJson(bean));
                    System.out.println("2 " + document);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean findNameExist(String name) {
        boolean b = false;
        if (name == null) {
            b = false;
            System.out.println("name " + name);
        } else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setLoginName(name);

            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(userInfoBean);
//            if (mongoCursor != null) {
//                b = mongoCursor.hasNext();
//            }
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }
        }
        return b;
    }
}
