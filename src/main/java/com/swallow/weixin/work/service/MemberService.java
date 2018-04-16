package com.swallow.weixin.work.service;

import com.google.common.collect.Lists;
import com.swallow.weixin.work.enums.AppType;
import com.swallow.weixin.work.param.MemberParam;
import com.swallow.weixin.work.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private DeptService deptService;

    private static AtomicLong index = new AtomicLong(10);

    public Boolean create() {
        Long c = index.incrementAndGet();
        MemberParam param = new MemberParam();
        param.setAccess_token(accessTokenService.get(AppType.contact.name()));
        param.setUserid("swallow" + c);
        param.setName("许望龙" + c);
        param.setDepartment(Lists.newArrayList(deptService.getList().get(0).getId()));
        param.setGender(String.valueOf(1));
        param.setMobile(String.valueOf(13726707256L + c));
        param.setEmail(String.valueOf(617756974L + c) + "@qq.com");
        return memberRepository.create(param);
    }

}
