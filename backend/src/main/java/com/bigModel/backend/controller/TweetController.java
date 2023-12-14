package com.bigModel.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.advice.ResponseNotIntercept;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@ResponseNotIntercept
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping("/tweet/listAll")
    public IPage<Tweet> getTweets(@RequestBody Map<String, String> IPage){
        Integer pageNum = Integer.parseInt(IPage.get("page"));
        Integer size = Integer.parseInt(IPage.get("size"));
        return tweetService.getTweets(pageNum, size);
    }

    @GetMapping("/tweet/queryTweets")
    public Map<String, Object> queryTweets(@RequestParam Map<String, String> data){
        Integer pageNum = Integer.parseInt(data.get("page"));
        String keyword = data.get("keyword");
        return tweetService.queryTweets(pageNum, keyword);
    }
}