package com.bigModel.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.mapper.YoutubeVideoMapper;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.YoutubeUser;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.YoutubeUserService;
import com.bigModel.backend.service.YoutubeVideoService;
import com.bigModel.backend.utils.YoutubeVideoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/youtube")
public class YoutubeVideoController {

    @Autowired
    private YoutubeVideoService youtubeVideoService;

    @Autowired
    private YoutubeUserService youtubeUserService;

    @GetMapping("/update")
    public void updateVideo() throws IOException {
        List<YoutubeUser> userList = youtubeUserService.listAll();
        List<YoutubeVideo> list = YoutubeVideoUtil.getVideo(userList);
        for (int i = 0; i < list.size(); i ++) {
            youtubeVideoService.addVideo(list.get(i));
        }
    }

    @PostMapping("/listAll/{page}/{size}")
    public IPage<YoutubeVideo> listAll(@PathVariable(value = "page") Integer page,
                                       @PathVariable(value = "size") Integer size) {
        return youtubeVideoService.listAll(page, size);
    }

    @PostMapping("/readTweet")
    public Result readTweet(@RequestBody List<YoutubeVideo> data){
//        List<Tweet> needReadList = castList(data.get("needReadList"), Tweet.class);
        for (YoutubeVideo youtubeVideo : data) {
            Integer id = youtubeVideo.getId();
            youtubeVideoService.updateFlag(id);
        }
        Integer a = 1;
        Integer b = 1;
        System.out.println();
        return Result.success("修改成功");
    }
}
