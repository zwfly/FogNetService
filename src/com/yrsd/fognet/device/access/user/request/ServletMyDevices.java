package com.yrsd.fognet.device.access.user.request;

import com.google.gson.Gson;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCursor;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.user.entity.UserOwnDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
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

/**
 * Created by admin on 2017/6/24.
 */
@WebServlet(name = "ServletMyDevices", urlPatterns = "/yurunsd/user/devices")
public class ServletMyDevices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletMyDevices doPost");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String> map = new HashMap<>();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = null;
        String pwd = null;
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            map.put("isSuccess", "n");
            map.put("isClearCookies", "y");
            map.put("msg", "登录超时，请重新登陆");

            out.print(new Gson().toJson(map));
            out.flush();
            return;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")) {
                    name = cookie.getValue();
                } else if (cookie.getName().equals("pwd")) {
                    pwd = cookie.getValue();
                }
            }
        }

        if (!login_verify(name, pwd)) {
            map.put("isSuccess", "n");
            map.put("isClearCookies", "n");
            map.put("msg", "密码错误");

            out.print(new Gson().toJson(map));
            out.flush();
            return;

        } else {
            map.put("isSuccess", "y");
            map.put("isClearCookies", "n");
//            map.put("msg", "密码错误");

        }

        String deviceType = request.getParameter("DeviceType");

        System.out.println("params  " + new Gson().toJson(request.getParameterMap()));

        if (deviceType == null) {

            List<WSDeviceBean> list = new ArrayList<>();
            UserInfoBean userInfoBean = new UserInfoBean();


            userInfoBean.setLoginName(name);
            userInfoBean.setLoginPassword(pwd);

            MongoCursor<UserInfoBean> mongoCursor = MongoDB_WSLink.find(userInfoBean);
            List<UserOwnDeviceBean> ownDevicelist = null;
            if (mongoCursor != null) {
                while (mongoCursor.hasNext()) {
                    ownDevicelist = mongoCursor.next().getOwnDevicelist();
                }
            }
            if (ownDevicelist != null) {
                for (int i = 0; i < ownDevicelist.size(); i++) {
                    String id = ownDevicelist.get(i).getDeviceId();
                    WSDeviceBean wsDeviceBean = new WSDeviceBean();
                    wsDeviceBean.setDeviceId(id);
                    MongoCursor<WSDeviceBean> mcDevice = MongoDB_WSLink.find(wsDeviceBean);
                    if (mcDevice != null) {
                        while (mcDevice.hasNext()) {
                            wsDeviceBean = mcDevice.next();
                        }
                    }


                    Document document = new Document().append("DeviceId", id);
                    WSRecordBean wsRecordBean = new WSRecordBean();
                    wsRecordBean.setDeviceId(id);

                    FindIterable findIterable = MongoDB_WSLink.getWsRecordCollection().find(document).sort(new Document("RecordCreateDate", 1));


                    if (mcRecord != null) {
                        while (mcRecord.hasNext()) {
//                            wsRecordBean = mcRecord.next();
                            try {
                                BeanUtils.copyProperties(wsDeviceBean, wsRecordBean);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    list.add(wsDeviceBean);
                }


            }

        } else {


        }


        out.print(new Gson().toJson(map));
        out.flush();
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
