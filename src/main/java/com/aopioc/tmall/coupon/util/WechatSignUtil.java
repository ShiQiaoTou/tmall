package com.aopioc.tmall.coupon.util;

import com.aopioc.tmall.coupon.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @ClassName WechatSignUtil
 * @Description 微信请求校验工具类
 * @Author Q_先生
 * @Date 2018/7/6 15:21
 **/
public class WechatSignUtil {

    /**
     * @Author Q_先生
     * @Description 验证签名
     * @Date 15:31 2018/7/6
     * @Param [signature, timestamp, nonce]
     * @return boolean
     **/
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{Constants.COMPONENT_TOKEN, timestamp, nonce};
        // 将 token、timestamp、nonce 三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String str : arr) {
            content.append(str);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行 sha1 加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将 sha1 加密后的字符串可与 signature 对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * @return java.lang.String
     * @Author Q_先生
     * @Description 将字节数组转换为十六进制字符串
     * @Date 15:26 2018/7/6
     * @Param [byteArray]
     **/
    private static String byteToStr(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for (byte mByte : byteArray) {
            sb.append(byteToHexStr(mByte));
        }
        return sb.toString();
    }

    /**
     * @return java.lang.String
     * @Author Q_先生
     * @Description 将字节转换为十六进制字符串
     * @Date 15:21 2018/7/6
     * @Param [mByte]
     **/
    private static String byteToHexStr(byte mByte) {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];
        return new String(tempArr);
    }
}
