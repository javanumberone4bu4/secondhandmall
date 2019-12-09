package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Sumclassify;import java.util.List;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 12:45
 */
public interface SumclassifyMapper {
    int deleteByPrimaryKey(Integer sumclassifyId);

    int insert(Sumclassify record);

    int insertSelective(Sumclassify record);

    Sumclassify selectByPrimaryKey(Integer sumclassifyId);

    int updateByPrimaryKeySelective(Sumclassify record);

    int updateByPrimaryKey(Sumclassify record);

    List<Sumclassify> selectAll();

    List<Sumclassify> selectTop();

    Sumclassify selectByGoodsId(Integer goodsId);

    Sumclassify selectByName(String sumclassifyName);
}