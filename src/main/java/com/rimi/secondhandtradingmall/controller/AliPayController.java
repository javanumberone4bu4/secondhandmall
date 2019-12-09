package com.rimi.secondhandtradingmall.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 沙箱支付
 *
 * @author Wang Xiaoping
 * @date 2019/11/7 19:15
 */
@Controller
public class AliPayController {

//    @GetMapping("/purchase")
//    // @GetMapping("/purchase")
//    public void payment( HttpServletRequest request, HttpServletResponse response) throws IOException {

        //生成订单
        //String orderForm =new AcquireOrderForm().getOrderForm("543534546954645");
        //String orderForm1 =new AcquireOrderForm().getOrderForm("423423432543");
       // AlipayClient alipayClient = new DefaultAlipayClient(
       //         "https://openapi.alipaydev.com/gateway.do",
       //         "2016101600699685",
       //         "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCj3FNoJq5xK0PBDlrp5PBbG2+qOMnederOFrXTC/qZ2vJl/C0bsCBFrv6G+Sa/5SkqcCWvFxKs5DL0ZJoPOW5nDKpjn+5pVfOx73KNfP68L1eQbJPoDkWbfpJLAtFAGjBbjVWI7Bk88uyhO2elPmO5/1rW//qrAGLVdf6ifKTIOveHLIPAy0Vx3enuzEU468oTqo7W6wdoLU3IxVOf5YzChA56dl/oZifaSOoAAIjEXQJpMgJUA2qccXFjq7ooQlcH2PhHBp009sRlBqndJyirALnsSHcwKy5bV3h/sA8EiKDpdGeVNDBMfsUrfi2GY6G1b22rpbF/5WlDpTNchwknAgMBAAECggEBAIe5m6yNPbinaXujdFKAO0Z7t0Z7u1n0ugTycrDZHz4JSPaIpqRkgpa1mEYcYahZHHv2YIBi0Ck866fbHHC31Wat6wSSGFxPwYcFGxFvE2C0pgcOqIRyLd3aPTq8nq5GAwASVWMQjOcbID18gyEwSFJr3MZxSSQTMco17jCQpbzjJI69d8g5Esw8V//TGT4dmz7e1CWbpBAXnvOTQCBkaAhg2VTzneSJvXpwfA6i+5pC47CPQGwG2DP8SizXwwuVqjBDpJFx80wBBraOsR7KUPb7oA83cZXBXdxI2j+uDuOUdd8MHDXNS2+gdw0A4tPjRhxuWDM1iwDgJYJLvopHw4ECgYEA6fTBhekJOBItTWMTOunxiBKznr7Xsvb7VkscQW4YFY6518gIngzHr4XOlwV1/Ag15u5axxRM2p288MSLnbqj+Mf2UGgJEoaX+HmVuZADEAgSdINnrqTRc3dwPbu38jWrdkqX3zBCb572dh7H8vYxmN5cWMAP8W2gyeUysSoicRcCgYEAs0zNFw5AWYVFQbwhp1MdOYzxVwgPqZNXDrJzH0TQk36GIFW/JBSqLMehvNPMscI3E2PQJyS9zhmVZ58sQoRnFsjYsRcd9wtPfDjocSvn3YyOOK6epK2+e7Bay7se4mEZTBslN+TG42upI1EJYYg5m7vLn3LF9KIbsiB5VVRMknECgYEA2FI0IhJgMNU1yClEnaO0bYIFTVHedZ7CtH6MqL8YS86FbcyKk0Dz3gqWA7PL7PbWiCl6DDtr+HQDQIgrI/NwK9cQnDYltVh36grZ8xMCke80yUC7PJMvC1mzkZEhuzX+zUiZdofT1gugjYVWkgMrxtJLhtBHrY8VLbjcD04LPI8CgYBBpwSNxaUgYmEqobV87D0OPqJ8KLJ7eDzvX74xm+P3reN04ZDcqEhrnymE19Ti3eeGzQyB2L07QzjmuWzealbJC//0UC+jrtuK89eA4P/EqtvEu4PRbuEJr1h/suOrJry5llL5dWayDVDgYqLZuKTHdmsn1kHPwq/7eFKVQQnMsQKBgQDVPfUEEUwk3PBh0wZ8EnhMbSjg3ooVrzbcowvzIJkpZjGDoM0sFj/pf4k6lGrZrTtcm57gUdfP5Ov6J/YJjYUCIcaDiGaRBsZ2HiGM6G8iU7Wg6dP7PMOE13mrHuxchP66FQwShw/EETJ+HJT65J5Hwh/jQ1yX8ra18Eiy0nqlZg==",
       //         "json",
       //         "UTF-8",
       //         "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp0vMtaYwqkKYvNhCp6Lc1+S40KpeBnNSQBqPGgAZhSLoNKP06S8xxjFOIzujkOvPa8k6V2X2nO0VmX2I29tJ+4lJL8Boqkmw6NYQZq6xC1VoeSA1l/vJGYvDz4IDhPY3mCcJeNjaph+afep9ZGW/P5bXGXK+AgmqYgLJBEc6IZdbc+RBeqUFalDWzUiySITHxH50Df1FBTy6TYx8/ezWJy0hPtTQfB3HOmiDsjjSy590vvfXneY0uHWmSFh2p3FMjsNX/vPAquHFHKgdpLVlJ9cpOlIZpxoNUNv6tDEd4DBgP/wsoQxNdHGfCbUhB/SLaN4slNf4YO3IVfzG97poTQIDAQAB",
       //         "RSA2");


        //获得初始化的AlipayClient

        //获取当前请求过来的地址
        //String urls=request.getRequestURL().toString();
        //AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //alipayRequest.setReturnUrl("http://10.2.3.48:8080/paySuccess");//http://10.2.3.48:8080/paySuccess
        //alipayRequest.setNotifyUrl("http://10.2.3.48:8080/paySuccess");//在公共参数中设置回跳和通知地址



//        alipayRequest.setBizContent("{" +
//                "    \"out_trade_no\":\""+orderForm+"\"," +
//                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
//                "    \"total_amount\":8888," +
//                "    \"subject\":\""+vo.getOrdersNum()+"\"," +
//                "    \"body\":\""+vo.getOrdersNum()+"\"," +
//                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//                "    \"extend_params\":{" +
//                "    \"sys_service_provider_id\":\""+orderForm1+"\"" +
//                "    }"+
//                "  }");//填充业务参数
//        String form="";
//        try {
//            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//            AlipayTradePagePayResponse responses = alipayClient.pageExecute(alipayRequest);
//            if(responses.isSuccess()){
//                System.out.println("调用成功");
//                //List<Orders> orders = ordersService.selectByUserTelAndNum(vo.getUserTel(), vo.getOrdersNum());
//                //if(orders==null||orders.size()==0){
//                //    Orders orders1=new Orders();
//                //    orders1.setUserTel(vo.getUserTel());
//                //    orders1.setOrdersNum(vo.getOrdersNum());
//                //    orders1.setOrdersGetname(vo.getOrdersGetname());
//                //    orders1.setOrdersGetaddress(vo.getOrdersGetaddress());
//                //    orders1.setOrdersGetmethod(vo.getOrdersGetmethod());
//                //    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                //    Date date=new Date();
//                //    String format = simpleDateFormat.format(date);
//                //    orders1.setOrdersTime(format);
//                //    orders1.setOrdersGetscore(Integer.parseInt(vo.getOrdersGetscore()));
//                //    orders1.setOrdersPay("已支付");
//                //    orders1.setOrdersStatus("我借的");
//                //    orders1.setOrdersMoney( BigDecimal.valueOf(Double.valueOf(vo.getGoodsPricesum())));
//                //    int i = ordersService.insertOrders(orders1);
//                //    Sm sm = smMapper.selectByTelephone(vo.getUserTel());
//                //    sm.setSmMark(sm.getSmMark()+Integer.parseInt(vo.getOrdersGetscore()));
//                //    int i2 = smMapper.updateByPrimaryKeySelective(sm);
//                //    for (int j = 0; j < vo.getIds().length; j++) {
//                //        int i1 = shopMapper.deleteByGoods(Integer.parseInt(vo.getIds()[j]), vo.getUserTel());
//                //    }
//                //   // return   ordersService.selectOrdersGoods(vo.getUserTel(),vo.getOrdersNum());
//                //
//                //}
//                //if(orders.size()==1) {
//                //    orders.get(0).setOrdersGetname(vo.getOrdersGetname());
//                //    orders.get(0).setOrdersGetaddress(vo.getOrdersGetaddress());
//                //    orders.get(0).setOrdersGetmethod(vo.getOrdersGetmethod());
//                //    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                //    Date date = new Date();
//                //    String format = simpleDateFormat.format(date);
//                //    orders.get(0).setOrdersTime(format);
//                //    orders.get(0).setOrdersGetscore(Integer.parseInt(vo.getOrdersGetscore()));
//                //    orders.get(0).setOrdersPay("已支付");
//                //    orders.get(0).setOrdersStatus("我借的");
//                //    orders.get(0).setOrdersMoney( BigDecimal.valueOf(Double.valueOf(vo.getGoodsPricesum())));
//                //    int i = mapper.updateByPrimaryKeySelective(orders.get(0));
//                //    Sm sm = smMapper.selectByTelephone(vo.getUserTel());
//                //    sm.setSmMark(sm.getSmMark()+Integer.parseInt(vo.getOrdersGetscore()));
//                //    int i2 = smMapper.updateByPrimaryKeySelective(sm);
//                //    for (int j = 0; j < vo.getIds().length; j++) {
//                //        int i1 = shopMapper.deleteByGoods(Integer.parseInt(vo.getIds()[j]), vo.getUserTel());
//                //    }
//                //    //return ordersService.selectOrdersGoods(vo.getUserTel(),vo.getOrdersNum());
//                //}else{
//                //    for (Orders order : orders) {
//                //        order.setOrdersGetname(vo.getOrdersGetname());
//                //        order.setOrdersGetaddress(vo.getOrdersGetaddress());
//                //        order.setOrdersGetmethod(vo.getOrdersGetmethod());
//                //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                //        Date date = new Date();
//                //        String format = simpleDateFormat.format(date);
//                //        order.setOrdersTime(format);
//                //        order.setOrdersGetscore(Integer.parseInt(vo.getOrdersGetscore()));
//                //        order.setOrdersPay("已支付");
//                //        order.setOrdersStatus("我借的");
//                //        order.setOrdersMoney( BigDecimal.valueOf(Double.valueOf(vo.getGoodsPricesum())));
//                //        int i = mapper.updateByPrimaryKeySelective(order);
//                //        Sm sm = smMapper.selectByTelephone(vo.getUserTel());
//                //        sm.setSmMark(sm.getSmMark()+Integer.parseInt(vo.getOrdersGetscore()));
//                //        int i2 = smMapper.updateByPrimaryKeySelective(sm);
//                //        for (int j = 0; j < vo.getIds().length; j++) {
//                //            int i1 = shopMapper.deleteByGoods(Integer.parseInt(vo.getIds()[j]), vo.getUserTel());
//                //        }
//                // }
//                //}
//
//            } else {
//                System.out.println("调用失败");
//            }
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//
//        //{    "out_trade_no":"4f59245f5bdf561f0f52",    "product_code":"FAST_INSTANT_TRADE_PAY",    "total_amount":8888,    "subject":"Iphone32 160G",    "body":"Iphone32 160G",    "passback_params":"merchantBizType%3d3C%26merchantBizNo%3d2016010101111",    "extend_params":{    "sys_service_provider_id":"92e7844abdd75710b6dd",    }  }
//        response.setContentType("text/html;charset=" +  "UTF-8");
//        response.getWriter().write(form);//直接将完整的表单html输出到页面
//        response.getWriter().flush();
//        response.getWriter().close();
//
//
//    }
//
//
}
