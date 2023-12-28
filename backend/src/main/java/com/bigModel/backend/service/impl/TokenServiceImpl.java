package com.bigModel.backend.service.impl;

import com.bigModel.backend.config.filter.JwtAuthenticationTokenFilter;
import com.bigModel.backend.mapper.TokenMapper;
import com.bigModel.backend.pojo.Tokens;
import com.bigModel.backend.service.TokenService;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void addMailtoken(String mailtoken) {
        Tokens tokens = new Tokens();
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        tokens.setUserId(JwtAuthenticationTokenFilter.getUserBasic().getUserId());
        tokens.setMailToken(mailtoken);
        Tokens dbtoken = tokenMapper.selectById(userId);
        if(dbtoken == null){
            tokenMapper.insert(tokens);
        }
        else{
            tokenMapper.updateById(tokens);
        }
    }
}
