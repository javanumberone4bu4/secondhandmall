package com.rimi.secondhandtradingmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.rimi.secondhandtradingmall.bean.Orders;
import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.common.DefaultResultData;
import com.rimi.secondhandtradingmall.common.LayuiData;
import com.rimi.secondhandtradingmall.common.ResultData;
import com.rimi.secondhandtradingmall.service.IOrdersService;
import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import com.rimi.secondhandtradingmall.vo.SinglecenterVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @PostMapping("/insertAddress")
    public ResultData insertAddress(SinglecenterVo vo) throws IOException {

        //  插入用户提交过来的详情收货地址
        Singlecenter singlecenter = singleCenterService.selectByTelephone(vo.getSTelephone());
        if (singlecenter != null) {
            // 添加地址
            singlecenter.setSinglecenterAddress(vo.getSinglecenterAddress());
            // 添加收货人
            singlecenter.setSinglecenterConsignee(vo.getSinglecenterConsignee());
            // 更新信息
            singleCenterService.updateByPrimaryKeySelective(singlecenter);
            // 将查询到的对象传到前端页面
            LayuiData layuiData = new LayuiData();
            layuiData.setCode(0);
            layuiData.setMsg("");
            layuiData.setData(singlecenter);
            return new DefaultResultData(layuiData);

        }
        return null;
    }
    @GetMapping("/userData")
    public ResultData user(String telephone,Model model,HttpServletResponse response) throws IOException {
        List<Orders> orders = ordersService.selectByTelephone(telephone);
        // 将查询到的对象传到前端页面
        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setData(orders);
       return new DefaultResultData(layuiData);
    }
}
