package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.mapper.GoodsMapper;
import com.rimi.secondhandtradingmall.service.IGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/4 13:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements IGoodsService {
    private GoodsMapper goodsMapper;
    private List<Goods> cache1;
    private List<Goods> cache2;
    private List<Goods> cache3;
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public List<Goods> selectTop() {
        if(cache1!=null){
            return cache1;
        }
        List<Goods> goods = goodsMapper.selectTop();
        if(goods!=null&&goods.size()>0){
            cache1=goods;
            return cache1;
        }
        return null;
    }

    @Override
    public List<Goods> selectTime() {
        if(cache2!=null){
            return cache2;
        }
        List<Goods> goods = goodsMapper.selectTime();
        if(goods!=null&&goods.size()>0){
            cache2=goods;
            return cache2;
        }
        return null;
    }

    @Override
    public List<Goods> selectLike() {
        if(cache3!=null){
            return cache3;
        }
        List<Goods> goods = goodsMapper.selectLike();
        if(goods!=null&&goods.size()>0){
            cache3=goods;
            return cache3;
        }
        return null;
    }

    @Override
    public List<Goods> selectSecond(Integer sumclassifyId) {
        List<Goods> goods = goodsMapper.selectSecond(sumclassifyId);
        if(goods!=null&&goods.size()>0){
            return goods;
        }
        return null;
    }

    @Override
    public Goods selectByPrimaryKey(Integer goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(goods!=null){
            return goods;
        }
        return null;
    }
}
