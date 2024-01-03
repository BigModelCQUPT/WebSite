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
    private UserMapper userMapper;

    @Override
    public void addMailtoken(String mailtoken) {
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        User dbUser = userMapper.selectOne(queryWrapper);
        dbUser.setMailToken(mailtoken);
        userMapper.updateById(dbUser);
    }

    @Override
    public void addYoutubetoken(String youtubetoken) {
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        User dbUser = userMapper.selectOne(queryWrapper);
        dbUser.setYoutubeToken(youtubetoken);
        userMapper.updateById(dbUser);
    }

    @Override
    public void addTwittertoken(String twittertoken) {
        Integer userId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        User dbUser = userMapper.selectOne(queryWrapper);
        dbUser.setTwitterToken(twittertoken);
        userMapper.updateById(dbUser);
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
