package com.rimi.secondhandtradingmall.controller;


import com.github.pagehelper.PageHelper;
import com.rimi.secondhandtradingmall.bean.Comments;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Sumclassify;
import com.rimi.secondhandtradingmall.service.ICommentsService;
import com.rimi.secondhandtradingmall.service.IGoodsService;
import com.rimi.secondhandtradingmall.service.ISumClassifyService;
import com.rimi.secondhandtradingmall.vo.CommentVo;
import com.rimi.secondhandtradingmall.vo.GoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author Wang Xiaoping
 * @date 2019/12/4 18:12
 */
@Controller
public class GoodsDetailController {

    private IGoodsService goodsService;
    private ISumClassifyService sumClassifyService;
    private ICommentsService commentsService;

    public GoodsDetailController(IGoodsService goodsService, ISumClassifyService sumClassifyService, ICommentsService commentsService) {
        this.goodsService = goodsService;
        this.sumClassifyService = sumClassifyService;
        this.commentsService = commentsService;
    }

    @GetMapping("/detail")
    public String detail(CommentVo vo, Model model, HttpSession session){
        Goods goods = goodsService.selectByPrimaryKey(vo.getGoodsId());
        Sumclassify sumclassify = sumClassifyService.selectByGoodsId(vo.getGoodsId());
        model.addAttribute("sumclassifyDetail",sumclassify);
        List<Comments> comments1 = commentsService.selectByGoodsId(vo.getGoodsId());
        model.addAttribute("count",comments1.size());
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Comments> comments = commentsService.selectByGoodsId(vo.getGoodsId());
        model.addAttribute("comments",comments);
        if(goods.getGoodsDescription().contains(",")){
            String[] split = goods.getGoodsDescription().split(",");
            model.addAttribute("descriptions6",split);
        }else{
            model.addAttribute("descriptions6",goods.getGoodsDescription());
        }
        if(goods.getGoodsColor().contains(",")){
            String[] split = goods.getGoodsColor().split(",");
            model.addAttribute("colors",split);
        }else{
            model.addAttribute("colors",goods.getGoodsColor());
        }
        if(goods.getGoodsSize().contains(",")){
            String[] split = goods.getGoodsSize().split(",");
            model.addAttribute("sizes",split);
        }else{
            model.addAttribute("sizes",goods.getGoodsSize());
        }
        model.addAttribute("detail",goods);
        return "detail";
    }
    @PostMapping("/chooseNum")
    public String chooseNum(GoodsVo vo, Model model){
        Goods goods = goodsService.selectByPrimaryKey(vo.getGoodsId());
        if((vo.getGoodsNum()-goods.getGoodsNum()>0)){
            model.addAttribute("value",0);
        }else {
            model.addAttribute("value",1);
        }
        return "detail";
    }
}
