package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Shoppingcar;
import com.rimi.secondhandtradingmall.common.*;
import com.rimi.secondhandtradingmall.mapper.ShoppingcarMapper;
import com.rimi.secondhandtradingmall.service.IShoppingCarService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author junelee
 * @date 2019/12/9 19:47
 */
@Service
public class ShoppingCarServiceImpl implements IShoppingCarService {

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
    public boolean dropShoppingcarGoodsByGoodsIdAndPhone(Integer id, String telephone) {
        return shoppingcarMapper.dropShoppingcarGoodsByGoodsIdAndPhone(id, telephone);
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
    public int dropShoppingByShoppingcarId(Integer ShoppingcarId, String telephone) {
        int i = shoppingcarMapper.deleteByPrimaryKey(ShoppingcarId,telephone);

        if (i > 0) {
            return i;
        }
        return -1;
    }


}
