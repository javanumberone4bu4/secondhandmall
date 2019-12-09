package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Shoppingcarmsg;
import com.rimi.secondhandtradingmall.service.IShoppingCarMsgService;
import com.rimi.secondhandtradingmall.vo.ShoppingCarMsgVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Wang Xiaoping
 * @date 2019/12/4 15:54
 */
@Controller
public class ShoppingCarMsgController {
    private IShoppingCarMsgService shoppingCarMsgService;

    public ShoppingCarMsgController(IShoppingCarMsgService shoppingCarMsgService) {
        this.shoppingCarMsgService = shoppingCarMsgService;
    }
    @PostMapping("/shoppingCarMsg/note")
    public String note(ShoppingCarMsgVo vo, Model model, HttpSession session){
        int i = shoppingCarMsgService.selectSumNum(vo.getTelephone());
        model.addAttribute("shoppingcarmsgSumnum",i);
        session.setAttribute("shoppingcarmsgSumnum",i);
        return "index";
    }
}
