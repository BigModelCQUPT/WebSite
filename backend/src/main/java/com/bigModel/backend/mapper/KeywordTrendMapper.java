package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.KeywordTrend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KeywordTrendMapper extends BaseMapper<KeywordTrend> {
    @Select("SELECT * FROM `keyword_date`\n" +
            "where now_date = CURDATE()\n" +
            "ORDER BY cnt DESC\n" +
            "LIMIT 5")
    List<KeywordTrend> top5KeywordByCnt();
    @Select("SELECT * FROM `keyword_date`\n" +
            "where keyword = #{keyword}\n" +
            "ORDER BY now_date ASC\n" +
            "LIMIT 7")
    List<KeywordTrend> weekTrendByKeyword(String keyword);
}
