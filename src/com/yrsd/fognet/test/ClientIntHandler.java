package com.yrsd.fognet.test;

import com.yrsd.fognet.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by admin on 2017/6/30.
 */
public class ClientIntHandler extends ChannelInboundHandlerAdapter {
    private static int id = 0;
//    private static Logger logger = LoggerFactory.getLogger(HelloClientIntHandler.class);

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //       logger.info("HelloClientIntHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("Server said:" + new String(result1));
        result.release();
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //       logger.info("HelloClientIntHandler.channelActive");


        new Thread(new Runnable() {
            @Override
            public void run() {
                TestMain testMain = new TestMain();
                byte[] bytes = testMain.giveData((byte) ((id++) & 0xFF));


                for (int i = 0; i < 5000; i++) {
                    ByteBuf encoded = ctx.alloc().buffer(bytes.length);
                    encoded.writeBytes(bytes);
                    ctx.write(encoded);
                    encoded.retain();
                    ctx.flush();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                //encoded.release();
                ctx.disconnect();
                ctx.close();
            }
        }).start();


    }
}
