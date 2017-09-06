package com.mud.helper;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by leeesven on 17/8/20.
 */
public class EncoderHelper {

    /**
     * 利用MD5进行加密
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException 不支持的算法
     * @throws UnsupportedEncodingException 不支持的字符集
     */
    public static String EcoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    public static String AccessToken() {
        return UUID.randomUUID().toString();
    }
}