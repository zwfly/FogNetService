package com.yrsd.fognet.device.access.weatherstation;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.Document;

import java.lang.reflect.InvocationTargetException;

import static com.yrsd.fognet.MongoUtil.bean2DBObject;

/**
 * Created by admin on 2017/6/14.
 */
public class MongoDB_WSLink {
    private static String host = "localhost";
    private static int port = 27017;
    private static String databaseName = "yurunsddb";
    private static String recordCollectionName = "wsRecord";
    private static String deviceCollectionName = "wsDevice";
    private static String userInfoCollectionName = "userInfo";

    private static MongoDatabase mongoDatabase = null;
    private static MongoClient mongoClient = null;
    private static MongoCollection<Document> wsRecordCollection = null;
    private static MongoCollection<Document> wsDeviceCollection = null;
    private static MongoCollection<UserInfoBean> useInfoCollection = null;


    public static void start() {
        System.out.println("weather station mongodb link start...");
        if (mongoClient == null) {
            mongoClient = new MongoClient(host, port);
            mongoDatabase = mongoClient.getDatabase(databaseName);

            wsRecordCollection = mongoDatabase.getCollection(recordCollectionName);
            wsDeviceCollection = mongoDatabase.getCollection(deviceCollectionName);
            useInfoCollection = mongoDatabase.getCollection(userInfoCollectionName, UserInfoBean.class);


            wsRecordCollection.createIndex(new Document("DeviceId", 1));
            wsDeviceCollection.createIndex(new Document("DeviceId", 1));
            useInfoCollection.createIndex(new Document("LoginName", 1));

        }
    }


    public static void stop() {
        System.out.println("weather station mongodb link stop...");

        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }

    }

    public static void insert(WSRecordBean bean) {
        Document document = new Document();
        try {
//            Document dbObject = bean2DBObject(bean);
            BeanUtils.copyProperties(document, bean);
            wsRecordCollection.insertOne(document);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void insert(WSDeviceBean bean) {
        Document document = new Document();
        try {
//            Document dbObject = bean2DBObject(bean);
            BeanUtils.copyProperties(document, bean);
            wsDeviceCollection.insertOne(document);
            System.out.println("insert " + document.toJson());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void insert(UserInfoBean bean) {

        useInfoCollection.insertOne(bean);
//            System.out.println("insert " + bean.toJson());
    }

    public static MongoCursor<Document> find(WSDeviceBean bean) {

        try {
            BasicDBObject dbObject = bean2DBObject(bean);
            FindIterable<Document> findIterable = wsDeviceCollection.find(dbObject);
            return findIterable.iterator();
//            MongoCursor<Document> mongoCursor = findIterable.iterator();
//            while (mongoCursor.hasNext()) {
//                System.out.println(mongoCursor.next());
//            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MongoCursor<Document> find(WSRecordBean bean) {
        try {
            BasicDBObject dbObject = bean2DBObject(bean);
            FindIterable<Document> findIterable = wsRecordCollection.find(dbObject);
            return findIterable.iterator();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MongoCursor<UserInfoBean> find(UserInfoBean bean) {

//        FindIterable<UserInfoBean> findIterable = useInfoCollection.find(bean);
        FindIterable<UserInfoBean> findIterable = useInfoCollection.find();
        return findIterable.iterator();
//            MongoCursor<Document> mongoCursor = findIterable.iterator();
//            while (mongoCursor.hasNext()) {
//                System.out.println(mongoCursor.next());
//            }

    }

    public static void update(WSDeviceBean bean) {
//        只修改指定字段值，当字段不存在时，则在该文档中添加一个新的字段并赋值

        wsDeviceCollection.updateOne(Filters.eq("deviceId", bean.getDeviceId()), new Document("$set", new Document("sex", 2222)));


    }

    public static MongoCollection<UserInfoBean> getUseInfoCollection() {
        return useInfoCollection;
    }

    public static void setUseInfoCollection(MongoCollection<UserInfoBean> useInfoCollection) {
        MongoDB_WSLink.useInfoCollection = useInfoCollection;
    }

    public static MongoCollection<Document> getWsRecordCollection() {
        return wsRecordCollection;
    }

    public static void setWsRecordCollection(MongoCollection<Document> wsRecordCollection) {
        MongoDB_WSLink.wsRecordCollection = wsRecordCollection;
    }

    public static MongoCollection<Document> getWsDeviceCollection() {
        return wsDeviceCollection;
    }

    public static void setWsDeviceCollection(MongoCollection<Document> wsDeviceCollection) {
        MongoDB_WSLink.wsDeviceCollection = wsDeviceCollection;
    }
}
