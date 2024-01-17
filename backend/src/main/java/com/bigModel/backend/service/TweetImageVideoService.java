package com.bigModel.backend.service;

import com.bigModel.backend.pojo.TweetImage;
import com.bigModel.backend.pojo.TweetVideo;

import java.util.List;

public interface TweetImageVideoService {
    boolean addTweetImage(TweetImage tweetImage);
    boolean addTweetVideo(TweetVideo tweetVideo);
    List<TweetImage> listImagesByTweetid(String tweetid);
    List<TweetVideo> listVideosByTweetid(String tweetid);
}
