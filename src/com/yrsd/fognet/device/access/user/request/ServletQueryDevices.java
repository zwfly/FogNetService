package com.yrsd.fognet.device.access.user.request;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCursor;
import com.yrsd.fognet.MongoUtil;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.user.entity.UserOwnDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import com.yrsd.fognet.utils.BeanMapConvertUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yrsd.fognet.MongoUtil.bean2DBObject;

/**
 * Created by admin on 2017/6/24.
 */
@WebServlet(name = "ServletQueryDevices", urlPatterns = "/yurunsd/user/devices/query")
public class ServletQueryDevices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletQueryDevices doPost");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Map<String, Object> map = new HashMap<>();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

//        String name = null;
//        String pwd = null;
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        UserAuthentication userAuthentication = new UserAuthentication(cookies);

        if (cookies == null) {
            map.put("isSuccess", "n");
            map.put("isClearCookies", "y");
            map.put("msg", "登录超时，请重新登陆");

            out.print(new Gson().toJson(map));
            out.flush();
            return;
        } else {
            if (!userAuthentication.verify()) {
                map.put("isSuccess", "n");
                map.put("isClearCookies", "n");
                map.put("msg", "密码错误");
                out.print(new Gson().toJson(map));
                out.flush();
                return;
            }
        }

        map.put("isSuccess", "y");
        map.put("isClearCookies", "n");
//            map.put("msg", "密码错误");
        List<Map<String, Object>> mapList = new ArrayList<>();
        String deviceType = request.getParameter("deviceType");
        System.out.println("params  " + new Gson().toJson(request.getParameterMap()));
        System.out.println("devicetype " + deviceType);
        if (deviceType == null) {

            List<WSDeviceBean> list = new ArrayList<>();
            UserInfoBean userInfoBean = new UserInfoBean();

            userInfoBean.setLoginName(userAuthentication.getName());
            userInfoBean.setLoginPassword(userAuthentication.getPwd());

            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(userInfoBean);
            List<String> ownDevicelist = new ArrayList<>();
            if (mongoCursor != null) {
                while (mongoCursor.hasNext()) {
                    ownDevicelist = (List<String>) mongoCursor.next().get("ownDeviceList");
                }
            }
            System.out.println("ownDevicelist  " + new Gson().toJson(ownDevicelist));

            for (String anOwnDevicelist : ownDevicelist) {
                WSDeviceBean wsDeviceBean = new WSDeviceBean();
                wsDeviceBean.setDeviceId(anOwnDevicelist);
                MongoCursor<Document> mcDevice = MongoDB_WSLink.find(wsDeviceBean);
                if (mcDevice != null) {
                    while (mcDevice.hasNext()) {
                        WSDeviceBean bean = new WSDeviceBean();
                        try {
                            MongoUtil.dbObject2Bean(mcDevice.next(), bean);


                            list.add(bean);
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("list: " + new Gson().toJson(list));

            for (WSDeviceBean wsDeviceBean : list) {
                Map<String, Object> map1 = new HashMap<>();
//                try {
////                    map1 = BeanUtils.describe(wsDeviceBean);
//                    BeanUtils.copyProperties(map1, wsDeviceBean);
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
                map1 = BeanMapConvertUtils.bean2Map(wsDeviceBean);
                mapList.add(map1);
                System.out.println("map1  " + new Gson().toJson(map1));

            }
            System.out.println("mapList  " + new Gson().toJson(mapList));

            for (Map<String, Object> aMapList : mapList) {
                WSRecordBean recordBean = new WSRecordBean();
                String id = (String) aMapList.get("deviceId");
                recordBean.setDeviceId(id);
                MongoCursor<Document> mcRecord = null;
                try {
                    mcRecord = MongoDB_WSLink.getWsRecordCollection()
                            .find(bean2DBObject(recordBean)).sort(new Document("recordCreateDate", 1)).iterator();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (mcRecord != null) {
                    Map<String, String> map3 = new HashMap<>();
                    List<WSRecordBean> wsRecordBeanList = new ArrayList<>();
                    while (mcRecord.hasNext()) {
                        WSRecordBean bean = new WSRecordBean();
                        try {
                            MongoUtil.dbObject2Bean(mcRecord.next(), bean);
                            wsRecordBeanList.add(bean);
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        if (wsRecordBeanList.size() > 0) {
                            BeanUtils.populate(wsRecordBeanList.get(wsRecordBeanList.size() - 1), map3);
                            aMapList.putAll(map3);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }


            System.out.println("list1  " + new Gson().toJson(mapList));

        } else {


        }
        map.put("list", mapList);

        out.print(new Gson().toJson(map));
        out.flush();
        System.out.println("out list" + new Gson().toJson(map));

        System.out.println("ServletMyDevices doPost flush");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    private boolean login_verify(String name, String pwd) {
        boolean b = false;

//        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
        if ((name == null) || (pwd == null)) {
            b = false;
        } else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setLoginName(name);

            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(userInfoBean);
            while (mongoCursor.hasNext()) {
                if (pwd.equals((String) mongoCursor.next().get("LoginPassword"))) {
                    b = true;
                }
            }
        }

        return b;
    }
}
