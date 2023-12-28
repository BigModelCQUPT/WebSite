package com.bigModel.backend.task;

import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.service.TweetService;
import com.bigModel.backend.utils.DingTalkNoticeUtil;
import com.bigModel.backend.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DingDingTask {
    @Autowired
    private TweetService tweetService;

    // @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(cron = "0 0 7 * * ?")
    public void DingDingMessage() throws Exception {
        List<Tweet> tweetsList = tweetService.getAllTweet();
        for(int i = 0;i < tweetsList.size();i++){
            int id = tweetsList.get(i).getId();
            String content = tweetsList.get(i).getText();
            int needReturn = tweetsList.get(i).getNeedReturn();
            if(needReturn == 1){
                DingTalkNoticeUtil.sendNotice();
                // sendMail();
            }
        }
    }

    // public void sendMail() throws Exception {
    //     Token token = tokenMapper.selectById(1);
    //     MailUtil.sendMail(token.getMailToken());
    // }
}
