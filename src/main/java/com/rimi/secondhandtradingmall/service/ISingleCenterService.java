package com.rimi.secondhandtradingmall.service;

import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.vo.SinglecenterVo;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 19:31
 */
public interface ISingleCenterService {

    Singlecenter selectByTelephone(String telephone,String singlecenterAddress);

    int insertSinglecenter(Singlecenter singlecenter);

    int updateByPrimaryKeySelective(Singlecenter singlecenter);

    List<Singlecenter> selectallbystelephone(String sTelephone);

    Singlecenter selectByPhoneAndSure(String sTelephone,String sureAddress);

    int delete(Integer singlecenterId);

    int update(SinglecenterVo vo);
}
