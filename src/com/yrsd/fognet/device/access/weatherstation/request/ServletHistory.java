package com.yrsd.fognet.device.access.weatherstation.request;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.yrsd.fognet.MongoUtil;
import com.yrsd.fognet.device.access.user.request.UserAuthentication;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by admin on 2017/7/3.
 */
@WebServlet(name = "ServletHistory", urlPatterns = "/weatherstation/record/query")
public class ServletHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("ServletHistory doPost");
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
        String deviceId = request.getParameter("deviceId");
        if (deviceId == null) {
            map.put("isSuccess", "n");
            map.put("msg", "数据错误");
            out.print(new Gson().toJson(map));
            out.flush();
            return;
        }


        List<Map<String, Object>> mapList = new ArrayList<>();

        Integer aheadHours = Integer.valueOf(request.getParameter("aheadHours"));
        String temperature = request.getParameter("temperature");
        String humidity = request.getParameter("humidity");
        String PM2d5 = request.getParameter("PM2d5");
        String PM10 = request.getParameter("PM10");
        String windSpeed = request.getParameter("windSpeed");
        String windDirection = request.getParameter("windDirection");

        if (aheadHours == null) {
            out.print(new Gson().toJson(mapList));
            out.flush();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, (-1) * aheadHours);
//        calendar.add(Calendar.MINUTE, (-1) * aheadHours);
        Date date = calendar.getTime();

        MongoCollection mongoCollection = MongoDB_WSLink.getWsRecordCollection();
        Document document = new Document();
        // 查询j不等于3,k大于10的结果集
        document.put("deviceId", new Document("$eq", deviceId));
        document.put("recordCreateDate", new Document("$gt", date));
        MongoCursor<Document> mongoCursor = mongoCollection.find(document)
                .sort(new Document("recordCreateDate", 1)).iterator();
        while (mongoCursor.hasNext()) {

            WSRecordBean recordBean = new WSRecordBean();
            try {
                MongoUtil.dbObject2Bean(mongoCursor.next(), recordBean);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            Map<String, Object> m = new HashMap<String, Object>();
            if (StringUtils.equalsIgnoreCase(temperature, "y")) {
                m.put("temperature", recordBean.getTemperature());
            }
            if (StringUtils.equalsIgnoreCase(humidity, "y")) {
                m.put("humidity", recordBean.getHumidity());
            }
            if (StringUtils.equalsIgnoreCase(PM2d5, "y")) {
                if (ObjectUtils.notEqual(null, recordBean.getPM2d5())) {
                    m.put("PM2d5", recordBean.getPM2d5());
                }
            }
            if (StringUtils.equalsIgnoreCase(PM10, "y")) {
                if (ObjectUtils.notEqual(null, recordBean.getPM10())) {
                    m.put("PM10", recordBean.getPM10());
                }
            }
            if (StringUtils.equalsIgnoreCase(windSpeed, "y")) {
                m.put("windSpeed", recordBean.getWindSpeed());
            }
            if (StringUtils.equalsIgnoreCase(windDirection, "y")) {
                m.put("windDirection", recordBean.getWindDirection());
            }

            if (!StringUtils.equalsIgnoreCase(temperature, "y")
                    && !StringUtils.equalsIgnoreCase(humidity, "y")
                    && !StringUtils.equalsIgnoreCase(PM2d5, "y")
                    && !StringUtils.equalsIgnoreCase(PM10, "y")
                    && !StringUtils.equalsIgnoreCase(windSpeed, "y")
                    && !StringUtils.equalsIgnoreCase(windDirection, "y")) {
                m.put("temperature", recordBean.getTemperature());
                m.put("humidity", recordBean.getHumidity());
                m.put("PM2d5", recordBean.getPM2d5());
                m.put("PM10", recordBean.getPM10());
                m.put("windSpeed", recordBean.getWindSpeed());
                m.put("windDirection", recordBean.getWindDirection());
            }

            mapList.add(m);
        }

        map.put("isSuccess", "y");
        map.put("msg", "查询成功");
        map.put("list", mapList);
        out.print(new Gson().toJson(map));
        out.flush();
        System.out.println("ServletHistory doPost flush");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
