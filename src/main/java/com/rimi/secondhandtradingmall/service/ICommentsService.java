package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Comments;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:29
 */
public interface ICommentsService {
    List<Comments> selectByGoodsId(Integer goodsId);
}
