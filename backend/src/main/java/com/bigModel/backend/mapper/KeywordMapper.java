package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.TopicKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KeywordMapper extends BaseMapper<Keyword> {
    @Select("SELECT number as value, keyword as name FROM keyword\n" +
            "ORDER BY number DESC")
    List<TopicKeyword> getTopicKeyword();
}
