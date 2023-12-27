package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.TwitterUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TwitterUserInfoMapper extends BaseMapper<TwitterUser> {
}
