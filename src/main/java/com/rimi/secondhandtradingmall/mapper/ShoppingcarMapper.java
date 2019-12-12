package com.rimi.secondhandtradingmall.mapper;

import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Shoppingcar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${Description}
 *
 * @author junelee
 * @date 2019/12/12 17:32
 */
public interface ShoppingcarMapper {

    int insert(Shoppingcar record);

    int insertSelective(Shoppingcar record);

    Shoppingcar selectByPrimaryKey(Integer shoppingcarId);

    int updateByPrimaryKeySelective(Shoppingcar record);

    int updateByPrimaryKey(Shoppingcar record);

    Goods selectAllGoodsByPhoneAndGoodsId(@Param("goodsId") Integer goodsId, @Param("telephone") String telephone);

    boolean dropShoppingcarGoodsByGoodsIdAndPhone(@Param("id") Integer id, @Param("telephone") String telephone);

    int selectCountByTelephone(@Param("telephone") String telephone);

    int insertByTelephone(Shoppingcar shoppingcar);

    List<Shoppingcar> selectAllGoodsByPhone(@Param("telephone") String telephone);

    int deleteByPrimaryKey(@Param("ShoppingcarId") Integer ShoppingcarId, @Param("telephone") String telephone);
}