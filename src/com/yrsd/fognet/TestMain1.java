package com.yrsd.fognet;

import com.yrsd.fognet.utils.MqttUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static com.sun.javafx.font.FontResource.SALT;

/**
 * Created by admin on 2017/6/20.
 */
public class TestMain1 {
    private static final int SPLITLENGTH = 4;
    private static final String SALT = "yunshouhu";

    public static void main(String[] args) {
        String code = getMachineCode();

        System.out.println("code:" + code);

        String authCode = auth(code);
        System.out.println("机器码：" + code);
        System.out.println("注册码：" + authCode);

    }

    private static String getMachineCode() {
        Set<String> result = new HashSet<>();
        String mac = getMac();
        System.out.println("mac:" + getMac());
        result.add(mac);
        Properties props = System.getProperties();
        String javaVersion = props.getProperty("java.version");
        result.add(javaVersion);
        // System.out.println("Java的运行环境版本：    " + javaVersion);
        String javaVMVersion = props.getProperty("java.vm.version");
        result.add(javaVMVersion);
        // System.out.println("Java的虚拟机实现版本：    " +
        // props.getProperty("java.vm.version"));
        String osVersion = props.getProperty("os.version");
        result.add(osVersion);
        // System.out.println("操作系统的版本：    " + props.getProperty("os.version"));

        String code = new Md5PasswordEncoder().encodePassword(
                result.toString(), SALT);
        return code;

    }


    public static String auth(String machineCode) {
        String newCode = "(yunshouhuxxx@gmail.com)["
                + machineCode.toUpperCase() + "](xxx应用级产品开发平台)";
        String code = new Md5PasswordEncoder().encodePassword(newCode, SALT)
                .toUpperCase() + machineCode.length();
        return code;
    }


    private static String getSplitString(String str, String split, int length) {
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i % length == 0 && i > 0) {
                temp.append(split);
            }
            temp.append(str.charAt(i));
        }
        String[] attrs = temp.toString().split(split);
        StringBuilder finalMachineCode = new StringBuilder();
        for (String attr : attrs) {
            if (attr.length() == length) {
                finalMachineCode.append(attr).append(split);
            }
        }
        String result = finalMachineCode.toString().substring(0,
                finalMachineCode.toString().length() - 1);
        return result;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    // ‎00-24-7E-0A-22-93
    private static String getMac() {
        try {
            Enumeration<NetworkInterface> el = NetworkInterface
                    .getNetworkInterfaces();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null)
                    continue;

                String hexstr = bytesToHexString(mac);
                return getSplitString(hexstr, "-", 2).toUpperCase();

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
