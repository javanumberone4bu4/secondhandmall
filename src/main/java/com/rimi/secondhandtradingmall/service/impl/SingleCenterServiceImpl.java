package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.mapper.SinglecenterMapper;
import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author junelee
 * @date 2019/12/10 11:06
 */
@Service
public class SingleCenterServiceImpl implements ISingleCenterService {

    @Autowired
    private SinglecenterMapper singlecenterMapper;

    @Override
    public Singlecenter selectByTelephone(String telephone) {
        Singlecenter singlecenter = singlecenterMapper.selectByTelephone(telephone);
        if (singlecenter != null) {
            return singlecenter;
        }
        return null;
    }

    @Override
    public int insertSinglecenter(Singlecenter singlecenter) {
        int i = singlecenterMapper.insertSelective(singlecenter);
        if (i > 0) {
            return i;
        }
        return -1;
    }

    @Override
    public int updateByPrimaryKeySelective(Singlecenter singlecenter) {

        int i = singlecenterMapper.updateByPrimaryKeySelective(singlecenter);
        if (i > 0) {
            return i;
        }
        return -1;

    }
}
