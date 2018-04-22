package com.swallow.weixin.work.repository;

import com.swallow.weixin.work.entity.Token;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}