package com.bigModel.backend.task;

import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.TwitterUser;
import com.bigModel.backend.service.KeywordService;
import com.bigModel.backend.service.TokenService;
import com.bigModel.backend.service.TweetService;
import com.bigModel.backend.service.twitterUser.TwitterUserInfoService;
import com.bigModel.backend.utils.MailUtil;
import com.bigModel.backend.utils.ParseJSONUtil;
import com.bigModel.backend.utils.chatGPT;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class OrderTask {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private TwitterUserInfoService infoService;
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private TokenService tokenService;

    // @Scheduled(cron = "0/5 * * * * ?") // 定时 5秒
   @Scheduled(cron = "0 */10 * * * ?") // 定时 10分钟
    // @Scheduled(cron = "0 0 * * * ?") // 整点
    // @Scheduled(cron = "0 */5 * * * ?") // 5分钟一次
    @Transactional(rollbackFor = Exception.class)
    public void TwitterHello() throws Exception {
        List<TwitterUser> list = infoService.listAll();
        // String token = "13893747a348d8fc";
        String token = tokenService.getToken("twitterToken");
        String uuid = UUID.randomUUID().toString();
        // for (int i = 0; i < 5; i++) { // 把测试用户放在用户第一个 只爬第一个用户的推文
        for (int i = 0; i < list.size(); i++) {
            log.info("查找用户 ：" + list.get(i).getUsername());
            String twitterId = list.get(i).getTwitterId();
            String username = list.get(i).getUsername();
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url("http://121.199.7.165:13422/twitter/user_timeline_byid?token=" + token + "&userId=" + twitterId+ "&max_id=")
                    .method("GET", null)
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();
            ResponseBody res = response.body();
            List<Tweet> tweetList = ParseJSONUtil.parseJSON(res.string(), username, twitterId, uuid);
            tweetService.saveTweet(tweetList);
        }
        this.keywordMatch(uuid);
        this.modeling(uuid);
        this.noticeMail(uuid);
        DingDingTask.DingDingMessage(uuid);
    }

    public void keywordMatch(String uuid) {
        List<Tweet> tweetList = tweetService.listByUuid(uuid);
        System.out.println(tweetList.size());
        List<Keyword> keywordList = keywordService.listAllKeywords();
        System.out.println(keywordList.size());
        for (int i = 0; i < tweetList.size(); i ++) {
            //        触发哪些keyword
            boolean flag = false;
            int pos = -1;
            for (int j = 0; j < keywordList.size(); j ++) {
                if (keywordList.get(j).getBackwardKeyword() != null && tweetList.get(i).getText().contains(keywordList.get(j).getBackwardKeyword())) {
                    break;
                }
                String[] keyword = keywordList.get(j).getForwardKeyword().split("，");
                int cnt = 0;
                for (int k = 0; k < keyword.length; k ++) {
                    if (tweetList.get(i).getText().contains(keyword[k])) {
                        cnt++;
                    }
                }
                if (cnt == keyword.length) {
                    flag = true;
                    pos = j;
                    break;
                }
            }
            if (flag) {
                tweetService.updateReturn(tweetList.get(i).getId());
                tweetService.saveKeywordList(tweetList.get(i).getId(), keywordList.get(pos).getForwardKeyword());
                String res = "触发关键词 [" + keywordList.get(pos).getForwardKeyword() + "]";
                tweetService.updateReason(tweetList.get(i).getId(), res);
            }
        }
    }

    // TODO chatgpt 分析
//    如果没有 keyword 没有匹配 就启用chatgpt
    public void modeling(String uuid) {
        List<Tweet> tweetList = tweetService.listAllNoReturn(uuid);
        for(int i = 0;i < tweetList.size();i++){
            Integer id = tweetList.get(i).getId();
            String content = tweetList.get(i).getText();
            HashMap<String, String> answerHash = chatGPT.getAnswer(content);
            String needReturn = answerHash.get("answer");
            if(needReturn.equals("是") || needReturn.equals("是。")){
                tweetService.updateReturn(id);
                String res = "AI分析返回";
                tweetService.updateReason(tweetList.get(i).getId(), res);
            }
        }
    }

    public void noticeMail(String uuid) throws Exception {
        List<Tweet> tweetList = tweetService.listByUuid(uuid);
        List<Tweet> needNotice = new ArrayList<>();
        for (Tweet tweet: tweetList) {
            if (tweet.getNeedReturn() == 1) {
                needNotice.add(tweet);
            }
        }
        if (needNotice.size() > 0) {
            MailUtil.sendMail(needNotice);
        }
    }
}
