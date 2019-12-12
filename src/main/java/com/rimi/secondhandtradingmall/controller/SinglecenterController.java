package com.rimi.secondhandtradingmall.controller;


import com.alibaba.fastjson.JSONObject;
import com.rimi.secondhandtradingmall.bean.Orders;
import com.rimi.secondhandtradingmall.common.LayuiData;
import com.rimi.secondhandtradingmall.service.IOrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
@Controller
public class SinglecenterController {
    private IOrdersService ordersService;

    public SinglecenterController(IOrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/userAdd")
     public String userAdd(){
        return "useradd";
     }
     @GetMapping("/user")
    public String user(String telephone, HttpServletResponse response) throws IOException {
         return "user";
    }
}
