package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.Tweet;

import java.util.List;
import java.util.Map;

public interface TweetService {
    void saveTweet(List<Tweet> list);
    IPage<Tweet> getTweets(Integer pageNum, Integer size);
    Map<String, Object> queryTweets(Integer pageNum, String keyword);

    IPage<Tweet> findTweetByKeyword(String keyword, List<String> needName, Integer pageNum, Integer size);

    Map<String, String> analysisByGPT(Integer id);

    Map<String, Object> getNumberTweet();

    List<Tweet> getAllTweet(String uuid);

    void updateFlag(Integer id);
    List<Tweet> getTweetByDate(String date);
    void checkKeyword(String keyword, String uuid);
    void updateReturn(Integer id);
    void saveKeywordList(int id, String list);
    List<Tweet> listAllNoReturn(String uuid);
    List<Tweet> listByUuid(String uuid);
    List<Tweet> listNeedExportIds(List<Integer> needExportIds);
    List<Tweet> listAllExportIds();
    List<Map<String, String>> listAllUser();
    Tweet getByTweetId(String tweetid);

    void updateReason(Integer id, String res);
}
