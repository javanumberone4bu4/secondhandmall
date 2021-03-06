package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 13:40
 */
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectTop();

    List<Goods> selectTime();

    List<Goods> selectLike();

    List<Goods> selectSecond(Integer sumclassifyId);

    List<Goods> selectShoppingcarInYourLike();

    List<Goods> selectByParams(@Param("secondclassifyName")String secondclassifyName,@Param("goodsPrice")double goodsPrice,@Param("goodsUptime")String goodsUptime,@Param("sumclassifyId") Integer sumclassifyId);
}