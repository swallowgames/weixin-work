package com.swallow.weixin.work.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.swallow.weixin.work.cache.JedisTemplate;
import com.swallow.weixin.work.context.TokenConText;
import com.swallow.weixin.work.entity.Token;
import com.swallow.weixin.work.repository.AccessTokenRepository;
import com.swallow.weixin.work.repository.TokenMapper;
import com.swallow.weixin.work.result.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class AccessTokenService {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private TokenMapper tokenMapper;

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
                if (accessToken == null || !accessToken.isValid()) {
                    continue;
                }
                tokens.put(entry.getKey(), accessToken.getAccess_token());
                Token token = new Token();
                token.setAccessToken(accessToken.getAccess_token());
                token.setExpireIn(accessToken.getExpires_in().longValue());
                token.setType((short)1);
                tokenMapper.insert(token);
            }
            TokenConText.addAll(tokens);
            String json = JSON.toJSONString(tokens);
            jedisTemplate.set("wx:token:list", json);
            logger.info("access token = {}", json);
        } catch (Exception e) {
            logger.error("refresh access token task error", e);
        } finally {
            logger.info("refresh access token task end cost={}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

}
