package com.swallow.weixin.work;

import com.swallow.weixin.work.util.HttpUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.swallow.weixin.work.controller",
        "com.swallow.weixin.work.service","com.swallow.weixin.work.repository","com.swallow.weixin.work.context"})
@MapperScan(basePackages = "com.swallow.weixin.work.repository")
@EnableScheduling
public class WeixinWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinWorkApplication.class, args);
    }

    //***************schedule***************

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("springboot-task-");
        return scheduler;
    }


    //***************http client***************

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//ms
        factory.setConnectTimeout(10000);//ms
        return factory;
    }

}
