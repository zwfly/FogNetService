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

        MqttUtils mqttUtils = new MqttUtils("tcp://192.168.1.10", "123345",
                "admin", "admin", new MemoryPersistence());

/*
        try {
            mqttUtils.subscribe("test1", 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        mqttUtils.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        for (int i = 0; i < 100; i++) {

            MqttMessage msg = new MqttMessage();
            msg.setPayload(("msg " + i).getBytes());
            msg.setQos(0);

            mqttUtils.publish(msg, "test2");

        }
*/

    }


}
