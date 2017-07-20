package com.yrsd.fognet.device.tcp2mq;

import com.google.gson.Gson;
import com.yrsd.fognet.device.access.ListenerDevice;
import com.yrsd.fognet.device.access.weatherstation.ChannelService;
import com.yrsd.fognet.user.entity.UserInfoBean;
import com.yrsd.fognet.utils.Utils;
import io.netty.channel.Channel;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.lang.reflect.InvocationTargetException;

public class MqttTransmit {

    //    private String domain = "localhost";
    private String domain = "192.168.1.10";
    private String broker = "tcp://" + domain + ":1883";
    private String userName = "admin";
    private String password = "admin";

    private MqttClient client;
    private MqttConnectOptions connOpts;
    private int qos = 0;

    public MqttTransmit() {


    }

    public void start() {

        String clientId = "sv_" + Utils.getUUID();
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            client = new MqttClient(broker, clientId, persistence);
            connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            // 设置连接的密码
            connOpts.setPassword(password.toCharArray());
            // 设置超时时间 单位为秒
            connOpts.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            connOpts.setKeepAliveInterval(35);

            client.setCallback(new mMqttCallback(client));

            client.connect(connOpts);

            this.subscribe("s123123");

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }

    }

    private class mMqttCallback implements MqttCallback {
        private MqttClient client = null;

        public mMqttCallback(MqttClient client) {
            this.client = client;
        }

        @Override
        public void connectionLost(Throwable throwable) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (true) {
                        if (client != null) {
                            try {
                                client.connect();
                            } catch (MqttException e) {
                                e.printStackTrace();
                            }
                        } else {
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cnt++;
                        if (cnt > 20) {
                            break;
                        }
                    }
                }
            }).start();

        }

        /**
         * 收到推送信息
         */
        @Override
        public void messageArrived(final String topic, final MqttMessage msg) {
            //topic:  手机ID

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String s = new String(msg.getPayload());
                    System.out.println("topic: " + topic);
                    System.out.println("s: " + s);

                    TransmitBaseBean tBean = null;
                    try {
                        tBean = new Gson().fromJson(s, TransmitBaseBean.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (tBean == null) {
                        return;
                    }
//                    UserInfoBean userInfoBean = new UserInfoBean();
//                    try {
//                        BeanUtils.copyProperties(userInfoBean, tBean.getObj());
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("userInfoBean: " + userInfoBean.getUserName());

                    Channel channel = ChannelService.getChannel(tBean.getToId());
                    if (channel != null) {
                        channel.writeAndFlush(new Gson().toJson(tBean.getObj()));
                    }
                }
            }).start();
        }

        /**
         * 推送回调函数
         */

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//            System.out.println("发送成功: ");
        }
    }

    public void subscribe(String topic) {
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void unsubscribe(String topic) {
        try {
            client.unsubscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String s) {

        try {
            MqttMessage msg = new MqttMessage();
            msg.setPayload(s.getBytes());
            msg.setQos(qos);

            client.publish(topic, msg);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {

        if (client.isConnected()) {
            try {
                client.disconnect();
                client.close();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }

}


