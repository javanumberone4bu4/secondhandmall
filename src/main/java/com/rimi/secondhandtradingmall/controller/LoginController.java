package com.rimi.secondhandtradingmall.controller;

import com.aliyuncs.exceptions.ClientException;
import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.service.IMsgService;
import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import com.rimi.secondhandtradingmall.util.SendMessageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Wang Xiaoping
 * @date 2019/12/10 15:02
 */
@Controller
public class LoginController {
    private IMsgService msgService;


    public LoginController(IMsgService msgService) {
        this.msgService = msgService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/sendMessage")
    public String sendMessage(String telephone){
        try {
            String s = SendMessageUtils.sendMessage(telephone);
            Msg msg = msgService.selectByTelephone(telephone);
            if(msg!=null){
                msg.setMsgMessage(s);
                int update = msgService.update(msg);
            }else{
                Msg msg1=new Msg();
                msg1.setTelephone(telephone);
                msg1.setMsgMessage(s);
                int insert = msgService.insert(msg1);
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
       return "login";
    }
}
