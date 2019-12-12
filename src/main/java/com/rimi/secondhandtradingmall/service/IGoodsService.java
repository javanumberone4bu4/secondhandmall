package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Goods;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:29
 */
public interface IGoodsService {
    List<Goods> selectTop();

    List<Goods> selectTime();

    List<Goods> selectLike();

    List<Goods> selectSecond(Integer sumclassifyId);

    Goods selectByPrimaryKey(Integer goodsId);

    List<Goods> selectShoppingcarInYourLike();

    List<Goods> selectByParams(String secondclassifyName,String goodsUpTime,double goodsPrice,Integer sumclassifyId);

}
