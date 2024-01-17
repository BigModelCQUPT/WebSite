package com.bigModel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private LocalDateTime releaseTime;
    private String feedbackReason;
}
