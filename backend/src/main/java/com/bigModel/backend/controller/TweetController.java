package com.bigModel.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.advice.ResponseNotIntercept;
import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@ResponseNotIntercept
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping("/tweet/listAll")
    public Result getTweets(@RequestBody Map<String, String> IPage){
        Integer pageNum = Integer.parseInt(IPage.get("page"));
        Integer size = Integer.parseInt(IPage.get("size"));
        return Result.success(tweetService.getTweets(pageNum, size));
    }

    @GetMapping("/tweet/queryTweets")
    public Map<String, Object> queryTweets(@RequestParam Map<String, String> data){
        Integer pageNum = Integer.parseInt(data.get("page"));
        String keyword = data.get("keyword");
        return tweetService.queryTweets(pageNum, keyword);
    }

    @PostMapping("/tweet/findByPage/{page}/{size}")
    public Result findTweetByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestBody Map<String, String> data) throws UnsupportedEncodingException, URISyntaxException {

        String keyword = data.get("keyword");
        return Result.success(tweetService.findTweetByKeyword(keyword, page, size));
    }

    @PostMapping("/tweet/analysisByGPT")
    public Result analysisByGPT(@RequestBody Map<String, String> data){
        Integer id = Integer.valueOf(data.get("id"));
        Map<String, String> map = tweetService.analysisByGPT(id);
        return Result.success(map);
    }

    @GetMapping("/tweet/getNumberTweet")
    public Result getNumberTweet(){
        Map<String, Object> map = tweetService.getNumberTweet();
        return Result.success(map);
    }

    @PostMapping("/tweet/updateFlag/{id}")
    public Result updateFlag(@PathVariable("id") int id) {
        tweetService.updateFlag(id);
        return Result.success("修改成功");
    }

    @PostMapping("/tweet/readTweet")
    public Result readTweet(@RequestBody List<Tweet> data){
//        List<Tweet> needReadList = castList(data.get("needReadList"), Tweet.class);
        for (Tweet tweet : data) {
            Integer id = tweet.getId();
            tweetService.updateFlag(id);
        }
        return Result.success("修改成功");
    }
}