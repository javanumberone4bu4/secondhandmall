package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Orders;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:44
 */
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer ordersId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer ordersId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}