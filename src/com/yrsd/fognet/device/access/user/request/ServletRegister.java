package com.yrsd.fognet.device.access.user.request;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/6/21.
 */
@WebServlet(name = "ServletRegister", urlPatterns = "/user/register")
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Register doPost");

        Map<String, String> map = new HashMap<>();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String nickName = request.getParameter("nickName");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");


        System.out.println("params  " + new Gson().toJson(request.getParameterMap()));

        if (findNameExist(name)) {
            map.put("isSuccess", "n");
            map.put("msg", "账号已存在");
            System.out.println("map1: " + new Gson().toJson(map));
        } else {
            UserInfoBean bean = new UserInfoBean();
            bean.setLoginName(name);
            bean.setUserName(nickName);
            bean.setLoginPassword(pwd);
            bean.setOwnDeviceList(new ArrayList<>());
            MongoDB_WSLink.insert(bean);

            map.put("isSuccess", "y");
            map.put("msg", "注册成功");
            System.out.println("map2: " + new Gson().toJson(map));

        }
        out.print(new Gson().toJson(map));
        out.flush();
        System.out.println("register doPost flush");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private boolean findNameExist(String name) {
        boolean b = false;
        if (name == null) {
            b = false;
        } else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setLoginName(name);

            System.out.println("findNameExist 1");
            Document document = new Document();
            document.append("LoginName", name);
            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(userInfoBean);
            System.out.println("findNameExist 2");
            System.out.println("findNameExist 3" + mongoCursor);
            if (mongoCursor != null) {
                b = mongoCursor.hasNext();
            }
        }
        return b;
    }
}
