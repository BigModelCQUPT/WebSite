package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.Tweet;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Daniel
 * @Description:
 * @Date: 2023/11/29 19:00
 * @Email: lxb2000m@gmail.com
 */

@Mapper
public interface TweetMapper extends BaseMapper<Tweet> {
}
