package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Orders;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * ${Description}
 *
 * @author junelee
 * @date 2019/12/11 9:01
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

    List<Orders> selectByTelephone(String telephone);
}