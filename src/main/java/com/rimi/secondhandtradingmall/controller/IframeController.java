package com.rimi.secondhandtradingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Wang Xiaoping
 * @date 2019/12/10 19:37
 */
@Controller
public class IframeController {
    @GetMapping("/iframe")
    public String jump(){
        return "iframe";
    }
}
