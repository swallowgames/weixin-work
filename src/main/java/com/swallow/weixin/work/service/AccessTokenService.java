package com.swallow.weixin.work.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.swallow.weixin.work.context.TokenConText;
import com.swallow.weixin.work.entity.AccessToken;
import com.swallow.weixin.work.repository.AccessTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class AccessTokenService {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);


    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Value("${corp_secret}")
    public String secrets;

    @Scheduled(cron = "0 0 * * * ?")
    public void refresh() {
        logger.info("refresh access token task start");
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            Map<String, String> secretList = JSON.parseObject(secrets, Map.class);
            ConcurrentHashMap<String, String> tokens = new ConcurrentHashMap<>(secretList.size());
            for (Map.Entry<String, String> entry : secretList.entrySet()) {
                AccessToken accessToken = accessTokenRepository.getAccessToken(entry.getValue());
                if (accessToken != null && accessToken.isValid()) {
                    tokens.put(entry.getKey(), accessToken.getAccess_token());
                }
            }
            TokenConText.addAll(tokens);
            logger.info("access token = {}", JSON.toJSONString(tokens));
        } catch (Exception e) {
            logger.error("refresh access token task error", e);
        } finally {
            logger.info("refresh access token task end cost={}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

}
