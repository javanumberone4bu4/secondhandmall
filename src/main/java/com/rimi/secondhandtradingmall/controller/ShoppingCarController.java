package com.rimi.secondhandtradingmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Shoppingcar;
import com.rimi.secondhandtradingmall.bean.Shoppingcarmsg;
import com.rimi.secondhandtradingmall.common.*;
import com.rimi.secondhandtradingmall.service.IGoodsService;
import com.rimi.secondhandtradingmall.service.IShoppingCarMsgService;
import com.rimi.secondhandtradingmall.service.IShoppingCarService;
import com.rimi.secondhandtradingmall.vo.ShoppingCarVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IShoppingCarMsgService shoppingCarMsgService;


    @ApiOperation("跳转到当前用户的购物车页面")
    @GetMapping("/usershop")
    public String toUserShop(Model model, HttpSession session) {

        // 判断当前用户 是否登录
        Object allTelephone = session.getAttribute("allTelephone");
        if (allTelephone == null) {
            return "login";
        }


        // 随机查询五条商品出来
        List<Goods> goods = goodsService.selectShoppingcarInYourLike();
        model.addAttribute("shoppingcarInYourLike", goods);


        model.addAttribute("mySession",session.getId());

        return "usershop";
    }


    @ApiOperation("将商品添加到对应用户的购物车里面")
    @GetMapping("/addShoppingCar")
    @ResponseBody
    public Result addShoppingCar(ShoppingCarVo vo) {


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
        // 完善尺寸
        //System.out.println(vo.getShoppingcarSize()+"========="+vo.getShoppingcarColor());
        shoppingcar.setShoppingcarSize(vo.getShoppingcarSize());
        // 完善颜色
        shoppingcar.setShoppingcarColor(vo.getShoppingcarColor());
        // 插入当前所有信息到购物车表内
        int i = shoppingCarService.insertByTelephone(shoppingcar);
        // 插入成功后更新购物车数字
        if (i > 0) {
            // 添加成功后，查找购物车msg里面的信息
            Shoppingcarmsg shoppingcarmsg = shoppingCarMsgService.selectCountByTelephone(vo.getTelephone());
            if (shoppingcarmsg != null) {
                // 不为空的话就更新
                // 将购物车数字+1
                shoppingcarmsg.setShoppingcarmsgSumnum(shoppingcarmsg.getShoppingcarmsgSumnum() + 1);
                // 更新数据
                shoppingCarMsgService.updateCountByTelephone(shoppingcarmsg);
            } else {
                // 为空则新增数据
                Shoppingcarmsg shoppingcarmsg1 = new Shoppingcarmsg();
                // 设置手机号
                shoppingcarmsg1.setTelephone(vo.getTelephone());
                // 设置初次添加默认值1
                shoppingcarmsg1.setShoppingcarmsgSumnum(1);
                // 插入新数据
                shoppingCarMsgService.insertCountByTelephone(shoppingcarmsg1);
            }

            return new DefaultResult(ResultCode.SUCCESS);
        }
        return new DefaultResult(ResultCode.FAIL);

    }





    @ApiOperation("根据手机号获取当前用户购物车内的所有商品")
    @GetMapping("/getMyGoods")
    @ResponseBody
    public ResultData getMyGoods(ShoppingCarVo vo, HttpSession session, HttpServletResponse response) throws IOException {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        List<Shoppingcar> shoppingcar = shoppingCarService.selectAllGoodsByPhone(vo.getTelephone());
        PageInfo pageInfo = new PageInfo(shoppingcar);
        return new DefaultResultData(pageInfo);

    }


    @ApiOperation("根据购物车id删除当前用户选中的商品")
    @GetMapping("/dropShopping")
    public String dropShopping(ShoppingCarVo vo, Model model) {

        // 删除商品
        int i = shoppingCarService.dropShoppingByShoppingcarId(vo.getShoppingcarId(), vo.getTelephone());
        // 更新
        if (i > 0) {
            // 查找购物车msg里面的信息
            Shoppingcarmsg shoppingcarmsg = shoppingCarMsgService.selectCountByTelephone(vo.getTelephone());
            // 将购物车数字-1
            shoppingcarmsg.setShoppingcarmsgSumnum(shoppingcarmsg.getShoppingcarmsgSumnum() - 1);
            // 更新数据
            shoppingCarMsgService.updateCountByTelephone(shoppingcarmsg);
            model.addAttribute("result", "删除成功！");
        }
        model.addAttribute("result", "遇到了网络波动~");

        return "redirect:/usershop";
    }

    @ApiOperation("根据手机号和多个id删除多条购物车内的数据")
    @PostMapping("/dropManyShopping")
    @ResponseBody
    public Result dropManyShopping(@RequestParam("shoppingcarIds[]") String[] shoppingcarIds, ShoppingCarVo vo) {

        // 删除
        int result = shoppingCarService.dropManyShoppingByShoppingcarId(shoppingcarIds, vo.getTelephone());

        if (result < 0) {
            return new DefaultResult(ResultCode.FAIL);
        }
        return new DefaultResult(ResultCode.SUCCESS);
    }




}
