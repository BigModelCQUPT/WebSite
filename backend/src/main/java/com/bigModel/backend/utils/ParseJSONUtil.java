package com.bigModel.backend.utils;


import com.bigModel.backend.mapper.TweetVideoMapper;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TweetImage;
import com.bigModel.backend.pojo.TweetVideo;
import com.bigModel.backend.service.TweetImageVideoService;
import com.bigModel.backend.service.impl.util.TraditionalToSimplifiedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Component
public class ParseJSONUtil {

    private static ParseJSONUtil parseJSONUtil;
    @Autowired
    private TweetImageVideoService tweetImageService;
    @Autowired
    private TweetVideoMapper tweetVideoMapper;

    @PostConstruct
    public void init() {
        parseJSONUtil = this;
        parseJSONUtil.tweetImageService = this.tweetImageService;
        parseJSONUtil.tweetVideoMapper = this.tweetVideoMapper;
    }

    /**
     * 解析字符串，得到推文以及推文包含的图片
     * @param string json字符串
     * @param username 用户名
     * @param twitterId 用户id
     * @return {@link List}<{@link Tweet}>
     */
    public static List<Tweet> parseJSON(String string, String username, String twitterId, String uuid) throws IOException {
        JSONObject json = new JSONObject(string);
        String data = json.getString("data");
        JSONArray jsonArray = new JSONArray(data);
        List<Tweet> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i ++) {
            JSONObject item = new JSONObject(jsonArray.getString(i));
            String type = "tweet";
            Tweet tweet = new Tweet();
            String text = TraditionalToSimplifiedUtil.TraditionalToSimplified(item.getString("full_text"));
            text = text.replace("\n", "");
            tweet.setText(text);
            if (text.charAt(0) == 'R' && text.charAt(1) == 'T')
                type = "reposted";
            else if (text.charAt(0) == '@')
                type = "reply";
            tweet.setFlag(0);
            tweet.setType(type);
            tweet.setTweetid(item.getString("id_str"));
            tweet.setUsername(username);
            tweet.setTwitterId(twitterId);
            tweet.setUrl("https://twitter.com/" + username + "/status/" + tweet.getTweetid());
            tweet.setPublishTime(new Date(item.getString("created_at")));
            tweet.setUuid(uuid);
            tweet.setNeedReturn(0);
            list.add(tweet);
            saveTweetImageVideo(item.toString(), tweet.getTweetid());
        }
        return list;
    }

    public static void saveTweetImageVideo(String json, String tweetId) throws IOException {
        try {
            JSONObject user = new JSONObject(json);
            JSONObject extended_entities = user.getJSONObject("extended_entities");
            JSONArray media = extended_entities.getJSONArray("media");
            for (int i = 0; i < media.length(); i ++) {
                JSONObject item = media.getJSONObject(i);
                // System.out.println(item.getString("type"));
                if (item.getString("type").equals("photo")) { // 该推文包含图片
                    TweetImage tweetImage = new TweetImage();
                    tweetImage.setUrl(item.getString("media_url_https"));
                    tweetImage.setTweetid(tweetId);
                    boolean res = parseJSONUtil.tweetImageService.addTweetImage(tweetImage);
                    if (res) {
                        // System.out.println(tweetImage);
                        ImageDownloadUtil.download(tweetImage.getUrl(), "./img", tweetId + "_" + i + ".jpg");
                    }
                } else if (item.getString("type").equals("video")) { // 视频
                    JSONObject video_info = item.getJSONObject("video_info");
                    JSONArray variants = video_info.getJSONArray("variants");
                    String url = variants.getJSONObject(0).getString("url");
                    for (int vi = 1; vi < variants.length(); vi ++) {
                        String tmpUrl = variants.getJSONObject(i).getString("url");
                        if (tmpUrl.contains("mp4")) {
                            url = tmpUrl;
                            break;
                        }
                    }
                    TweetVideo tweetVideo = new TweetVideo();
                    tweetVideo.setTweetid(tweetId);
                    tweetVideo.setUrl(url);
                    // System.out.println(tweetVideo);
                    parseJSONUtil.tweetVideoMapper.insert(tweetVideo);
                }
                else {
                    System.out.println("找不到图片或者视频类别1");
                }
            }
        }catch (JSONException e) {
            System.out.println("第一种json分析失败");
            try {
                JSONObject user = new JSONObject(json);
                JSONObject retweeted_status = user.getJSONObject("retweeted_status");
                JSONObject extended_entities = retweeted_status.getJSONObject("extended_entities");
                JSONArray media = extended_entities.getJSONArray("media");
                for (int i = 0; i < media.length(); i++) {
                    JSONObject item = media.getJSONObject(i);
                    // System.out.println(item.toString());
                    if (item.getString("type").equals("photo")) { // 该推文包含图片
                        TweetImage tweetImage = new TweetImage();
                        tweetImage.setUrl(item.getString("media_url_https"));
                        tweetImage.setTweetid(tweetId);
                        boolean res = parseJSONUtil.tweetImageService.addTweetImage(tweetImage);
                        // System.out.println(tweetImage);
                        if (res) {
                            ImageDownloadUtil.download(tweetImage.getUrl(), "./img", tweetId + "_" + i + ".jpg");
                        }
                    } else if (item.getString("type").equals("video")) { // 视频
                        JSONObject video_info = item.getJSONObject("video_info");
                        JSONArray variants = video_info.getJSONArray("variants");
                        String url = variants.getJSONObject(0).getString("url");
                        TweetVideo tweetVideo = new TweetVideo();
                        tweetVideo.setTweetid(tweetId);
                        tweetVideo.setUrl(url);
                        // System.out.println(tweetVideo);
                        parseJSONUtil.tweetVideoMapper.insert(tweetVideo);
                    } else {
                        System.out.println("找不到图片或者视频类别2");
                    }
                }
            }catch (JSONException jsonException) {
                System.out.println("第二种json分析失败");
            }
        }
    }
}
