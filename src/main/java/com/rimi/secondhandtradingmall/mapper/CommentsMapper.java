package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Comments;

import java.util.List;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:45
 */
public interface CommentsMapper {
    int deleteByPrimaryKey(Integer commentsId);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer commentsId);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);

    List<Comments> selectByGoodsId(Integer goodsId);
}