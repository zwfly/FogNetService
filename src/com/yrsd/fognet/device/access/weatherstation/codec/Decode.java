package com.yrsd.fognet.device.access.weatherstation.codec;

import com.yrsd.fognet.Util;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2017/6/12.
 */
public class Decode extends ByteToMessageDecoder {
    private static ByteBuf buf = Unpooled.buffer();//创建一个ByteBuf缓存区
    private static java.util.concurrent.atomic.AtomicInteger c = new AtomicInteger(1);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        Object o = decode(ctx, in);
        if (o != null) {
            out.add(o);
            System.err.println("getAndIncrement = " + c.getAndIncrement());
        }
    }

    private Object decode(ChannelHandlerContext ctx, ByteBuf in) {
        //标记读指针位置，以便可以回滚指针
        in.markReaderIndex();
        //如果读取的包长度不够head_length，回滚指针不做处理，等待下个包再解析
        if (in.readableBytes() < 22) {
            in.resetReaderIndex();
            return null;
        } else {
            in.readBytes(buf, 22);
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);

            if (((req[0] & 0xFF) == 0x55) && ((req[1] & 0xFF) == 0xAA)
                    && ((req[2] & 0xFF) == 0x5A) && ((req[3] & 0xFF) == 0xA5)) {
                int index = 4;
                int length = req[index++] & 0xFF;
                length |= (req[index++] & 0xFF) << 8;

                int cmd = req[index++];
                cmd |= (req[index++] & 0xFF) << 8;

                StringBuffer deviceId = new StringBuffer("");
                for (int i = 0; i < 12; i++) {
                    deviceId.append(Integer.toHexString(req[index++] & 0xFF));
                }
                String deviceType = String.valueOf(req[index++] & 0xFF);
                in.resetReaderIndex();
                int l = in.readableBytes();
                if (l < (length + 8)) {
                    in.resetReaderIndex();
                    return null;
                }
                buf.clear();
                in.readBytes(buf, length + 8);
                buf.markReaderIndex();
                //

                byte[] bBuf = new byte[buf.readableBytes()];
                buf.readBytes(bBuf);
                int check = ((bBuf[length + 6] & 0xFF) << 8) | bBuf[length + 7] & 0xFF;
                int check1 = Util.calcCrc16(bBuf, 0, length + 6);
                if (check != check1) {
                    System.out.println("check error! " + Integer.toHexString(check1));
                    buf.resetReaderIndex();
                    in.markReaderIndex();
                    buf.clear();
                    return null;
                }
                //
                buf.resetReaderIndex();
                in.markReaderIndex();
                ByteBuf frame = ctx.alloc().buffer(length + 8);
                frame.writeBytes(buf, 0, length + 8);
                buf.clear();
                return frame;

            } else {
                in.resetReaderIndex();
                in.readByte();
                in.markReaderIndex();
                in.resetReaderIndex();
                return null;
            }

        }
    }

    /**
     * 从一个byte[]数组中截取一部分
     *
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] req, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i = begin; i < begin + count; i++) bs[i - begin] = req[i];
        return bs;
    }

    /**
     * 字节转int
     *
     * @return
     */
    public static int byteToint(byte[] res) {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        int targets = (res[0] << 8 & 0xFF00) | (res[1] & 0xFF);
        return targets;
    }

}
