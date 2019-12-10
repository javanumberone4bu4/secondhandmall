package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Msg;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:29
 */
public interface IMsgService {
    Msg selectByTelephoneAndSessionId(String telephone,String sessionId);

    Msg selectByTelephone(String telephone);

    int insert(Msg msg);

    int update(Msg msg);

    Msg selectByTelephoneAndMsg(String telephone,String msgMessage);
}
