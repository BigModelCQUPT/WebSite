package com.bigModel.backend.utils;

import com.bigModel.backend.pojo.Tweet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailUtilTest {

    @Test
    void sendMail() throws Exception {
        List<Tweet> list = new ArrayList<>();
        Tweet tweet = new Tweet();
        tweet.setTweetid("1707416653095735754");
        list.add(tweet);
        MailUtil.sendMail(list);
    }
}