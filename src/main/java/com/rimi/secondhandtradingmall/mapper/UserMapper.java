package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.User;

/**
 * ${Description}
 * @author Wang Xiaoping
 * @date 2019/12/3 19:26
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}