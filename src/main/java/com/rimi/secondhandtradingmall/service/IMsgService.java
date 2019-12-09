package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Msg;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:29
 */
public interface IMsgService {
    Msg selectByTelephoneAndSessionId(String telephone,String sessionId);
}
