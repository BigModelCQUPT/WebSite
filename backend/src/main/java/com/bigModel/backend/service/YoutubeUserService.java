package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.pojo.YoutubeUser;

import java.util.List;

public interface YoutubeUserService {
    List<YoutubeUser> listAll();

    IPage<YoutubeUser> findYoutubeUserByUsername(YoutubeUser youtubeUser, Integer page, Integer size);

    void deleteYoutubeUserById(String username);

    void addYoutubeUser(YoutubeUser youtubeUser);
}
