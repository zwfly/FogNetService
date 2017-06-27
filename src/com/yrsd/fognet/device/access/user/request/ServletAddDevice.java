package com.yrsd.fognet.device.access.user.request;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.user.entity.UserOwnDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by admin on 2017/6/26.
 */
@WebServlet(name = "ServletAddDevice", urlPatterns = "/yurunsd/user/device/add")
public class ServletAddDevice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletAddDevice doPost");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String> map = new HashMap<>();
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        UserAuthentication userAuthentication = new UserAuthentication(cookies);
        if (!userAuthentication.verify()) {
            map.put("isSuccess", "n");
            map.put("isClearCookies", "n");
            map.put("msg", "密码错误");
            out.print(new Gson().toJson(map));
            out.flush();

            System.out.println("ServletAddDevice verify fail");
            return;
        } else {
            map.put("isSuccess", "n");
            map.put("isContain", "n");
//            map.put("msg", "添加成功");

        }
        String uid = request.getParameter("uid");
//        String type = request.getParameter("type");

        System.out.println("ServletAddDevice params: " + new Gson().toJson(request.getParameterMap()));

        UserInfoBean bean = new UserInfoBean();
        bean.setLoginName(userAuthentication.getName());

        MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(bean);
        while (mongoCursor.hasNext()) {
            List<String> list = (List<String>) mongoCursor.next().get("ownDeviceList");
            if (list == null) {
                list = new ArrayList<>();
            }
            if (uid != null) {

                if (list.contains(uid)) {
                    map.put("isSuccess", "n");
                    map.put("isContain", "y");
                    map.put("msg", "设备已存在");
                } else {
                    list.add(uid);
                    map.put("isSuccess", "y");
                    map.put("isContain", "n");
                    map.put("msg", "添加成功");
                }
            }
            MongoCollection mongoCollection = MongoDB_WSLink.getUseInfoCollection();
            mongoCollection.updateOne(Filters.eq("loginName", userAuthentication.getName()),
                    new Document("$set", new Document("ownDeviceList", list)));
        }

        out.print(new Gson().toJson(map));
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletAddDevice doGet");

    }

    private boolean uid_verify(int type, String uid) {

        return true;
    }


}
