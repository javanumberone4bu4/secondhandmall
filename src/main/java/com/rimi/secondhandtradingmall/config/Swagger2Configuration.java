package com.rimi.secondhandtradingmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger的配置文件
 * @author Wang Xiaoping
 * @date 2019/10/31 16:24
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //设置文档的具体的信息
                .select() //会选择那些路径或接口生成文档
                 .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "二手交易商场API文档" //标题
                ,"" //描述信息
                ,"1.0" //版本信息
                ,"" //服务地址
                ,"Wang Xiaoping" //作者名
                ,"" //签名
                ,"" //签名链接
        );
    }
}
