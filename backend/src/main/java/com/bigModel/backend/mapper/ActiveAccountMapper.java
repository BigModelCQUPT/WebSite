package com.bigModel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bigModel.backend.pojo.ActiveAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActiveAccountMapper extends BaseMapper<ActiveAccount> {
    @Select("SELECT COUNT(*) as tweetCnt, username FROM tweet\n" +
//            "where date <=  CURDATE() and date >=  CURDATE()- 7\n" +
            "GROUP BY username\n" +
            "ORDER BY tweetCnt DESC")
    List<ActiveAccount> getActiveAccount();
}
