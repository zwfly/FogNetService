package com.yrsd.fognet;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import com.yrsd.fognet.utils.MqttUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

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


        String msg = "";

        System.out.println(StringUtils.isEmpty(msg));


    }


}
