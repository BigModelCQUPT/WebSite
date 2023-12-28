package com.bigModel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 自动添加构造函数
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String mailToken;
    private String twitterToken;
    private String youtubeToken;

}
