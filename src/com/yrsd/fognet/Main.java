package com.yrsd.fognet;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import com.mongodb.MongoClient;
import com.yrsd.fognet.device.access.weatherstation.MongoDB_WSLink;
import com.yrsd.fognet.device.access.weatherstation.Server;
import org.bson.Document;

import java.util.Arrays;
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
            Server server = new Server();
            server.start();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

}
