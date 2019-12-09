package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Collections;
import com.rimi.secondhandtradingmall.mapper.CollectionsMapper;
import com.rimi.secondhandtradingmall.service.ICollectionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wang Xiaoping
 * @date 2019/12/9 19:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CollectionsServiceImpl implements ICollectionsService {
    private CollectionsMapper collectionsMapper;

    public CollectionsServiceImpl(CollectionsMapper collectionsMapper) {
        this.collectionsMapper = collectionsMapper;
    }

    @Override
    public int insert(Collections collections) {
        int i = collectionsMapper.insertSelective(collections);
        if(i>0){
            return i;
        }
        return 0;
    }

    @Override
    public Collections selectByTelephoneAndGoodsId(String telephone, Integer goodsId) {
        Collections collections = collectionsMapper.selectByTelephoneAndGoodsId(telephone, goodsId);
        if(collections!=null){
            return collections;
        }
        return null;
    }
}
