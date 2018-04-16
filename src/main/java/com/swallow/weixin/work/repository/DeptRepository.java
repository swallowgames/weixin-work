package com.swallow.weixin.work.repository;

import com.alibaba.fastjson.JSON;
import com.swallow.weixin.work.entity.DeptListResult;
import com.swallow.weixin.work.entity.DeptResult;
import com.swallow.weixin.work.param.DeptParam;
import com.swallow.weixin.work.util.BeanUtils;
import com.swallow.weixin.work.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class DeptRepository {

    private static final Logger logger = LoggerFactory.getLogger(DeptRepository.class);

    private static final String ROOT_PATH = "https://qyapi.weixin.qq.com/cgi-bin/department/";

    public Long create(DeptParam param) {
        String content = HttpUtils.get(ROOT_PATH + "create", param);
        DeptResult result = JSON.parseObject(content, DeptResult.class);
        return result.isValid() ? result.getId() : null;
    }

    public List<DeptParam> list(DeptParam param){
        String content = HttpUtils.get(ROOT_PATH + "list", param);
        DeptListResult result = JSON.parseObject(content, DeptListResult.class);
        return result.isValid() ? result.getDepartment() : Collections.emptyList();
    }

}
