package com.bigModel.backend.mapper;

import com.bigModel.backend.pojo.TweetImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TweetImageMapperTest {
    @Autowired
    private TweetImageMapper tweetImageMapper;
    @Test
    void insertImage() {
        TweetImage tweetImage = new TweetImage();
        tweetImage.setTweetid("1745027202943750436");
        tweetImage.setUrl("https://pbs.twimg.com/media/FSZq6fmX0AEJRoY.png");
        System.out.println(tweetImageMapper.insertImage(tweetImage));
    }
}