package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Collections;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:27
 */
public interface ICollectionsService {
    int insert(Collections collections);

    Collections selectByTelephoneAndGoodsId(String telephone,Integer goodsId);
}
