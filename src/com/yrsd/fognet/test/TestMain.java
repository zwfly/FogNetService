package com.yrsd.fognet.test;


import com.yrsd.fognet.Util;

/**
 * Created by admin on 2017/6/30.
 */
public class TestMain {


    public byte[] giveData(byte id) {

        byte[] bytes = new byte[41];
        int index = 0;
        bytes[index++] = 0x55;
        bytes[index++] = (byte) 0xAA;
        bytes[index++] = 0x5A;
        bytes[index++] = (byte) 0xA5;

        bytes[index++] = 0x21;  //len
        bytes[index++] = 0x00;

        bytes[index++] = 0x12;  //cmd
        bytes[index++] = 0x01;

        bytes[index++] = 0x34;  //id
        bytes[index++] = (byte) 0xFF;
        bytes[index++] = (byte) 0xD8;
        bytes[index++] = 0x05;
        bytes[index++] = 0x50;
        bytes[index++] = 0x54;
        bytes[index++] = 0x37;
        bytes[index++] = 0x36;
        bytes[index++] = 0x10;
        bytes[index++] = 0x75;
        bytes[index++] = 0x07;
        bytes[index++] = id;

        bytes[index++] = 0x02;  //type

        bytes[index++] = 0x00;
        bytes[index++] = 0x6F;
        bytes[index++] = (byte) 0xD2;
        bytes[index++] = 0x04;
        bytes[index++] = 0x00;
        bytes[index++] = 0x00;
        bytes[index++] = 0x63;
        bytes[index++] = 0x01;
        bytes[index++] = 0x5A;
        bytes[index++] = 0x02;
        bytes[index++] = 0x00;
        bytes[index++] = 0x00;
        bytes[index++] = (byte) 0xEA;
        bytes[index++] = 0x00;
        bytes[index++] = 0x59;
        bytes[index++] = 0x01;
        bytes[index++] = 0x00;
        bytes[index++] = 0x00;
        int check = Util.calcCrc16(bytes, 0, index);
        //CRC
        bytes[index++] = (byte) ((check >> 8) & 0xFF);
        bytes[index++] = (byte) (check & 0xFF);

        return bytes;
    }

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.giveData((byte) 1);
    }
}
