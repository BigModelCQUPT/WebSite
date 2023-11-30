package com.bigModel.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.pojo.PageQuery;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.service.TweetService;
import com.bigModel.backend.service.user.account.InfoService;
import com.bigModel.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweet/listAll/")
    public Map<String, Object> getTweets(@RequestParam Map<String, String> data){
//        System.out.println(data);
        Integer pageNum = Integer.parseInt(data.get("page"));
        return tweetService.getTweets(pageNum);
    }
}