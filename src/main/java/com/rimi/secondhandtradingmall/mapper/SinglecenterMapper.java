package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Singlecenter;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${Description}
 *
 * @author junelee
 * @date 2019/12/10 11:38
 */
public interface SinglecenterMapper {
    int deleteByPrimaryKey(Integer singlecenterId);

    int insert(Singlecenter record);

    int insertSelective(Singlecenter record);

    Singlecenter selectByPrimaryKey(Integer singlecenterId);

    int updateByPrimaryKeySelective(Singlecenter record);

    int updateByPrimaryKey(Singlecenter record);

    Singlecenter selectByTelephone(@Param("telephone") String telephone);
}