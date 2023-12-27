package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.YoutubeUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YoutubeUserMapper extends BaseMapper<YoutubeUser> {
}
