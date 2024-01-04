package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.Tweet;

import java.util.List;
import java.util.Map;

public interface TweetService {
    void saveTweet(List<Tweet> list);
    IPage<Tweet> getTweets(Integer pageNum, Integer size);
    Map<String, Object> queryTweets(Integer pageNum, String keyword);

    IPage<Tweet> findTweetByKeyword(String keyword, Integer pageNum, Integer size);

    Map<String, String> analysisByGPT(Integer id);

    Map<String, Object> getNumberTweet();

    List<Tweet> getAllTweet();

    void updateFlag(Integer id);
    List<Tweet> getTweetByDate(String date);
    boolean checkKeyword(Integer id, String keyword);
    void updateReturn(Integer id);

    void saveKeywordList(int id, List<String> list);

    List<Tweet> listAllNoReturn();
}
