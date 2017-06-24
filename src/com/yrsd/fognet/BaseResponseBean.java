package com.yrsd.fognet;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/24.
 */
public class BaseResponseBean implements Serializable {

    String isSuccess;
    String msg;
    String isClearCookies;


    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsClearCookies() {
        return isClearCookies;
    }

    public void setIsClearCookies(String isClearCookies) {
        this.isClearCookies = isClearCookies;
    }
}
