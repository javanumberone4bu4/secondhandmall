package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Singlecenter;

/**
 * ${Description}
 *
 * @author junelee
 * @date 2019/12/9 17:03
 */
public interface SinglecenterMapper {
    int deleteByPrimaryKey(Integer singlecenterId);

    int insert(Singlecenter record);

    int insertSelective(Singlecenter record);

    Singlecenter selectByPrimaryKey(Integer singlecenterId);

    int updateByPrimaryKeySelective(Singlecenter record);

    int updateByPrimaryKey(Singlecenter record);
}