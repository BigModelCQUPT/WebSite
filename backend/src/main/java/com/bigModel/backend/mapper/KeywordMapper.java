package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.KeywordTrend;
import com.bigModel.backend.pojo.TopicKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KeywordMapper extends BaseMapper<Keyword> {
    @Select("SELECT number as value, forward_keyword as name FROM keyword\n" +
            "ORDER BY number DESC")
    List<TopicKeyword> getTopicKeyword();
    @Select("SELECT forward_keyword, backward_keyword, number as cnt, CURRENT_DATE as nowDate\n" +
            "from keyword\n")
    List<KeywordTrend> listAllKeyword();
}
