package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.TweetImage;
import com.bigModel.backend.pojo.TweetVideo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;

@Mapper
public interface TweetVideoMapper extends BaseMapper<TweetVideo> {
    @Insert("insert into tweet_video(tweetid, url) SELECT #{tweetid}, #{url} FROM DUAL where not exists(select * from tweet_video where tweetid=#{tweetid});")
    @ResultType(Integer.class)
    int insertVideo(TweetVideo tweetVideo);
}
