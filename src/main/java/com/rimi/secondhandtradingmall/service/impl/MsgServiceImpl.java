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

    @Override
    public Msg selectByTelephone(String telephone) {
        Msg msg = msgMapper.selectByTelephone(telephone);
        if(msg!=null){
            return msg;
        }
        return null;
    }

    @Override
    public int insert(Msg msg) {
        int i = msgMapper.insertSelective(msg);
        if(i>0){
            return i;
        }
        return 0;
    }

    @Override
    public int update(Msg msg) {
        int i = msgMapper.updateByPrimaryKeySelective(msg);
        if(i>0){
            return i;
        }
        return 0;
    }

    @Override
    public Msg selectByTelephoneAndMsg(String telephone, String msgMessage) {
        Msg msg = msgMapper.selectByTelephoneAndMsg(telephone, msgMessage);
        if(msg!=null){
            return msg;
        }
        return null;
    }
}
