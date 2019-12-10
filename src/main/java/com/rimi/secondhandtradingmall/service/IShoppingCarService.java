package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:30
 */
public interface IShoppingCarService {
    /**
     * 根据商品id查询当前用户购物车内的商品
     *
     * @param goodsId   商品id
     * @param telephone 当前用户手机号
     * @return 查找到的一个商品对象
     */
    Goods selectAllGoodsByPhoneAndGoodsId(@Param("goodsId") Integer goodsId, @Param("telephone") Object telephone);


    /**
     * 根据商品id和手机号删除当前用户已购买的购物车内的商品
     *
     * @param id
     * @param telephone
     * @return
     */
    boolean dropShoppingcarGoodsByGoodsIdAndPhone(@Param("id") Integer id, @Param("telephone") Object telephone);

    int selectCountByTelephone(@Param("telephone") String telephone);
}
