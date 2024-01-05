package com.bigModel.backend.utils;

import com.bigModel.backend.pojo.YoutubeUser;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.TokenService;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.IOException;
import java.lang.management.LockInfo;
import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoUtil {

    @Autowired
    private static TokenService tokenService;

    public static List<YoutubeVideo> getVideo(List<YoutubeUser> userList) throws IOException {
        String host = "127.0.0.1";
        String port = "54321";
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("https.proxyHost", host);
        System.setProperty("https.proxyPort", port);
        // String[] channelIdList = new String[]{"UCilwQlk62k1z7aUEZPOB6yw", "UCKQVSNdzGBJSXaUmS4TOWww"};
        List<YoutubeVideo> list = new ArrayList<>();
        for (int j = 0; j < userList.size(); j++) {
            YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request -> {
            }).setApplicationName("youtube-cmdline-search-sample").build();
            YouTube.Search.List search = null;
            try {
                search = youtube.search().list("id,snippet");
            } catch (IOException e) {
                // LogUtil.error(e);
                // return e.getMessage();
                System.out.println("出错");
            }

            String apiKey = tokenService.getToken("youtubeToken");

            search.setKey(apiKey);
            // 接口返回数据模型
            search.setType("video");
            // 设置需要接口返回的字段
            search.setFields("items(id/kind,id/videoId,id/videoId,snippet/title,snippet/thumbnails/default/url),nextPageToken,pageInfo,prevPageToken");
            // 返回的最大记录条数
            search.setMaxResults(5L);
            // 设置要查询的channel id
            search.setChannelId(userList.get(j).getChannelId());
            // 安装视频上传时间倒序获取
            search.setOrder("date");
            SearchListResponse searchResponse = null;
            try {
                searchResponse = search.execute();
            } catch (IOException e) {
                // LogUtil.error(e);
                System.out.println(e);
                System.out.println("出错了");
            }
            List<SearchResult> searchResultList = searchResponse.getItems();
            for (int i = 0; i < searchResultList.size(); i++) {
                // JSONArray jsonArray = new JSONArray(searchResultList.get(i));
                // for (int k = 0; k < jsonArray.length(); k++) {
                //     JSONObject json = new JSONObject(jsonArray.getString(k));
                    JSONObject json = new JSONObject(searchResultList.get(i));
                    String videoId = "https://www.youtube.com/watch?v=" + new JSONObject(json.getString("id")).getString("videoId");
                    String cover = new JSONObject(new JSONObject(new JSONObject(json.getString("snippet")).getString("thumbnails")).getString("default")).getString("url");
                    String title = new JSONObject(json.getString("snippet")).getString("title");
                    String username = userList.get(j).getUsername();
                    YoutubeVideo youtubeVideo = new YoutubeVideo(null, cover, username, userList.get(j).getChannelId(), videoId, title, null, 0, null, 0);
                    list.add(youtubeVideo);
                // }
                // if (searchResultList != null) {
                //     PageInfo pageInfo = searchResponse.getPageInfo();
                //     // 根据分页获取全部数据
                //     while (true) {
                //         allRecord.addAll(searchResultList);
                //         if (pageInfo.getTotalResults() <= NUMBER_OF_VIDEOS_RETURNED) {
                //             break;
                //         }
                //         // 设置分页的参数
                //         search.setPageToken(searchResponse.getNextPageToken());
                //         try {
                //             searchResponse = search.execute();
                //         } catch (IOException e) {
                //             LogUtil.error(e);
                //             return e.getMessage();
                //         }
                //         if (searchResponse == null ||
                //                 AppUtil.isNull(searchResponse.getItems())) {
                //             break;
                //         }
                //         List<SearchResult> items = searchResponse.getItems();
                //         if (AppUtil.isNull(items)) {
                //             break;
                //         }
                //         allRecord.addAll(items);
                //         if (items.size() < NUMBER_OF_VIDEOS_RETURNED) {
                //             break;
                //         }
                //     }
                //     if (AppUtil.isNull(allRecord)) {
                //         return "当前频道未查询到视频信息";
                //     }
                //     // 获取所有的video信息
                //     List<YoutubeSimpleSource> objects = new ArrayList<>();
                //     allRecord.forEach(record -> {
                //         YoutubeSimpleSource source = new YoutubeSimpleSource();
                //         source.setVideoId(record.getId().getVideoId());
                //         source.setTitle(record.getSnippet().getTitle());
                //         source.setThumbnails(record.getSnippet().getThumbnails().getDefault().getUrl());
                //         objects.add(source);
                //     });
                //     return JSONObject.toJSONString(objects);
                // }
            }
        }
        return list;
    }
}
