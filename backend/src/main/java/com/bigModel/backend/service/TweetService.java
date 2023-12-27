package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
<<<<<<< HEAD
import com.bigModel.backend.pojo.PageQuery;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
=======
import com.bigModel.backend.pojo.Tweet;
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f

import java.util.List;
import java.util.Map;

public interface TweetService {
    void saveTweet(List<Tweet> list);
    IPage<Tweet> getTweets(Integer pageNum, Integer size);
    Map<String, Object> queryTweets(Integer pageNum, String keyword);

    IPage<Tweet> findTweetByKeyword(String keyword, Integer pageNum, Integer size);

    Map<String, Object> analysisByGPT(Integer id);

    Map<String, Object> getNumberTweet();

    List<Tweet> getAllTweet();

    void updateFlag(Integer id);
<<<<<<< HEAD
=======
    List<Tweet> getTweetByDate(String date);
    boolean checkKeyword(Integer id, String keyword);
    void updateReturn(Integer id);

    void saveKeywordList(int id, List<String> list);
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
}
