package com.swallow.weixin.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(
        scanBasePackages = {
        "com.swallow.weixin.work.controller",
        "com.swallow.weixin.work.service",
        "com.swallow.weixin.work.repository",
        "com.swallow.weixin.work.context",
                "com.swallow.weixin.work.cache"
        })
@Import(value = {ApplicationConfig.class, RedisConfig.class})
public class WeixinWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinWorkApplication.class, args);
    }

}
