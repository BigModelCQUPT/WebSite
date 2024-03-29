package com.bigModel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 自动添加构造函数
@NoArgsConstructor
@AllArgsConstructor
public class TwitterUser {

    // 如果需要自增必须增加的注解 Mybatis-plus 里面的
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String twitterId;
    private String username;
    private String name;
    private String description;
    private Integer followersCount;

}
