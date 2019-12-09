package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.service.IMsgService;
import com.rimi.secondhandtradingmall.vo.AllGoodsVo2;
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
        // 调用支付功能
        return "purchase";
    }

}
