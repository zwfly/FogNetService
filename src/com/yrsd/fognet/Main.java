package com.yrsd.fognet;


import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.WSNettyServer;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;


/**
 * Created by admin on 2017/6/12.
 */
public class Main {

    public static void main(String[] args) {

        Logger log = Logger.getLogger("org.mongodb.driver");
        log.setLevel(Level.OFF);

        try {

            MongoDB_WSLink.start();
            WSNettyServer WSNettyServer = new WSNettyServer();
            WSNettyServer.start();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

}
