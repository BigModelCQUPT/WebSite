package com.bigModel.backend.task;

import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.service.TweetService;
import com.bigModel.backend.utils.ParseJSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderTask {

    @Autowired
    private TweetService tweetService;
    //    测试定时任务
//    每小时
//    @Scheduled(cron = "0/40 * * * * ?")
    @Scheduled(cron = "0 0 12 * * ?")
    public void testHello() throws IOException, URISyntaxException {
        String keyword = "中共";
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        String encode = "";
        if(p.matcher(keyword).find()){
            encode = URLEncoder.encode(keyword, "utf-8");
        }
        else{
            encode = keyword;
        }
        System.out.println(new Date().toInstant());
        String apiUrl = "https://api.twitter.com/2/tweets/search/recent?query=" + encode;
        String bearerToken = "Bearer AAAAAAAAAAAAAAAAAAAAAIRBrAEAAAAAgU%2BrSU6jqqQhmNjGbP9Vw24qUfI%3DyeaoWbeaoWvguoY0PWb56I0NFoGYbwIf8M9alsTWyuqThfi3Tq";

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
        headers.add("Authorization", bearerToken);
        headers.add("Accept", "*/*");
        headers.add("Host", "api.twitter.com");
        headers.add("Connection", "keep-alive");

        RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(apiUrl));

        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);

        // 处理响应，可以通过responseEntity.getBody()获取响应内容等
        String data = responseEntity.getBody();
        List<Tweet> list = ParseJSONUtil.parseJSON(data, keyword);
        tweetService.saveTweet(list);
    }

}
