package com.bigModel.backend.service;

import com.bigModel.backend.pojo.PageQuery;
import com.bigModel.backend.pojo.Tweet;

import java.util.List;
import java.util.Map;

/**
 * @Author: Daniel
 * @Description:
 * @Date: 2023/11/29 19:06
 * @Email: lxb2000m@gmail.com
 */

public interface TweetService {
    void saveTweet(List<Tweet> list);
    Map<String, Object> getTweets(Integer pageNum);
}
