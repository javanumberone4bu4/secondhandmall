package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Shoppingcar;
import com.rimi.secondhandtradingmall.common.Result;
import com.rimi.secondhandtradingmall.common.ResultData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    Goods selectAllGoodsByPhoneAndGoodsId(Integer goodsId, String telephone);


    /**
     * 根据商品id和手机号删除当前用户已购买的购物车内的商品
     *
     * @param id
     * @param telephone
     * @return
     */
    boolean dropShoppingcarGoodsByGoodsIdAndPhone(String id, String telephone);

    int selectCountByTelephone(@Param("telephone") String telephone);

    int insertByTelephone(Shoppingcar shoppingcar);

    List<Shoppingcar> selectAllGoodsByPhone(@Param("telephone") String telephone);


    int dropShoppingByShoppingcarId(@Param("ShoppingcarId") Integer ShoppingcarId,
                                    @Param("telephone") String telephone);

    int dropManyShoppingByShoppingcarId(@Param("ShoppingcarIds") String[] ShoppingcarIds,
                                        @Param("telephone") String telephone);

    int selectShoppingcarByShoppingcarIdAndTelephone(@Param("shoppingcarIds") String[] shoppingcarIds, @Param(
            "telephone") String telephone);

    double selectAllShoppingcarPayByShoppingcarIdAndTelephone(@Param("shoppingcarIds") String[] shoppingcarIds, @Param(
            "telephone") String telephone);
}
