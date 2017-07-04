package com.yrsd.fognet.device.access.weatherstation;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.yrsd.fognet.device.access.weatherstation.entity.WSDeviceBean;
import com.yrsd.fognet.device.access.weatherstation.entity.WSRecordBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.bson.Document;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

/**
 * Created by admin on 2017/6/12.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

//    private String deviceKey = "";

    /*
     *
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     *
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
//        System.out.println("ctx id : " + ctx.channel().id().asShortText());

        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {

                String did = ChannelService.getId(ctx.channel());
                if (did != null) {

                    MongoDB_WSLink.getWsDeviceCollection().updateMany(Filters.eq("deviceId", did),
                            new Document("$set", new Document("lastOffLineDate", new Date())));

/*

                    WSDeviceBean wsDeviceBean = new WSDeviceBean();
                    wsDeviceBean.setDeviceId(did);
                    MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(wsDeviceBean);
                    while (mongoCursor.hasNext()) {
                        if (null != mongoCursor.next().get("DeviceId")) {
                            MongoCollection mongoCollection = MongoDB_WSLink.getWsDeviceCollection();
                            mongoCollection.updateOne(Filters.eq("DeviceId", did),
                                    new Document("$set", new Document("LastOffLineDate", new Date())));
                        }
                    }*/

                }


                /*读超时*/
                System.out.println("READER_IDLE 读超时");
                ChannelService.remove(ctx.channel());
//                System.out.println(ctx.channel() + "will be removed from map");
//                System.out.println("print now map");
//                Map<String, Channel> map = ChannelService.getChannels();
//                for (Map.Entry<String, Channel> entry : map.entrySet()) {
//                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//                }

                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                /*写超时*/
                System.out.println("WRITER_IDLE 写超时");
//                ctx.disconnect();
            } else if (event.state() == IdleState.ALL_IDLE) {
                /*总超时*/
                System.out.println("ALL_IDLE 总超时");
//                ctx.disconnect();
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelInactive  ");
        super.channelInactive(ctx);
        System.out.println("channelInactive  close");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        cause.printStackTrace();
        System.out.println("ctx exceptionCaught  " + ctx.channel().id().asShortText());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
//        System.out.println("ctx channelReadComplete  ");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);

//        System.out.println("ctx channelRead  ");

        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] result = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(result);
        byteBuf.release();
//        byteBuf.retain();

        WSPackage wsPackage = new WSPackage();
        int index = 4;

        int length = result[index++] & 0xFF;
        length |= (result[index++] & 0xFF) << 8;

        int cmd = result[index++] & 0xFF;
        cmd |= (result[index++] & 0xFF) << 8;

        StringBuffer deviceId = new StringBuffer("");
        for (int i = 0; i < 12; i++) {
            String s = Integer.toHexString(result[index++] & 0xFF);
            if (s.length() == 1) {
                s = "0" + s;
            }
            deviceId.append(s);
        }
        String deviceType = String.valueOf(result[index++] & 0xFF);
        byte[] bytes = new byte[length - 15];
        System.arraycopy(result, index, bytes, 0, bytes.length);

        wsPackage.setLength(length);
        wsPackage.setCmd(cmd);
        wsPackage.setDeviceId(String.valueOf(deviceId));
        wsPackage.setDeviceType(deviceType);
        wsPackage.setData(bytes);
        weatherStation_pro(ctx, wsPackage);

//        byteBuf.release();
        //   ctx.writeAndFlush("Received your message !\n");


    }
///////////////////////////////////////////////////////////////////////////


    private void weatherStation_pro(ChannelHandlerContext ctx, WSPackage pack) {

        if (!ctx.channel().equals(ChannelService.getChannel(pack.getDeviceId()))) {
            String did = null;
            did = ChannelService.getId(ctx.channel());
            if (did != null) {
//                WSDeviceBean wsDeviceBean = new WSDeviceBean();
//                wsDeviceBean.setDeviceId(did);

                MongoDB_WSLink.getWsDeviceCollection().updateMany(Filters.eq("deviceId", did),
                        new Document("$set", new Document("lastOffLineDate", new Date())));

//                MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(wsDeviceBean);
//                while (mongoCursor.hasNext()) {
//                    if (null != mongoCursor.next().get("DeviceId")) {
//                        MongoCollection mongoCollection = MongoDB_WSLink.getWsDeviceCollection();
//                        mongoCollection.updateOne(Filters.eq("DeviceId", did),
//                                new Document("$set", new Document("LastOffLineDate", new Date())));
//                    }
//                }
            }

            System.out.println("did = " + did);
            if (null != ChannelService.getChannel(pack.getDeviceId())) {
                ChannelService.getChannel(pack.getDeviceId()).disconnect();
                ChannelService.remove(ChannelService.getChannel(pack.getDeviceId()));
            }
        }
        if (null == ChannelService.getChannel(pack.getDeviceId())) {
            ChannelService.addChannel(pack.getDeviceId(), ctx.channel());

            WSDeviceBean wsDeviceBean = new WSDeviceBean();
            wsDeviceBean.setDeviceId(pack.getDeviceId());

            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(wsDeviceBean);
            if (mongoCursor.hasNext()) {
                MongoDB_WSLink.getWsDeviceCollection().updateMany(Filters.eq("deviceId", pack.getDeviceId()),
                        new Document("$set", new Document("lastOnLineDate", new Date())
                                .append("deviceType", pack.getDeviceType())));
            } else {
                MongoDB_WSLink.getWsDeviceCollection().updateMany(Filters.eq("deviceId", pack.getDeviceId()),
                        new Document("$set", new Document("lastOnLineDate", new Date())
                                .append("deviceType", pack.getDeviceType())
                                .append("deviceCreateDate", new Date()))
                        , new UpdateOptions().upsert(true));
            }

/*
            WSDeviceBean wsDeviceBean = new WSDeviceBean();
            wsDeviceBean.setDeviceId(pack.getDeviceId());

            MongoCursor<Document> mongoCursor = MongoDB_WSLink.find(wsDeviceBean);

            if (mongoCursor.hasNext()) {
                if (null != mongoCursor.next().get("DeviceId")) {
                    MongoCollection mongoCollection = MongoDB_WSLink.getWsDeviceCollection();
                    mongoCollection.updateOne(Filters.eq("DeviceId", pack.getDeviceId()),
                            new Document("$set", new Document("lastOnLineDate", new Date())));
                } else {
                    wsDeviceBean.setDeviceType(pack.getDeviceType());
                    wsDeviceBean.setDeviceCreateDate(new Date());
                    wsDeviceBean.setLastOnLineDate(new Date());
                    MongoDB_WSLink.insert(wsDeviceBean);
                }
                System.out.println("weatherStation_pro  hasNext");
            } else {
                wsDeviceBean.setDeviceType(pack.getDeviceType());
                wsDeviceBean.setDeviceCreateDate(new Date());
                wsDeviceBean.setLastOnLineDate(new Date());
                MongoDB_WSLink.insert(wsDeviceBean);
                System.out.println("weatherStation_pro else hasNext");
            }
            System.out.println("weatherStation_pro  null");
*/
        }

        switch (pack.getCmd()) {
            case 0:
                break;
            case 0x000112:  //只包含传感器信息的心跳命令
                WSRecordBean wsRecordBean = new WSRecordBean();
                wsRecordBean.setDeviceId(pack.getDeviceId());
                wsRecordBean.setDeviceType(pack.getDeviceType());
                wsRecordBean.setRecordCreateDate(new Date());

                int index = 2;
                int windSpeed = pack.getData()[index++] & 0xFF;
                windSpeed |= (pack.getData()[index++] & 0xFF) << 8;
                wsRecordBean.setWindSpeed((double) windSpeed / 100.0);

                int windDirection = pack.getData()[index++] & 0xFF;
                windDirection |= (pack.getData()[index++] & 0xFF) << 8;
                String wd = "";
                if ((windDirection >= 3375) || (windDirection < 225)) {  //北
                    wd = "北";
                } else if ((windDirection >= 225)
                        && (windDirection < 675)) { //东北
                    wd = "东北";
                } else if ((windDirection >= 675)
                        && (windDirection < 1125)) { //东
                    wd = "东";
                } else if ((windDirection >= 1125)
                        && (windDirection < 1575)) { //东南
                    wd = "东南";
                } else if ((windDirection >= 1575)
                        && (windDirection < 2025)) { //南
                    wd = "南";
                } else if ((windDirection >= 2025)
                        && (windDirection < 2475)) { //西南
                    wd = "西南";
                } else if ((windDirection >= 2475)
                        && (windDirection < 2925)) { //西
                    wd = "西";
                } else if ((windDirection >= 2925)
                        && (windDirection < 3375)) { //西北
                    wd = "西北";
                }
                wsRecordBean.setWindDirection(wd);

                short temperature = (short) (pack.getData()[index++] & 0xFF);
                temperature |= ((short) (pack.getData()[index++] & 0xFF)) << 8;

                wsRecordBean.setTemperature((double) temperature / 10.0);

                int humidity = pack.getData()[index++] & 0xFF;
                humidity |= (pack.getData()[index++] & 0xFF) << 8;
                wsRecordBean.setHumidity((double) humidity / 10.0);

                index++;
                index++;  //跳过 airPressure

                int pm2d5 = pack.getData()[index++] & 0xFF;
                pm2d5 |= (pack.getData()[index++] & 0xFF) << 8;
                wsRecordBean.setPM2d5(pm2d5);

                int pm10 = pack.getData()[index++] & 0xFF;
                pm10 |= (pack.getData()[index++] & 0xFF) << 8;
                wsRecordBean.setPM10(pm10);

                for (int i = 0; i < 8; i++) {
                    if (((pack.getData()[1] & 0xFF) & (1 << i)) == 0) {
                        switch (i) {
                            case 7:  //CO2

                                break;
                            case 6:  //pm10
                                wsRecordBean.setPM10(null);
                                break;
                            case 5:  //pm2.5
                                wsRecordBean.setPM2d5(null);
                                break;
                            case 4: //airPressure
                                break;
                            case 3:  //humidity
                                wsRecordBean.setHumidity(null);
                                break;
                            case 2:  //temperature
                                wsRecordBean.setTemperature(null);
                                break;
                            case 1:  //windDirection
                                wsRecordBean.setWindDirection(null);
                                break;
                            case 0:  //windSpeed
                                wsRecordBean.setWindSpeed(null);
                                break;
                            default:
                                break;
                        }
                    }
                }
                MongoDB_WSLink.insert(wsRecordBean);
                break;
            default:
                break;
        }


//        System.out.println("ChannelService size =  " + ChannelService.getChannels().size());
//        System.out.println("package cmd " + Integer.toHexString(pack.getCmd()));

    }

}
