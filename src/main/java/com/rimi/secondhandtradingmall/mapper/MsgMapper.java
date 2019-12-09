package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Msg;
import org.apache.ibatis.annotations.Param;

/**
 * ${Description}
 * @author Wang Xiaoping
 * @date 2019/12/9 20:08
 */
public interface MsgMapper {
    int deleteByPrimaryKey(Integer msgId);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Integer msgId);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);

    Msg selectByTelephoneAndSessionId(@Param("telephone") String telephone, @Param("sessionId") String sessionId);
}