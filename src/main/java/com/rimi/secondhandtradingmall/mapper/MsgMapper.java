package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Msg;

/**
 * ${Description}
 *
 * @author junelee
 * @date 2019/12/9 14:24
 */
public interface MsgMapper {
    int deleteByPrimaryKey(Integer msgId);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Integer msgId);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);
}