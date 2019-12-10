package com.rimi.secondhandtradingmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.common.LayuiData;
import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import com.rimi.secondhandtradingmall.vo.SinglecenterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户个人中心
 *
 * @author junelee
 * @date 2019/12/10 11:40
 */
@Controller
public class SinglecenterController {

    @Autowired
    private ISingleCenterService singleCenterService;

    @PostMapping("/insertAddress")
    public String insertAddress(SinglecenterVo vo, HttpServletResponse response) throws IOException {

        // TODO 插入用户提交过来的详情收货地址
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
            // 将对象转成json
            String s = JSONObject.toJSONString(layuiData);
            response.setContentType("application/json;charset=utf8");
            PrintWriter writer = response.getWriter();
            writer.print(s);
            writer.close();

        }



        return "useradd";
    }

}
