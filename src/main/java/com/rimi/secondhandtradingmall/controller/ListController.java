package com.rimi.secondhandtradingmall.controller;


import com.github.pagehelper.PageHelper;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Secondclassify;
import com.rimi.secondhandtradingmall.bean.Sumclassify;
import com.rimi.secondhandtradingmall.common.DefaultResultData;
import com.rimi.secondhandtradingmall.common.ResultData;
import com.rimi.secondhandtradingmall.service.IGoodsService;
import com.rimi.secondhandtradingmall.service.ISecondClassifyService;
import com.rimi.secondhandtradingmall.service.ISumClassifyService;
import com.rimi.secondhandtradingmall.vo.SumclassifyVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/4 17:39
 */
@Controller
public class ListController {
    private ISecondClassifyService secondClassifyService;
    private IGoodsService goodsService;
    private ISumClassifyService sumClassifyService;

    public ListController(ISecondClassifyService secondClassifyService, IGoodsService goodsService, ISumClassifyService sumClassifyService) {
        this.secondClassifyService = secondClassifyService;
        this.goodsService = goodsService;
        this.sumClassifyService = sumClassifyService;
    }

    @GetMapping("/list")
    public String list(SumclassifyVo vo, Model model, HttpSession session){
        model.addAttribute("sumclassifyName",vo);
        session.setAttribute("sumclassifyName",vo);
        List<Secondclassify> secondclassifies = secondClassifyService.selectSecond(vo.getSumclassifyId());
        model.addAttribute("secondclassifies",secondclassifies);
        session.setAttribute("secondclassifies",secondclassifies);
        Sumclassify sumclassify = sumClassifyService.selectByName(vo.getSumclassifyName());
        sumclassify.setSumclassifyClicknum(sumclassify.getSumclassifyClicknum()+1);
        int update = sumClassifyService.update(sumclassify);
        List<Goods> goods1 = goodsService.selectSecond(vo.getSumclassifyId());
        session.setAttribute("count",goods1.size());
        model.addAttribute("curr",vo.getPageNum());
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Goods> cache = goodsService.selectSecond(vo.getSumclassifyId());
        model.addAttribute("cache", cache);
        return "list";
    }
}
