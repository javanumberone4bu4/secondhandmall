package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.mapper.SinglecenterMapper;
import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import com.rimi.secondhandtradingmall.vo.SinglecenterVo;
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
    public Singlecenter selectByTelephone(String telephone,String singlecenterAddress) {
        Singlecenter singlecenter = singlecenterMapper.selectByTelephone(telephone,singlecenterAddress);
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

    @Override
    public List<Singlecenter> selectallbystelephone(String sTelephone) {
        List<Singlecenter> singlecenters = singlecenterMapper.selectAllBySTelephone(sTelephone);
        if(singlecenters!=null&&singlecenters.size()>0){
            return singlecenters;
        }
        return null;
    }

    @Override
    public Singlecenter selectByPhoneAndSure(String sTelephone, String sureAddress) {
        Singlecenter singlecenter = singlecenterMapper.selectByPhoneAndSure(sTelephone, sureAddress);
        if(singlecenter!=null){
            return singlecenter;
        }
        return null;
    }

    @Override
    public int delete(Integer singlecenterId) {
        int i = singlecenterMapper.deleteByPrimaryKey(singlecenterId);
        if(i>0){
            return i;
        }
        return 0;
    }

    @Override
    public int update(SinglecenterVo vo) {
        Singlecenter singlecenter=new Singlecenter();
        singlecenter.setSinglecenterAddress(vo.getSinglecenterAddress());
        singlecenter.setSureaddress(vo.getSureAddress());
        singlecenter.setSinglecenterConsignee(vo.getSinglecenterConsignee());
        singlecenter.setSTelephone(vo.getSTelephone());
        int i = singlecenterMapper.updateByPrimaryKeySelective(singlecenter);
        if(i>0){
            return i;
        }
        return 0;
    }
}
