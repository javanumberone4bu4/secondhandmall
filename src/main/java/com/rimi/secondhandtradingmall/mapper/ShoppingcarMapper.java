package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Shoppingcar;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:43
 */
public interface ShoppingcarMapper {
    int deleteByPrimaryKey(Integer shoppingcarId);

    int insert(Shoppingcar record);

    int insertSelective(Shoppingcar record);

    Shoppingcar selectByPrimaryKey(Integer shoppingcarId);

    int updateByPrimaryKeySelective(Shoppingcar record);

    int updateByPrimaryKey(Shoppingcar record);
}