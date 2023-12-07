package com.bigModel.backend.controller.twitterUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.twitterUser.TwitterUserInfoService;
import com.bigModel.backend.utils.UsernameToInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("twitterUser")
public class TwitterUserInfoController {

    @Autowired
    private TwitterUserInfoService infoService;

    @GetMapping("getUsers")
    public IPage<TwitterUser> listAllUser(@RequestParam Map<String, String> data) {
        Integer pageNum = Integer.parseInt(data.get("page"));
        return infoService.getTwitterUsers(pageNum);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam Map<String, String> data) {
        infoService.deleteTwitterUserByTwitterId(data.get("twitterId"));
    }

    @PostMapping("addUser")
    public void addUser(@RequestParam Map<String, String> data) throws UnsupportedEncodingException, URISyntaxException {
        TwitterUser twitterUser = UsernameToInfoUtil.getInfoByUsername(data.get("username"));
        infoService.addTwitterUser(twitterUser);
    }
}
