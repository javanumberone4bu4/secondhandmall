package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Orders;import org.apache.ibatis.annotations.Param;

/**
 * ${Description}
 *
 * @author junelee
 * @date 2019/12/9 17:03
 */
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer ordersId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer ordersId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    boolean insertAll(@Param("orderForm") String orderForm, @Param("goodsId") String goodsId, @Param("shoppingcarNum"
    ) Integer shoppingcarNum, @Param("total") double total, @Param("telephone") Object telephone);
}