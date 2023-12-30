package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.YoutubeUserMapper;
import com.bigModel.backend.mapper.YoutubeVideoMapper;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.pojo.YoutubeUser;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.YoutubeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeUserServiceImpl implements YoutubeUserService {

    @Autowired
    private YoutubeUserMapper youtubeUserMapper;

    @Override
    public List<YoutubeUser> listAll() {
        return youtubeUserMapper.selectList(null);
    }


    @Override
    public IPage<YoutubeUser> findYoutubeUserByUsername(YoutubeUser youtubeUser, Integer page, Integer size) {
        Page<YoutubeUser> pageHelper = new Page<>(page, size);
        QueryWrapper<YoutubeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", youtubeUser.getUsername());
        IPage<YoutubeUser> pageList = youtubeUserMapper.selectPage(pageHelper, queryWrapper);
        return pageList;
    }

    @Override
    public void deleteYoutubeUserById(String username) {
        QueryWrapper<YoutubeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        youtubeUserMapper.delete(queryWrapper);
    }

    @Override
    public void addYoutubeUser(YoutubeUser youtubeUser) {
        youtubeUserMapper.insert(youtubeUser);
    }
}
