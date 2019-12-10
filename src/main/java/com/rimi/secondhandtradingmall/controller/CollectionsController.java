package com.rimi.secondhandtradingmall.controller;

import com.rimi.secondhandtradingmall.bean.Collections;
import com.rimi.secondhandtradingmall.bean.Msg;
import com.rimi.secondhandtradingmall.service.ICollectionsService;
import com.rimi.secondhandtradingmall.service.IMsgService;
import com.rimi.secondhandtradingmall.vo.CollectionsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/collect")
    public String collect(CollectionsVo vo){
        Msg msg = msgService.selectByTelephoneAndSessionId(vo.getTelephone(), vo.getSessionId());
        if(msg==null){
            return "redirect:/login";
        }
        Collections collections1 = collectionsService.selectByTelephoneAndGoodsId(vo.getTelephone(), vo.getGoodsId());
        if(collections1==null) {
            Collections collections = new Collections();
            collections.setGoodsId(vo.getGoodsId());
            collections.setTelephone(vo.getTelephone());
            int insert = collectionsService.insert(collections);
        }
        return "detail";
    }
    @GetMapping("/selectCollections")
    public String selectCollections(String telephone, Model model){
        List<Collections> collections = collectionsService.selectAllByTelephone(telephone);
        model.addAttribute("collections",collections);
        return "usercol";
    }
}
