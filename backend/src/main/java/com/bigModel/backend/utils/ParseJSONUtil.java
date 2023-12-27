package com.bigModel.backend.utils;


import com.bigModel.backend.pojo.Tweet;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ParseJSONUtil {
    public static List<Tweet> parseJSON(String string,String username, String twitterId) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject json = new JSONObject(string);
        String data = json.getString("data");
        JSONArray jsonArray = new JSONArray(data);
        List<Tweet> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i ++) {
            JSONObject item = new JSONObject(jsonArray.getString(i));
            String type = "tweet";
            try {
                JSONArray referenced_tweets = new JSONArray(item.getString("referenced_tweets"));
                JSONObject referenced_tweet_item = new JSONObject((String) referenced_tweets.getString(0));
                type = referenced_tweet_item.getString("type");
            }catch (JSONException exception) {
            }
            Tweet tweet = new Tweet();
            tweet.setText(item.getString("text"));
            tweet.setFlag(0);
            tweet.setType(type);
            tweet.setTweetid(item.getString("id"));
            tweet.setUsername(username);
            tweet.setTwitterId(twitterId);
            tweet.setDate(s.format(c.getTime()));
            list.add(tweet);
        }
        return list;
    }
}
