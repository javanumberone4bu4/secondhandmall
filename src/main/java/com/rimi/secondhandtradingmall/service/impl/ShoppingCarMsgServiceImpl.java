package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Shoppingcarmsg;
import com.rimi.secondhandtradingmall.mapper.ShoppingcarmsgMapper;
import com.rimi.secondhandtradingmall.service.IShoppingCarMsgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wang Xiaoping
 * @date 2019/12/4 15:50
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCarMsgServiceImpl implements IShoppingCarMsgService {
    private ShoppingcarmsgMapper shoppingcarmsgMapper;

    public ShoppingCarMsgServiceImpl(ShoppingcarmsgMapper shoppingcarmsgMapper) {
        this.shoppingcarmsgMapper = shoppingcarmsgMapper;
    }

    @Override
    public int selectSumNum(String telephone) {
        Shoppingcarmsg shoppingcarmsg = shoppingcarmsgMapper.selectShoppingMsg(telephone);
        if(shoppingcarmsg!=null){
            return shoppingcarmsg.getShoppingcarmsgSumnum();
        }
      return 0;
    }
}
