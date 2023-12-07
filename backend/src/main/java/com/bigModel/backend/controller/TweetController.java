package com.bigModel.backend.controller;

import com.bigModel.backend.advice.ResponseNotIntercept;
import com.bigModel.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@ResponseNotIntercept
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