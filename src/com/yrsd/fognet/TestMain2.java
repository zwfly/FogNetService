package com.yrsd.fognet;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

/**
 * Created by admin on 2017/6/23.
 */
public class TestMain2 {

    public static void main(String[] args) {


        WSDeviceBean wsDeviceBean = new WSDeviceBean();

        wsDeviceBean.setTemperature(123);
        wsDeviceBean.setDeviceId("789");

        System.out.println(new Gson().toJson(wsDeviceBean));
    }

    private static boolean findNameExist(String name) {
        boolean b = false;
        if (name == null) {
            b = false;
            System.out.println("name " + name);
        } else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setLoginName(name);

            MongoCursor<UserInfoBean> mongoCursor = MongoDB_WSLink.find(userInfoBean);
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
