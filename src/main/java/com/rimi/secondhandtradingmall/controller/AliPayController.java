package com.rimi.secondhandtradingmall.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Shoppingcarmsg;
import com.rimi.secondhandtradingmall.common.AcquireOrderForm;
import com.rimi.secondhandtradingmall.service.IOrdersService;
import com.rimi.secondhandtradingmall.service.IShoppingCarMsgService;
import com.rimi.secondhandtradingmall.service.IShoppingCarService;
import com.rimi.secondhandtradingmall.vo.AllGoodsVo2;
import com.rimi.secondhandtradingmall.vo.GoodsVo2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 沙箱支付
 *
 * @author Wang Xiaoping
 * @date 2019/11/7 19:15
 */
@Controller
public class AliPayController {

    @Value("${alipay.server-url}")
    private String serverUrl;
    @Value("${alipay.app-id}")
    private String appId;
    @Value("${alipay.private-key}")
    private String privateKey;
    @Value("${alipay.alipay-public-key}")
    private String alipayPublicKey;
    @Value("${alipay.format}")
    private String format = "json";
    @Value("${alipay.sign-type}")
    private String signType = "RSA2";
    @Value("${alipay.charset}")
    private String charSet = "UTF-8";


    /**
     * 注入
     */

    private IShoppingCarMsgService shoppingCarMsgService;

    private IShoppingCarService shoppingCarService;

    private IOrdersService ordersService;

    public AliPayController(IShoppingCarMsgService shoppingCarMsgService, IShoppingCarService shoppingCarService,
                            IOrdersService ordersService) {
        this.shoppingCarMsgService = shoppingCarMsgService;
        this.shoppingCarService = shoppingCarService;
        this.ordersService = ordersService;
    }

    /**
     * 获得一个随机数
     *
     * @return 随机数
     */
    public String getRan() {
        double v = Math.random() * 10;
        String s = String.valueOf(v);
        return s + System.currentTimeMillis();
    }

    @GetMapping("/purchase")
    public void payment(GoodsVo2 vo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获得商品总数量
       int shoppingcarNum = vo.getShoppingcarNum();
        // 获得商品单价
        double goodsPrice = vo.getGoodsPrice();
        // 计算出总价
        double total = shoppingcarNum * goodsPrice;


        //生成订单
        String orderForm = new AcquireOrderForm().getOrderForm(getRan());
        String orderForm1 = new AcquireOrderForm().getOrderForm("1219528455234492");
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charSet,
                alipayPublicKey, signType);
        //获得初始化的AlipayClient

        //获取当前请求过来的地址
        String urls = request.getRequestURL().toString();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:80/user?telephone="+vo.getTelephone());//http://10.2.3.48:8080/paySuccess
        alipayRequest.setNotifyUrl("http://localhost:80/user?telephone="+vo.getTelephone());//在公共参数中设置回跳和通知地址


        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + orderForm + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                //  算 金额
                "    \"total_amount\":\"" + total + "\"," +
                //  随便写
                "    \"subject\":\"" + vo.getGoodsName() + "\"," +
                //  随便写
                "    \"body\":\"" + vo.getGoodsName() + "\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"" + orderForm1 + "\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            AlipayTradePagePayResponse responses = alipayClient.pageExecute(alipayRequest);
            if (responses.isSuccess()) {
                System.out.println("调用成功");
                // 生成订单
                // 获取用户手机号
                //Object telephone = request.getAttribute("telephone");
                // 获取商品id
                int goodsId = vo.getGoodsId();
                String goodsId2 = String.valueOf(goodsId);
                // 获取商品总件数
                int shoppingcarNum1 = vo.getShoppingcarNum();

                boolean success = ordersService.insertAll(orderForm, goodsId2, shoppingcarNum, total, vo.getTelephone());
                if (success) {
                    System.out.println("获取订单信息成功");
                }


            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }


        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();


    }


    @GetMapping("/purchaseCar")
    public void paymentCar(AllGoodsVo2 vo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获取当前用户的手机号
        Object telephone = request.getAttribute("telephone");
        // 获取所有商品的总数量
        Integer allCount = 0;
        // 获得将要支付的购物车内单个类型的商品的总数量
        Integer count = 0;
        // 获得将要支付所有商品的总价
        Double total = 0.0;
        // 创建一个商品集合，用来存查到的当前用户的所有将要付款的购物车里的商品
        List<Goods> allGoods = new ArrayList<>();
        // 根据前端传来的商品id 查询出当前用户购物车的所有商品
        List<Integer> goodsId1 = vo.getGoodsId();
        for (Integer goodsId : goodsId1) {
            allGoods.add(shoppingCarService.selectAllGoodsByPhoneAndGoodsId(goodsId, telephone));
        }
        // 创建一个Integer类型的集合，用来存储查到的所有商品id
        List<Integer> ids = new ArrayList();

        // 遍历商品，获取所有商品的所有信息
        for (Goods good : allGoods) {
            // 获得单个商品的单价
            Double goodsPrice = good.getGoodsPrice();
            // 获得单个商品的总量
            Integer goodsNum = good.getGoodsNum();
            // 计算出单个商品的总价
            Double goodsTotal = goodsPrice * goodsNum;
            // 价格叠加
            total += goodsTotal;
            // 支付所有商品总数叠加
            allCount += goodsNum;
            // 支付单个商品总数+1
            count++;
            // 添加当前商品的id到集合，该集合用于删除购物车内的商品
            ids.add(good.getGoodsId());

        }


        //生成订单
        String orderForm = new AcquireOrderForm().getOrderForm(getRan());
        String orderForm1 = new AcquireOrderForm().getOrderForm(getRan());
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charSet,
                alipayPublicKey, signType);
        //获得初始化的AlipayClient

        //获取当前请求过来的地址
        String urls = request.getRequestURL().toString();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:80/user?telephone="+vo.getTelephone());//http://10.2.3.48:8080/paySuccess
        alipayRequest.setNotifyUrl("http://localhost:80/user?telephone="+vo.getTelephone());//在公共参数中设置回跳和通知地址

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + orderForm + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                // TODO 算 金额
                "    \"total_amount\":\"" + total + "\"," +
                //  随便写
                "    \"subject\":\"" + getRan() + "\"," +
                //  随便写
                "    \"body\":\"" + getRan() + "\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"" + orderForm1 + "\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            AlipayTradePagePayResponse responses = alipayClient.pageExecute(alipayRequest);
            if (responses.isSuccess()) {
                System.out.println("调用成功");
                String allIds = null;
                // 删除购物车内对应商品 (若不是通过购物车支付，则不需要完成这步)
                // 根据商品id和用户名手机号码删除对应用户购物车的已购买的商品
                for (Integer id : ids) {
                    allIds = id + ",";
                    boolean success = shoppingCarService.dropShoppingcarGoodsByGoodsIdAndPhone(id, telephone);
                    if (!success) {
                        System.out.println("删除已购买商品失败！");
                    }
                }

                // 生成订单
                boolean success = ordersService.insertAll(orderForm, allIds, allCount, total, telephone);
                if (success) {
                    System.out.println("获取订单信息成功");
                }
                // 算出购买后的购物车内还剩的商品数量 (若不是通过购物车支付，则不需要完成这步)
                //根据当前用户的手机号查询当前用户购物车内的总商品数
                int count1 = shoppingCarService.selectCountByTelephone((String) telephone);
                // 根据手机号查询msg里面的sumnum，有就修改，没有就添加
                Shoppingcarmsg shoppingcarmsg = shoppingCarMsgService.selectCountByTelephone((String) telephone);

                if (shoppingcarmsg != null) {
                    // 修改总商品数量
                    shoppingcarmsg.setShoppingcarmsgSumnum(count1);
                    int i = shoppingCarMsgService.updateCountByTelephone(shoppingcarmsg);

                } else {
                    // 插入一个新的购物车信息
                    Shoppingcarmsg shoppingcarmsg1 = new Shoppingcarmsg();
                    shoppingcarmsg1.setShoppingcarmsgSumnum(count1);
                    shoppingcarmsg1.setTelephone((String)telephone);
                    int res = shoppingCarMsgService.insertCountByTelephone(shoppingcarmsg);

                }

            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();


    }


}
