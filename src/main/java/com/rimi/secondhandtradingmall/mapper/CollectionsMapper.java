package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Collections;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:46
 */
public interface CollectionsMapper {
    int deleteByPrimaryKey(Integer collectionsId);

    int insert(Collections record);

    int insertSelective(Collections record);

    Collections selectByPrimaryKey(Integer collectionsId);

    int updateByPrimaryKeySelective(Collections record);

    int updateByPrimaryKey(Collections record);
}