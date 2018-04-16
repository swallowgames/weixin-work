package com.swallow.weixin.work.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenConText {

    private volatile static Map<String, String> tokens = new ConcurrentHashMap<>(12);

    public static String get(String secret) {
        return tokens.get(secret);
    }

    public static void addAll(Map<String, String> tokens){
        TokenConText.tokens = tokens;
    }

}
