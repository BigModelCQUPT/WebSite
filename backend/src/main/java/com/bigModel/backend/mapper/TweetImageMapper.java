package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.TweetImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;

@Mapper
public interface TweetImageMapper extends BaseMapper<TweetImage> {
    @Insert("insert into tweet_image(tweetid, url) SELECT #{tweetid}, #{url} FROM DUAL where not exists(select * from tweet_image where tweetid=#{tweetid});")
    @ResultType(Integer.class)
    int insertImage(TweetImage tweetImage);
}
