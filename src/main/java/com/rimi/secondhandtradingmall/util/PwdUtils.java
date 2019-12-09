package com.rimi.secondhandtradingmall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 密码工具类
 *
 * @author Wang Xiaoping
 * @date 2019/9/23 16:34
 */
public class
PwdUtils {
    private static final String SALT = "%%dqdyqw46548<>euru)^$#$";

    public static String getPwd(String source) {
        //使用MD5 hash算法
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] digest1 = digest.digest((source + SALT).getBytes());
            //把加密后的字节转换成字符串
            String target = Base64.getEncoder().encodeToString(digest1);
            return target;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }
}

