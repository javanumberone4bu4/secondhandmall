package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.mapper.OrdersMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:29
 */
public interface IOrdersService {



    boolean insertAll(@Param("orderForm") String orderForm, @Param("goodsId") String goodsId, @Param("shoppingcarNum"
    ) Integer shoppingcarNum, @Param("total") double total, @Param("telephone") Object telephone);
}
