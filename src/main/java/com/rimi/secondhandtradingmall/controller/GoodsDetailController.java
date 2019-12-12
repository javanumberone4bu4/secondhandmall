package com.rimi.secondhandtradingmall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rimi.secondhandtradingmall.bean.Comments;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Sumclassify;
import com.rimi.secondhandtradingmall.common.DefaultResultData;
import com.rimi.secondhandtradingmall.common.ResultData;
import com.rimi.secondhandtradingmall.service.ICommentsService;
import com.rimi.secondhandtradingmall.service.IGoodsService;
import com.rimi.secondhandtradingmall.service.ISumClassifyService;
import com.rimi.secondhandtradingmall.vo.CommentVo;
import com.rimi.secondhandtradingmall.vo.GoodsVo;
import com.rimi.secondhandtradingmall.vo.ParamsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        if(comments1!=null&&comments1.size()>0){
            session.setAttribute("d_count",comments1.size());
        }else {
            session.setAttribute("d_count",0);
        }
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Comments> comments = commentsService.selectByGoodsId(vo.getGoodsId());
        model.addAttribute("comments",comments);
        //assert goods != null;
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
        String id = session.getId();
        model.addAttribute("sessionId",id);
        model.addAttribute("detail",goods);
        model.addAttribute("curr",vo.getPageNum());
        return "detail";
    }
    @PostMapping("/selectByParams")
    @ResponseBody
   public ResultData selectByParam(ParamsVo vo){
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<Goods> goods = goodsService.selectByParams(vo.getSecondclassifyName(), vo.getGoodsUptime(), vo.getGoodsPrice(), vo.getSumclassifyId());
        System.out.println(goods);
        PageInfo pageInfo=new PageInfo(goods);
        return new DefaultResultData(pageInfo);
    }
}
