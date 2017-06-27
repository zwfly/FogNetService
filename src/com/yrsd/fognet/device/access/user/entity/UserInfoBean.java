package com.yrsd.fognet.device.access.user.entity;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/6/15.
 */
public class UserInfoBean implements Serializable {

    private String userId;
    private String userName;
    private String loginName;
    private String loginPassword;

    private List<String> ownDeviceList;



    /////////////////////////////////

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public List<String> getOwnDeviceList() {
        return ownDeviceList;
    }

    public void setOwnDeviceList(List<String> ownDeviceList) {
        this.ownDeviceList = ownDeviceList;
    }
}
