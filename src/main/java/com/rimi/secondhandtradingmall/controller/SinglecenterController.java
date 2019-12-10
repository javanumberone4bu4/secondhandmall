package com.rimi.secondhandtradingmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.rimi.secondhandtradingmall.bean.Orders;
import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.common.LayuiData;
import com.rimi.secondhandtradingmall.service.IOrdersService;
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
import java.util.List;

/**
 * 用户个人中心
 *
 * @author junelee
 * @date 2019/12/10 11:40
 */
@Controller
public class SinglecenterController {

    @GetMapping("/userAdd")
     public String userAdd(){
        return "useradd";
     }
     @GetMapping("/user")
    public String user(){
        return "user";
    }
}
