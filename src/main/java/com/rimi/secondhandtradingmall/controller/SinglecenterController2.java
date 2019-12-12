package com.rimi.secondhandtradingmall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rimi.secondhandtradingmall.bean.Orders;
import com.rimi.secondhandtradingmall.bean.Singlecenter;

import com.rimi.secondhandtradingmall.common.*;
import com.rimi.secondhandtradingmall.service.IOrdersService;


import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import com.rimi.secondhandtradingmall.vo.SinglecenterVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 用户个人中心
 *
 * @author junelee
 * @date 2019/12/10 11:40
 */
@RestController
public class SinglecenterController2 {
    private ISingleCenterService singleCenterService;
    private IOrdersService ordersService;
    public SinglecenterController2(ISingleCenterService singleCenterService, IOrdersService ordersService) {
        this.singleCenterService = singleCenterService;
        this.ordersService = ordersService;
    }

    @GetMapping("/insertAddress9")
    public Result insertAddress(SinglecenterVo vo, HttpSession session) throws IOException {
          session.setAttribute("allAddress",vo);
        //  插入用户提交过来的详情收货地址
        Singlecenter singlecenter = singleCenterService.selectByPhoneAndSure(vo.getSTelephone(),vo.getSureAddress());
        if (singlecenter != null) {
            // 添加收货人
            singlecenter.setSinglecenterConsignee(vo.getSinglecenterConsignee());
            //是否默认
            singlecenter.setSinglecenterAddress(vo.getSinglecenterAddress());
            // 更新信息
            singleCenterService.updateByPrimaryKeySelective(singlecenter);
            // 将查询到的对象传到前端页面
            return new DefaultResult(ResultCode.SUCCESS);

        }
            Singlecenter singlecenter1=new Singlecenter();
            singlecenter1.setSTelephone(vo.getSTelephone());
            singlecenter1.setSinglecenterAddress(vo.getSinglecenterAddress());
            singlecenter1.setSureaddress(vo.getSureAddress());
            singlecenter1.setSinglecenterConsignee(vo.getSinglecenterConsignee());
            int i = singleCenterService.insertSinglecenter(singlecenter1);
            return new DefaultResult(ResultCode.SUCCESS);
    }
    @GetMapping("/userData")
    public ResultData user(String telephone,Integer page,Integer limit) throws IOException {
        PageHelper.startPage(page,limit);
        List<Orders> orders = ordersService.selectByTelephone(telephone);
        PageInfo pageInfo=new PageInfo(orders);
        // 将查询到的对象传到前端页面
       return new DefaultResultData(pageInfo);
    }
    @GetMapping("/allAddress")
    public ResultData allAddress(String sTelephone,Integer page,Integer limit){
        PageHelper.startPage(page,limit);
        List<Singlecenter> selectallbystelephone = singleCenterService.selectallbystelephone(sTelephone);
        PageInfo pageInfo=new PageInfo(selectallbystelephone);
        return new DefaultResultData(pageInfo);
    }
    @PostMapping("/deleteSinglecenterId")
    public Result delete(Integer singlecenterId){
        int delete = singleCenterService.delete(singlecenterId);
        return new DefaultResult(ResultCode.SUCCESS);
    }
    @PostMapping("/updateSinglecenter")
    public Result update(SinglecenterVo vo){
        int update = singleCenterService.update(vo);
        return new DefaultResult(ResultCode.SUCCESS);
    }
    @GetMapping("/selectByStatus")
    public ResultData selectByStatus(String ordersStatus,String telephone,Integer page,Integer limit){
        if(telephone==null||telephone.equals("")){
            telephone="-1";
        }
        PageHelper.startPage(page,limit);
        List<Orders> orders = ordersService.selectByStatus(ordersStatus,telephone);
        PageInfo pageInfo=new PageInfo(orders);
        return new DefaultResultData(pageInfo);
    }
}
