package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.bean.Singlecenter;
import com.rimi.secondhandtradingmall.common.DefaultResult;
import com.rimi.secondhandtradingmall.common.Result;
import com.rimi.secondhandtradingmall.common.ResultCode;
import com.rimi.secondhandtradingmall.common.ResultData;
import com.rimi.secondhandtradingmall.service.IMsgService;
import com.rimi.secondhandtradingmall.service.ISingleCenterService;
import com.rimi.secondhandtradingmall.vo.AllGoodsVo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class JudgeLoginShoppingcarController {

    private  ISingleCenterService singleCenterService;

    private IMsgService msgService;

    public JudgeLoginShoppingcarController(IMsgService msgService, ISingleCenterService singleCenterService) {
        this.msgService = msgService;
        this.singleCenterService = singleCenterService;
    }

    @GetMapping("/judgeLogin2")
    public Result judgeLogin(AllGoodsVo2 vo, HttpSession session) throws IOException {
        Msg msg = msgService.selectByTelephoneAndSessionId(vo.getTelephone(), vo.getSessionId());
        // 判断当前用户是否登陆
        // 判断当前用户的sessionId是否与数据库里面的sessionId相同，相同则为登陆，否则未登录
        System.out.println(vo.getSessionId());
        System.out.println(vo.getTelephone());
        if (msg == null) {
            return new DefaultResult(ResultCode.FAIL);
        }

        session.setAttribute("allMsg",vo);

        //  查询个人中心表，查询是否存在地址，有则直接跳转支付页面，没有则跳转到iframe页面进行添加地址
        Singlecenter singlecenter = singleCenterService.selectByPhoneAndSure(vo.getTelephone(),"是");
        if (singlecenter != null) {
            if(singlecenter.getSinglecenterAddress()!=null) {
                // 调用支付功能
                return new DefaultResult(ResultCode.SUCCESS);
            }else{
                // 跳转到iframe页面
                return new DefaultResult(ResultCode.OTHER);
            }
        }
        // 跳转到iframe页面
        return new DefaultResult(ResultCode.OTHER);

    }

}
