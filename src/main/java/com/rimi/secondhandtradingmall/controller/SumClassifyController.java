package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Sumclassify;
import com.rimi.secondhandtradingmall.service.IGoodsService;
import com.rimi.secondhandtradingmall.service.ISumClassifyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/3 20:44
 */
@Controller
public class SumClassifyController {
    private ISumClassifyService sumClassifyService;
    private IGoodsService goodsService;

    public SumClassifyController(ISumClassifyService sumClassifyService, IGoodsService goodsService) {
        this.sumClassifyService = sumClassifyService;
        this.goodsService = goodsService;
    }

    @GetMapping("/sumClassify/all")
    @ApiOperation(value = "查询所有总分类")
    public String selectAll(Model model, HttpSession session){
        List<Sumclassify> sumclassifies = sumClassifyService.selectAll();
        List<Sumclassify> top = sumClassifyService.selectTop();
        List<Goods> goods = goodsService.selectTop();
        List<Goods> upTimes = goodsService.selectTime();
        List<Goods> likes = goodsService.selectLike();
        model.addAttribute("sumClassifies",sumclassifies);
        session.setAttribute("sumClassifies2",sumclassifies);
        model.addAttribute("top",top);
        model.addAttribute("goods",goods);
        session.setAttribute("goods",goods);
        model.addAttribute("upTime",upTimes);
        model.addAttribute("like",likes);
        return "index";
    }
    @GetMapping("/")
    public String get(){
        return "redirect:/sumClassify/all";
    }


}
