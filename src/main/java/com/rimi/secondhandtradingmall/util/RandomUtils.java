package com.rimi.secondhandtradingmall.util;

/**
 * @author chengliang
 * 随机数工具类
 */
public class RandomUtils {

    /**
     * 获得一个随机数
     *
     * @return 随机数
     */
    public static String getRan() {
        Double v = Math.random() * 10;
        String s = v.toString();
        return s + System.currentTimeMillis();
    }


}
