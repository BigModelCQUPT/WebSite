package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.TweetMapper;
import com.bigModel.backend.pojo.PageQuery;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Daniel
 * @Description:
 * @Date: 2023/11/29 19:07
 * @Email: lxb2000m@gmail.com
 */

@Service
public class TweetServiceImpl implements TweetService{

    @Autowired
    private TweetMapper tweetMapper;

    @Override
    public void saveTweet(List<Tweet> list) {
        for (int i = 0; i < list.size(); i ++) {
            tweetMapper.insert(list.get(i));
        }
    }

    @Override
    public Map<String, Object> getTweets(Integer pageNum) {
        IPage<Tweet> page = new Page<>(pageNum, 10);
//        page.setCurrent(pageQuery.getCurrentNum());
//        page.setSize(pageQuery.getPageSize());
        QueryWrapper<Tweet> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<Tweet> pageList = tweetMapper.selectPage(page, queryWrapper);
        Long count = tweetMapper.selectCount(null);
        Map<String, Object> map = new HashMap<>();
        map.put("data", pageList.getRecords());
        map.put("current", String.valueOf(pageList.getCurrent()));
        map.put("size", String.valueOf(pageList.getSize()));
        map.put("pages", String.valueOf(pageList.getPages()));
        map.put("total_tweets", count);
        return map;
    }
}
