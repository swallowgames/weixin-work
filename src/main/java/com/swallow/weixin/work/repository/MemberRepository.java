package com.swallow.weixin.work.repository;

import com.alibaba.fastjson.JSON;
import com.swallow.weixin.work.result.BaseResult;
import com.swallow.weixin.work.param.MemberParam;
import com.swallow.weixin.work.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private static final Logger logger = LoggerFactory.getLogger(MemberRepository.class);

    private static final String ROOT_PATH = "https://qyapi.weixin.qq.com/cgi-bin/user/";

    public boolean create(MemberParam param){
        String content = HttpUtils.post(ROOT_PATH+"create?access_token="+param.getAccess_token(), param);
        return JSON.parseObject(content, BaseResult.class).isValid();
    }


}