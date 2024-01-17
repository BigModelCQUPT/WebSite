package com.bigModel.backend.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data // 自动添加构造函数
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    @TableId(type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("推文id")
    private String tweetid;
    @ExcelProperty("文章内容")
    private String text;
    @ExcelProperty("关键词")
    private String keyword;
    @ExcelProperty("用户名")
    private String username;
    private Integer flag;
    @ExcelProperty("推文类型")
    private String type;
    private String twitterId;
    private Integer needReturn;
    @ExcelIgnore
    private String category;
    private String url;
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private Date publishTime;
    private String uuid;
    @ExcelProperty("反馈原因")
    private String feedbackReason;
}