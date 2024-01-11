package com.bigModel.backend.utils;


import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.service.impl.util.TraditionalToSimplifiedUtil;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class ParseJSONUtil {

    /**
     * 解析字符串，得到推文以及推文包含的图片
     * @param string json字符串
     * @param username 用户名
     * @param twitterId 用户id
     * @return {@link List}<{@link Tweet}>
     */
    public static List<Tweet> parseJSON(String string, String username, String twitterId, String uuid) {
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
            tweet.setUrl("https://twitter.com/" + username + "/status/" + twitterId);
            tweet.setPublishTime(new Date(item.getString("created_at")));
            tweet.setUuid(uuid);
            tweet.setNeedReturn(0);
            list.add(tweet);
        }
        return list;
    }
}
