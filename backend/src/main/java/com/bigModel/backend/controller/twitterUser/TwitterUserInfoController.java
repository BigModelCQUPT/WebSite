package com.bigModel.backend.controller.twitterUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.twitterUser.TwitterUserInfoService;
import com.bigModel.backend.utils.UsernameToInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("twitterUser")
public class TwitterUserInfoController {

    @Autowired
    private TwitterUserInfoService infoService;

    @PostMapping("getUsers")
    public IPage<TwitterUser> listAllUser(@RequestBody Map<String, String> IPage) {
        System.out.println(IPage);
        Integer pageNum = Integer.parseInt(IPage.get("page"));
        Integer size = Integer.parseInt(IPage.get("size"));
        return infoService.getTwitterUsers(pageNum, size);
    }

    @PostMapping("deleteUser")
    public void deleteUser(@RequestParam Map<String, String> data) {
        infoService.deleteTwitterUserByTwitterId(data.get("twitterId"));
    }

    @PostMapping("addUser")
    public void addUser(@RequestBody Map<String, String> data) throws UnsupportedEncodingException, URISyntaxException {
        TwitterUser twitterUser = UsernameToInfoUtil.getInfoByUsername(data.get("username"));
        infoService.addTwitterUser(twitterUser);
    }

    @PostMapping("findByUsername")
    public IPage<TwitterUser> findByUsername(@RequestBody Map<String, String> data) throws UnsupportedEncodingException, URISyntaxException {
        TwitterUser twitterUser = new TwitterUser();
        twitterUser.setUsername(data.get("username"));
        Integer pageNum = Integer.parseInt(data.get("page"));
        Integer size = Integer.parseInt(data.get("size"));
        return infoService.findTwitterUserByUsername(twitterUser, pageNum, size);
    }

    @PostMapping("findByPage/{page}/{size}")
    public IPage<TwitterUser> findUserByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestBody Map<String, String> data) throws UnsupportedEncodingException, URISyntaxException {
        System.out.println(data);
        TwitterUser twitterUser = new TwitterUser();
        twitterUser.setUsername(data.get("username"));
//        Integer pageNum = Integer.parseInt(data.get("page"));
//        Integer size = Integer.parseInt(data.get("size"));
        return infoService.findTwitterUserByUsername(twitterUser, page, size);
    }

    @GetMapping("/getNumberUser")
    public Result getNumberUser(){
        Map<String, Object> map = infoService.getNumberUser();
        return Result.success(map);
    }
}
