package com.rimi.secondhandtradingmall.controller;

import com.alibaba.fastjson.JSONObject;
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
    public void getMyGoods(ShoppingCarVo vo, HttpSession session, HttpServletResponse response) throws IOException {


        Object allTelephone = session.getAttribute("allTelephone");


        double sumsubtotal = 0.0;

        LayuiData layuiData = new LayuiData();

        List<ShoppingCarVo> shoppingcarVo = new ArrayList<>();

        // 根据手机号获取到所有购物车表里面对应用户的商品

        List<Shoppingcar> shoppingcar = shoppingCarService.selectAllGoodsByPhone((String)allTelephone);
        for (Shoppingcar shoppingcar1 : shoppingcar) {
            // 根据购物车商品id查询出商品的名称
            Goods goods = goodsService.selectByPrimaryKey(shoppingcar1.getGoodsId());
            // 完善商品名称
            vo.setGoodsName(goods.getGoodsName());
            // 完善商品单价
            vo.setGoodsPrice(goods.getGoodsPrice());
            // 完善商品数量
            vo.setGoodsCount(shoppingcar1.getShoppingcarNum());
            // 完善商品图片
            vo.setGoodsLogo(goods.getGoodsLogo());
            // 完善商品小计
            vo.setGoodsSubtotal(shoppingcar1.getShoppingcarSubtotal());
            // 完善单个购物车商品的信息
            shoppingcarVo.add(vo);
            // 叠加单个商品的总价
            sumsubtotal += shoppingcar1.getShoppingcarSubtotal();

        }


        if (shoppingcar.size() > 0) {
            layuiData.setCode(0);
            layuiData.setMsg("获取到了数据");
            layuiData.setData(shoppingcarVo);

            // 将封装后的数据转成json格式的数据
            String jsonString = JSONObject.toJSONString(layuiData);
            // 设置响应的格式
            response.setContentType("application/json;charset=utf-8");
            // 将数据响应到浏览器
            PrintWriter writer = response.getWriter();
            writer.print(jsonString);
            writer.close();

        }

        layuiData.setMsg("没有数据");

    }


}
