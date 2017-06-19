package com.yrsd.fognet.device.access.weatherstation;

import com.yrsd.fognet.device.access.weatherstation.codec.Decode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/6/12.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 以("\n")为结尾分割的 解码器
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        pipeline.addLast("decoder", new Decode());
        // 字符串解码 和 编码
//        pipeline.addLast("decoder", new StringDecoder());
//        pipeline.addLast("encoder", new StringEncoder());

//        pipeline.addLast("decoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,
//                4, 0, 4));
//        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
        pipeline.addLast("idle", new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
        // 自己的逻辑Handler
        pipeline.addLast("myhandler", new ServerHandler());
    }


}
