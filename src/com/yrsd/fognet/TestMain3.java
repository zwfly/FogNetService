package com.yrsd.fognet;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 2017/6/27.
 */
public class TestMain3 {

    public static void main(String[] args) {


        MongoDB_WSLink.start();

        MongoCollection mongoCollection = MongoDB_WSLink.getWsRecordCollection();
//        System.out.println(mongoCollection.updateMany(Filters.eq("deviceId", "32ffd8054b48303706692258")
//                , new Document("$set", new Document("deviceName", "我的23").append("deviceAddr", "china3"))
//                , new UpdateOptions().upsert(false)
//        ));

        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.HOUR_OF_DAY, (-1) * 1);
        calendar.add(Calendar.MINUTE, -2);
        Date date = calendar.getTime();
        System.out.println("date: " + date.toString());

        Document document = new Document();
        // 查询j不等于3,k大于10的结果集
        document.put("deviceId", new Document("$eq", "34ffd8055054373610750751"));
        document.put("recordCreateDate", new Document("$gt", date));

        MongoCursor<Document> mongoCursor = mongoCollection.find(document).sort(new Document("recordCreateDate", 1)).iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }
}
