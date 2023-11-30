package com.bigModel.backend.utils;


import com.bigModel.backend.pojo.Tweet;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJSONUtil {
    public static List<Tweet> parseJSON(String string, String keyword) {
        // String string = "{\"data\":[{\"id\":\"1\", \"text\":\"1111\"}, {\"id\":\"12\", \"text\":\"1111\"}], \"meta\": \"dasd\"}";
        JSONObject json = new JSONObject(string);
        String data = json.getString("data");
        JSONArray jsonArray = new JSONArray(data);
        List<Tweet> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i ++)    {
            String tweetid = jsonArray.getJSONObject(i).getString("id");
            String text = jsonArray.getJSONObject(i).getString("text");
            Tweet tweet = new Tweet();
            tweet.setTweetid(tweetid);
            tweet.setText(text);
            tweet.setKeyword(keyword);
            list.add(tweet);
        }
        return list;
    }
}
