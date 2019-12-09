package com.rimi.secondhandtradingmall.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.rimi.secondhandtradingmall.bean.AcquireOrderForm;
import com.rimi.secondhandtradingmall.bean.Goods;
import com.rimi.secondhandtradingmall.service.IOrdersService;
import com.rimi.secondhandtradingmall.service.IShoppingCarService;
import com.rimi.secondhandtradingmall.vo.AllGoodsVo2;
import com.rimi.secondhandtradingmall.vo.GoodsVo2;
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

    private IShoppingCarService shoppingCarService;

    private IOrdersService ordersService;

    public AliPayController(IShoppingCarService shoppingCarService, IOrdersService ordersService) {
        this.shoppingCarService = shoppingCarService;
        this.ordersService = ordersService;
    }

    /**
     * 获得一个随机数
     *
     * @return
     */
    public String getRan() {
        Double v = Math.random() * 10;
        String s = v.toString();
        return s+System.currentTimeMillis();
    }

    @GetMapping("/purchase")
    public void payment(GoodsVo2 vo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获得商品总数量
        Integer shoppingcarNum = vo.getShoppingcarNum();
        // 获得商品单价
        Double goodsPrice = vo.getGoodsPrice();
        // 计算出总价
        double total = shoppingcarNum * goodsPrice;


        //生成订单
        String orderForm = new AcquireOrderForm().getOrderForm(getRan());
        String orderForm1 = new AcquireOrderForm().getOrderForm("1219528455234492");
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101600699685",
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCj3FNoJq5xK0PBDlrp5PBbG2+qOMnederOFrXTC/qZ2vJl" +
                        "/C0bsCBFrv6G+Sa/5SkqcCWvFxKs5DL0ZJoPOW5nDKpjn" +
                        "+5pVfOx73KNfP68L1eQbJPoDkWbfpJLAtFAGjBbjVWI7Bk88uyhO2elPmO5/1rW" +
                        "//qrAGLVdf6ifKTIOveHLIPAy0Vx3enuzEU468oTqo7W6wdoLU3IxVOf5YzChA56dl" +
                        "/oZifaSOoAAIjEXQJpMgJUA2qccXFjq7ooQlcH2PhHBp009sRlBqndJyirALnsSHcwKy5bV3h" +
                        "/sA8EiKDpdGeVNDBMfsUrfi2GY6G1b22rpbF" +
                        "/5WlDpTNchwknAgMBAAECggEBAIe5m6yNPbinaXujdFKAO0Z7t0Z7u1n0ugTycrDZHz4JSPaIpqRkgpa1mEYcYahZHHv2YIBi0Ck866fbHHC31Wat6wSSGFxPwYcFGxFvE2C0pgcOqIRyLd3aPTq8nq5GAwASVWMQjOcbID18gyEwSFJr3MZxSSQTMco17jCQpbzjJI69d8g5Esw8V//TGT4dmz7e1CWbpBAXnvOTQCBkaAhg2VTzneSJvXpwfA6i+5pC47CPQGwG2DP8SizXwwuVqjBDpJFx80wBBraOsR7KUPb7oA83cZXBXdxI2j+uDuOUdd8MHDXNS2+gdw0A4tPjRhxuWDM1iwDgJYJLvopHw4ECgYEA6fTBhekJOBItTWMTOunxiBKznr7Xsvb7VkscQW4YFY6518gIngzHr4XOlwV1/Ag15u5axxRM2p288MSLnbqj+Mf2UGgJEoaX+HmVuZADEAgSdINnrqTRc3dwPbu38jWrdkqX3zBCb572dh7H8vYxmN5cWMAP8W2gyeUysSoicRcCgYEAs0zNFw5AWYVFQbwhp1MdOYzxVwgPqZNXDrJzH0TQk36GIFW/JBSqLMehvNPMscI3E2PQJyS9zhmVZ58sQoRnFsjYsRcd9wtPfDjocSvn3YyOOK6epK2+e7Bay7se4mEZTBslN+TG42upI1EJYYg5m7vLn3LF9KIbsiB5VVRMknECgYEA2FI0IhJgMNU1yClEnaO0bYIFTVHedZ7CtH6MqL8YS86FbcyKk0Dz3gqWA7PL7PbWiCl6DDtr+HQDQIgrI/NwK9cQnDYltVh36grZ8xMCke80yUC7PJMvC1mzkZEhuzX+zUiZdofT1gugjYVWkgMrxtJLhtBHrY8VLbjcD04LPI8CgYBBpwSNxaUgYmEqobV87D0OPqJ8KLJ7eDzvX74xm+P3reN04ZDcqEhrnymE19Ti3eeGzQyB2L07QzjmuWzealbJC//0UC+jrtuK89eA4P/EqtvEu4PRbuEJr1h/suOrJry5llL5dWayDVDgYqLZuKTHdmsn1kHPwq/7eFKVQQnMsQKBgQDVPfUEEUwk3PBh0wZ8EnhMbSjg3ooVrzbcowvzIJkpZjGDoM0sFj/pf4k6lGrZrTtcm57gUdfP5Ov6J/YJjYUCIcaDiGaRBsZ2HiGM6G8iU7Wg6dP7PMOE13mrHuxchP66FQwShw/EETJ+HJT65J5Hwh/jQ1yX8ra18Eiy0nqlZg==",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp0vMtaYwqkKYvNhCp6Lc1" +
                        "+S40KpeBnNSQBqPGgAZhSLoNKP06S8xxjFOIzujkOvPa8k6V2X2nO0VmX2I29tJ+4lJL8Boqkmw6NYQZq6xC1VoeSA1l" +
                        "/vJGYvDz4IDhPY3mCcJeNjaph+afep9ZGW/P5bXGXK+AgmqYgLJBEc6IZdbc" +
                        "+RBeqUFalDWzUiySITHxH50Df1FBTy6TYx8/ezWJy0hPtTQfB3HOmiDsjjSy590vvfXneY0uHWmSFh2p3FMjsNX" +
                        "/vPAquHFHKgdpLVlJ9cpOlIZpxoNUNv6tDEd4DBgP/wsoQxNdHGfCbUhB/SLaN4slNf4YO3IVfzG97poTQIDAQAB",
                "RSA2");


        //获得初始化的AlipayClient

        //获取当前请求过来的地址
        String urls = request.getRequestURL().toString();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:80/list?sumclassifyName=居家用品&sumclassifyId=1&pageNum=1&pageSize=3");//http://10.2.3.48:8080/paySuccess
        alipayRequest.setNotifyUrl("http://localhost:80/list?sumclassifyName=居家用品&sumclassifyId=1&pageNum=1&pageSize=3");//在公共参数中设置回跳和通知地址


        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + orderForm + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                // TODO 算 金额
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


                // TODO 生成订单
                // 获取用户手机号
                Object telephone = request.getAttribute("telephone");
                // 获取商品id
                Integer goodsId = vo.getGoodsId();
                String goodsId2 = goodsId.toString();
                // 获取商品总件数
                Integer shoppingcarNum1 = vo.getShoppingcarNum();

                boolean success = ordersService.insertAll(orderForm, goodsId2, shoppingcarNum, total, telephone);
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
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101600699685",
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCj3FNoJq5xK0PBDlrp5PBbG2+qOMnederOFrXTC/qZ2vJl" +
                        "/C0bsCBFrv6G+Sa/5SkqcCWvFxKs5DL0ZJoPOW5nDKpjn" +
                        "+5pVfOx73KNfP68L1eQbJPoDkWbfpJLAtFAGjBbjVWI7Bk88uyhO2elPmO5/1rW" +
                        "//qrAGLVdf6ifKTIOveHLIPAy0Vx3enuzEU468oTqo7W6wdoLU3IxVOf5YzChA56dl" +
                        "/oZifaSOoAAIjEXQJpMgJUA2qccXFjq7ooQlcH2PhHBp009sRlBqndJyirALnsSHcwKy5bV3h" +
                        "/sA8EiKDpdGeVNDBMfsUrfi2GY6G1b22rpbF" +
                        "/5WlDpTNchwknAgMBAAECggEBAIe5m6yNPbinaXujdFKAO0Z7t0Z7u1n0ugTycrDZHz4JSPaIpqRkgpa1mEYcYahZHHv2YIBi0Ck866fbHHC31Wat6wSSGFxPwYcFGxFvE2C0pgcOqIRyLd3aPTq8nq5GAwASVWMQjOcbID18gyEwSFJr3MZxSSQTMco17jCQpbzjJI69d8g5Esw8V//TGT4dmz7e1CWbpBAXnvOTQCBkaAhg2VTzneSJvXpwfA6i+5pC47CPQGwG2DP8SizXwwuVqjBDpJFx80wBBraOsR7KUPb7oA83cZXBXdxI2j+uDuOUdd8MHDXNS2+gdw0A4tPjRhxuWDM1iwDgJYJLvopHw4ECgYEA6fTBhekJOBItTWMTOunxiBKznr7Xsvb7VkscQW4YFY6518gIngzHr4XOlwV1/Ag15u5axxRM2p288MSLnbqj+Mf2UGgJEoaX+HmVuZADEAgSdINnrqTRc3dwPbu38jWrdkqX3zBCb572dh7H8vYxmN5cWMAP8W2gyeUysSoicRcCgYEAs0zNFw5AWYVFQbwhp1MdOYzxVwgPqZNXDrJzH0TQk36GIFW/JBSqLMehvNPMscI3E2PQJyS9zhmVZ58sQoRnFsjYsRcd9wtPfDjocSvn3YyOOK6epK2+e7Bay7se4mEZTBslN+TG42upI1EJYYg5m7vLn3LF9KIbsiB5VVRMknECgYEA2FI0IhJgMNU1yClEnaO0bYIFTVHedZ7CtH6MqL8YS86FbcyKk0Dz3gqWA7PL7PbWiCl6DDtr+HQDQIgrI/NwK9cQnDYltVh36grZ8xMCke80yUC7PJMvC1mzkZEhuzX+zUiZdofT1gugjYVWkgMrxtJLhtBHrY8VLbjcD04LPI8CgYBBpwSNxaUgYmEqobV87D0OPqJ8KLJ7eDzvX74xm+P3reN04ZDcqEhrnymE19Ti3eeGzQyB2L07QzjmuWzealbJC//0UC+jrtuK89eA4P/EqtvEu4PRbuEJr1h/suOrJry5llL5dWayDVDgYqLZuKTHdmsn1kHPwq/7eFKVQQnMsQKBgQDVPfUEEUwk3PBh0wZ8EnhMbSjg3ooVrzbcowvzIJkpZjGDoM0sFj/pf4k6lGrZrTtcm57gUdfP5Ov6J/YJjYUCIcaDiGaRBsZ2HiGM6G8iU7Wg6dP7PMOE13mrHuxchP66FQwShw/EETJ+HJT65J5Hwh/jQ1yX8ra18Eiy0nqlZg==",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp0vMtaYwqkKYvNhCp6Lc1" +
                        "+S40KpeBnNSQBqPGgAZhSLoNKP06S8xxjFOIzujkOvPa8k6V2X2nO0VmX2I29tJ+4lJL8Boqkmw6NYQZq6xC1VoeSA1l" +
                        "/vJGYvDz4IDhPY3mCcJeNjaph+afep9ZGW/P5bXGXK+AgmqYgLJBEc6IZdbc" +
                        "+RBeqUFalDWzUiySITHxH50Df1FBTy6TYx8/ezWJy0hPtTQfB3HOmiDsjjSy590vvfXneY0uHWmSFh2p3FMjsNX" +
                        "/vPAquHFHKgdpLVlJ9cpOlIZpxoNUNv6tDEd4DBgP/wsoQxNdHGfCbUhB/SLaN4slNf4YO3IVfzG97poTQIDAQAB",
                "RSA2");


        //获得初始化的AlipayClient

        //获取当前请求过来的地址
        String urls = request.getRequestURL().toString();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://10.2.3.48:8080/paySuccess");//http://10.2.3.48:8080/paySuccess
        alipayRequest.setNotifyUrl("http://10.2.3.48:8080/paySuccess");//在公共参数中设置回跳和通知地址


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
                // TODO 删除购物车内对应商品 (若不是通过购物车支付，则不需要完成这步)
                // 根据商品id和用户名手机号码删除对应用户购物车的已购买的商品
                for (Integer id : ids) {
                    allIds = id + ",";
                    boolean success = shoppingCarService.dropShoppingcarGoodsByGoodsIdAndPhone(id, telephone);
                    if (!success) {
                        System.out.println("删除已购买商品失败！");
                    }
                }

                // TODO 生成订单
                boolean success = ordersService.insertAll(orderForm, allIds, allCount, total, telephone);
                if (success) {
                    System.out.println("获取订单信息成功");
                }
                // TODO 算出购买后的购物车内还剩的商品数量 (若不是通过购物车支付，则不需要完成这步)



            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //{    "out_trade_no":"4f59245f5bdf561f0f52",    "product_code":"FAST_INSTANT_TRADE_PAY",
        // "total_amount":8888,    "subject":"Iphone32 160G",    "body":"Iphone32 160G",
        // "passback_params":"merchantBizType%3d3C%26merchantBizNo%3d2016010101111",    "extend_params":{
        // "sys_service_provider_id":"92e7844abdd75710b6dd",    }  }
        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();


    }


}
