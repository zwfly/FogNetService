package com.yrsd.mqtt;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/6/20.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 以("\n")为结尾分割的 解码器
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        pipeline.addLast("decoder", new MqttDecoder());
//        pipeline.addLast("encoder", new MqttEncoder());

        pipeline.addLast("idle", new IdleStateHandler(5 * 60, 0, 0, TimeUnit.SECONDS));
        // 自己的逻辑Handler
        pipeline.addLast("myhandler", new ServerHandler());
    }


}
