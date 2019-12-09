package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Secondclassify;
import com.rimi.secondhandtradingmall.mapper.SecondclassifyMapper;
import com.rimi.secondhandtradingmall.service.ISecondClassifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/4 19:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SecondClassifyServiceImpl implements ISecondClassifyService {
    private SecondclassifyMapper secondclassifyMapper;
    //private List<Secondclassify> cache1;
    public SecondClassifyServiceImpl(SecondclassifyMapper secondclassifyMapper) {
        this.secondclassifyMapper = secondclassifyMapper;
    }

    @Override
    public List<Secondclassify> selectSecond(Integer sumclassifyId) {
        //if(cache1!=null){
        //    return cache1;
        //}
        List<Secondclassify> secondclassifies = secondclassifyMapper.selectSecond(sumclassifyId);
        if(secondclassifies!=null&&secondclassifies.size()>0){
           // cache1=secondclassifies;
            return secondclassifies;
        }
        return null;
    }
}
