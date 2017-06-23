package com.yrsd.fognet;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by admin on 2017/6/23.
 */
public class TestMain2 {

    public static void main(String[] args) {

        String msg1 = null;
        String msg2 = null;

//        boolean b = StringUtils.isEmpty(msg1) || StringUtils.isEmpty(msg2);
        System.out.println(msg1.length()==0);
        System.out.println(login_verify(msg1, msg2));


    }

    private static boolean login_verify(String name, String pwd) {
        boolean b = false;

        System.out.println("name = " + name + ", " + "pwd " + pwd);

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            b = false;
        } else {
//            UserInfoBean userInfoBean = new UserInfoBean();
//            userInfoBean.setLoginName(name);
//
//            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(userInfoBean);
//            while (mongoCursor.hasNext()) {
//                if (StringUtils.equals(pwd, (String) mongoCursor.next().get("LoginPassword"))) {
//                    b = true;
//                }
//            }
        }

        System.out.println("login_verify " + b);
        return b;
    }
}
