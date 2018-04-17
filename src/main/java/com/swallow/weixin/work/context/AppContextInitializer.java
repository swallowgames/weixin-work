package com.swallow.weixin.work.context;

import com.swallow.weixin.work.service.AccessTokenService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppContextInitializer implements ApplicationListener<ContextRefreshedEvent> {
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 刷新token
        AccessTokenService accessTokenService = event.getApplicationContext().getBean(AccessTokenService.class);
        accessTokenService.refresh();
    }
}