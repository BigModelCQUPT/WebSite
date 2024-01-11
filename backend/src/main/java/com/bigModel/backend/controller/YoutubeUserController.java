package com.bigModel.backend.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.pojo.YoutubeUser;
import com.bigModel.backend.service.YoutubeUserService;
import com.bigModel.backend.utils.UsernameToInfoUtil;
import com.bigModel.backend.utils.YoutubeUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("youtubeUser")
public class YoutubeUserController {

    @Autowired
    private YoutubeUserService youtubeUserService;

    @PostMapping("findByPage/{page}/{size}")
    public IPage<YoutubeUser> findUserByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestBody Map<String, String> data) throws UnsupportedEncodingException, URISyntaxException {
        YoutubeUser youtubeUser = new YoutubeUser();
        youtubeUser.setUsername(data.get("username"));
        return youtubeUserService.findYoutubeUserByUsername(youtubeUser, page, size);
    }

    @PostMapping("deleteUser")
    public void deleteUser(@RequestParam Map<String, String> data) {
        youtubeUserService.deleteYoutubeUserById(data.get("username"));
    }

    @PostMapping("addUser")
    public void addUser(@RequestBody Map<String, String> data) throws IOException, URISyntaxException {
        System.out.println(data);
        YoutubeUser youtubeUser = YoutubeUserUtil.getChannel(data.get("channelId"));
        youtubeUserService.addYoutubeUser(youtubeUser);
    }
}
