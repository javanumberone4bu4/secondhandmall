package com.rimi.secondhandtradingmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Shoppingcar;
import com.rimi.secondhandtradingmall.common.DefaultResultData;
import com.rimi.secondhandtradingmall.common.LayuiData;
import com.rimi.secondhandtradingmall.common.ResultCode;
import com.rimi.secondhandtradingmall.common.ResultData;
import com.rimi.secondhandtradingmall.service.IGoodsService;
import com.rimi.secondhandtradingmall.service.IShoppingCarService;
import com.rimi.secondhandtradingmall.vo.ShoppingCarVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车控制层
 *
 * @author junelee
 * @date 2019/12/11 11:18
 */
@Controller
@Api(tags = ("添加到购物车的相关操作"))
public class ShoppingCarController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IShoppingCarService shoppingCarService;




    @ApiOperation("跳转到当前用户的购物车页面")
    @GetMapping("/usershop")
    public String toUserShop(Model model,HttpSession session){

        // 判断当前用户 是否登录
        Object allTelephone = session.getAttribute("allTelephone");
        if (allTelephone == null) {
            return "login";
        }


        // 随机查询五条商品出来
        List<Goods> goods = goodsService.selectShoppingcarInYourLike();
        model.addAttribute("shoppingcarInYourLike",goods);
        return "usershop";
    }




    @ApiOperation("将商品添加到对应用户的购物车里面")
    @GetMapping("/addShoppingCar")
    public ResultData addShoppingCar(ShoppingCarVo vo) {

        // 初始化一个购物车对象
        Shoppingcar shoppingcar = new Shoppingcar();

        // 根据商品 id查询出商品的信息
        Goods goods = goodsService.selectByPrimaryKey(vo.getGoodsId());

        // 获取单个商品的数量
        int goodsNum = vo.getGoodsNum();
        // 获取单个商品的价格
        double goodsPrice = goods.getGoodsPrice();
        // 获取单个商品的小计
        double oneGoodsMoney = goodsNum * goodsPrice;
        // 完善购物车对象信息
        shoppingcar.setGoodsId(vo.getGoodsId());
        // 完善商品数量
        shoppingcar.setShoppingcarNum(vo.getGoodsNum());
        // 完善手机号
        shoppingcar.setTelephone(vo.getTelephone());
        // 完善小计
        shoppingcar.setShoppingcarSubtotal(oneGoodsMoney);
        // 插入当前所有信息到购物车表内
        int i = shoppingCarService.insertByTelephone(shoppingcar);
        if (i > 0) {
            return new DefaultResultData(ResultCode.SUCCESS);
        }
        return new DefaultResultData(ResultCode.FAIL);

    }


    @ApiOperation("根据手机号获取当前用户购物车内的所有商品")
    @GetMapping("/getMyGoods")
    @ResponseBody
    public ResultData getMyGoods(ShoppingCarVo vo, HttpSession session, HttpServletResponse response) throws IOException {
        PageHelper.startPage(vo.getPage(),vo.getLimit());
        List<Shoppingcar> shoppingcar = shoppingCarService.selectAllGoodsByPhone(vo.getTelephone());
        PageInfo pageInfo=new PageInfo(shoppingcar);
     return new DefaultResultData(pageInfo);

    }


}
