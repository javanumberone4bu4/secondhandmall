package com.rimi.secondhandtradingmall.controller;

import com.github.pagehelper.PageHelper;
import com.rimi.secondhandtradingmall.bean.Collections;
import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.common.DefaultResult;
import com.rimi.secondhandtradingmall.common.Result;
import com.rimi.secondhandtradingmall.common.ResultCode;
import com.rimi.secondhandtradingmall.service.ICollectionsService;
import com.rimi.secondhandtradingmall.service.IMsgService;
import com.rimi.secondhandtradingmall.vo.CollectionsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/9 19:41
 */
@Controller
public class CollectionsController {
    private ICollectionsService collectionsService;
    private IMsgService msgService;

    public CollectionsController(ICollectionsService collectionsService, IMsgService msgService) {
        this.collectionsService = collectionsService;
        this.msgService = msgService;
    }

    @ResponseBody
    @PostMapping("/collect")
    public Result collect(CollectionsVo vo){
        Msg msg = msgService.selectByTelephoneAndSessionId(vo.getTelephone(), vo.getSessionId());
        if(msg==null){
            return new DefaultResult(ResultCode.FAIL);
        }
        Collections collections1 = collectionsService.selectByTelephoneAndGoodsId(vo.getTelephone(), vo.getGoodsId());
        if(collections1==null) {
            Collections collections = new Collections();
            collections.setGoodsId(vo.getGoodsId());
            collections.setTelephone(vo.getTelephone());
            int insert = collectionsService.insert(collections);
        }
        return new DefaultResult(ResultCode.SUCCESS);
    }
    @GetMapping("/selectCollections")
    public String selectCollections(String telephone, Integer pageNum, Integer pageSize, Model model, HttpSession session){
        if(telephone==null||telephone.equals("")){
            telephone="-1";
        }
        List<Collections> collections1 = collectionsService.selectAllByTelephone(telephone);
        if(collections1!=null&&collections1.size()>0) {
            session.setAttribute("l_count", collections1.size());
        }else{
            session.setAttribute("l_count", 0);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Collections> collections = collectionsService.selectAllByTelephone(telephone);
        model.addAttribute("collections3",collections);
        return "usercol";
    }
    @ResponseBody
    @PostMapping("/deleteCollections")
    public Result delete(Integer collectionsId){
        int delete = collectionsService.delete(collectionsId);
        return new DefaultResult(ResultCode.SUCCESS);
    }
}
