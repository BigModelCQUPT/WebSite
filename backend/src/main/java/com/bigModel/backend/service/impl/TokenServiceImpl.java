package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigModel.backend.config.filter.JwtAuthenticationTokenFilter;
import com.bigModel.backend.mapper.TokenMapper;
import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.Tokens;
import com.bigModel.backend.pojo.User;
import com.bigModel.backend.service.TokenService;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addMailtoken(String mailtoken) {
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<Tokens> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        Tokens dbToken = tokenMapper.selectOne(queryWrapper);
        if(dbToken == null){
            Tokens tokens = new Tokens();
            tokens.setUserId(userId);
            tokens.setMailToken(mailtoken);
            tokenMapper.insert(tokens);
        }else{
            dbToken.setMailToken(mailtoken);
            tokenMapper.updateById(dbToken);
        }

    }

    @Override
    public void addYoutubetoken(String youtubetoken) {
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<Tokens> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        Tokens dbToken = tokenMapper.selectOne(queryWrapper);
        if(dbToken == null){
            Tokens tokens = new Tokens();
            tokens.setUserId(userId);
            tokens.setMailToken(youtubetoken);
            tokenMapper.insert(tokens);
        }else{
            dbToken.setMailToken(youtubetoken);
            tokenMapper.updateById(dbToken);
        }
    }

    @Override
    public void addTwittertoken(String twittertoken) {
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<Tokens> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        Tokens dbToken = tokenMapper.selectOne(queryWrapper);
        if(dbToken == null){
            Tokens tokens = new Tokens();
            tokens.setUserId(userId);
            tokens.setMailToken(twittertoken);
            tokenMapper.insert(tokens);
        }else{
            dbToken.setMailToken(twittertoken);
            tokenMapper.updateById(dbToken);
        }
    }

    @Override
    public void addUserEmail(String email) {
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        User dbUser = userMapper.selectOne(queryWrapper);
        dbUser.setEmail(email);
        userMapper.updateById(dbUser);
    }
}
