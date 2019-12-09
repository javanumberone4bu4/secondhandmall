package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Sumclassify;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:31
 */
public interface ISumClassifyService {
    List<Sumclassify> selectAll();

    List<Sumclassify> selectTop();

    Sumclassify selectByGoodsId(Integer goodsId);

    Sumclassify selectByName(String sumclassifyName);

    int update(Sumclassify sumclassify);
}
