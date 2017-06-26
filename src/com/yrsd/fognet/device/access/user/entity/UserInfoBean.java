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
public class UserInfoBean extends Document implements Serializable {

    private String UserId;
    private String UserName;
    private String LoginName;
    private String LoginPassword;

    private List<UserOwnDeviceBean> OwnDevicelist;

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<UserInfoBean>(this, codecRegistry.get(UserInfoBean.class));
    }

    /////////////////////////////////
    // 以下都是获取和设置字段值
    public ObjectId getId() {
        return this.getObjectId("_id");
    }

    public void setId(ObjectId id) {
        this.append("_id", id);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
        this.append("UserId", userId);
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
        this.append("UserName", userName);
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
        this.append("LoginName", loginName);
    }

    public String getLoginPassword() {
        return LoginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
        this.append("LoginPassword", loginPassword);
    }

    public List<UserOwnDeviceBean> getOwnDevicelist() {
        return OwnDevicelist;
    }

    public void setOwnDevicelist(List<UserOwnDeviceBean> ownDevicelist) {
        OwnDevicelist = ownDevicelist;
        this.append("OwnDevicelist", ownDevicelist);
    }
}
