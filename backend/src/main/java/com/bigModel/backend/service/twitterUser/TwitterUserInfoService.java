package com.bigModel.backend.service.twitterUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.TwitterUser;

import java.util.List;

public interface TwitterUserInfoService {
    void addTwitterUser(TwitterUser twitterUser);
    IPage<TwitterUser> getTwitterUsers(Integer pageNum);
    void deleteTwitterUserByTwitterId(String twitterId);
    List<TwitterUser> listAll();
}
