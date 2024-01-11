package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.TweetMapper;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.pojo.PageQuery;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bigModel.backend.utils.chatGPT;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TweetServiceImpl implements TweetService{

    @Autowired
    private TweetMapper tweetMapper;

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
    public IPage<Tweet> findTweetByKeyword(String keyword, Integer pageNum, Integer size) {
        IPage<Tweet> page = new Page<>(pageNum, size);
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
    public List<Tweet> getAllTweet() {
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
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
    public boolean checkKeyword(Integer id, String keyword) {
        return tweetMapper.checkKeyword(id, keyword) == 1;
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
    public List<Tweet> listAllNoReturn() {
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("need_return", 0);
        List<Tweet> tweetList = tweetMapper.selectList(queryWrapper);
        return tweetList;
    }

    @Override
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
}
