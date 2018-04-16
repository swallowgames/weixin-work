package com.swallow.weixin.work.util;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.swallow.weixin.work.context.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger("http");

    public static String get(String path, Object params){
        return get(path, params, String.class);
    }


    public static <T> T get(String path, Object params, Class<T> clazz){
        T result = null;
        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            String url = build(path, BeanUtils.beanToMap(params));
            logger.info("type=[get] url = {} params = {}", url, "");
            RestTemplate restTemplate = SpringContextHolder.getBean(RestTemplate.class);
            result = restTemplate.getForEntity(url, clazz).getBody();
            logger.info("type=[get] result = {} cost={}ms", result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            logger.info("type=[get] request error", e);
        }
        return result;
    }

    public static String post(String path, Object params){
        return post(path, params, String.class);
    }

    public static <T> T post(String path, Object params, Class<T> clazz){
        T result = null;
        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            logger.info("type=[post] url = {} params = {}", path, JSON.toJSONString(params));
            RestTemplate restTemplate = SpringContextHolder.getBean(RestTemplate.class);
            result = restTemplate.postForEntity(path, params, clazz).getBody();
            logger.info("type=[post] result = {} cost={}ms", result, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            logger.info("type=[post] request error", e);
        }
        return result;
    }

    /**
     * 构造get请求参数
     * @param path
     * @param params
     * @return
     */
    private static String build(String path, Map<String, Object> params) {
        String fullUrl = path;
        try {
            StringBuffer tempParams = new StringBuffer();
            //处理参数
            int pos = 0;
            for (String key : params.keySet()) {
                if (params.get(key) == null) continue;
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(String.valueOf(params.get(key)), "utf-8")));
                pos++;
            }
            fullUrl = String.format("%s?%s", path, tempParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullUrl;
    }

}
