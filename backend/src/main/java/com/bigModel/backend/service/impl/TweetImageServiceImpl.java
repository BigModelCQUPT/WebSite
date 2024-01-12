package com.bigModel.backend.service.impl;

import com.bigModel.backend.mapper.TweetImageMapper;
import com.bigModel.backend.pojo.TweetImage;
import com.bigModel.backend.service.TweetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetImageServiceImpl implements TweetImageService {

    @Autowired
    private TweetImageMapper tweetImageMapper;

    @Override
    public void add(TweetImage tweetImage) {
        tweetImageMapper.insert(tweetImage);
    }
}
