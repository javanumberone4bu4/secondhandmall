package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Msg;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:45
 */
public interface MsgMapper {
    int deleteByPrimaryKey(Integer msgId);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Integer msgId);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);
}