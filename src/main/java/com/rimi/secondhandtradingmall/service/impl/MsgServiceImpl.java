package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.mapper.MsgMapper;
import com.rimi.secondhandtradingmall.service.IMsgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wang Xiaoping
 * @date 2019/12/9 20:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MsgServiceImpl implements IMsgService {
    private MsgMapper msgMapper;

    public MsgServiceImpl(MsgMapper msgMapper) {
        this.msgMapper = msgMapper;
    }

    @Override
    public Msg selectByTelephoneAndSessionId(String telephone, String sessionId) {
        Msg msg = msgMapper.selectByTelephoneAndSessionId(telephone, sessionId);
        if(msg!=null){
            return msg;
        }
        return null;
    }
}
