package com.bigModel.backend.utils;

import com.bigModel.backend.pojo.TwitterUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import twitter4j.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class UsernameToInfoUtil {

    public static TwitterUser getInfoByUsername(String username) throws UnsupportedEncodingException, URISyntaxException {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        String encode = "";
        if(p.matcher(username).find()){
            encode = URLEncoder.encode(username, "utf-8");
        }
        else{
            encode = username;
        }
        String apiUrl = "https://api.twitter.com/2/users/by/username/" + encode;
        String bearerToken = "Bearer AAAAAAAAAAAAAAAAAAAAAIRBrAEAAAAAgU%2BrSU6jqqQhmNjGbP9Vw24qUfI%3DyeaoWbeaoWvguoY0PWb56I0NFoGYbwIf8M9alsTWyuqThfi3Tq";
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
        headers.add("Authorization", bearerToken);
        headers.add("Accept", "*/*");
        headers.add("Host", "api.twitter.com");
        headers.add("Connection", "keep-alive");
        RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(apiUrl));
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        String data = responseEntity.getBody();
        data = new JSONObject(data).getString("data");
        JSONObject json = new JSONObject(data);
        String twitterId = json.getString("id");
        String name = json.getString("name");
        TwitterUser twitterUser = new TwitterUser();
        twitterUser.setName(name);
        twitterUser.setUsername(username);
        twitterUser.setTwitterId(twitterId);
        return twitterUser;
    }
}
