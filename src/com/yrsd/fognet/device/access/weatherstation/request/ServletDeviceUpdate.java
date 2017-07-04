package com.yrsd.fognet.device.access.weatherstation.request;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.yrsd.fognet.device.access.user.request.UserAuthentication;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/6/29.
 */
@WebServlet(name = "ServletDeviceUpdate", urlPatterns = "/weatherstation/config/update")
public class ServletDeviceUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("ServletDeviceUpdate doPost");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");


        Map<String, Object> map = new HashMap<>();

        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        UserAuthentication userAuthentication = new UserAuthentication(cookies);

        if (cookies == null) {
            map.put("isSuccess", "n");
            map.put("msg", "登录超时，请重新登陆");
            out.print(new Gson().toJson(map));
            out.flush();
            return;
        } else {
            if (!userAuthentication.verify()) {
                map.put("isSuccess", "n");
                map.put("msg", "密码错误");
                out.print(new Gson().toJson(map));
                out.flush();
                return;
            }
        }

        System.out.println("ServletDeviceUpdate params: " + new Gson().toJson(request.getParameterMap()));

        String deviceId = request.getParameter("deviceId");
        if (deviceId == null) {
            map.put("isSuccess", "n");
            map.put("msg", "数据错误");
            out.print(new Gson().toJson(map));
            out.flush();
            return;
        }
        String deviceName = request.getParameter("deviceName");
        String deviceAddr = request.getParameter("deviceAddr");
        String phoneNumber = request.getParameter("phoneNumber");

        MongoCollection mongoCollection = MongoDB_WSLink.getWsDeviceCollection();
        System.out.println(mongoCollection.updateMany(Filters.eq("deviceId", deviceId)
                , new Document("$set", new Document("deviceName", deviceName).append("deviceAddr", deviceAddr).append("phoneNumber", phoneNumber)),
                new UpdateOptions().upsert(false)));


        map.put("isSuccess", "y");
        map.put("msg", "修改成功");
        out.print(new Gson().toJson(map));
        out.flush();
        System.out.println("ServletDeviceUpdate doPost flush");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
