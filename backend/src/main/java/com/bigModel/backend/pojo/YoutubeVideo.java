package com.bigModel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("youtube")
public class YoutubeVideo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String coverUrl;
    private String username;
    private String channelId;
    private String videoUrl;
    private String title;
    private String summary;
    private Integer needReturn;
    private String keyword;
    private Integer flag;
}
