package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.PageQuery;
import com.bigModel.backend.pojo.Tweet;

import java.util.List;
import java.util.Map;

public interface TweetService {
    void saveTweet(List<Tweet> list);
    IPage<Tweet> getTweets(Integer pageNum, Integer size);
    Map<String, Object> queryTweets(Integer pageNum, String keyword);
}
