package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.TweetMapper;
import com.bigModel.backend.mapper.TwitterUserInfoMapper;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bigModel.backend.utils.chatGPT;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TweetServiceImpl implements TweetService{

    @Autowired
    private TweetMapper tweetMapper;

    @Autowired
    private TwitterUserInfoMapper twitterUserInfoMapper;

    @Override
    public void saveTweet(List<Tweet> list) {
        for (int i = 0; i < list.size(); i ++) {
            tweetMapper.saveTweet(list.get(i));
        }
    }

    @Override
    public IPage<Tweet> getTweets(Integer pageNum, Integer size) {
        IPage<Tweet> page = new Page<>(pageNum, size);
//        page.setCurrent(pageQuery.getCurrentNum());
//        page.setSize(pageQuery.getPageSize());
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<Tweet> pageList = tweetMapper.selectPage(page, queryWrapper);
        return pageList;
    }

    @Override
    public Map<String, Object> queryTweets(Integer pageNum, String keyword) {
        IPage<Tweet> page = new Page<>(pageNum, 10);
//        page.setCurrent(pageQuery.getCurrentNum());
//        page.setSize(pageQuery.getPageSize());
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("text", keyword);
        queryWrapper.orderByDesc("id");
        IPage<Tweet> pageList = tweetMapper.selectPage(page, queryWrapper);
        Long count = tweetMapper.selectCount(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("data", pageList.getRecords());
        map.put("current", String.valueOf(pageList.getCurrent()));
        map.put("size", String.valueOf(pageList.getSize()));
        map.put("pages", String.valueOf(pageList.getPages()));
        map.put("total_tweets", count);
        return map;
    }

    @Override
    public IPage<Tweet> findTweetByKeyword(String keyword, List<String> needName, Integer pageNum, Integer size) {
        IPage<Tweet> page = new Page<>(pageNum, size);
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("text", keyword);

        if(!needName.isEmpty() && needName != null){
            queryWrapper.in("username", needName);
        }
        queryWrapper.orderByDesc("id");
        IPage<Tweet> pageList = tweetMapper.selectPage(page, queryWrapper);
        Long count = tweetMapper.selectCount(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("data", pageList.getRecords());
        map.put("current", String.valueOf(pageList.getCurrent()));
        map.put("size", String.valueOf(pageList.getSize()));
        map.put("pages", String.valueOf(pageList.getPages()));
        map.put("total_tweets", count);
        return pageList;
    }

    @Override
    @Transactional
    public Map<String, String> analysisByGPT(Integer id) {
        Tweet tweet = tweetMapper.selectById(id);
        String content = tweet.getText();
        Map<String, String> res = chatGPT.getAnswer(content);

//        存入类别
        tweet.setCategory(res.get("category").toString());
        tweetMapper.updateById(tweet);
        return res;
    }

    @Override
    public Map<String, Object> getNumberTweet() {
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        Long count = tweetMapper.selectCount(queryWrapper);
        Map<String, Object> res = new HashMap<>();
        res.put("totalTweet",count);
        return res;
    }

    @Override
    public List<Tweet> getAllTweet(String uuid) {
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("need_return", 1);
        queryWrapper.eq("uuid", uuid);
        List<Tweet> tweetList = tweetMapper.selectList(queryWrapper);
        return tweetList;
    }

    @Override
    public void updateFlag(Integer id) {
        Tweet tweet = new Tweet();
        tweet.setId(id);
        tweet.setFlag(1);
        tweetMapper.updateById(tweet);
    }

    @Override
    public List<Tweet> getTweetByDate(String date) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("date", date);
        return tweetMapper.selectList(queryWrapper);
    }

    @Override
    public void checkKeyword(String keyword, String uuid) {
        tweetMapper.checkKeyword(keyword, uuid);
    }

    @Override
    public void updateReturn(Integer id) {
        Tweet tweet = new Tweet();
        tweet.setId(id);
        tweet.setNeedReturn(1);
        tweetMapper.updateById(tweet);
    }

    @Override
    public void saveKeywordList(int id, List<String> list) {
        String join = String.join(",", list);
        Tweet tweet = tweetMapper.selectById(id);
        tweet.setKeyword(join);
        tweetMapper.updateById(tweet);
    }

    @Override
    public List<Tweet> listAllNoReturn(String uuid) {
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("need_return", 0);
        queryWrapper.eq("uuid", uuid);
        List<Tweet> tweetList = tweetMapper.selectList(queryWrapper);
        return tweetList;
    }

    @Override
    public List<Tweet> listByUuid(String uuid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uuid", uuid);
        return tweetMapper.selectList(queryWrapper);
    }

    public List<Tweet> listNeedExportIds(List<Integer> needExportIds) {
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", needExportIds);
        List<Tweet> tweetList = tweetMapper.selectList(queryWrapper);
        return tweetList;
    }

    @Override
    public List<Tweet> listAllExportIds() {
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        List<Tweet> tweetList = tweetMapper.selectList(queryWrapper);
        return tweetList;
    }

    @Override
    public List<Map<String, String>> listAllUser() {
        List<Map<String, String>> res = new ArrayList<>();
        QueryWrapper<TwitterUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "name").groupBy("username", "name");
        List<TwitterUser> tweetList = twitterUserInfoMapper.selectList(queryWrapper);
        for(TwitterUser twitterUser : tweetList){
            Map<String, String> userMap = new HashMap<>();
            userMap.put("label", twitterUser.getName());
            userMap.put("key", twitterUser.getUsername());
            res.add(userMap);
        }
        return res;
    }

    @Override
    public Tweet getByTweetId(String tweetid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tweetid", tweetid);
        List<Tweet> list =  tweetMapper.selectList(queryWrapper);
        return list.get(0);
    }

//    返回原因
    @Override
    public void updateReason(Integer id, String res) {
        Tweet tweet = tweetMapper.selectById(id);
        tweet.setFeedbackReason(res);
        tweetMapper.updateById(tweet);
    }
}
