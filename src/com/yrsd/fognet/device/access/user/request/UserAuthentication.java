package com.yrsd.fognet.device.access.user.request;

import com.mongodb.client.MongoCursor;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import org.bson.Document;

import javax.servlet.http.Cookie;

/**
 * Created by admin on 2017/6/26.
 */
public class UserAuthentication {

    private Cookie[] cookies;

    private String name;
    private String pwd;

    public UserAuthentication(Cookie[] cookies) {
        this.cookies = cookies;
    }

    public UserAuthentication(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean verify() {
        boolean b = false;

        if (cookies != null) {
            b = this.verify(cookies);
        } else if (name != null && pwd != null) {
            b = this.verify(name, pwd);
        } else {
            b = false;
        }

        return b;
    }

    private boolean verify(String n, String p) {
        boolean b = false;

        if ((n != null) && (p != null)) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setLoginName(n);
            userInfoBean.setLoginPassword(p);

            MongoCursor<UserInfoBean> mongoCursor = MongoDB_WSLink.find(userInfoBean);
            if (mongoCursor.hasNext()) {
                b = true;
            } else {
                b = false;
            }
        } else {
            b = false;
        }

        return b;
    }

    private boolean verify(Cookie[] cookies) {

        boolean b = false;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("name")) {
                name = cookie.getValue();
            } else if (cookie.getName().equals("pwd")) {
                pwd = cookie.getValue();
            }
        }
        b = verify(name, pwd);

        return b;
    }
}
