package com.swallow.weixin.work.context;

import com.swallow.weixin.work.service.AccessTokenService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class WeixinApplicationContextInitializer implements ApplicationListener<ContextRefreshedEvent> {
    public void onApplicationEvent(ContextRefreshedEvent event) {
        AccessTokenService accessTokenService = event.getApplicationContext().getBean(AccessTokenService.class);
        accessTokenService.refresh();
    }
}