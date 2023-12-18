package com.bigModel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // 自动添加构造函数
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String tweetid;
    private String text;
    private String username;
    private Integer flag;
    private String type;
    private String keyword;
    private String twitterId;
    private String date;
}