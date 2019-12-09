package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Secondclassify;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:30
 */
public interface ISecondClassifyService {
    List<Secondclassify> selectSecond(Integer sumclassifyId);
}
