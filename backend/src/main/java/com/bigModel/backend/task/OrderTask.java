package com.bigModel.backend.task;

import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.KeywordService;
import com.bigModel.backend.service.TweetService;
import com.bigModel.backend.service.twitterUser.TwitterUserInfoService;
import com.bigModel.backend.utils.ParseJSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class OrderTask {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private TwitterUserInfoService infoService;
    @Autowired
    private KeywordService keywordService;
    //    测试定时任务
//    每小时
//    @Scheduled(cron = "0/40 * * * * ?")
    @Scheduled(cron = "0 0 7 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void testHello() throws IOException, URISyntaxException, InterruptedException {
        List<TwitterUser> list = infoService.listAll();
        for (int i = 0; i < list.size(); i++) {
            Thread.sleep(60000);
            String twitterId = list.get(i).getTwitterId();
            String username = list.get(i).getUsername();
            System.out.println(username);
            Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
            String encode = "";
            if (p.matcher(twitterId).find()) {
                encode = URLEncoder.encode(twitterId, "utf-8");
            } else {
                encode = twitterId;
            }
            // System.out.println(new Date().toInstant());
            String apiUrl = "https://api.twitter.com/2/users/" + encode + "/tweets?max_results=5&expansions=referenced_tweets.id";
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
            List<Tweet> tweetList = ParseJSONUtil.parseJSON(data, username, twitterId);
            tweetService.saveTweet(tweetList);
        }
        this.keywordMatch();
        this.modeling();
    }

    public void keywordMatch() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = df.format(now);
        List<Keyword> keywordList = keywordService.listAllKeywords();
        List<Tweet> tweetList = tweetService.getTweetByDate(date);
        for (int i = 0; i < tweetList.size(); i ++) {
            List<String> list = new ArrayList<>();
            int id = tweetList.get(i).getId();
            for (int j = 0; j < keywordList.size(); j ++) {
                String keyword = keywordList.get(j).getKeyword();
                if (tweetService.checkKeyword(id, keyword)) {
                    list.add(keyword);
                }
            }
            if (list.size() > 0) {
                tweetService.updateReturn(id);
                tweetService.saveKeywordList(id, list);
            }
        }
    }

    public void modeling() {

    }
}
