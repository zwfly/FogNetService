package com.yrsd.fognet.device.access.user.request;

import com.mongodb.client.MongoCursor;
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

/**
 * Created by admin on 2017/6/21.
 */
@WebServlet(name = "ServletRegister", urlPatterns = "/yurunsd/user/register")
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Register doPost");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String nickName = request.getHeader("UserName");
        String loginName = request.getHeader("LoginName");
        String loginPwd = request.getHeader("LoginPassword");


        if (findNameExist(loginName)) {
            response.addHeader("isSuccess", "n");
            out.write("账号已存在");
        } else {
            UserInfoBean bean = new UserInfoBean();
            bean.setLoginName(loginName);
            bean.setUserName(nickName);
            bean.setLoginPassword(loginPwd);
            MongoDB_WSLink.insert(bean);

            response.addHeader("isSuccess", "y");
            out.write("注册成功");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private boolean findNameExist(String name) {
        boolean b = false;
        if (StringUtils.equals(name, null)) {
            b = false;
        } else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setLoginName(name);

            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(userInfoBean);
            Boolean bb = mongoCursor.hasNext();
            if (ObjectUtils.notEqual(bb, null)) {
                b = bb;
            }
        }
        return b;
    }
}
