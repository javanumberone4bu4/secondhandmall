package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.common.DefaultResult;
import com.rimi.secondhandtradingmall.common.Result;
import com.rimi.secondhandtradingmall.common.ResultCode;
import com.rimi.secondhandtradingmall.service.IMsgService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Wang Xiaoping
 * @date 2019/12/10 18:48
 */
@RestController
public class LoginController2 {
    private IMsgService msgService;

    public LoginController2(IMsgService msgService) {
        this.msgService = msgService;
    }
    @PostMapping("/okLogin")
    public Result loginSuccess(String telephone, String msgMessage, HttpSession session, Model model){
        Msg msg = msgService.selectByTelephoneAndMsg(telephone, msgMessage);
        if(msg==null){
            return new DefaultResult(ResultCode.FAIL);
        }
        String id = session.getId();
        msg.setSessionId(id);
        int update = msgService.update(msg);
        session.setAttribute("allTelephone",telephone);
        return new DefaultResult(ResultCode.SUCCESS);
    }
}
