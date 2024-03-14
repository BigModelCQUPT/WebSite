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

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class DingDingTask {

    private static DingDingTask dingDingTask;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserMapper userMapper;

    @PostConstruct
    public void init() {
        dingDingTask = this;
        dingDingTask.userMapper = this.userMapper;
        dingDingTask.tweetService = this.tweetService;
    }

    public static void DingDingMessage() throws Exception {
        List<Tweet> tweetsList = dingDingTask.tweetService.getAllTweet();
        for(int i = 0;i < tweetsList.size();i++){
            int needReturn = tweetsList.get(i).getNeedReturn();
            if(needReturn == 1){
                class MyMap<K,T> extends HashMap<K,T> {
                    @Override
                    public String toString() {
                        String ans = "";
                        for (Map.Entry<K,T> entry : entrySet())
                        {
                            ans += entry.getKey() + ": " + entry.getValue() + ",\n";
                        }
                        return ans;
                    }
                }
                MyMap<String, String> content = new MyMap<>();
                content.put("风险提醒原因", tweetsList.get(i).getFeedbackReason());
                content.put("发帖链接", tweetsList.get(i).getUrl());
                content.put("发帖时间", tweetsList.get(i).getPublishTime().toString());
                content.put("发帖ID", tweetsList.get(i).getTwitterId());
                content.put("发帖内容", tweetsList.get(i).getText());
                content.put("平台", "推特");
                DingTalkNoticeUtil.sendNotice(content.toString());
            }
        }
    }


    public void sendMessage(String content){
        Integer currentUserId = JwtAuthenticationTokenFilter.getUserBasic().getUserId();
        User user = userMapper.selectById(currentUserId);
        String phone = user.getPhone();
        System.out.println(phone);
        MessageUtil.sendMessage(phone, content);
    }
}
