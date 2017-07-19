package com.yrsd.fognet.device.access; /**
 * Created by admin on 2017/6/17.
 */

import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.WSNettyServer;
import com.yrsd.fognet.device.tcp2mq.MqttTransmit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener()
public class ListenerDevice implements ServletContextListener {

    private WSNettyServer wsNettyServer = null;
    private static MqttTransmit mqttTransmit = null;

    // Public constructor is required by servlet spec
    public ListenerDevice() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("ListenerDevice contextInitialized... ");

        Logger log = Logger.getLogger("org.mongodb.driver");
        log.setLevel(Level.OFF);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MongoDB_WSLink.start();
                    System.out.println("mongo link ok");
                    if (wsNettyServer == null) {
                        wsNettyServer = new WSNettyServer();
                        wsNettyServer.start();
//                        System.out.println("netty start ok");
                    }

                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                mqttTransmit = new MqttTransmit();

                mqttTransmit.start();

            }
        }).start();

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ListenerDevice contextDestroyed... ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                wsNettyServer.stop();
                MongoDB_WSLink.stop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mqttTransmit.destroy();
            }
        }).start();
    }

    public static MqttTransmit getMqttTransmit() {
        return mqttTransmit;
    }
}
