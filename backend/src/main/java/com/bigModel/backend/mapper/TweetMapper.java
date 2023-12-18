package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.Tweet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TweetMapper extends BaseMapper<Tweet> {
    @Insert("insert into tweet(tweetid, text, keyword, username, flag, type, twitter_id, date) SELECT #{tweetid}, #{text}, #{keyword}, #{username}, #{flag}, #{type}, #{twitterId}, #{date} FROM DUAL where not exists(select * from tweet where tweetid=#{tweetid})")
    void saveTweet(Tweet tweet);
}
