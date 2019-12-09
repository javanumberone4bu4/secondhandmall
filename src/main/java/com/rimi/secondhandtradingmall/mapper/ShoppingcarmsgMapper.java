package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Shoppingcarmsg;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:43
 */
public interface ShoppingcarmsgMapper {
    int deleteByPrimaryKey(Integer shoppingcarmsgId);

    int insert(Shoppingcarmsg record);

    int insertSelective(Shoppingcarmsg record);

    Shoppingcarmsg selectByPrimaryKey(Integer shoppingcarmsgId);

    int updateByPrimaryKeySelective(Shoppingcarmsg record);

    int updateByPrimaryKey(Shoppingcarmsg record);

    Shoppingcarmsg selectShoppingMsg(String telephone);
}