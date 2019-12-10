package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Orders;
import com.rimi.secondhandtradingmall.mapper.OrdersMapper;
import com.rimi.secondhandtradingmall.service.IOrdersService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author junelee
 * @date 2019/12/9 16:17
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    private OrdersMapper ordersMapper;

    public OrdersServiceImpl(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    @Override
    public boolean insertAll(String orderForm, String goodsId, Integer shoppingcarNum, double total,
                             Object telephone) {

        return ordersMapper.insertAll(orderForm, goodsId, shoppingcarNum, total,telephone);
    }

    @Override
    public List<Orders> selectByTelephone(String telephone) {
        List<Orders> orders = ordersMapper.selectByTelephone(telephone);
        if(orders!=null&&orders.size()>0){
            return orders;
        }
        return null;
    }

    @Override
    public int insert(Orders orders) {
        int insert = ordersMapper.insert(orders);
        if(insert>0){
            return insert;
        }
        return 0;
    }
}
