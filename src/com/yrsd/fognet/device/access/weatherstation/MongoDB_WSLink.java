package com.yrsd.fognet.device.access.weatherstation;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.yrsd.fognet.device.access.user.entity.UserInfoBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import org.bson.Document;

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
    private static MongoCollection wsRecordCollection = null;
    private static MongoCollection wsDeviceCollection = null;
    private static MongoCollection useInfoCollection = null;


    public static void start() {
        System.out.println("weather station mongodb link start...");
        if (mongoClient == null) {
            mongoClient = new MongoClient(host, port);
            mongoDatabase = mongoClient.getDatabase(databaseName);

            wsRecordCollection = mongoDatabase.getCollection(recordCollectionName);
            wsDeviceCollection = mongoDatabase.getCollection(deviceCollectionName);
            useInfoCollection = mongoDatabase.getCollection(userInfoCollectionName);


            wsRecordCollection.createIndex(new Document("DeviceId", 1));
            wsDeviceCollection.createIndex(new Document("DeviceId", 1));
            useInfoCollection.createIndex(new Document("LoginName", 1));

        }
    }


    public static MongoCollection getWsRecordCollection() {
        return wsRecordCollection;
    }

    public static void setWsRecordCollection(MongoCollection wsRecordCollection) {
        MongoDB_WSLink.wsRecordCollection = wsRecordCollection;
    }

    public static void stop() {
        System.out.println("weather station mongodb link stop...");

        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }

    }

    public static void insert(WSRecordBean bean) {

        try {
            Document dbObject = bean2DBObject(bean);
            wsRecordCollection.insertOne(dbObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void insert(WSDeviceBean bean) {

        try {
            Document dbObject = bean2DBObject(bean);
            wsDeviceCollection.insertOne(dbObject);
            System.out.println("insert " + dbObject.toJson());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void insert(UserInfoBean bean) {

        try {
            Document dbObject = bean2DBObject(bean);
            useInfoCollection.insertOne(dbObject);
            System.out.println("insert " + dbObject.toJson());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static MongoCursor<WSDeviceBean> find(WSDeviceBean bean) {

        try {
            Document dbObject = bean2DBObject(bean);
            FindIterable<WSDeviceBean> findIterable = wsDeviceCollection.find(dbObject);
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

    public static MongoCursor<WSRecordBean> find(WSRecordBean bean) {
        try {
            Document dbObject = bean2DBObject(bean);
            FindIterable<WSRecordBean> findIterable = wsRecordCollection.find(dbObject);
            return findIterable.iterator();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MongoCursor<UserInfoBean> find(UserInfoBean bean) {

        try {
            Document dbObject = bean2DBObject(bean);
            FindIterable<UserInfoBean> findIterable = useInfoCollection.find(dbObject);
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

    public static void update(WSDeviceBean bean) {
//        只修改指定字段值，当字段不存在时，则在该文档中添加一个新的字段并赋值

        wsDeviceCollection.updateOne(Filters.eq("deviceId", bean.getDeviceId()), new Document("$set", new Document("sex", 2222)));


    }

    public static MongoCollection getWsDeviceCollection() {
        return wsDeviceCollection;
    }

    public static void setWsDeviceCollection(MongoCollection wsDeviceCollection) {
        MongoDB_WSLink.wsDeviceCollection = wsDeviceCollection;
    }
}
