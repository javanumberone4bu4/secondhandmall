package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Sumclassify;
import com.rimi.secondhandtradingmall.mapper.SumclassifyMapper;
import com.rimi.secondhandtradingmall.service.ISumClassifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 20:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SumClassifyServiceImpl implements ISumClassifyService {
    private SumclassifyMapper sumclassifyMapper;
    private List<Sumclassify> cache1;
    private List<Sumclassify> cache2;

    public SumClassifyServiceImpl(SumclassifyMapper sumclassifyMapper) {
        this.sumclassifyMapper = sumclassifyMapper;
    }

    @Override
    public List<Sumclassify> selectAll() {
        if(cache1!=null){
            return cache1;
        }
        List<Sumclassify> sumclassifies = sumclassifyMapper.selectAll();
        if(sumclassifies!=null&&sumclassifies.size()>0){
            cache1=sumclassifies;
            return cache1;
        }
        return null;
    }

    @Override
    public List<Sumclassify> selectTop() {
        if(cache2!=null){
            return cache2;
        }
        List<Sumclassify> top = sumclassifyMapper.selectTop();
        if(top!=null&&top.size()>0){
            cache2=top;
            return cache2;
        }
        return null;
    }

    @Override
    public Sumclassify selectByGoodsId(Integer goodsId) {
        Sumclassify sumclassify = sumclassifyMapper.selectByGoodsId(goodsId);
        if(sumclassify!=null){
            return sumclassify;
        }
        return null;
    }

    @Override
    public Sumclassify selectByName(String sumclassifyName) {
        Sumclassify sumclassify = sumclassifyMapper.selectByName(sumclassifyName);
        if(sumclassify!=null){
            return sumclassify;
        }
        return null;
    }

    @Override
    public int update(Sumclassify sumclassify) {
        int i = sumclassifyMapper.updateByPrimaryKeySelective(sumclassify);
        if(i>0){
            return i;
        }
        return 0;
    }
}
