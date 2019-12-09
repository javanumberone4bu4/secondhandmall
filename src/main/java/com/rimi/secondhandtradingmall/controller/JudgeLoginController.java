package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.service.IMsgService;
import com.rimi.secondhandtradingmall.vo.GoodsVo2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 判断当前用户是否登陆
 *
 * @author junelee
 * @date 2019/12/9 14:40
 */
@Controller
public class JudgeLoginController {
    private IMsgService msgService;

    public JudgeLoginController(IMsgService msgService) {
        this.msgService = msgService;
    }

    @GetMapping("/judgeLogin")
    public String judgeLogin(GoodsVo2 vo,HttpServletRequest request) throws IOException {
        Msg msg = msgService.selectByTelephoneAndSessionId(vo.getTelephone(), vo.getSessionId());
        // 判断当前用户是否登陆
        // 判断当前用户的sessionId是否与数据库里面的sessionId相同，相同则为登陆，否则未登录
        if (msg==null){
            return "redirect:/login";
        }
        request.setAttribute("sessionId",vo.getTelephone());
        // 调用支付功能
        return "purchase";
    }

}
