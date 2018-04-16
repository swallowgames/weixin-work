package com.swallow.weixin.work.controller;

import com.swallow.weixin.work.context.TokenConText;
import com.swallow.weixin.work.enums.AppType;
import com.swallow.weixin.work.service.AccessTokenService;
import com.swallow.weixin.work.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeixinController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "hc")
    public String hc(){
        return "ok";
    }

    @GetMapping(value = "getAccessToken")
    public String getAccessToken(){
        return TokenConText.get(AppType.test.name());
    }

    @GetMapping(value = "createDept")
    public Boolean createDept(){
        boolean result = true;
        for (int i = 0; i < 10; i++) {
            result &= memberService.create();
        }
        return result;
    }

}
