package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<Tokens> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Tokens dbtoken = tokenMapper.selectOne(queryWrapper);

        if(dbtoken == null){
            tokens.setUserId(userId);
            tokens.setMailToken(mailtoken);
            tokenMapper.insert(tokens);
        }else{
            dbtoken.setMailToken(mailtoken);
            tokenMapper.updateById(dbtoken);
        }
    }

    @Override
    public void addYoutubetoken(String youtubetoken) {
        Tokens tokens = new Tokens();
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<Tokens> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Tokens dbtoken = tokenMapper.selectOne(queryWrapper);

        if(dbtoken == null){
            tokens.setUserId(userId);
            tokens.setYoutubeToken(youtubetoken);
            tokenMapper.insert(tokens);
        }else{
            dbtoken.setYoutubeToken(youtubetoken);
            tokenMapper.updateById(dbtoken);
        }
    }

    @Override
    public void addTwittertoken(String twittertoken) {
        Tokens tokens = new Tokens();
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<Tokens> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Tokens dbtoken = tokenMapper.selectOne(queryWrapper);

        if(dbtoken == null){
            tokens.setUserId(userId);
            tokens.setTwitterToken(twittertoken);
            tokenMapper.insert(tokens);
        }else{
            dbtoken.setTwitterToken(twittertoken);
            tokenMapper.updateById(dbtoken);
        }
    }
}
