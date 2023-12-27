package com.bigModel.backend.utils;


import com.bigModel.backend.pojo.Tweet;
<<<<<<< HEAD
=======
import com.bigModel.backend.service.impl.util.TraditionalToSimplifiedUtil;
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
            tweet.setText(item.getString("text"));
=======
            tweet.setText(TraditionalToSimplifiedUtil.TraditionalToSimplified(item.getString("text")));
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
