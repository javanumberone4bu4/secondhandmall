package com.rimi.secondhandtradingmall.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.bean.Orders;
import com.rimi.secondhandtradingmall.bean.Shoppingcarmsg;


import com.rimi.secondhandtradingmall.common.AcquireOrderForm;
import com.rimi.secondhandtradingmall.common.LayuiData;
import com.rimi.secondhandtradingmall.service.IOrdersService;


import com.rimi.secondhandtradingmall.service.IShoppingCarMsgService;
import com.rimi.secondhandtradingmall.service.IShoppingCarService;
import com.rimi.secondhandtradingmall.util.RandomUtils;
import com.rimi.secondhandtradingmall.vo.AllGoodsVo2;
import com.rimi.secondhandtradingmall.vo.GoodsVo2;
import com.rimi.secondhandtradingmall.vo.ShoppingCarVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        int v = (int) (Math.random() * 3);
        String s = String.valueOf(v);
        return s + System.currentTimeMillis();
    }

    @GetMapping("/purchase")
    public void payment(GoodsVo2 vo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(vo);
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
        alipayRequest.setReturnUrl("http://localhost:80/user?telephone="+vo.getTelephone());//http://10.2.3
        // .48:8080/paySuccess
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
                Orders orders = new Orders();
                orders.setGoodsId(goodsId2);
                orders.setOrdersAddress(vo.getOrdersAddress());
                orders.setOrdersMsg(orderForm);
                orders.setOrdersStatus("等待发货");
                orders.setTelephone(vo.getTelephone());
                orders.setOrdersSummoney(total);
                orders.setOrdersSumnum(shoppingcarNum1);
                orders.setColor(vo.getColor());
                orders.setSize(vo.getSize());
                int insert = ordersService.insert(orders);
                System.out.println("订单生成成功");
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


    @ApiOperation("购物车支付入口")
    @PostMapping("/purchaseCar")
    public void paymentCar(@RequestParam("shoppingcarIds[]") String[] shoppingcarIds, ShoppingCarVo vo, HttpServletResponse response) throws IOException {
        //System.out.println(shoppingcarIds+"----"+vo);
        // 获取所有商品的总数量
        int allCount = 0;
        // 获得将要支付的购物车内单个类型的商品的总数量
        int count = 0;
        // 获得将要支付所有商品的总价
        double total = 0.0;

        // 根据id查找对应用户的购物车内是否存在该商品
        int result = shoppingCarService.selectShoppingcarByShoppingcarIdAndTelephone(shoppingcarIds,vo.getTelephone());

        if (result < 0) {
            return;
        }

        // 根据购物车id，用户的手机号定位到用户选中的购物车 算出用户将要支付多少钱
        double resultPay = shoppingCarService.selectAllShoppingcarPayByShoppingcarIdAndTelephone(shoppingcarIds,vo.getTelephone());





        //生成订单
        String orderForm = new AcquireOrderForm().getOrderForm(RandomUtils.getRan());
        String orderForm1 = new AcquireOrderForm().getOrderForm(RandomUtils.getRan());
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charSet,
                alipayPublicKey, signType);
        //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:80/user?telephone=" + vo.getTelephone());//http://10.2.3
        // .48:8080/paySuccess
        alipayRequest.setNotifyUrl("http://localhost:80/user?telephone=" + vo.getTelephone());//在公共参数中设置回跳和通知地址

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + orderForm + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                //  算 金额
                "    \"total_amount\":\"" + total + "\"," +
                //  随便写
                "    \"subject\":\"" + RandomUtils.getRan() + "\"," +
                //  随便写
                "    \"body\":\"" + RandomUtils.getRan() + "\"," +
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
                for (String shoppingcarId : shoppingcarIds) {
                    allIds = shoppingcarId + ",";
                    boolean success = shoppingCarService.dropShoppingcarGoodsByGoodsIdAndPhone(shoppingcarId, vo.getTelephone());
                    if (!success) {
                        System.out.println("删除已购买商品失败！");
                    }
                }

                // 生成订单
                boolean success = ordersService.insertAll(orderForm, allIds, allCount, total, vo.getTelephone());
                if (success) {
                    System.out.println("获取订单信息成功");
                }
                // 算出购买后的购物车内还剩的商品数量 (若不是通过购物车支付，则不需要完成这步)
                //根据当前用户的手机号查询当前用户购物车内的总商品数
                int count1 = shoppingCarService.selectCountByTelephone(vo.getTelephone());
                // 根据手机号查询msg里面的sumnum，有就修改，没有就添加
                Shoppingcarmsg shoppingcarmsg = shoppingCarMsgService.selectCountByTelephone(vo.getTelephone());

                if (shoppingcarmsg != null) {
                    // 修改总商品数量
                    shoppingcarmsg.setShoppingcarmsgSumnum(count1);
                    int i = shoppingCarMsgService.updateCountByTelephone(shoppingcarmsg);

                } else {
                    // 插入一个新的购物车信息
                    Shoppingcarmsg shoppingcarmsg1 = new Shoppingcarmsg();
                    shoppingcarmsg1.setShoppingcarmsgSumnum(count1);
                    shoppingcarmsg1.setTelephone(vo.getTelephone());
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
