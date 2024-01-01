package com.bigModel.backend.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistory {
    @TableId(type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("昵称")
    private String nickname;
    @ExcelProperty("用户名")
    private String username;
    @ColumnWidth(10)
    @ExcelProperty("用户id")
    private String userId;
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("消息发送时间")
    @ColumnWidth(20)
    private Date postalTime;
    @ExcelProperty("消息内容")
    private String message;
    @ExcelProperty("群组名称")
    private String groupName;
    @ColumnWidth(10)
    @ExcelProperty("群组id")
    private String groupId;
}