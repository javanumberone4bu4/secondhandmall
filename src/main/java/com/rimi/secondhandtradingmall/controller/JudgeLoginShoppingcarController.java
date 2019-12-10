package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.service.IMsgService;
import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import com.rimi.secondhandtradingmall.vo.AllGoodsVo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断当前用户是否登陆
 *
 * @author junelee
 * @date 2019/12/9 14:40
 */
@Controller
public class JudgeLoginShoppingcarController {

    @Autowired
    private ISingleCenterService singleCenterService;

    private IMsgService msgService;

    public JudgeLoginShoppingcarController(IMsgService msgService) {
        this.msgService = msgService;
    }

    @GetMapping("/judgeLogin2")
    public String judgeLogin(AllGoodsVo2 vo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Msg msg = msgService.selectByTelephoneAndSessionId(vo.getTelephone(), vo.getSessionId());
        // 判断当前用户是否登陆
        if (msg == null) {
            return "redirect:/login";
        }

        request.setAttribute("sessionId", vo.getTelephone());

        // TODO 查询个人中心表，查询是否存在地址，有则直接跳转支付页面，没有则跳转到iframe页面进行添加地址
        Singlecenter singlecenter = singleCenterService.selectByTelephone(vo.getTelephone());
        if (singlecenter != null) {
            // 调用支付功能
            return "purchase";
        }

        // 跳转到iframe页面
        return "iframe";

    }

}
