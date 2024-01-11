package com.bigModel.backend.utils;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigModel.backend.mapper.TokenMapper;
import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.Tokens;
import com.bigModel.backend.pojo.User;
import com.bigModel.backend.pojo.YoutubeUser;
import com.bigModel.backend.utils.AppUtil;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class YoutubeUserUtil {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private UserMapper userMapper;
    private static YoutubeUserUtil youtubeUserUtil;

    @PostConstruct
    public void init() {
        youtubeUserUtil = this;
        youtubeUserUtil.userMapper = this.userMapper;
        youtubeUserUtil.tokenMapper = this.tokenMapper;

    }

    public static YoutubeUser getChannel(String channelIdstr) throws IOException {
        String host = "127.0.0.1";
        String port = "54321";
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("https.proxyHost", host);
        System.setProperty("https.proxyPort", port);
        // String[] channelIdList = new String[]{"UCilwQlk62k1z7aUEZPOB6yw", "UCKQVSNdzGBJSXaUmS4TOWww"};


        YouTube youtubeChannel = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request -> {
        }).setApplicationName("youtube-cmdline-search-sample").build();
        YouTube.Channels.List search = youtubeChannel.channels().list("id,snippet");
        search.setFields("items(snippet/publishedAt,snippet/title,snippet/description,snippet/thumbnails)");


        //        改成从数据库中取得
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "admin");
        User user = youtubeUserUtil.userMapper.selectOne(queryWrapper);
        Integer userId = user.getId();
        QueryWrapper<Tokens> queryWrapperToken = new QueryWrapper<>();
        queryWrapperToken.eq("user_id", userId);
        Tokens tokens = youtubeUserUtil.tokenMapper.selectOne(queryWrapperToken);
        String apiKey = tokens.getYoutubeToken();



        search.setKey(apiKey);
        search.setMaxResults(5L);
        String channelId = channelIdstr;
        search.setId(channelId);
        ChannelListResponse response;
        while (true) {
            try {
                response = search.execute();
                break;
            } catch (GoogleJsonResponseException e) {
                if (403 == e.getDetails().getCode()) {
                    // 配额用尽，使用下一个
                    return null;
                } else {
                    throw e;
                }
            }
        }
        if (response == null || AppUtil.isNull(response.getItems())) {
            return null;
        }
        List<Channel> items = response.getItems();
        if (AppUtil.isNull(items)) {
            return null;
        }
        Channel channel = items.get(0);
        System.out.println("channelId:" + channelId);
        System.out.println("频道名称:" + channel.getSnippet().getTitle());
        System.out.println("缩略图 thumbnails:" + channel.getSnippet().getThumbnails().getDefault().getUrl());
        System.out.println("频道简介：" + channel.getSnippet().getDescription());

        YoutubeUser youtubeUser = new YoutubeUser();
        youtubeUser.setUsername(channel.getSnippet().getTitle());
        youtubeUser.setChannelId(channelId);
        youtubeUser.setResume(channel.getSnippet().getDescription());

        return youtubeUser;
    }
}


