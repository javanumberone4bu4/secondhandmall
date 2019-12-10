package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Singlecenter;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/10 9:04
 */
public interface SinglecenterMapper {
    int deleteByPrimaryKey(Integer singlecenterId);

    int insert(Singlecenter record);

    int insertSelective(Singlecenter record);

    Singlecenter selectByPrimaryKey(Integer singlecenterId);

    int updateByPrimaryKeySelective(Singlecenter record);

    int updateByPrimaryKey(Singlecenter record);
}