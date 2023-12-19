package com.bigModel.backend.service.impl;

import com.bigModel.backend.mapper.YoutubeUserMapper;
import com.bigModel.backend.mapper.YoutubeVideoMapper;
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
}
