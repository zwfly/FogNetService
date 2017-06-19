package com.yrsd.fognet;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.abs;

/**
 * Created by admin on 2017/6/15.
 */
public class mongoTest {

    public static void main(String[] args) {

        Logger log = Logger.getLogger("org.mongodb.driver");
        log.setLevel(Level.OFF);

        MongoDB_WSLink.start();

        WSDeviceBean wsDeviceBean = new WSDeviceBean();
        wsDeviceBean.setDeviceId("32ffd8054b48303706692251");
        MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(wsDeviceBean);
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }

    }


}
