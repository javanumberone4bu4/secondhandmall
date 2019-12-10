package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.vo.SinglecenterVo;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:31
 */
public interface ISingleCenterService {

    Singlecenter selectByTelephone(String telephone);

    int insertSinglecenter(Singlecenter singlecenter);

    int updateByPrimaryKeySelective(Singlecenter singlecenter);
}
