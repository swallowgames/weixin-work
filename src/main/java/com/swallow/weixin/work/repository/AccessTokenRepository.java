package com.swallow.weixin.work.repository;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.swallow.weixin.work.param.AccessTokenParam;
import com.swallow.weixin.work.util.BeanUtils;
import com.swallow.weixin.work.util.HttpUtils;
import com.swallow.weixin.work.entity.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Repository
public class AccessTokenRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenRepository.class);

    private static final String ROOT_PATH = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    @Value("${corp_id}")
    public String corpId;

    public AccessToken getAccessToken(String corpSecret){
        AccessTokenParam param = new AccessTokenParam();
        param.setCorpsecret(corpSecret);
        param.setCorpid(corpId);
        String content = HttpUtils.get(ROOT_PATH, param);
        return JSON.parseObject(content, AccessToken.class);
    }

}
