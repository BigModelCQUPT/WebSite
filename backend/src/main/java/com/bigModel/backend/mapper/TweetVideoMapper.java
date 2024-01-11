package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.TweetImage;
import com.bigModel.backend.pojo.TweetVideo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TweetVideoMapper extends BaseMapper<TweetVideo> {
}
