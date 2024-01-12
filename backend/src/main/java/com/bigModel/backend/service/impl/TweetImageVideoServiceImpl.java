package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigModel.backend.mapper.TweetImageMapper;
import com.bigModel.backend.mapper.TweetVideoMapper;
import com.bigModel.backend.pojo.TweetImage;
import com.bigModel.backend.pojo.TweetVideo;
import com.bigModel.backend.service.TweetImageVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetImageVideoServiceImpl implements TweetImageVideoService {


    @Autowired
    private TweetImageMapper tweetImageMapper;
    @Autowired
    private TweetVideoMapper tweetVideoMapper;

    @Override
    public void addTweetImage(TweetImage tweetImage) {
        tweetImageMapper.insert(tweetImage);
    }

    @Override
    public void addTweetVideo(TweetVideo tweetVideo) {
        tweetVideoMapper.insert(tweetVideo);
    }

    @Override
    public List<TweetImage> listImagesByTweetid(String tweetid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tweetid", tweetid);
        return tweetImageMapper.selectList(queryWrapper);
    }

    @Override
    public List<TweetVideo> listVideosByTweetid(String tweetid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tweetid", tweetid);
        return tweetVideoMapper.selectList(queryWrapper);
    }
}
