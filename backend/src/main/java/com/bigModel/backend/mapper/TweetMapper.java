package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.Tweet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Select;
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f

@Mapper
public interface TweetMapper extends BaseMapper<Tweet> {
    @Insert("insert into tweet(tweetid, text, keyword, username, flag, type, twitter_id, date) SELECT #{tweetid}, #{text}, #{keyword}, #{username}, #{flag}, #{type}, #{twitterId}, #{date} FROM DUAL where not exists(select * from tweet where tweetid=#{tweetid})")
    void saveTweet(Tweet tweet);
<<<<<<< HEAD
=======
    @Select("SELECT \n" +
            "    CASE \n" +
            "        WHEN COUNT(*) > 0 THEN TRUE \n" +
            "        ELSE FALSE \n" +
            "    END \n" +
            "FROM \n" +
            "    youtube\n" +
            "WHERE \n" +
            "    id = #{id} and summary like '%${keyword}%';\n")
    int checkKeyword(Integer id, String keyword);
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
}
