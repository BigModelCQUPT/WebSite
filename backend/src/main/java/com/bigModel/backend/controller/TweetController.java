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
<<<<<<< HEAD
    public IPage<Tweet> findTweetByPage(
=======
    public Result findTweetByPage(
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestBody Map<String, String> data) throws UnsupportedEncodingException, URISyntaxException {

        String keyword = data.get("keyword");
<<<<<<< HEAD
        return tweetService.findTweetByKeyword(keyword, page, size);
=======
        return Result.success(tweetService.findTweetByKeyword(keyword, page, size));
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
    }

    @PostMapping("/tweet/analysisByGPT")
    public Result analysisByGPT(@RequestBody Map<String, String> data){
        Integer id = Integer.valueOf(data.get("id"));
<<<<<<< HEAD
        System.out.println(id);
=======
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        Map<String, Object> map = tweetService.analysisByGPT(id);
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
}