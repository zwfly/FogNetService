package com.yrsd.fognet;

import com.yrsd.fognet.utils.MqttUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by admin on 2017/6/20.
 */
public class TestMain1 {

    public static void main(String[] args) {

        MqttUtils mqttUtils = new MqttUtils("tcp://192.168.1.10:1883", "1hhr55yhertg23345",
                "admin", "admin", new MemoryPersistence());


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

        try {
            mqttUtils.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 500; i++) {

            MqttMessage msg = new MqttMessage();
            msg.setPayload(("msg " + i).getBytes());
            msg.setQos(0);
            System.out.println(i);
            mqttUtils.publish(msg, "test2");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mqttUtils.publish(msg, "test3");
        }


    }
}
