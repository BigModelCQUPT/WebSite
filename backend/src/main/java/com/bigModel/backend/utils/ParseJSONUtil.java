package com.bigModel.backend.utils;


import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.service.impl.util.TraditionalToSimplifiedUtil;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ParseJSONUtil {
    public static List<Tweet> parseJSON(String string, String username, String twitterId) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject json = new JSONObject(string);
        String data = json.getString("data");
        JSONArray jsonArray = new JSONArray(data);
        List<Tweet> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i ++) {
            JSONObject item = new JSONObject(jsonArray.getString(i));
            String type = "tweet";
            Tweet tweet = new Tweet();
            String text = TraditionalToSimplifiedUtil.TraditionalToSimplified(item.getString("full_text"));
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
            tweet.setDate(s.format(c.getTime()));
            list.add(tweet);
        }
        return list;
    }

    public static void main(String[] args) {
        parseJSON(null, null, null);
    }
}
