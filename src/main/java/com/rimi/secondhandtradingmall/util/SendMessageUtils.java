package com.rimi.secondhandtradingmall.util;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 发送短信
 *
 * @author Wang Xiaoping
 * @date 2019/11/4 16:22
 */
public final class SendMessageUtils {
    // 产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";
    private static String accessKeyId = "LTAI4FovhEb6FLzhBnVya8sR";
    private static String accessKeySecret = "XteD9K2gczwZbQ0d3vwP2AZhWXO7Ko";
    private static String signName = "享动";
    private static String templeteCode = "SMS_176926742";
    private static String num=String.valueOf(((int)(Math.random()*900000+100000)));

    private SendMessageUtils() {
    }

    public static String sendMessage(String telephone) throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-sichuan", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-sichuan", "cn-sichuan", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templeteCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"尊敬的用户,您的验证码为${code}"时,此处的值为
        String jsonParam = "{\"code\":\""+num+"\"}";
        request.setTemplateParam(jsonParam);

        try {
            // hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.println("没填手机号");
        }
        return num;
        }


}
