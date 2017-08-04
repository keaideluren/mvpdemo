package com.fxl.mvpdemo.myutils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/15.
 * 签名生成工具类，微信支付签名，Api服务器签名
 */

public class SignUtils {
    private static final String RANDOM_SOURCE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String SIGN_KEY = "xunlebao.cn";
    public static String TOKEN;


    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int i1 = random.nextInt(62);
            stringBuilder.append(RANDOM_SOURCE.charAt(i1));
        }
        String string = stringBuilder.toString();
        if (length == 6) {
            return string.toLowerCase();
        } else {
            return string;
        }
    }

    public static String getWechatSign(String[] strArr) {
        StringBuilder builder = new StringBuilder();
        String[] strarr = new String[6];
        for (int i = 0; i < 6; i++) {
            StringBuilder builder1 = new StringBuilder();
            String key = null;
            switch (i) {
                case 0:
                    key = "appid";
                    break;
                case 1:
                    key = "partnerid";
                    break;
                case 2:
                    key = "prepayid";
                    break;
                case 3:
                    key = "noncestr";
                    break;
                case 4:
                    key = "timestamp";
                    break;
                case 5:
                    key = "package";
                    break;
            }
            builder1.append(key).append("=").append(strArr[i]);
            String param = builder1.toString();
            strarr[i] = param;
        }
        Arrays.sort(strarr);
        for (int i = 0; i < 6; i++) {
            String s = strarr[i];
            builder.append(s).append("&");
        }

        builder.append("key").append("=").append("AUdfS51hS1TkSqitdjvLLUJ1eY4Pmg3e");
        return MD5Util.string2MD5(builder.toString()).toUpperCase();
    }


    /**
     * 生成接口签名
     *
     * @param timeStep 时间戳
     * @param random   随机数
     * @return 签名
     */
    public static String getSign(String timeStep, String random) {
        String sign = null;
        if (TOKEN == null) {
            return "";
        }
        String[] arrTmp = new String[]{SIGN_KEY, TOKEN, timeStep, random};

        Arrays.sort(arrTmp);
        StringBuilder sb = new StringBuilder();
        for (String s : arrTmp) {
            sb.append(s);
        }
        String tmpStr = sb.toString();
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            String s = ByteArrUtils.bytesToHexString(sha1.digest(tmpStr.getBytes()));
            sign = s == null ? null : s.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sign;
    }

}
