package com.bigModel.backend.task;

import com.bigModel.backend.config.filter.JwtAuthenticationTokenFilter;
import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.User;
import com.bigModel.backend.service.TweetService;
import com.bigModel.backend.utils.DingTalkNoticeUtil;
import com.bigModel.backend.utils.MailUtil;
import com.bigModel.backend.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DingDingTask {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserMapper userMapper;

    // @Scheduled(cron = "*/5 * * * * ?")
//    @Scheduled(cron = "*/20 * * * * ?")
    @Scheduled(cron = "0 15 10 ? * 6L ")
    public void DingDingMessage() throws Exception {
        List<Tweet> tweetsList = tweetService.getAllTweet();
        for(int i = 0;i < tweetsList.size();i++){
            int needReturn = tweetsList.get(i).getNeedReturn();
            if(needReturn == 1){
                String content = tweetsList.get(i).getText();
                DingTalkNoticeUtil.sendNotice();
                // sendMail();
//                sendMessage(content);
            }
        }
    }

    // public void sendMail() throws Exception {
    //     Token token = tokenMapper.selectById(1);
    //     MailUtil.sendMail(token.getMailToken());
    // }

    public void sendMessage(String content){
        Integer currentUserId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        User user = userMapper.selectById(currentUserId);
        String phone = user.getPhone();
        System.out.println(phone);
        MessageUtil.sendMessage(phone, content);
    }
}
