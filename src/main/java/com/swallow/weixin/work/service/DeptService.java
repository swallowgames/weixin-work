package com.swallow.weixin.work.service;

import com.swallow.weixin.work.context.TokenConText;
import com.swallow.weixin.work.enums.AppType;
import com.swallow.weixin.work.param.DeptParam;
import com.swallow.weixin.work.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptRepository deptRepository;

    public List<DeptParam> getList(){
        DeptParam param = new DeptParam();
        param.setAccess_token(TokenConText.get(AppType.test.name()));
        return deptRepository.list(param);
    }

}
