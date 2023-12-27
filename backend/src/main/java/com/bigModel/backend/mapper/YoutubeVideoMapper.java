package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.YoutubeVideo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YoutubeVideoMapper extends BaseMapper<YoutubeVideo> {
}
