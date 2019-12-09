package com.rimi.secondhandtradingmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rimi.secondhandtradingmall.mapper")
@SpringBootApplication
public class SecondhandtradingmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondhandtradingmallApplication.class, args);
    }

}
