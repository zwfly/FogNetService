package com.yrsd.fognet.device.access.weatherstation;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by admin on 2017/6/13.
 */
public class ChannelService {


    private static Map<String, Channel> map = new ConcurrentHashMap<>();

    public static void addChannel(String id, Channel channel) {
        map.put(id, channel);
    }

    public static Map<String, Channel> getChannels() {
        return map;
    }

    public static Channel getChannel(String id) {
        return map.get(id);
    }

    public static void remove(String id) {
        map.remove(id);
    }

    public static void remove(Channel channel) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == channel) {
                map.remove(entry.getKey());
            }
        }
    }

    public static String getId(Channel channel) {
        String key = null;
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == channel) {
                key = (String) entry.getKey();
            }
        }
        return key;
    }
}
