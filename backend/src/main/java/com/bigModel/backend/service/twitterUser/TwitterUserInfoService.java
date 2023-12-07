package com.bigModel.backend.service.twitterUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.TwitterUser;

public interface TwitterUserInfoService {
    void addTwitterUser(TwitterUser twitterUser);
    IPage<TwitterUser> getTwitterUsers(Integer pageNum);
    void deleteTwitterUserByTwitterId(String twitterId);
}
