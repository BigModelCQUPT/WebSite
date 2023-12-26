package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.YoutubeVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface YoutubeVideoMapper extends BaseMapper<YoutubeVideo> {

    @Select("SELECT \n" +
            "    CASE \n" +
            "        WHEN COUNT(*) > 0 THEN TRUE \n" +
            "        ELSE FALSE \n" +
            "    END \n" +
            "FROM \n" +
            "    tweet\n" +
            "WHERE \n" +
            "    id = #{id} and text like '%${keyword}%';\n")
    int checkKeyword(Integer id, String keyword);
}
