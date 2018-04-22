package com.swallow.weixin.work.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisTemplate {

    private static final Logger logger = LoggerFactory.getLogger("jedis");

    private static final String EMPTY = StringUtils.EMPTY;

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String str = EMPTY;
        try {
            str = jedis.get(key);
        } finally {
            closeQuiety(jedis);
        }
        return str;
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = EMPTY;
        try {
            str = jedis.set(key, value);
        } finally {
            closeQuiety(jedis);
        }
        return str;
    }

    public String set(String key, String value, int time) {
        Jedis jedis = jedisPool.getResource();
        String str = EMPTY;
        try {
            str = jedis.set(key, value);
            expire(key, time);
        } finally {
            closeQuiety(jedis);
        }
        return str;
    }

    public String setex(String key, int seconds, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = EMPTY;
        try {
            str = jedis.setex(key, seconds, value);
        } finally {
            closeQuiety(jedis);
        }
        return str;
    }

    public Long expire(String key, int time) {
        Jedis jedis = jedisPool.getResource();
        Long result = 0L;
        try {
            result = jedis.expire(key, time);
        } finally {
            closeQuiety(jedis);
        }
        return result;
    }

    private void closeQuiety(Jedis jedis) {
        try {
            jedis.close();
        } catch (Exception e) {
            logger.error("close redis connection error:" + e.getMessage(), e);
        }
    }

}
