package com.bigModel.backend.task;

import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.KeywordService;
import com.bigModel.backend.service.TweetService;
import com.bigModel.backend.service.twitterUser.TwitterUserInfoService;
import com.bigModel.backend.utils.ParseJSONUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
//     @Scheduled(cron = "0 0 7 * * ?")
    @Scheduled(cron = "0 0 7 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void testHello() throws Exception {
        List<TwitterUser> list = infoService.listAll();
        String token = "13893747a348d8fc";
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
            String twitterId = list.get(i).getTwitterId();
            String username = list.get(i).getUsername();
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://106.227.5.196:13422/twitter/user_timeline_byid?token=" + token + "&userId=" + twitterId+ "&max_id=")
                    .method("GET", null)
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            ResponseBody res = response.body();
            List<Tweet> tweetList = ParseJSONUtil.parseJSON(res.string(), username, twitterId);
            // tweetList.forEach(System.out::println);
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
