package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Secondclassify;

import java.util.List;

/**
 * ${Description}
 * @author Wang Xiaoping
 * @date 2019/12/3 19:25
 */
public interface SecondclassifyMapper {
    int deleteByPrimaryKey(Integer secondclassifyId);

    int insert(Secondclassify record);

    int insertSelective(Secondclassify record);

    Secondclassify selectByPrimaryKey(Integer secondclassifyId);

    int updateByPrimaryKeySelective(Secondclassify record);

    int updateByPrimaryKey(Secondclassify record);

    List<Secondclassify> selectSecond(Integer sumclassifyId);
}