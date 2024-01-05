package com.bigModel.backend.utils;

import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.TokenService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

@Component
public class UsernameToInfoUtil {

    @Autowired
    private static TokenService tokenService;

    public static TwitterUser getInfoByUsername(String username) throws IOException {
        String token = tokenService.getToken("twitterToken");
        // Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        // String encode = "";
        // if(p.matcher(username).find()){
        //     encode = URLEncoder.encode(username, "utf-8");
        // }
        // else{
        //     encode = username;
        // }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://106.227.5.196:13422/twitter/user_timeline_byname?token=" + token + "&screen_name=" + username + "&max_id=")
                .method("GET", null)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        ResponseBody res = response.body();
        String data = res.string();

        JSONArray dataList = new JSONArray(new JSONObject(data).getString("data").toString());
        JSONObject user = new JSONObject(dataList.get(0).toString()).getJSONObject("user");
        String twitterId = user.getString("id");
        String name = user.getString("name");
        TwitterUser twitterUser = new TwitterUser();
        twitterUser.setName(name);
        twitterUser.setUsername(username);
        twitterUser.setTwitterId(twitterId);
        return twitterUser;
    }
}
