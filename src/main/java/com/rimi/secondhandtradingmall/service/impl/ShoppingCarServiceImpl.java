package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Shoppingcar;
import com.rimi.secondhandtradingmall.bean.Shoppingcarmsg;
import com.rimi.secondhandtradingmall.common.*;
import com.rimi.secondhandtradingmall.mapper.ShoppingcarMapper;
import com.rimi.secondhandtradingmall.service.IShoppingCarMsgService;
import com.rimi.secondhandtradingmall.service.IShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author junelee
 * @date 2019/12/9 19:47
 */
@Service
public class ShoppingCarServiceImpl implements IShoppingCarService {

    @Autowired
    IShoppingCarMsgService shoppingCarMsgService;

    private ShoppingcarMapper shoppingcarMapper;

    public ShoppingCarServiceImpl(ShoppingcarMapper shoppingcarMapper) {
        this.shoppingcarMapper = shoppingcarMapper;
    }

    /**
     * 根据商品id查询当前用户购物车内的商品
     *
     * @param goodsId   商品id
     * @param telephone 当前用户手机号
     * @return 查找到的一个商品对象
     */
    @Override
    public Goods selectAllGoodsByPhoneAndGoodsId(Integer goodsId, String telephone) {
        return shoppingcarMapper.selectAllGoodsByPhoneAndGoodsId(goodsId, telephone);
    }

    @Override
    public boolean dropShoppingcarGoodsByGoodsIdAndPhone(String id, String telephone) {

        int result = shoppingcarMapper.dropShoppingcarGoodsByGoodsIdAndPhone(id, telephone);

        if (result < 1) {
            return false;
        }
        return true;
    }

    /**
     * 查询商品总量
     *
     * @param telephone
     * @return
     */
    @Override
    public int selectCountByTelephone(String telephone) {

        return shoppingcarMapper.selectCountByTelephone(telephone);

    }

    @Override
    public int insertByTelephone(Shoppingcar shoppingcar) {

        int i = shoppingcarMapper.insertByTelephone(shoppingcar);
        if (i > 0) {
            return i;
        }
        return -1;
    }

    @Override
    public List<Shoppingcar> selectAllGoodsByPhone(String telephone) {

        List<Shoppingcar> shoppingcars = shoppingcarMapper.selectAllGoodsByPhone(telephone);
        return shoppingcars;


    }

    @Override
    public int dropShoppingByShoppingcarId(Integer ShoppingcarIds, String telephone) {
        int i = shoppingcarMapper.deleteByPrimaryKey(ShoppingcarIds,telephone);


        if (i > 0) {
            return i;
        }
        return -1;
    }

    @Override
    public int dropManyShoppingByShoppingcarId(String[] ShoppingcarIds, String telephone) {
        for (String shoppingcarId : ShoppingcarIds) {
            int i = shoppingcarMapper.deleteManyShoppingByPrimaryKey(shoppingcarId,telephone);
            if (i < 1) {
                return -1;
            }
            // 更新

                // 查找购物车msg里面的信息
                Shoppingcarmsg shoppingcarmsg = shoppingCarMsgService.selectCountByTelephone(telephone);
                // 将购物车数字-1
                shoppingcarmsg.setShoppingcarmsgSumnum(shoppingcarmsg.getShoppingcarmsgSumnum() - 1);
                // 更新数据
                shoppingCarMsgService.updateCountByTelephone(shoppingcarmsg);
        }

        return 1;
    }

    @Override
    public int selectShoppingcarByShoppingcarIdAndTelephone(String[] shoppingcarIds, String telephone) {

        // 分别查询多个id
        for (String shoppingcarId : shoppingcarIds) {
            Shoppingcar shoppingcar = shoppingcarMapper.selectShoppingcarByShoppingcarIdAndTelephone(shoppingcarId,telephone);
            if (shoppingcar == null) {
                return -1;
            }
        }
        return 1;
    }

    @Override
    public double selectAllShoppingcarPayByShoppingcarIdAndTelephone(String[] shoppingcarIds, String telephone) {
        double money = 0.0;
        for (String shoppingcarId : shoppingcarIds) {
            money += shoppingcarMapper.selectOneShoppingcarPayByShoppingcarIdAndTelephone(shoppingcarId,telephone);
        }
        return money;
    }


}
