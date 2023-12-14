package com.bigModel.backend.service.twitterUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.TwitterUser;

import java.util.List;

public interface TwitterUserInfoService {
    void addTwitterUser(TwitterUser twitterUser);
    IPage<TwitterUser> getTwitterUsers(Integer pageNum, Integer size);
    void deleteTwitterUserByTwitterId(String twitterId);
    List<TwitterUser> listAll();
    IPage<TwitterUser> findTwitterUserByUsername(TwitterUser twitterUser, Integer pageNum, Integer size);
}
