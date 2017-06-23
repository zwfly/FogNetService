package com.yrsd.fognet.device.access.user.request;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/6/21.
 */
@WebServlet(name = "ServletLogin", urlPatterns = "/yurunsd/user/login")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login doPost");
        //设置服务器端以UTF-8编码进行输出
        response.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码进行接收,解决中文乱码问题
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String> map = new HashMap<>();
        System.out.println("test1  ");

        System.out.println("test2  " + login_verify(null, null));

        PrintWriter out = response.getWriter();
        //获取浏览器访问访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        String name = null;
        String pwd = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")) {
                    name = cookie.getValue();
                } else if (cookie.getName().equals("pwd")) {
                    pwd = cookie.getValue();
                }
            }

            if (login_verify(name, pwd)) {
                map.put("isSuccess", "y");
            } else {
                map.put("isSuccess", "n");
            }
        } else {
            System.out.println("cookie null");
            name = request.getHeader("name");
            pwd = request.getHeader("pwd");
            String isSave = request.getHeader("isSave");

            if (login_verify(name, pwd)) {
                if (StringUtils.equals(isSave, "y")) {
                    Cookie cookieName = new Cookie("name", name);
                    Cookie cookiePwd = new Cookie("pwd", pwd);

                    cookieName.setMaxAge(16 * 24 * 3600);
                    cookiePwd.setMaxAge(16 * 24 * 3600);

                    response.addCookie(cookieName);
                    response.addCookie(cookiePwd);
                }
                map.put("isSuccess", "y");
                map.put("msg", "欢迎");
            } else {
                map.put("isSuccess", "n");
                map.put("msg", "用户名或密码错误");
            }
            out.print(new Gson().toJson(map));
            out.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login doGet");

        //设置服务器端以UTF-8编码进行输出
        response.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码进行接收,解决中文乱码问题
        response.setContentType("text/html;charset=UTF-8");


    }

    private boolean login_verify(String name, String pwd) {
        boolean b = false;

        System.out.println("name = " + name + ", " + "pwd " + pwd);

//        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
        if ((name == null) || (pwd == null)) {
            b = false;
        } else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setLoginName(name);

            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(userInfoBean);
            while (mongoCursor.hasNext()) {
                if (StringUtils.equals(pwd, (String) mongoCursor.next().get("LoginPassword"))) {
                    b = true;
                }
            }
        }

        System.out.println("login_verify " + b);
        return b;
    }

}
